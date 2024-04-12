package quek.undergarden.entity.monster.denizen;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;

public class DenizenStareAtPlayerGoal extends LookAtPlayerGoal {

	public DenizenStareAtPlayerGoal(Denizen denizen) {
		super(denizen, Player.class, 16.0F);
	}

	@Override
	public boolean canUse() {
		if (((Denizen)this.mob).getStareTarget() == null && this.mob.getRandom().nextFloat() >= this.probability) {
			return false;
		} else {
			if (this.mob.getTarget() != null) {
				this.lookAt = this.mob.getTarget();
			}

			if (this.lookAtType == Player.class) {
				this.lookAt = this.mob.level().getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
			}

			if (this.lookAt != null && this.lookAt == ((Denizen)this.mob).getStareTarget()) {
				return true;
			}

			return this.lookAt != null;
		}
	}
}
