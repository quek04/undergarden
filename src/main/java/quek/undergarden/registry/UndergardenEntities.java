package quek.undergarden.registry;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.*;
import quek.undergarden.entity.boss.*;
import quek.undergarden.entity.projectile.*;
import quek.undergarden.entity.rotspawn.*;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class UndergardenEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, UndergardenMod.MODID);

    public static final CreatureAttribute ROTSPAWN = new CreatureAttribute();

    public static final EntityType<SlingshotAmmoEntity> slingshot_ammo = EntityType.Builder.<SlingshotAmmoEntity>create(SlingshotAmmoEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("slingshot_ammo");
    public static final EntityType<GooBallEntity> goo_ball = EntityType.Builder.<GooBallEntity>create(GooBallEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("goo_ball");
    public static final EntityType<RottenBlisterberryEntity> rotten_blisterberry = EntityType.Builder.<RottenBlisterberryEntity>create(RottenBlisterberryEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("rotten_blisterberry");
    public static final EntityType<BlisterbombEntity> blisterbomb = EntityType.Builder.<BlisterbombEntity>create(BlisterbombEntity::new, EntityClassification.MISC)
            .size(0.5F, 0.5F).build("blisterbomb");

    public static final EntityType<RotlingEntity> rotling = EntityType.Builder.create(RotlingEntity::new, EntityClassification.MONSTER)
            .size(0.6F, 1.0F).build("rotling");
    public static final EntityType<RotwalkerEntity> rotwalker = EntityType.Builder.create(RotwalkerEntity::new, EntityClassification.MONSTER)
            .size(0.8f,2.5f).build("rotwalker");
    public static final EntityType<RotbeastEntity> rotbeast = EntityType.Builder.create(RotbeastEntity::new, EntityClassification.MONSTER)
            .size(1.2F,3).build("rotbeast");
    public static final EntityType<DwellerEntity> dweller = EntityType.Builder.create(DwellerEntity::new, EntityClassification.CREATURE)
            .size(1,1.8F).build("dweller");
    public static final EntityType<RotDwellerEntity> rotdweller = EntityType.Builder.create(RotDwellerEntity::new, EntityClassification.MONSTER)
            .size(1, 1.8F).build("rotdweller");
    public static final EntityType<GwiblingEntity> gwibling = EntityType.Builder.create(GwiblingEntity::new, EntityClassification.WATER_CREATURE)
            .size(.5F, .5F).build("gwibling");
    public static final EntityType<BruteEntity> brute = EntityType.Builder.create(BruteEntity::new, EntityClassification.MONSTER)
            .size(1, 2).build("brute");
    public static final EntityType<ScintlingEntity> scintling = EntityType.Builder.create(ScintlingEntity::new, EntityClassification.AMBIENT)
            .size(1, .5F).build("scintling");
    public static final EntityType<BlisterbomberEntity> blisterbomber = EntityType.Builder.create(BlisterbomberEntity::new, EntityClassification.MONSTER)
            .size(1F, 2.5F).build("blisterbomber");
    public static final EntityType<GloomperEntity> gloomper = EntityType.Builder.create(GloomperEntity::new, EntityClassification.CREATURE)
            .size(1F, 1F).build("gloomper");
    public static final EntityType<StonebornEntity> stoneborn = EntityType.Builder.create(StonebornEntity::new, EntityClassification.MONSTER)
            .size(1F, 2.6F).build("stoneborn");

    public static final EntityType<MasticatorEntity> masticator = EntityType.Builder.create(MasticatorEntity::new, EntityClassification.MONSTER)
            .size(2.5F, 4).build("masticator");

    public static final RegistryObject<EntityType<SlingshotAmmoEntity>> SLINGSHOT_AMMO = ENTITIES.register("slingshot_ammo", () -> slingshot_ammo);
    public static final RegistryObject<EntityType<GooBallEntity>> GOO_BALL = ENTITIES.register("goo_ball", () -> goo_ball);
    public static final RegistryObject<EntityType<RottenBlisterberryEntity>> ROTTEN_BLISTERBERRY = ENTITIES.register("rotten_blisterberry", () -> rotten_blisterberry);
    public static final RegistryObject<EntityType<BlisterbombEntity>> BLISTERBOMB = ENTITIES.register("blisterbomb", () -> blisterbomb);

    public static final RegistryObject<EntityType<RotlingEntity>> ROTLING = ENTITIES.register("rotling", () -> rotling);
    public static final RegistryObject<EntityType<RotwalkerEntity>> ROTWALKER = ENTITIES.register("rotwalker", () -> rotwalker);
    public static final RegistryObject<EntityType<RotbeastEntity>> ROTBEAST = ENTITIES.register("rotbeast", () -> rotbeast);
    public static final RegistryObject<EntityType<DwellerEntity>> DWELLER = ENTITIES.register("dweller", () -> dweller);
    public static final RegistryObject<EntityType<RotDwellerEntity>> ROTDWELLER = ENTITIES.register("rotdweller", () -> rotdweller);
    public static final RegistryObject<EntityType<GwiblingEntity>> GWIBLING = ENTITIES.register("gwibling", () -> gwibling);
    public static final RegistryObject<EntityType<BruteEntity>> BRUTE = ENTITIES.register("brute", () -> brute);
    public static final RegistryObject<EntityType<ScintlingEntity>> SCINTLING = ENTITIES.register("scintling", () -> scintling);
    public static final RegistryObject<EntityType<BlisterbomberEntity>> BLISTERBOMBER = ENTITIES.register("blisterbomber", () -> blisterbomber);
    public static final RegistryObject<EntityType<GloomperEntity>> GLOOMPER = ENTITIES.register("gloomper", () -> gloomper);
    public static final RegistryObject<EntityType<StonebornEntity>> STONEBORN = ENTITIES.register("stoneborn", () -> stoneborn);

    //bosses
    public static final RegistryObject<EntityType<MasticatorEntity>> MASTICATOR = ENTITIES.register("masticator", () -> masticator);

    public static void spawnPlacements() {
        EntitySpawnPlacementRegistry.register(GWIBLING.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(DWELLER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DwellerEntity::canDwellerSpawn);
        EntitySpawnPlacementRegistry.register(ROTLING.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        EntitySpawnPlacementRegistry.register(ROTWALKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        EntitySpawnPlacementRegistry.register(ROTBEAST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractRotspawnEntity::canRotspawnSpawn);
        EntitySpawnPlacementRegistry.register(BRUTE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BruteEntity::canBruteSpawn);
        EntitySpawnPlacementRegistry.register(SCINTLING.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ScintlingEntity::canScintlingSpawn);
        EntitySpawnPlacementRegistry.register(BLISTERBOMBER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BlisterbomberEntity::canBlisterbomberSpawn);
        EntitySpawnPlacementRegistry.register(GLOOMPER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GloomperEntity::canGloomperSpawn);
    }

    public static void entityAttributes() {
        GlobalEntityTypeAttributes.put(ROTLING.get(), RotlingEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(ROTWALKER.get(), RotwalkerEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(ROTBEAST.get(), RotbeastEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(DWELLER.get(), DwellerEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(ROTDWELLER.get(), RotDwellerEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(GWIBLING.get(), AbstractFishEntity.func_234176_m_().func_233813_a_());
        GlobalEntityTypeAttributes.put(BRUTE.get(), BruteEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(SCINTLING.get(), ScintlingEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(BLISTERBOMBER.get(), BlisterbomberEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(GLOOMPER.get(), GloomperEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(STONEBORN.get(), StonebornEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(MASTICATOR.get(), MasticatorEntity.registerAttributes().func_233813_a_());
    }

}
