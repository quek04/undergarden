package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MuncherModel;
import quek.undergarden.entity.cavern.Muncher;

public class MuncherEyesLayer<T extends Muncher, M extends MuncherModel<T>> extends EyesLayer<T, M> {

    public MuncherEyesLayer(RenderLayerParent<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/muncher_eyes.png"));
    }
}
