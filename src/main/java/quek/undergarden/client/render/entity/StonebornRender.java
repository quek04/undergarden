package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.client.render.layer.StonebornEyesLayer;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class StonebornRender extends MobRenderer<StonebornEntity, StonebornModel<StonebornEntity>> {

    public StonebornRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new StonebornModel<>(renderContext.bakeLayer(StonebornModel.LAYER_LOCATION)), 0.6F);
        this.addLayer(new StonebornEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(StonebornEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/stoneborn.png");
    }

    @Override
    protected boolean isShaking(StonebornEntity stoneborn) {
        return !stoneborn.inUndergarden();
    }
}