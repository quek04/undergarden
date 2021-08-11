package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GloomperModel;
import quek.undergarden.entity.GloomperEntity;

public class GloomperEyesLayer<T extends GloomperEntity, M extends GloomperModel<T>> extends AbstractEyesLayer<T, M> {

    public GloomperEyesLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/gloomper_eyes.png"));
    }
}
