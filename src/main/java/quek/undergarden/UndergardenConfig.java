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

	public static class Client {

		public static ConfigValue<Boolean> toggle_undergarden_fog;

		public Client(ForgeConfigSpec.Builder builder) {
			toggle_undergarden_fog = builder
					.comment("""
							Toggles the Undergarden's special fog parameters. Set  false to disable it, and return to the vanilla fog.""")
					.translation("undergarden.config.toggle_undergarden_fog")
					.define("Toggle Undergarden Fog", true);
			builder.build();
		}
	}

	static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	static {
		final Pair<UndergardenConfig.Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(UndergardenConfig.Common::new);
		COMMON_SPEC = common.getRight();
		COMMON = common.getLeft();
		final Pair<UndergardenConfig.Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(UndergardenConfig.Client::new);
		CLIENT_SPEC = client.getRight();
		CLIENT = client.getLeft();
	}
}
