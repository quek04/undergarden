package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotlingModel;
import quek.undergarden.client.render.layer.RotlingEyesLayer;
import quek.undergarden.entity.rotspawn.RotlingEntity;

public class RotlingRender extends MobRenderer<RotlingEntity, RotlingModel<RotlingEntity>> {

    public RotlingRender(EntityRenderDispatcher manager) {
        super(manager, new RotlingModel<>(), 0.6F);
        this.addLayer(new RotlingEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RotlingEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/rotling.png");
    }
}