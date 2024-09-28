package quek.undergarden.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;

import java.util.Set;
import java.util.stream.Collectors;

public class UGPointOfInterests {

	private static final Set<BlockState> RESTING_BLOCKS = ImmutableSet.of(Blocks.CAMPFIRE, Blocks.SOUL_CAMPFIRE).stream()
			.flatMap(block -> block.getStateDefinition().getPossibleStates().stream())
			.filter(state -> state.getValue(BlockStateProperties.LIT))
			.collect(Collectors.toSet());

	public static final DeferredRegister<PoiType> POI = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Undergarden.MODID);

	public static final DeferredHolder<PoiType, PoiType> UNDERGARDEN_PORTAL = POI.register("undergarden_portal", () -> new PoiType(ImmutableSet.copyOf(UGBlocks.UNDERGARDEN_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));
	public static final DeferredHolder<PoiType, PoiType> DENIZEN_RESTING_BLOCKS = POI.register("denizen_resting_blocks", () -> new PoiType(RESTING_BLOCKS, 5, 6));
}
