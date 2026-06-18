package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.DepthrockPotBlockEntity;
import quek.undergarden.client.model.PotModel;
import quek.undergarden.client.model.UGModelLayers;

public class DepthrockPotRenderer implements BlockEntityRenderer<DepthrockPotBlockEntity, BlockEntityRenderState> {

	public static final Identifier TEXTURE = Undergarden.prefix("textures/entity/pot.png");
	private final PotModel pot;

	public DepthrockPotRenderer(BlockEntityRendererProvider.Context context) {
		this.pot = new PotModel(context.bakeLayer(UGModelLayers.POT));
	}

	@Override
	public BlockEntityRenderState createRenderState() {
		return new BlockEntityRenderState();
	}

	@Override
	public void submit(BlockEntityRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
		stack.pushPose();
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(-0.5F, -1.501F, 0.5F);
		collector.submitModel(this.pot, Unit.INSTANCE, stack, RenderTypes.entitySolid(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, null, 0, state.breakProgress);
		stack.popPose();
	}
}
