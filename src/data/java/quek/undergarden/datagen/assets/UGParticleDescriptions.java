package quek.undergarden.datagen.assets;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGParticleTypes;

import java.util.Iterator;

public class UGParticleDescriptions extends ParticleDescriptionProvider {

	public UGParticleDescriptions(PackOutput output) {
		super(output);
	}

	@Override
	protected void addDescriptions() {
		this.spriteSet(UGParticleTypes.DRIPPING_BLOOD.get(), Identifier.withDefaultNamespace("drip_hang"));
		this.spriteSet(UGParticleTypes.FALLING_BLOOD.get(), Identifier.withDefaultNamespace("drip_fall"));
		this.spriteSet(UGParticleTypes.LANDING_BLOOD.get(), Identifier.withDefaultNamespace("drip_land"));
		this.spriteSet(UGParticleTypes.DRIPPING_INK.get(), Identifier.withDefaultNamespace("drip_hang"));
		this.spriteSet(UGParticleTypes.FALLING_INK.get(), Identifier.withDefaultNamespace("drip_fall"));
		this.spriteSet(UGParticleTypes.LANDING_INK.get(), Identifier.withDefaultNamespace("drip_land"));
		this.spriteSet(UGParticleTypes.DRIPPING_VIRULENT.get(), Identifier.withDefaultNamespace("drip_hang"));
		this.spriteSet(UGParticleTypes.FALLING_VIRULENT.get(), Identifier.withDefaultNamespace("drip_fall"));
		this.spriteSet(UGParticleTypes.LANDING_VIRULENT.get(), Identifier.withDefaultNamespace("drip_land"));
		this.spriteSet(UGParticleTypes.FALLING_GOO.get(), Identifier.withDefaultNamespace("drip_fall"));
		this.spriteSet(UGParticleTypes.LANDING_GOO.get(), Identifier.withDefaultNamespace("drip_land"));
		this.spriteSet(UGParticleTypes.SHARD.get(), Undergarden.prefix("utherium_flame"));
		this.spriteSet(UGParticleTypes.SHARD_BEAM.get(), Undergarden.prefix("utherium_crit"));
		this.spriteSet(UGParticleTypes.UTHERIUM_CRIT.get(), Undergarden.prefix("utherium_crit"));

		this.spriteSet(UGParticleTypes.GLOOMPER_FART.get(), Undergarden.prefix("gloomper_fart"), 3, false);
		this.spriteSet(UGParticleTypes.GRONGLE_SPORE.get(), Undergarden.prefix("grongle_spore"), 3, false);
		this.spriteSet(UGParticleTypes.ROGDORIUM_SPARKLE.get(), Undergarden.prefix("rogdorium_sparkle"), 3, false);
		this.spriteSet(UGParticleTypes.SNOWFLAKE.get(), Undergarden.prefix("snowflake"), 3, false);
		this.spriteSet(UGParticleTypes.SHIMMER.get(), Undergarden.prefix("shimmer"), 5, false);
		this.spriteSet(UGParticleTypes.UNDERGARDEN_PORTAL.get(), Identifier.withDefaultNamespace("generic"), 8, false);
		this.spriteSet(UGParticleTypes.SMOG.get(), Identifier.withDefaultNamespace("big_smoke"), 12, false);
		this.spriteSet(UGParticleTypes.ROGDORIUM_WISP.get(), Undergarden.prefix("rogdorium_wisp"), 22, false);

		this.spriteSet(UGParticleTypes.TOTEM_BEAM.get(), () -> new Iterator<>() {
			private int counter = 0;
			private int spriteIdx = 0;

			@Override
			public boolean hasNext() {
				return this.counter < 8;
			}

			@Override
			public Identifier next() {
				var texture = Undergarden.prefix("rogdorium_sparkle").withSuffix("_" + this.spriteIdx);
				this.counter++;
				if (this.counter >= 4 && this.counter % 2 == 0) {
					this.spriteIdx++;
				}
				return texture;
			}
		});
	}
}
