package quek.undergarden.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.component.RogdoriumInfusion;

public class UGDataComponents {
	public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Undergarden.MODID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<RogdoriumInfusion>> ROGDORIUM_INFUSION = COMPONENTS.register("rogdorium_infusion", () -> DataComponentType.<RogdoriumInfusion>builder().persistent(RogdoriumInfusion.CODEC).networkSynchronized(RogdoriumInfusion.STREAM_CODEC).build());
}