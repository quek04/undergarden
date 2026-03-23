package quek.undergarden.entity.projectile.slingshot.effect.entity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.registry.custom.UGHitEffects;

import java.util.Optional;

public record HealHitEffect(FloatProvider healAmount, Optional<HolderSet<EntityType<?>>> affectedEntities) implements HitEffect {

	public static final MapCodec<HealHitEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		FloatProviders.CODEC.fieldOf("heal_amount").forGetter(HealHitEffect::healAmount),
		RegistryCodecs.homogeneousList(Registries.ENTITY_TYPE).optionalFieldOf("affected_entities").forGetter(HealHitEffect::affectedEntities)
	).apply(instance, HealHitEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, HealHitEffect> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.fromCodec(FloatProviders.CODEC), HealHitEffect::healAmount,
		ByteBufCodecs.optional(ByteBufCodecs.holderSet(Registries.ENTITY_TYPE)), HealHitEffect::affectedEntities,
		HealHitEffect::new
	);

	public HealHitEffect(float healAmount, Holder<EntityType<?>> affected) {
		this(ConstantFloat.of(healAmount), Optional.of(HolderSet.direct(affected)));
	}

	@Override
	public Type<? extends HitEffect> getType() {
		return UGHitEffects.HEAL.get();
	}

	@Override
	public boolean apply(ServerLevel level, ItemStack ammoStack, SlingshotProjectile projectile, HitResult result) {
		if (result instanceof EntityHitResult entityResult && entityResult.getEntity() instanceof LivingEntity living) {
			if (this.affectedEntities().isEmpty() || this.affectedEntities().get().contains(living.typeHolder())) {
				living.heal(this.healAmount().sample(projectile.getRandom()));
				return true;
			}
		}
		return false;
	}
}
