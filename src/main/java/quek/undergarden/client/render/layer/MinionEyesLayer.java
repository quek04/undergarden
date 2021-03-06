package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MinionModel;
import quek.undergarden.entity.MinionEntity;

public class MinionEyesLayer<T extends MinionEntity, M extends MinionModel<T>> extends AbstractEyesLayer<T, M> {

    public MinionEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.getEyes(new ResourceLocation(Undergarden.MODID, "textures/entity/minion_eye.png"));
    }
}
