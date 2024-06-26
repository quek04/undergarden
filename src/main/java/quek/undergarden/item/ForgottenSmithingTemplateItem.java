package quek.undergarden.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import quek.undergarden.Undergarden;

import java.util.List;

public class ForgottenSmithingTemplateItem extends SmithingTemplateItem {

	private static final Component FORGOTTEN_UPGRADE = Component.translatable("upgrade.undergarden.forgotten_upgrade").withStyle(ChatFormatting.GRAY);
	private static final Component FORGOTTEN_UPGRADE_APPLIES_TO = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.applies_to").withStyle(ChatFormatting.BLUE);
	private static final Component FORGOTTEN_UPGRADE_INGREDIENTS = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.ingredients").withStyle(ChatFormatting.BLUE);
	private static final Component FORGOTTEN_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.base_slot_description");
	private static final Component FORGOTTEN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.additions_slot_description");

	private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
	private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
	private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
	private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
	private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
	private static final ResourceLocation EMPTY_SLOT_BATTLEAXE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "item/empty_slot_battleaxe");
	private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

	public ForgottenSmithingTemplateItem() {
		super(FORGOTTEN_UPGRADE_APPLIES_TO, FORGOTTEN_UPGRADE_INGREDIENTS, FORGOTTEN_UPGRADE, FORGOTTEN_UPGRADE_BASE_SLOT_DESCRIPTION, FORGOTTEN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createForgottenUpgradeIconList(), List.of(EMPTY_SLOT_INGOT));
	}

	public static List<ResourceLocation> createForgottenUpgradeIconList() {
		return List.of(EMPTY_SLOT_SWORD, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL, EMPTY_SLOT_BATTLEAXE);
	}
}
