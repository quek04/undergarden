package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SmogMogModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.SmogMogEyesLayer;
import quek.undergarden.entity.animal.SmogMog;

public class SmogMogRender extends MobRenderer<SmogMog, SmogMogModel<SmogMog>> {

    public SmogMogRender(EntityRendererProvider.Context context) {
        super(context, new SmogMogModel<>(context.bakeLayer(UGModelLayers.SMOG_MOG)), 0.5F);
        this.addLayer(new SmogMogEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SmogMog entity) {
        if (entity.hasMoss()) {
            return new ResourceLocation(Undergarden.MODID, "textures/entity/smog_mog.png");
        }
        return new ResourceLocation(Undergarden.MODID, "textures/entity/smog_mog_naked.png");
    }
}
