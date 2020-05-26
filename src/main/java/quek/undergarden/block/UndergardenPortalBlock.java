package quek.undergarden.block;

import com.google.common.cache.LoadingCache;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenSoundEvents;
import quek.undergarden.world.UndergardenTeleporter;

import javax.annotation.Nullable;
import java.util.Random;

public class UndergardenPortalBlock extends Block {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public UndergardenPortalBlock() {
        super(Properties.create(Material.PORTAL)
                .hardnessAndResistance(-1F)
                .doesNotBlockMovement()
                .lightValue(10)
                .noDrops()
        );
        setDefaultState(stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    public boolean trySpawnPortal(IWorld worldIn, BlockPos pos) {
        UndergardenPortalBlock.Size portalSize = isPortal(worldIn, pos);
        if (portalSize != null /*&& canMakePortal(worldIn.getWorld())*/) {
            portalSize.placePortalBlocks();
            return true;
        } else {
            return false;
        }
    }

    private boolean canMakePortal(World world) {
        return world.dimension.getType() == DimensionType.OVERWORLD || world.dimension.getType() == UndergardenMod.undergarden_dimension;
    }

    @Nullable
    public UndergardenPortalBlock.Size isPortal(IWorld worldIn, BlockPos pos) {
        UndergardenPortalBlock.Size portalSize = new UndergardenPortalBlock.Size(worldIn, pos, Direction.Axis.X);
        if (portalSize.isValid() && portalSize.portalBlockCount == 0) {
            return portalSize;
        } else {
            UndergardenPortalBlock.Size portalSize1 = new UndergardenPortalBlock.Size(worldIn, pos, Direction.Axis.Z);
            return portalSize1.isValid() && portalSize1.portalBlockCount == 0 ? portalSize1 : null;
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.get(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new UndergardenPortalBlock.Size(worldIn, currentPos, direction$axis1)).func_208508_f() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isBeingRidden() && entity.isNonBoss()) {
            if (entity.timeUntilPortal > 0) {
                entity.timeUntilPortal = entity.getPortalCooldown();
            } else {
                if (!entity.world.isRemote && !pos.equals(entity.lastPortalPos)) {
                    entity.lastPortalPos = new BlockPos(pos);
                    BlockPattern.PatternHelper helper = createPatternHelper(entity.world, entity.lastPortalPos);
                    double axis = helper.getForwards().getAxis() == Direction.Axis.X ? (double)helper.getFrontTopLeft().getZ() : (double)helper.getFrontTopLeft().getX();
                    double x = Math.abs(MathHelper.pct((helper.getForwards().getAxis() == Direction.Axis.X ? entity.getPosZ() : entity.getPosX()) - (double)(helper.getForwards().rotateY().getAxisDirection() == Direction.AxisDirection.NEGATIVE ? 1 : 0), axis, axis - (double)helper.getWidth()));
                    double y = MathHelper.pct(entity.getPosY() - 1.0D, helper.getFrontTopLeft().getY(), helper.getFrontTopLeft().getY() - helper.getHeight());
                    entity.lastPortalVec = new Vec3d(x, y, 0.0D);
                    entity.teleportDirection = helper.getForwards();
                }

                if (entity.world instanceof ServerWorld) {
                    if (entity.world.getServer().getAllowNether() && !entity.isPassenger()) {
                        entity.timeUntilPortal = entity.getPortalCooldown();
                        DimensionType type = worldIn.dimension.getType() == UndergardenMod.undergarden_dimension ? DimensionType.OVERWORLD : UndergardenMod.undergarden_dimension;
                        entity.changeDimension(type, new UndergardenTeleporter(worldIn.getServer().getWorld(UndergardenMod.undergarden_dimension)));
                    }
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, UndergardenSoundEvents.UNDERGARDEN_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + (double)rand.nextFloat();
            double d1 = (double)pos.getY() + (double)rand.nextFloat();
            double d2 = (double)pos.getZ() + (double)rand.nextFloat();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = rand.nextFloat() * 2.0F * (float)j;
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = rand.nextFloat() * 2.0F * (float)j;
            }

            worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, d3, d4, d5);
        }

    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch(state.get(AXIS)) {
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    public static BlockPattern.PatternHelper createPatternHelper(IWorld p_181089_0_, BlockPos worldIn) {
        Direction.Axis direction$axis = Direction.Axis.Z;
        UndergardenPortalBlock.Size portalSize = new UndergardenPortalBlock.Size(p_181089_0_, worldIn, Direction.Axis.X);
        LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(p_181089_0_, true);
        if (!portalSize.isValid()) {
            direction$axis = Direction.Axis.X;
            portalSize = new UndergardenPortalBlock.Size(p_181089_0_, worldIn, Direction.Axis.Z);
        }

        if (!portalSize.isValid()) {
            return new BlockPattern.PatternHelper(worldIn, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
        } else {
            int[] aint = new int[Direction.AxisDirection.values().length];
            Direction direction = portalSize.rightDir.rotateYCCW();
            BlockPos blockpos = portalSize.bottomLeft.up(portalSize.getHeight() - 1);

            for(Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values()) {
                BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);

                for(int i = 0; i < portalSize.getWidth(); ++i) {
                    for(int j = 0; j < portalSize.getHeight(); ++j) {
                        CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.translateOffset(i, j, 1);
                        if (!cachedblockinfo.getBlockState().isAir()) {
                            ++aint[direction$axisdirection.ordinal()];
                        }
                    }
                }
            }

            Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;

            for(Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
                if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
                    direction$axisdirection1 = direction$axisdirection2;
                }
            }

            return new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection1 ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection1, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);
        }
    }

    public static class Size {
        private final IWorld world;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private int portalBlockCount;
        @Nullable
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public Size(IWorld worldIn, BlockPos pos, Direction.Axis axisIn) {
            world = worldIn;
            axis = axisIn;
            if (axisIn == Direction.Axis.X) {
                leftDir = Direction.EAST;
                rightDir = Direction.WEST;
            } else {
                leftDir = Direction.NORTH;
                rightDir = Direction.SOUTH;
            }

            for(BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 3 && pos.getY() > 0 && func_196900_a(worldIn.getBlockState(pos.down())); pos = pos.down()) {
                ;
            }

            int i = getDistanceUntilEdge(pos, leftDir) - 1;
            if (i >= 0) {
                bottomLeft = pos.offset(leftDir, i);
                width = getDistanceUntilEdge(bottomLeft, rightDir);
                if (width != 3) {
                    bottomLeft = null;
                    width = 0;
                }
            }

            if (bottomLeft != null) {
                height = calculatePortalHeight();
            }

        }

        protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn) {
            int i;
            for(i = 0; i < 22; ++i) {
                BlockPos blockpos = pos.offset(directionIn, i);
                if (!func_196900_a(world.getBlockState(blockpos)) || world.getBlockState(blockpos.down()) != Blocks.STONE_BRICKS.getDefaultState()) {
                    break;
                }
            }

            Block block = world.getBlockState(pos.offset(directionIn, i)).getBlock();
            return block == Blocks.STONE_BRICKS ? i : 0;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        protected int calculatePortalHeight() {
            label56:
            for(height = 0; height < 3; ++height) {
                for(int i = 0; i < width; ++i) {
                    BlockPos blockpos = bottomLeft.offset(rightDir, i).up(height);
                    BlockState blockstate = world.getBlockState(blockpos);
                    if (!func_196900_a(blockstate)) {
                        break label56;
                    }

                    Block block = blockstate.getBlock();
                    if (block == UndergardenBlocks.undergarden_portal.get()) {
                        ++portalBlockCount;
                    }

                    if (i == 0) {
                        BlockPos framePos = blockpos.offset(leftDir);
                        if (!world.getBlockState(framePos).isPortalFrame(world, framePos)) {
                            break label56;
                        }
                    } else if (i == width - 1) {
                        BlockPos framePos = blockpos.offset(rightDir);
                        if (!world.getBlockState(framePos).isPortalFrame(world, framePos)) {
                            break label56;
                        }
                    }
                }
            }

            for(int j = 0; j < width; ++j) {
                BlockPos framePos = bottomLeft.offset(rightDir, j).up(height);
                if (!world.getBlockState(framePos).isPortalFrame(world, framePos)) {
                    height = 0;
                    break;
                }
            }

            if (height == 3) {
                return height;
            } else {
                bottomLeft = null;
                width = 0;
                height = 0;
                return 0;
            }
        }

        protected boolean func_196900_a(BlockState pos) {
            Block block = pos.getBlock();
            return pos.isAir() || block == UndergardenBlocks.undergarden_portal.get();
        }

        public boolean isValid() {
            return bottomLeft != null && width == 3 && height == 3;
        }

        public void placePortalBlocks() {
            for(int i = 0; i < width; ++i) {
                BlockPos blockpos = bottomLeft.offset(rightDir, i);

                for(int j = 0; j < height; ++j) {
                    world.setBlockState(blockpos.up(j), UndergardenBlocks.undergarden_portal.get().getDefaultState().with(UndergardenPortalBlock.AXIS, axis), 18);
                }
            }

        }

        private boolean func_196899_f() {
            return portalBlockCount >= width * height;
        }

        public boolean func_208508_f() {
            return isValid() && func_196899_f();
        }
    }
}
