package quek.undergarden.entity.monster.denizen;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import quek.undergarden.registry.UGItems;

public class DenizenStareDownTargetGoal extends NearestAttackableTargetGoal<Player> {
	public DenizenStareDownTargetGoal(Denizen denizen) {
		super(denizen, Player.class, true, entity -> !entity.getItemBySlot(EquipmentSlot.HEAD).is(UGItems.DENIZEN_MASK) || denizen.getTarget() == entity);
	}

	@Override
	protected void findTarget() {
		Player nearestPlayer = this.mob.level().getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
		if (nearestPlayer != null) {
			if (this.mob.distanceTo(nearestPlayer) <= this.getFollowDistance() / 2 || this.mob.getTarget() == nearestPlayer) {
				this.target = nearestPlayer;
				((Denizen)this.mob).setStareTarget(null);
			} else {
				((Denizen)this.mob).setStareTarget(nearestPlayer);
				this.mob.getNavigation().stop();
			}
		} else {
			((Denizen)this.mob).setStareTarget(null);
			this.mob.setTarget(null);
		}
	}
}
