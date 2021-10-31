package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MuncherModel;
import quek.undergarden.client.render.layer.MuncherEyesLayer;
import quek.undergarden.entity.cavern.MuncherEntity;

public class MuncherRender extends MobRenderer<MuncherEntity, MuncherModel<MuncherEntity>> {

    public MuncherRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new MuncherModel<>(renderContext.bakeLayer(MuncherModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new MuncherEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MuncherEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/muncher.png");
    }
}