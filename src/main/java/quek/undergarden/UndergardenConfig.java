package quek.undergarden;

import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

public class UndergardenConfig {

    public static class Common {
        public static ConfigValue<String> return_portal_frame_block_id;

        public Common(ForgeConfigSpec.Builder builder) {
            return_portal_frame_block_id = builder
                    .comment("""
                            Determines what block the game will generate Undergarden return portals out of
                            Use the block tag undergarden:portal_frame_blocks to determine what blocks portals can be built with
                            If value entered here is not a valid block it will default to generating minecraft:stone_bricks""")
                    .translation("undergarden.config.return_portal_frame_block_id")
                    .define("Return Portal Frame Block ID", ForgeRegistries.BLOCKS.getKey(Blocks.STONE_BRICKS).toString());
            builder.build();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<UndergardenConfig.Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(UndergardenConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
