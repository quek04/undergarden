package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SploogieModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.entity.cavern.Sploogie;

public class SploogieRender extends MobRenderer<Sploogie, SploogieModel<Sploogie>> {

    public SploogieRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new SploogieModel<>(renderContext.bakeLayer(UGModelLayers.SPLOOGIE)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Sploogie entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/sploogie.png");
    }
}
