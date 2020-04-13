package quek.undergarden.client.render;

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

}
