package quek.undergarden.registry;

import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.stoneborn.StonebornJob;

public class StonebornJobs {
	public static final DeferredRegister<StonebornJob> STONEBORN_JOBS = DeferredRegister.create(UGRegistries.STONEBORN_JOB_REGISTRY, Undergarden.MODID);

	public static final DeferredHolder<StonebornJob, StonebornJob> TEST = STONEBORN_JOBS.register("test_job", () -> new StonebornJob(Items.STICK));
}