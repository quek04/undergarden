package quek.undergarden.component;

import com.google.common.base.Preconditions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jspecify.annotations.Nullable;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.registry.UGSoundEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public record SlingshotAmmo(List<Identifier> chargeTextures, boolean dropAsItem, IntProvider breakParticleCount, Holder<SoundEvent> shootSound, Optional<Holder<SoundEvent>> hitSound, List<HitEffect> hitEffects) implements TooltipProvider {

	public static final Codec<SlingshotAmmo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Identifier.CODEC.listOf().optionalFieldOf("charge_textures", new ArrayList<>()).forGetter(SlingshotAmmo::chargeTextures),
			Codec.BOOL.fieldOf("drop_as_item").forGetter(SlingshotAmmo::dropAsItem),
			IntProviders.NON_NEGATIVE_CODEC.optionalFieldOf("break_particle_count", ConstantInt.of(0)).forGetter(SlingshotAmmo::breakParticleCount),
			SoundEvent.CODEC.optionalFieldOf("shoot_sound", UGSoundEvents.SLINGSHOT_SHOOT).forGetter(SlingshotAmmo::shootSound),
			SoundEvent.CODEC.optionalFieldOf("hit_sound").forGetter(SlingshotAmmo::hitSound),
			HitEffect.CODEC.listOf().optionalFieldOf("hit_effects", new ArrayList<>()).forGetter(SlingshotAmmo::hitEffects))
		.apply(instance, SlingshotAmmo::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, SlingshotAmmo> STREAM_CODEC = StreamCodec.composite(
		Identifier.STREAM_CODEC.apply(ByteBufCodecs.list()), SlingshotAmmo::chargeTextures,
		ByteBufCodecs.BOOL, SlingshotAmmo::dropAsItem,
		ByteBufCodecs.fromCodec(IntProviders.NON_NEGATIVE_CODEC), SlingshotAmmo::breakParticleCount,
		ByteBufCodecs.holderRegistry(Registries.SOUND_EVENT), SlingshotAmmo::shootSound,
		ByteBufCodecs.optional(ByteBufCodecs.holderRegistry(Registries.SOUND_EVENT)), SlingshotAmmo::hitSound,
		HitEffect.STREAM_CODEC.apply(ByteBufCodecs.list()), SlingshotAmmo::hitEffects,
		SlingshotAmmo::new
	);

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
		consumer.accept(Component.translatable("tooltip.undergarden.slingshot_ammo").withStyle(ChatFormatting.GRAY));
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private List<Identifier> chargeTextures = new ArrayList<>();
		private boolean dropAsItem = false;
		private IntProvider breakParticleCount = ConstantInt.of(0);
		private Holder<SoundEvent> shootSound = UGSoundEvents.SLINGSHOT_SHOOT;
		@Nullable
		private Holder<SoundEvent> hitSound;
		private final List<HitEffect> hitEffects = new ArrayList<>();

		public Builder setSlingshotChargeTextures(List<Identifier> chargeTextures) {
			Preconditions.checkArgument(chargeTextures.size() == 3, "Must define 3 charge textures for slingshot ammo");
			this.chargeTextures = chargeTextures;
			return this;
		}

		public Builder setSlingshotChargeTextures(Identifier baseTexture) {
			this.chargeTextures = List.of(
				baseTexture.withSuffix("_0"),
				baseTexture.withSuffix("_1"),
				baseTexture.withSuffix("_2")
			);
			return this;
		}

		public Builder dropAsItem() {
			this.dropAsItem = true;
			return this;
		}

		public Builder setBreakParticleCount(IntProvider count) {
			this.breakParticleCount = count;
			return this;
		}

		public Builder setShootSound(Holder<SoundEvent> sound) {
			this.shootSound = sound;
			return this;
		}

		public Builder setHitSound(Holder<SoundEvent> sound) {
			this.hitSound = sound;
			return this;
		}

		public Builder setHitSound(SoundEvent sound) {
			this.hitSound = BuiltInRegistries.SOUND_EVENT.wrapAsHolder(sound);
			return this;
		}

		public Builder addHitEffect(HitEffect type) {
			this.hitEffects.add(type);
			return this;
		}

		public SlingshotAmmo build() {
			return new SlingshotAmmo(this.chargeTextures, this.dropAsItem, this.breakParticleCount, this.shootSound, Optional.ofNullable(this.hitSound), this.hitEffects);
		}
	}
}
