package quek.undergarden.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;
import quek.undergarden.block.entity.DenizenTotemBlockEntity;
import quek.undergarden.registry.UGBlockEntities;

public class DenizenTotemBlock extends BaseEntityBlock implements EntityBlock {
	public static final MapCodec<DenizenTotemBlock> CODEC = simpleCodec(DenizenTotemBlock::new);
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public DenizenTotemBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(ACTIVE, false));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ACTIVE);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return UGBlockEntities.DENIZEN_TOTEM.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
		return createTickerHelper(blockEntity, UGBlockEntities.DENIZEN_TOTEM.get(), DenizenTotemBlockEntity::tick);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	/*@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		level.scheduleTick(pos, this, 20);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		List<LivingEntity> entityList = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(5.0D));
		if (!entityList.isEmpty()) {
			level.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
			entityList.forEach(entity -> {
				if (!entity.hasEffect(UGEffects.PURITY.get())) {
					entity.addEffect(new MobEffectInstance(UGEffects.PURITY.get(), 200, 0));
					drawParticlesTo(level, pos.getCenter(), entity);
				}

			});
		} else level.setBlockAndUpdate(pos, state.setValue(ACTIVE, false));

		level.scheduleTick(pos, this, 20);
	}*/
}