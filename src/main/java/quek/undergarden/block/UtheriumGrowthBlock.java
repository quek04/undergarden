package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGAttachments;
import quek.undergarden.registry.UGTags;

public class UtheriumGrowthBlock extends Block {
	public UtheriumGrowthBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		level.scheduleTick(pos, this, 100);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(5.0F),
			livingEntity -> !livingEntity.getType().is(UGTags.Entities.ROTSPAWN)).forEach(livingEntity -> {
			int infection = livingEntity.getData(UGAttachments.UTHERIC_INFECTION);
			if (infection < 20) {
				livingEntity.setData(UGAttachments.UTHERIC_INFECTION.get(), infection + 1);
			}
		});
		level.scheduleTick(pos, this, 100);
	}
}