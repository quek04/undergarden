package quek.undergarden.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFluids;

@Mixin(CauldronBlock.class)
public class CauldronBlockMixin {

	@Inject(method = "receiveStalactiteDrip", at = @At("HEAD"))
	public void undergarden$fillVirulentCauldron(BlockState state, Level level, BlockPos pos, Fluid fluid, CallbackInfo ci) {
		if (fluid == UGFluids.VIRULENT_MIX_SOURCE.get()) {
			BlockState cauldron = UGBlocks.VIRULENT_MIX_CAULDRON.get().defaultBlockState();
			level.setBlockAndUpdate(pos, cauldron);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(cauldron));
			level.playSound(null, pos, SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON, SoundSource.BLOCKS, 2.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
		}
	}
}
