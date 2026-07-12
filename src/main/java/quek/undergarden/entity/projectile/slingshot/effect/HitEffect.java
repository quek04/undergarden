package quek.undergarden.entity.projectile.slingshot.effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.UGRegistries;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;

public interface HitEffect {

	Codec<HitEffect> CODEC = UGRegistries.HIT_EFFECT_TYPE.byNameCodec().dispatch(HitEffect::getType, HitEffect.Type::codec);
	StreamCodec<RegistryFriendlyByteBuf, HitEffect> STREAM_CODEC = ByteBufCodecs.registry(UGRegistries.Keys.HIT_EFFECT_TYPE).dispatch(HitEffect::getType, HitEffect.Type::streamCodec);

	HitEffect.Type<? extends HitEffect> getType();

	/**
	 * Do something when hitting a target.
	 * @param level the current level
	 * @param ammoStack the ammo item. Can be used to check for other components or whatever
	 * @param projectile the current projectile being shot
	 * @param result the hit result of the ammo. Will never be a miss
	 * @return true if an effect was performed to finish the logic. If false, the ammo will drop as an item if the component allows it
	 */
	boolean apply(ServerLevel level, ItemStack ammoStack, SlingshotProjectile projectile, HitResult result);

	record Type<T extends HitEffect>(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {}
}
