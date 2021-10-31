package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.NargoyleModel;
import quek.undergarden.entity.cavern.NargoyleEntity;

public class NargoyleRender extends MobRenderer<NargoyleEntity, NargoyleModel<NargoyleEntity>> {

    public NargoyleRender(EntityRendererProvider.Context renderContext) {
        super(renderContext, new NargoyleModel<>(renderContext.bakeLayer(NargoyleModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(NargoyleEntity entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/nargoyle.png");
    }
}