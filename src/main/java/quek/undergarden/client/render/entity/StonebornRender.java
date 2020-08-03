package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.client.render.layer.StonebornEyesLayer;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class StonebornRender extends MobRenderer<StonebornEntity, StonebornModel<StonebornEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UndergardenMod.MODID, "textures/entities/stoneborn.png");

    public StonebornRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StonebornModel<>(), 0.6F);
        this.addLayer(new StonebornEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(StonebornEntity entity) {
        return TEXTURE;
    }
}
