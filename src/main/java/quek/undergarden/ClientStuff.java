package quek.undergarden;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ClientStuff {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }

    public static void registerBlockRenderers() {

        RenderType cutout = RenderType.getCutout();
        RenderType translucent = RenderType.getTranslucent();

        render(UndergardenBlocks.tall_deepturf, cutout);
        render(UndergardenBlocks.shimmerweed, cutout);
        render(UndergardenBlocks.smogstem_sapling, cutout);

    }

}
