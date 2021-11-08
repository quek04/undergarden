package quek.undergarden.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.*;
import quek.undergarden.entity.boss.*;
import quek.undergarden.entity.cavern.*;
import quek.undergarden.entity.projectile.*;
import quek.undergarden.entity.rotspawn.*;
import quek.undergarden.entity.stoneborn.StonebornEntity;

@Mod.EventBusSubscriber(modid = "undergarden", bus = Mod.EventBusSubscriber.Bus.MOD)
public class UGEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Undergarden.MODID);

    //misc
    public static final EntityType<UGBoatEntity> BOAT_TYPE = EntityType.Builder.<UGBoatEntity>of(UGBoatEntity::new, MobCategory.MISC)
            .sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat");
    public static final RegistryObject<EntityType<UGBoatEntity>> BOAT = ENTITIES.register("boat", () -> BOAT_TYPE);

    public static final EntityType<SlingshotAmmoEntity> SLINGSHOT_AMMO_TYPE = EntityType.Builder.<SlingshotAmmoEntity>of(SlingshotAmmoEntity::new, MobCategory.MISC)
            .sized(0.25F, 0.25F).build("slingshot_ammo");
    public static final RegistryObject<EntityType<SlingshotAmmoEntity>> SLINGSHOT_AMMO = ENTITIES.register("slingshot_ammo", () -> SLINGSHOT_AMMO_TYPE);

    public static final EntityType<GooBallEntity> GOO_BALL_TYPE = EntityType.Builder.<GooBallEntity>of(GooBallEntity::new, MobCategory.MISC)
            .sized(0.25F, 0.25F).build("goo_ball");
    public static final RegistryObject<EntityType<GooBallEntity>> GOO_BALL = ENTITIES.register("goo_ball", () -> GOO_BALL_TYPE);

    public static final EntityType<RottenBlisterberryEntity> ROTTEN_BLISTERBERRY_TYPE = EntityType.Builder.<RottenBlisterberryEntity>of(RottenBlisterberryEntity::new, MobCategory.MISC)
            .sized(0.25F, 0.25F).build("rotten_blisterberry");
    public static final RegistryObject<EntityType<RottenBlisterberryEntity>> ROTTEN_BLISTERBERRY = ENTITIES.register("rotten_blisterberry", () -> ROTTEN_BLISTERBERRY_TYPE);

    public static final EntityType<BlisterbombEntity> BLISTERBOMB_TYPE = EntityType.Builder.<BlisterbombEntity>of(BlisterbombEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F).build("blisterbomb");
    public static final RegistryObject<EntityType<BlisterbombEntity>> BLISTERBOMB = ENTITIES.register("blisterbomb", () -> BLISTERBOMB_TYPE);

    public static final EntityType<MinionProjectileEntity> MINION_PROJECTILE_TYPE = EntityType.Builder.<MinionProjectileEntity>of(MinionProjectileEntity::new, MobCategory.MISC)
            .sized(0.25F, 0.25F).build("minion_projectile");
    public static final RegistryObject<EntityType<MinionProjectileEntity>> MINION_PROJECTILE = ENTITIES.register("minion_projectile", () -> MINION_PROJECTILE_TYPE);

    public static final EntityType<MinionEntity> MINION_TYPE = EntityType.Builder.of(MinionEntity::new, MobCategory.MISC)
            .sized(1.0F, 1.6F).build("minion");
    public static final RegistryObject<EntityType<MinionEntity>> MINION = ENTITIES.register("minion", () -> MINION_TYPE);

    //normal
    public static final EntityType<RotlingEntity> ROTLING_TYPE = EntityType.Builder.of(RotlingEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.0F).build("rotling");
    public static final RegistryObject<EntityType<RotlingEntity>> ROTLING = ENTITIES.register("rotling", () -> ROTLING_TYPE);

    public static final EntityType<RotwalkerEntity> ROTWALKER_TYPE = EntityType.Builder.of(RotwalkerEntity::new, MobCategory.MONSTER)
            .sized(0.8F,2.5F).build("rotwalker");
    public static final RegistryObject<EntityType<RotwalkerEntity>> ROTWALKER = ENTITIES.register("rotwalker", () -> ROTWALKER_TYPE);

    public static final EntityType<RotbeastEntity> ROTBEAST_TYPE = EntityType.Builder.of(RotbeastEntity::new, MobCategory.MONSTER)
            .sized(2.0F,3.0F).build("rotbeast");
    public static final RegistryObject<EntityType<RotbeastEntity>> ROTBEAST = ENTITIES.register("rotbeast", () -> ROTBEAST_TYPE);

    public static final EntityType<DwellerEntity> DWELLER_TYPE = EntityType.Builder.of(DwellerEntity::new, MobCategory.CREATURE)
            .sized(1.2F,1.8F).build("dweller");
    public static final RegistryObject<EntityType<DwellerEntity>> DWELLER = ENTITIES.register("dweller", () -> DWELLER_TYPE);

    public static final EntityType<GwiblingEntity> GWIBLING_TYPE = EntityType.Builder.of(GwiblingEntity::new, MobCategory.WATER_AMBIENT)
            .sized(.5F, .5F).build("gwibling");
    public static final RegistryObject<EntityType<GwiblingEntity>> GWIBLING = ENTITIES.register("gwibling", () -> GWIBLING_TYPE);

    public static final EntityType<BruteEntity> BRUTE_TYPE = EntityType.Builder.of(BruteEntity::new, MobCategory.CREATURE)
            .sized(1.0F, 2).build("brute");
    public static final RegistryObject<EntityType<BruteEntity>> BRUTE = ENTITIES.register("brute", () -> BRUTE_TYPE);

    public static final EntityType<ScintlingEntity> SCINTLING_TYPE = EntityType.Builder.of(ScintlingEntity::new, MobCategory.AMBIENT)
            .sized(1.0F, .5F).build("scintling");
    public static final RegistryObject<EntityType<ScintlingEntity>> SCINTLING = ENTITIES.register("scintling", () -> SCINTLING_TYPE);

    public static final EntityType<GloomperEntity> GLOOMPER_TYPE = EntityType.Builder.of(GloomperEntity::new, MobCategory.CREATURE)
            .sized(0.99F, 0.99F).build("gloomper");
    public static final RegistryObject<EntityType<GloomperEntity>> GLOOMPER = ENTITIES.register("gloomper", () -> GLOOMPER_TYPE);

    public static final EntityType<StonebornEntity> STONEBORN_TYPE = EntityType.Builder.of(StonebornEntity::new, MobCategory.MONSTER)
            .sized(1.0F, 2.6F).build("stoneborn");
    public static final RegistryObject<EntityType<StonebornEntity>> STONEBORN = ENTITIES.register("stoneborn", () -> STONEBORN_TYPE);

    public static final EntityType<NargoyleEntity> NARGOYLE_TYPE = EntityType.Builder.of(NargoyleEntity::new, MobCategory.MONSTER)
            .sized(1.0F, 1.5F).build("nargoyle");
    public static final RegistryObject<EntityType<NargoyleEntity>> NARGOYLE = ENTITIES.register("nargoyle", () -> NARGOYLE_TYPE);

    public static final EntityType<MuncherEntity> MUNCHER_TYPE = EntityType.Builder.of(MuncherEntity::new, MobCategory.MONSTER)
            .sized(0.8F, 0.8F).build("muncher");
    public static final RegistryObject<EntityType<MuncherEntity>> MUNCHER = ENTITIES.register("muncher", () -> MUNCHER_TYPE);

    public static final EntityType<SploogieEntity> SPLOOGIE_TYPE = EntityType.Builder.of(SploogieEntity::new, MobCategory.MONSTER)
            .sized(0.8F, 0.8F).build("sploogie");
    public static final RegistryObject<EntityType<SploogieEntity>> SPLOOGIE = ENTITIES.register("sploogie", () -> SPLOOGIE_TYPE);

    public static final EntityType<GwibEntity> GWIB_TYPE = EntityType.Builder.of(GwibEntity::new, MobCategory.WATER_CREATURE)
            .sized(1.5F, 1.5F).build("gwib");
    public static final RegistryObject<EntityType<GwibEntity>> GWIB = ENTITIES.register("gwib", () -> GWIB_TYPE);

    public static final EntityType<MogEntity> MOG_TYPE = EntityType.Builder.of(MogEntity::new, MobCategory.CREATURE)
            .sized(1.0F, 1.0F).build("mog");
    public static final RegistryObject<EntityType<MogEntity>> MOG = ENTITIES.register("mog", () -> MOG_TYPE);

    //bosses
    public static final EntityType<MasticatorEntity> MASTICATOR_TYPE = EntityType.Builder.of(MasticatorEntity::new, MobCategory.MONSTER)
            .sized(2.5F, 4).build("masticator");
    public static final RegistryObject<EntityType<MasticatorEntity>> MASTICATOR = ENTITIES.register("masticator", () -> MASTICATOR_TYPE);

    public static final EntityType<ForgottenGuardianEntity> FORGOTTEN_GUARDIAN_TYPE = EntityType.Builder.of(ForgottenGuardianEntity::new, MobCategory.MONSTER)
            .sized(1.0F, 3.8F).build("forgotten_guardian");
    public static final RegistryObject<EntityType<ForgottenGuardianEntity>> FORGOTTEN_GUARDIAN = ENTITIES.register("forgotten_guardian", () -> FORGOTTEN_GUARDIAN_TYPE);

    public static void spawnPlacements() {
        SpawnPlacements.register(GWIBLING.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GwiblingEntity::canGwiblingSpawn);
        SpawnPlacements.register(DWELLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DwellerEntity::canDwellerSpawn);
        SpawnPlacements.register(ROTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        SpawnPlacements.register(ROTWALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        SpawnPlacements.register(ROTBEAST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        SpawnPlacements.register(BRUTE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BruteEntity::canBruteSpawn);
        SpawnPlacements.register(SCINTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ScintlingEntity::canScintlingSpawn);
        SpawnPlacements.register(GLOOMPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GloomperEntity::canGloomperSpawn);
        SpawnPlacements.register(STONEBORN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StonebornEntity::canStonebornSpawn);
        SpawnPlacements.register(NARGOYLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCavernCreatureEntity::canCreatureSpawn);
        SpawnPlacements.register(MUNCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCavernCreatureEntity::canCreatureSpawn);
        SpawnPlacements.register(SPLOOGIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractCavernCreatureEntity::canCreatureSpawn);
        SpawnPlacements.register(GWIB.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GwibEntity::canGwibSpawn);
        SpawnPlacements.register(MOG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MogEntity::canMogSpawn);
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