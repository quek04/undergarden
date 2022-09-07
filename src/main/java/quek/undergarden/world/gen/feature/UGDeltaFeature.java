package quek.undergarden.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DeltaFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import quek.undergarden.registry.UGBlocks;

public class UGDeltaFeature extends DeltaFeature {

    private static final ImmutableList<Block> CANNOT_REPLACE = ImmutableList.of(
            Blocks.BEDROCK,
            Blocks.CHEST,
            Blocks.SPAWNER,
            UGBlocks.SMOGSTEM_LEAVES.get(),
            UGBlocks.WIGGLEWOOD_LEAVES.get(),
            UGBlocks.GRONGLE_LEAVES.get(),
            UGBlocks.BLOOD_MUSHROOM_CAP.get(),
            UGBlocks.INDIGO_MUSHROOM_CAP.get(),
            UGBlocks.INK_MUSHROOM_CAP.get(),
            UGBlocks.VEIL_MUSHROOM_CAP.get(),
            UGBlocks.DEPTHROCK.get(),
            UGBlocks.SHIVERSTONE.get(),
            UGBlocks.BLOOD_MUSHROOM_GLOBULE.get(),
            Blocks.PACKED_ICE,
            Blocks.SNOW_BLOCK
    );

    private static final Direction[] DIRECTIONS = Direction.values();

    public UGDeltaFeature(Codec<DeltaFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<DeltaFeatureConfiguration> context) {
        boolean generate = false;
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        DeltaFeatureConfiguration config = context.config();
        BlockPos pos = context.origin();
        boolean rimChance = random.nextDouble() < 0.9D;
        int i = rimChance ? config.rimSize().sample(random) : 0;
        int j = rimChance ? config.rimSize().sample(random) : 0;
        boolean flag2 = rimChance && i != 0 && j != 0;
        int k = config.size().sample(random);
        int l = config.size().sample(random);
        int i1 = Math.max(k, l);

        for(BlockPos blockpos1 : BlockPos.withinManhattan(pos, k, 0, l)) {
            if (blockpos1.distManhattan(pos) > i1) {
                break;
            }

            if (isClear(level, blockpos1, config)) {
                if (flag2) {
                    generate = true;
                    this.setBlock(level, blockpos1, config.rim());
                }

                BlockPos blockpos2 = blockpos1.offset(i, 0, j);
                if (isClear(level, blockpos2, config)) {
                    generate = true;
                    this.setBlock(level, blockpos2, config.contents());
                }
            }
        }

        return generate;
    }

    private static boolean isClear(LevelAccessor level, BlockPos pos, DeltaFeatureConfiguration config) {
        BlockState state = level.getBlockState(pos);
        if (state.is(config.contents().getBlock()) || CANNOT_REPLACE.contains(state.getBlock()) || !state.isSolidRender(level, pos)) {
            return false;
        } else {
            for(Direction direction : DIRECTIONS) {
                boolean isAir = level.getBlockState(pos.relative(direction)).isAir();
                if (isAir && direction != Direction.UP || !isAir && direction == Direction.UP) {
                    return false;
                }
            }

            return true;
        }
    }
}
