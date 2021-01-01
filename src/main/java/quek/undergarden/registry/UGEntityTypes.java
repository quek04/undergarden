package quek.undergarden.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UGMod;
import quek.undergarden.entity.*;
import quek.undergarden.entity.boss.*;
import quek.undergarden.entity.cavern.AbstractCavernCreatureEntity;
import quek.undergarden.entity.cavern.MuncherEntity;
import quek.undergarden.entity.cavern.NargoyleEntity;
import quek.undergarden.entity.cavern.SploogieEntity;
import quek.undergarden.entity.projectile.*;
import quek.undergarden.entity.rotspawn.*;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class UGEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, UGMod.MODID);

    //misc
    public static final EntityType<UGBoatEntity> BOAT_TYPE = EntityType.Builder.<UGBoatEntity>create(UGBoatEntity::new, EntityClassification.MISC)
            .size(1.375F, 0.5625F).trackingRange(10).build("boat");
    public static final RegistryObject<EntityType<UGBoatEntity>> BOAT = ENTITIES.register("boat", () -> BOAT_TYPE);
    public static final EntityType<SlingshotAmmoEntity> SLINGSHOT_AMMO_TYPE = EntityType.Builder.<SlingshotAmmoEntity>create(SlingshotAmmoEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("slingshot_ammo");
    public static final RegistryObject<EntityType<SlingshotAmmoEntity>> SLINGSHOT_AMMO = ENTITIES.register("slingshot_ammo", () -> SLINGSHOT_AMMO_TYPE);
    public static final EntityType<GooBallEntity> GOO_BALL_TYPE = EntityType.Builder.<GooBallEntity>create(GooBallEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("goo_ball");
    public static final RegistryObject<EntityType<GooBallEntity>> GOO_BALL = ENTITIES.register("goo_ball", () -> GOO_BALL_TYPE);
    public static final EntityType<RottenBlisterberryEntity> ROTTEN_BLISTERBERRY_TYPE = EntityType.Builder.<RottenBlisterberryEntity>create(RottenBlisterberryEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("rotten_blisterberry");
    public static final RegistryObject<EntityType<RottenBlisterberryEntity>> ROTTEN_BLISTERBERRY = ENTITIES.register("rotten_blisterberry", () -> ROTTEN_BLISTERBERRY_TYPE);
    public static final EntityType<BlisterbombEntity> BLISTERBOMB_TYPE = EntityType.Builder.<BlisterbombEntity>create(BlisterbombEntity::new, EntityClassification.MISC)
            .size(0.5F, 0.5F).build("blisterbomb");
    public static final RegistryObject<EntityType<BlisterbombEntity>> BLISTERBOMB = ENTITIES.register("blisterbomb", () -> BLISTERBOMB_TYPE);
    public static final EntityType<MinionProjectileEntity> MINION_PROJECTILE_TYPE = EntityType.Builder.<MinionProjectileEntity>create(MinionProjectileEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("minion_projectile");
    public static final RegistryObject<EntityType<MinionProjectileEntity>> MINION_PROJECTILE = ENTITIES.register("minion_projectile", () -> MINION_PROJECTILE_TYPE);
    public static final EntityType<MinionEntity> MINION_TYPE = EntityType.Builder.create(MinionEntity::new, EntityClassification.MISC)
            .size(0.8F, 1.2F).build("minion");
    public static final RegistryObject<EntityType<MinionEntity>> MINION = ENTITIES.register("minion", () -> MINION_TYPE);

    //normal
    public static final EntityType<RotlingEntity> ROTLING_TYPE = EntityType.Builder.create(RotlingEntity::new, EntityClassification.MONSTER)
            .size(0.6F, 1.0F).build("rotling");
    public static final RegistryObject<EntityType<RotlingEntity>> ROTLING = ENTITIES.register("rotling", () -> ROTLING_TYPE);
    public static final EntityType<RotwalkerEntity> ROTWALKER_TYPE = EntityType.Builder.create(RotwalkerEntity::new, EntityClassification.MONSTER)
            .size(0.8F,2.5F).build("rotwalker");
    public static final RegistryObject<EntityType<RotwalkerEntity>> ROTWALKER = ENTITIES.register("rotwalker", () -> ROTWALKER_TYPE);
    public static final EntityType<RotbeastEntity> ROTBEAST_TYPE = EntityType.Builder.create(RotbeastEntity::new, EntityClassification.MONSTER)
            .size(0.9F,3).build("rotbeast");
    public static final RegistryObject<EntityType<RotbeastEntity>> ROTBEAST = ENTITIES.register("rotbeast", () -> ROTBEAST_TYPE);
    public static final EntityType<DwellerEntity> DWELLER_TYPE = EntityType.Builder.create(DwellerEntity::new, EntityClassification.CREATURE)
            .size(0.9F,1.8F).build("dweller");
    public static final RegistryObject<EntityType<DwellerEntity>> DWELLER = ENTITIES.register("dweller", () -> DWELLER_TYPE);
    public static final EntityType<RotDwellerEntity> ROTDWELLER_TYPE = EntityType.Builder.create(RotDwellerEntity::new, EntityClassification.MONSTER)
            .size(0.9F, 1.8F).build("rotdweller");
    public static final RegistryObject<EntityType<RotDwellerEntity>> ROTDWELLER = ENTITIES.register("rotdweller", () -> ROTDWELLER_TYPE);
    public static final EntityType<GwiblingEntity> GWIBLING_TYPE = EntityType.Builder.create(GwiblingEntity::new, EntityClassification.WATER_AMBIENT)
            .size(.5F, .5F).build("gwibling");
    public static final RegistryObject<EntityType<GwiblingEntity>> GWIBLING = ENTITIES.register("gwibling", () -> GWIBLING_TYPE);
    public static final EntityType<BruteEntity> BRUTE_TYPE = EntityType.Builder.create(BruteEntity::new, EntityClassification.MONSTER)
            .size(0.9F, 2).build("brute");
    public static final RegistryObject<EntityType<BruteEntity>> BRUTE = ENTITIES.register("brute", () -> BRUTE_TYPE);
    public static final EntityType<ScintlingEntity> SCINTLING_TYPE = EntityType.Builder.create(ScintlingEntity::new, EntityClassification.AMBIENT)
            .size(0.9F, .5F).build("scintling");
    public static final RegistryObject<EntityType<ScintlingEntity>> SCINTLING = ENTITIES.register("scintling", () -> SCINTLING_TYPE);
    public static final EntityType<GloomperEntity> GLOOMPER_TYPE = EntityType.Builder.create(GloomperEntity::new, EntityClassification.CREATURE)
            .size(0.99F, 0.99F).build("gloomper");
    public static final RegistryObject<EntityType<GloomperEntity>> GLOOMPER = ENTITIES.register("gloomper", () -> GLOOMPER_TYPE);
    public static final EntityType<StonebornEntity> STONEBORN_TYPE = EntityType.Builder.create(StonebornEntity::new, EntityClassification.MONSTER)
            .size(0.9F, 2.6F).build("stoneborn");
    public static final RegistryObject<EntityType<StonebornEntity>> STONEBORN = ENTITIES.register("stoneborn", () -> STONEBORN_TYPE);
    public static final EntityType<NargoyleEntity> NARGOYLE_TYPE = EntityType.Builder.create(NargoyleEntity::new, EntityClassification.MONSTER)
            .size(0.99F, 1.5F).build("nargoyle");
    public static final RegistryObject<EntityType<NargoyleEntity>> NARGOYLE = ENTITIES.register("nargoyle", () -> NARGOYLE_TYPE);
    public static final EntityType<MuncherEntity> MUNCHER_TYPE = EntityType.Builder.create(MuncherEntity::new, EntityClassification.MONSTER)
            .size(0.8F, 0.8F).build("muncher");
    public static final RegistryObject<EntityType<MuncherEntity>> MUNCHER = ENTITIES.register("muncher", () -> MUNCHER_TYPE);
    public static final EntityType<SploogieEntity> SPLOOGIE_TYPE = EntityType.Builder.create(SploogieEntity::new, EntityClassification.MONSTER)
            .size(0.8F, 0.8F).build("sploogie");
    public static final RegistryObject<EntityType<SploogieEntity>> SPLOOGIE = ENTITIES.register("sploogie", () -> SPLOOGIE_TYPE);

    //bosses
    public static final EntityType<MasticatorEntity> MASTICATOR_TYPE = EntityType.Builder.create(MasticatorEntity::new, EntityClassification.MONSTER)
            .size(2.5F, 4).build("masticator");
    public static final RegistryObject<EntityType<MasticatorEntity>> MASTICATOR = ENTITIES.register("masticator", () -> MASTICATOR_TYPE);
    public static final EntityType<ForgottenGuardianEntity> FORGOTTEN_GUARDIAN_TYPE = EntityType.Builder.create(ForgottenGuardianEntity::new, EntityClassification.MONSTER)
            .size(0.8F, 3.5F).build("forgotten_guardian");
    public static final RegistryObject<EntityType<ForgottenGuardianEntity>> FORGOTTEN_GUARDIAN = ENTITIES.register("forgotten_guardian", () -> FORGOTTEN_GUARDIAN_TYPE);

    public static void spawnPlacements() {
        EntitySpawnPlacementRegistry.register(GWIBLING.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GwiblingEntity::canGwiblingSpawn);
        EntitySpawnPlacementRegistry.register(DWELLER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DwellerEntity::canDwellerSpawn);
        EntitySpawnPlacementRegistry.register(ROTLING.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        EntitySpawnPlacementRegistry.register(ROTWALKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        EntitySpawnPlacementRegistry.register(ROTBEAST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        EntitySpawnPlacementRegistry.register(BRUTE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BruteEntity::canBruteSpawn);
        EntitySpawnPlacementRegistry.register(SCINTLING.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ScintlingEntity::canScintlingSpawn);
        EntitySpawnPlacementRegistry.register(GLOOMPER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GloomperEntity::canGloomperSpawn);
        EntitySpawnPlacementRegistry.register(STONEBORN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, StonebornEntity::canStonebornSpawn);
        EntitySpawnPlacementRegistry.register(NARGOYLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractCavernCreatureEntity::canCreatureSpawn);
        EntitySpawnPlacementRegistry.register(MUNCHER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractCavernCreatureEntity::canCreatureSpawn);
        EntitySpawnPlacementRegistry.register(SPLOOGIE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractCavernCreatureEntity::canCreatureSpawn);
    }

    public static void entityAttributes() {
        GlobalEntityTypeAttributes.put(ROTLING.get(), RotlingEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ROTWALKER.get(), RotwalkerEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ROTBEAST.get(), RotbeastEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(DWELLER.get(), DwellerEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ROTDWELLER.get(), RotDwellerEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(GWIBLING.get(), AbstractFishEntity.func_234176_m_().create());
        GlobalEntityTypeAttributes.put(BRUTE.get(), BruteEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(SCINTLING.get(), ScintlingEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(GLOOMPER.get(), GloomperEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(STONEBORN.get(), StonebornEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MASTICATOR.get(), MasticatorEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(NARGOYLE.get(), NargoyleEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(FORGOTTEN_GUARDIAN.get(), ForgottenGuardianEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MUNCHER.get(), MuncherEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(SPLOOGIE.get(), SploogieEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MINION.get(), MinionEntity.registerAttributes().create());
    }
}