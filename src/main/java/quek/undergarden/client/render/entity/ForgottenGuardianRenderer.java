package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Difficulty;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenGuardianModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.ForgottenGuardianRenderState;
import quek.undergarden.entity.monster.boss.ForgottenGuardian;

public class ForgottenGuardianRenderer extends MobRenderer<ForgottenGuardian, ForgottenGuardianRenderState, ForgottenGuardianModel> {

	private static final Identifier FORGOTTEN_GUARDIAN = Undergarden.prefix("textures/entity/forgotten_guardian.png");
	private static final RenderType FORGOTTEN_GUARDIAN_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/forgotten_guardian_eyes.png"));

	public ForgottenGuardianRenderer(EntityRendererProvider.Context context) {
		super(context, new ForgottenGuardianModel(context.bakeLayer(UGModelLayers.FORGOTTEN_GUARDIAN)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, FORGOTTEN_GUARDIAN_EYES, state -> state.isActive));
	}

	@Override
	public ForgottenGuardianRenderState createRenderState() {
		return new ForgottenGuardianRenderState();
	}

	@Override
	public void extractRenderState(ForgottenGuardian entity, ForgottenGuardianRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.attackTimer = entity.getAttackTimer() > 0.0F ? entity.getAttackTimer() - partialTicks : 0.0F;
		state.isActive = entity.level().getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	public Identifier getTextureLocation(ForgottenGuardianRenderState state) {
		return FORGOTTEN_GUARDIAN;
	}

	@Override
	protected void setupRotations(ForgottenGuardianRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
		super.setupRotations(state, poseStack, bodyRot, entityScale);
		if (!(state.walkAnimationSpeed < 0.01D)) {
			float wp = state.walkAnimationPos + 6.0F;
			float triangleWave = (Math.abs(wp % 13.0F - 6.5F) - 3.25F) / 3.25F;
			poseStack.mulPose(Axis.ZP.rotationDegrees(6.5F * triangleWave));
		}
	}
}