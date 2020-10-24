package quek.undergarden.world.gen.feature;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.BitSetVoxelShapePart;
import net.minecraft.util.math.shapes.VoxelShapePart;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.*;

public class UndergardenTreeFeature extends Feature<BaseTreeFeatureConfig> {

    public UndergardenTreeFeature(Codec<BaseTreeFeatureConfig> codec) {
        super(codec);
    }

    public static boolean func_236410_c_(IWorldGenerationBaseReader world, BlockPos pos) {
        return isReplaceableAt(world, pos) || world.hasBlockState(pos, (state) -> state.isIn(BlockTags.LOGS));
    }

    private static boolean isVine(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> state.isIn(Blocks.VINE));
    }

    private static boolean isWater(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> state.isIn(Blocks.WATER));
    }

    public static boolean isLeaves(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> state.isAir() || state.isIn(BlockTags.LEAVES));
    }

    private static boolean isDeepturf(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return block == UndergardenBlocks.deepturf_block.get();
        });
    }

    private static boolean isTallPlant(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> {
            Material material = state.getMaterial();
            return material == Material.TALL_PLANTS;
        });
    }

    public static void func_236408_b_(IWorldWriter world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, 19);
    }

    public static boolean isReplaceableAt(IWorldGenerationBaseReader world, BlockPos pos) {
        return isLeaves(world, pos) || isTallPlant(world, pos) || isWater(world, pos);
    }

    /**
     * Called when placing the tree feature.
     */
    private boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> leaves, Set<BlockPos> logs, MutableBoundingBox boundingBoxIn, BaseTreeFeatureConfig configIn) {
        int trunk = configIn.field_236678_g_.func_236917_a_(rand);
        int leaf = configIn.field_236677_f_.func_230374_a_(rand, trunk, configIn);
        int k = trunk - leaf;
        int l = configIn.field_236677_f_.func_230376_a_(rand, k);
        BlockPos blockpos;

        blockpos = positionIn;

        if (blockpos.getY() >= 1 && blockpos.getY() + trunk + 1 <= 256) {
            if (!isDeepturf(generationReader, blockpos.down()) || isWater(generationReader, positionIn)) {
                return false;
            } else {
                OptionalInt optionalint = configIn.field_236679_h_.func_236710_c_();
                int l1 = this.func_241521_a_(generationReader, trunk, blockpos, configIn);
                if (l1 >= trunk || optionalint.isPresent() && l1 >= optionalint.getAsInt()) {
                    List<FoliagePlacer.Foliage> list = configIn.field_236678_g_.func_230382_a_(generationReader, rand, l1, blockpos, leaves, boundingBoxIn, configIn);
                    list.forEach((foliage) -> configIn.field_236677_f_.func_236752_a_(generationReader, rand, configIn, l1, foliage, leaf, l, logs, boundingBoxIn));
                    return true;
                } else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }

    private int func_241521_a_(IWorldGenerationBaseReader p_241521_1_, int p_241521_2_, BlockPos p_241521_3_, BaseTreeFeatureConfig p_241521_4_) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i = 0; i <= p_241521_2_ + 1; ++i) {
            int j = p_241521_4_.field_236679_h_.func_230369_a_(p_241521_2_, i);

            for(int k = -j; k <= j; ++k) {
                for(int l = -j; l <= j; ++l) {
                    blockpos$mutable.setAndOffset(p_241521_3_, k, i, l);
                    if (!func_236410_c_(p_241521_1_, blockpos$mutable) || !p_241521_4_.field_236681_j_ && isVine(p_241521_1_, blockpos$mutable)) {
                        return i - 2;
                    }
                }
            }
        }

        return p_241521_2_;
    }

    protected void setBlockState(IWorldWriter world, BlockPos pos, BlockState state) {
        func_236408_b_(world, pos, state);
    }

    @Override
    public final boolean func_241855_a(ISeedReader p_241855_1_, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos p_241855_4_, BaseTreeFeatureConfig p_241855_5_) {
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set1 = Sets.newHashSet();
        Set<BlockPos> set2 = Sets.newHashSet();
        MutableBoundingBox mutableboundingbox = MutableBoundingBox.getNewBoundingBox();
        boolean flag = this.place(p_241855_1_, p_241855_3_, p_241855_4_, set, set1, mutableboundingbox, p_241855_5_);
        if (mutableboundingbox.minX <= mutableboundingbox.maxX && flag && !set.isEmpty()) {
            if (!p_241855_5_.decorators.isEmpty()) {
                List<BlockPos> list = Lists.newArrayList(set);
                List<BlockPos> list1 = Lists.newArrayList(set1);
                list.sort(Comparator.comparingInt(Vector3i::getY));
                list1.sort(Comparator.comparingInt(Vector3i::getY));
                p_241855_5_.decorators.forEach((p_236405_6_) -> {
                    p_236405_6_.func_225576_a_(p_241855_1_, p_241855_3_, list, list1, set2, mutableboundingbox);
                });
            }

            VoxelShapePart voxelshapepart = this.func_236403_a_(p_241855_1_, mutableboundingbox, set, set2);
            Template.func_222857_a(p_241855_1_, 3, voxelshapepart, mutableboundingbox.minX, mutableboundingbox.minY, mutableboundingbox.minZ);
            return true;
        } else {
            return false;
        }
    }

    private VoxelShapePart func_236403_a_(IWorld p_236403_1_, MutableBoundingBox p_236403_2_, Set<BlockPos> p_236403_3_, Set<BlockPos> p_236403_4_) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        VoxelShapePart voxelshapepart = new BitSetVoxelShapePart(p_236403_2_.getXSize(), p_236403_2_.getYSize(), p_236403_2_.getZSize());
        int i = 6;

        for(int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(BlockPos blockpos : Lists.newArrayList(p_236403_4_)) {
            if (p_236403_2_.isVecInside(blockpos)) {
                voxelshapepart.setFilled(blockpos.getX() - p_236403_2_.minX, blockpos.getY() - p_236403_2_.minY, blockpos.getZ() - p_236403_2_.minZ, true, true);
            }
        }

        for(BlockPos blockpos1 : Lists.newArrayList(p_236403_3_)) {
            if (p_236403_2_.isVecInside(blockpos1)) {
                voxelshapepart.setFilled(blockpos1.getX() - p_236403_2_.minX, blockpos1.getY() - p_236403_2_.minY, blockpos1.getZ() - p_236403_2_.minZ, true, true);
            }

            for(Direction direction : Direction.values()) {
                blockpos$mutable.setAndMove(blockpos1, direction);
                if (!p_236403_3_.contains(blockpos$mutable)) {
                    BlockState blockstate = p_236403_1_.getBlockState(blockpos$mutable);
                    if (blockstate.hasProperty(BlockStateProperties.DISTANCE_1_7)) {
                        list.get(0).add(blockpos$mutable.toImmutable());
                        func_236408_b_(p_236403_1_, blockpos$mutable, blockstate.with(BlockStateProperties.DISTANCE_1_7, Integer.valueOf(1)));
                        if (p_236403_2_.isVecInside(blockpos$mutable)) {
                            voxelshapepart.setFilled(blockpos$mutable.getX() - p_236403_2_.minX, blockpos$mutable.getY() - p_236403_2_.minY, blockpos$mutable.getZ() - p_236403_2_.minZ, true, true);
                        }
                    }
                }
            }
        }

        for(int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for(BlockPos blockpos2 : set) {
                if (p_236403_2_.isVecInside(blockpos2)) {
                    voxelshapepart.setFilled(blockpos2.getX() - p_236403_2_.minX, blockpos2.getY() - p_236403_2_.minY, blockpos2.getZ() - p_236403_2_.minZ, true, true);
                }

                for(Direction direction1 : Direction.values()) {
                    blockpos$mutable.setAndMove(blockpos2, direction1);
                    if (!set.contains(blockpos$mutable) && !set1.contains(blockpos$mutable)) {
                        BlockState blockstate1 = p_236403_1_.getBlockState(blockpos$mutable);
                        if (blockstate1.hasProperty(BlockStateProperties.DISTANCE_1_7)) {
                            int k = blockstate1.get(BlockStateProperties.DISTANCE_1_7);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.with(BlockStateProperties.DISTANCE_1_7, Integer.valueOf(l + 1));
                                func_236408_b_(p_236403_1_, blockpos$mutable, blockstate2);
                                if (p_236403_2_.isVecInside(blockpos$mutable)) {
                                    voxelshapepart.setFilled(blockpos$mutable.getX() - p_236403_2_.minX, blockpos$mutable.getY() - p_236403_2_.minY, blockpos$mutable.getZ() - p_236403_2_.minZ, true, true);
                                }

                                set1.add(blockpos$mutable.toImmutable());
                            }
                        }
                    }
                }
            }
        }

        return voxelshapepart;
    }

}
