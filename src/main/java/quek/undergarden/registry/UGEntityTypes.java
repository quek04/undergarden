package quek.undergarden.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.*;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.entity.animal.Gwib;
import quek.undergarden.entity.Minion;
import quek.undergarden.entity.UGBoat;
import quek.undergarden.entity.animal.*;
import quek.undergarden.entity.boss.ForgottenGuardian;
import quek.undergarden.entity.boss.Masticator;
import quek.undergarden.entity.cavern.CavernMonster;
import quek.undergarden.entity.cavern.Muncher;
import quek.undergarden.entity.cavern.Nargoyle;
import quek.undergarden.entity.cavern.Sploogie;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.entity.projectile.MinionProjectile;
import quek.undergarden.entity.projectile.slingshot.DepthrockPebble;
import quek.undergarden.entity.projectile.slingshot.GooBall;
import quek.undergarden.entity.projectile.slingshot.Gronglet;
import quek.undergarden.entity.projectile.slingshot.RottenBlisterberry;
import quek.undergarden.entity.rotspawn.Rotbeast;
import quek.undergarden.entity.rotspawn.Rotling;
import quek.undergarden.entity.rotspawn.RotspawnMonster;
import quek.undergarden.entity.rotspawn.Rotwalker;
import quek.undergarden.entity.stoneborn.Stoneborn;

