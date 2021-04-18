package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwibModel;
import quek.undergarden.client.render.layer.GwibEyesLayer;
import quek.undergarden.entity.GwibEntity;

@OnlyIn(Dist.CLIENT)
public class GwibRender extends MobRenderer<GwibEntity, GwibModel<GwibEntity>> {

    public GwibRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GwibModel<>(), 0.5F);
        this.addLayer(new GwibEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GwibEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/gwib.png");
    }

    @Override
    protected void setupRotations(GwibEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
    }
}