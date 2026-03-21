package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotwalkerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.monster.rotspawn.Rotwalker;

public class RotwalkerRenderer extends MobRenderer<Rotwalker, LivingEntityRenderState, RotwalkerModel> {

	private static final Identifier ROTWALKER = Undergarden.prefix("textures/entity/rotwalker.png");
	private static final RenderType ROTWALKER_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/rotwalker_eyes.png"));

	public RotwalkerRenderer(EntityRendererProvider.Context context) {
		super(context, new RotwalkerModel(context.bakeLayer(UGModelLayers.ROTWALKER)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, ROTWALKER_EYES));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState entity) {
		return ROTWALKER;
	}

	@Override
	protected void setupRotations(LivingEntityRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
		super.setupRotations(state, poseStack, bodyRot, entityScale);
		if (!(state.walkAnimationSpeed < 0.01D)) {
			float wp = state.walkAnimationPos + 6.0F;
			float triangleWave = (Math.abs(wp % 13.0F - 6.5F) - 3.25F) / 3.25F;
			poseStack.mulPose(Axis.ZP.rotationDegrees(6.5F * triangleWave));
		}
	}
}