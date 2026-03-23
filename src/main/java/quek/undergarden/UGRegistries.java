package quek.undergarden;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTrade;
import quek.undergarden.entity.monster.stoneborn.trading.StonebornTradeSet;
import quek.undergarden.entity.projectile.slingshot.effect.HitEffect;

public class UGRegistries {

	public static final Registry<HitEffect.Type<?>> HIT_EFFECT_TYPE = new RegistryBuilder<>(Keys.HIT_EFFECT_TYPE).sync(true).create();

	public static class Keys {
		public static final ResourceKey<Registry<HitEffect.Type<?>>> HIT_EFFECT_TYPE = ResourceKey.createRegistryKey(Undergarden.prefix("hit_effect_type"));
		public static final ResourceKey<Registry<StonebornTrade>> STONEBORN_TRADE = ResourceKey.createRegistryKey(Undergarden.prefix("stoneborn_trade"));
		public static final ResourceKey<Registry<StonebornTradeSet>> STONEBORN_TRADE_SET = ResourceKey.createRegistryKey(Undergarden.prefix("stoneborn_trade_set"));
	}

}
