package quek.undergarden.compat.jade;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGItems;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.addon.harvest.SimpleToolHandler;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

import java.util.List;

@WailaPlugin
public class UndergardenJadeCompat implements IWailaPlugin {

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		HarvestToolProvider.registerHandler(new ForgottenToolHandler(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "forgotten"), Lists.transform(List.of(UGItems.FORGOTTEN_PICKAXE.get(), UGItems.FORGOTTEN_AXE.get(), UGItems.FORGOTTEN_SHOVEL.get(), UGItems.FORGOTTEN_HOE.get()), Item::getDefaultInstance)));
	}
}
