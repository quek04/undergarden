package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.effect.*;

public class UGEffects {

	public static final ResourceLocation CHILLY_MODIFIER = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "effect.chilly_slowness");

	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Undergarden.MODID);

	public static final DeferredHolder<MobEffect, MobEffect> GOOEY = EFFECTS.register("gooey", () -> new GooeyEffect(MobEffectCategory.HARMFUL, 7827026).withSoundOnAdded(SoundEvents.SLIME_SQUISH));
	public static final DeferredHolder<MobEffect, MobEffect> BRITTLENESS = EFFECTS.register("brittleness", () -> new GenericMobEffect(MobEffectCategory.HARMFUL, 9843250));
	public static final DeferredHolder<MobEffect, MobEffect> FEATHERWEIGHT = EFFECTS.register("featherweight", () -> new GenericMobEffect(MobEffectCategory.HARMFUL, 13158655));
	public static final DeferredHolder<MobEffect, MobEffect> VIRULENT_RESISTANCE = EFFECTS.register("virulent_resistance", () -> new GenericMobEffect(MobEffectCategory.BENEFICIAL, 3550530));
	public static final DeferredHolder<MobEffect, MobEffect> VIRULENCE = EFFECTS.register("virulence", () -> new VirulenceEffect(MobEffectCategory.HARMFUL, 3550530));
	public static final DeferredHolder<MobEffect, MobEffect> CHILLY = EFFECTS.register("chilly", () -> new GenericMobEffect(MobEffectCategory.HARMFUL, 9154528).addAttributeModifier(Attributes.MOVEMENT_SPEED, CHILLY_MODIFIER, -0.15D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
	public static final DeferredHolder<MobEffect, MobEffect> PURITY = EFFECTS.register("purity", PurityEffect::new);
}