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
import quek.undergarden.registry.UGBlocks;

import java.util.*;

public class UGTreeFeature extends Feature<BaseTreeFeatureConfig> {

    public UGTreeFeature(Codec<BaseTreeFeatureConfig> codec) {
        super(codec);
    }

    public static boolean isLog(IWorldGenerationBaseReader world, BlockPos pos) {
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

    private static boolean isDirtOrFarmland(IWorldGenerationBaseReader world, BlockPos pos) {
        return world.hasBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return isDirt(block) || block == Blocks.FARMLAND;
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

    private boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> leaves, Set<BlockPos> logs, MutableBoundingBox boundingBoxIn, BaseTreeFeatureConfig configIn) {
        int trunk = configIn.trunkPlacer.func_236917_a_(rand);
        int leaf = configIn.foliagePlacer.func_230374_a_(rand, trunk, configIn);
        int k = trunk - leaf;
        int l = configIn.foliagePlacer.func_230376_a_(rand, k);
        BlockPos blockpos;

        blockpos = positionIn;

        if (blockpos.getY() >= 1 && blockpos.getY() + trunk + 1 <= 256) {
            if (!isDirtOrFarmland(generationReader, blockpos.down()) || isWater(generationReader, positionIn)) {
                return false;
            }
            else {
                OptionalInt optionalint = configIn.minimumSize.func_236710_c_();
                int l1 = this.func_241521_a_(generationReader, trunk, blockpos, configIn);
                if (l1 >= trunk || optionalint.isPresent() && l1 >= optionalint.getAsInt()) {
                    List<FoliagePlacer.Foliage> list = configIn.trunkPlacer.func_230382_a_(generationReader, rand, l1, blockpos, leaves, boundingBoxIn, configIn);
                    list.forEach((foliage) -> configIn.foliagePlacer.func_236752_a_(generationReader, rand, configIn, l1, foliage, leaf, l, logs, boundingBoxIn));
                    return true;
                }
                else {
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
            int j = p_241521_4_.minimumSize.func_230369_a_(p_241521_2_, i);

            for(int k = -j; k <= j; ++k) {
                for(int l = -j; l <= j; ++l) {
                    blockpos$mutable.setAndOffset(p_241521_3_, k, i, l);
                    if (!isLog(p_241521_1_, blockpos$mutable) || !p_241521_4_.ignoreVines && isVine(p_241521_1_, blockpos$mutable)) {
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
    public final boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BaseTreeFeatureConfig config) {
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set1 = Sets.newHashSet();
        Set<BlockPos> set2 = Sets.newHashSet();
        MutableBoundingBox mutableboundingbox = MutableBoundingBox.getNewBoundingBox();
        boolean canPlace = this.place(reader, rand, pos, set, set1, mutableboundingbox, config);
        if (mutableboundingbox.minX <= mutableboundingbox.maxX && canPlace && !set.isEmpty()) {
            if (!config.decorators.isEmpty()) {
                List<BlockPos> list = Lists.newArrayList(set);
                List<BlockPos> list1 = Lists.newArrayList(set1);
                list.sort(Comparator.comparingInt(Vector3i::getY));
                list1.sort(Comparator.comparingInt(Vector3i::getY));
                config.decorators.forEach((treeDecorator) -> treeDecorator.func_225576_a_(reader, rand, list, list1, set2, mutableboundingbox));
            }

            VoxelShapePart voxelshapepart = this.placeLogsAndLeaves(reader, mutableboundingbox, set, set2);
            Template.func_222857_a(reader, 3, voxelshapepart, mutableboundingbox.minX, mutableboundingbox.minY, mutableboundingbox.minZ);
            return true;
        }
        else {
            return false;
        }
    }

    private VoxelShapePart placeLogsAndLeaves(IWorld world, MutableBoundingBox boundingBox, Set<BlockPos> p_236403_3_, Set<BlockPos> p_236403_4_) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        VoxelShapePart voxelshapepart = new BitSetVoxelShapePart(boundingBox.getXSize(), boundingBox.getYSize(), boundingBox.getZSize());

        for(int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(BlockPos blockpos : Lists.newArrayList(p_236403_4_)) {
            if (boundingBox.isVecInside(blockpos)) {
                voxelshapepart.setFilled(blockpos.getX() - boundingBox.minX, blockpos.getY() - boundingBox.minY, blockpos.getZ() - boundingBox.minZ, true, true);
            }
        }

        for(BlockPos blockpos1 : Lists.newArrayList(p_236403_3_)) {
            if (boundingBox.isVecInside(blockpos1)) {
                voxelshapepart.setFilled(blockpos1.getX() - boundingBox.minX, blockpos1.getY() - boundingBox.minY, blockpos1.getZ() - boundingBox.minZ, true, true);
            }

            for(Direction direction : Direction.values()) {
                blockpos$mutable.setAndMove(blockpos1, direction);
                if (!p_236403_3_.contains(blockpos$mutable)) {
                    BlockState blockstate = world.getBlockState(blockpos$mutable);
                    if (blockstate.hasProperty(BlockStateProperties.DISTANCE_1_7)) {
                        list.get(0).add(blockpos$mutable.toImmutable());
                        func_236408_b_(world, blockpos$mutable, blockstate.with(BlockStateProperties.DISTANCE_1_7, 1));
                        if (boundingBox.isVecInside(blockpos$mutable)) {
                            voxelshapepart.setFilled(blockpos$mutable.getX() - boundingBox.minX, blockpos$mutable.getY() - boundingBox.minY, blockpos$mutable.getZ() - boundingBox.minZ, true, true);
                        }
                    }
                }
            }
        }

        for(int l = 1; l < 6; ++l) {
            Set<BlockPos> set = list.get(l - 1);
            Set<BlockPos> set1 = list.get(l);

            for(BlockPos blockpos2 : set) {
                if (boundingBox.isVecInside(blockpos2)) {
                    voxelshapepart.setFilled(blockpos2.getX() - boundingBox.minX, blockpos2.getY() - boundingBox.minY, blockpos2.getZ() - boundingBox.minZ, true, true);
                }

                for(Direction direction1 : Direction.values()) {
                    blockpos$mutable.setAndMove(blockpos2, direction1);
                    if (!set.contains(blockpos$mutable) && !set1.contains(blockpos$mutable)) {
                        BlockState blockstate1 = world.getBlockState(blockpos$mutable);
                        if (blockstate1.hasProperty(BlockStateProperties.DISTANCE_1_7)) {
                            int k = blockstate1.get(BlockStateProperties.DISTANCE_1_7);
                            if (k > l + 1) {
                                BlockState blockstate2 = blockstate1.with(BlockStateProperties.DISTANCE_1_7, l + 1);
                                func_236408_b_(world, blockpos$mutable, blockstate2);
                                if (boundingBox.isVecInside(blockpos$mutable)) {
                                    voxelshapepart.setFilled(blockpos$mutable.getX() - boundingBox.minX, blockpos$mutable.getY() - boundingBox.minY, blockpos$mutable.getZ() - boundingBox.minZ, true, true);
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