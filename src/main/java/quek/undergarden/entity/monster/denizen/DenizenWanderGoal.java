package quek.undergarden.entity.monster.denizen;

import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class DenizenWanderGoal extends WaterAvoidingRandomStrollGoal {
	public DenizenWanderGoal(Denizen denizen, double modifier) {
		super(denizen, modifier);
	}

	@Override
	public boolean canUse() {
		if (((Denizen)this.mob).getStareTarget() != null || this.mob.getTarget() != null || this.mob.hasPose(Pose.SITTING)) return false;
		return super.canUse();
	}
}
