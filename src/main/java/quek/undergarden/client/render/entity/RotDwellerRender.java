package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.client.render.layer.RotDwellerEyesLayer;
import quek.undergarden.entity.rotspawn.RotDwellerEntity;

public class RotDwellerRender extends MobRenderer<RotDwellerEntity, DwellerModel<RotDwellerEntity>> {

    public RotDwellerRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DwellerModel<>(), 0.7F);
        this.addLayer(new RotDwellerEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RotDwellerEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/rotdweller.png");
    }
}