package quek.undergarden.client.render.layer;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import quek.undergarden.Undergarden;

public class ForgottenEyesLayer<T extends AbstractSkeleton, M extends SkeletonModel<T>> extends EyesLayer<T, M> {

    public ForgottenEyesLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/forgotten_eyes.png"));
    }
}
