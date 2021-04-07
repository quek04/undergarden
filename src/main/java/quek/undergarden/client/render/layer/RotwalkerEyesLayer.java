package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotwalkerModel;
import quek.undergarden.entity.rotspawn.RotwalkerEntity;

@OnlyIn(Dist.CLIENT)
public class RotwalkerEyesLayer<T extends RotwalkerEntity, M extends RotwalkerModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/rotwalker_eyes.png"));

    public RotwalkerEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return TEXTURE;
    }
}
