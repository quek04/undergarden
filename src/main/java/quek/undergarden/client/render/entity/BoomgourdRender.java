package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.registry.UGBlocks;

public class BoomgourdRender extends EntityRenderer<Boomgourd> {

	private final BlockRenderDispatcher blockRenderer;

	public BoomgourdRender(EntityRendererProvider.Context context) {
		super(context);
		this.blockRenderer = context.getBlockRenderDispatcher();
	}

	@Override
	public void render(Boomgourd entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		stack.pushPose();
		stack.translate(0.0D, 0.5D, 0.0D);
		int fuse = entity.getFuse();
		if ((float) fuse - partialTicks + 1.0F < 10.0F) {
			float f = 1.0F - ((float) fuse - partialTicks + 1.0F) / 10.0F;
			f = Mth.clamp(f, 0.0F, 1.0F);
			f *= f;
			f *= f;
			float f1 = 1.0F + f * 0.3F;
			stack.scale(f1, f1, f1);
		}
		stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
		stack.translate(-0.5D, -0.5D, 0.5D);
		stack.mulPose(Axis.YP.rotationDegrees(90.0F));
		TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, UGBlocks.BOOMGOURD.get().defaultBlockState(), stack, buffer, light, fuse / 5 % 2 == 0);
		stack.popPose();
		super.render(entity, yaw, partialTicks, stack, buffer, light);
	}

	@Override
	public ResourceLocation getTextureLocation(Boomgourd entity) {
		return InventoryMenu.BLOCK_ATLAS;
	}
}