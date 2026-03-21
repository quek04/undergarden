package quek.undergarden.entity.animal.dweller;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;

public class DwellerItemBasedSteering {
	private final SynchedEntityData entityData;
	private final EntityDataAccessor<Integer> boostTimeAccessor;
	public boolean boosting;
	public int boostTime;

	public DwellerItemBasedSteering(SynchedEntityData data, EntityDataAccessor<Integer> boostTimeAccessor) {
		this.entityData = data;
		this.boostTimeAccessor = boostTimeAccessor;
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
}
