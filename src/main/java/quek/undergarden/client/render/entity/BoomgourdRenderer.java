package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;
import quek.undergarden.entity.Boomgourd;

public class BoomgourdRenderer extends EntityRenderer<Boomgourd, TntRenderState> {

	public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
	private final BlockModelResolver blockModelResolver;

	public BoomgourdRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.blockModelResolver = context.getBlockModelResolver();
	}

	@Override
	public void submit(TntRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
		stack.pushPose();
		stack.translate(0.0D, 0.5D, 0.0D);
		float fuse = state.fuseRemainingInTicks;
		if (fuse < 10.0F) {
			float f = 1.0F - fuse / 10.0F;
			f = Mth.clamp(f, 0.0F, 1.0F);
			f *= f;
			f *= f;
			float f1 = 1.0F + f * 0.3F;
			stack.scale(f1, f1, f1);
		}
		stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
		stack.translate(-0.5D, -0.5D, 0.5D);
		stack.mulPose(Axis.YP.rotationDegrees(90.0F));
		if (!state.blockState.isEmpty()) {
			TntMinecartRenderer.submitWhiteSolidBlock(state.blockState, stack, collector, state.lightCoords, (int)fuse / 5 % 2 == 0, state.outlineColor);
		}
		stack.popPose();
		super.submit(state, stack, collector, camera);
	}

	@Override
	public TntRenderState createRenderState() {
		return new TntRenderState();
	}

	@Override
	public void extractRenderState(Boomgourd entity, TntRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.fuseRemainingInTicks = entity.getFuse() - partialTicks + 1.0F;
		this.blockModelResolver.update(state.blockState, entity.getBlockState(), BLOCK_DISPLAY_CONTEXT);
	}
}