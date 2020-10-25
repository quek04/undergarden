package quek.undergarden.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class UGFoods {

    public static final Food UNDERBEANS = (new Food.Builder().hunger(3).saturation(0.2F).fastToEat().build());
    public static final Food BLISTERBERRY = (new Food.Builder().hunger(1).saturation(3).fastToEat().build());
    public static final Food GLOOMGOURD_PIE = (new Food.Builder()).hunger(8).saturation(0.3F).build();
    public static final Food RAW_DWELLER = buildRawMeat();
    public static final Food COOKED_DWELLER = buildMeat();
    public static final Food GOO_BALL = (new Food.Builder().hunger(0).saturation(0).setAlwaysEdible().effect(() -> new EffectInstance(UGEffects.gooey.get(), 600, 0, false, true), 1.0F).build());
    public static final Food RAW_GWIBLING = (new Food.Builder()).hunger(2).saturation(0.1F).build();
    public static final Food COOKED_GWIBLING = (new Food.Builder()).hunger(5).saturation(0.6F).build();
    public static final Food DROOPVINE = (new Food.Builder()).hunger(1).saturation(1).effect(() -> new EffectInstance(Effects.GLOWING, 600, 0, false, true), 1.0F).build();

    private static Food buildRawMeat() {
        return (new Food.Builder().hunger(3).saturation(0.3F).meat().build());
    }

    private static Food buildMeat() {
        return (new Food.Builder().hunger(8).saturation(0.8F).meat().build());
    }

}
