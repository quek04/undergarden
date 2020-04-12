package quek.undergarden.registry;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.block.world.*;
import quek.undergarden.world.gen.tree.SmogstemTree;
import quek.undergarden.world.gen.tree.WigglewoodTree;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class UndergardenBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, UndergardenMod.MODID);

    //basic shit
    public static final RegistryObject<Block> deepsoil = registerBlock("deepsoil", DeepsoilBlock::new);
    public static final RegistryObject<Block> deepturf_block = registerBlock("deepturf_block", DeepturfBlock::new);
    public static final RegistryObject<Block> depthrock = registerBlock("depthrock", () -> new UndergardenBlock(
            Material.ROCK,1.5F,30F, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<Block> cobbled_depthrock = registerBlock("cobbled_depthrock", () -> new UndergardenBlock(
            Material.ROCK,2F,30F, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<Block> smogstem_planks = registerBlock("smogstem_planks", () -> new UndergardenBlock(
            Material.WOOD,1.5F,10F, SoundType.WOOD, 0, ToolType.AXE));
    public static final RegistryObject<Block> wigglewood_planks = registerBlock("wigglewood_planks", () -> new UndergardenBlock(
            Material.WOOD,1.5F,10F, SoundType.WOOD, 0, ToolType.AXE));

    //nature
    public static final RegistryObject<Block> underbean_bush = BLOCKS.register("underbean_bush", BeanBushBlock::new);
    public static final RegistryObject<SaplingBlock> smogstem_sapling = registerBlock("smogstem_sapling", () -> new UndergardenSaplingBlock(new SmogstemTree()));
    public static final RegistryObject<LogBlock> smogstem_log = registerBlock("smogstem_log", UndergardenLogBlock::new);
    public static final RegistryObject<Block> smogstem_leaves = registerBlock("smogstem_leaves", UndergardenLeavesBlock::new);
    public static final RegistryObject<SaplingBlock> wigglewood_sapling = registerBlock("wigglewood_sapling", () -> new UndergardenSaplingBlock(new WigglewoodTree()));
    public static final RegistryObject<LogBlock> wigglewood_log = registerBlock("wigglewood_log", UndergardenLogBlock::new);
    public static final RegistryObject<Block> wigglewood_leaves = registerBlock("wigglewood_leaves", UndergardenLeavesBlock::new);
    public static final RegistryObject<Block> tall_deepturf = registerBlock("tall_deepturf", UndergardenTallGrassBlock::new);
    public static final RegistryObject<Block> shimmerweed = registerBlock("shimmerweed", () -> new LightbearingPlantBlock(12));
    public static final RegistryObject<Block> indigo_mushroom = registerBlock("indigo_mushroom", () -> new UndergardenMushroomBlock(2));
    public static final RegistryObject<Block> veil_mushroom = registerBlock("veil_mushroom", UndergardenMushroomBlock::new);
    public static final RegistryObject<Block> ink_mushroom = registerBlock("ink_mushroom", UndergardenMushroomBlock::new);
    public static final RegistryObject<Block> blood_mushroom = registerBlock("blood_mushroom", UndergardenMushroomBlock::new);

    //ores
    public static final RegistryObject<Block> coal_ore = registerBlock("coal_ore", () -> new UndergardenOreBlock(0));
    public static final RegistryObject<Block> cloggrum_ore = registerBlock("cloggrum_ore", () -> new UndergardenOreBlock(2));
    public static final RegistryObject<Block> froststeel_ore = registerBlock("froststeel_ore", () -> new UndergardenOreBlock(3));
    public static final RegistryObject<Block> utherium_ore = registerBlock("utherium_ore", () -> new UndergardenOreBlock(3));

    //tiles




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
