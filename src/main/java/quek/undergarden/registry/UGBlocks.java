package quek.undergarden.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.block.*;
import quek.undergarden.world.gen.tree.SmogstemTree;
import quek.undergarden.world.gen.tree.WigglewoodTree;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class UGBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UGMod.MODID);

    public static final RegistryObject<Block> undergarden_portal = BLOCKS.register("undergarden_portal", UndergardenPortalBlock::new);

    //basic shit
    public static final RegistryObject<Block> deepsoil = registerBlock("deepsoil", () -> new DeepsoilBlock(AbstractBlock.Properties.from(Blocks.DIRT)));
    public static final RegistryObject<Block> deepsoil_farmland = registerBlock("deepsoil_farmland", () -> new DeepsoilFarmlandBlock(AbstractBlock.Properties.from(Blocks.FARMLAND)));
    public static final RegistryObject<Block> coarse_deepsoil = registerBlock("coarse_deepsoil", () -> new DeepsoilBlock(AbstractBlock.Properties.from(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> deepturf_block = registerBlock("deepturf_block", () -> new DeepturfBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> ashen_deepturf_block = registerBlock("ashen_deepturf_block", () -> new Block(AbstractBlock.Properties.from(deepturf_block.get())));
    public static final RegistryObject<Block> depthrock = registerBlock("depthrock", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE).sound(SoundType.BASALT).harvestLevel(1).setRequiresTool()));
    public static final RegistryObject<Block> smogstem_planks = registerBlock("smogstem_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> wigglewood_planks = registerBlock("wigglewood_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> grongle_planks = registerBlock("grongle_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> depthrock_bricks = registerBlock("depthrock_bricks", () -> new Block(AbstractBlock.Properties.from(depthrock.get()).setRequiresTool()));
    public static final RegistryObject<Block> cracked_depthrock_bricks = registerBlock("cracked_depthrock_bricks", () -> new Block(AbstractBlock.Properties.from(depthrock.get()).setRequiresTool()));
    public static final RegistryObject<Block> shiverstone = registerBlock("shiverstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.5F, 12F).sound(SoundType.NETHER_BRICK).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().slipperiness(0.98F)));
    public static final RegistryObject<Block> shiverstone_bricks = registerBlock("shiverstone_bricks", () -> new Block(AbstractBlock.Properties.from(shiverstone.get()).setRequiresTool()));
    public static final RegistryObject<Block> cracked_shiverstone_bricks = registerBlock("cracked_shiverstone_bricks", () -> new Block(AbstractBlock.Properties.from(shiverstone.get())));
    public static final RegistryObject<Block> goo = registerBlock("goo", () -> new GooBlock(AbstractBlock.Properties.from(Blocks.SNOW).sound(SoundType.SLIME).notSolid().doesNotBlockMovement().harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> smog_vent = registerBlock("smog_vent", () -> new SmogVentBlock(AbstractBlock.Properties.from(depthrock.get())));

    //otherside
    public static final RegistryObject<Block> tremblecrust = registerBlock("tremblecrust", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(6F, 24F).sound(SoundType.NETHERRACK).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool()));
    public static final RegistryObject<Block> loose_tremblecrust = registerBlock("loose_tremblecrust", () -> new LooseTremblecrustBlock(AbstractBlock.Properties.from(tremblecrust.get()).noDrops()));
    public static final RegistryObject<Block> tremblecrust_bricks = registerBlock("tremblecrust_bricks", () -> new Block(AbstractBlock.Properties.from(tremblecrust.get()).setRequiresTool()));

    //tree/wood
    public static final RegistryObject<SaplingBlock> smogstem_sapling = registerBlock("smogstem_sapling", () -> new UGSaplingBlock(new SmogstemTree()));
    public static final RegistryObject<RotatedPillarBlock> smogstem_log = registerBlock("smogstem_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> stripped_smogstem_log = registerBlock("stripped_smogstem_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> smogstem_wood = registerBlock("smogstem_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> stripped_smogstem_wood = registerBlock("stripped_smogstem_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> smogstem_leaves = registerBlock("smogstem_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));

    public static final RegistryObject<SaplingBlock> wigglewood_sapling = registerBlock("wigglewood_sapling", () -> new UGSaplingBlock(new WigglewoodTree()));
    public static final RegistryObject<RotatedPillarBlock> wigglewood_log = registerBlock("wigglewood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> stripped_wigglewood_log = registerBlock("stripped_wigglewood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> wigglewood_wood = registerBlock("wigglewood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> stripped_wigglewood_wood = registerBlock("stripped_wigglewood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> wigglewood_leaves = registerBlock("wigglewood_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> gronglet = registerBlock("gronglet", () -> new GrongletBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_FUNGUS).sound(SoundType.SHROOMLIGHT), () -> Feature.HUGE_FUNGUS.withConfiguration(new HugeFungusConfig(UGBlocks.deepturf_block.get().getDefaultState(), UGBlocks.grongle_stem.get().getDefaultState(), UGBlocks.grongle_cap.get().getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), true))));
    public static final RegistryObject<RotatedPillarBlock> grongle_stem = registerBlock("grongle_stem", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<RotatedPillarBlock> stripped_grongle_stem = registerBlock("stripped_grongle_stem", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<RotatedPillarBlock> grongle_hyphae = registerBlock("grongle_hyphae", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<RotatedPillarBlock> stripped_grongle_hyphae = registerBlock("stripped_grongle_hyphae", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_STEM).sound(SoundType.SHROOMLIGHT)));
    public static final RegistryObject<Block> grongle_cap = registerBlock("grongle_cap", () -> new Block(AbstractBlock.Properties.from(Blocks.WARPED_WART_BLOCK).sound(SoundType.SHROOMLIGHT)));

    //mushroom
    public static final RegistryObject<Block> indigo_mushroom = registerBlock("indigo_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM).setLightLevel((state) -> 2)));
    public static final RegistryObject<Block> veil_mushroom = registerBlock("veil_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> ink_mushroom = registerBlock("ink_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM)));
    public static final RegistryObject<Block> blood_mushroom = registerBlock("blood_mushroom", () -> new UGMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM)));

    public static final RegistryObject<Block> indigo_mushroom_cap = registerBlock("indigo_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> indigo_mushroom_stalk = registerBlock("indigo_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));

    public static final RegistryObject<Block> veil_mushroom_cap = registerBlock("veil_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> veil_mushroom_stalk = registerBlock("veil_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> mushroom_veil = BLOCKS.register("mushroom_veil", () -> new MushroomVeilBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES).noDrops(), Direction.DOWN, VoxelShapes.fullCube(), false));
    public static final RegistryObject<AbstractTopPlantBlock> mushroom_veil_top = BLOCKS.register("mushroom_veil_top", () -> new MushroomVeilTopBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES_PLANT).noDrops(), Direction.DOWN, VoxelShapes.fullCube(), false, 0.1D));

    public static final RegistryObject<Block> ink_mushroom_cap = registerBlock("ink_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> seeping_ink = BLOCKS.register("seeping_ink", () -> new SeepingInkBlock(AbstractBlock.Properties.create(Material.WOOD).zeroHardnessAndResistance().noDrops().notSolid().doesNotBlockMovement()));

    public static final RegistryObject<Block> blood_mushroom_cap = registerBlock("blood_mushroom_cap", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static final RegistryObject<Block> blood_mushroom_globule = registerBlock("blood_mushroom_globule", () -> new Block(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK).sound(SoundType.SLIME)));
    public static final RegistryObject<Block> blood_mushroom_stalk = registerBlock("blood_mushroom_stalk", () -> new HugeMushroomBlock(AbstractBlock.Properties.from(Blocks.MUSHROOM_STEM)));

    //plants
    public static final RegistryObject<Block> underbean_bush = BLOCKS.register("underbean_bush", () -> new UnderbeanBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> blisterberry_bush = BLOCKS.register("blisterberry_bush", () -> new BlisterberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH).setLightLevel((state) -> 6)));
    public static final RegistryObject<Block> deepturf = registerBlock("deepturf", () -> new UGTallGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS)));
    public static final RegistryObject<Block> ashen_deepturf = registerBlock("ashen_deepturf", () -> new AshenTallDeepturfBlock(AbstractBlock.Properties.from(Blocks.GRASS)));
    public static final RegistryObject<Block> tall_deepturf = registerBlock("tall_deepturf", () -> new UGDoublePlantBlock(AbstractBlock.Properties.from(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> shimmerweed = registerBlock("shimmerweed", () -> new UGTallGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS).setLightLevel((state) -> 12)));
    public static final RegistryObject<Block> tall_shimmerweed = registerBlock("tall_shimmerweed", () -> new UGDoublePlantBlock(AbstractBlock.Properties.from(Blocks.TALL_GRASS).setLightLevel((state) -> 14)));
    public static final RegistryObject<Block> ditchbulb_plant = registerBlock("ditchbulb_plant", () -> new DitchbulbBlock(AbstractBlock.Properties.from(Blocks.TALL_GRASS).setLightLevel((state) -> 6)));
    public static final RegistryObject<StemGrownBlock> gloomgourd = registerBlock("gloomgourd", () -> new GloomgourdBlock(AbstractBlock.Properties.from(Blocks.PUMPKIN)));
    public static final RegistryObject<Block> carved_gloomgourd = BLOCKS.register("carved_gloomgourd", () -> new CarvedGloomgourdBlock(AbstractBlock.Properties.from(Blocks.CARVED_PUMPKIN)));
    public static final RegistryObject<StemBlock> gloomgourd_stem = BLOCKS.register("gloomgourd_stem", () -> new UGStemBlock(gloomgourd.get(), AbstractBlock.Properties.from(Blocks.PUMPKIN_STEM)));
    public static final RegistryObject<AttachedStemBlock> gloomgourd_stem_attached = BLOCKS.register("gloomgourd_stem_attached", () -> new UGAttachedStemBlock(gloomgourd.get(), AbstractBlock.Properties.from(Blocks.ATTACHED_PUMPKIN_STEM)));
    public static final RegistryObject<Block> depthrock_pebbles = registerBlock("depthrock_pebbles", () -> new DepthrockPebblesBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT).notSolid().doesNotBlockMovement().hardnessAndResistance(0F)));
    public static final RegistryObject<GlowingKelpTopBlock> glowing_kelp = BLOCKS.register("glowing_kelp", () -> new GlowingKelpTopBlock(AbstractBlock.Properties.from(Blocks.KELP).setLightLevel((state) -> 10)));
    public static final RegistryObject<Block> glowing_kelp_plant = BLOCKS.register("glowing_kelp_plant", () -> new GlowingKelpBlock(AbstractBlock.Properties.from(Blocks.KELP_PLANT).setLightLevel((state) -> 10)));
    public static final RegistryObject<Block> droopvine_top = BLOCKS.register("droopvine_top", () -> new DroopvineTopBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES).setLightLevel((state) -> 10), Direction.DOWN, false, 0.1D));
    public static final RegistryObject<Block> droopvine = BLOCKS.register("droopvine", () -> new DroopvineBlock(AbstractBlock.Properties.from(Blocks.WEEPING_VINES_PLANT).setLightLevel(DroopvineBlock.glowIfGlowy()), Direction.DOWN, false));

    //ores
    public static final RegistryObject<Block> coal_ore = registerBlock("coal_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(0)));
    public static final RegistryObject<Block> iron_ore = registerBlock("iron_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(1)));
    public static final RegistryObject<Block> gold_ore = registerBlock("gold_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(2)));
    public static final RegistryObject<Block> diamond_ore = registerBlock("diamond_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(3)));
    public static final RegistryObject<Block> cloggrum_ore = registerBlock("cloggrum_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(1)));
    public static final RegistryObject<Block> froststeel_ore = registerBlock("froststeel_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(2)));
    public static final RegistryObject<Block> utherium_ore = registerBlock("utherium_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(3)));
    public static final RegistryObject<Block> otherside_utherium_ore = registerBlock("otherside_utherium_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(4)));
    public static final RegistryObject<Block> regalium_ore = registerBlock("regalium_ore", () -> new UGOreBlock(AbstractBlock.Properties.from(depthrock.get()).harvestLevel(4)));

    //manufactured
    public static final RegistryObject<Block> cloggrum_block = registerBlock("cloggrum_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Block> froststeel_block = registerBlock("froststeel_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(3).setRequiresTool()));
    public static final RegistryObject<Block> utherium_block = registerBlock("utherium_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(3).setRequiresTool()));
    public static final RegistryObject<Block> regalium_block = registerBlock("regalium_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(4).setRequiresTool()));
    public static final RegistryObject<Block> shard_torch = BLOCKS.register("shard_torch", () -> new ShardTorchBlock(AbstractBlock.Properties.from(Blocks.TORCH).setLightLevel((state) -> 6)));
    public static final RegistryObject<Block> shard_wall_torch = BLOCKS.register("shard_wall_torch", () -> new ShardWallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH).setLightLevel((state) -> 6)));
    public static final RegistryObject<Block> gloom_o_lantern = registerBlock("gloom_o_lantern", () -> new CarvedGloomgourdBlock(AbstractBlock.Properties.from(Blocks.JACK_O_LANTERN).setLightLevel((state) -> 15)));
    public static final RegistryObject<Block> cloggrum_bars = registerBlock("cloggrum_bars", () -> new PaneBlock(AbstractBlock.Properties.from(Blocks.IRON_BARS)));

    public static final RegistryObject<StairsBlock> depthrock_stairs = registerBlock("depthrock_stairs", () -> new StairsBlock(() -> UGBlocks.depthrock.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.depthrock.get()).notSolid()));
    public static final RegistryObject<StairsBlock> depthrock_brick_stairs = registerBlock("depthrock_brick_stairs", () -> new StairsBlock(() -> UGBlocks.depthrock_bricks.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.depthrock_bricks.get()).notSolid()));
    public static final RegistryObject<StairsBlock> smogstem_stairs = registerBlock("smogstem_stairs", () -> new StairsBlock(() -> UGBlocks.smogstem_planks.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<StairsBlock> wigglewood_stairs = registerBlock("wigglewood_stairs", () -> new StairsBlock(() -> UGBlocks.smogstem_planks.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<StairsBlock> grongle_stairs = registerBlock("grongle_stairs", () -> new StairsBlock(() -> UGBlocks.grongle_planks.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid()));
    public static final RegistryObject<StairsBlock> shiverstone_stairs = registerBlock("shiverstone_stairs", () -> new StairsBlock(() -> UGBlocks.shiverstone.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.shiverstone.get()).notSolid()));
    public static final RegistryObject<StairsBlock> shiverstone_brick_stairs = registerBlock("shiverstone_brick_stairs", () -> new StairsBlock(() -> UGBlocks.shiverstone_bricks.get().getDefaultState(), AbstractBlock.Properties.from(UGBlocks.shiverstone_bricks.get()).notSolid()));

    public static final RegistryObject<SlabBlock> depthrock_slab = registerBlock("depthrock_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.depthrock.get()).notSolid()));
    public static final RegistryObject<SlabBlock> depthrock_brick_slab = registerBlock("depthrock_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.depthrock_bricks.get()).notSolid()));
    public static final RegistryObject<SlabBlock> smogstem_slab = registerBlock("smogstem_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<SlabBlock> wigglewood_slab = registerBlock("wigglewood_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.wigglewood_planks.get()).notSolid()));
    public static final RegistryObject<SlabBlock> grongle_slab = registerBlock("grongle_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid()));
    public static final RegistryObject<SlabBlock> shiverstone_slab = registerBlock("shiverstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.shiverstone.get()).notSolid()));
    public static final RegistryObject<SlabBlock> shiverstone_brick_slab = registerBlock("shiverstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UGBlocks.shiverstone_bricks.get()).notSolid()));

    public static final RegistryObject<WallBlock> depthrock_wall = registerBlock("depthrock_wall", () -> new WallBlock(AbstractBlock.Properties.from(depthrock.get()).notSolid()));
    public static final RegistryObject<WallBlock> depthrock_brick_wall = registerBlock("depthrock_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(depthrock_bricks.get()).notSolid()));
    public static final RegistryObject<WallBlock> shiverstone_wall = registerBlock("shiverstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(shiverstone.get()).notSolid()));
    public static final RegistryObject<WallBlock> shiverstone_brick_wall = registerBlock("shiverstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(shiverstone_bricks.get()).notSolid()));

    public static final RegistryObject<FenceBlock> smogstem_fence = registerBlock("smogstem_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<FenceBlock> wigglewood_fence = registerBlock("wigglewood_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UGBlocks.wigglewood_planks.get()).notSolid()));
    public static final RegistryObject<FenceBlock> grongle_fence = registerBlock("grongle_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid()));

    public static final RegistryObject<FenceGateBlock> smogstem_fence_gate = registerBlock("smogstem_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<FenceGateBlock> wigglewood_fence_gate = registerBlock("wigglewood_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UGBlocks.wigglewood_planks.get()).notSolid()));
    public static final RegistryObject<FenceGateBlock> grongle_fence_gate = registerBlock("grongle_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid()));

    public static final RegistryObject<DoorBlock> smogstem_door = registerBlock("smogstem_door", () -> new DoorBlock(AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<DoorBlock> wigglewood_door = registerBlock("wigglewood_door", () -> new DoorBlock(AbstractBlock.Properties.from(UGBlocks.wigglewood_planks.get()).notSolid()));
    public static final RegistryObject<DoorBlock> grongle_door = registerBlock("grongle_door", () -> new DoorBlock(AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid()));

    public static final RegistryObject<TrapDoorBlock> smogstem_trapdoor = registerBlock("smogstem_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<TrapDoorBlock> wigglewood_trapdoor = registerBlock("wigglewood_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UGBlocks.wigglewood_planks.get()).notSolid()));
    public static final RegistryObject<TrapDoorBlock> grongle_trapdoor = registerBlock("grongle_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid()));

    public static final RegistryObject<WoodButtonBlock> smogstem_button = registerBlock("smogstem_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<WoodButtonBlock> wigglewood_button = registerBlock("wigglewood_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UGBlocks.wigglewood_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<WoodButtonBlock> grongle_button = registerBlock("grongle_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<StoneButtonBlock> depthrock_button = registerBlock("depthrock_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(UGBlocks.depthrock.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<StoneButtonBlock> shiverstone_button = registerBlock("shiverstone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(UGBlocks.shiverstone.get()).notSolid().doesNotBlockMovement()));

    public static final RegistryObject<PressurePlateBlock> smogstem_pressure_plate = registerBlock("smogstem_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UGBlocks.smogstem_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> wigglewood_pressure_plate = registerBlock("wigglewood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UGBlocks.wigglewood_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> grongle_pressure_plate = registerBlock("grongle_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UGBlocks.grongle_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> depthrock_pressure_plate = registerBlock("depthrock_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(UGBlocks.depthrock.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> shiverstone_pressure_plate = registerBlock("shiverstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(UGBlocks.shiverstone.get()).notSolid().doesNotBlockMovement()));

    //fluids
    public static final RegistryObject<FlowingFluidBlock> virulent_mix = BLOCKS.register("virulent_mix", () -> new UGFluidBlock(
            UGFluids.virulent_mix_source, AbstractBlock.Properties.create(Material.WATER).noDrops()));

    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        UGItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>)baseRegister(name, block, UGBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().group(UGItemGroups.GROUP));
    }
}
