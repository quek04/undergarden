package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SploogieModel;
import quek.undergarden.entity.cavern.SploogieEntity;

@OnlyIn(Dist.CLIENT)
public class SploogieRender extends MobRenderer<SploogieEntity, SploogieModel<SploogieEntity>> {

    public SploogieRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SploogieModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(SploogieEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/sploogie.png");
    }
}
