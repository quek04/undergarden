package quek.undergarden.entity.monster.stoneborn.trading;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record StonebornTrade(TradeCost wants, Optional<TradeCost> additionalWants, ItemStackTemplate gives, NumberProvider maxUses, NumberProvider xp, Optional<LootItemCondition> merchantPredicate, List<LootItemFunction> givenItemModifiers) implements Validatable {

	public static final Codec<StonebornTrade> CODEC = RecordCodecBuilder.<StonebornTrade>create(i -> i.group(
			TradeCost.CODEC.fieldOf("wants").forGetter(StonebornTrade::wants),
			TradeCost.CODEC.optionalFieldOf("additional_wants").forGetter(StonebornTrade::additionalWants),
			ItemStackTemplate.CODEC.fieldOf("gives").forGetter(StonebornTrade::gives),
			NumberProviders.CODEC.lenientOptionalFieldOf("max_uses", ConstantValue.exactly(4.0F)).forGetter(StonebornTrade::maxUses),
			NumberProviders.CODEC.lenientOptionalFieldOf("xp", ConstantValue.exactly(1.0F)).forGetter(StonebornTrade::xp),
			LootItemCondition.DIRECT_CODEC.optionalFieldOf("merchant_predicate").forGetter(StonebornTrade::merchantPredicate),
			LootItemFunctions.ROOT_CODEC.listOf().optionalFieldOf("given_item_modifiers", List.of()).forGetter(StonebornTrade::givenItemModifiers)
		).apply(i, StonebornTrade::new))
		.validate(Validatable.validatorForContext(LootContextParamSets.VILLAGER_TRADE));

	public StonebornTrade(TradeCost wants, Optional<TradeCost> additionalWants, ItemStackTemplate gives, int maxUses, int xp, Optional<LootItemCondition> merchantPredicate, List<LootItemFunction> givenItemModifiers) {
		this(wants, additionalWants, gives, ConstantValue.exactly(maxUses), ConstantValue.exactly(xp), merchantPredicate, givenItemModifiers);
	}

	public StonebornTrade(TradeCost wants, Optional<TradeCost> additionalWants, ItemStackTemplate gives, int maxUses, int xp, Optional<LootItemCondition> merchantPredicate) {
		this(wants, additionalWants, gives, ConstantValue.exactly(maxUses), ConstantValue.exactly(xp), merchantPredicate, List.of());
	}

	public StonebornTrade(TradeCost wants, ItemStackTemplate gives, int maxUses, int xp, Optional<LootItemCondition> merchantPredicate) {
		this(wants, Optional.empty(), gives, ConstantValue.exactly(maxUses), ConstantValue.exactly(xp), merchantPredicate, List.of());
	}

	public StonebornTrade(TradeCost wants, ItemStackTemplate gives, int maxUses, int xp) {
		this(wants, Optional.empty(), gives, ConstantValue.exactly(maxUses), ConstantValue.exactly(xp), Optional.empty(), List.of());
	}

	@Override
	public void validate(ValidationContext context) {
		Validatable.validate(context, "wants", this.wants);
		Validatable.validate(context, "additional_wants", this.additionalWants);
		Validatable.validate(context, "max_uses", this.maxUses);
		Validatable.validate(context, "xp", this.xp);
		Validatable.validate(context, "merchant_predicate", this.merchantPredicate);
		Validatable.validate(context, "given_item_modifiers", this.givenItemModifiers);
	}

	public @Nullable MerchantOffer getOffer(LootContext lootContext) {
		if (this.merchantPredicate.isPresent() && !this.merchantPredicate.get().test(lootContext)) {
			return null;
		} else {
			ItemStack result = this.gives.create();
			int additionalCost = 0;

			for (LootItemFunction outputItemModifier : this.givenItemModifiers) {
				result = outputItemModifier.apply(result, lootContext);
				if (result.isEmpty()) {
					return null;
				}
			}

			Integer additionalTradeCost = result.remove(DataComponents.ADDITIONAL_TRADE_COST);
			if (additionalTradeCost != null) {
				additionalCost += additionalTradeCost;
			}

			ItemCost itemCost = this.wants.toItemCost(lootContext, additionalCost);
			if (itemCost.count() < 1) {
				return null;
			} else {
				Optional<ItemCost> additionalItemCost = this.additionalWants.map(tradeCost -> tradeCost.toItemCost(lootContext, 0));
				return additionalItemCost.isPresent() && additionalItemCost.get().count() < 1 ? null : new MerchantOffer(itemCost, additionalItemCost, result, Math.max(this.maxUses.getInt(lootContext), 1), Math.max(this.xp.getInt(lootContext), 0), 1.0F);
			}
		}
	}
}
