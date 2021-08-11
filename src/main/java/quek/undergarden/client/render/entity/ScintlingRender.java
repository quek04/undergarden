package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.client.render.layer.ScintlingGlowLayer;
import quek.undergarden.entity.ScintlingEntity;

public class ScintlingRender extends MobRenderer<ScintlingEntity, ScintlingModel<ScintlingEntity>> {

    public ScintlingRender(EntityRendererManager manager) {
        super(manager, new ScintlingModel<>(), 0.5F);
        this.addLayer(new ScintlingGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ScintlingEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/scintling.png");
    }

}