package quek.undergarden.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.component.RogdoriumInfusion;
import quek.undergarden.component.SlingshotAmmo;

public class UGDataComponents {
	public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Undergarden.MODID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<RogdoriumInfusion>> ROGDORIUM_INFUSION = COMPONENTS.register("rogdorium_infusion", () -> DataComponentType.<RogdoriumInfusion>builder().persistent(RogdoriumInfusion.CODEC).networkSynchronized(RogdoriumInfusion.STREAM_CODEC).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<SimpleFluidContent>> STORED_FLUID = COMPONENTS.register("stored_fluid", () -> DataComponentType.<SimpleFluidContent>builder().persistent(SimpleFluidContent.CODEC).networkSynchronized(SimpleFluidContent.STREAM_CODEC).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockState>> STORED_BLOCK = COMPONENTS.register("stored_block", () -> DataComponentType.<BlockState>builder().persistent(BlockState.CODEC).networkSynchronized(ByteBufCodecs.fromCodec(BlockState.CODEC)).build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<SlingshotAmmo>> SLINGSHOT_AMMO = COMPONENTS.register("slingshot_ammo", () -> DataComponentType.<SlingshotAmmo>builder().persistent(SlingshotAmmo.CODEC).networkSynchronized(SlingshotAmmo.STREAM_CODEC).build());
}