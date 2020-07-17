package quek.undergarden.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import java.util.Map;

public class UndergardenWallBlock extends WallBlock {
    public static final BooleanProperty UP;
    public static final EnumProperty<WallHeight> field_235612_b_;
    public static final EnumProperty<WallHeight> field_235613_c_;
    public static final EnumProperty<WallHeight> field_235614_d_;
    public static final EnumProperty<WallHeight> field_235615_e_;
    public static final BooleanProperty field_235616_f_;
    private final Map<BlockState, VoxelShape> field_235617_g_;
    private final Map<BlockState, VoxelShape> field_235618_h_;
    private static final VoxelShape field_235619_i_;
    private static final VoxelShape field_235620_j_;
    private static final VoxelShape field_235621_k_;
    private static final VoxelShape field_235622_o_;
    private static final VoxelShape field_235623_p_;

    public UndergardenWallBlock(Material material, float hardness, float resistance, SoundType sound, int level, ToolType tool) {
        super(Properties.create(material)
                .hardnessAndResistance(hardness, resistance)
                .sound(sound)
                .harvestLevel(level)
                .harvestTool(tool)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(UP, true).with(field_235613_c_, WallHeight.NONE).with(field_235612_b_, WallHeight.NONE).with(field_235614_d_, WallHeight.NONE).with(field_235615_e_, WallHeight.NONE).with(field_235616_f_, false));
        this.field_235617_g_ = this.func_235624_a_(4.0F, 3.0F, 16.0F, 0.0F, 14.0F, 16.0F);
        this.field_235618_h_ = this.func_235624_a_(4.0F, 3.0F, 24.0F, 0.0F, 24.0F, 24.0F);
    }

    private static VoxelShape func_235631_a_(VoxelShape p_235631_0_, WallHeight p_235631_1_, VoxelShape p_235631_2_, VoxelShape p_235631_3_) {
        if (p_235631_1_ == WallHeight.TALL) {
            return VoxelShapes.or(p_235631_0_, p_235631_3_);
        } else {
            return p_235631_1_ == WallHeight.LOW ? VoxelShapes.or(p_235631_0_, p_235631_2_) : p_235631_0_;
        }
    }

