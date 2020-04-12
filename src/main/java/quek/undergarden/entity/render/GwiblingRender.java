package quek.undergarden.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.GwiblingEntity;
import quek.undergarden.entity.model.GwiblingModel;

@OnlyIn(Dist.CLIENT)
public class GwiblingRender extends MobRenderer<GwiblingEntity, GwiblingModel<GwiblingEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UndergardenMod.MODID, "textures/entities/gwibling.png");

    public GwiblingRender(EntityRendererManager rendererManager) {
        super(rendererManager, new GwiblingModel<>(), .5F);
    }

    @Override
    public ResourceLocation getEntityTexture(GwiblingEntity entity) {
        return TEXTURE;
    }

    protected void applyRotations(GwiblingEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}
