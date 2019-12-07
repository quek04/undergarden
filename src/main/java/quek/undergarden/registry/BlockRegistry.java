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
import quek.undergarden.Undergarden;
import quek.undergarden.block.world.*;

import java.util.function.Function;
import java.util.function.Supplier;

import static quek.undergarden.Undergarden.MODID;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);

    //basic shit
    public static final RegistryObject<Block> depthrock = registerBlock("depthrock", () -> new UndergardenBlock(Material.ROCK,1.5f,35f, SoundType.STONE,1, ToolType.PICKAXE));
    public static final RegistryObject<Block> smogstem_planks = registerBlock("smogstem_planks", () -> new UndergardenBlock(Material.WOOD,1.5f,2f, SoundType.WOOD, 0, ToolType.AXE));

    //nature
    public static final RegistryObject<Block> smogstem_log = registerBlock("smogstem_log", () -> new UndergardenLog());

    //thanks andro
    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        ItemRegistry.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends Block> block) {
        return (RegistryObject<T>) baseRegister(name, block, BlockRegistry::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().group(UndergardenItemGroups.UNDERGARDEN_BLOCKS));
    }

}
