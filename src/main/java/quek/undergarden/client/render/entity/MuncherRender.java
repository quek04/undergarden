package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MuncherModel;
import quek.undergarden.client.render.layer.MuncherEyesLayer;
import quek.undergarden.entity.cavern.MuncherEntity;

public class MuncherRender extends MobRenderer<MuncherEntity, MuncherModel<MuncherEntity>> {

    public MuncherRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MuncherModel<>(), 0.5F);
        this.addLayer(new MuncherEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MuncherEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/muncher.png");
    }
}