@Mod.EventBusSubscriber(modid = "undergarden", bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Undergarden.MODID);

    //misc
    public static final RegistryObject<EntityType<UGBoat>> BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<UGBoat>of(UGBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
    public static final RegistryObject<EntityType<Boomgourd>> BOOMGOURD = ENTITIES.register("boomgourd", () -> EntityType.Builder.<Boomgourd>of(Boomgourd::new, MobCategory.MISC).fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10).updateInterval(10).build("boomgourd"));
    public static final RegistryObject<EntityType<DepthrockPebble>> DEPTHROCK_PEBBLE = ENTITIES.register("depthrock_pebble", () -> EntityType.Builder.<DepthrockPebble>of(DepthrockPebble::new, MobCategory.MISC).sized(0.25F, 0.25F).build("depthrock_pebble"));
    public static final RegistryObject<EntityType<GooBall>> GOO_BALL = ENTITIES.register("goo_ball", () -> EntityType.Builder.<GooBall>of(GooBall::new, MobCategory.MISC).sized(0.25F, 0.25F).build("goo_ball"));
    public static final RegistryObject<EntityType<RottenBlisterberry>> ROTTEN_BLISTERBERRY = ENTITIES.register("rotten_blisterberry", () -> EntityType.Builder.<RottenBlisterberry>of(RottenBlisterberry::new, MobCategory.MISC).sized(0.25F, 0.25F).build("rotten_blisterberry"));
    public static final RegistryObject<EntityType<Blisterbomb>> BLISTERBOMB = ENTITIES.register("blisterbomb", () -> EntityType.Builder.<Blisterbomb>of(Blisterbomb::new, MobCategory.MISC).sized(0.5F, 0.5F).build("blisterbomb"));
    public static final RegistryObject<EntityType<Gronglet>> GRONGLET = ENTITIES.register("gronglet", () -> EntityType.Builder.<Gronglet>of(Gronglet::new, MobCategory.MISC).sized(0.5F, 0.5F).build("gronglet"));
    public static final RegistryObject<EntityType<MinionProjectile>> MINION_PROJECTILE = ENTITIES.register("minion_projectile", () -> EntityType.Builder.<MinionProjectile>of(MinionProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F).build("minion_projectile"));
    public static final RegistryObject<EntityType<Minion>> MINION = ENTITIES.register("minion", () -> EntityType.Builder.of(Minion::new, MobCategory.MISC).sized(1.0F, 1.6F).build("minion"));

    //normal
    public static final RegistryObject<EntityType<Rotling>> ROTLING = ENTITIES.register("rotling", () -> EntityType.Builder.of(Rotling::new, MobCategory.MONSTER).sized(0.6F, 1.0F).build("rotling"));
    public static final RegistryObject<EntityType<Rotwalker>> ROTWALKER = ENTITIES.register("rotwalker", () -> EntityType.Builder.of(Rotwalker::new, MobCategory.MONSTER).sized(0.8F,2.5F).build("rotwalker"));
    public static final RegistryObject<EntityType<Rotbeast>> ROTBEAST = ENTITIES.register("rotbeast", () -> EntityType.Builder.of(Rotbeast::new, MobCategory.MONSTER).sized(1.5F,3.0F).build("rotbeast"));
    public static final RegistryObject<EntityType<Dweller>> DWELLER = ENTITIES.register("dweller", () -> EntityType.Builder.of(Dweller::new, MobCategory.CREATURE).sized(1.2F,1.8F).build("dweller"));
    public static final RegistryObject<EntityType<Gwibling>> GWIBLING = ENTITIES.register("gwibling", () -> EntityType.Builder.of(Gwibling::new, MobCategory.WATER_AMBIENT).sized(.5F, .3F).build("gwibling"));
    public static final RegistryObject<EntityType<Brute>> BRUTE = ENTITIES.register("brute", () -> EntityType.Builder.of(Brute::new, MobCategory.CREATURE).sized(1.0F, 2).build("brute"));
    public static final RegistryObject<EntityType<Scintling>> SCINTLING = ENTITIES.register("scintling", () -> EntityType.Builder.of(Scintling::new, MobCategory.AMBIENT).sized(1.0F, .5F).build("scintling"));
    public static final RegistryObject<EntityType<Gloomper>> GLOOMPER = ENTITIES.register("gloomper", () -> EntityType.Builder.of(Gloomper::new, MobCategory.CREATURE).sized(0.99F, 0.99F).build("gloomper"));
    public static final RegistryObject<EntityType<Stoneborn>> STONEBORN = ENTITIES.register("stoneborn", () -> EntityType.Builder.of(Stoneborn::new, MobCategory.MONSTER).sized(1.0F, 2.6F).build("stoneborn"));
    public static final RegistryObject<EntityType<Nargoyle>> NARGOYLE = ENTITIES.register("nargoyle", () -> EntityType.Builder.of(Nargoyle::new, MobCategory.MONSTER).sized(1.0F, 1.5F).build("nargoyle"));
    public static final RegistryObject<EntityType<Muncher>> MUNCHER = ENTITIES.register("muncher", () -> EntityType.Builder.of(Muncher::new, MobCategory.MONSTER).sized(0.8F, 0.8F).build("muncher"));
    public static final RegistryObject<EntityType<Sploogie>> SPLOOGIE = ENTITIES.register("sploogie", () -> EntityType.Builder.of(Sploogie::new, MobCategory.MONSTER).sized(0.8F, 0.8F).build("sploogie"));
    public static final RegistryObject<EntityType<Gwib>> GWIB = ENTITIES.register("gwib", () -> EntityType.Builder.of(Gwib::new, MobCategory.WATER_CREATURE).sized(1.0F, 0.5F).build("gwib"));
    public static final RegistryObject<EntityType<Mog>> MOG = ENTITIES.register("mog", () -> EntityType.Builder.of(Mog::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build("mog"));
    public static final RegistryObject<EntityType<Forgotten>> FORGOTTEN = ENTITIES.register("forgotten", () -> EntityType.Builder.of(Forgotten::new, MobCategory.MONSTER).sized(0.7F, 2.2F).build("forgotten"));

    //bosses
    public static final RegistryObject<EntityType<Masticator>> MASTICATOR = ENTITIES.register("masticator", () -> EntityType.Builder.of(Masticator::new, MobCategory.MONSTER).sized(2.5F, 4).build("masticator"));
    public static final RegistryObject<EntityType<ForgottenGuardian>> FORGOTTEN_GUARDIAN = ENTITIES.register("forgotten_guardian", () -> EntityType.Builder.of(ForgottenGuardian::new, MobCategory.MONSTER).sized(1.0F, 3.8F).build("forgotten_guardian"));

    public static void spawnPlacements() {
        SpawnPlacements.register(GWIBLING.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Gwibling::canGwiblingSpawn);
        SpawnPlacements.register(DWELLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ROTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn);
        SpawnPlacements.register(ROTWALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn);
        SpawnPlacements.register(ROTBEAST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnMonster::canRotspawnSpawn);
        SpawnPlacements.register(BRUTE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(SCINTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Scintling::canScintlingSpawn);
        SpawnPlacements.register(GLOOMPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(STONEBORN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Stoneborn::canStonebornSpawn);
        SpawnPlacements.register(NARGOYLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn);
        SpawnPlacements.register(MUNCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn);
        SpawnPlacements.register(SPLOOGIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernMonster::canCreatureSpawn);
        SpawnPlacements.register(GWIB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Gwib::canGwibSpawn);
        SpawnPlacements.register(MOG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(FORGOTTEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ROTLING.get(), Rotling.registerAttributes().build());
        event.put(ROTWALKER.get(), Rotwalker.registerAttributes().build());
        event.put(ROTBEAST.get(), Rotbeast.registerAttributes().build());
        event.put(DWELLER.get(), Dweller.registerAttributes().build());
        event.put(GWIBLING.get(), AbstractFish.createAttributes().build());
        event.put(BRUTE.get(), Brute.registerAttributes().build());
        event.put(SCINTLING.get(), Scintling.registerAttributes().build());
        event.put(GLOOMPER.get(), Gloomper.registerAttributes().build());
        event.put(STONEBORN.get(), Stoneborn.registerAttributes().build());
        event.put(MASTICATOR.get(), Masticator.registerAttributes().build());
        event.put(NARGOYLE.get(), Nargoyle.registerAttributes().build());
        event.put(FORGOTTEN_GUARDIAN.get(), ForgottenGuardian.registerAttributes().build());
        event.put(MUNCHER.get(), Muncher.registerAttributes().build());
        event.put(SPLOOGIE.get(), Sploogie.registerAttributes().build());
        event.put(MINION.get(), Minion.registerAttributes().build());
        event.put(GWIB.get(), Gwib.registerAttributes().build());
        event.put(MOG.get(), Mog.registerAttributes().build());
        event.put(FORGOTTEN.get(), Forgotten.createAttributes().build());
    }
}