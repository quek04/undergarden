package quek.undergarden.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.DwellerEntity;
import quek.undergarden.client.model.DwellerModel;

@OnlyIn(Dist.CLIENT)
public class DwellerRender extends MobRenderer<DwellerEntity, DwellerModel<DwellerEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UndergardenMod.MODID, "textures/entities/dweller.png");

    public DwellerRender(EntityRendererManager rendererManager) {
        super(rendererManager, new DwellerModel<>(), .7F);
    }

    @Override
    public ResourceLocation getEntityTexture(DwellerEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(DwellerEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

}
