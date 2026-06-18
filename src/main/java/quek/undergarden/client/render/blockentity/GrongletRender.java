package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.block.entity.GrongletBlockEntity;
import quek.undergarden.client.model.GrongletModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.state.block.GrongletRenderState;

import java.util.HashMap;
import java.util.Map;

public class GrongletRender implements BlockEntityRenderer<GrongletBlockEntity, GrongletRenderState> {

	private static final Map<Block, Identifier> TEXTURE_CACHE = new HashMap<>();
	private final GrongletModel model;

	public GrongletRender(BlockEntityRendererProvider.Context context) {
		this.model = new GrongletModel(context.bakeLayer(UGModelLayers.GRONGLET));
	}

	@Override
	public GrongletRenderState createRenderState() {
		return new GrongletRenderState();
	}

	@Override
	public void extractRenderState(GrongletBlockEntity entity, GrongletRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(entity, state, partialTicks, cameraPosition, breakProgress);
		state.facing = entity.getBlockState().getValue(GrongletBlock.FACING);
		state.texture = TEXTURE_CACHE.computeIfAbsent(entity.getBlockState().getBlock(), block -> BuiltInRegistries.BLOCK.getKey(block).withPrefix("textures/entity/gronglet/").withSuffix(".png"));
		state.yaw = entity.yaw;
	}

	@Override
	public void submit(GrongletRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
		Direction direction = state.facing;

		stack.pushPose();
		stack.translate(0.5D + direction.getStepX(), 0.5D + direction.getStepY(), 0.5D + direction.getStepZ());
		stack.mulPose(direction.getRotation());
		stack.mulPose(Axis.ZP.rotationDegrees(180F));
		int yaw = state.yaw;
		stack.mulPose(Axis.YP.rotationDegrees(yaw));
		collector.submitModel(this.model, Unit.INSTANCE, stack, state.texture, state.lightCoords, OverlayTexture.NO_OVERLAY, 0, state.breakProgress);
		stack.popPose();
	}
}
