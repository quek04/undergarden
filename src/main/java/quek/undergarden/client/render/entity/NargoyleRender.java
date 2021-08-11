package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.NargoyleModel;
import quek.undergarden.entity.cavern.NargoyleEntity;

public class NargoyleRender extends MobRenderer<NargoyleEntity, NargoyleModel<NargoyleEntity>> {

    public NargoyleRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NargoyleModel<>(), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(NargoyleEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/nargoyle.png");
    }
}