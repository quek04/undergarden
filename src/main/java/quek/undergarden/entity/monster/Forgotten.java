package quek.undergarden.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.entity.monster.rotspawn.RotspawnMonster;
import quek.undergarden.registry.UGItems;
import quek.undergarden.registry.UGSoundEvents;

import java.util.Arrays;

public class Forgotten extends Monster {

	public Forgotten(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		Arrays.fill(this.armorDropChances, 0.03F);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, RotspawnMonster.class, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D)
				.add(Attributes.ARMOR, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return UGSoundEvents.FORGOTTEN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return UGSoundEvents.FORGOTTEN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UGSoundEvents.FORGOTTEN_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(UGSoundEvents.FORGOTTEN_STEP.get(), 0.15F, 1.0F);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		if (random.nextInt(50) == 0) {
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(UGItems.CLOGGRUM_BATTLEAXE.get()));
			this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(UGItems.ANCIENT_HELMET.get()));
			this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(UGItems.ANCIENT_CHESTPLATE.get()));
			this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(UGItems.ANCIENT_LEGGINGS.get()));
			this.setDropChance(EquipmentSlot.MAINHAND, 1.0F);
		} else {
			for (EquipmentSlot slot : EquipmentSlot.values()) {
				if (slot.getType() == EquipmentSlot.Type.ARMOR && this.getItemBySlot(slot).isEmpty() && random.nextBoolean()) {
					Item armor = switch (slot) {
						case HEAD -> UGItems.ANCIENT_HELMET.get();
						case CHEST -> UGItems.ANCIENT_CHESTPLATE.get();
						case LEGS -> UGItems.ANCIENT_LEGGINGS.get();
						default -> Items.AIR;
					};
					this.setItemSlot(slot, new ItemStack(armor));
				}
			}
			if (random.nextBoolean()) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(UGItems.CLOGGRUM_SWORD.get()));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(UGItems.CLOGGRUM_AXE.get()));
			}
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		data = super.finalizeSpawn(level, difficulty, type, data, tag);
		this.populateDefaultEquipmentSlots(level.getRandom(), difficulty);
		this.populateDefaultEquipmentEnchantments(level.getRandom(), difficulty);
		return data;
	}

	@Override
	public boolean canPickUpLoot() {
		return false;
	}
}