package quek.undergarden.registry.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import quek.undergarden.Undergarden;
import quek.undergarden.component.SlingshotAmmo;
import quek.undergarden.entity.projectile.slingshot.effect.ExplodeHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.block.PlaceBlockHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.AddMobEffectHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.EquipItemHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.HealHitEffect;
import quek.undergarden.entity.projectile.slingshot.effect.entity.VelocityBasedDamageHitEffect;
import quek.undergarden.registry.*;

import java.util.function.Function;

public class UGSlingshotAmmoSettings {

	public static final SlingshotAmmo BLISTERBERRY = SlingshotAmmo.builder()
		.setSlingshotChargeTextures(Undergarden.prefix("item/slingshot_pulling_blisterberry"))
		.addHitEffect(new ExplodeHitEffect(1.5F, false, Level.ExplosionInteraction.NONE))
		.build();

	public static final SlingshotAmmo DEPTHROCK_PEBBLE = SlingshotAmmo.builder()
		.setBreakParticleCount(ConstantInt.of(8))
		.setHitSound(SoundEvents.STONE_BREAK)
		.addHitEffect(new VelocityBasedDamageHitEffect())
		.build();

	public static final SlingshotAmmo GOO_BALL = SlingshotAmmo.builder()
		.setSlingshotChargeTextures(Undergarden.prefix("item/slingshot_pulling_gooball"))
		.setBreakParticleCount(ConstantInt.of(8))
		.setHitSound(SoundEvents.SLIME_BLOCK_BREAK)
		.addHitEffect(new PlaceBlockHitEffect(UGBlocks.GOO.get().defaultBlockState()))
		.addHitEffect(new AddMobEffectHitEffect(new MobEffectInstance(UGEffects.GOOEY, 100), UGTags.Entities.IMMUNE_TO_GOOEY_EFFECT))
		.addHitEffect(new HealHitEffect(2.0F, UGEntityTypes.SCINTLING))
		.build();

	public static final Function<String, SlingshotAmmo> GRONGLET = pref -> SlingshotAmmo.builder()
		.setSlingshotChargeTextures(Undergarden.prefix("item/slingshot_pulling_" + pref + "gronglet"))
		.dropAsItem()
		.setShootSound(UGSoundEvents.GRONGLET_SHOOT)
		.setHitSound(UGSoundEvents.GRONGLET_PLACE)
		.addHitEffect(new PlaceBlockHitEffect(UGBlocks.GRONGLET.get().defaultBlockState()))
		.addHitEffect(new EquipItemHitEffect())
		.build();
}
