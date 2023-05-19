package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenGuardianModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.ForgottenGuardianEyesLayer;
import quek.undergarden.entity.boss.ForgottenGuardian;

public class ForgottenGuardianRender extends MobRenderer<ForgottenGuardian, ForgottenGuardianModel<ForgottenGuardian>> {

	public ForgottenGuardianRender(EntityRendererProvider.Context renderContext) {
		super(renderContext, new ForgottenGuardianModel<>(renderContext.bakeLayer(UGModelLayers.FORGOTTEN_GUARDIAN)), 0.6F);
		this.addLayer(new ForgottenGuardianEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(ForgottenGuardian entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/forgotten_guardian.png");
	}

	@Override
	public void setupRotations(ForgottenGuardian entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		if (!((double) entityLiving.walkAnimation.speed() < 0.01D)) {
			float f1 = entityLiving.walkAnimation.position() - entityLiving.walkAnimation.speed() * (1.0F - partialTicks) + 6.0F;
			float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
			matrixStackIn.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
		}
	}
}