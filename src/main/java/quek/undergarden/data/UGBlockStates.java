package quek.undergarden.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.DeepturfBlock;
import quek.undergarden.block.HangingGrongleLeavesBlock;
import quek.undergarden.data.provider.UGBlockstateProvider;
import quek.undergarden.registry.UGBlocks;

public class UGBlockStates extends UGBlockstateProvider {

	public UGBlockStates(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, fileHelper);
	}

	@Override
	public String getName() {
		return "Undergarden Block States";
	}

	@Override
	protected void registerStatesAndModels() {
		torchBlock(UGBlocks.SHARD_TORCH, UGBlocks.SHARD_WALL_TORCH);
		block(UGBlocks.DEPTHROCK);
		block(UGBlocks.DEEPSOIL);
		crossBlock(UGBlocks.ASHEN_DEEPTURF);
		block(UGBlocks.DEPTHROCK_COAL_ORE);
		block(UGBlocks.DEPTHROCK_CLOGGRUM_ORE);
		block(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE);
		block(UGBlocks.DEPTHROCK_UTHERIUM_ORE);
		log(UGBlocks.SMOGSTEM_LOG, "smogstem_log");
		axisBlock(UGBlocks.SMOGSTEM_WOOD.get(), texture("smogstem_log_side"), texture("smogstem_log_side"));
		crossBlock(UGBlocks.SMOGSTEM_SAPLING);
		log(UGBlocks.WIGGLEWOOD_LOG, "wigglewood_log");
		axisBlock(UGBlocks.WIGGLEWOOD_WOOD.get(), texture("wigglewood_log_side"), texture("wigglewood_log_side"));
		crossBlock(UGBlocks.WIGGLEWOOD_SAPLING);
		block(UGBlocks.SMOGSTEM_PLANKS);
		block(UGBlocks.WIGGLEWOOD_PLANKS);
		block(UGBlocks.SMOGSTEM_LEAVES);
		block(UGBlocks.WIGGLEWOOD_LEAVES);
		crossBlock(UGBlocks.INDIGO_MUSHROOM);
		crossBlock(UGBlocks.VEIL_MUSHROOM);
		crossBlock(UGBlocks.INK_MUSHROOM);
		crossBlock(UGBlocks.BLOOD_MUSHROOM);
		block(UGBlocks.DEPTHROCK_BRICKS);
		block(UGBlocks.CRACKED_DEPTHROCK_BRICKS);
		crossBlock(UGBlocks.GLITTERKELP);
		crossBlock(UGBlocks.GLITTERKELP_PLANT);
		block(UGBlocks.SHIVERSTONE);
		block(UGBlocks.SHIVERSTONE_BRICKS);
		block(UGBlocks.DEPTHROCK_REGALIUM_ORE);
		block(UGBlocks.TREMBLECRUST);
		block(UGBlocks.TREMBLECRUST_BRICKS);
		block(UGBlocks.CRACKED_TREMBLECRUST_BRICKS);
		block(UGBlocks.TREMBLECRUST_UTHERIUM_ORE);
		block(UGBlocks.LOOSE_TREMBLECRUST);
		block(UGBlocks.DEPTHROCK_IRON_ORE);
		block(UGBlocks.DEPTHROCK_GOLD_ORE);
		block(UGBlocks.DEPTHROCK_DIAMOND_ORE);
		block(UGBlocks.COARSE_DEEPSOIL);
		crossBlock(UGBlocks.GRONGLE_SAPLING);
		block(UGBlocks.GRONGLE_LEAVES);
		log(UGBlocks.GRONGLE_LOG, "grongle_log");
		axisBlock(UGBlocks.GRONGLE_WOOD.get(), texture("grongle_log_side"), texture("grongle_log_side"));
		block(UGBlocks.GRONGLE_PLANKS);
		log(UGBlocks.STRIPPED_SMOGSTEM_LOG, "stripped_smogstem_log");
		log(UGBlocks.STRIPPED_WIGGLEWOOD_LOG, "stripped_wigglewood_log");
		log(UGBlocks.STRIPPED_GRONGLE_LOG, "stripped_grongle_log");
		axisBlock(UGBlocks.STRIPPED_SMOGSTEM_WOOD.get(), texture("stripped_smogstem_log_side"), texture("stripped_smogstem_log_side"));
		axisBlock(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get(), texture("stripped_wigglewood_log_side"), texture("stripped_wigglewood_log_side"));
		axisBlock(UGBlocks.STRIPPED_GRONGLE_WOOD.get(), texture("stripped_grongle_log_side"), texture("stripped_grongle_log_side"));
		block(UGBlocks.CRACKED_SHIVERSTONE_BRICKS);
		crossBlock(UGBlocks.SEEPING_INK);
		block(UGBlocks.FORGOTTEN_BLOCK);
		block(UGBlocks.CLOGGRUM_BLOCK);
		block(UGBlocks.FROSTSTEEL_BLOCK);
		block(UGBlocks.UTHERIUM_BLOCK);
		block(UGBlocks.CHISELED_DEPTHROCK_BRICKS);
		block(UGBlocks.CHISELED_SHIVERSTONE_BRICKS);
		crossBlock(UGBlocks.FROZEN_DEEPTURF);
		block(UGBlocks.CHISELED_TREMBLECRUST_BRICKS);
		block(UGBlocks.SEDIMENT);
		blockTranslucent(UGBlocks.SEDIMENT_GLASS);
		block(UGBlocks.CLOGGRUM_TILES);
		block(UGBlocks.DEPTHROCK_TILES);
		carpet(UGBlocks.MOGMOSS_RUG);
		block(UGBlocks.SHIVERSTONE_COAL_ORE);
		block(UGBlocks.SHIVERSTONE_IRON_ORE);
		block(UGBlocks.SHIVERSTONE_DIAMOND_ORE);
		block(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE);
		block(UGBlocks.SHIVERSTONE_UTHERIUM_ORE);
		block(UGBlocks.SHIVERSTONE_REGALIUM_ORE);
		block(UGBlocks.RAW_CLOGGRUM_BLOCK);
		block(UGBlocks.RAW_FROSTSTEEL_BLOCK);
		block(UGBlocks.POLISHED_DEPTHROCK);
		crossBlock(UGBlocks.AMOROUS_BRISTLE);
		crossBlock(UGBlocks.MISERABELL);
		crossBlock(UGBlocks.BUTTERBUNCH);
		block(UGBlocks.INDIGO_MUSHROOM_CAP);
		block(UGBlocks.INDIGO_MUSHROOM_STEM);
		block(UGBlocks.VEIL_MUSHROOM_CAP);
		block(UGBlocks.VEIL_MUSHROOM_STEM);
		block(UGBlocks.INK_MUSHROOM_CAP);
		simpleBlock(UGBlocks.INK_MUSHROOM_STEM.get(), models().cubeAll(name(UGBlocks.INK_MUSHROOM_STEM), blockTexture(Blocks.MUSHROOM_STEM)));
		block(UGBlocks.BLOOD_MUSHROOM_CAP);
		block(UGBlocks.BLOOD_MUSHROOM_STEM);
		getVariantBuilder(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(cubeAll(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get())).nextModel()
						.modelFile(models().cubeAll(name(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP) + "_2", new ResourceLocation(Undergarden.MODID, "block/engorged_blood_mushroom_cap_2"))).nextModel()
						.modelFile(models().cubeAll(name(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP) + "_3", new ResourceLocation(Undergarden.MODID, "block/engorged_blood_mushroom_cap_3")))
						.build()
		);

		stairs(UGBlocks.DEPTHROCK_STAIRS, UGBlocks.DEPTHROCK);
		stairs(UGBlocks.POLISHED_DEPTHROCK_STAIRS, UGBlocks.POLISHED_DEPTHROCK);
		stairs(UGBlocks.DEPTHROCK_BRICK_STAIRS, UGBlocks.DEPTHROCK_BRICKS);
		stairs(UGBlocks.SMOGSTEM_STAIRS, UGBlocks.SMOGSTEM_PLANKS);
		stairs(UGBlocks.WIGGLEWOOD_STAIRS, UGBlocks.WIGGLEWOOD_PLANKS);
		stairs(UGBlocks.SHIVERSTONE_STAIRS, UGBlocks.SHIVERSTONE);
		stairs(UGBlocks.SHIVERSTONE_BRICK_STAIRS, UGBlocks.SHIVERSTONE_BRICKS);
		stairs(UGBlocks.GRONGLE_STAIRS, UGBlocks.GRONGLE_PLANKS);
		stairs(UGBlocks.TREMBLECRUST_STAIRS, UGBlocks.TREMBLECRUST);
		stairs(UGBlocks.TREMBLECRUST_BRICK_STAIRS, UGBlocks.TREMBLECRUST_BRICKS);
		stairs(UGBlocks.CLOGGRUM_TILE_STAIRS, UGBlocks.CLOGGRUM_TILES);
		stairs(UGBlocks.DEPTHROCK_TILE_STAIRS, UGBlocks.DEPTHROCK_TILES);

		slab(UGBlocks.DEPTHROCK_SLAB, UGBlocks.DEPTHROCK);
		slab(UGBlocks.POLISHED_DEPTHROCK_SLAB, UGBlocks.POLISHED_DEPTHROCK);
		slab(UGBlocks.DEPTHROCK_BRICK_SLAB, UGBlocks.DEPTHROCK_BRICKS);
		slab(UGBlocks.SMOGSTEM_SLAB, UGBlocks.SMOGSTEM_PLANKS);
		slab(UGBlocks.WIGGLEWOOD_SLAB, UGBlocks.WIGGLEWOOD_PLANKS);
		slab(UGBlocks.SHIVERSTONE_SLAB, UGBlocks.SHIVERSTONE);
		slab(UGBlocks.SHIVERSTONE_BRICK_SLAB, UGBlocks.SHIVERSTONE_BRICKS);
		slab(UGBlocks.GRONGLE_SLAB, UGBlocks.GRONGLE_PLANKS);
		slab(UGBlocks.TREMBLECRUST_SLAB, UGBlocks.TREMBLECRUST);
		slab(UGBlocks.TREMBLECRUST_BRICK_SLAB, UGBlocks.TREMBLECRUST_BRICKS);
		slab(UGBlocks.CLOGGRUM_TILE_SLAB, UGBlocks.CLOGGRUM_TILES);
		slab(UGBlocks.DEPTHROCK_TILE_SLAB, UGBlocks.DEPTHROCK_TILES);

		wall(UGBlocks.DEPTHROCK_WALL, UGBlocks.DEPTHROCK);
		wall(UGBlocks.POLISHED_DEPTHROCK_WALL, UGBlocks.POLISHED_DEPTHROCK);
		wall(UGBlocks.DEPTHROCK_BRICK_WALL, UGBlocks.DEPTHROCK_BRICKS);
		wall(UGBlocks.SHIVERSTONE_WALL, UGBlocks.SHIVERSTONE);
		wall(UGBlocks.SHIVERSTONE_BRICK_WALL, UGBlocks.SHIVERSTONE_BRICKS);
		wall(UGBlocks.TREMBLECRUST_WALL, UGBlocks.TREMBLECRUST);
		wall(UGBlocks.TREMBLECRUST_BRICK_WALL, UGBlocks.TREMBLECRUST_BRICKS);

		fence(UGBlocks.SMOGSTEM_FENCE, UGBlocks.SMOGSTEM_PLANKS);
		fence(UGBlocks.WIGGLEWOOD_FENCE, UGBlocks.WIGGLEWOOD_PLANKS);
		fence(UGBlocks.GRONGLE_FENCE, UGBlocks.GRONGLE_PLANKS);

		fenceGate(UGBlocks.SMOGSTEM_FENCE_GATE, UGBlocks.SMOGSTEM_PLANKS);
		fenceGate(UGBlocks.WIGGLEWOOD_FENCE_GATE, UGBlocks.WIGGLEWOOD_PLANKS);
		fenceGate(UGBlocks.GRONGLE_FENCE_GATE, UGBlocks.GRONGLE_PLANKS);

		door(UGBlocks.SMOGSTEM_DOOR, "smogstem");
		door(UGBlocks.WIGGLEWOOD_DOOR, "wigglewood");
		door(UGBlocks.GRONGLE_DOOR, "grongle");

		trapdoor(UGBlocks.SMOGSTEM_TRAPDOOR, "smogstem");
		trapdoor(UGBlocks.WIGGLEWOOD_TRAPDOOR, "wigglewood");
		trapdoor(UGBlocks.GRONGLE_TRAPDOOR, "grongle");

		button(UGBlocks.DEPTHROCK_BUTTON, UGBlocks.DEPTHROCK);
		button(UGBlocks.SHIVERSTONE_BUTTON, UGBlocks.SHIVERSTONE);
		button(UGBlocks.TREMBLECRUST_BUTTON, UGBlocks.TREMBLECRUST);
		button(UGBlocks.SMOGSTEM_BUTTON, UGBlocks.SMOGSTEM_PLANKS);
		button(UGBlocks.WIGGLEWOOD_BUTTON, UGBlocks.WIGGLEWOOD_PLANKS);
		button(UGBlocks.GRONGLE_BUTTON, UGBlocks.GRONGLE_PLANKS);

		pressurePlate(UGBlocks.DEPTHROCK_PRESSURE_PLATE, UGBlocks.DEPTHROCK);
		pressurePlate(UGBlocks.SHIVERSTONE_PRESSURE_PLATE, UGBlocks.SHIVERSTONE);
		pressurePlate(UGBlocks.TREMBLECRUST_PRESSURE_PLATE, UGBlocks.TREMBLECRUST);
		pressurePlate(UGBlocks.SMOGSTEM_PRESSURE_PLATE, UGBlocks.SMOGSTEM_PLANKS);
		pressurePlate(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE, UGBlocks.WIGGLEWOOD_PLANKS);
		pressurePlate(UGBlocks.GRONGLE_PRESSURE_PLATE, UGBlocks.GRONGLE_PLANKS);

		sign(UGBlocks.SMOGSTEM_SIGN, UGBlocks.SMOGSTEM_WALL_SIGN, "smogstem_planks");
		sign(UGBlocks.WIGGLEWOOD_SIGN, UGBlocks.WIGGLEWOOD_WALL_SIGN, "wigglewood_planks");
		sign(UGBlocks.GRONGLE_SIGN, UGBlocks.GRONGLE_WALL_SIGN, "grongle_planks");

		hangingSign(UGBlocks.SMOGSTEM_HANGING_SIGN, UGBlocks.SMOGSTEM_WALL_HANGING_SIGN, "stripped_smogstem_log_side");
		hangingSign(UGBlocks.WIGGLEWOOD_HANGING_SIGN, UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN, "stripped_wigglewood_log_side");
		hangingSign(UGBlocks.GRONGLE_HANGING_SIGN, UGBlocks.GRONGLE_WALL_HANGING_SIGN, "stripped_grongle_log_side");

		ModelFile ashen_deepturf = models().cubeBottomTop(name(UGBlocks.ASHEN_DEEPTURF_BLOCK), texture("ashen_deepturf_block_side"), texture("deepsoil"), texture("ashen_deepturf_block_top"));
		simpleBlock(UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), ConfiguredModel.builder()
				.modelFile(ashen_deepturf).nextModel()
				.rotationY(270).modelFile(ashen_deepturf).nextModel()
				.rotationY(180).modelFile(ashen_deepturf).nextModel()
				.rotationY(90).modelFile(ashen_deepturf).build());

		getVariantBuilder(UGBlocks.BLISTERBERRY_BUSH.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(models().cross(name(UGBlocks.BLISTERBERRY_BUSH) + "_" + state.getValue(BlisterberryBushBlock.AGE), texture(name(UGBlocks.BLISTERBERRY_BUSH) + "_" + state.getValue(BlisterberryBushBlock.AGE))).renderType("cutout"))
						.build());

		simpleBlock(UGBlocks.BOOMGOURD.get(), models().cubeBottomTop(name(UGBlocks.BOOMGOURD), texture("boomgourd_side"), texture("boomgourd_top"), texture("boomgourd_top")));
		horizontalBlock(UGBlocks.CARVED_GLOOMGOURD.get(), models().orientable(name(UGBlocks.CARVED_GLOOMGOURD), texture("gloomgourd_side"), texture("carved_gloomgourd"), texture("gloomgourd_top")));

		tintedCrossBlock(UGBlocks.DEEPTURF);
		ModelFile deepturf = models().getExistingFile(new ResourceLocation(Undergarden.MODID, "block/deepturf_block"));
		ModelFile deepturf_snow = models().cubeBottomTop(name(UGBlocks.DEEPTURF_BLOCK) + "_snowy", texture("frozen_deepturf_block_side"), texture("deepsoil"), mcLoc("block/snow"));
		getVariantBuilder(UGBlocks.DEEPTURF_BLOCK.get()).forAllStates(state -> {
			if (state.getValue(DeepturfBlock.SNOWY)) {
				return ConfiguredModel.builder().modelFile(deepturf_snow).build();
			} else {
				return ConfiguredModel.builder()
						.modelFile(deepturf).nextModel()
						.rotationY(270).modelFile(deepturf).nextModel()
						.rotationY(180).modelFile(deepturf).nextModel()
						.rotationY(90).modelFile(deepturf).build();
			}
		});
		ModelFile frozen_deepturf = models().cubeBottomTop(name(UGBlocks.FROZEN_DEEPTURF_BLOCK), texture("frozen_deepturf_block_side"), texture("deepsoil"), texture("frozen_deepturf_block_top"));
		simpleBlock(UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), ConfiguredModel.builder()
				.modelFile(frozen_deepturf).nextModel()
				.rotationY(270).modelFile(frozen_deepturf).nextModel()
				.rotationY(180).modelFile(frozen_deepturf).nextModel()
				.rotationY(90).modelFile(frozen_deepturf).build());

		horizontalBlock(UGBlocks.GLOOM_O_LANTERN.get(), models().orientable(name(UGBlocks.GLOOM_O_LANTERN), texture("gloomgourd_side"), texture("gloom_o_lantern"), texture("gloomgourd_top")));
		simpleBlock(UGBlocks.GLOOMGOURD.get(), models().cubeColumn(name(UGBlocks.GLOOMGOURD), texture("gloomgourd_side"), texture("gloomgourd_top")));
		getVariantBuilder(UGBlocks.GLOOMGOURD_STEM.get()).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(models().withExistingParent(name(UGBlocks.GLOOMGOURD_STEM) + "_stage" + state.getValue(StemBlock.AGE), mcLoc("block/stem_growth" + state.getValue(StemBlock.AGE))).texture("stem", mcLoc("block/melon_stem")).renderType("cutout"))
						.build());
		horizontalBlock(UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(), models().withExistingParent(name(UGBlocks.GLOOMGOURD_STEM_ATTACHED), mcLoc("stem_fruit")).texture("stem", mcLoc("block/melon_stem")).texture("upperstem", mcLoc("block/attached_melon_stem")).renderType("cutout"));

		simpleBlock(UGBlocks.GOO_BLOCK.get(), models().cubeBottomTop(name(UGBlocks.GOO_BLOCK), texture("goo_block_side"), texture("goo_block_bottom"), texture("goo_block_top")));

		getVariantBuilder(UGBlocks.HANGING_GRONGLE_LEAVES.get()).forAllStates(state -> {
			String name = name(UGBlocks.HANGING_GRONGLE_LEAVES) + (state.getValue(HangingGrongleLeavesBlock.HALF) == DoubleBlockHalf.UPPER ? "_top" : "");
			return ConfiguredModel.builder().modelFile(models().cross(name, texture(name)).renderType("minecraft:cutout")).build();
		});
	}
}