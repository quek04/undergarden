package quek.undergarden.client.model.animation;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Ease;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class BattleaxeAnimation {

	private static float progress(float time, float start, float end) {
		return Mth.clamp(Mth.inverseLerp(time, start, end), 0.0F, 1.0F);
	}

	//TODO swing main hand like normal when holding axe in offhand. Axe should not swing at all when in offhand
	public static void thirdPersonAttackHand(HumanoidModel<?> model, HumanoidRenderState entity, HumanoidArm arm) {
		float attackTime = entity.attackTime;

		boolean holdingInRightArm = entity.mainArm == arm;
		ModelPart mainArm = holdingInRightArm ? model.rightArm : model.leftArm;
		ModelPart otherArm = holdingInRightArm ? model.leftArm : model.rightArm;

		if (entity.attackArm == entity.mainArm) {
			float prepare = Ease.inOutSine(progress(attackTime, 0.0F, 0.05F));
			float attack = Ease.inQuad(progress(attackTime, 0.05F, 0.3F));
			float retract = Ease.inQuad(progress(attackTime, 0.3F, 1.0F));

			float animationRot = (90.0F * prepare - 120.0F * attack + 30.0F * retract) * Mth.DEG_TO_RAD;
			float animationOtherHandRot = (90.0F * prepare + 30.0F * attack - 120.0F * retract) * Mth.DEG_TO_RAD;

			//It's used to recreate the momentum of a swing animation.
			float animationSwingingRot = (120.0F * attack - 120.0F * retract) * Mth.DEG_TO_RAD;
			float animationOtherHandSwingingRot = (120.0F * prepare - 120.0F * retract) * Mth.DEG_TO_RAD;

			mainArm.xRot = -1.3F - animationRot * 0.6F + animationSwingingRot * 0.6F;
			mainArm.zRot = holdingInRightArm ? -1.0F - animationSwingingRot * 0.2F : 1.0F + animationSwingingRot * 0.2F;

			otherArm.xRot = -0.5F - animationRot * 0.3F - animationOtherHandRot * 0.3F + animationOtherHandSwingingRot * 0.8F;
			otherArm.yRot = holdingInRightArm ? 0.7F + animationRot * 0.2F : -0.7F - animationRot * 0.2F;
		} else {
			mainArm.xRot = -1.3F;
			mainArm.yRot = holdingInRightArm ? 0.3F : -0.3F;
			mainArm.zRot = holdingInRightArm ? -1.0F : 1.0F;
			otherArm.xRot = -0.5F;
			otherArm.yRot = holdingInRightArm ? 0.7F : -0.7F;
		}

		AnimationUtils.bobModelPart(model.rightArm, entity.ageInTicks, -1.0F);
		AnimationUtils.bobModelPart(model.leftArm, entity.ageInTicks, 1.0F);
	}
}