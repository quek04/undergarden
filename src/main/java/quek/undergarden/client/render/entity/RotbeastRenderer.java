package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbeastModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.RotbeastRenderState;
import quek.undergarden.entity.monster.rotspawn.Rotbeast;

public class RotbeastRenderer extends MobRenderer<Rotbeast, RotbeastRenderState, RotbeastModel> {

	private static final Identifier ROTBEAST = Undergarden.prefix("textures/entity/rotbeast.png");
	private static final RenderType ROTBEAST_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/rotbeast_eyes.png"));

	public RotbeastRenderer(EntityRendererProvider.Context context) {
		super(context, new RotbeastModel(context.bakeLayer(UGModelLayers.ROTBEAST)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, ROTBEAST_EYES));
	}

	@Override
	public RotbeastRenderState createRenderState() {
		return new RotbeastRenderState();
	}

	@Override
	public void extractRenderState(Rotbeast entity, RotbeastRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.aggressive = entity.isAggressive();
		state.attackTimer = entity.getAttackTimer() > 0.0F ? entity.getAttackTimer() - partialTicks : 0.0F;
	}

	@Override
	public Identifier getTextureLocation(RotbeastRenderState state) {
		return ROTBEAST;
	}

	@Override
	protected void setupRotations(RotbeastRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
		super.setupRotations(state, poseStack, bodyRot, entityScale);
		if (!(state.walkAnimationSpeed < 0.01D)) {
			float wp = state.walkAnimationPos + 6.0F;
			float triangleWave = (Math.abs(wp % 13.0F - 6.5F) - 3.25F) / 3.25F;
			poseStack.mulPose(Axis.ZP.rotationDegrees(6.5F * triangleWave));
		}
	}
}