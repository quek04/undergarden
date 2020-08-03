package quek.undergarden.entity.stoneborn;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.item.Item;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItems;

public class StonebornTasks {

    public static final Item regalium = UndergardenItems.regalium_nugget.get();

    public static Brain<?> getStonebornTasks(StonebornEntity stoneborn, Brain<StonebornEntity> brain) {
        coreTasks(brain);
        idleTasks(brain);
        brain.setDefaultActivities(ImmutableSet.of(Activity.CORE));
        brain.setFallbackActivity(Activity.IDLE);
        brain.func_233714_e_();
        return brain;
    }

    public static void stuff(StonebornEntity stoneborn) {
        Brain<StonebornEntity> brain = stoneborn.getBrain();
        brain.func_233706_a_(ImmutableList.of(Activity.CORE, Activity.IDLE));
    }

    private static void coreTasks(Brain<StonebornEntity> brain) {
        brain.func_233698_a_(Activity.CORE, 0, ImmutableList.<Task<? super StonebornEntity>>of(new LookTask(45, 90), new WalkToTargetTask(200), new WalkRandomlyTask(0.3F), new InteractWithDoorTask()));
    }

    private static void idleTasks(Brain<StonebornEntity> brain) {
        brain.func_233698_a_(Activity.IDLE, 10, ImmutableList.<Task<? super StonebornEntity>>of(new LookAtEntityTask(StonebornTasks::target, 14.0F)));
    }

    public static boolean target(LivingEntity entity) {
        return entity.getType() == EntityType.PLAYER || entity.getType() == UndergardenEntities.STONEBORN.get();
    }

    private static boolean isRegalium(Item item) {
        return item == regalium;
    }

    private static boolean isOffhandEmpty(StonebornEntity stoneborn) {
        return stoneborn.getHeldItemOffhand().isEmpty();
    }
}
