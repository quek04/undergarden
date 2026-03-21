package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbelcherModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.RotbelcherRenderState;
import quek.undergarden.entity.monster.rotspawn.Rotbelcher;

public class RotbelcherRenderer extends MobRenderer<Rotbelcher, RotbelcherRenderState, RotbelcherModel> {

	private static final Identifier ROTBELCHER = Undergarden.prefix("textures/entity/rotbelcher.png");
	private static final RenderType ROTBELCHER_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/rotbelcher_eyes.png"));

	public RotbelcherRenderer(EntityRendererProvider.Context context) {
		super(context, new RotbelcherModel(context.bakeLayer(UGModelLayers.ROTBELCHER)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, ROTBELCHER_EYES));
	}

	@Override
	public RotbelcherRenderState createRenderState() {
		return new RotbelcherRenderState();
	}

	@Override
	public void extractRenderState(Rotbelcher entity, RotbelcherRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isCharging = entity.isCharging();
		state.attackAnimationState.copyFrom(entity.attackAnimation);
		state.shootAnimationState.copyFrom(entity.shootAnimation);
	}

	@Override
	public Identifier getTextureLocation(RotbelcherRenderState state) {
		return ROTBELCHER;
	}

	@Override
	protected void setupRotations(RotbelcherRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
		super.setupRotations(state, poseStack, bodyRot, entityScale);
		if (!(state.walkAnimationSpeed < 0.01D)) {
			float wp = state.walkAnimationPos + 6.0F;
			float triangleWave = (Math.abs(wp % 13.0F - 6.5F) - 3.25F) / 3.25F;
			poseStack.mulPose(Axis.ZP.rotationDegrees(6.5F * triangleWave));
		}
	}
}
