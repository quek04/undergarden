package quek.undergarden.item.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.projectile.ThrownSpear;

public class SpearItem extends Item implements ProjectileItem {

	private static final ResourceLocation ENTITY_REACH_UUID = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "spear_entity_reach");

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
	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
		if (entity instanceof Player player) {
			int useTime = this.getUseDuration(stack, entity) - timeLeft;
			if (useTime >= 10) {
				if (!level.isClientSide()) {
					stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(entity.getUsedItemHand()));

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
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
		return true;
	}

	/*@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity livingEntity) {
		if ((double)state.getDestroySpeed(level, pos) != 0.0) {
			stack.hurtAndBreak(2, livingEntity, entity -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		}

		return true;
	}*/

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
		return ItemAbilities.DEFAULT_TRIDENT_ACTIONS.contains(itemAbility);
	}

	@Override
	public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
		ThrownSpear spear = new ThrownSpear(level, pos.x(), pos.y(), pos.z(), stack);
		spear.pickup = AbstractArrow.Pickup.ALLOWED;
		return spear;
	}
}