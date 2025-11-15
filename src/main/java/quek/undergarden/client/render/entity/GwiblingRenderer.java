package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwiblingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Gwibling;

public class GwiblingRenderer extends MobRenderer<Gwibling, GwiblingModel<Gwibling>> {

	private static final ResourceLocation GWIBLING = Undergarden.prefix("textures/entity/gwibling.png");
	private static final RenderType GWIBLING_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/gwibling_eyes.png"));

	public GwiblingRenderer(EntityRendererProvider.Context context) {
		super(context, new GwiblingModel<>(context.bakeLayer(UGModelLayers.GWIBLING)), 0.3F);
		this.addLayer(new BasicEyesLayer<>(this, GWIBLING_EYES));
	}

	@Override
	public ResourceLocation getTextureLocation(Gwibling entity) {
		return GWIBLING;
	}

	@Override
	protected void setupRotations(Gwibling entity, PoseStack stack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
		super.setupRotations(entity, stack, ageInTicks, rotationYaw, partialTicks, scale);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		stack.mulPose(Axis.YP.rotationDegrees(f));
		if (!entity.isInWater()) {
			stack.translate(0.1F, 0.1F, -0.1F);
			stack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}