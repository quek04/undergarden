package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.*;

public class UGBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Undergarden.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SmogVentBlockEntity>> SMOG_VENT = BLOCK_ENTITIES.register("smog_vent", () -> new BlockEntityType<>(SmogVentBlockEntity::new, UGBlocks.SMOG_VENT.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DepthrockBedBlockEntity>> DEPTHROCK_BED = BLOCK_ENTITIES.register("depthrock_bed", () -> new BlockEntityType<>(DepthrockBedBlockEntity::new, UGBlocks.DEPTHROCK_BED.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrongletBlockEntity>> GRONGLET = BLOCK_ENTITIES.register("gronglet", () -> new BlockEntityType<>(GrongletBlockEntity::new, UGBlocks.GRONGLET.get(), UGBlocks.UTHERIC_GRONGLET.get(), UGBlocks.ROGDORIC_GRONGLET.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DenizenTotemBlockEntity>> DENIZEN_TOTEM = BLOCK_ENTITIES.register("denizen_totem", () -> new BlockEntityType<>(DenizenTotemBlockEntity::new, UGBlocks.DENIZEN_TOTEM.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfuserBlockEntity>> INFUSER = BLOCK_ENTITIES.register("infuser", () -> new BlockEntityType<>(InfuserBlockEntity::new, UGBlocks.INFUSER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DepthrockPotBlockEntity>> DEPTHROCK_POT = BLOCK_ENTITIES.register("depthrock_pot", () -> new BlockEntityType<>(DepthrockPotBlockEntity::new, UGBlocks.DEPTHROCK_POT.get()));
}