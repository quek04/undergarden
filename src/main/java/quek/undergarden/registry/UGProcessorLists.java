package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import quek.undergarden.Undergarden;

public class UGProcessorLists {

    public static final StructureProcessorList CATACOMBS_DEGRADATION = register("catacombs_degradation", ImmutableList.of(new RuleProcessor(ImmutableList.of(new ProcessorRule(new RandomBlockMatchTest(UGBlocks.DEPTHROCK_BRICKS.get(), 0.5F), AlwaysTrueTest.INSTANCE, UGBlocks.CRACKED_DEPTHROCK_BRICKS.get().defaultBlockState())))));

    private static StructureProcessorList register(String name, ImmutableList<StructureProcessor> processors) {
        return BuiltinRegistries.register(BuiltinRegistries.PROCESSOR_LIST, new ResourceLocation(Undergarden.MODID, name), new StructureProcessorList(processors));
    }

    public static void init() {}
}