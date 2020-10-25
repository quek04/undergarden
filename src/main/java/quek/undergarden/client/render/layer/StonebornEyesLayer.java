package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class StonebornEyesLayer<T extends StonebornEntity, M extends StonebornModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(UGMod.MODID, "textures/entities/stoneborn_eyes.png"));

    public StonebornEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType() {
        return TEXTURE;
    }

}
