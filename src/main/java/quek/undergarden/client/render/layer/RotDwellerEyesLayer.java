package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.entity.rotspawn.RotDwellerEntity;

public class RotDwellerEyesLayer<T extends RotDwellerEntity, M extends DwellerModel<T>> extends AbstractEyesLayer<T, M> {

    public RotDwellerEyesLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/rotdweller_eyes.png"));
    }
}
