package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import quek.undergarden.entity.projectile.slingshot.GrongletEntity;

public class GrongletEntityRender extends ThrownItemRenderer<GrongletEntity> {

    private final ItemRenderer itemRenderer;

    public GrongletEntityRender(EntityRendererProvider.Context context) {
        super(context, 0.0F, true);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(GrongletEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25D)) {
            poseStack.pushPose();
            poseStack.scale(3.0F, 3.0F, 3.0F);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(entity.tickCount * 20));
            this.itemRenderer.renderStatic(entity.getItem(), ItemTransforms.TransformType.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, entity.getId());
            poseStack.popPose();
        }
    }
}
