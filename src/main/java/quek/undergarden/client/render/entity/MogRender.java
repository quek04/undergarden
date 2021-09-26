package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MogModel;
import quek.undergarden.client.render.layer.MogEyesLayer;
import quek.undergarden.entity.MogEntity;

public class MogRender extends MobRenderer<MogEntity, MogModel<MogEntity>> {

    public MogRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new MogModel<>(), 0.5F);
        this.addLayer(new MogEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MogEntity entity) {
        if(entity.hasMoss()) {
            return new ResourceLocation(Undergarden.MODID, "textures/entity/mog.png");
        }
        return new ResourceLocation(Undergarden.MODID, "textures/entity/mog_naked.png");
    }
}