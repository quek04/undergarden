package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import quek.undergarden.Undergarden;
import quek.undergarden.datamap.BiomeLethality;

public class UGDataMaps {
	public static final DataMapType<Biome, BiomeLethality> BIOME_LETHALITY = DataMapType.builder(
		ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "biome_lethality"), Registries.BIOME, BiomeLethality.CODEC).synced(BiomeLethality.CODEC, false).build();
}
