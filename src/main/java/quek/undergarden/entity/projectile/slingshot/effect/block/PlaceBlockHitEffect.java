package quek.undergarden.entity.projectile.slingshot.effect.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.registry.custom.UGHitEffects;

public record PlaceBlockHitEffect(BlockState stateToMimic) implements HitEffect {

	public static final MapCodec<PlaceBlockHitEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		BlockState.CODEC.fieldOf("mimicked").forGetter(PlaceBlockHitEffect::stateToMimic)
	).apply(instance, PlaceBlockHitEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, PlaceBlockHitEffect> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.fromCodec(BlockState.CODEC), PlaceBlockHitEffect::stateToMimic,
		PlaceBlockHitEffect::new
	);

	@Override
	public Type<? extends HitEffect> getType() {
		return UGHitEffects.PLACE_BLOCK.get();
	}

	@Override
	public boolean apply(ServerLevel level, ItemStack ammoStack, SlingshotProjectile projectile, HitResult result) {
		if (result instanceof BlockHitResult hitResult) {
			Direction direction = hitResult.getDirection();
			BlockPos placePos = hitResult.getBlockPos().relative(direction);
			if (ammoStack.getItem() instanceof BlockItem block && level.getBlockState(placePos).canBeReplaced()) {
				BlockState placeState = block.getBlock().withPropertiesOf(this.stateToMimic()).trySetValue(BlockStateProperties.FACING, direction);
				if (placeState.canSurvive(level, placePos)) {
					level.setBlock(placePos, placeState, 2);
					return true;
				}
			} else {
				projectile.spawnAtLocation(level, ammoStack);
			}
		}
		return false;
	}
}
