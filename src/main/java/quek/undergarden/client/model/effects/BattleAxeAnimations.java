package quek.undergarden.client.model.effects;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Ease;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class BattleAxeAnimations {


	private static float progress(float time, float start, float end) {
		return Mth.clamp(Mth.inverseLerp(time, start, end), 0.0F, 1.0F);
	}

	public static void thirdPersonAttackHand(HumanoidModel<?> model, HumanoidRenderState entity, HumanoidArm arm) {
		float attackTime = entity.attackTime;

		boolean holdingInRightArm = entity.mainArm == HumanoidArm.RIGHT;
		ModelPart mainArm = holdingInRightArm ? model.rightArm : model.leftArm;
		ModelPart otherArm = holdingInRightArm ? model.leftArm : model.rightArm;
		float prepare = Ease.inOutSine(progress(attackTime, 0.0F, 0.05F));
		float attack = Ease.inQuad(progress(attackTime, 0.05F, 0.3F));
		float retract = Ease.inQuad(progress(attackTime, 0.3F, 1.0F));

		float animationRot = (90.0F * prepare - 120.0F * attack + 30.0F * retract) * ((float) Math.PI / 180F);
		float animationOtherHandRot = (90.0F * prepare + 30.0F * attack - 120.0F * retract) * ((float) Math.PI / 180F);

		//It's used to recreate the momentum of a swing animation.
		float animationSwingingRot = (120.0F * attack - 120.0F * retract) * ((float) Math.PI / 180F);
		float animationOtherHandSwingingRot = (120.0F * prepare - 120.0F * retract) * ((float) Math.PI / 180F);

		mainArm.xRot = -1.3F - animationRot * 0.6F + animationSwingingRot * 0.6F;
		mainArm.yRot = holdingInRightArm ? 0.3F : -0.3F;
		mainArm.zRot = holdingInRightArm ? -1.0F - animationSwingingRot * 0.2F : 1.0F + animationSwingingRot * 0.2F;

		otherArm.xRot = -0.5F - animationRot * 0.3F - animationOtherHandRot * 0.3F + animationOtherHandSwingingRot * 0.8F;
		otherArm.yRot = holdingInRightArm ? 0.7F + animationRot * 0.2F : -0.7F - animationRot * 0.2F;

	}
}
