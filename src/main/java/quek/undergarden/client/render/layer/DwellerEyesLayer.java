package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.entity.DwellerEntity;

public class DwellerEyesLayer<T extends DwellerEntity, M extends DwellerModel<T>> extends AbstractEyesLayer<T, M> {

    public DwellerEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/dweller_eyes.png"));
    }
}
