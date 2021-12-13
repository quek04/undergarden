package quek.undergarden.registry;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import quek.undergarden.Undergarden;

public class UGPools {

    public static final StructureTemplatePool CATACOMBS_START = Pools.register(new StructureTemplatePool(new ResourceLocation(Undergarden.MODID, "catacombs/catacombs_entrance"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.single("catacombs/entrance", UGProcessorLists.CATACOMBS_DEGRADATION), 1)), StructureTemplatePool.Projection.RIGID));
}