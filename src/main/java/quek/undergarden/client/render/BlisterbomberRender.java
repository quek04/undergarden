package quek.undergarden.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.BlisterbomberModel;
import quek.undergarden.client.render.layer.BlisterbomberGlowLayer;
import quek.undergarden.entity.BlisterbomberEntity;

public class BlisterbomberRender extends MobRenderer<BlisterbomberEntity, BlisterbomberModel<BlisterbomberEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UndergardenMod.MODID, "textures/entities/blisterbomber.png");

    public BlisterbomberRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BlisterbomberModel<>(), 1F);
        this.addLayer(new BlisterbomberGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(BlisterbomberEntity entity) {
        return TEXTURE;
    }
}
