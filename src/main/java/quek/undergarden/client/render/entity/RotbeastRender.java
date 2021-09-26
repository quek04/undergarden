package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbeastModel;
import quek.undergarden.client.render.layer.RotbeastEyesLayer;
import quek.undergarden.entity.rotspawn.RotbeastEntity;

public class RotbeastRender extends MobRenderer<RotbeastEntity, RotbeastModel<RotbeastEntity>> {

    public RotbeastRender(EntityRenderDispatcher manager) {
        super(manager, new RotbeastModel(), 0.6F);
        this.addLayer(new RotbeastEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RotbeastEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/rotbeast.png");
    }

    @Override
    protected void setupRotations(RotbeastEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.animationSpeed < 0.01D)) {
            float f1 = entityLiving.animationPosition - entityLiving.animationSpeed * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}