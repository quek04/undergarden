package quek.undergarden.registry;

import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.block.tileentity.ShardTorchTE;
import quek.undergarden.block.tileentity.SmogVentTE;

public class UGTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, UGMod.MODID);

    public static final RegistryObject<TileEntityType<ShardTorchTE>> SHARD_TORCH = TILE_ENTITIES.register("shard_torch_te", () ->
            new TileEntityType<>(ShardTorchTE::new, Sets.newHashSet(UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get()), null));
    public static final RegistryObject<TileEntityType<SmogVentTE>> SMOG_VENT = TILE_ENTITIES.register("smog_vent_te", () ->
            new TileEntityType<>(SmogVentTE::new, Sets.newHashSet(UGBlocks.SMOG_VENT.get()), null));
}