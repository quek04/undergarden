package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.client.render.layer.DwellerEyesLayer;
import quek.undergarden.entity.DwellerEntity;

public class DwellerRender extends MobRenderer<DwellerEntity, DwellerModel<DwellerEntity>> {

    public DwellerRender(EntityRendererManager rendererManager) {
        super(rendererManager, new DwellerModel<>(), 0.7F);
        this.addLayer(new DwellerEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(DwellerEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/dweller.png");
    }
}