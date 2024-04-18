package quek.undergarden.entity.monster.denizen;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import quek.undergarden.registry.UGItems;

public class DenizenSpearAttackGoal extends RangedAttackGoal {
	private final Denizen denizen;

	public DenizenSpearAttackGoal(RangedAttackMob rangedAttackMob, double speedModifier, int attackInterval, float attackRadius) {
		super(rangedAttackMob, speedModifier, attackInterval, attackRadius);
		this.denizen = (Denizen) rangedAttackMob;
	}

	@Override
	public boolean canUse() {
		return super.canUse() && this.denizen.getMainHandItem().is(UGItems.SPEAR);
	}

	@Override
	public void start() {
		super.start();
		this.denizen.setAggressive(true);
		this.denizen.startUsingItem(InteractionHand.MAIN_HAND);
	}

	@Override
	public void stop() {
		super.stop();
		this.denizen.stopUsingItem();
		this.denizen.setAggressive(false);
	}
}
