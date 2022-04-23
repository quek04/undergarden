package quek.undergarden.registry;

import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.treedecorator.GrongleLeafDecorator;
import quek.undergarden.world.gen.treedecorator.GrongletTrunkDecorator;

public class UGTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Undergarden.MODID);

    public static final RegistryObject<TreeDecoratorType<GrongleLeafDecorator>> GRONGLE_LEAF_DECORATOR = TREE_DECORATORS.register("grongle_leaf_decorator", () -> new TreeDecoratorType<>(GrongleLeafDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<GrongletTrunkDecorator>> GRONGLET_TRUNK_DECORATOR = TREE_DECORATORS.register("gronglet_trunk_decorator", () -> new TreeDecoratorType<>(GrongletTrunkDecorator.CODEC));
}