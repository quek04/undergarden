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
import quek.undergarden.client.render.layer.GwiblingEyesLayer;
import quek.undergarden.entity.GwiblingEntity;
import quek.undergarden.client.model.GwiblingModel;

@OnlyIn(Dist.CLIENT)
public class GwiblingRender extends MobRenderer<GwiblingEntity, GwiblingModel<GwiblingEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Undergarden.MODID, "textures/entity/gwibling.png");

    public GwiblingRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GwiblingModel<>(), .3F);
        this.addLayer(new GwiblingEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GwiblingEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(GwiblingEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}
