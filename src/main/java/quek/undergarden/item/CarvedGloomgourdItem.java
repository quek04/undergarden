package quek.undergarden.item;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.event.entity.living.EnderManAngerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = Undergarden.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CarvedGloomgourdItem extends BlockItem {

	public CarvedGloomgourdItem(Block block, Item.Properties properties) {
		super(block, properties);
	}

	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EquipmentSlot.HEAD;
	}

	@SubscribeEvent
	public static void lookedAtEnderman(EnderManAngerEvent event) {
		if (!event.isCanceled() && !event.getPlayer().isCreative() && isPlayerLookingAtEnderman(event.getPlayer(), event.getEntity()) && !event.getEntity().isAngryAt(event.getPlayer()) && event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(UGBlocks.CARVED_GLOOMGOURD.get().asItem())) {
			event.getEntity().level().getEntitiesOfClass(EnderMan.class, event.getEntity().getBoundingBox().inflate(64.0F), enderMan -> enderMan.hasLineOfSight(event.getPlayer())).forEach(enderMan -> enderMan.setTarget(event.getPlayer()));
		}
	}

	private static boolean isPlayerLookingAtEnderman(Player player, EnderMan enderMan) {
		Vec3 vec3 = player.getViewVector(1.0F).normalize();
		Vec3 vec31 = new Vec3(enderMan.getX() - player.getX(), enderMan.getEyeY() - player.getEyeY(), enderMan.getZ() - player.getZ());
		double d0 = vec31.length();
		vec31 = vec31.normalize();
		double d1 = vec3.dot(vec31);
		return d1 > 1.0D - 0.025D / d0 && player.hasLineOfSight(enderMan);
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public void renderHelmetOverlay(ItemStack stack, Player player, int width, int height, float partialTicks) {
				ResourceLocation overlay = new ResourceLocation(Undergarden.MODID, "textures/gloomgourd_overlay.png");
				RenderSystem.disableDepthTest();
				RenderSystem.depthMask(false);
				RenderSystem.defaultBlendFunc();
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.setShaderTexture(0, overlay);
				Minecraft.getInstance().getTextureManager().bindForSetup(overlay);
				Tesselator tessellator = Tesselator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuilder();
				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				final double scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
				final double scaledHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
				bufferbuilder.vertex(0.0D, scaledHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
				bufferbuilder.vertex(scaledWidth, scaledHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
				bufferbuilder.vertex(scaledWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
				bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
				tessellator.end();
				RenderSystem.depthMask(true);
				RenderSystem.enableDepthTest();
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			}
		});
	}
}