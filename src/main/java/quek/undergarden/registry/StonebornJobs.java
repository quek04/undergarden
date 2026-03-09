package quek.undergarden.registry;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.stoneborn.StonebornJob;

public class StonebornJobs {
	public static final DeferredRegister<StonebornJob> JOBS = DeferredRegister.create(UGRegistries.STONEBORN_JOB_REGISTRY, Undergarden.MODID);

	public static final DeferredHolder<StonebornJob, StonebornJob> NONE = JOBS.register("none", () -> new StonebornJob(ItemStack.EMPTY));
	public static final DeferredHolder<StonebornJob, StonebornJob> TEST = JOBS.register("test_job", () -> new StonebornJob(new ItemStack(Items.STICK)));
}