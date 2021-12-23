package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import quek.undergarden.Undergarden;
import quek.undergarden.client.render.layer.ForgottenEyesLayer;

public class ForgottenRender extends SkeletonRenderer {

    public static final ModelLayerLocation FORGOTTEN_LAYER = new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, "forgotten"), "main");
    public static final ModelLayerLocation FORGOTTEN_INNER_ARMOR_LAYER = new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, "forgotten"), "inner_armor");
    public static final ModelLayerLocation FORGOTTEN_OUTER_ARMOR_LAYER = new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, "forgotten"), "outer_armor");

    public ForgottenRender(EntityRendererProvider.Context context) {
        super(context, FORGOTTEN_LAYER, FORGOTTEN_INNER_ARMOR_LAYER, FORGOTTEN_OUTER_ARMOR_LAYER);
        this.addLayer(new ForgottenEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractSkeleton entity) {
        return new ResourceLocation(Undergarden.MODID, "textures/entity/forgotten.png");
    }

    @Override
    protected void scale(AbstractSkeleton entity, PoseStack stack, float p_115316_) {
        stack.scale(1.1F, 1.1F, 1.1F);
    }
}