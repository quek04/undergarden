package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GloomperModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.GloomperEyesLayer;
import quek.undergarden.entity.animal.GloomperEntity;

public class GloomperRender extends MobRenderer<GloomperEntity, GloomperModel<GloomperEntity>> {

    public GloomperRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new GloomperModel<>(renderContext.bakeLayer(UGModelLayers.GLOOMPER)), 1.0F);
        this.addLayer(new GloomperEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GloomperEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/gloomper.png");
    }
}