package quek.undergarden.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.block.tileentity.DepthrockBedTE;
import quek.undergarden.block.tileentity.ShardTorchTE;
import quek.undergarden.block.tileentity.SmogVentTE;
import quek.undergarden.block.tileentity.UndergardenSignTE;

public class UGTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Undergarden.MODID);

    public static final RegistryObject<TileEntityType<ShardTorchTE>> SHARD_TORCH = TILE_ENTITIES.register("shard_torch_te", () ->
            TileEntityType.Builder.of(ShardTorchTE::new, UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get(), UGBlocks.SHARD_O_LANTERN.get()).build(null));
    public static final RegistryObject<TileEntityType<SmogVentTE>> SMOG_VENT = TILE_ENTITIES.register("smog_vent_te", () ->
            TileEntityType.Builder.of(SmogVentTE::new, UGBlocks.SMOG_VENT.get()).build(null));
    public static final RegistryObject<TileEntityType<UndergardenSignTE>> UNDERGARDEN_SIGN = TILE_ENTITIES.register("undergarden_sign", () ->
            TileEntityType.Builder.of(UndergardenSignTE::new,
                    UGBlocks.SMOGSTEM_SIGN.get(),
                    UGBlocks.SMOGSTEM_WALL_SIGN.get(),
                    UGBlocks.WIGGLEWOOD_SIGN.get(),
                    UGBlocks.WIGGLEWOOD_WALL_SIGN.get(),
                    UGBlocks.GRONGLE_SIGN.get(),
                    UGBlocks.GRONGLE_WALL_SIGN.get()
            ).build(null));
    public static final RegistryObject<TileEntityType<DepthrockBedTE>> DEPTHROCK_BED = TILE_ENTITIES.register("depthrock_bed", () -> TileEntityType.Builder.of(DepthrockBedTE::new, UGBlocks.DEPTHROCK_BED.get()).build(null));
}