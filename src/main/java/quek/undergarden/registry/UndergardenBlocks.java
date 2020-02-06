package quek.undergarden.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.block.UndergardenChestBlock;
import quek.undergarden.block.world.*;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import static quek.undergarden.Undergarden.MODID;

@SuppressWarnings("unused")
public class UndergardenBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);

    //basic shit
    public static final RegistryObject<Block> deepsoil = registerBlock("deepsoil", () -> new UndergardenBlock(
            Material.EARTH, 0.6f, 3f, SoundType.GROUND, 0, ToolType.SHOVEL));
    public static final RegistryObject<Block> deepturf = registerBlock("deepturf", DeepturfBlock::new);
    public static final RegistryObject<Block> depthrock = registerBlock("depthrock", () -> new UndergardenBlock(
            Material.ROCK,1.5f,35f, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<Block> cobbled_depthrock = registerBlock("cobbled_depthrock", () -> new UndergardenBlock(
            Material.ROCK,1.5f,35f, SoundType.STONE,1, ToolType.PICKAXE)); //TODO: texture & find block values on wiki
    public static final RegistryObject<Block> cloggrum_ore = registerBlock("cloggrum_ore", () -> new UndergardenOre(3));
    public static final RegistryObject<Block> smogstem_planks = registerBlock("smogstem_planks", () -> new UndergardenBlock(
            Material.WOOD,1.5f,10f, SoundType.WOOD, 0, ToolType.AXE));

    //nature
    public static final RegistryObject<Block> underbean_bush = registerBlock("underbean_bush", BeanBushBlock::new); //TODO: texture
    public static final RegistryObject<Block> smogstem_log = registerBlock("smogstem_log", UndergardenLog::new);
    public static final RegistryObject<Block> tall_deepturf = registerBlock("tall_deepturf", UndergardenTallGrass::new);

    //tiles
    public static final RegistryObject<Block> smogstem_chest = registerBlock("smogstem_chest", () -> new UndergardenChestBlock(
            Material.WOOD,1.5f,10f, SoundType.WOOD, 0, ToolType.AXE)); //TODO: texture

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
