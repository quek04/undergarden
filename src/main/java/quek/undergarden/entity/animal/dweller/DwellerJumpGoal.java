package quek.undergarden.entity.animal.dweller;

import net.minecraft.world.entity.ai.goal.JumpGoal;

public class DwellerJumpGoal extends JumpGoal {

	private final Dweller dweller;

	public DwellerJumpGoal(Dweller dweller) {
		this.dweller = dweller;
	}

	@Override
	public boolean canUse() {
		return !this.dweller.getMoveControl().hasWanted() && this.isAbleToFreelyJump() && this.hasEnoughRoomToJump();
	}

	@Override
	public boolean canContinueToUse() {
		return false;
	}

	@Override
	public void start() {
		this.dweller.jump(true);
		this.dweller.setWildJumpCooldown(this.dweller.getRandom().nextInt(500) + 300);
	}

	private boolean isAbleToFreelyJump() {
		if (!this.dweller.getPassengers().isEmpty() || this.dweller.getWildJumpCooldown() > 0 || !this.dweller.onGround()) return false;
		return this.dweller.getPanicGoal() != null && !this.dweller.getPanicGoal().isRunning() &&
				this.dweller.getAvoidGoal() != null && !this.dweller.getAvoidGoal().isRunning();
	}

	private boolean hasEnoughRoomToJump() {
		for (int i = 1; i < 7; i++) {
			if (!this.dweller.level().getBlockState(this.dweller.blockPosition().above(i)).getCollisionShape(this.dweller.level(), this.dweller.blockPosition().above(i)).isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
