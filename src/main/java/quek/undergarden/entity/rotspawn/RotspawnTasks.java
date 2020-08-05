package quek.undergarden.entity.rotspawn;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.task.*;

import java.util.Optional;

public class RotspawnTasks {

    public static Brain<?> getRotspawnTasks(Brain<AbstractRotspawnEntity> brain) {
        coreTasks(brain);
        idleTasks(brain);
        fightTasks(brain);

        brain.setDefaultActivities(ImmutableSet.of(Activity.CORE));
        brain.setFallbackActivity(Activity.IDLE);
        brain.func_233714_e_();
        return brain;
    }

    protected static void stuff(AbstractRotspawnEntity rotspawn) {
        Brain<AbstractRotspawnEntity> brain = rotspawn.getBrain();
        brain.func_233706_a_(ImmutableList.of(Activity.FIGHT, Activity.AVOID, Activity.IDLE));
        rotspawn.setAggroed(brain.hasMemory(MemoryModuleType.ATTACK_TARGET));
    }

    private static void coreTasks(Brain<AbstractRotspawnEntity> brain) {
        brain.func_233698_a_(Activity.CORE, 0, ImmutableList.of(new SwimTask(0.1F), new LookTask(45, 90), new WalkToTargetTask(200), new WalkRandomlyTask(1F)));
    }

    private static void idleTasks(Brain<AbstractRotspawnEntity> brain) {
        brain.func_233698_a_(Activity.IDLE, 10, ImmutableList.<Task<? super AbstractRotspawnEntity>>of(new LookAtEntityTask(LivingEntity::attackable, 10.0F), new RandomlyStopAttackingTask(MemoryModuleType.NEAREST_REPELLENT, 200), RunAwayTask.func_233963_a_(MemoryModuleType.NEAREST_REPELLENT, 1.0F, 8, true), new ForgetAttackTargetTask<>(RotspawnTasks::attackTarget)));
    }

    private static void fightTasks(Brain<AbstractRotspawnEntity> brain) {
        brain.func_233699_a_(Activity.FIGHT, 10, ImmutableList.<Task<? super AbstractRotspawnEntity>>of(new RandomlyStopAttackingTask(MemoryModuleType.NEAREST_REPELLENT, 200), new MoveToTargetTask(1.0F), new AttackTargetTask(40)), MemoryModuleType.ATTACK_TARGET);
    }

    private static Optional<? extends LivingEntity> attackTarget(AbstractRotspawnEntity rotspawn) {
        return !rotspawn.getBrain().hasMemory(MemoryModuleType.PACIFIED) ? rotspawn.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER) : Optional.empty();
    }
}
