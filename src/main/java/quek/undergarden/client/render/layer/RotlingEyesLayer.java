package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.RotlingModel;
import quek.undergarden.entity.rotspawn.RotlingEntity;

public class RotlingEyesLayer<T extends RotlingEntity, M extends RotlingModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(UGMod.MODID, "textures/entity/rotling_eyes.png"));

    public RotlingEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType() {
        return TEXTURE;
    }

}
