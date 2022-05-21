package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbeastModel;
import quek.undergarden.entity.rotspawn.Rotbeast;

public class RotbeastEyesLayer<T extends Rotbeast, M extends RotbeastModel<T>> extends EyesLayer<T, M> {

    public RotbeastEyesLayer(RenderLayerParent<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/rotbeast_eyes.png"));
    }
}
