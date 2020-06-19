package quek.undergarden.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import quek.undergarden.block.world.DroopweedBlock;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;
import java.util.function.Function;

public class DroopweedFeature extends Feature<NoFeatureConfig> {

    private static final Direction[] DIRECTIONS = Direction.values();

    public DroopweedFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(pos);
        BlockPos blockpos2 = new BlockPos(blockpos$mutable.getX(), blockpos$mutable.getY() - 1, blockpos$mutable.getZ());

        if(worldIn.isAirBlock(pos.down()) && worldIn.getBlockState(pos).getBlock() == UndergardenBlocks.depthrock.get()) {
            for(int i = pos.getY(); i < 255; ++i) {
                blockpos$mutable.setPos(pos);
                blockpos$mutable.move(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
                blockpos$mutable.setY(rand.nextInt(i));
                if (worldIn.isAirBlock(blockpos$mutable)) {
                    for(Direction direction : DIRECTIONS) {
                        if (direction == Direction.DOWN && DroopweedBlock.canAttachTo(worldIn, blockpos$mutable, direction)) {
                            worldIn.setBlockState(blockpos$mutable, UndergardenBlocks.droopweed.get().getDefaultState().with(DroopweedBlock.getPropertyFor(direction), Boolean.TRUE), 2);
                            worldIn.setBlockState(blockpos2, UndergardenBlocks.droopweed.get().getDefaultState().with(DroopweedBlock.getPropertyFor(direction), Boolean.TRUE), 2);
                            break;
                        }
                    }
                }
            }

            return true;
        }
        else return false;
    }
}
