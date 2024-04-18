package quek.undergarden.item.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;
import quek.undergarden.entity.projectile.ThrownSpear;

import java.util.UUID;

public class SpearItem extends Item implements Vanishable {

	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	private static final UUID ENTITY_REACH_UUID = UUID.fromString("cfa9de08-8bcc-48f0-ad6d-87c5df22ccfe");

	public SpearItem() {
		super(new Properties()
			.stacksTo(1)
			.durability(250)
			.rarity(Rarity.UNCOMMON)
		);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 6.0, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.9F, AttributeModifier.Operation.ADDITION));
		builder.put(NeoForgeMod.ENTITY_REACH.value(), new AttributeModifier(ENTITY_REACH_UUID, "Tool modifier", 2.0, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (entity instanceof Player player) {
			int useTime = this.getUseDuration(stack) - timeLeft;
			if (useTime >= 10) {
				if (!level.isClientSide()) {
					stack.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(player1.getUsedItemHand()));

					ThrownSpear spear = new ThrownSpear(level, player, stack);
					spear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);

					if (player.getAbilities().instabuild) {
						spear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
					}

					level.addFreshEntity(spear);
					level.playSound(null, spear, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);

					if (!player.getAbilities().instabuild) {
						player.getInventory().removeItem(stack);
					}
				}

				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
			return InteractionResultHolder.fail(stack);
		} else {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(stack);
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		return true;
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity livingEntity) {
		if ((double)state.getDestroySpeed(level, pos) != 0.0) {
			stack.hurtAndBreak(2, livingEntity, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		}

		return true;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
	}

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ToolAction toolAction) {
		return net.neoforged.neoforge.common.ToolActions.DEFAULT_TRIDENT_ACTIONS.contains(toolAction);
	}
}