package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import quek.undergarden.Undergarden;
import quek.undergarden.datamap.UthericInfectionLethality;

public class UGDataMaps {
	public static final DataMapType<Biome, UthericInfectionLethality> BIOME_LETHALITY = DataMapType.builder(
		Undergarden.prefix("biome_lethality"), Registries.BIOME, UthericInfectionLethality.CODEC).synced(UthericInfectionLethality.CODEC, false).build();
	public static final DataMapType<EntityType<?>, UthericInfectionLethality> ENTITY_LETHALITY = DataMapType.builder(
		Undergarden.prefix("entity_lethality"), Registries.ENTITY_TYPE, UthericInfectionLethality.CODEC).synced(UthericInfectionLethality.CODEC, false).build();
}
