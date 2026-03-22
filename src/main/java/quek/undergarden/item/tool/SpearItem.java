package quek.undergarden.item.tool;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.projectile.ThrownSpear;

public class SpearItem extends Item implements ProjectileItem {

	private static final Identifier ENTITY_REACH_UUID = Undergarden.prefix("spear_entity_reach");

	public SpearItem(Properties properties) {
		super(properties);
	}

	public static ItemAttributeModifiers createAttributes() {
		return ItemAttributeModifiers.builder()
			.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 6.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ENTITY_REACH_UUID, 2.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
			.build();
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.TRIDENT;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

	@Override
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (entity instanceof Player player) {
			int useTime = this.getUseDuration(stack, entity) - timeLeft;
			if (useTime >= 10) {
				if (!level.isClientSide()) {
					stack.hurtAndBreak(1, player, entity.getUsedItemHand());

					ThrownSpear spear = new ThrownSpear(level, player, stack);
					spear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);

					if (player.hasInfiniteMaterials()) {
						spear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
					}

					level.addFreshEntity(spear);
					level.playSound(null, spear, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);

					if (!player.hasInfiniteMaterials()) {
						player.getInventory().removeItem(stack);
					}
				}

				player.awardStat(Stats.ITEM_USED.get(this));
			}
		}
		return false;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
			return InteractionResult.FAIL;
		} else {
			player.startUsingItem(hand);
			return InteractionResult.CONSUME;
		}
	}

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
	}

	@Override
	public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {
		return ItemAbilities.DEFAULT_TRIDENT_ACTIONS.contains(itemAbility);
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		ThrownSpear spear = new ThrownSpear(level, pos.x(), pos.y(), pos.z(), stack);
		spear.pickup = AbstractArrow.Pickup.ALLOWED;
		return spear;
	}
}