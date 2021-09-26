package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SploogieModel;
import quek.undergarden.entity.cavern.SploogieEntity;

public class SploogieRender extends MobRenderer<SploogieEntity, SploogieModel<SploogieEntity>> {

    public SploogieRender(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new SploogieModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SploogieEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/sploogie.png");
    }
}
