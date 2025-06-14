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
import quek.undergarden.block.entity.DepthrockBedBlockEntity;
import quek.undergarden.block.entity.GrongletBlockEntity;
import quek.undergarden.registry.UGBlocks;

public class UndergardenBEWLR extends BlockEntityWithoutLevelRenderer {

	public UndergardenBEWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();
			if (block == UGBlocks.GRONGLET.get()) {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new GrongletBlockEntity(BlockPos.ZERO, UGBlocks.GRONGLET.get().defaultBlockState()), ms, buffer, light, overlay);
			} else if (block == UGBlocks.UTHERIC_GRONGLET.get()) {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new GrongletBlockEntity(BlockPos.ZERO, UGBlocks.UTHERIC_GRONGLET.get().defaultBlockState()), ms, buffer, light, overlay);
			} else if (block == UGBlocks.ROGDORIC_GRONGLET.get()) {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new GrongletBlockEntity(BlockPos.ZERO, UGBlocks.ROGDORIC_GRONGLET.get().defaultBlockState()), ms, buffer, light, overlay);
			} else if (block == UGBlocks.DEPTHROCK_BED.get()) {
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new DepthrockBedBlockEntity(BlockPos.ZERO, UGBlocks.DEPTHROCK_BED.get().defaultBlockState()), ms, buffer, light, overlay);
			}
		}
	}
}