package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Vector3f;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwiblingModel;
import quek.undergarden.client.render.layer.GwiblingEyesLayer;
import quek.undergarden.entity.GwiblingEntity;

public class GwiblingRender extends MobRenderer<GwiblingEntity, GwiblingModel<GwiblingEntity>> {

    public GwiblingRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new GwiblingModel<>(), 0.3F);
        this.addLayer(new GwiblingEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GwiblingEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/gwibling.png");
    }

    @Override
    protected void setupRotations(GwiblingEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}