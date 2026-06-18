package quek.undergarden.compat.jade;

import com.google.common.collect.Lists;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.animal.MysteriousPot;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.api.*;

import java.util.List;

@WailaPlugin
public class UndergardenJadeCompat implements IWailaPlugin {

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.addRayTraceCallback((hitResult, accessor, _) -> this.createOverrides(registration, hitResult, accessor));

		HarvestToolProvider.registerHandler(() -> new ForgottenToolHandler(Undergarden.prefix("forgotten"), Lists.transform(List.of(UGItems.FORGOTTEN_PICKAXE.get(), UGItems.FORGOTTEN_AXE.get(), UGItems.FORGOTTEN_SHOVEL.get(), UGItems.FORGOTTEN_HOE.get()), Item::getDefaultInstance)));
	}

	private @Nullable Accessor<?> createOverrides(IWailaClientRegistration client, HitResult hitResult, @Nullable Accessor<?> accessor) {
		if (accessor instanceof EntityAccessor entity) {
			if (entity.getEntity() instanceof MysteriousPot pot && !pot.isActive()) {
				return client.blockAccessor().level(entity.getLevel()).hit(new BlockHitResult(hitResult.getLocation(), pot.getNearestViewDirection(), pot.blockPosition(), false)).blockState(UGBlocks.DEPTHROCK_POT.get().defaultBlockState()).build();
			}
		}
		return accessor;
	}
}
