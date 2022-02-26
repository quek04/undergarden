package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.BruteModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BruteEyesLayer;
import quek.undergarden.entity.animal.BruteEntity;

public class BruteRender extends MobRenderer<BruteEntity, BruteModel<BruteEntity>> {

    public BruteRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new BruteModel<>(renderContext.bakeLayer(UGModelLayers.BRUTE)), 0.7F);
        this.addLayer(new BruteEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BruteEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/brute.png");
    }
}
