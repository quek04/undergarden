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
import quek.undergarden.client.model.GwibModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Gwib;

public class GwibRenderer extends MobRenderer<Gwib, LivingEntityRenderState, GwibModel> {

	public static final Identifier GWIB = Undergarden.prefix("textures/entity/gwib.png");
	private static final RenderType GWIB_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/gwib_eyes.png"));

	public GwibRenderer(EntityRendererProvider.Context context) {
		super(context, new GwibModel(context.bakeLayer(UGModelLayers.GWIB)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, GWIB_EYES));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return GWIB;
	}

	@Override
	protected void setupRotations(LivingEntityRenderState state, PoseStack stack, float bodyRot, float entityScale) {
		super.setupRotations(state, stack, bodyRot, entityScale);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		stack.mulPose(Axis.YP.rotationDegrees(f));
	}
}