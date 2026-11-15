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
import snownee.jade.api.harvest.ToolTier;

import java.util.List;

@WailaPlugin
public class UndergardenJadeCompat implements IWailaPlugin {

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.addRayTraceCallback((hitResult, accessor, _) -> this.createOverrides(registration, hitResult, accessor));

		registration.addHarvestPlugin(registry -> {
			registry.type(JadeIds.JADE("pickaxe")).addTier(ToolTier.item(UGItems.FORGOTTEN_PICKAXE.get()));
			registry.type(JadeIds.JADE("axe")).addTier(ToolTier.item(UGItems.FORGOTTEN_AXE.get()));
			registry.type(JadeIds.JADE("shovel")).addTier(ToolTier.item(UGItems.FORGOTTEN_SHOVEL.get()));
			registry.type(JadeIds.JADE("hoe")).addTier(ToolTier.item(UGItems.FORGOTTEN_HOE.get()));
		});
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
