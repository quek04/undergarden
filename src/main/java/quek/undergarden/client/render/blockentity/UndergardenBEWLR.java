package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.block.entity.DepthrockBedBlockEntity;
import quek.undergarden.registry.UGBlocks;

public class UndergardenBEWLR extends BlockEntityWithoutLevelRenderer {

    public UndergardenBEWLR() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pOverlay) {
        Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new DepthrockBedBlockEntity(BlockPos.ZERO, UGBlocks.DEPTHROCK_BED.get().defaultBlockState()), pPoseStack, pBuffer, pPackedLight, pOverlay);
    }
}
