package quek.undergarden.entity.monster.stoneborn.trading;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import quek.undergarden.UGRegistries;

import java.util.Optional;

public record StonebornTradeSet(HolderSet<StonebornTrade> trades, NumberProvider amount, boolean allowDuplicates, Optional<Identifier> randomSequence) {

	public static final Codec<StonebornTradeSet> CODEC = RecordCodecBuilder.create(
		i -> i.group(
			RegistryCodecs.homogeneousList(UGRegistries.Keys.STONEBORN_TRADE).fieldOf("trades").forGetter(StonebornTradeSet::trades),
			NumberProviders.CODEC.fieldOf("amount").forGetter(StonebornTradeSet::amount),
			Codec.BOOL.optionalFieldOf("allow_duplicates", false).forGetter(StonebornTradeSet::allowDuplicates),
			Identifier.CODEC.optionalFieldOf("random_sequence").forGetter(StonebornTradeSet::randomSequence)
		).apply(i, StonebornTradeSet::new));

	public int calculateNumberOfTrades(LootContext lootContext) {
		return this.amount.getInt(lootContext);
	}
}
