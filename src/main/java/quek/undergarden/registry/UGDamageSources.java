package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import quek.undergarden.Undergarden;

public class UGDamageSources {

	public static final ResourceKey<DamageType> DEPTHROCK_PEBBLE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Undergarden.MODID, "depthrock_pebble"));
	public static final ResourceKey<DamageType> GOO_BALL = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Undergarden.MODID, "goo_ball"));
	public static final ResourceKey<DamageType> BLISTERBERRY_BUSH = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Undergarden.MODID, "blisterberry_bush"));
	public static final ResourceKey<DamageType> SHARD_TORCH = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Undergarden.MODID, "shard_torch"));

	public static DamageSource getShardTorchDamage(Level level, @NotNull Vec3 position) {
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(SHARD_TORCH), position);
	}

	public static void bootstrap(BootstrapContext<DamageType> context) {
		context.register(DEPTHROCK_PEBBLE, new DamageType("arrow", 0.1F));
		context.register(GOO_BALL, new DamageType("arrow", 0.1F));
		context.register(BLISTERBERRY_BUSH, new DamageType("blisterberry_bush", 0.1F, DamageEffects.POKING));
		context.register(SHARD_TORCH, new DamageType("shard_torch", DamageScaling.ALWAYS, 0.1F));
	}
}