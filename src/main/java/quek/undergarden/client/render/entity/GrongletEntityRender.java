package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import quek.undergarden.entity.projectile.slingshot.Gronglet;

public class GrongletEntityRender extends ThrownItemRenderer<Gronglet> {

	private final ItemRenderer itemRenderer;

	public GrongletEntityRender(EntityRendererProvider.Context context) {
		super(context, 0.0F, true);
		this.itemRenderer = context.getItemRenderer();
	}

	@Override
	public void render(Gronglet entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int light) {
		if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25D)) {
			stack.pushPose();
			stack.scale(3.0F, 3.0F, 3.0F);
			stack.mulPose(Axis.YP.rotationDegrees((entity.tickCount + partialTicks) * 20));
			this.itemRenderer.renderStatic(entity.getItem(), ItemDisplayContext.GROUND, light, OverlayTexture.NO_OVERLAY, stack, bufferSource, entity.level(), entity.getId());
			stack.popPose();
		}
	}
}
