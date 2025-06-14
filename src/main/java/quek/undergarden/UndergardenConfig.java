package quek.undergarden;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class UndergardenConfig {

	public static class Common {
		public static ModConfigSpec.ConfigValue<String> return_portal_frame_block_id;

		public Common(ModConfigSpec.Builder builder) {
			return_portal_frame_block_id = builder
				.comment("""
					Determines what block the game will generate Undergarden return portals out of
					Use the block tag undergarden:portal_frame_blocks to determine what blocks portals can be built with
					If value entered here is not a valid block it will default to generating minecraft:stone_bricks""")
				.translation("config.undergarden.return_portal_frame_block_id")
				.define("Return Portal Frame Block ID", BuiltInRegistries.BLOCK.getKey(Blocks.STONE_BRICKS).toString());
			builder.build();
		}
	}

	public static class Client {

		public static ModConfigSpec.ConfigValue<Boolean> toggle_undergarden_fog;
		public static ModConfigSpec.ConfigValue<Boolean> toggle_utheric_infection_number_display;

		public Client(ModConfigSpec.Builder builder) {
			toggle_undergarden_fog = builder
				.comment("""
					Toggles the Undergarden's special fog parameters. Set  false to disable it, and return to the vanilla fog.""")
				.translation("config.undergarden.toggle_undergarden_fog")
				.define("Toggle Undergarden Fog", true);
			toggle_utheric_infection_number_display = builder
				.comment("""
					Displays the numerical value of the player's Utheric Infection value.""")
				.translation("config.undergarden.toggle_utheric_infection_number_display")
				.define("Toggle Utheric Infection Number Display", false);
			builder.build();
		}
	}

	static final ModConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static final ModConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	static {
		final Pair<UndergardenConfig.Common, ModConfigSpec> common = new ModConfigSpec.Builder().configure(UndergardenConfig.Common::new);
		COMMON_SPEC = common.getRight();
		COMMON = common.getLeft();
		final Pair<UndergardenConfig.Client, ModConfigSpec> client = new ModConfigSpec.Builder().configure(UndergardenConfig.Client::new);
		CLIENT_SPEC = client.getRight();
		CLIENT = client.getLeft();
	}
}
