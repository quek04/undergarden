package quek.undergarden.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;
import quek.undergarden.UndergardenMod;

public class OthersideSkyRender implements IRenderHandler {

    private static final ResourceLocation VORTEX_TEXTURE = new ResourceLocation(UndergardenMod.MODID,"textures/environment/otherside_vortex.png");

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {

    }
}
