package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.util.Unit;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.DepthrockPotBlockEntity;
import quek.undergarden.client.model.PotModel;
import quek.undergarden.client.model.UGModelLayers;

public class DepthrockPotRenderer implements BlockEntityRenderer<DepthrockPotBlockEntity, BlockEntityRenderState> {

	public static final SpriteId TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(Undergarden.prefix("pot"));
	private final SpriteGetter sprites;
	private final PotModel pot;

	public DepthrockPotRenderer(BlockEntityRendererProvider.Context context) {
		this.sprites = context.sprites();
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
		collector.submitModel(this.pot, Unit.INSTANCE, stack, state.lightCoords, OverlayTexture.NO_OVERLAY, -1, TEXTURE, this.sprites, 0, state.breakProgress);
		stack.popPose();
	}
}
