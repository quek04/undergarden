package quek.undergarden.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.treedecorator.GrongleLeafDecorator;

public class UGTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Undergarden.MODID);

//    public static final RegistryObject<TreeDecoratorType<GrongleLeafDecorator>> GRONGLE_LEAF_DECORATOR = TREE_DECORATORS.register("grongle_leaf_decorator", () -> new TreeDecoratorType<>(GrongleLeafDecorator.CODEC));
    public static final TreeDecoratorType<GrongleLeafDecorator> GRONGLE_LEAF_DECORATOR = register("grongle_leaf_decorator", GrongleLeafDecorator.CODEC);

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, Codec<P> decorator) {
        return Registry.register(Registry.TREE_DECORATOR_TYPES, new ResourceLocation(Undergarden.MODID, name), new TreeDecoratorType<>(decorator));
    }

    public static void stupid() {}
}