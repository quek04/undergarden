package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwiblingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Gwibling;

public class GwiblingRenderer extends MobRenderer<Gwibling, LivingEntityRenderState, GwiblingModel> {

	private static final Identifier GWIBLING = Undergarden.prefix("textures/entity/gwibling.png");
	private static final RenderType GWIBLING_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/gwibling_eyes.png"));

	public GwiblingRenderer(EntityRendererProvider.Context context) {
		super(context, new GwiblingModel(context.bakeLayer(UGModelLayers.GWIBLING)), 0.3F);
		this.addLayer(new BasicEyesLayer<>(this, GWIBLING_EYES));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return GWIBLING;
	}

	@Override
	protected void setupRotations(LivingEntityRenderState state, PoseStack stack, float bodyRot, float entityScale) {
		super.setupRotations(state, stack, bodyRot, entityScale);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		stack.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			stack.translate(0.1F, 0.1F, -0.1F);
			stack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}