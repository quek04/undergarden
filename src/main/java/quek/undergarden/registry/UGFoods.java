package quek.undergarden.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class UGFoods {

    public static final FoodProperties UNDERBEANS = (new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).fast().build());
    public static final FoodProperties ROASTED_UNDERBEANS = (new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).build());
    public static final FoodProperties BLISTERBERRY = (new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).build());
    public static final FoodProperties GLOOMGOURD_PIE = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.3F).effect(() -> new MobEffectInstance(UGEffects.VIRULENT_RESISTANCE.get(), 600, 0, false, true), 1.0F).build();
    public static final FoodProperties RAW_DWELLER = (new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).meat().build());
    public static final FoodProperties COOKED_DWELLER = (new FoodProperties.Builder().nutrition(8).saturationMod(1.2F).meat().build());
    public static final FoodProperties RAW_GLOOMPER_LEG = (new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).meat().build());
    public static final FoodProperties GLOOMPER_LEG = (new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).meat().effect(() -> new MobEffectInstance(MobEffects.JUMP, 600, 0, false, true), 1.0F).build());
    public static final FoodProperties GOO_BALL = (new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().effect(() -> new MobEffectInstance(UGEffects.GOOEY.get(), 600, 0, false, true), 1.0F).build());
    public static final FoodProperties RAW_GWIBLING = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties COOKED_GWIBLING = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties DROOPFRUIT = (new FoodProperties.Builder()).nutrition(1).saturationMod(1).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 600, 0, false, true), 1.0F).build();
    public static final FoodProperties BLOODY = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0, false, true), 1.0F)
            .effect(() -> new MobEffectInstance(UGEffects.BRITTLENESS.get(), 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
    public static final FoodProperties INKY = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0, false, true), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
    public static final FoodProperties INDIGO = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0, false, true), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
    public static final FoodProperties VEILED = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 600, 0, false, true), 1.0F)
            .effect(() -> new MobEffectInstance(UGEffects.FEATHERWEIGHT.get(), 600, 0, false, true), 1.0F)
            .alwaysEat()
            .build();
}