package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbeastModel;
import quek.undergarden.entity.rotspawn.RotbeastEntity;

public class RotbeastEyesLayer<T extends RotbeastEntity, M extends RotbeastModel<T>> extends AbstractEyesLayer<T, M> {

    public RotbeastEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/rotbeast_eyes.png"));
    }
}
