package quek.undergarden.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;
import java.util.function.Function;

public class SmogVentFeature extends Feature<NoFeatureConfig> {

    public SmogVentFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        BlockPos blockpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        if (worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos).getBlock() == UndergardenBlocks.sooten_deepsoil.get()) {
            BlockState depthrock = UndergardenBlocks.depthrock.get().getDefaultState();
            BlockState vent = UndergardenBlocks.smog_vent.get().getDefaultState();
            int k = 1 + rand.nextInt(10);

            for (int l = 0; l <= k; ++l) {
                if (l == k) {
                    worldIn.setBlockState(blockpos, depthrock, 2);
                    ++i;
                } else {
                    worldIn.setBlockState(blockpos, vent, 2);
                }
                if (l > 0) {
                    BlockPos blockpos1 = blockpos.down();
                    if (depthrock.isValidPosition(worldIn, blockpos1) && worldIn.getBlockState(blockpos1.down()).getBlock() != UndergardenBlocks.smog_vent.get()) {
                        worldIn.setBlockState(blockpos1, depthrock, 2);
                        ++i;
                    }
                    break;
                }

                blockpos = blockpos.up();
            }
        }
        return i > 0;
    }
}
