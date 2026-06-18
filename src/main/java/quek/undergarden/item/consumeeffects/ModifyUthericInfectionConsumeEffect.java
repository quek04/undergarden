package quek.undergarden.item.consumeeffects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import quek.undergarden.component.UndergardenData;
import quek.undergarden.event.UthericInfectionEvents;
import quek.undergarden.registry.UGAttachments;
import quek.undergarden.registry.UGConsumeEffects;

public record ModifyUthericInfectionConsumeEffect(float value, boolean addToExisting) implements ConsumeEffect {

	public static final MapCodec<ModifyUthericInfectionConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		Codec.FLOAT.fieldOf("value").forGetter(ModifyUthericInfectionConsumeEffect::value),
		Codec.BOOL.optionalFieldOf("add_to_existing", false).forGetter(ModifyUthericInfectionConsumeEffect::addToExisting)
	).apply(i, ModifyUthericInfectionConsumeEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, ModifyUthericInfectionConsumeEffect> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.FLOAT,
		ModifyUthericInfectionConsumeEffect::value,
		ByteBufCodecs.BOOL,
		ModifyUthericInfectionConsumeEffect::addToExisting,
		ModifyUthericInfectionConsumeEffect::new
	);

	@Override
	public Type<? extends ConsumeEffect> getType() {
		return UGConsumeEffects.UTHERIC_INFECTION.get();
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity user) {
		if (user instanceof Player player) {
			UndergardenData data = player.getData(UGAttachments.UNDERGARDEN_DATA);
			if (this.addToExisting()) {
				player.setData(UGAttachments.UNDERGARDEN_DATA, data.setInfectionLevel(Mth.clamp(data.uthericInfection() + this.value(), 0.0F, UthericInfectionEvents.MAX_INFECTION)));
			} else {
				player.setData(UGAttachments.UNDERGARDEN_DATA, data.setInfectionLevel(Mth.clamp(this.value(), 0.0F, UthericInfectionEvents.MAX_INFECTION)));
			}
			return true;
		}
		return false;
	}
}
