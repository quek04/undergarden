package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.List;
import java.util.Random;

public class DeepturfBlock extends UndergardenGrassBlock implements IGrowable {

    public DeepturfBlock() {
        super(Properties.create(Material.ORGANIC)
                .hardnessAndResistance(0.6F, 3F)
                .sound(SoundType.PLANT)
                .harvestLevel(0)
                .harvestTool(ToolType.SHOVEL)
                .tickRandomly()
        );
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld serverWorld, Random random, BlockPos blockPos, BlockState blockState) {
        BlockPos blockPosUp = blockPos.up();
        BlockState BlockStateIn = UndergardenBlocks.deepturf_block.get().getDefaultState();

        label48:
        for(int lvt_7_1_ = 0; lvt_7_1_ < 128; ++lvt_7_1_) {
            BlockPos posUp = blockPosUp;

            for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_7_1_ / 16; ++lvt_9_1_) {
                posUp = posUp.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (serverWorld.getBlockState(posUp.down()).getBlock() != this || serverWorld.getBlockState(posUp).isCollisionShapeOpaque(serverWorld, posUp)) {
                    continue label48;
                }
            }

            BlockState blockStateUp = serverWorld.getBlockState(posUp);
            if (blockStateUp.getBlock() == BlockStateIn.getBlock() && random.nextInt(10) == 0) {
                ((IGrowable)BlockStateIn.getBlock()).grow(serverWorld, random, posUp, blockStateUp);
            }

            if (blockStateUp.isAir()) {
                BlockState lvt_10_2_;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> lvt_11_1_ = serverWorld.getBiome(posUp).getFlowers();
                    if (lvt_11_1_.isEmpty()) {
                        continue;
                    }

                    ConfiguredFeature<?, ?> lvt_12_1_ = ((DecoratedFeatureConfig)((ConfiguredFeature)lvt_11_1_.get(0)).config).feature;
                    lvt_10_2_ = ((FlowersFeature)lvt_12_1_.feature).getFlowerToPlace(random, posUp, lvt_12_1_.config);
                } else {
                    lvt_10_2_ = BlockStateIn;
                }

                if (lvt_10_2_.isValidPosition(serverWorld, posUp)) {
                    serverWorld.setBlockState(posUp, lvt_10_2_, 3);
                }
            }
        }

    }
}
