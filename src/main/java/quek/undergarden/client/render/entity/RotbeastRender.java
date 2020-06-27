package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.render.layer.RotbeastEyesLayer;
import quek.undergarden.entity.RotbeastEntity;
import quek.undergarden.client.model.RotbeastModel;

@OnlyIn(Dist.CLIENT)
public class RotbeastRender extends MobRenderer<RotbeastEntity, RotbeastModel<RotbeastEntity>> {

    private static final ResourceLocation texture = new ResourceLocation(UndergardenMod.MODID, "textures/entities/rotbeast.png");

    public RotbeastRender(EntityRendererManager manager) {
        super(manager, new RotbeastModel(), 0.6F);
        this.addLayer(new RotbeastEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(RotbeastEntity entity) {
        return texture;
    }

    @Override
    protected void applyRotations(RotbeastEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.limbSwingAmount < 0.01D)) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
