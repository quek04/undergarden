package quek.undergarden.entity.projectile.slingshot.effect.entity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.registry.custom.UGHitEffects;

public record AddMobEffectHitEffect(MobEffectInstance instance, TagKey<EntityType<?>> immuneEntities) implements HitEffect {

	public static final MapCodec<AddMobEffectHitEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		MobEffectInstance.CODEC.fieldOf("effect").forGetter(AddMobEffectHitEffect::instance),
		TagKey.codec(Registries.ENTITY_TYPE).fieldOf("immune_entities").forGetter(AddMobEffectHitEffect::immuneEntities)
	).apply(instance, AddMobEffectHitEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, AddMobEffectHitEffect> STREAM_CODEC = StreamCodec.composite(
		MobEffectInstance.STREAM_CODEC, AddMobEffectHitEffect::instance,
		TagKey.streamCodec(Registries.ENTITY_TYPE), AddMobEffectHitEffect::immuneEntities,
		AddMobEffectHitEffect::new
	);

	@Override
	public Type<? extends HitEffect> getType() {
		return UGHitEffects.ADD_EFFECT.get();
	}

	@Override
	public boolean apply(ServerLevel level, ItemStack ammoStack, SlingshotProjectile projectile, HitResult result) {
		if (result instanceof EntityHitResult entityResult && entityResult.getEntity() instanceof LivingEntity living) {
			if (!living.is(this.immuneEntities())) {
				living.addEffect(this.instance());
				return true;
			}
		}
		return false;
	}
}
