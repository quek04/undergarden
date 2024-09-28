package quek.undergarden.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import quek.undergarden.Undergarden;

public class UGEnchantments {
	public static final ResourceKey<Enchantment> RICOCHET = create("ricochet");
	public static final ResourceKey<Enchantment> LONGEVITY = create("longevity");
	public static final ResourceKey<Enchantment> SELF_SLING = create("self_sling");

	private static ResourceKey<Enchantment> create(String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, name));
	}

	public static void bootstrap(BootstrapContext<Enchantment> context) {
		HolderGetter<Item> items = context.lookup(Registries.ITEM);
		HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);

		context.register(RICOCHET, Enchantment.enchantment(
			Enchantment.definition(
				items.getOrThrow(UGTags.Items.SLINGSHOT_ENCHANTABLE),
				10,
				3,
				Enchantment.dynamicCost(1, 10),
				Enchantment.dynamicCost(20, 10),
				1,
				EquipmentSlotGroup.MAINHAND
			)
		).exclusiveWith(enchantments.getOrThrow(UGTags.Enchantments.SLINGSHOT_EXCLUSIVE)).build(RICOCHET.location()));

		context.register(LONGEVITY, Enchantment.enchantment(
			Enchantment.definition(
				items.getOrThrow(UGTags.Items.SLINGSHOT_ENCHANTABLE),
				10,
				3,
				Enchantment.dynamicCost(1, 10),
				Enchantment.dynamicCost(20, 10),
				1,
				EquipmentSlotGroup.MAINHAND
			)
		).build(LONGEVITY.location()));

		context.register(SELF_SLING, Enchantment.enchantment(
			Enchantment.definition(
				items.getOrThrow(UGTags.Items.SLINGSHOT_ENCHANTABLE),
				5,
				1,
				Enchantment.constantCost(20),
				Enchantment.constantCost(50),
				1,
				EquipmentSlotGroup.MAINHAND
			)
		).exclusiveWith(enchantments.getOrThrow(UGTags.Enchantments.SLINGSHOT_EXCLUSIVE)).build(SELF_SLING.location()));
	}
}