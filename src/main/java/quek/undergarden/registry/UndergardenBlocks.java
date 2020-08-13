package quek.undergarden.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.block.*;
import quek.undergarden.block.world.*;
import quek.undergarden.world.gen.tree.*;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class UndergardenBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UndergardenMod.MODID);

    public static final RegistryObject<Block> undergarden_portal = BLOCKS.register("undergarden_portal", UndergardenPortalBlock::new);

    //basic shit
    public static final RegistryObject<Block> deepsoil = registerBlock("deepsoil", DeepsoilBlock::new);
    public static final RegistryObject<Block> deepsoil_farmland = registerBlock("deepsoil_farmland", DeepsoilFarmlandBlock::new);
    public static final RegistryObject<Block> coarse_deepsoil = registerBlock("coarse_deepsoil", DeepsoilBlock::new);
    public static final RegistryObject<Block> deepturf_block = registerBlock("deepturf_block", DeepturfBlock::new);
    public static final RegistryObject<Block> ashen_deepturf = registerBlock("ashen_deepturf", () -> new Block(
            AbstractBlock.Properties.from(deepturf_block.get())));
    public static final RegistryObject<Block> depthrock = registerBlock("depthrock", () -> new Block(
            AbstractBlock.Properties.from(Blocks.STONE).sound(SoundType.field_235587_I_).harvestLevel(1).setRequiresTool()));
    public static final RegistryObject<Block> smogstem_planks = registerBlock("smogstem_planks", () -> new Block(
            AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> wigglewood_planks = registerBlock("wigglewood_planks", () -> new Block(
            AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> depthrock_bricks = registerBlock("depthrock_bricks", () -> new Block(
            AbstractBlock.Properties.from(depthrock.get()).setRequiresTool()));
    public static final RegistryObject<Block> cracked_depthrock_bricks = registerBlock("cracked_depthrock_bricks", () -> new Block(
            AbstractBlock.Properties.from(depthrock.get()).setRequiresTool()));
    public static final RegistryObject<Block> shiverstone = registerBlock("shiverstone", () -> new Block(
            AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.5F, 12F).sound(SoundType.field_235590_L_).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Block> shiverstone_bricks = registerBlock("shiverstone_bricks", () -> new Block(
            AbstractBlock.Properties.from(shiverstone.get()).setRequiresTool()));
    public static final RegistryObject<Block> goo = registerBlock("goo", GooBlock::new);
    public static final RegistryObject<Block> smog_vent = registerBlock("smog_vent", SmogVentBlock::new);

    //otherside
    public static final RegistryObject<Block> tremblecrust = registerBlock("tremblecrust", () -> new Block(
            AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(6F, 24F).sound(SoundType.field_235589_K_).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool()));
    public static final RegistryObject<Block> loose_tremblecrust = registerBlock("loose_tremblecrust", () -> new LooseTremblecrustBlock(
            AbstractBlock.Properties.from(tremblecrust.get()).noDrops()));
    public static final RegistryObject<Block> tremblecrust_bricks = registerBlock("tremblecrust_bricks", () -> new Block(
            AbstractBlock.Properties.from(tremblecrust.get()).setRequiresTool()));

    //nature
    public static final RegistryObject<Block> underbean_bush = BLOCKS.register("underbean_bush", BeanBushBlock::new);
    public static final RegistryObject<Block> blisterberry_bush = BLOCKS.register("blisterberry_bush", BlisterberryBushBlock::new);

    public static final RegistryObject<SaplingBlock> smogstem_sapling = registerBlock("smogstem_sapling", () -> new UndergardenSaplingBlock(new SmogstemTree()));
    public static final RegistryObject<RotatedPillarBlock> smogstem_log = registerBlock("smogstem_log", UndergardenLogBlock::new);
    public static final RegistryObject<Block> smogstem_wood = registerBlock("smogstem_wood", () -> new Block(AbstractBlock.Properties.from(UndergardenBlocks.smogstem_log.get())));
    public static final RegistryObject<Block> smogstem_leaves = registerBlock("smogstem_leaves", UndergardenLeavesBlock::new);

    public static final RegistryObject<SaplingBlock> wigglewood_sapling = registerBlock("wigglewood_sapling", () -> new UndergardenSaplingBlock(new WigglewoodTree()));
    public static final RegistryObject<RotatedPillarBlock> wigglewood_log = registerBlock("wigglewood_log", UndergardenLogBlock::new);
    public static final RegistryObject<Block> wigglewood_wood = registerBlock("wigglewood_wood", () -> new Block(AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_log.get())));
    public static final RegistryObject<Block> wigglewood_leaves = registerBlock("wigglewood_leaves", UndergardenLeavesBlock::new);

    public static final RegistryObject<Block> gronglet = registerBlock("gronglet", () -> new GrongletBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_FUNGUS), () -> Feature.field_236281_L_.withConfiguration(new HugeFungusConfig(Blocks.CRIMSON_NYLIUM.getDefaultState(), Blocks.CRIMSON_STEM.getDefaultState(), Blocks.NETHER_WART_BLOCK.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), true))));

    public static final RegistryObject<Block> tall_deepturf = registerBlock("tall_deepturf", UndergardenTallGrassBlock::new);
    public static final RegistryObject<Block> ashen_tall_deepturf = registerBlock("ashen_tall_deepturf", AshenTallDeepturfBlock::new);
    public static final RegistryObject<Block> double_deepturf = registerBlock("double_deepturf", UndergardenDoublePlantBlock::new);
    public static final RegistryObject<Block> shimmerweed = registerBlock("shimmerweed", () -> new UndergardenTallGrassBlock(12));
    public static final RegistryObject<Block> double_shimmerweed = registerBlock("double_shimmerweed", () -> new UndergardenDoublePlantBlock(14));
    public static final RegistryObject<Block> ditchbulb_plant = registerBlock("ditchbulb_plant", DitchbulbBlock::new);
    public static final RegistryObject<Block> indigo_mushroom = registerBlock("indigo_mushroom", () -> new UndergardenMushroomBlock(2));
    public static final RegistryObject<Block> veil_mushroom = registerBlock("veil_mushroom", UndergardenMushroomBlock::new);
    public static final RegistryObject<Block> ink_mushroom = registerBlock("ink_mushroom", UndergardenMushroomBlock::new);
    public static final RegistryObject<Block> blood_mushroom = registerBlock("blood_mushroom", UndergardenMushroomBlock::new);
    public static final RegistryObject<StemGrownBlock> gloomgourd = registerBlock("gloomgourd", GloomgourdBlock::new);
    public static final RegistryObject<Block> carved_gloomgourd = registerBlock("carved_gloomgourd", CarvedGloomgourdBlock::new);
    public static final RegistryObject<StemBlock> gloomgourd_stem = BLOCKS.register("gloomgourd_stem", () -> new UndergardenStemBlock(gloomgourd.get()));
    public static final RegistryObject<AttachedStemBlock> gloomgourd_stem_attached = BLOCKS.register("gloomgourd_stem_attached", () -> new UndergardenAttachedStemBlock(gloomgourd.get()));
    public static final RegistryObject<Block> depthrock_pebbles = registerBlock("depthrock_pebbles", DepthrockPebblesBlock::new);
    public static final RegistryObject<GlowingKelpTopBlock> glowing_kelp = BLOCKS.register("glowing_kelp", GlowingKelpTopBlock::new);
    public static final RegistryObject<Block> glowing_kelp_plant = BLOCKS.register("glowing_kelp_plant", () -> new GlowingKelpBlock(glowing_kelp.get()));
    public static final RegistryObject<Block> glowing_sea_grass = registerBlock("glowing_sea_grass", GlowingSeaGrassBlock::new);
    public static final RegistryObject<Block> droopvine_top = BLOCKS.register("droopvine_top", DroopvineTopBlock::new);
    public static final RegistryObject<Block> droopvine = BLOCKS.register("droopvine", DroopvineBlock::new);

    //ores
    public static final RegistryObject<Block> coal_ore = registerBlock("coal_ore", () -> new UndergardenOreBlock(0));
    public static final RegistryObject<Block> iron_ore = registerBlock("iron_ore", () -> new UndergardenOreBlock(1));
    public static final RegistryObject<Block> gold_ore = registerBlock("gold_ore", () -> new UndergardenOreBlock(2));
    public static final RegistryObject<Block> diamond_ore = registerBlock("diamond_ore", () -> new UndergardenOreBlock(3));
    public static final RegistryObject<Block> cloggrum_ore = registerBlock("cloggrum_ore", () -> new UndergardenOreBlock(1));
    public static final RegistryObject<Block> froststeel_ore = registerBlock("froststeel_ore", () -> new UndergardenOreBlock(2));
    public static final RegistryObject<Block> utherium_ore = registerBlock("utherium_ore", () -> new UndergardenOreBlock(3));
    public static final RegistryObject<Block> otherside_utherium_ore = registerBlock("otherside_utherium_ore", () -> new UndergardenOreBlock(3));
    public static final RegistryObject<Block> regalium_ore = registerBlock("regalium_ore", () -> new UndergardenOreBlock(4));

    //manufactured
    public static final RegistryObject<Block> cloggrum_block = registerBlock("cloggrum_block", () -> new Block(
            AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Block> froststeel_block = registerBlock("froststeel_block", () -> new Block(
            AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(3).setRequiresTool()));
    public static final RegistryObject<Block> utherium_block = registerBlock("utherium_block", () -> new Block(
            AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(3).setRequiresTool()));
    public static final RegistryObject<Block> regalium_block = registerBlock("regalium_block", () -> new Block(
            AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestLevel(4).setRequiresTool()));
    public static final RegistryObject<Block> smogstem_torch = BLOCKS.register("smogstem_torch", UndergardenTorchBlock::new);
    public static final RegistryObject<Block> smogstem_wall_torch = BLOCKS.register("smogstem_wall_torch", UndergardenWallTorchBlock::new);
    public static final RegistryObject<Block> shard_torch = BLOCKS.register("shard_torch", ShardTorchBlock::new);
    public static final RegistryObject<Block> shard_wall_torch = BLOCKS.register("shard_wall_torch", ShardWallTorchBlock::new);
    public static final RegistryObject<Block> gloom_o_lantern = registerBlock("gloom_o_lantern", () -> new CarvedGloomgourdBlock(15));
    public static final RegistryObject<Block> cloggrum_bars = registerBlock("cloggrum_bars", CloggrumBarsBlock::new);

    public static final RegistryObject<StairsBlock> depthrock_stairs = registerBlock("depthrock_stairs", () -> new StairsBlock(UndergardenBlocks.depthrock.get().getDefaultState(), AbstractBlock.Properties.from(UndergardenBlocks.depthrock.get()).notSolid()));
    public static final RegistryObject<StairsBlock> depthrock_brick_stairs = registerBlock("depthrock_brick_stairs", () -> new StairsBlock(UndergardenBlocks.depthrock_bricks.get().getDefaultState(), AbstractBlock.Properties.from(UndergardenBlocks.depthrock_bricks.get()).notSolid()));
    public static final RegistryObject<StairsBlock> smogstem_stairs = registerBlock("smogstem_stairs", () -> new StairsBlock(UndergardenBlocks.smogstem_planks.get().getDefaultState(), AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<StairsBlock> wigglewood_stairs = registerBlock("wigglewood_stairs", () -> new StairsBlock(UndergardenBlocks.smogstem_planks.get().getDefaultState(), AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<StairsBlock> shiverstone_stairs = registerBlock("shiverstone_stairs", () -> new StairsBlock(UndergardenBlocks.shiverstone.get().getDefaultState(), AbstractBlock.Properties.from(UndergardenBlocks.shiverstone.get()).notSolid()));
    public static final RegistryObject<StairsBlock> shiverstone_brick_stairs = registerBlock("shiverstone_brick_stairs", () -> new StairsBlock(UndergardenBlocks.shiverstone_bricks.get().getDefaultState(), AbstractBlock.Properties.from(UndergardenBlocks.shiverstone_bricks.get()).notSolid()));

    public static final RegistryObject<SlabBlock> depthrock_slab = registerBlock("depthrock_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UndergardenBlocks.depthrock.get()).notSolid()));
    public static final RegistryObject<SlabBlock> depthrock_brick_slab = registerBlock("depthrock_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UndergardenBlocks.depthrock_bricks.get()).notSolid()));
    public static final RegistryObject<SlabBlock> smogstem_slab = registerBlock("smogstem_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<SlabBlock> wigglewood_slab = registerBlock("wigglewood_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_planks.get()).notSolid()));
    public static final RegistryObject<SlabBlock> shiverstone_slab = registerBlock("shiverstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UndergardenBlocks.shiverstone.get()).notSolid()));
    public static final RegistryObject<SlabBlock> shiverstone_brick_slab = registerBlock("shiverstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(UndergardenBlocks.shiverstone_bricks.get()).notSolid()));

    public static final RegistryObject<WallBlock> depthrock_brick_wall = registerBlock("depthrock_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(depthrock_bricks.get()).notSolid()));
    public static final RegistryObject<WallBlock> shiverstone_brick_wall = registerBlock("shiverstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(shiverstone_bricks.get()).notSolid()));

    public static final RegistryObject<FenceBlock> smogstem_fence = registerBlock("smogstem_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<FenceBlock> wigglewood_fence = registerBlock("wigglewood_fence", () -> new FenceBlock(AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_planks.get()).notSolid()));

    public static final RegistryObject<FenceGateBlock> smogstem_fence_gate = registerBlock("smogstem_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<FenceGateBlock> wigglewood_fence_gate = registerBlock("wigglewood_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_planks.get()).notSolid()));

    public static final RegistryObject<DoorBlock> smogstem_door = registerBlock("smogstem_door", () -> new DoorBlock(AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<DoorBlock> wigglewood_door = registerBlock("wigglewood_door", () -> new DoorBlock(AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_planks.get()).notSolid()));

    public static final RegistryObject<TrapDoorBlock> smogstem_trapdoor = registerBlock("smogstem_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid()));
    public static final RegistryObject<TrapDoorBlock> wigglewood_trapdoor = registerBlock("wigglewood_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_planks.get()).notSolid()));

    public static final RegistryObject<WoodButtonBlock> smogstem_button = registerBlock("smogstem_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<WoodButtonBlock> wigglewood_button = registerBlock("wigglewood_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<StoneButtonBlock> depthrock_button = registerBlock("depthrock_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(UndergardenBlocks.depthrock.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<StoneButtonBlock> shiverstone_button = registerBlock("shiverstone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(UndergardenBlocks.shiverstone.get()).notSolid().doesNotBlockMovement()));

    public static final RegistryObject<PressurePlateBlock> smogstem_pressure_plate = registerBlock("smogstem_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UndergardenBlocks.smogstem_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> wigglewood_pressure_plate = registerBlock("wigglewood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.from(UndergardenBlocks.wigglewood_planks.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> depthrock_pressure_plate = registerBlock("depthrock_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(UndergardenBlocks.depthrock.get()).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<PressurePlateBlock> shiverstone_pressure_plate = registerBlock("shiverstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(UndergardenBlocks.shiverstone.get()).notSolid().doesNotBlockMovement()));

    //fluids
    public static final RegistryObject<FlowingFluidBlock> virulent_mix = BLOCKS.register("virulent_mix", () -> new UndergardenFluidBlock(
            UndergardenFluids.virulent_mix_source, AbstractBlock.Properties.create(Material.WATER)));


    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        UndergardenItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>) baseRegister(name, block, UndergardenBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().group(UndergardenItemGroups.GROUP));
    }
}
