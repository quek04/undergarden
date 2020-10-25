package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.RotbeastModel;
import quek.undergarden.entity.rotspawn.RotbeastEntity;

@OnlyIn(Dist.CLIENT)
public class RotbeastEyesLayer<T extends RotbeastEntity, M extends RotbeastModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(UGMod.MODID, "textures/entities/rotbeast_eyes.png"));

    public RotbeastEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType() {
        return TEXTURE;
    }
}
