package quek.undergarden.registry.custom;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.UGRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.projectile.slingshot.effect.ExplodeHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.block.PlaceBlockHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.AddMobEffectHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.EquipItemHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.HealHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.VelocityBasedDamageHitEffect;

public class UGHitEffects {

	public static final DeferredRegister<HitEffect.Type<?>> HIT_EFFECTS = DeferredRegister.create(UGRegistries.HIT_EFFECT_TYPE, Undergarden.MODID);

	public static final DeferredHolder<HitEffect.Type<?>, HitEffect.Type<ExplodeHitEffect>> EXPLODE = HIT_EFFECTS.register("explode", () -> new HitEffect.Type<>(ExplodeHitEffect.CODEC, ExplodeHitEffect.STREAM_CODEC));
	public static final DeferredHolder<HitEffect.Type<?>, HitEffect.Type<PlaceBlockHitEffect>> PLACE_BLOCK = HIT_EFFECTS.register("place_block", () -> new HitEffect.Type<>(PlaceBlockHitEffect.CODEC, PlaceBlockHitEffect.STREAM_CODEC));
	public static final DeferredHolder<HitEffect.Type<?>, HitEffect.Type<AddMobEffectHitEffect>> ADD_EFFECT = HIT_EFFECTS.register("add_effect", () -> new HitEffect.Type<>(AddMobEffectHitEffect.CODEC, AddMobEffectHitEffect.STREAM_CODEC));
	public static final DeferredHolder<HitEffect.Type<?>, HitEffect.Type<EquipItemHitEffect>> EQUIP_ITEM = HIT_EFFECTS.register("equip_item", () -> new HitEffect.Type<>(EquipItemHitEffect.CODEC, EquipItemHitEffect.STREAM_CODEC));
	public static final DeferredHolder<HitEffect.Type<?>, HitEffect.Type<HealHitEffect>> HEAL = HIT_EFFECTS.register("heal", () -> new HitEffect.Type<>(HealHitEffect.CODEC, HealHitEffect.STREAM_CODEC));
	public static final DeferredHolder<HitEffect.Type<?>, HitEffect.Type<VelocityBasedDamageHitEffect>> VELOCITY_BASED_DAMAGE = HIT_EFFECTS.register("velocity_based_damage", () -> new HitEffect.Type<>(VelocityBasedDamageHitEffect.CODEC, VelocityBasedDamageHitEffect.STREAM_CODEC));
}
