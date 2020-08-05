package quek.undergarden.registry;

import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.block.tileentity.ShardTorchTE;

public class UndergardenTEs {

    public static final DeferredRegister<TileEntityType<?>> TEs = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, UndergardenMod.MODID);

    public static final RegistryObject<TileEntityType<ShardTorchTE>> shard_torch_te = TEs.register("shard_torch_te", () ->
            new TileEntityType<>(ShardTorchTE::new, Sets.newHashSet(UndergardenBlocks.shard_torch.get(), UndergardenBlocks.shard_wall_torch.get()), null));
}
