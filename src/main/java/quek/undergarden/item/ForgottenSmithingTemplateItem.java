package quek.undergarden.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import quek.undergarden.Undergarden;

import java.util.List;

public class ForgottenSmithingTemplateItem extends SmithingTemplateItem {

	private static final Component FORGOTTEN_UPGRADE_APPLIES_TO = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.applies_to").withStyle(ChatFormatting.BLUE);
	private static final Component FORGOTTEN_UPGRADE_INGREDIENTS = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.ingredients").withStyle(ChatFormatting.BLUE);
	private static final Component FORGOTTEN_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.base_slot_description");
	private static final Component FORGOTTEN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable("item.undergarden.smithing_template.forgotten_upgrade.additions_slot_description");

	private static final Identifier EMPTY_SLOT_HOE = Identifier.withDefaultNamespace("container/slot/hoe");
	private static final Identifier EMPTY_SLOT_AXE = Identifier.withDefaultNamespace("container/slot/axe");
	private static final Identifier EMPTY_SLOT_SWORD = Identifier.withDefaultNamespace("container/slot/sword");
	private static final Identifier EMPTY_SLOT_SHOVEL = Identifier.withDefaultNamespace("container/slot/shovel");
	private static final Identifier EMPTY_SLOT_PICKAXE = Identifier.withDefaultNamespace("container/slot/pickaxe");
	private static final Identifier EMPTY_SLOT_BATTLEAXE = Undergarden.prefix("container/slot/battleaxe");
	private static final Identifier EMPTY_SLOT_SPEAR = Identifier.withDefaultNamespace("container/slot/spear");
	private static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");

	public ForgottenSmithingTemplateItem(Item.Properties properties) {
		super(FORGOTTEN_UPGRADE_APPLIES_TO, FORGOTTEN_UPGRADE_INGREDIENTS, FORGOTTEN_UPGRADE_BASE_SLOT_DESCRIPTION, FORGOTTEN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createForgottenUpgradeIconList(), List.of(EMPTY_SLOT_INGOT), properties);
	}

	public static List<Identifier> createForgottenUpgradeIconList() {
		return List.of(EMPTY_SLOT_SWORD, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL, EMPTY_SLOT_BATTLEAXE, EMPTY_SLOT_SPEAR);
	}
}
