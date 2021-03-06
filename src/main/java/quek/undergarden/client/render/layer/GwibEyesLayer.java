package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GwibModel;
import quek.undergarden.entity.GwibEntity;

@OnlyIn(Dist.CLIENT)
public class GwibEyesLayer<T extends GwibEntity, M extends GwibModel<T>> extends AbstractEyesLayer<T, M> {

    public GwibEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.getEyes(new ResourceLocation(Undergarden.MODID, "textures/entity/gwib_eyes.png"));
    }
}
