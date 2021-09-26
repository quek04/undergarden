package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.client.render.layer.DwellerEyesLayer;
import quek.undergarden.entity.DwellerEntity;

public class DwellerRender extends MobRenderer<DwellerEntity, DwellerModel<DwellerEntity>> {

    public DwellerRender(EntityRenderDispatcher rendererManager) {
        super(rendererManager, new DwellerModel<>(0.0F), 0.7F);
        this.addLayer(new DwellerEyesLayer<>(this));
        this.addLayer(new SaddleLayer<>(this, new DwellerModel<>(0.5F), new ResourceLocation(Undergarden.MODID, "textures/entity/dweller_saddle.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(DwellerEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/dweller.png");
    }
}