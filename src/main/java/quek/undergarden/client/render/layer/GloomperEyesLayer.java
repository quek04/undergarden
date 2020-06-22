package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.GloomperModel;
import quek.undergarden.entity.GloomperEntity;

public class GloomperEyesLayer<T extends GloomperEntity, M extends GloomperModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(UndergardenMod.MODID, "textures/entities/gloomper_eyes.png"));

    public GloomperEyesLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType getRenderType() {
        return TEXTURE;
    }
}
