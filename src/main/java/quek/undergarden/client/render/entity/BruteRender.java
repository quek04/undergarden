package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.BruteModel;
import quek.undergarden.client.render.layer.BruteEyesLayer;
import quek.undergarden.entity.BruteEntity;

public class BruteRender extends MobRenderer<BruteEntity, BruteModel<BruteEntity>> {

    public BruteRender(EntityRendererManager rendererManager) {
        super(rendererManager, new BruteModel<>(), 0.7F);
        this.addLayer(new BruteEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BruteEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/brute.png");
    }
}
