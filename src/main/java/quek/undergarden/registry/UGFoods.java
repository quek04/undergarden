package quek.undergarden.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class UGFoods {

	public static final FoodProperties UNDERBEANS = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).fast().build();
	public static final FoodProperties ROASTED_UNDERBEANS = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4F).build();
	public static final FoodProperties BLISTERBERRY = new FoodProperties.Builder().nutrition(6).saturationModifier(0.2F).build();
	public static final FoodProperties ROTTEN_BLISTERBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 1.0F).build();
	public static final FoodProperties GLOOMGOURD_PIE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5F).effect(() -> new MobEffectInstance(UGEffects.VIRULENT_RESISTANCE, 600, 0, false, true), 1.0F).build();
	public static final FoodProperties RAW_DWELLER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5F).build();
	public static final FoodProperties COOKED_DWELLER = new FoodProperties.Builder().nutrition(8).saturationModifier(1.2F).build();
	public static final FoodProperties RAW_GLOOMPER_LEG = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5F).build();
	public static final FoodProperties GLOOMPER_LEG = new FoodProperties.Builder().nutrition(6).saturationModifier(0.9F).effect(() -> new MobEffectInstance(MobEffects.JUMP, 600, 0, false, true), 1.0F).build();
	public static final FoodProperties GOO_BALL = new FoodProperties.Builder().nutrition(0).saturationModifier(0).alwaysEdible().effect(() -> new MobEffectInstance(UGEffects.GOOEY, 600, 0, false, true), 1.0F).build();
	public static final FoodProperties RAW_GWIBLING = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
	public static final FoodProperties COOKED_GWIBLING = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
	public static final FoodProperties DROOPFRUIT = new FoodProperties.Builder().nutrition(1).saturationModifier(1).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 600, 0, false, true), 1.0F).build();
	public static final FoodProperties BLOOD_GLOBULE = new FoodProperties.Builder().nutrition(0).saturationModifier(0.0F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 20, 3, false, true), 1.0F).alwaysEdible().build();
	public static final FoodProperties BLOODY_STEW = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0, false, true), 1.0F).effect(() -> new MobEffectInstance(UGEffects.BRITTLENESS, 600, 0, false, true), 1.0F).alwaysEdible().usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties INKY_STEW = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0, false, true), 1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0, false, true), 1.0F).alwaysEdible().usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties INDIGO_STEW = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0, false, true), 1.0F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0, false, true), 1.0F).alwaysEdible().usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties VEILED_STEW = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 600, 0, false, true), 1.0F).effect(() -> new MobEffectInstance(UGEffects.FEATHERWEIGHT, 600, 0, false, true), 1.0F).alwaysEdible().usingConvertsTo(Items.BOWL).build();
	public static final FoodProperties SLOP_BOWL = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0, false, true), 1.0F).usingConvertsTo(Items.BOWL).build();
}