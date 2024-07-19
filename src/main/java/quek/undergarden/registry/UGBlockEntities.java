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
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UndergardenSignBlockEntity>> UNDERGARDEN_SIGN = BLOCK_ENTITIES.register("undergarden_sign", () ->
			BlockEntityType.Builder.of(UndergardenSignBlockEntity::new,
					UGBlocks.SMOGSTEM_SIGN.get(),
					UGBlocks.SMOGSTEM_WALL_SIGN.get(),
					UGBlocks.WIGGLEWOOD_SIGN.get(),
					UGBlocks.WIGGLEWOOD_WALL_SIGN.get(),
					UGBlocks.GRONGLE_SIGN.get(),
					UGBlocks.GRONGLE_WALL_SIGN.get(),
					UGBlocks.ANCIENT_ROOT_SIGN.get(),
					UGBlocks.ANCIENT_ROOT_WALL_SIGN.get()
			).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UndergardenHangingSignBlockEntity>> UNDERGARDEN_HANGING_SIGN = BLOCK_ENTITIES.register("undergarden_hanging_sign", () ->
			BlockEntityType.Builder.of(UndergardenHangingSignBlockEntity::new,
					UGBlocks.SMOGSTEM_HANGING_SIGN.get(),
					UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get(),
					UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(),
					UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get(),
					UGBlocks.GRONGLE_HANGING_SIGN.get(),
					UGBlocks.GRONGLE_WALL_HANGING_SIGN.get(),
					UGBlocks.ANCIENT_ROOT_HANGING_SIGN.get(),
					UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN.get()
			).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DepthrockBedBlockEntity>> DEPTHROCK_BED = BLOCK_ENTITIES.register("depthrock_bed", () ->
			BlockEntityType.Builder.of(DepthrockBedBlockEntity::new, UGBlocks.DEPTHROCK_BED.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrongletBlockEntity>> GRONGLET = BLOCK_ENTITIES.register("gronglet", () -> BlockEntityType.Builder.of(GrongletBlockEntity::new, UGBlocks.GRONGLET.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DenizenTotemBlockEntity>> DENIZEN_TOTEM = BLOCK_ENTITIES.register("denizen_totem", () -> BlockEntityType.Builder.of(DenizenTotemBlockEntity::new, UGBlocks.DENIZEN_TOTEM.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfuserBlockEntity>> INFUSER = BLOCK_ENTITIES.register("infuser", () -> BlockEntityType.Builder.of(InfuserBlockEntity::new, UGBlocks.INFUSER.get()).build(null));
}