package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwiblingModel;
import quek.undergarden.entity.animal.Gwibling;

public class GwiblingEyesLayer<T extends Gwibling, M extends GwiblingModel<T>> extends EyesLayer<T, M> {

    public GwiblingEyesLayer(RenderLayerParent<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/gwibling_eyes.png"));
    }
}
