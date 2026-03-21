package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MysteriousPotModel;
import quek.undergarden.client.model.PotModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.blockentity.DepthrockPotRenderer;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.MysteriousPot;

public class MysteriousPotRenderer extends MobRenderer<MysteriousPot, MysteriousPotModel> {

	private static final ResourceLocation MYSTERIOUS_POT = Undergarden.prefix("textures/entity/potguy.png");
	private static final RenderType MYSTERIOUS_POT_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/potguy_eyes.png"));

	private final PotModel inactiveModel;

	public MysteriousPotRenderer(EntityRendererProvider.Context context) {
		super(context, new MysteriousPotModel(context.bakeLayer(UGModelLayers.LIVING_POT)), 0.3F);
		this.addLayer(new BasicEyesLayer<>(this, MYSTERIOUS_POT_EYES, MysteriousPot::isActive));
		this.inactiveModel = new PotModel(context.bakeLayer(UGModelLayers.POT));
	}

	@Override
	public void render(MysteriousPot entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		if (!entity.isActive()) {
			stack.pushPose();
			stack.scale(-1.0F, -1.0F, 1.0F);
			stack.translate(-0.5F, 0.0F, -0.5F);
			float wiggle = entity.getInactiveWiggleTicks(partialTicks) / 10.0F;
			float f5 = Mth.sin(-wiggle * 4.0F * Mth.PI) * 0.125F;
			float f6 = 1.0F - wiggle;
			stack.rotateAround(Axis.YP.rotation(f5 * f6), 0.5F, 0.0F, 0.5F);
			stack.translate(0.5F, -1.501F, 0.5F);
			this.inactiveModel.renderToBuffer(stack, buffer.getBuffer(this.inactiveModel.renderType(DepthrockPotRenderer.TEXTURE)), light, OverlayTexture.NO_OVERLAY);
			stack.popPose();
		} else {
			super.render(entity, entityYaw, partialTicks, stack, buffer, light);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(MysteriousPot entity) {
		return MYSTERIOUS_POT;
	}
}
