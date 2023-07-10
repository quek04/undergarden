package quek.undergarden.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class MasticatedChestplateItem extends ArmorItem {

	public MasticatedChestplateItem(ArmorMaterial material) {
		super(material, Type.CHESTPLATE, new Properties()
				.rarity(Rarity.EPIC)
		);
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level level, Player playerIn) {
		stack.serializeNBT();
		stack.enchant(Enchantments.THORNS, 5);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
		return "undergarden:textures/armor/masticated_layer_1.png";
	}
}