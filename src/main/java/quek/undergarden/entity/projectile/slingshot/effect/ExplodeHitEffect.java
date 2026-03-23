package quek.undergarden.entity.projectile.slingshot.effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.registry.custom.UGHitEffects;

public record ExplodeHitEffect(float radius, boolean fire, Level.ExplosionInteraction interaction) implements HitEffect {

	public static final MapCodec<ExplodeHitEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		Codec.FLOAT.fieldOf("radius").forGetter(ExplodeHitEffect::radius),
		Codec.BOOL.fieldOf("fire").forGetter(ExplodeHitEffect::fire),
		Level.ExplosionInteraction.CODEC.fieldOf("interaction").forGetter(ExplodeHitEffect::interaction)
	).apply(instance, ExplodeHitEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, ExplodeHitEffect> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.FLOAT, ExplodeHitEffect::radius,
		ByteBufCodecs.BOOL, ExplodeHitEffect::fire,
		ByteBufCodecs.fromCodec(Level.ExplosionInteraction.CODEC), ExplodeHitEffect::interaction,
		ExplodeHitEffect::new
	);

	@Override
	public Type<? extends HitEffect> getType() {
		return UGHitEffects.EXPLODE.get();
	}

	@Override
	public boolean apply(ServerLevel level, ItemStack ammoStack, SlingshotProjectile projectile, HitResult result) {
		level.explode(projectile, projectile.getX(), projectile.getY(), projectile.getZ(), this.radius(), this.fire(), this.interaction());
		return true;
	}
}
