package quek.undergarden.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.*;

public class UGBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Undergarden.MODID);

	public static final RegistryObject<BlockEntityType<SmogVentBlockEntity>> SMOG_VENT = BLOCK_ENTITIES.register("smog_vent", () ->
			BlockEntityType.Builder.of(SmogVentBlockEntity::new, UGBlocks.SMOG_VENT.get()).build(null));
	public static final RegistryObject<BlockEntityType<UndergardenSignBlockEntity>> UNDERGARDEN_SIGN = BLOCK_ENTITIES.register("undergarden_sign", () ->
			BlockEntityType.Builder.of(UndergardenSignBlockEntity::new,
					UGBlocks.SMOGSTEM_SIGN.get(),
					UGBlocks.SMOGSTEM_WALL_SIGN.get(),
					UGBlocks.WIGGLEWOOD_SIGN.get(),
					UGBlocks.WIGGLEWOOD_WALL_SIGN.get(),
					UGBlocks.GRONGLE_SIGN.get(),
					UGBlocks.GRONGLE_WALL_SIGN.get()
			).build(null));
	public static final RegistryObject<BlockEntityType<UndergardenHangingSignBlockEntity>> UNDERGARDEN_HANGING_SIGN = BLOCK_ENTITIES.register("undergarden_hanging_sign", () ->
			BlockEntityType.Builder.of(UndergardenHangingSignBlockEntity::new,
					UGBlocks.SMOGSTEM_HANGING_SIGN.get(),
					UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get(),
					UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(),
					UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get(),
					UGBlocks.GRONGLE_HANGING_SIGN.get(),
					UGBlocks.GRONGLE_WALL_HANGING_SIGN.get()
			).build(null));
	public static final RegistryObject<BlockEntityType<DepthrockBedBlockEntity>> DEPTHROCK_BED = BLOCK_ENTITIES.register("depthrock_bed", () ->
			BlockEntityType.Builder.of(DepthrockBedBlockEntity::new, UGBlocks.DEPTHROCK_BED.get()).build(null));
	public static final RegistryObject<BlockEntityType<GrongletBlockEntity>> GRONGLET = BLOCK_ENTITIES.register("gronglet", () -> BlockEntityType.Builder.of(GrongletBlockEntity::new, UGBlocks.GRONGLET.get()).build(null));
}