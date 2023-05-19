package quek.undergarden.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class UGItem extends Item {

	public UGItem() {
		super(new Properties()
		);
	}

	public UGItem(Rarity rarity) {
		super(new Properties()
				.rarity(rarity)
		);
	}

	public UGItem(FoodProperties food) {
		super(new Properties()
				.food(food));
	}
}