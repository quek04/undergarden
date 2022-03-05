package quek.undergarden.registry;

import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import quek.undergarden.world.gen.trunkplacer.SmogstemTrunkPlacer;

public class UGTrunkPlacerTypes {

    public static final TrunkPlacerType<SmogstemTrunkPlacer> SMOGSTEM_TRUNK_PLACER = new TrunkPlacerType<>(SmogstemTrunkPlacer.CODEC);
}