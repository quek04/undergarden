package quek.undergarden.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.GloomperModel;
import quek.undergarden.client.render.layer.GloomperEyesLayer;
import quek.undergarden.entity.GloomperEntity;

public class GloomperRender extends MobRenderer<GloomperEntity, GloomperModel<GloomperEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UndergardenMod.MODID, "textures/entities/gloomper.png");

    public GloomperRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GloomperModel<>(), 1F);
        this.addLayer(new GloomperEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GloomperEntity entity) {
        return TEXTURE;
    }
}
