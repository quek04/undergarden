package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.block.entity.DepthrockPotBlockEntity;

public class DepthrockPotBlock extends BaseEntityBlock {

	private static final VoxelShape SHAPE = Shapes.or(
		Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
		Block.box(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D)
	);

	public DepthrockPotBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return null;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
		Containers.dropContentsOnDestroy(state, newState, level, pos);
		super.onRemove(state, level, pos, newState, movedByPiston);
	}

	@Override
	protected void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
		BlockPos blockpos = result.getBlockPos();
		if (!level.isClientSide() && projectile.mayInteract(level, blockpos) && projectile.mayBreak(level)) {
			level.destroyBlock(blockpos, true, projectile);
		}
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DepthrockPotBlockEntity(pos, state);
	}
}
