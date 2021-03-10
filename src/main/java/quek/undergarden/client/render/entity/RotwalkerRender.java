package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.render.layer.RotwalkerEyesLayer;
import quek.undergarden.entity.rotspawn.RotwalkerEntity;
import quek.undergarden.client.model.RotwalkerModel;
//import quek.undergarden.world.OthersideDimension;

@OnlyIn(Dist.CLIENT)
public class RotwalkerRender extends MobRenderer<RotwalkerEntity, RotwalkerModel<RotwalkerEntity>> {

    private static final ResourceLocation texture = new ResourceLocation(Undergarden.MODID, "textures/entity/rotwalker.png");
    private static final ResourceLocation otherside_texture = new ResourceLocation(Undergarden.MODID, "textures/entity/rotwalker_otherside.png");

    public RotwalkerRender(EntityRendererManager manager) {
        super(manager, new RotwalkerModel(), 0.6f);
        this.addLayer(new RotwalkerEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RotwalkerEntity entity) {
        //if(OthersideDimension.isTheOtherside(entity.world)) {
        //    return otherside_texture;
        //}
        //else
            return texture;
    }

    @Override
    public void setupRotations(RotwalkerEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.animationSpeed < 0.01D)) {
            float f1 = entityLiving.animationPosition - entityLiving.animationSpeed * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
