package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.BlisterbomberModel;
import quek.undergarden.entity.BlisterbomberEntity;

public class BlisterbomberGlowLayer<T extends BlisterbomberEntity, M extends BlisterbomberModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(UndergardenMod.MODID, "textures/entities/blisterbomber_glow.png"));

    public BlisterbomberGlowLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType() {
        return TEXTURE;
    }
}
