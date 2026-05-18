package quek.undergarden.datagen.assets;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGBlocks;

import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class UGBlockFamilies {

	private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

	public static final BlockFamily WIGGLEWOOD_PLANKS = familyBuilder(UGBlocks.WIGGLEWOOD_PLANKS.get())
		.button(UGBlocks.WIGGLEWOOD_BUTTON.get())
		.fence(UGBlocks.WIGGLEWOOD_FENCE.get())
		.fenceGate(UGBlocks.WIGGLEWOOD_FENCE_GATE.get())
		.pressurePlate(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get())
		.sign(UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get())
		.slab(UGBlocks.WIGGLEWOOD_SLAB.get())
		.stairs(UGBlocks.WIGGLEWOOD_STAIRS.get())
		.door(UGBlocks.WIGGLEWOOD_DOOR.get())
		.trapdoor(UGBlocks.WIGGLEWOOD_TRAPDOOR.get())
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	public static final BlockFamily SMOGSTEM_PLANKS = familyBuilder(UGBlocks.SMOGSTEM_PLANKS.get())
		.button(UGBlocks.SMOGSTEM_BUTTON.get())
		.fence(UGBlocks.SMOGSTEM_FENCE.get())
		.fenceGate(UGBlocks.SMOGSTEM_FENCE_GATE.get())
		.pressurePlate(UGBlocks.SMOGSTEM_PRESSURE_PLATE.get())
		.sign(UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.SMOGSTEM_WALL_SIGN.get())
		.slab(UGBlocks.SMOGSTEM_SLAB.get())
		.stairs(UGBlocks.SMOGSTEM_STAIRS.get())
		.door(UGBlocks.SMOGSTEM_DOOR.get())
		.trapdoor(UGBlocks.SMOGSTEM_TRAPDOOR.get())
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	public static final BlockFamily GRONGLE_PLANKS = familyBuilder(UGBlocks.GRONGLE_PLANKS.get())
		.button(UGBlocks.GRONGLE_BUTTON.get())
		.fence(UGBlocks.GRONGLE_FENCE.get())
		.fenceGate(UGBlocks.GRONGLE_FENCE_GATE.get())
		.pressurePlate(UGBlocks.GRONGLE_PRESSURE_PLATE.get())
		.sign(UGBlocks.GRONGLE_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get())
		.slab(UGBlocks.GRONGLE_SLAB.get())
		.stairs(UGBlocks.GRONGLE_STAIRS.get())
		.door(UGBlocks.GRONGLE_DOOR.get())
		.trapdoor(UGBlocks.GRONGLE_TRAPDOOR.get())
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	public static final BlockFamily ANCIENT_ROOT_PLANKS = familyBuilder(UGBlocks.ANCIENT_ROOT_PLANKS.get())
		.button(UGBlocks.ANCIENT_ROOT_BUTTON.get())
		.fence(UGBlocks.ANCIENT_ROOT_FENCE.get())
		.fenceGate(UGBlocks.ANCIENT_ROOT_FENCE_GATE.get())
		.pressurePlate(UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE.get())
		.sign(UGBlocks.ANCIENT_ROOT_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_SIGN.get())
		.slab(UGBlocks.ANCIENT_ROOT_SLAB.get())
		.stairs(UGBlocks.ANCIENT_ROOT_STAIRS.get())
		.door(UGBlocks.ANCIENT_ROOT_DOOR.get())
		.trapdoor(UGBlocks.ANCIENT_ROOT_TRAPDOOR.get())
		.recipeGroupPrefix("wooden")
		.recipeUnlockedBy("has_planks")
		.getFamily();

	public static final BlockFamily DEPTHROCK = familyBuilder(UGBlocks.DEPTHROCK.get())
		.wall(UGBlocks.DEPTHROCK_WALL.get())
		.stairs(UGBlocks.DEPTHROCK_STAIRS.get())
		.slab(UGBlocks.DEPTHROCK_SLAB.get())
		.polished(UGBlocks.POLISHED_DEPTHROCK.get())
		.pressurePlate(UGBlocks.DEPTHROCK_PRESSURE_PLATE.get())
		.button(UGBlocks.DEPTHROCK_BUTTON.get())
		.polished(UGBlocks.POLISHED_DEPTHROCK.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily POLISHED_DEPTHROCK = familyBuilder(UGBlocks.POLISHED_DEPTHROCK.get())
		.wall(UGBlocks.POLISHED_DEPTHROCK_WALL.get())
		.stairs(UGBlocks.POLISHED_DEPTHROCK_STAIRS.get())
		.slab(UGBlocks.POLISHED_DEPTHROCK_SLAB.get())
		.bricks(UGBlocks.DEPTHROCK_BRICKS.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily DEPTHROCK_BRICKS = familyBuilder(UGBlocks.DEPTHROCK_BRICKS.get())
		.wall(UGBlocks.DEPTHROCK_BRICK_WALL.get())
		.stairs(UGBlocks.DEPTHROCK_BRICK_STAIRS.get())
		.slab(UGBlocks.DEPTHROCK_BRICK_SLAB.get())
		.chiseled(UGBlocks.CHISELED_DEPTHROCK_BRICKS.get())
		.cracked(UGBlocks.CRACKED_DEPTHROCK_BRICKS.get())
		.tiles(UGBlocks.DEPTHROCK_TILES.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily DEPTHROCK_TILES = familyBuilder(UGBlocks.DEPTHROCK_TILES.get())
		.stairs(UGBlocks.DEPTHROCK_TILE_STAIRS.get())
		.slab(UGBlocks.DEPTHROCK_TILE_SLAB.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily SHIVERSTONE = familyBuilder(UGBlocks.SHIVERSTONE.get())
		.wall(UGBlocks.SHIVERSTONE_WALL.get())
		.stairs(UGBlocks.SHIVERSTONE_STAIRS.get())
		.slab(UGBlocks.SHIVERSTONE_SLAB.get())
		.pressurePlate(UGBlocks.SHIVERSTONE_PRESSURE_PLATE.get())
		.button(UGBlocks.SHIVERSTONE_BUTTON.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily SHIVERSTONE_BRICKS = familyBuilder(UGBlocks.SHIVERSTONE_BRICKS.get())
		.wall(UGBlocks.SHIVERSTONE_BRICK_WALL.get())
		.stairs(UGBlocks.SHIVERSTONE_BRICK_STAIRS.get())
		.slab(UGBlocks.SHIVERSTONE_BRICK_SLAB.get())
		.chiseled(UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get())
		.cracked(UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily DREADROCK = familyBuilder(UGBlocks.DREADROCK.get())
		.wall(UGBlocks.DREADROCK_WALL.get())
		.stairs(UGBlocks.DREADROCK_STAIRS.get())
		.slab(UGBlocks.DREADROCK_SLAB.get())
		.pressurePlate(UGBlocks.DREADROCK_PRESSURE_PLATE.get())
		.button(UGBlocks.DREADROCK_BUTTON.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily DREADROCK_BRICKS = familyBuilder(UGBlocks.DREADROCK_BRICKS.get())
		.wall(UGBlocks.DREADROCK_BRICK_WALL.get())
		.stairs(UGBlocks.DREADROCK_BRICK_STAIRS.get())
		.slab(UGBlocks.DREADROCK_BRICK_SLAB.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily TREMBLECRUST = familyBuilder(UGBlocks.TREMBLECRUST.get())
		.wall(UGBlocks.TREMBLECRUST_WALL.get())
		.stairs(UGBlocks.TREMBLECRUST_STAIRS.get())
		.slab(UGBlocks.TREMBLECRUST_SLAB.get())
		.pressurePlate(UGBlocks.TREMBLECRUST_PRESSURE_PLATE.get())
		.button(UGBlocks.TREMBLECRUST_BUTTON.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily TREMBLECRUST_BRICKS = familyBuilder(UGBlocks.TREMBLECRUST_BRICKS.get())
		.wall(UGBlocks.TREMBLECRUST_BRICK_WALL.get())
		.stairs(UGBlocks.TREMBLECRUST_BRICK_STAIRS.get())
		.slab(UGBlocks.TREMBLECRUST_BRICK_SLAB.get())
		.chiseled(UGBlocks.CHISELED_TREMBLECRUST_BRICKS.get())
		.cracked(UGBlocks.CRACKED_TREMBLECRUST_BRICKS.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily CLOGGRUM_TILES = familyBuilder(UGBlocks.CLOGGRUM_TILES.get())
		.stairs(UGBlocks.CLOGGRUM_TILE_STAIRS.get())
		.slab(UGBlocks.CLOGGRUM_TILE_SLAB.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily SEDIMENT_STONE = familyBuilder(UGBlocks.SEDIMENT_STONE.get())
		.stairs(UGBlocks.SEDIMENT_STONE_STAIRS.get())
		.slab(UGBlocks.SEDIMENT_STONE_SLAB.get())
		.wall(UGBlocks.SEDIMENT_STONE_WALL.get())
		.polished(UGBlocks.POLISHED_SEDIMENT_STONE.get())
		.chiseled(UGBlocks.CHISELED_SEDIMENT_STONE.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily POLISHED_SEDIMENT_STONE = familyBuilder(UGBlocks.POLISHED_SEDIMENT_STONE.get())
		.stairs(UGBlocks.POLISHED_SEDIMENT_STONE_STAIRS.get())
		.slab(UGBlocks.POLISHED_SEDIMENT_STONE_SLAB.get())
		.wall(UGBlocks.POLISHED_SEDIMENT_STONE_WALL.get())
		.bricks(UGBlocks.SEDIMENT_STONE_BRICKS.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily SEDIMENT_STONE_BRICKS = familyBuilder(UGBlocks.SEDIMENT_STONE_BRICKS.get())
		.stairs(UGBlocks.SEDIMENT_STONE_BRICK_STAIRS.get())
		.slab(UGBlocks.SEDIMENT_STONE_BRICK_SLAB.get())
		.wall(UGBlocks.SEDIMENT_STONE_BRICK_WALL.get())
		.cracked(UGBlocks.CRACKED_SEDIMENT_STONE_BRICKS.get())
		.generateStonecutterRecipe()
		.getFamily();

	public static final BlockFamily SMOOTH_SEDIMENT_STONE = familyBuilder(UGBlocks.SMOOTH_SEDIMENT_STONE.get())
		.stairs(UGBlocks.SMOOTH_SEDIMENT_STONE_STAIRS.get())
		.slab(UGBlocks.SMOOTH_SEDIMENT_STONE_SLAB.get())
		.wall(UGBlocks.SMOOTH_SEDIMENT_STONE_WALL.get())
		.generateStonecutterRecipe()
		.getFamily();

	private static BlockFamily.Builder familyBuilder(Block base) {
		BlockFamily.Builder builder = new BlockFamily.Builder(base);
		BlockFamilies.MAP.put(base, builder.getFamily());
		BlockFamily blockFamily = MAP.put(base, builder.getFamily());
		if (blockFamily != null) {
			throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(base));
		} else {
			return builder;
		}
	}

	public static Stream<BlockFamily> getAllFamilies() {
		return MAP.values().stream();
	}

	public static @Nullable BlockFamily getFamily(Block base) {
		return MAP.get(base);
	}
}
