package quek.undergarden.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import quek.undergarden.registry.UGBlockEntities;

public class GrongletBlockEntity extends BlockEntity {

	public int yaw = -1;

	public GrongletBlockEntity(BlockPos pos, BlockState state) {
		super(UGBlockEntities.GRONGLET.get(), pos, state);
		if (this.yaw == -1) {
			this.yaw = (RandomSource.create().nextInt(360));
		}
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putInt("yaw", this.yaw);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.yaw = input.getIntOr("yaw", -1);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return this.saveCustomOnly(registries);
	}
}