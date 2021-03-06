package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GloomperModel;
import quek.undergarden.client.render.layer.GloomperEyesLayer;
import quek.undergarden.entity.GloomperEntity;

@OnlyIn(Dist.CLIENT)
public class GloomperRender extends MobRenderer<GloomperEntity, GloomperModel<GloomperEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Undergarden.MODID, "textures/entity/gloomper.png");

    public GloomperRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GloomperModel<>(), 1F);
        this.addLayer(new GloomperEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GloomperEntity entity) {
        return TEXTURE;
    }
}
