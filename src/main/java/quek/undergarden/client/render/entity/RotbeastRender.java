package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbeastModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.RotbeastEyesLayer;
import quek.undergarden.entity.rotspawn.Rotbeast;

public class RotbeastRender extends MobRenderer<Rotbeast, RotbeastModel<Rotbeast>> {

    public RotbeastRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new RotbeastModel<>(renderContext.bakeLayer(UGModelLayers.ROTBEAST)), 0.6F);
        this.addLayer(new RotbeastEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Rotbeast entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/rotbeast.png");
    }

    @Override
    protected void setupRotations(Rotbeast entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.animationSpeed < 0.01D)) {
            float f1 = entityLiving.animationPosition - entityLiving.animationSpeed * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}