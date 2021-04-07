package quek.undergarden.item;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItemGroups;

import net.minecraft.item.Item.Properties;

public class CarvedGloomgourdItem extends BlockItem {

    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(Undergarden.MODID, "textures/gloomgourd_overlay.png");

    public CarvedGloomgourdItem() {
        super(UGBlocks.CARVED_GLOOMGOURD.get(), (new Properties()
                .tab(UGItemGroups.GROUP)
        ));
    }

    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.HEAD;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderHelmetOverlay(ItemStack stack, PlayerEntity player, int width, int height, float partialTicks) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableAlphaTest();
        Minecraft.getInstance().getTextureManager().bind(OVERLAY_TEXTURE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        final double scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        final double scaledHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        bufferbuilder.vertex(0.0D, scaledHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(scaledWidth, scaledHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(scaledWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tessellator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableAlphaTest();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}