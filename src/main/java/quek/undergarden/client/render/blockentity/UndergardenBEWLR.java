package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import quek.undergarden.block.GrongletBlock;
import quek.undergarden.block.entity.DepthrockBedBlockEntity;
import quek.undergarden.block.entity.GrongletBlockEntity;
import quek.undergarden.registry.UGBlocks;

public class UndergardenBEWLR extends BlockEntityWithoutLevelRenderer {

	public UndergardenBEWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();
			if (block instanceof GrongletBlock) {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new GrongletBlockEntity(BlockPos.ZERO, UGBlocks.GRONGLET.get().defaultBlockState()), poseStack, buffer, packedLight, packedOverlay);
			} else {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new DepthrockBedBlockEntity(BlockPos.ZERO, UGBlocks.DEPTHROCK_BED.get().defaultBlockState()), poseStack, buffer, packedLight, packedOverlay);
			}
		}
	}
}