    private Map<BlockState, VoxelShape> func_235624_a_(float p_235624_1_, float p_235624_2_, float p_235624_3_, float p_235624_4_, float p_235624_5_, float p_235624_6_) {
        float lvt_7_1_ = 8.0F - p_235624_1_;
        float lvt_8_1_ = 8.0F + p_235624_1_;
        float lvt_9_1_ = 8.0F - p_235624_2_;
        float lvt_10_1_ = 8.0F + p_235624_2_;
        VoxelShape lvt_11_1_ = Block.makeCuboidShape(lvt_7_1_, 0.0D, lvt_7_1_, lvt_8_1_, p_235624_3_, lvt_8_1_);
        VoxelShape lvt_12_1_ = Block.makeCuboidShape(lvt_9_1_, p_235624_4_, 0.0D, lvt_10_1_, p_235624_5_, lvt_10_1_);
        VoxelShape lvt_13_1_ = Block.makeCuboidShape(lvt_9_1_, p_235624_4_, lvt_9_1_, lvt_10_1_, p_235624_5_, 16.0D);
        VoxelShape lvt_14_1_ = Block.makeCuboidShape(0.0D, p_235624_4_, lvt_9_1_, lvt_10_1_, p_235624_5_, lvt_10_1_);
        VoxelShape lvt_15_1_ = Block.makeCuboidShape(lvt_9_1_, p_235624_4_, lvt_9_1_, 16.0D, p_235624_5_, lvt_10_1_);
        VoxelShape lvt_16_1_ = Block.makeCuboidShape(lvt_9_1_, p_235624_4_, 0.0D, lvt_10_1_, p_235624_6_, lvt_10_1_);
        VoxelShape lvt_17_1_ = Block.makeCuboidShape(lvt_9_1_, p_235624_4_, lvt_9_1_, lvt_10_1_, p_235624_6_, 16.0D);
        VoxelShape lvt_18_1_ = Block.makeCuboidShape(0.0D, p_235624_4_, lvt_9_1_, lvt_10_1_, p_235624_6_, lvt_10_1_);
        VoxelShape lvt_19_1_ = Block.makeCuboidShape(lvt_9_1_, p_235624_4_, lvt_9_1_, 16.0D, p_235624_6_, lvt_10_1_);
        ImmutableMap.Builder<BlockState, VoxelShape> lvt_20_1_ = ImmutableMap.builder();

        for (Boolean lvt_22_1_ : UP.getAllowedValues()) {

            for (WallHeight lvt_24_1_ : field_235612_b_.getAllowedValues()) {

                for (WallHeight lvt_26_1_ : field_235613_c_.getAllowedValues()) {

                    for (WallHeight lvt_28_1_ : field_235615_e_.getAllowedValues()) {

                        for (WallHeight lvt_30_1_ : field_235614_d_.getAllowedValues()) {
                            VoxelShape lvt_31_1_ = VoxelShapes.empty();
                            lvt_31_1_ = func_235631_a_(lvt_31_1_, lvt_24_1_, lvt_15_1_, lvt_19_1_);
                            lvt_31_1_ = func_235631_a_(lvt_31_1_, lvt_28_1_, lvt_14_1_, lvt_18_1_);
                            lvt_31_1_ = func_235631_a_(lvt_31_1_, lvt_26_1_, lvt_12_1_, lvt_16_1_);
                            lvt_31_1_ = func_235631_a_(lvt_31_1_, lvt_30_1_, lvt_13_1_, lvt_17_1_);
                            if (lvt_22_1_) {
                                lvt_31_1_ = VoxelShapes.or(lvt_31_1_, lvt_11_1_);
                            }

                            BlockState lvt_32_1_ =  ( ( ( ( this.getDefaultState().with(UP, lvt_22_1_)).with(field_235612_b_, lvt_24_1_)).with(field_235615_e_, lvt_28_1_)).with(field_235613_c_, lvt_26_1_)).with(field_235614_d_, lvt_30_1_);
                            lvt_20_1_.put(lvt_32_1_.with(field_235616_f_, false), lvt_31_1_);
                            lvt_20_1_.put(lvt_32_1_.with(field_235616_f_, true), lvt_31_1_);
                        }
                    }
                }
            }
        }

        return lvt_20_1_.build();
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return (VoxelShape)this.field_235617_g_.get(p_220053_1_);
    }

    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return (VoxelShape)this.field_235618_h_.get(p_220071_1_);
    }

    public boolean allowsMovement(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }

    private boolean func_220113_a(BlockState p_220113_1_, boolean p_220113_2_, Direction p_220113_3_) {
        Block lvt_4_1_ = p_220113_1_.getBlock();
        boolean lvt_5_1_ = lvt_4_1_ instanceof FenceGateBlock && FenceGateBlock.isParallel(p_220113_1_, p_220113_3_);
        return p_220113_1_.func_235714_a_(BlockTags.WALLS) || !cannotAttach(lvt_4_1_) && p_220113_2_ || lvt_4_1_ instanceof PaneBlock || lvt_5_1_;
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        IWorldReader lvt_2_1_ = p_196258_1_.getWorld();
        BlockPos lvt_3_1_ = p_196258_1_.getPos();
        FluidState lvt_4_1_ = p_196258_1_.getWorld().getFluidState(p_196258_1_.getPos());
        BlockPos lvt_5_1_ = lvt_3_1_.north();
        BlockPos lvt_6_1_ = lvt_3_1_.east();
        BlockPos lvt_7_1_ = lvt_3_1_.south();
        BlockPos lvt_8_1_ = lvt_3_1_.west();
        BlockPos lvt_9_1_ = lvt_3_1_.up();
        BlockState lvt_10_1_ = lvt_2_1_.getBlockState(lvt_5_1_);
        BlockState lvt_11_1_ = lvt_2_1_.getBlockState(lvt_6_1_);
        BlockState lvt_12_1_ = lvt_2_1_.getBlockState(lvt_7_1_);
        BlockState lvt_13_1_ = lvt_2_1_.getBlockState(lvt_8_1_);
        BlockState lvt_14_1_ = lvt_2_1_.getBlockState(lvt_9_1_);
        boolean lvt_15_1_ = this.func_220113_a(lvt_10_1_, lvt_10_1_.isSolidSide(lvt_2_1_, lvt_5_1_, Direction.SOUTH), Direction.SOUTH);
        boolean lvt_16_1_ = this.func_220113_a(lvt_11_1_, lvt_11_1_.isSolidSide(lvt_2_1_, lvt_6_1_, Direction.WEST), Direction.WEST);
        boolean lvt_17_1_ = this.func_220113_a(lvt_12_1_, lvt_12_1_.isSolidSide(lvt_2_1_, lvt_7_1_, Direction.NORTH), Direction.NORTH);
        boolean lvt_18_1_ = this.func_220113_a(lvt_13_1_, lvt_13_1_.isSolidSide(lvt_2_1_, lvt_8_1_, Direction.EAST), Direction.EAST);
        BlockState lvt_19_1_ = this.getDefaultState().with(field_235616_f_, lvt_4_1_.getFluid() == Fluids.WATER);
        return this.func_235626_a_(lvt_2_1_, lvt_19_1_, lvt_9_1_, lvt_14_1_, lvt_15_1_, lvt_16_1_, lvt_17_1_, lvt_18_1_);
    }

    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (p_196271_1_.get(field_235616_f_)) {
            p_196271_4_.getPendingFluidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickRate(p_196271_4_));
        }

        if (p_196271_2_ == Direction.DOWN) {
            return super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
        } else {
            return p_196271_2_ == Direction.UP ? this.func_235625_a_(p_196271_4_, p_196271_1_, p_196271_6_, p_196271_3_) : this.func_235627_a_(p_196271_4_, p_196271_5_, p_196271_1_, p_196271_6_, p_196271_3_, p_196271_2_);
        }
    }

    private static boolean func_235629_a_(BlockState p_235629_0_, Property<WallHeight> p_235629_1_) {
        return p_235629_0_.get(p_235629_1_) != WallHeight.NONE;
    }

    private static boolean func_235632_a_(VoxelShape p_235632_0_, VoxelShape p_235632_1_) {
        return !VoxelShapes.compare(p_235632_1_, p_235632_0_, IBooleanFunction.ONLY_FIRST);
    }

    private BlockState func_235625_a_(IWorldReader p_235625_1_, BlockState p_235625_2_, BlockPos p_235625_3_, BlockState p_235625_4_) {
        boolean lvt_5_1_ = func_235629_a_(p_235625_2_, field_235613_c_);
        boolean lvt_6_1_ = func_235629_a_(p_235625_2_, field_235612_b_);
        boolean lvt_7_1_ = func_235629_a_(p_235625_2_, field_235614_d_);
        boolean lvt_8_1_ = func_235629_a_(p_235625_2_, field_235615_e_);
        return this.func_235626_a_(p_235625_1_, p_235625_2_, p_235625_3_, p_235625_4_, lvt_5_1_, lvt_6_1_, lvt_7_1_, lvt_8_1_);
    }

    private BlockState func_235627_a_(IWorldReader p_235627_1_, BlockPos p_235627_2_, BlockState p_235627_3_, BlockPos p_235627_4_, BlockState p_235627_5_, Direction p_235627_6_) {
        Direction lvt_7_1_ = p_235627_6_.getOpposite();
        boolean lvt_8_1_ = p_235627_6_ == Direction.NORTH ? this.func_220113_a(p_235627_5_, p_235627_5_.isSolidSide(p_235627_1_, p_235627_4_, lvt_7_1_), lvt_7_1_) : func_235629_a_(p_235627_3_, field_235613_c_);
        boolean lvt_9_1_ = p_235627_6_ == Direction.EAST ? this.func_220113_a(p_235627_5_, p_235627_5_.isSolidSide(p_235627_1_, p_235627_4_, lvt_7_1_), lvt_7_1_) : func_235629_a_(p_235627_3_, field_235612_b_);
        boolean lvt_10_1_ = p_235627_6_ == Direction.SOUTH ? this.func_220113_a(p_235627_5_, p_235627_5_.isSolidSide(p_235627_1_, p_235627_4_, lvt_7_1_), lvt_7_1_) : func_235629_a_(p_235627_3_, field_235614_d_);
        boolean lvt_11_1_ = p_235627_6_ == Direction.WEST ? this.func_220113_a(p_235627_5_, p_235627_5_.isSolidSide(p_235627_1_, p_235627_4_, lvt_7_1_), lvt_7_1_) : func_235629_a_(p_235627_3_, field_235615_e_);
        BlockPos lvt_12_1_ = p_235627_2_.up();
        BlockState lvt_13_1_ = p_235627_1_.getBlockState(lvt_12_1_);
        return this.func_235626_a_(p_235627_1_, p_235627_3_, lvt_12_1_, lvt_13_1_, lvt_8_1_, lvt_9_1_, lvt_10_1_, lvt_11_1_);
    }

    private BlockState func_235626_a_(IWorldReader p_235626_1_, BlockState p_235626_2_, BlockPos p_235626_3_, BlockState p_235626_4_, boolean p_235626_5_, boolean p_235626_6_, boolean p_235626_7_, boolean p_235626_8_) {
        VoxelShape lvt_9_1_ = p_235626_4_.getCollisionShape(p_235626_1_, p_235626_3_).project(Direction.DOWN);
        BlockState lvt_10_1_ = this.func_235630_a_(p_235626_2_, p_235626_5_, p_235626_6_, p_235626_7_, p_235626_8_, lvt_9_1_);
        return lvt_10_1_.with(UP, this.func_235628_a_(lvt_10_1_, p_235626_4_, lvt_9_1_));
    }

    private boolean func_235628_a_(BlockState p_235628_1_, BlockState p_235628_2_, VoxelShape p_235628_3_) {
        boolean lvt_4_1_ = p_235628_2_.getBlock() instanceof WallBlock && p_235628_2_.get(UP);
        if (lvt_4_1_) {
            return true;
        } else {
            WallHeight lvt_5_1_ = p_235628_1_.get(field_235613_c_);
            WallHeight lvt_6_1_ = p_235628_1_.get(field_235614_d_);
            WallHeight lvt_7_1_ = p_235628_1_.get(field_235612_b_);
            WallHeight lvt_8_1_ = p_235628_1_.get(field_235615_e_);
            boolean lvt_9_1_ = lvt_6_1_ == WallHeight.NONE;
            boolean lvt_10_1_ = lvt_8_1_ == WallHeight.NONE;
            boolean lvt_11_1_ = lvt_7_1_ == WallHeight.NONE;
            boolean lvt_12_1_ = lvt_5_1_ == WallHeight.NONE;
            boolean lvt_13_1_ = lvt_12_1_ && lvt_9_1_ && lvt_10_1_ && lvt_11_1_ || lvt_12_1_ != lvt_9_1_ || lvt_10_1_ != lvt_11_1_;
            if (lvt_13_1_) {
                return true;
            } else {
                boolean lvt_14_1_ = lvt_5_1_ == WallHeight.TALL && lvt_6_1_ == WallHeight.TALL || lvt_7_1_ == WallHeight.TALL && lvt_8_1_ == WallHeight.TALL;
                if (lvt_14_1_) {
                    return false;
                } else {
                    return p_235628_2_.getBlock().isIn(BlockTags.field_232877_ar_) || func_235632_a_(p_235628_3_, field_235619_i_);
                }
            }
        }
    }

    private BlockState func_235630_a_(BlockState p_235630_1_, boolean p_235630_2_, boolean p_235630_3_, boolean p_235630_4_, boolean p_235630_5_, VoxelShape p_235630_6_) {
        return (((p_235630_1_.with(field_235613_c_, this.func_235633_a_(p_235630_2_, p_235630_6_, field_235620_j_))).with(field_235612_b_, this.func_235633_a_(p_235630_3_, p_235630_6_, field_235623_p_))).with(field_235614_d_, this.func_235633_a_(p_235630_4_, p_235630_6_, field_235621_k_))).with(field_235615_e_, this.func_235633_a_(p_235630_5_, p_235630_6_, field_235622_o_));
    }

    private WallHeight func_235633_a_(boolean p_235633_1_, VoxelShape p_235633_2_, VoxelShape p_235633_3_) {
        if (p_235633_1_) {
            return func_235632_a_(p_235633_2_, p_235633_3_) ? WallHeight.TALL : WallHeight.LOW;
        } else {
            return WallHeight.NONE;
        }
    }

    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.get(field_235616_f_) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    public boolean propagatesSkylightDown(BlockState p_200123_1_, IBlockReader p_200123_2_, BlockPos p_200123_3_) {
        return !p_200123_1_.get(field_235616_f_);
    }

    protected void fillStateContainer(net.minecraft.state.StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(UP, field_235613_c_, field_235612_b_, field_235615_e_, field_235614_d_, field_235616_f_);
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        switch(p_185499_2_) {
            case CLOCKWISE_180:
                return (((p_185499_1_.with(field_235613_c_, p_185499_1_.get(field_235614_d_))).with(field_235612_b_, p_185499_1_.get(field_235615_e_))).with(field_235614_d_, p_185499_1_.get(field_235613_c_))).with(field_235615_e_, p_185499_1_.get(field_235612_b_));
            case COUNTERCLOCKWISE_90:
                return (((p_185499_1_.with(field_235613_c_, p_185499_1_.get(field_235612_b_))).with(field_235612_b_, p_185499_1_.get(field_235614_d_))).with(field_235614_d_, p_185499_1_.get(field_235615_e_))).with(field_235615_e_, p_185499_1_.get(field_235613_c_));
            case CLOCKWISE_90:
                return (((p_185499_1_.with(field_235613_c_, p_185499_1_.get(field_235615_e_))).with(field_235612_b_, p_185499_1_.get(field_235613_c_))).with(field_235614_d_, p_185499_1_.get(field_235612_b_))).with(field_235615_e_, p_185499_1_.get(field_235614_d_));
            default:
                return p_185499_1_;
        }
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        switch(p_185471_2_) {
            case LEFT_RIGHT:
                return (p_185471_1_.with(field_235613_c_, p_185471_1_.get(field_235614_d_))).with(field_235614_d_, p_185471_1_.get(field_235613_c_));
            case FRONT_BACK:
                return (p_185471_1_.with(field_235612_b_, p_185471_1_.get(field_235615_e_))).with(field_235615_e_, p_185471_1_.get(field_235612_b_));
            default:
                return super.mirror(p_185471_1_, p_185471_2_);
        }
    }

    static {
        UP = BlockStateProperties.UP;
        field_235612_b_ = BlockStateProperties.field_235908_S_;
        field_235613_c_ = BlockStateProperties.field_235909_T_;
        field_235614_d_ = BlockStateProperties.field_235910_U_;
        field_235615_e_ = BlockStateProperties.field_235911_V_;
        field_235616_f_ = BlockStateProperties.WATERLOGGED;
        field_235619_i_ = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
        field_235620_j_ = Block.makeCuboidShape(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
        field_235621_k_ = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
        field_235622_o_ = Block.makeCuboidShape(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
        field_235623_p_ = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    }
}
