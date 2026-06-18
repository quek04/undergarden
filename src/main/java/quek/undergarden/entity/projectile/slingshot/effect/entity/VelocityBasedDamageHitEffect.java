package quek.undergarden.entity.projectile.slingshot.effect.entity;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.custom.UGHitEffects;

public class VelocityBasedDamageHitEffect implements HitEffect {

	public static final VelocityBasedDamageHitEffect INSTANCE = new VelocityBasedDamageHitEffect();
	public static final MapCodec<VelocityBasedDamageHitEffect> CODEC = MapCodec.unit(INSTANCE);
	public static final StreamCodec<RegistryFriendlyByteBuf, VelocityBasedDamageHitEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends HitEffect> getType() {
		return UGHitEffects.VELOCITY_BASED_DAMAGE.get();
	}

	@Override
	public boolean apply(ServerLevel level, ItemStack ammoStack, SlingshotProjectile projectile, HitResult result) {
		if (result instanceof EntityHitResult entityResult) {
			float length = (float) projectile.getDeltaMovement().length();
			int damage = Mth.ceil(Mth.clamp((double) length * projectile.getAirTime(), 0.0D, 2.147483647E9D));
			return entityResult.getEntity().hurtServer(level, projectile.damageSources().source(UGDamageSources.DEPTHROCK_PEBBLE, projectile, projectile.getOwner()), damage);
		}
		return false;
	}
}
