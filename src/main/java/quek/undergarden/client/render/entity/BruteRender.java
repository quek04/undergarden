package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.BruteModel;
import quek.undergarden.client.render.layer.BruteEyesLayer;
import quek.undergarden.entity.BruteEntity;

@OnlyIn(Dist.CLIENT)
public class BruteRender extends MobRenderer<BruteEntity, BruteModel<BruteEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UGMod.MODID, "textures/entities/brute.png");

    public BruteRender(EntityRendererManager rendererManager) {
        super(rendererManager, new BruteModel<>(), .7F);
        this.addLayer(new BruteEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(BruteEntity entity) {
        return TEXTURE;
    }
}
