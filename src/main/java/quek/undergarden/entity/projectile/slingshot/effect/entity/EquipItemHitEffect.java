package quek.undergarden.entity.projectile.slingshot.effect.entity;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;
import quek.undergarden.registry.custom.UGHitEffects;

public record EquipItemHitEffect() implements HitEffect {

	public static final EquipItemHitEffect INSTANCE = new EquipItemHitEffect();
	public static final MapCodec<EquipItemHitEffect> CODEC = MapCodec.unit(INSTANCE);
	public static final StreamCodec<RegistryFriendlyByteBuf, EquipItemHitEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);

	@Override
	public Type<? extends HitEffect> getType() {
		return UGHitEffects.EQUIP_ITEM.get();
	}

	@Override
	public boolean apply(ServerLevel level, ItemStack ammoStack, SlingshotProjectile projectile, HitResult result) {
		if (result instanceof EntityHitResult entityResult) {
			if (entityResult.getEntity() instanceof Player player && ammoStack.has(DataComponents.EQUIPPABLE)) {
				return ammoStack.get(DataComponents.EQUIPPABLE).equipOnTarget(player, player, ammoStack).consumesAction();
			}
		}
		return false;
	}
}
