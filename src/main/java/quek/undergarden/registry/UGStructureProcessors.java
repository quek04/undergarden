package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.structure.processor.ReplaceBlockWithEntityProcessor;

public class UGStructureProcessors {
	public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, Undergarden.MODID);

	public static final DeferredHolder<StructureProcessorType<?>, StructureProcessorType<ReplaceBlockWithEntityProcessor>> REPLACE_BLOCK_WITH_ENTITY = STRUCTURE_PROCESSORS.register("replace_block_with_entity", () -> () -> ReplaceBlockWithEntityProcessor.CODEC);
}
