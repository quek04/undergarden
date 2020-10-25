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

    public static final DeferredRegister<TileEntityType<?>> TEs = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, UGMod.MODID);

    public static final RegistryObject<TileEntityType<ShardTorchTE>> shard_torch_te = TEs.register("shard_torch_te", () ->
            new TileEntityType<>(ShardTorchTE::new, Sets.newHashSet(UGBlocks.shard_torch.get(), UGBlocks.shard_wall_torch.get()), null));
    public static final RegistryObject<TileEntityType<SmogVentTE>> smog_vent_te = TEs.register("smog_vent_te", () ->
            new TileEntityType<>(SmogVentTE::new, Sets.newHashSet(UGBlocks.smog_vent.get()), null));
}
