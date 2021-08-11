package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MasticatorModel;
import quek.undergarden.entity.boss.MasticatorEntity;

public class MasticatorEyesLayer<T extends MasticatorEntity, M extends MasticatorModel<T>> extends AbstractEyesLayer<T, M> {

    public MasticatorEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/masticator_eyes.png"));
    }
}
