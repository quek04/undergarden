package quek.undergarden.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.block.tileentity.ShardTorchTE;
import quek.undergarden.block.tileentity.SmogVentTE;

public class UGTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Undergarden.MODID);

    public static final RegistryObject<TileEntityType<ShardTorchTE>> SHARD_TORCH = TILE_ENTITIES.register("shard_torch_te", () ->
            TileEntityType.Builder.of(ShardTorchTE::new, UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get(), UGBlocks.SHARD_O_LANTERN.get()).build(null));
    public static final RegistryObject<TileEntityType<SmogVentTE>> SMOG_VENT = TILE_ENTITIES.register("smog_vent_te", () ->
            TileEntityType.Builder.of(SmogVentTE::new, UGBlocks.SMOG_VENT.get()).build(null));
}