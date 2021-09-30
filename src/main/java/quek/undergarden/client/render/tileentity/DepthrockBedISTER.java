package quek.undergarden.client.render.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.block.entity.DepthrockBedBlockEntity;

public class DepthrockBedISTER extends BlockEntityWithoutLevelRenderer {

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int combinedLight, int combinedOverlay) {
        BlockEntityRenderDispatcher.instance.renderItem(new DepthrockBedBlockEntity(), matrixStack, renderTypeBuffer, combinedLight, combinedOverlay);
    }
}
