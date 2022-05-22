package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwibModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.GwibEyesLayer;
import quek.undergarden.entity.animal.Gwib;

public class GwibRender extends MobRenderer<Gwib, GwibModel<Gwib>> {

    public GwibRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new GwibModel<>(renderContext.bakeLayer(UGModelLayers.GWIB)), 0.5F);
        this.addLayer(new GwibEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Gwib entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/gwib.png");
    }

    @Override
    protected void setupRotations(Gwib entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
    }
}