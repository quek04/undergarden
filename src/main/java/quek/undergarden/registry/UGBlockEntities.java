package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.*;

public class UGBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Undergarden.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SmogVentBlockEntity>> SMOG_VENT = BLOCK_ENTITIES.register("smog_vent", () ->
			BlockEntityType.Builder.of(SmogVentBlockEntity::new, UGBlocks.SMOG_VENT.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DepthrockBedBlockEntity>> DEPTHROCK_BED = BLOCK_ENTITIES.register("depthrock_bed", () ->
			BlockEntityType.Builder.of(DepthrockBedBlockEntity::new, UGBlocks.DEPTHROCK_BED.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrongletBlockEntity>> GRONGLET = BLOCK_ENTITIES.register("gronglet", () -> BlockEntityType.Builder.of(GrongletBlockEntity::new, UGBlocks.GRONGLET.get()).build(null));
}