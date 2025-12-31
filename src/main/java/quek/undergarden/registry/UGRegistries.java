package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.stoneborn.StonebornJob;

public class UGRegistries {
	public static final ResourceKey<Registry<StonebornJob>> STONEBORN_JOB_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "stoneborn_jobs"));
	public static final Registry<StonebornJob> STONEBORN_JOB_REGISTRY = new RegistryBuilder<>(STONEBORN_JOB_REGISTRY_KEY).create();
}