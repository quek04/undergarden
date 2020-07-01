package quek.undergarden.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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
    public static final RegistryObject<Block> deepturf_block = registerBlock("deepturf_block", DeepturfBlock::new);
    public static final RegistryObject<Block> ashen_deepturf = registerBlock("ashen_deepturf", () -> new UndergardenBlock(
            Material.EARTH, 0.5F, 0.5F, SoundType.GROUND, 0, ToolType.SHOVEL));
    public static final RegistryObject<Block> depthrock = registerBlock("depthrock", () -> new UndergardenBlock(
            Material.ROCK,1.5F,6F, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<Block> cobbled_depthrock = registerBlock("cobbled_depthrock", () -> new UndergardenBlock(
            Material.ROCK,2F,6F, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<Block> smogstem_planks = registerBlock("smogstem_planks", () -> new UndergardenBlock(
            Material.WOOD,2F,3F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<Block> wigglewood_planks = registerBlock("wigglewood_planks", () -> new UndergardenBlock(
            Material.WOOD,2F,3F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<Block> depthrock_bricks = registerBlock("depthrock_bricks", () -> new UndergardenBlock(
            Material.ROCK, 1.5F, 6F, SoundType.STONE, 1, ToolType.PICKAXE));
    public static final RegistryObject<Block> cracked_depthrock_bricks = registerBlock("cracked_depthrock_bricks", () -> new UndergardenBlock(
            Material.ROCK, 1.5F, 6F, SoundType.STONE, 1, ToolType.PICKAXE));
    public static final RegistryObject<Block> shiverstone = registerBlock("shiverstone", () -> new UndergardenBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));
    public static final RegistryObject<Block> cobbled_shiverstone = registerBlock("cobbled_shiverstone", () -> new UndergardenBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));
    public static final RegistryObject<Block> shiverstone_bricks = registerBlock("shiverstone_bricks", () -> new UndergardenBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));
    public static final RegistryObject<Block> goo = registerBlock("goo", GooBlock::new);
    public static final RegistryObject<Block> smog_vent = registerBlock("smog_vent", SmogVentBlock::new);

    //otherside
    public static final RegistryObject<Block> tremblecrust = registerBlock("tremblecrust", () -> new UndergardenBlock(
            Material.ROCK, 6F, 24F, SoundType.STONE, 3, ToolType.PICKAXE));
    public static final RegistryObject<Block> tremblecrust_bricks = registerBlock("tremblecrust_bricks", () -> new UndergardenBlock(
            Material.ROCK, 6F, 24F, SoundType.STONE, 3, ToolType.PICKAXE));

    //nature
    public static final RegistryObject<Block> underbean_bush = BLOCKS.register("underbean_bush", BeanBushBlock::new);
    public static final RegistryObject<Block> blisterberry_bush = BLOCKS.register("blisterberry_bush", BlisterberryBushBlock::new);
    public static final RegistryObject<SaplingBlock> smogstem_sapling = registerBlock("smogstem_sapling", () -> new UndergardenSaplingBlock(new SmogstemTree()));
    public static final RegistryObject<RotatedPillarBlock> smogstem_log = registerBlock("smogstem_log", UndergardenLogBlock::new);
    public static final RegistryObject<Block> smogstem_leaves = registerBlock("smogstem_leaves", UndergardenLeavesBlock::new);
    public static final RegistryObject<SaplingBlock> wigglewood_sapling = registerBlock("wigglewood_sapling", () -> new UndergardenSaplingBlock(new WigglewoodTree()));
    public static final RegistryObject<RotatedPillarBlock> wigglewood_log = registerBlock("wigglewood_log", UndergardenLogBlock::new);
    public static final RegistryObject<Block> wigglewood_leaves = registerBlock("wigglewood_leaves", UndergardenLeavesBlock::new);
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
    //public static final RegistryObject<Block> droopweed = registerBlock("droopweed", DroopweedBlock::new);

    //ores
    public static final RegistryObject<Block> coal_ore = registerBlock("coal_ore", () -> new UndergardenOreBlock(0));
    public static final RegistryObject<Block> cloggrum_ore = registerBlock("cloggrum_ore", () -> new UndergardenOreBlock(1));
    public static final RegistryObject<Block> froststeel_ore = registerBlock("froststeel_ore", () -> new UndergardenOreBlock(2));
    public static final RegistryObject<Block> utherium_ore = registerBlock("utherium_ore", () -> new UndergardenOreBlock(3));
    public static final RegistryObject<Block> otherside_utherium_ore = registerBlock("otherside_utherium_ore", () -> new UndergardenOreBlock(3));
    public static final RegistryObject<Block> regalium_ore = registerBlock("regalium_ore", () -> new UndergardenOreBlock(4));

    //manufactured
    public static final RegistryObject<Block> cloggrum_block = registerBlock("cloggrum_block", () -> new UndergardenBlock(
            Material.IRON, 5.0F, 6.0F, SoundType.METAL, 2, ToolType.PICKAXE));
    public static final RegistryObject<Block> froststeel_block = registerBlock("froststeel_block", () -> new UndergardenBlock(
            Material.IRON, 5.0F, 6.0F, SoundType.METAL, 3, ToolType.PICKAXE));
    public static final RegistryObject<Block> utherium_block = registerBlock("utherium_block", () -> new UndergardenBlock(
            Material.IRON, 5.0F, 6.0F, SoundType.METAL, 3, ToolType.PICKAXE));
    public static final RegistryObject<Block> regalium_block = registerBlock("regalium_block", () -> new UndergardenBlock(
            Material.IRON, 5.0F, 6.0F, SoundType.METAL, 4, ToolType.PICKAXE));
    public static final RegistryObject<Block> smogstem_torch = BLOCKS.register("smogstem_torch", UndergardenTorchBlock::new);
    public static final RegistryObject<Block> smogstem_wall_torch = BLOCKS.register("smogstem_wall_torch", UndergardenWallTorchBlock::new);
    public static final RegistryObject<Block> gloom_o_lantern = registerBlock("gloom_o_lantern", () -> new CarvedGloomgourdBlock(15));
    public static final RegistryObject<Block> cloggrum_bars = registerBlock("cloggrum_bars", CloggrumBarsBlock::new);

    public static final RegistryObject<StairsBlock> depthrock_stairs = registerBlock("depthrock_stairs", () -> new UndergardenStairsBlock(depthrock));
    public static final RegistryObject<StairsBlock> cobbled_depthrock_stairs = registerBlock("cobbled_depthrock_stairs", () -> new UndergardenStairsBlock(cobbled_depthrock));
    public static final RegistryObject<StairsBlock> depthrock_brick_stairs = registerBlock("depthrock_brick_stairs", () -> new UndergardenStairsBlock(depthrock_bricks));
    public static final RegistryObject<StairsBlock> smogstem_stairs = registerBlock("smogstem_stairs", () -> new UndergardenStairsBlock(smogstem_planks));
    public static final RegistryObject<StairsBlock> wigglewood_stairs = registerBlock("wigglewood_stairs", () -> new UndergardenStairsBlock(wigglewood_planks));
    public static final RegistryObject<StairsBlock> shiverstone_stairs = registerBlock("shiverstone_stairs", () -> new UndergardenStairsBlock(shiverstone));
    public static final RegistryObject<StairsBlock> cobbled_shiverstone_stairs = registerBlock("cobbled_shiverstone_stairs", () -> new UndergardenStairsBlock(cobbled_shiverstone));
    public static final RegistryObject<StairsBlock> shiverstone_brick_stairs = registerBlock("shiverstone_brick_stairs", () -> new UndergardenStairsBlock(shiverstone_bricks));

    public static final RegistryObject<SlabBlock> depthrock_slab = registerBlock("depthrock_slab", () -> new UndergardenSlabBlock(
            Material.ROCK,1.5F,6F, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<SlabBlock> cobbled_depthrock_slab = registerBlock("cobbled_depthrock_slab", () -> new UndergardenSlabBlock(
            Material.ROCK,2F,6F, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<SlabBlock> depthrock_brick_slab = registerBlock("depthrock_brick_slab", () -> new UndergardenSlabBlock(
            Material.ROCK, 1.5F, 6F, SoundType.STONE, 1, ToolType.PICKAXE));
    public static final RegistryObject<SlabBlock> smogstem_slab = registerBlock("smogstem_slab", () -> new UndergardenSlabBlock(
            Material.WOOD,2F,3F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<SlabBlock> wigglewood_slab = registerBlock("wigglewood_slab", () -> new UndergardenSlabBlock(
            Material.WOOD,2F,3F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<SlabBlock> shiverstone_slab = registerBlock("shiverstone_slab", () -> new UndergardenSlabBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));
    public static final RegistryObject<SlabBlock> cobbled_shiverstone_slab = registerBlock("cobbled_shiverstone_slab", () -> new UndergardenSlabBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));
    public static final RegistryObject<SlabBlock> shiverstone_brick_slab = registerBlock("shiverstone_brick_slab", () -> new UndergardenSlabBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));

    public static final RegistryObject<WallBlock> cobbled_depthrock_wall = registerBlock("cobbled_depthrock_wall", () -> new UndergardenWallBlock(
            Material.ROCK, 2F, 6F, SoundType.STONE, 1, ToolType.PICKAXE));
    public static final RegistryObject<WallBlock> depthrock_brick_wall = registerBlock("depthrock_brick_wall", () -> new UndergardenWallBlock(
            Material.ROCK, 1.5F, 6F, SoundType.STONE, 1, ToolType.PICKAXE));
    public static final RegistryObject<WallBlock> cobbled_shiverstone_wall = registerBlock("cobbled_shiverstone_wall", () -> new UndergardenWallBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));
    public static final RegistryObject<WallBlock> shiverstone_brick_wall = registerBlock("shiverstone_brick_wall", () -> new UndergardenWallBlock(
            Material.ROCK, 3.5F, 12F, SoundType.STONE, 2, ToolType.PICKAXE));

    public static final RegistryObject<FenceBlock> smogstem_fence = registerBlock("smogstem_fence", () -> new UndergardenFenceBlock(
            Material.WOOD,2F,3F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<FenceBlock> wigglewood_fence = registerBlock("wigglewood_fence", () -> new UndergardenFenceBlock(
            Material.WOOD,2F,3F, SoundType.WOOD, 0, ToolType.AXE));

    public static final RegistryObject<DoorBlock> smogstem_door = registerBlock("smogstem_door", () -> new UndergardenDoorBlock(
            Material.WOOD, 2F, 3F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<DoorBlock> wigglewood_door = registerBlock("wigglewood_door", () -> new UndergardenDoorBlock(
            Material.WOOD, 2F, 3F, SoundType.WOOD, 0, ToolType.AXE));

    public static final RegistryObject<TrapDoorBlock> smogstem_trapdoor = registerBlock("smogstem_trapdoor", () -> new UndergardenTrapDoorBlock(
            Material.WOOD, 2F, 3F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<TrapDoorBlock> wigglewood_trapdoor = registerBlock("wigglewood_trapdoor", () -> new UndergardenTrapDoorBlock(
            Material.WOOD, 2F, 3F, SoundType.WOOD, 0, ToolType.AXE));

    //fluids
    public static final RegistryObject<FlowingFluidBlock> virulent_mix = BLOCKS.register("virulent_mix", () -> new UndergardenFluidBlock(
            UndergardenFluids.virulent_mix_source, Block.Properties.create(Material.WATER)));


    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        UndergardenItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>) baseRegister(name, block, UndergardenBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().group(UndergardenItemGroups.UNDERGARDEN_BLOCKS));
    }
}
