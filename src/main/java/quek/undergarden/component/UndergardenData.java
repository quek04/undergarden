package quek.undergarden.component;

import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentCopyHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jspecify.annotations.Nullable;

public record UndergardenData(float uthericInfection, int uthericHits, boolean handledStartSpawn) implements IAttachmentCopyHandler<UndergardenData>, IAttachmentSerializer<UndergardenData>, AttachmentSyncHandler<UndergardenData> {

	public UndergardenData() {
		this(0.0F, 0, false);
	}

	@Override
	public UndergardenData copy(UndergardenData attachment, IAttachmentHolder holder, HolderLookup.Provider provider) {
		return new UndergardenData(0.0F, 0, attachment.handledStartSpawn);
	}

	@Override
	public UndergardenData read(IAttachmentHolder holder, ValueInput input) {
		return new UndergardenData(input.getFloatOr("infection", 0.0F), input.getIntOr("infection_hits", 0), input.getBooleanOr("handled_spawn", false));
	}

	@Override
	public boolean write(UndergardenData attachment, ValueOutput output) {
		output.putFloat("infection", attachment.uthericInfection);
		output.putInt("infection_hits", attachment.uthericHits);
		output.putBoolean("handled_spawn", attachment.handledStartSpawn);
		return true;
	}

	@Override
	public void write(RegistryFriendlyByteBuf buf, UndergardenData attachment, boolean initialSync) {
		buf.writeFloat(attachment.uthericInfection);
		buf.writeFloat(attachment.uthericHits);
		buf.writeBoolean(attachment.handledStartSpawn);
	}

	@Override
	public UndergardenData read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable UndergardenData previousValue) {
		return new UndergardenData(buf.readFloat(), buf.readInt(), buf.readBoolean());
	}

	public UndergardenData setInfectionLevel(float infection) {
		return new UndergardenData(infection, this.uthericHits(), this.handledStartSpawn());
	}

	public UndergardenData setHits(int hits) {
		return new UndergardenData(this.uthericInfection(), hits, this.handledStartSpawn());
	}

	public UndergardenData handleSpawn() {
		return new UndergardenData(this.uthericInfection(), this.uthericHits(), true);
	}
}
