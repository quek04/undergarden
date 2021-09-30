package quek.undergarden.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.DepthrockBedBlockEntity;
import quek.undergarden.block.entity.ShardTorchBlockEntity;
import quek.undergarden.block.entity.SmogVentBlockEntity;
import quek.undergarden.block.entity.UndergardenSignBlockEntity;

public class UGBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Undergarden.MODID);

    public static final RegistryObject<BlockEntityType<ShardTorchBlockEntity>> SHARD_TORCH = BLOCK_ENTITIES.register("shard_torch", () ->
            BlockEntityType.Builder.of(ShardTorchBlockEntity::new, UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get(), UGBlocks.SHARD_O_LANTERN.get()).build(null));
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
    public static final RegistryObject<BlockEntityType<DepthrockBedBlockEntity>> DEPTHROCK_BED = BLOCK_ENTITIES.register("depthrock_bed", () ->
            BlockEntityType.Builder.of(DepthrockBedBlockEntity::new, UGBlocks.DEPTHROCK_BED.get()).build(null));
}