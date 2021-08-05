package quek.undergarden.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class UGFoods {

    public static final Food UNDERBEANS = (new Food.Builder().nutrition(3).saturationMod(0.2F).fast().build());
    public static final Food ROASTED_UNDERBEANS = (new Food.Builder().nutrition(6).saturationMod(0.4F).build());
    public static final Food BLISTERBERRY = (new Food.Builder().nutrition(6).saturationMod(0.4F).build());
    public static final Food GLOOMGOURD_PIE = (new Food.Builder()).nutrition(8).saturationMod(0.3F).effect(() -> new EffectInstance(UGEffects.VIRULENT_RESISTANCE.get(), 600, 0, false, true), 1.0F).build();
    public static final Food RAW_DWELLER = (new Food.Builder().nutrition(3).saturationMod(0.5F).meat().build());
    public static final Food COOKED_DWELLER = (new Food.Builder().nutrition(8).saturationMod(1.2F).meat().build());
    public static final Food RAW_GLOOMPER_LEG = (new Food.Builder().nutrition(2).saturationMod(0.5F).meat().build());
    public static final Food GLOOMPER_LEG = (new Food.Builder().nutrition(6).saturationMod(0.9F).meat().effect(() -> new EffectInstance(Effects.JUMP, 600, 0, false, true), 1.0F).build());
    public static final Food GOO_BALL = (new Food.Builder().nutrition(0).saturationMod(0).alwaysEat().effect(() -> new EffectInstance(UGEffects.GOOEY.get(), 600, 0, false, true), 1.0F).build());
    public static final Food RAW_GWIBLING = (new Food.Builder()).nutrition(2).saturationMod(0.1F).build();
    public static final Food COOKED_GWIBLING = (new Food.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final Food DROOPFRUIT = (new Food.Builder()).nutrition(1).saturationMod(1).effect(() -> new EffectInstance(Effects.GLOWING, 600, 0, false, true), 1.0F).build();
    public static final Food BLOODY = (new Food.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 600, 0, false, true), 1.0F)
            .effect(() -> new EffectInstance(UGEffects.BRITTLENESS.get(), 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
    public static final Food INKY = (new Food.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new EffectInstance(Effects.BLINDNESS, 600, 0, false, true), 1.0F)
            .effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
    public static final Food INDIGO = (new Food.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new EffectInstance(Effects.NIGHT_VISION, 600, 0, false, true), 1.0F)
            .effect(() -> new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
    public static final Food VEILED = (new Food.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new EffectInstance(Effects.SLOW_FALLING, 600, 0, false, true), 1.0F)
            .effect(() -> new EffectInstance(UGEffects.FEATHERWEIGHT.get(), 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
}