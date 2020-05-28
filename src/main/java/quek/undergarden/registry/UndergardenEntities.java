package quek.undergarden.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.*;
import quek.undergarden.entity.boss.*;
import quek.undergarden.entity.projectile.*;

public class UndergardenEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, UndergardenMod.MODID);

    public static final EntityType<SlingshotAmmoEntity> slingshot_ammo = EntityType.Builder.<SlingshotAmmoEntity>create(SlingshotAmmoEntity::new, EntityClassification.MISC)
            .size(0.25F, 0.25F).build("slingshot_ammo");

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
    public static final EntityType<SkizSwarmerEntity> skiz_swarmer = EntityType.Builder.create(SkizSwarmerEntity::new, EntityClassification.MONSTER)
            .size(.5F, .5F).build("skiz_swarmer");
    public static final EntityType<BruteEntity> brute = EntityType.Builder.create(BruteEntity::new, EntityClassification.CREATURE)
            .size(1, 2).build("brute");
    public static final EntityType<MasticatorEntity> masticator = EntityType.Builder.create(MasticatorEntity::new, EntityClassification.MONSTER)
            .size(2.5F, 4).build("masticator");
    public static final EntityType<ScintlingEntity> scintling = EntityType.Builder.create(ScintlingEntity::new, EntityClassification.AMBIENT)
            .size(1, .5F).build("scintling");

    public static final RegistryObject<EntityType<SlingshotAmmoEntity>> SLINGSHOT_AMMO = ENTITIES.register("slingshot_ammo", () -> slingshot_ammo);

    public static final RegistryObject<EntityType<RotwalkerEntity>> ROTWALKER = ENTITIES.register("rotwalker", () -> rotwalker);
    public static final RegistryObject<EntityType<RotbeastEntity>> ROTBEAST = ENTITIES.register("rotbeast", () -> rotbeast);
    public static final RegistryObject<EntityType<DwellerEntity>> DWELLER = ENTITIES.register("dweller", () -> dweller);
    public static final RegistryObject<EntityType<RotDwellerEntity>> ROTDWELLER = ENTITIES.register("rotdweller", () -> rotdweller);
    public static final RegistryObject<EntityType<GwiblingEntity>> GWIBLING = ENTITIES.register("gwibling", () -> gwibling);
    public static final RegistryObject<EntityType<SkizSwarmerEntity>> SKIZ_SWARMER = ENTITIES.register("skiz_swarmer", () -> skiz_swarmer);
    public static final RegistryObject<EntityType<BruteEntity>> BRUTE = ENTITIES.register("brute", () -> brute);
    public static final RegistryObject<EntityType<ScintlingEntity>> SCINTLING = ENTITIES.register("scintling", () -> scintling);

    //bosses
    public static final RegistryObject<EntityType<MasticatorEntity>> MASTICATOR = ENTITIES.register("masticator", () -> masticator);

    public static void spawnPlacements() {
        EntitySpawnPlacementRegistry.register(GWIBLING.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(DWELLER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DwellerEntity::canDwellerSpawn);
        EntitySpawnPlacementRegistry.register(ROTWALKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RotwalkerEntity::canRotwalkerSpawn);
        EntitySpawnPlacementRegistry.register(ROTBEAST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RotbeastEntity::canRotbeastSpawn);
        EntitySpawnPlacementRegistry.register(BRUTE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BruteEntity::canBruteSpawn);
        EntitySpawnPlacementRegistry.register(SCINTLING.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ScintlingEntity::canScintlingSpawn);
    }
}
