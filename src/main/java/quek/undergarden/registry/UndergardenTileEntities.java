package quek.undergarden.registry;

import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.block.tile.*;

import static quek.undergarden.Undergarden.MODID;

public class UndergardenTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);

    public static final RegistryObject<TileEntityType<UndergardenChestTileEntity>> smogstem_chest = TILE_ENTITIES.register(
            "smogstem_chest", () -> new TileEntityType<>(UndergardenChestTileEntity::new, Sets.newHashSet(UndergardenBlocks.smogstem_chest.get()), null));
}
