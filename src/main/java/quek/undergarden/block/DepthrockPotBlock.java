package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import quek.undergarden.block.entity.DepthrockPotBlockEntity;

public class DepthrockPotBlock extends BaseEntityBlock {

	public static final MapCodec<DepthrockPotBlock> CODEC = simpleCodec(DepthrockPotBlock::new);
	private static final VoxelShape SHAPE = Shapes.or(
		Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
		Block.box(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D)
	);

	public DepthrockPotBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
		BlockPos blockpos = result.getBlockPos();
		if (level instanceof ServerLevel serverLevel && projectile.mayInteract(serverLevel, blockpos) && projectile.mayBreak(serverLevel)) {
			level.destroyBlock(blockpos, true, projectile);
		}
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DepthrockPotBlockEntity(pos, state);
	}
}
