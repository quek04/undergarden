package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenGuardianModel;
import quek.undergarden.client.render.layer.ForgottenGuardianEyesLayer;
import quek.undergarden.entity.boss.ForgottenGuardianEntity;

@OnlyIn(Dist.CLIENT)
public class ForgottenGuardianRender extends MobRenderer<ForgottenGuardianEntity, ForgottenGuardianModel<ForgottenGuardianEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Undergarden.MODID, "textures/entity/forgotten_guardian.png");

    public ForgottenGuardianRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ForgottenGuardianModel<>(), 0.6F);
        this.addLayer(new ForgottenGuardianEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ForgottenGuardianEntity entity) {
        return TEXTURE;
    }

    @Override
    public void setupRotations(ForgottenGuardianEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.animationSpeed < 0.01D)) {
            float f1 = entityLiving.animationPosition - entityLiving.animationSpeed * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
