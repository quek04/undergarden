package quek.undergarden.registry;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import quek.undergarden.Undergarden;

import java.util.Map;

public class UGTrimMaterials {
	public static final ResourceKey<TrimMaterial> CLOGGRUM = registerKey("cloggrum");
	public static final ResourceKey<TrimMaterial> FROSTSTEEL = registerKey("froststeel");
	public static final ResourceKey<TrimMaterial> UTHERIUM = registerKey("utherium");
	public static final ResourceKey<TrimMaterial> REGALIUM = registerKey("regalium");
	public static final ResourceKey<TrimMaterial> FORGOTTEN = registerKey("forgotten");

	private static ResourceKey<TrimMaterial> registerKey(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, new ResourceLocation(Undergarden.MODID, name));
	}

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, CLOGGRUM, UGItems.CLOGGRUM_INGOT, Style.EMPTY.withColor(9863528), 0.2F);
		register(context, FROSTSTEEL, UGItems.FROSTSTEEL_INGOT, Style.EMPTY.withColor(9484768), 0.9F);
		register(context, UTHERIUM, UGItems.UTHERIUM_CRYSTAL, Style.EMPTY.withColor(14440522), 0.4F);
		register(context, REGALIUM, UGItems.REGALIUM_CRYSTAL, Style.EMPTY.withColor(16570493), 0.6F);
		register(context, FORGOTTEN, UGItems.FORGOTTEN_INGOT, Style.EMPTY.withColor(4769934), 0.7F);
	}

	private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Holder<Item> trimItem, Style color, float itemModelIndex) {
		TrimMaterial material = new TrimMaterial(trimKey.location().getPath(), trimItem, itemModelIndex, Map.of(), Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(color));
		context.register(trimKey, material);
	}
}
