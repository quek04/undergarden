package quek.undergarden;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;

public class UndergardenConfig {

    private static final String config = UndergardenMod.MODID + ".config.";

    public static ForgeConfigSpec.BooleanValue genObsidianRoof;
    public static ForgeConfigSpec.BooleanValue genObsidianFloor;

    public static class CommonConfig {
        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("mod_compatibility");
            genObsidianRoof = builder
                        .translation(config + "generate_obsidian_roof")
                        .comment("Determines if The Undergarden's roof will generate with obsidian instead of bedrock, for compatibility with Immersive Portals dimension stacking")
                        .define("genObsidianRoof", false)
                        ;
            genObsidianFloor = builder
                    .translation(config + "generate_obsidian_floor")
                    .comment("Determines if The Undergarden's floor will generate with obsidian instead of bedrock, for compatibility with Immersive Portals dimension stacking")
                    .define("genObsidianFloor", false)
            ;

            builder.pop();

        }
    }

    public static ForgeConfigSpec.BooleanValue toggleAmbiance;

    public static class ClientConfig {
        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("client");
            toggleAmbiance = builder
                    .translation(config + "toggle_ambiance")
                    .comment("If set to false this will disable The Undergarden's ambiance sounds, in case it bothers you or causes problems")
                    .define("toggleAmbiance", true)
                    ;
        }
    }

}
