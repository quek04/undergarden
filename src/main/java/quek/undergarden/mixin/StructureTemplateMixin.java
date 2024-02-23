package quek.undergarden.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quek.undergarden.registry.UGStructureProcessors;

@Mixin(StructureTemplate.class)
public class StructureTemplateMixin {

	@Inject(method = "placeInWorld", at = @At(value = "HEAD"))
	private void undergarden$removeWaterloggingInStructures(ServerLevelAccessor level, BlockPos pos, BlockPos p_230331_, StructurePlaceSettings settings, RandomSource random, int flags, CallbackInfoReturnable<Boolean> cir) {
		if (settings.getProcessors().stream().anyMatch(processor -> ((StructureProcessorAccessor)processor).callGetType() == UGStructureProcessors.NO_WATERLOGGING.get())) {
			settings.setKeepLiquids(false);
		}
	}
}