package quek.undergarden.entity.stoneborn;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import quek.undergarden.registry.StonebornJobs;
import quek.undergarden.registry.UGRegistries;

public class StonebornData {
	private static final int[] NEXT_LEVEL_XP_THRESHOLDS = new int[]{0, 10, 70, 150, 250};
	private final StonebornJob job;
	private final int level;
	public static final Codec<StonebornData> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
				UGRegistries.STONEBORN_JOB_REGISTRY
					.byNameCodec()
					.fieldOf("job")
					.orElseGet(StonebornJobs.NONE)
					.forGetter(data -> data.job),
				Codec.INT.fieldOf("level")
					.orElse(1)
					.forGetter(data -> data.level)
			)
			.apply(instance, StonebornData::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, StonebornData> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.registry(UGRegistries.STONEBORN_JOB_REGISTRY_KEY),
		data -> data.job,
		ByteBufCodecs.INT,
		data -> data.level,
		StonebornData::new
	);

	public StonebornData(StonebornJob job, int level) {
		this.job = job;
		this.level = level;
	}

	public StonebornJob getJob() {
		return this.job;
	}

	public int getLevel() {
		return level;
	}

	public StonebornData setJob(StonebornJob job) {
		return new StonebornData(job, this.level);
	}

	public StonebornData setLevel(int level) {
		return new StonebornData(this.job, level);
	}

	public static int getMinXpPerLevel(int level) {
		return canLevelUp(level) ? NEXT_LEVEL_XP_THRESHOLDS[level - 1] : 0;
	}

	public static int getMaxXpPerLevel(int level) {
		return canLevelUp(level) ? NEXT_LEVEL_XP_THRESHOLDS[level] : 0;
	}

	public static boolean canLevelUp(int level) {
		return level >= 1 && level < 5;
	}
}