package quek.undergarden.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.GwibEntity;
import quek.undergarden.entity.MinionEntity;
import quek.undergarden.entity.UGBoatEntity;
import quek.undergarden.entity.animal.*;
import quek.undergarden.entity.boss.ForgottenGuardianEntity;
import quek.undergarden.entity.boss.MasticatorEntity;
import quek.undergarden.entity.cavern.CavernEntity;
import quek.undergarden.entity.cavern.MuncherEntity;
import quek.undergarden.entity.cavern.NargoyleEntity;
import quek.undergarden.entity.cavern.SploogieEntity;
import quek.undergarden.entity.projectile.*;
import quek.undergarden.entity.rotspawn.RotbeastEntity;
import quek.undergarden.entity.rotspawn.RotlingEntity;
import quek.undergarden.entity.rotspawn.RotspawnEntity;
import quek.undergarden.entity.rotspawn.RotwalkerEntity;
import quek.undergarden.entity.stoneborn.StonebornEntity;

@Mod.EventBusSubscriber(modid = "undergarden", bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Undergarden.MODID);

    //misc
    public static final RegistryObject<EntityType<UGBoatEntity>> BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<UGBoatEntity>of(UGBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
    public static final RegistryObject<EntityType<SlingshotPebbleEntity>> SLINGSHOT_PEBBLE = ENTITIES.register("slingshot_pebble", () -> EntityType.Builder.<SlingshotPebbleEntity>of(SlingshotPebbleEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).build("slingshot_pebble"));
    public static final RegistryObject<EntityType<GooBallEntity>> GOO_BALL = ENTITIES.register("goo_ball", () -> EntityType.Builder.<GooBallEntity>of(GooBallEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).build("goo_ball"));
    public static final RegistryObject<EntityType<RottenBlisterberryEntity>> ROTTEN_BLISTERBERRY = ENTITIES.register("rotten_blisterberry", () -> EntityType.Builder.<RottenBlisterberryEntity>of(RottenBlisterberryEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).build("rotten_blisterberry"));
    public static final RegistryObject<EntityType<BlisterbombEntity>> BLISTERBOMB = ENTITIES.register("blisterbomb", () -> EntityType.Builder.<BlisterbombEntity>of(BlisterbombEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build("blisterbomb"));
    public static final RegistryObject<EntityType<MinionProjectileEntity>> MINION_PROJECTILE = ENTITIES.register("minion_projectile", () -> EntityType.Builder.<MinionProjectileEntity>of(MinionProjectileEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).build("minion_projectile"));
    public static final RegistryObject<EntityType<MinionEntity>> MINION = ENTITIES.register("minion", () -> EntityType.Builder.of(MinionEntity::new, MobCategory.MISC).sized(1.0F, 1.6F).build("minion"));

    //normal
    public static final RegistryObject<EntityType<RotlingEntity>> ROTLING = ENTITIES.register("rotling", () -> EntityType.Builder.of(RotlingEntity::new, MobCategory.MONSTER).sized(0.6F, 1.0F).build("rotling"));
    public static final RegistryObject<EntityType<RotwalkerEntity>> ROTWALKER = ENTITIES.register("rotwalker", () -> EntityType.Builder.of(RotwalkerEntity::new, MobCategory.MONSTER).sized(0.8F,2.5F).build("rotwalker"));
    public static final RegistryObject<EntityType<RotbeastEntity>> ROTBEAST = ENTITIES.register("rotbeast", () -> EntityType.Builder.of(RotbeastEntity::new, MobCategory.MONSTER).sized(2.0F,3.0F).build("rotbeast"));
    public static final RegistryObject<EntityType<DwellerEntity>> DWELLER = ENTITIES.register("dweller", () -> EntityType.Builder.of(DwellerEntity::new, MobCategory.CREATURE).sized(1.2F,1.8F).build("dweller"));
    public static final RegistryObject<EntityType<GwiblingEntity>> GWIBLING = ENTITIES.register("gwibling", () -> EntityType.Builder.of(GwiblingEntity::new, MobCategory.WATER_AMBIENT).sized(.5F, .3F).build("gwibling"));
    public static final RegistryObject<EntityType<BruteEntity>> BRUTE = ENTITIES.register("brute", () -> EntityType.Builder.of(BruteEntity::new, MobCategory.CREATURE).sized(1.0F, 2).build("brute"));
    public static final RegistryObject<EntityType<ScintlingEntity>> SCINTLING = ENTITIES.register("scintling", () -> EntityType.Builder.of(ScintlingEntity::new, MobCategory.AMBIENT).sized(1.0F, .5F).build("scintling"));
    public static final RegistryObject<EntityType<GloomperEntity>> GLOOMPER = ENTITIES.register("gloomper", () -> EntityType.Builder.of(GloomperEntity::new, MobCategory.CREATURE).sized(0.99F, 0.99F).build("gloomper"));
    public static final RegistryObject<EntityType<StonebornEntity>> STONEBORN = ENTITIES.register("stoneborn", () -> EntityType.Builder.of(StonebornEntity::new, MobCategory.MONSTER).sized(1.0F, 2.6F).build("stoneborn"));
    public static final RegistryObject<EntityType<NargoyleEntity>> NARGOYLE = ENTITIES.register("nargoyle", () -> EntityType.Builder.of(NargoyleEntity::new, MobCategory.MONSTER).sized(1.0F, 1.5F).build("nargoyle"));
    public static final RegistryObject<EntityType<MuncherEntity>> MUNCHER = ENTITIES.register("muncher", () -> EntityType.Builder.of(MuncherEntity::new, MobCategory.MONSTER).sized(0.8F, 0.8F).build("muncher"));
    public static final RegistryObject<EntityType<SploogieEntity>> SPLOOGIE = ENTITIES.register("sploogie", () -> EntityType.Builder.of(SploogieEntity::new, MobCategory.MONSTER).sized(0.8F, 0.8F).build("sploogie"));
    public static final RegistryObject<EntityType<GwibEntity>> GWIB = ENTITIES.register("gwib", () -> EntityType.Builder.of(GwibEntity::new, MobCategory.WATER_CREATURE).sized(1.0F, 0.5F).build("gwib"));
    public static final RegistryObject<EntityType<MogEntity>> MOG = ENTITIES.register("mog", () -> EntityType.Builder.of(MogEntity::new, MobCategory.CREATURE).sized(1.0F, 1.0F).build("mog"));

    //bosses
    public static final RegistryObject<EntityType<MasticatorEntity>> MASTICATOR = ENTITIES.register("masticator", () -> EntityType.Builder.of(MasticatorEntity::new, MobCategory.MONSTER).sized(2.5F, 4).build("masticator"));
    public static final RegistryObject<EntityType<ForgottenGuardianEntity>> FORGOTTEN_GUARDIAN = ENTITIES.register("forgotten_guardian", () -> EntityType.Builder.of(ForgottenGuardianEntity::new, MobCategory.MONSTER).sized(1.0F, 3.8F).build("forgotten_guardian"));

    public static void spawnPlacements() {
        SpawnPlacements.register(GWIBLING.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GwiblingEntity::canGwiblingSpawn);
        SpawnPlacements.register(DWELLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(ROTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnEntity::canRotspawnSpawn);
        SpawnPlacements.register(ROTWALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnEntity::canRotspawnSpawn);
        SpawnPlacements.register(ROTBEAST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RotspawnEntity::canRotspawnSpawn);
        SpawnPlacements.register(BRUTE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(SCINTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ScintlingEntity::canScintlingSpawn);
        SpawnPlacements.register(GLOOMPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(STONEBORN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StonebornEntity::canStonebornSpawn);
        SpawnPlacements.register(NARGOYLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernEntity::canCreatureSpawn);
        SpawnPlacements.register(MUNCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernEntity::canCreatureSpawn);
        SpawnPlacements.register(SPLOOGIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CavernEntity::canCreatureSpawn);
        SpawnPlacements.register(GWIB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GwibEntity::canGwibSpawn);
        SpawnPlacements.register(MOG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ROTLING.get(), RotlingEntity.registerAttributes().build());
        event.put(ROTWALKER.get(), RotwalkerEntity.registerAttributes().build());
        event.put(ROTBEAST.get(), RotbeastEntity.registerAttributes().build());
        event.put(DWELLER.get(), DwellerEntity.registerAttributes().build());
        event.put(GWIBLING.get(), AbstractFish.createAttributes().build());
        event.put(BRUTE.get(), BruteEntity.registerAttributes().build());
        event.put(SCINTLING.get(), ScintlingEntity.registerAttributes().build());
        event.put(GLOOMPER.get(), GloomperEntity.registerAttributes().build());
        event.put(STONEBORN.get(), StonebornEntity.registerAttributes().build());
        event.put(MASTICATOR.get(), MasticatorEntity.registerAttributes().build());
        event.put(NARGOYLE.get(), NargoyleEntity.registerAttributes().build());
        event.put(FORGOTTEN_GUARDIAN.get(), ForgottenGuardianEntity.registerAttributes().build());
        event.put(MUNCHER.get(), MuncherEntity.registerAttributes().build());
        event.put(SPLOOGIE.get(), SploogieEntity.registerAttributes().build());
        event.put(MINION.get(), MinionEntity.registerAttributes().build());
        event.put(GWIB.get(), GwibEntity.registerAttributes().build());
        event.put(MOG.get(), MogEntity.registerAttributes().build());
    }
}