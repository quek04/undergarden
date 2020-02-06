package quek.undergarden.registry;

import net.minecraft.item.Food;

public class UndergardenFoods {

    public static final Food UNDERBEANS = (new Food.Builder().hunger(3).saturation(0.2F).fastToEat().build());
    public static final Food RAW_DWELLER = buildRawMeat();
    public static final Food COOKED_DWELLER = buildMeat();

    public static Food buildRawMeat() {
        return (new Food.Builder().hunger(3).saturation(0.3F).meat().build());
    }

    public static Food buildMeat() {
        return (new Food.Builder().hunger(8).saturation(0.8F).meat().build());
    }



}
