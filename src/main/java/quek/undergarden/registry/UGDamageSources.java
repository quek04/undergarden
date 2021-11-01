package quek.undergarden.registry;

import net.minecraft.world.damagesource.DamageSource;

public class UGDamageSources {

    public static final DamageSource BLISTERBERRY_BUSH = new DamageSource("blisterberry_bush");
    public static final DamageSource SHARD_TORCH = new DamageSource("shard_torch").bypassArmor().setMagic();
}