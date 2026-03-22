package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import quek.undergarden.entity.projectile.slingshot.Gronglet;

public class GrongletEntityRenderer<T extends Gronglet> extends ThrownItemRenderer<T> {

	public GrongletEntityRenderer(EntityRendererProvider.Context context) {
		super(context, 3.0F, true);
	}

	@Override
	public void submit(ThrownItemRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		poseStack.scale(3.0F, 3.0F, 3.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(state.ageInTicks * 20));
		state.item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
