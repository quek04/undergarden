package quek.undergarden.item;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import quek.undergarden.Undergarden;

import java.util.function.Consumer;

public class CarvedGloomgourdItem extends BlockItem {

	public CarvedGloomgourdItem(Block block, Item.Properties properties) {
		super(block, properties);
	}

	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.HEAD;
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public void renderHelmetOverlay(ItemStack stack, Player player, int width, int height, float partialTicks) {
				ResourceLocation overlay = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/gloomgourd_overlay.png");
				RenderSystem.disableDepthTest();
				RenderSystem.depthMask(false);
				RenderSystem.defaultBlendFunc();
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.setShaderTexture(0, overlay);
				Minecraft.getInstance().getTextureManager().bindForSetup(overlay);
				Tesselator tesselator = Tesselator.getInstance();
				BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				final float scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
				final float scaledHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
				bufferbuilder.addVertex(0.0F, scaledHeight, -90.0F).setUv(0.0F, 1.0F);
				bufferbuilder.addVertex(scaledWidth, scaledHeight, -90.0F).setUv(1.0F, 1.0F);
				bufferbuilder.addVertex(scaledWidth, 0.0F, -90.0F).setUv(1.0F, 0.0F);
				bufferbuilder.addVertex(0.0F, 0.0F, -90.0F).setUv(0.0F, 0.0F);
				BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
				RenderSystem.depthMask(true);
				RenderSystem.enableDepthTest();
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			}
		});
	}
}