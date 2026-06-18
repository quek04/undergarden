package quek.undergarden.registry;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.waypoints.Waypoint;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.block.*;
import quek.undergarden.block.portal.UndergardenPortalBlock;
import quek.undergarden.world.gen.tree.UGTreeGrowers;

import java.util.function.Function;
import java.util.function.Supplier;

public class UGBlocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Undergarden.MODID);

	public static final DeferredBlock<Block> UNDERGARDEN_PORTAL = register("undergarden_portal", UndergardenPortalBlock::new, () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK).strength(-1.0F).noCollision().lightLevel((state) -> 10).sound(SoundType.GLASS).noLootTable());

	public static final DeferredBlock<Block> SHARD_TORCH = register("shard_torch", ShardTorchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> 6));
	public static final DeferredBlock<Block> SHARD_WALL_TORCH = register("shard_wall_torch", ShardWallTorchBlock::new, () -> wallVariant(SHARD_TORCH.get(), true).noCollision().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).lightLevel((state) -> 6));

	//depthrock
	public static final DeferredBlock<Block> DEPTHROCK = registerWithItem("depthrock", Block::new, () -> BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(1.5F, 6.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> POLISHED_DEPTHROCK = registerWithItem("polished_depthrock", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<Block> DEPTHROCK_BRICKS = registerWithItem("depthrock_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<Block> CRACKED_DEPTHROCK_BRICKS = registerWithItem("cracked_depthrock_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<Block> CHISELED_DEPTHROCK_BRICKS = registerWithItem("chiseled_depthrock_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<Block> DEPTHROCK_TILES = registerWithItem("depthrock_tiles", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));

	public static final DeferredBlock<StairBlock> DEPTHROCK_STAIRS = registerWithItem("depthrock_stairs", (properties) -> new StairBlock(DEPTHROCK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<StairBlock> POLISHED_DEPTHROCK_STAIRS = registerWithItem("polished_depthrock_stairs", (properties) -> new StairBlock(POLISHED_DEPTHROCK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(POLISHED_DEPTHROCK.get()));
	public static final DeferredBlock<StairBlock> DEPTHROCK_BRICK_STAIRS = registerWithItem("depthrock_brick_stairs", (properties) -> new StairBlock(DEPTHROCK_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_BRICKS.get()));
	public static final DeferredBlock<StairBlock> DEPTHROCK_TILE_STAIRS = registerWithItem("depthrock_tile_stairs", (properties) -> new StairBlock(DEPTHROCK_TILES.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_TILES.get()));

	public static final DeferredBlock<SlabBlock> DEPTHROCK_SLAB = registerWithItem("depthrock_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<SlabBlock> POLISHED_DEPTHROCK_SLAB = registerWithItem("polished_depthrock_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(POLISHED_DEPTHROCK.get()));
	public static final DeferredBlock<SlabBlock> DEPTHROCK_BRICK_SLAB = registerWithItem("depthrock_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_BRICKS.get()));
	public static final DeferredBlock<SlabBlock> DEPTHROCK_TILE_SLAB = registerWithItem("depthrock_tile_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));

	public static final DeferredBlock<WallBlock> DEPTHROCK_WALL = registerWithItem("depthrock_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<WallBlock> POLISHED_DEPTHROCK_WALL = registerWithItem("polished_depthrock_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(POLISHED_DEPTHROCK.get()));
	public static final DeferredBlock<WallBlock> DEPTHROCK_BRICK_WALL = registerWithItem("depthrock_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK_BRICKS.get()));

	public static final DeferredBlock<ButtonBlock> DEPTHROCK_BUTTON = registerWithItem("depthrock_button", (properties) -> new ButtonBlock(BlockSetType.STONE, 20, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.BASALT));
	public static final DeferredBlock<PressurePlateBlock> DEPTHROCK_PRESSURE_PLATE = registerWithItem("depthrock_pressure_plate", (properties) -> new PressurePlateBlock(BlockSetType.STONE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE).sound(SoundType.BASALT));

	public static final DeferredBlock<Block> DEPTHROCK_POT = registerWithItem("depthrock_pot", DepthrockPotBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).sound(SoundType.DECORATED_POT_CRACKED));

	//shiverstone
	public static final DeferredBlock<Block> SHIVERSTONE = registerWithItem("shiverstone", Block::new, () -> BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.GLOW_LICHEN).strength(3.5F, 12F).sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops().friction(0.98F));
	public static final DeferredBlock<Block> SHIVERSTONE_BRICKS = registerWithItem("shiverstone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()));
	public static final DeferredBlock<Block> CRACKED_SHIVERSTONE_BRICKS = registerWithItem("cracked_shiverstone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()));
	public static final DeferredBlock<Block> CHISELED_SHIVERSTONE_BRICKS = registerWithItem("chiseled_shiverstone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()));

	public static final DeferredBlock<StairBlock> SHIVERSTONE_STAIRS = registerWithItem("shiverstone_stairs", (properties) -> new StairBlock(SHIVERSTONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()));
	public static final DeferredBlock<StairBlock> SHIVERSTONE_BRICK_STAIRS = registerWithItem("shiverstone_brick_stairs", (properties) -> new StairBlock(SHIVERSTONE_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE_BRICKS.get()));

	public static final DeferredBlock<SlabBlock> SHIVERSTONE_SLAB = registerWithItem("shiverstone_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()));
	public static final DeferredBlock<SlabBlock> SHIVERSTONE_BRICK_SLAB = registerWithItem("shiverstone_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE_BRICKS.get()));

	public static final DeferredBlock<WallBlock> SHIVERSTONE_WALL = registerWithItem("shiverstone_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()));
	public static final DeferredBlock<WallBlock> SHIVERSTONE_BRICK_WALL = registerWithItem("shiverstone_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE_BRICKS.get()));

	public static final DeferredBlock<ButtonBlock> SHIVERSTONE_BUTTON = registerWithItem("shiverstone_button", (properties) -> new ButtonBlock(BlockSetType.STONE, 20, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.NETHER_BRICKS));
	public static final DeferredBlock<PressurePlateBlock> SHIVERSTONE_PRESSURE_PLATE = registerWithItem("shiverstone_pressure_plate", (properties) -> new PressurePlateBlock(BlockSetType.STONE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE).sound(SoundType.NETHER_BRICKS));

	//sediment
	public static final DeferredBlock<Block> SEDIMENT = registerWithItem("sediment", Block::new, () -> BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(MapColor.DEEPSLATE).strength(0.5F).sound(SoundType.SAND));
	public static final DeferredBlock<Block> SEDIMENT_GLASS = registerWithItem("sediment_glass", TransparentBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
	public static final DeferredBlock<Block> SEDIMENT_GLASS_PANE = registerWithItem("sediment_glass_pane", IronBarsBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE));

	public static final DeferredBlock<Block> SEDIMENT_STONE = registerWithItem("sediment_stone", Block::new, () -> BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(0.8F));
	public static final DeferredBlock<Block> POLISHED_SEDIMENT_STONE = registerWithItem("polished_sediment_stone", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<Block> SEDIMENT_STONE_BRICKS = registerWithItem("sediment_stone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<Block> CRACKED_SEDIMENT_STONE_BRICKS = registerWithItem("cracked_sediment_stone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<Block> DIRTY_SEDIMENT_STONE_BRICKS = registerWithItem("dirty_sediment_stone_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<Block> CHISELED_SEDIMENT_STONE = registerWithItem("chiseled_sediment_stone", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<Block> SMOOTH_SEDIMENT_STONE = registerWithItem("smooth_sediment_stone", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));

	public static final DeferredBlock<StairBlock> SEDIMENT_STONE_STAIRS = registerWithItem("sediment_stone_stairs", (properties) -> new StairBlock(UGBlocks.SEDIMENT_STONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<StairBlock> POLISHED_SEDIMENT_STONE_STAIRS = registerWithItem("polished_sediment_stone_stairs", (properties) -> new StairBlock(UGBlocks.POLISHED_SEDIMENT_STONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(POLISHED_SEDIMENT_STONE.get()));
	public static final DeferredBlock<StairBlock> SEDIMENT_STONE_BRICK_STAIRS = registerWithItem("sediment_stone_brick_stairs", (properties) -> new StairBlock(UGBlocks.SEDIMENT_STONE_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE_BRICKS.get()));
	public static final DeferredBlock<StairBlock> SMOOTH_SEDIMENT_STONE_STAIRS = registerWithItem("smooth_sediment_stone_stairs", (properties) -> new StairBlock(UGBlocks.SMOOTH_SEDIMENT_STONE.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE_BRICKS.get()));

	public static final DeferredBlock<SlabBlock> SEDIMENT_STONE_SLAB = registerWithItem("sediment_stone_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<SlabBlock> POLISHED_SEDIMENT_STONE_SLAB = registerWithItem("polished_sediment_stone_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(POLISHED_SEDIMENT_STONE.get()));
	public static final DeferredBlock<SlabBlock> SEDIMENT_STONE_BRICK_SLAB = registerWithItem("sediment_stone_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE_BRICKS.get()));
	public static final DeferredBlock<SlabBlock> SMOOTH_SEDIMENT_STONE_SLAB = registerWithItem("smooth_sediment_stone_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SMOOTH_SEDIMENT_STONE.get()));

	public static final DeferredBlock<WallBlock> SEDIMENT_STONE_WALL = registerWithItem("sediment_stone_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE.get()));
	public static final DeferredBlock<WallBlock> POLISHED_SEDIMENT_STONE_WALL = registerWithItem("polished_sediment_stone_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(POLISHED_SEDIMENT_STONE.get()));
	public static final DeferredBlock<WallBlock> SEDIMENT_STONE_BRICK_WALL = registerWithItem("sediment_stone_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SEDIMENT_STONE_BRICKS.get()));
	public static final DeferredBlock<WallBlock> SMOOTH_SEDIMENT_STONE_WALL = registerWithItem("smooth_sediment_stone_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SMOOTH_SEDIMENT_STONE.get()));

	//dreadrock
	public static final DeferredBlock<Block> DREADROCK = registerWithItem("dreadrock", DreadrockBlock::new, () -> BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_GREEN).strength(3.0F, 12.0F).sound(UGSoundTypes.DREADROCK).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> DREADROCK_BRICKS = registerWithItem("dreadrock_bricks", DreadrockBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()));

	public static final DeferredBlock<StairBlock> DREADROCK_STAIRS = registerWithItem("dreadrock_stairs", (properties) -> new DreadrockStairBlock(DREADROCK.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()));
	public static final DeferredBlock<StairBlock> DREADROCK_BRICK_STAIRS = registerWithItem("dreadrock_brick_stairs", (properties) -> new DreadrockStairBlock(DREADROCK_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK_BRICKS.get()));

	public static final DeferredBlock<SlabBlock> DREADROCK_SLAB = registerWithItem("dreadrock_slab", DreadrockSlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()));
	public static final DeferredBlock<SlabBlock> DREADROCK_BRICK_SLAB = registerWithItem("dreadrock_brick_slab", DreadrockSlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK_BRICKS.get()));

	public static final DeferredBlock<WallBlock> DREADROCK_WALL = registerWithItem("dreadrock_wall", DreadrockWallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()));
	public static final DeferredBlock<WallBlock> DREADROCK_BRICK_WALL = registerWithItem("dreadrock_brick_wall", DreadrockWallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK_BRICKS.get()));

	public static final DeferredBlock<ButtonBlock> DREADROCK_BUTTON = registerWithItem("dreadrock_button", (properties) -> new ButtonBlock(BlockSetType.STONE, 20, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(UGSoundTypes.DREADROCK));
	public static final DeferredBlock<PressurePlateBlock> DREADROCK_PRESSURE_PLATE = registerWithItem("dreadrock_pressure_plate", (properties) -> new PressurePlateBlock(BlockSetType.STONE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE).sound(UGSoundTypes.DREADROCK));

	//tremblecrust
	public static final DeferredBlock<Block> TREMBLECRUST = registerWithItem("tremblecrust", Block::new, () -> BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.DEEPSLATE).strength(6F, 24F).sound(SoundType.STONE).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> LOOSE_TREMBLECRUST = registerWithItem("loose_tremblecrust", LooseTremblecrustBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).strength(3F, 24F).noLootTable());
	public static final DeferredBlock<Block> TREMBLECRUST_BRICKS = registerWithItem("tremblecrust_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> CRACKED_TREMBLECRUST_BRICKS = registerWithItem("cracked_tremblecrust_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get()));
	public static final DeferredBlock<Block> CHISELED_TREMBLECRUST_BRICKS = registerWithItem("chiseled_tremblecrust_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get()));

	public static final DeferredBlock<StairBlock> TREMBLECRUST_STAIRS = registerWithItem("tremblecrust_stairs", (properties) -> new StairBlock(TREMBLECRUST.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()));
	public static final DeferredBlock<StairBlock> TREMBLECRUST_BRICK_STAIRS = registerWithItem("tremblecrust_brick_stairs", (properties) -> new StairBlock(TREMBLECRUST_BRICKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get()));

	public static final DeferredBlock<SlabBlock> TREMBLECRUST_SLAB = registerWithItem("tremblecrust_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()));
	public static final DeferredBlock<SlabBlock> TREMBLECRUST_BRICK_SLAB = registerWithItem("tremblecrust_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get()));

	public static final DeferredBlock<WallBlock> TREMBLECRUST_WALL = registerWithItem("tremblecrust_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()));
	public static final DeferredBlock<WallBlock> TREMBLECRUST_BRICK_WALL = registerWithItem("tremblecrust_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST_BRICKS.get()));

	public static final DeferredBlock<ButtonBlock> TREMBLECRUST_BUTTON = registerWithItem("tremblecrust_button", (properties) -> new ButtonBlock(BlockSetType.STONE, 20, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> TREMBLECRUST_PRESSURE_PLATE = registerWithItem("tremblecrust_pressure_plate", (properties) -> new PressurePlateBlock(BlockSetType.STONE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE));

	//ores
	public static final DeferredBlock<Block> DEPTHROCK_COAL_ORE = registerWithItem("depthrock_coal_ore", (properties) -> new DropExperienceBlock(UniformInt.of(0, 2), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> SHIVERSTONE_COAL_ORE = registerWithItem("shiverstone_coal_ore", (properties) -> new DropExperienceBlock(UniformInt.of(0, 2), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> DEPTHROCK_IRON_ORE = registerWithItem("depthrock_iron_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> SHIVERSTONE_IRON_ORE = registerWithItem("shiverstone_iron_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> DEPTHROCK_GOLD_ORE = registerWithItem("depthrock_gold_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> DEPTHROCK_DIAMOND_ORE = registerWithItem("depthrock_diamond_ore", (properties) -> new DropExperienceBlock(UniformInt.of(4, 8), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> SHIVERSTONE_DIAMOND_ORE = registerWithItem("shiverstone_diamond_ore", (properties) -> new DropExperienceBlock(UniformInt.of(4, 8), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> DEPTHROCK_CLOGGRUM_ORE = registerWithItem("depthrock_cloggrum_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> SHIVERSTONE_CLOGGRUM_ORE = registerWithItem("shiverstone_cloggrum_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> SHIVERSTONE_FROSTSTEEL_ORE = registerWithItem("shiverstone_froststeel_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops());
	public static final DeferredBlock<Block> DEPTHROCK_UTHERIUM_ORE = registerWithItem("depthrock_utherium_ore", (properties) -> new DropExperienceBlock(UniformInt.of(4, 8), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(UGItems.UTHERIUM_RARITY));
	public static final DeferredBlock<Block> SHIVERSTONE_UTHERIUM_ORE = registerWithItem("shiverstone_utherium_ore", (properties) -> new DropExperienceBlock(UniformInt.of(4, 8), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(UGItems.UTHERIUM_RARITY));
	public static final DeferredBlock<Block> TREMBLECRUST_UTHERIUM_ORE = registerWithItem("tremblecrust_utherium_ore", (properties) -> new DropExperienceBlock(UniformInt.of(4, 8), properties), () -> BlockBehaviour.Properties.ofFullCopy(TREMBLECRUST.get()).strength(7.0F, 24.0F).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(UGItems.UTHERIUM_RARITY));
	public static final DeferredBlock<Block> DREADROCK_UTHERIUM_ORE = registerWithItem("dreadrock_utherium_ore", (properties) -> new DreadrockOreBlock(UniformInt.of(4, 8), properties), () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(UGItems.UTHERIUM_RARITY));
	public static final DeferredBlock<Block> DEPTHROCK_REGALIUM_ORE = registerWithItem("depthrock_regalium_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).strength(3.0F, 6.0F).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(Rarity.UNCOMMON));
	public static final DeferredBlock<Block> SHIVERSTONE_REGALIUM_ORE = registerWithItem("shiverstone_regalium_ore", (properties) -> new DropExperienceBlock(ConstantInt.of(0), properties), () -> BlockBehaviour.Properties.ofFullCopy(SHIVERSTONE.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(Rarity.UNCOMMON));
	public static final DeferredBlock<Block> DREADROCK_ROGDORIUM_ORE = registerWithItem("dreadrock_rogdorium_ore", (properties) -> new DreadrockOreBlock(UniformInt.of(4, 8), properties), () -> BlockBehaviour.Properties.ofFullCopy(DREADROCK.get()).strength(4.5F, 12.0F).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(UGItems.ROGDORIUM_RARITY));

	//storage blocks
	public static final DeferredBlock<Block> RAW_CLOGGRUM_BLOCK = registerWithItem("raw_cloggrum_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
	public static final DeferredBlock<Block> RAW_FROSTSTEEL_BLOCK = registerWithItem("raw_froststeel_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F));
	public static final DeferredBlock<Block> CLOGGRUM_BLOCK = registerWithItem("cloggrum_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
	public static final DeferredBlock<Block> FROSTSTEEL_BLOCK = registerWithItem("froststeel_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
	public static final DeferredBlock<Block> UTHERIUM_BLOCK = registerWithItem("utherium_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL), () -> new Item.Properties().rarity(UGItems.UTHERIUM_RARITY));
	public static final DeferredBlock<Block> REGALIUM_BLOCK = registerWithItem("regalium_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL), () -> new Item.Properties().rarity(Rarity.UNCOMMON));
	public static final DeferredBlock<Block> ROGDORIUM_BLOCK = registerWithItem("rogdorium_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL), () -> new Item.Properties().rarity(UGItems.ROGDORIUM_RARITY));
	public static final DeferredBlock<Block> FORGOTTEN_BLOCK = registerWithItem("forgotten_block", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.NETHERITE_BLOCK), () -> new Item.Properties().rarity(UGItems.FORGOTTEN_RARITY));

	//normal blocks
	public static final DeferredBlock<Block> DEEPTURF_BLOCK = registerWithItem("deepturf_block", SpreadingDeepturfBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).randomTicks().strength(0.6F).sound(SoundType.GRASS));
	public static final DeferredBlock<Block> ASHEN_DEEPTURF_BLOCK = registerWithItem("ashen_deepturf_block", BasicDeepturfBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).randomTicks().strength(0.6F).sound(SoundType.GRASS));
	public static final DeferredBlock<Block> FROZEN_DEEPTURF_BLOCK = registerWithItem("frozen_deepturf_block", BasicDeepturfBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).randomTicks().strength(0.6F).sound(SoundType.GRASS));
	public static final DeferredBlock<Block> DEEPSOIL = registerWithItem("deepsoil", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
	public static final DeferredBlock<Block> COARSE_DEEPSOIL = registerWithItem("coarse_deepsoil", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(DEEPSOIL.get()));
	public static final DeferredBlock<Block> DEEPSOIL_FARMLAND = registerWithItem("deepsoil_farmland", DeepsoilFarmlandBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND));
	public static final DeferredBlock<Block> GOO = registerWithItem("goo", GooLayerBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().pushReaction(PushReaction.DESTROY).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((_, _, _) -> false).sound(SoundType.SLIME_BLOCK).noOcclusion().noCollision());
	public static final DeferredBlock<Block> GOO_BLOCK = registerWithItem("goo_block", GooBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion());
	public static final DeferredBlock<Block> SMOG_VENT = registerWithItem("smog_vent", SmogVentBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()).lightLevel((state) -> 10).isValidSpawn(((state, level, pos, entity) -> false)));
	public static final DeferredBlock<Block> CLOGGRUM_BARS = registerWithItem("cloggrum_bars", IronBarsBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS));
	public static final DeferredBlock<Block> CLOGGRUM_TILES = registerWithItem("cloggrum_tiles", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_BLOCK.get()));
	public static final DeferredBlock<StairBlock> CLOGGRUM_TILE_STAIRS = registerWithItem("cloggrum_tile_stairs", (properties) -> new StairBlock(CLOGGRUM_TILES.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_TILES.get()));
	public static final DeferredBlock<SlabBlock> CLOGGRUM_TILE_SLAB = registerWithItem("cloggrum_tile_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_TILES.get()));
	public static final DeferredBlock<Block> CLOGGRUM_PILLAR = registerWithItem("cloggrum_pillar", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_BLOCK.get()));
	public static final DeferredBlock<Block> CLOGGRUM_GRATE = registerWithItem("cloggrum_grate", WaterloggedTransparentBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_BLOCK.get()).noOcclusion().isValidSpawn(Blocks::never).isRedstoneConductor((_, _, _) -> false).isSuffocating((_, _, _) -> false).isViewBlocking((_, _, _) -> false));
	public static final DeferredBlock<Block> CLOGGRUM_LADDER = registerWithItem("cloggrum_ladder", LadderBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(CLOGGRUM_BLOCK.get()).forceSolidOff().strength(1.5F).noOcclusion().pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<BedBlock> DEPTHROCK_BED = register("depthrock_bed", DepthrockBedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(DEPTHROCK.get()));
	public static final DeferredBlock<WoolCarpetBlock> MOGMOSS_RUG = registerWithItem("mogmoss_rug", (properties) -> new WoolCarpetBlock(DyeColor.LIME, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET));
	public static final DeferredBlock<WoolCarpetBlock> BLUE_MOGMOSS_RUG = registerWithItem("blue_mogmoss_rug", (properties) -> new WoolCarpetBlock(DyeColor.BLUE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET));
	public static final DeferredBlock<Block> CLOGGRUM_LANTERN = registerWithItem("cloggrum_lantern", CloggrumLanternBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<Block> UTHERIUM_GROWTH = registerWithItem("utherium_growth", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED).lightLevel((state) -> 15), () -> new Item.Properties().rarity(UGItems.UTHERIUM_RARITY));

	//plants
	public static final DeferredBlock<Block> AMOROUS_BRISTLE = registerWithItem("amorous_bristle", UGFlowerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));
	public static final DeferredBlock<Block> MISERABELL = registerWithItem("miserabell", UGFlowerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER));
	public static final DeferredBlock<Block> BUTTERBUNCH = registerWithItem("butterbunch", UGFlowerBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
	public static final DeferredBlock<Block> UNDERBEAN_BUSH = register("underbean_bush", UnderbeanBushBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
	public static final DeferredBlock<Block> BLISTERBERRY_BUSH = register("blisterberry_bush", BlisterberryBushBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> 6));
	public static final DeferredBlock<Block> DEEPTURF = registerWithItem("deepturf", TallDeepturfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
	public static final DeferredBlock<Block> ASHEN_DEEPTURF = registerWithItem("ashen_deepturf", TallDeepturfVariantBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
	public static final DeferredBlock<Block> FROZEN_DEEPTURF = registerWithItem("frozen_deepturf", TallDeepturfVariantBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));
	public static final DeferredBlock<Block> TALL_DEEPTURF = registerWithItem("tall_deepturf", DoublePlantBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final DeferredBlock<Block> SHIMMERWEED = registerWithItem("shimmerweed", ShimmerweedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).lightLevel((state) -> 12));
	public static final DeferredBlock<Block> TALL_SHIMMERWEED = registerWithItem("tall_shimmerweed", TallShimmerweedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).lightLevel((state) -> 14));
	public static final DeferredBlock<Block> DITCHBULB_PLANT = register("ditchbulb_plant", DitchbulbBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).randomTicks().lightLevel((state) -> state.getValue(DitchbulbBlock.AGE) == 1 ? 6 : 0));
	public static final DeferredBlock<Block> GLOOMGOURD = registerWithItem("gloomgourd", GloomgourdBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> CARVED_GLOOMGOURD = registerWithItem("carved_gloomgourd", CarvedGloomgourdBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD), () -> Waypoint.addHideAttribute(new Item.Properties().component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).setSwappable(false).setCameraOverlay(Undergarden.prefix("misc/gloomgourd_overlay")).build())));
	public static final DeferredBlock<Block> GLOOM_O_LANTERN = registerWithItem("gloom_o_lantern", CarvedGloomgourdBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).strength(1.0F).sound(SoundType.WOOD).lightLevel((state) -> 15));
	public static final DeferredBlock<Block> SHARD_O_LANTERN = registerWithItem("shard_o_lantern", CarvedGloomgourdShardBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GLOOM_O_LANTERN.get()).lightLevel((state) -> 6), () -> new Item.Properties().rarity(UGItems.UTHERIUM_RARITY));
	public static final DeferredBlock<Block> BOOMGOURD = registerWithItem("boomgourd", BoomgourdBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<StemBlock> GLOOMGOURD_STEM = register("gloomgourd_stem", (properties) -> new StemBlock(GLOOMGOURD.getKey(), UGBlocks.GLOOMGOURD_STEM_ATTACHED.getKey(), UGItems.GLOOMGOURD_SEEDS.getKey(), BlockTags.SUPPORTS_STEM_CROPS, BlockTags.SUPPORTS_STEM_FRUIT, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MELON_STEM));
	public static final DeferredBlock<AttachedStemBlock> GLOOMGOURD_STEM_ATTACHED = register("gloomgourd_stem_attached", (properties) -> new AttachedStemBlock(UGBlocks.GLOOMGOURD_STEM.getKey(), UGBlocks.GLOOMGOURD.getKey(), UGItems.GLOOMGOURD_SEEDS.getKey(), BlockTags.SUPPORTS_STEM_CROPS, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ATTACHED_MELON_STEM));
	public static final DeferredBlock<Block> DEPTHROCK_PEBBLES = register("depthrock_pebbles", DepthrockPebblesBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().pushReaction(PushReaction.DESTROY).sound(SoundType.BASALT).noOcclusion().noCollision());
	public static final DeferredBlock<GlitterkelpBlock> GLITTERKELP = register("glitterkelp", GlitterkelpBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).lightLevel((state) -> 10));
	public static final DeferredBlock<GlitterkelpPlantBlock> GLITTERKELP_PLANT = register("glitterkelp_plant", GlitterkelpPlantBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.KELP_PLANT).lightLevel((state) -> 10));
	public static final DeferredBlock<DroopvineBlock> DROOPVINE = register("droopvine", DroopvineBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES).strength(0.1F).lightLevel(Droopvine.light()));
	public static final DeferredBlock<DroopvinePlantBlock> DROOPVINE_PLANT = register("droopvine_plant", DroopvinePlantBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT).strength(0.1F).lightLevel(Droopvine.light()));
	public static final DeferredBlock<Block> THORNREED = register("thornreed", ThornreedBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE));
	public static final DeferredBlock<Block> TWISTYBUSH = registerWithItem("twistybush", TwistybushBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH));

	//mushroom
	public static final DeferredBlock<Block> INDIGO_MUSHROOM = registerWithItem("indigo_mushroom", (properties) -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_INDIGO_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).postProcess((state, level, pos) -> pos));
	public static final DeferredBlock<Block> INDIGO_MUSHROOM_CAP = registerWithItem("indigo_mushroom_cap", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.2F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> INDIGO_MUSHROOM_STEM = registerWithItem("indigo_mushroom_stem", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.2F).sound(SoundType.WOOD));

	public static final DeferredBlock<Block> VEIL_MUSHROOM = registerWithItem("veil_mushroom", (properties) -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_VEIL_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).postProcess((state, level, pos) -> pos));
	public static final DeferredBlock<Block> VEIL_MUSHROOM_CAP = registerWithItem("veil_mushroom_cap", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).strength(0.2F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> VEIL_MUSHROOM_STEM = registerWithItem("veil_mushroom_stem", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM));
	public static final DeferredBlock<Block> MUSHROOM_VEIL = registerWithItem("mushroom_veil", MushroomVeilBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.VINE));

	public static final DeferredBlock<Block> INK_MUSHROOM = registerWithItem("ink_mushroom", (properties) -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_INK_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).postProcess((state, level, pos) -> pos));
	public static final DeferredBlock<Block> INK_MUSHROOM_CAP = registerWithItem("ink_mushroom_cap", InkCapBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).strength(0.2F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> SEEPING_INK = registerWithItem("seeping_ink", SeepingInkBlock::new, () -> BlockBehaviour.Properties.of().sound(SoundType.WET_GRASS).instabreak().noOcclusion().noCollision());
	public static final DeferredBlock<Block> INK_MUSHROOM_STEM = registerWithItem("ink_mushroom_stem", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM));

	public static final DeferredBlock<Block> BLOOD_MUSHROOM = registerWithItem("blood_mushroom", (properties) -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_BLOOD_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).postProcess((state, level, pos) -> pos));
	public static final DeferredBlock<Block> BLOOD_MUSHROOM_CAP = registerWithItem("blood_mushroom_cap", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.2F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> ENGORGED_BLOOD_MUSHROOM_CAP = registerWithItem("engorged_blood_mushroom_cap", EngorgedCapBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK));
	public static final DeferredBlock<Block> BLOOD_MUSHROOM_STEM = registerWithItem("blood_mushroom_stem", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM));

	public static final DeferredBlock<Block> PUFF_MUSHROOM = registerWithItem("puff_mushroom", (properties) -> new UGMushroomBlock(UGConfiguredFeatures.HUGE_PUFF_MUSHROOM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).postProcess((state, level, pos) -> pos));
	public static final DeferredBlock<Block> PUFF_MUSHROOM_CAP = registerWithItem("puff_mushroom_cap", PuffMushroomCapBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.2F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> PUFF_MUSHROOM_STEM = registerWithItem("puff_mushroom_stem", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM));

	//smogstem
	public static final DeferredBlock<SaplingBlock> SMOGSTEM_SAPLING = registerWithItem("smogstem_sapling", (properties) -> new UGSaplingBlock(UGTreeGrowers.SMOGSTEM, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).instabreak().randomTicks().sound(SoundType.GRASS).noOcclusion().noCollision());
	public static final DeferredBlock<RotatedPillarBlock> SMOGSTEM_LOG = registerWithItem("smogstem_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SMOGSTEM_LOG = registerWithItem("stripped_smogstem_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> SMOGSTEM_WOOD = registerWithItem("smogstem_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SMOGSTEM_WOOD = registerWithItem("stripped_smogstem_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> SMOGSTEM_LEAVES = registerWithItem("smogstem_leaves", (properties) -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0x14626d), properties), () -> BlockBehaviour.Properties.of().ignitedByLava().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn((state, level, pos, value) -> false).isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false));
	public static final DeferredBlock<Block> SMOGSTEM_PLANKS = registerWithItem("smogstem_planks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
	public static final DeferredBlock<StairBlock> SMOGSTEM_STAIRS = registerWithItem("smogstem_stairs", (properties) -> new StairBlock(SMOGSTEM_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SMOGSTEM_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> SMOGSTEM_SLAB = registerWithItem("smogstem_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SMOGSTEM_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> SMOGSTEM_FENCE = registerWithItem("smogstem_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
	public static final DeferredBlock<FenceGateBlock> SMOGSTEM_FENCE_GATE = registerWithItem("smogstem_fence_gate", (properties) -> new FenceGateBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
	public static final DeferredBlock<DoorBlock> SMOGSTEM_DOOR = registerWithItem("smogstem_door", (properties) -> new DoorBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
	public static final DeferredBlock<TrapDoorBlock> SMOGSTEM_TRAPDOOR = registerWithItem("smogstem_trapdoor", (properties) -> new TrapDoorBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
	public static final DeferredBlock<ButtonBlock> SMOGSTEM_BUTTON = registerWithItem("smogstem_button", (properties) -> new ButtonBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> SMOGSTEM_PRESSURE_PLATE = registerWithItem("smogstem_pressure_plate", (properties) -> new PressurePlateBlock(UGWoodStuff.SMOGSTEM_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));

	public static final DeferredBlock<StandingSignBlock> SMOGSTEM_SIGN = register("smogstem_sign", (properties) -> new StandingSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SIGN));
	public static final DeferredBlock<WallSignBlock> SMOGSTEM_WALL_SIGN = register("smogstem_wall_sign", (properties) -> new WallSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> SMOGSTEM_HANGING_SIGN = register("smogstem_hanging_sign", (properties) -> new CeilingHangingSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> SMOGSTEM_WALL_HANGING_SIGN = register("smogstem_wall_hanging_sign", (properties) -> new WallHangingSignBlock(UGWoodStuff.SMOGSTEM_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_HANGING_SIGN));

	//wigglewood
	public static final DeferredBlock<SaplingBlock> WIGGLEWOOD_SAPLING = registerWithItem("wigglewood_sapling", (properties) -> new UGSaplingBlock(UGTreeGrowers.WIGGLEWOOD, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).instabreak().randomTicks().sound(SoundType.GRASS).noOcclusion().noCollision());
	public static final DeferredBlock<RotatedPillarBlock> WIGGLEWOOD_LOG = registerWithItem("wigglewood_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_LOG = registerWithItem("stripped_wigglewood_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> WIGGLEWOOD_WOOD = registerWithItem("wigglewood_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_WIGGLEWOOD_WOOD = registerWithItem("stripped_wigglewood_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.WOOD));
	public static final DeferredBlock<Block> WIGGLEWOOD_LEAVES = registerWithItem("wigglewood_leaves", (properties) -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0xf58888), properties), () -> BlockBehaviour.Properties.of().ignitedByLava().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_PINK).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isValidSpawn((state, level, pos, value) -> false).isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false));
	public static final DeferredBlock<Block> WIGGLEWOOD_PLANKS = registerWithItem("wigglewood_planks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
	public static final DeferredBlock<StairBlock> WIGGLEWOOD_STAIRS = registerWithItem("wigglewood_stairs", (properties) -> new StairBlock(WIGGLEWOOD_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(WIGGLEWOOD_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> WIGGLEWOOD_SLAB = registerWithItem("wigglewood_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(WIGGLEWOOD_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> WIGGLEWOOD_FENCE = registerWithItem("wigglewood_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
	public static final DeferredBlock<FenceGateBlock> WIGGLEWOOD_FENCE_GATE = registerWithItem("wigglewood_fence_gate", (properties) -> new FenceGateBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
	public static final DeferredBlock<DoorBlock> WIGGLEWOOD_DOOR = registerWithItem("wigglewood_door", (properties) -> new DoorBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
	public static final DeferredBlock<TrapDoorBlock> WIGGLEWOOD_TRAPDOOR = registerWithItem("wigglewood_trapdoor", (properties) -> new TrapDoorBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
	public static final DeferredBlock<ButtonBlock> WIGGLEWOOD_BUTTON = registerWithItem("wigglewood_button", (properties) -> new ButtonBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> WIGGLEWOOD_PRESSURE_PLATE = registerWithItem("wigglewood_pressure_plate", (properties) -> new PressurePlateBlock(UGWoodStuff.WIGGLEWOOD_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));

	public static final DeferredBlock<StandingSignBlock> WIGGLEWOOD_SIGN = register("wigglewood_sign", (properties) -> new StandingSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));
	public static final DeferredBlock<WallSignBlock> WIGGLEWOOD_WALL_SIGN = register("wigglewood_wall_sign", (properties) -> new WallSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, properties), () -> wallVariant(WIGGLEWOOD_SIGN.get(), true)
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava());
	public static final DeferredBlock<CeilingHangingSignBlock> WIGGLEWOOD_HANGING_SIGN = register("wigglewood_hanging_sign", (properties) -> new CeilingHangingSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> WIGGLEWOOD_WALL_HANGING_SIGN = register("wigglewood_wall_hanging_sign", (properties) -> new WallHangingSignBlock(UGWoodStuff.WIGGLEWOOD_WOOD_TYPE, properties), () -> wallVariant(WIGGLEWOOD_HANGING_SIGN.get(), true).mapColor(WIGGLEWOOD_LOG.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());

	//grongle
	public static final DeferredBlock<Block> GRONGLE_SAPLING = registerWithItem("grongle_sapling", (properties) -> new UGSaplingBlock(UGTreeGrowers.GRONGLE, properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).instabreak().randomTicks().sound(SoundType.GRASS).noOcclusion().noCollision());
	public static final DeferredBlock<RotatedPillarBlock> GRONGLE_LOG = registerWithItem("grongle_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GRONGLE_LOG = registerWithItem("stripped_grongle_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
	public static final DeferredBlock<RotatedPillarBlock> GRONGLE_WOOD = registerWithItem("grongle_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GRONGLE_WOOD = registerWithItem("stripped_grongle_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));
	public static final DeferredBlock<Block> GRONGLE_LEAVES = registerWithItem("grongle_leaves", (properties) -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0x36971d), properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).sound(SoundType.AZALEA_LEAVES));
	public static final DeferredBlock<Block> HANGING_GRONGLE_LEAVES = registerWithItem("hanging_grongle_leaves", HangingGrongleLeavesBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GRONGLE_LEAVES.get()).noCollision().noOcclusion());
	public static final DeferredBlock<Block> GRONGLE_PLANKS = registerWithItem("grongle_planks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
	public static final DeferredBlock<StairBlock> GRONGLE_STAIRS = registerWithItem("grongle_stairs", (properties) -> new StairBlock(GRONGLE_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(GRONGLE_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> GRONGLE_SLAB = registerWithItem("grongle_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(GRONGLE_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> GRONGLE_FENCE = registerWithItem("grongle_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
	public static final DeferredBlock<FenceGateBlock> GRONGLE_FENCE_GATE = registerWithItem("grongle_fence_gate", (properties) -> new FenceGateBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
	public static final DeferredBlock<DoorBlock> GRONGLE_DOOR = registerWithItem("grongle_door", (properties) -> new DoorBlock(UGWoodStuff.GRONGLE_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
	public static final DeferredBlock<TrapDoorBlock> GRONGLE_TRAPDOOR = registerWithItem("grongle_trapdoor", (properties) -> new TrapDoorBlock(UGWoodStuff.GRONGLE_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
	public static final DeferredBlock<ButtonBlock> GRONGLE_BUTTON = registerWithItem("grongle_button", (properties) -> new ButtonBlock(UGWoodStuff.GRONGLE_WOOD_SET, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> GRONGLE_PRESSURE_PLATE = registerWithItem("grongle_pressure_plate", (properties) -> new PressurePlateBlock(UGWoodStuff.GRONGLE_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));

	public static final DeferredBlock<StandingSignBlock> GRONGLE_SIGN = register("grongle_sign", (properties) -> new StandingSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));
	public static final DeferredBlock<WallSignBlock> GRONGLE_WALL_SIGN = register("grongle_wall_sign", (properties) -> new WallSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, properties), () -> wallVariant(GRONGLE_SIGN.get(), true)
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava());
	public static final DeferredBlock<CeilingHangingSignBlock> GRONGLE_HANGING_SIGN = register("grongle_hanging_sign", (properties) -> new CeilingHangingSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> GRONGLE_WALL_HANGING_SIGN = register("grongle_wall_hanging_sign", (properties) -> new WallHangingSignBlock(UGWoodStuff.GRONGLE_WOOD_TYPE, properties), () -> wallVariant(GRONGLE_HANGING_SIGN.get(), true).mapColor(GRONGLE_LOG.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());

	public static final DeferredBlock<GrongletBlock> GRONGLET = register("gronglet", GrongletBlock::new, () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noTerrainParticles().lightLevel((state) -> 12).noOcclusion().noCollision().strength(0.0F).sound(UGSoundTypes.GRONGLET));
	public static final DeferredBlock<GrongletBlock> UTHERIC_GRONGLET = register("utheric_gronglet", GrongletBlock::new, () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noTerrainParticles().lightLevel((state) -> 12).noOcclusion().noCollision().strength(0.0F).sound(UGSoundTypes.GRONGLET));
	public static final DeferredBlock<GrongletBlock> ROGDORIC_GRONGLET = register("rogdoric_gronglet", GrongletBlock::new, () -> BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noTerrainParticles().lightLevel((state) -> 12).noOcclusion().noCollision().strength(0.0F).sound(UGSoundTypes.GRONGLET));

	//ancient root
	public static final DeferredBlock<RotatedPillarBlock> ANCIENT_ROOT = registerWithItem("ancient_root", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
	public static final DeferredBlock<RotatedPillarBlock> ROGDORIC_ANCIENT_ROOT = registerWithItem("rogdoric_ancient_root", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ANCIENT_ROOT.get()).strength(4.0F).lightLevel((state) -> 10).requiresCorrectToolForDrops(), () -> new Item.Properties().rarity(UGItems.ROGDORIUM_RARITY));
	public static final DeferredBlock<Block> DENIZEN_TOTEM = registerWithItem("denizen_totem", DenizenTotemBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(4.0F).lightLevel((state) -> state.getValue(DenizenTotemBlock.ACTIVE) ? 15 : 0));
	public static final DeferredBlock<Block> ANCIENT_ROOT_PLANKS = registerWithItem("ancient_root_planks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
	public static final DeferredBlock<StairBlock> ANCIENT_ROOT_STAIRS = registerWithItem("ancient_root_stairs", (properties) -> new StairBlock(ANCIENT_ROOT_PLANKS.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(ANCIENT_ROOT_PLANKS.get()));
	public static final DeferredBlock<SlabBlock> ANCIENT_ROOT_SLAB = registerWithItem("ancient_root_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ANCIENT_ROOT_PLANKS.get()));
	public static final DeferredBlock<FenceBlock> ANCIENT_ROOT_FENCE = registerWithItem("ancient_root_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
	public static final DeferredBlock<FenceGateBlock> ANCIENT_ROOT_FENCE_GATE = registerWithItem("ancient_root_fence_gate", (properties) -> new FenceGateBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
	public static final DeferredBlock<DoorBlock> ANCIENT_ROOT_DOOR = registerWithItem("ancient_root_door", (properties) -> new DoorBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
	public static final DeferredBlock<TrapDoorBlock> ANCIENT_ROOT_TRAPDOOR = registerWithItem("ancient_root_trapdoor", (properties) -> new TrapDoorBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
	public static final DeferredBlock<ButtonBlock> ANCIENT_ROOT_BUTTON = registerWithItem("ancient_root_button", (properties) -> new ButtonBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON));
	public static final DeferredBlock<PressurePlateBlock> ANCIENT_ROOT_PRESSURE_PLATE = registerWithItem("ancient_root_pressure_plate", (properties) -> new PressurePlateBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_SET, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE));

	public static final DeferredBlock<StandingSignBlock> ANCIENT_ROOT_SIGN = register("ancient_root_sign", (properties) -> new StandingSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN));
	public static final DeferredBlock<WallSignBlock> ANCIENT_ROOT_WALL_SIGN = register("ancient_root_wall_sign", (properties) -> new WallSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, properties), () -> wallVariant(ANCIENT_ROOT_SIGN.get(), true)
		.mapColor(MapColor.WOOD)
		.forceSolidOn()
		.instrument(NoteBlockInstrument.BASS)
		.noCollision()
		.strength(1.0F)
		.ignitedByLava());
	public static final DeferredBlock<CeilingHangingSignBlock> ANCIENT_ROOT_HANGING_SIGN = register("ancient_root_hanging_sign", (properties) -> new CeilingHangingSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN));
	public static final DeferredBlock<WallHangingSignBlock> ANCIENT_ROOT_WALL_HANGING_SIGN = register("ancient_root_wall_hanging_sign", (properties) -> new WallHangingSignBlock(UGWoodStuff.ANCIENT_ROOT_WOOD_TYPE, properties), () -> wallVariant(ANCIENT_ROOT_HANGING_SIGN.get(), true).mapColor(ANCIENT_ROOT.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());

	//functional
	public static final DeferredBlock<Block> INFUSER = registerWithItem("infuser", InfuserBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(UGSoundTypes.DREADROCK).noOcclusion().lightLevel((state) -> state.getValue(InfuserBlock.STATE) != InfuserState.INACTIVE ? 10 : 0));

	//flower pots
	public static final DeferredBlock<FlowerPotBlock> POTTED_SMOGSTEM_SAPLING = register("potted_smogstem_sapling", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SMOGSTEM_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_WIGGLEWOOD_SAPLING = register("potted_wigglewood_sapling", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WIGGLEWOOD_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GRONGLE_SAPLING = register("potted_grongle_sapling", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GRONGLE_SAPLING, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SHIMMERWEED = register("potted_shimmerweed", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SHIMMERWEED, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).lightLevel((state) -> 12));
	public static final DeferredBlock<FlowerPotBlock> POTTED_INDIGO_MUSHROOM = register("potted_indigo_mushroom", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INDIGO_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VEIL_MUSHROOM = register("potted_veil_mushroom", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VEIL_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_INK_MUSHROOM = register("potted_ink_mushroom", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, INK_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BLOOD_MUSHROOM = register("potted_blood_mushroom", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLOOD_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_PUFF_MUSHROOM = register("potted_puff_mushroom", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PUFF_MUSHROOM, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_AMOROUS_BRISTLE = register("potted_amorous_bristle", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AMOROUS_BRISTLE, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_MISERABELL = register("potted_miserabell", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, MISERABELL, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BUTTERBUNCH = register("potted_butterbunch", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BUTTERBUNCH, properties), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));

	//fluids
	public static final DeferredBlock<LiquidBlock> VIRULENT_MIX = register("virulent_mix", (properties) -> new VirulentMixBlock(UGFluids.VIRULENT_MIX_SOURCE.get(), properties), () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).replaceable().pushReaction(PushReaction.DESTROY).liquid());

	//cauldrons
	public static final DeferredBlock<Block> VIRULENT_MIX_CAULDRON = register("virulent_mix_cauldron", VirulentMixCauldronBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).lightLevel((state) -> 10).randomTicks());

	public static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		return BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, Undergarden.prefix(name)))));
	}

	public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		return registerWithItem(name, block, properties, Item.Properties::new);
	}

	public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties, Supplier<Item.Properties> itemProperties) {
		DeferredBlock<T> ret = register(name, block, properties);
		UGItems.register(name, itemProps -> new BlockItem(ret.get(), itemProps.useBlockDescriptionPrefix()), itemProperties);
		return ret;
	}

	private static BlockBehaviour.Properties wallVariant(Block standingBlock, boolean copyName) {
		BlockBehaviour.Properties wallProperties = BlockBehaviour.Properties.of().overrideLootTable(standingBlock.getLootTable());
		if (copyName) {
			wallProperties = wallProperties.overrideDescription(standingBlock.getDescriptionId());
		}

		return wallProperties;
	}
}