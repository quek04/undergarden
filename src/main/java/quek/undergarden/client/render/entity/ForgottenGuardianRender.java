package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenGuardianModel;
import quek.undergarden.client.render.layer.ForgottenGuardianEyesLayer;
import quek.undergarden.entity.boss.ForgottenGuardianEntity;

public class ForgottenGuardianRender extends MobRenderer<ForgottenGuardianEntity, ForgottenGuardianModel<ForgottenGuardianEntity>> {

    public ForgottenGuardianRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new ForgottenGuardianModel<>(renderContext.bakeLayer(ForgottenGuardianModel.LAYER_LOCATION)), 0.6F);
        this.addLayer(new ForgottenGuardianEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ForgottenGuardianEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/forgotten_guardian.png");
    }

    @Override
    public void setupRotations(ForgottenGuardianEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.animationSpeed < 0.01D)) {
            float f1 = entityLiving.animationPosition - entityLiving.animationSpeed * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}