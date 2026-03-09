package quek.undergarden.registry;

import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.stoneborn.StonebornData;

public class UGEntityDataSerializers {
	public static final DeferredRegister<EntityDataSerializer<?>> DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, Undergarden.MODID);

	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<StonebornData>> STONEBORN_DATA = DATA_SERIALIZERS.register("stoneborn_data", () -> EntityDataSerializer.forValueType(StonebornData.STREAM_CODEC));
}
