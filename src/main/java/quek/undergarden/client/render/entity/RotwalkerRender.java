package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotwalkerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.RotwalkerEyesLayer;
import quek.undergarden.entity.rotspawn.Rotwalker;

public class RotwalkerRender extends MobRenderer<Rotwalker, RotwalkerModel<Rotwalker>> {

	public RotwalkerRender(EntityRendererProvider.Context renderContext) {
		super(renderContext, new RotwalkerModel<>(renderContext.bakeLayer(UGModelLayers.ROTWALKER)), 0.6F);
		this.addLayer(new RotwalkerEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Rotwalker entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/rotwalker.png");
	}

	@Override
	public void setupRotations(Rotwalker entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		if (!((double) entityLiving.walkAnimation.speed() < 0.01D)) {
			float f1 = entityLiving.walkAnimation.position() - entityLiving.walkAnimation.speed() * (1.0F - partialTicks) + 6.0F;
			float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
			matrixStackIn.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
		}
	}
}