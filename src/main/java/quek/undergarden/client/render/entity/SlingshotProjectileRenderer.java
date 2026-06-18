package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import quek.undergarden.client.state.entity.SlingshotProjectileRenderState;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;

public class SlingshotProjectileRenderer extends EntityRenderer<SlingshotProjectile, SlingshotProjectileRenderState> {

	private final ItemModelResolver itemModelResolver;

	public SlingshotProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.itemModelResolver = context.getItemModelResolver();
	}

	@Override
	public void submit(SlingshotProjectileRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();

		if (state.isBlock) {
			poseStack.scale(3.0F, 3.0F, 3.0F);
			poseStack.mulPose(Axis.YP.rotationDegrees(state.ageInTicks * 20));
		} else {
			poseStack.mulPose(camera.orientation);
		}
		state.item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	public SlingshotProjectileRenderState createRenderState() {
		return new SlingshotProjectileRenderState();
	}

	@Override
	public void extractRenderState(SlingshotProjectile entity, SlingshotProjectileRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.itemModelResolver.updateForNonLiving(state.item, entity.getItem(), ItemDisplayContext.GROUND, entity);
		state.isBlock = state.item.usesBlockLight();
	}
}
