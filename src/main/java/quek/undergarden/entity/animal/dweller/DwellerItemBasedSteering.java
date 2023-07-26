package quek.undergarden.entity.animal.dweller;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;

public class DwellerItemBasedSteering {
	private final SynchedEntityData entityData;
	private final EntityDataAccessor<Integer> boostTimeAccessor;
	private final EntityDataAccessor<Boolean> hasSaddleAccessor;
	public boolean boosting;
	public int boostTime;

	public DwellerItemBasedSteering(SynchedEntityData data, EntityDataAccessor<Integer> boostTimeAccessor, EntityDataAccessor<Boolean> hasSaddleAccessor) {
		this.entityData = data;
		this.boostTimeAccessor = boostTimeAccessor;
		this.hasSaddleAccessor = hasSaddleAccessor;
	}

	public void onSynced() {
		this.boosting = true;
		this.boostTime = 0;
	}

	public boolean boost(RandomSource random) {
		if (this.boosting) {
			return false;
		} else {
			this.boosting = true;
			this.boostTime = 0;
			this.entityData.set(this.boostTimeAccessor, random.nextInt(841) + 140);
			//TODO add a sound for boosting
			return true;
		}
	}

	public void tickBoost() {
		if (this.boosting && this.boostTime++ > this.boostTimeTotal()) {
			this.boosting = false;
			//TODO add a sound for when the dweller slows down
		}

	}

	public float boostFactor() {
		return this.boosting ? 1.95F : 1.5F;
	}

	public int boostTimeTotal() {
		return this.entityData.get(this.boostTimeAccessor);
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putBoolean("Saddle", this.hasSaddle());
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		this.setSaddle(tag.getBoolean("Saddle"));
	}

	public void setSaddle(boolean saddle) {
		this.entityData.set(this.hasSaddleAccessor, saddle);
	}

	public boolean hasSaddle() {
		return this.entityData.get(this.hasSaddleAccessor);
	}
}
