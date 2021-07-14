package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MogModel;
import quek.undergarden.entity.MogEntity;

public class MogEyesLayer<E extends MogEntity, M extends MogModel<E>> extends AbstractEyesLayer<E, M> {

    public MogEyesLayer(IEntityRenderer<E, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/mog_eyes.png"));
    }
}
