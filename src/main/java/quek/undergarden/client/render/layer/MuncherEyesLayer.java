package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.MuncherModel;
import quek.undergarden.entity.cavern.MuncherEntity;

public class MuncherEyesLayer<T extends MuncherEntity, M extends MuncherModel<T>> extends AbstractEyesLayer<T, M> {

    public MuncherEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.getEyes(new ResourceLocation(UGMod.MODID, "textures/entity/muncher_eyes.png"));
    }
}
