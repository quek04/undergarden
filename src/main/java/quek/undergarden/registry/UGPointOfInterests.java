package quek.undergarden.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;

import java.util.Set;

public class UGPointOfInterests {

	public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, Undergarden.MODID);

	public static final RegistryObject<PoiType> UNDERGARDEN_PORTAL = POI.register("undergarden_portal", () -> new PoiType(getBlockStates(UGBlocks.UNDERGARDEN_PORTAL.get()), 0, 1));

	private static Set<BlockState> getBlockStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
}
