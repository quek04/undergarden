package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.NargoyleModel;
import quek.undergarden.entity.cavern.NargoyleEntity;

@OnlyIn(Dist.CLIENT)
public class NargoyleRender extends MobRenderer<NargoyleEntity, NargoyleModel<NargoyleEntity>> {

    private static final ResourceLocation texture = new ResourceLocation(Undergarden.MODID, "textures/entity/nargoyle.png");

    public NargoyleRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NargoyleModel<>(), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(NargoyleEntity entity) {
        return texture;
    }
}
