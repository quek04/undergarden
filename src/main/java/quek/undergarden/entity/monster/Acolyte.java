package quek.undergarden.entity.monster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Acolyte extends Monster {
	public Acolyte(EntityType<? extends Monster> type, Level level) {
		super(type, level);
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
//		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, RotspawnMonster.class, true));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 20.0D)
			.add(Attributes.ATTACK_DAMAGE, 2.0D)
			.add(Attributes.ARMOR, 2.0D)
			.add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public void aiStep() {
		if (this.level().isClientSide()) {
			for (int i = 0; i < 2; i++) {
				this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.2), this.getY() + this.getEyeHeight(), this.getRandomZ(0.2), 0.0, 0.0, 0.0);
			}
		}
		super.aiStep();
	}
}