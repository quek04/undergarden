package quek.undergarden.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.Boomgourd;
import quek.undergarden.entity.Minion;
import quek.undergarden.entity.animal.*;
import quek.undergarden.entity.animal.dweller.Dweller;
import quek.undergarden.entity.monster.Forgotten;
import quek.undergarden.entity.monster.boss.ForgottenGuardian;
import quek.undergarden.entity.monster.cavern.CavernMonster;
import quek.undergarden.entity.monster.cavern.Muncher;
import quek.undergarden.entity.monster.cavern.Nargoyle;
import quek.undergarden.entity.monster.cavern.Sploogie;
import quek.undergarden.entity.monster.denizen.Denizen;
import quek.undergarden.entity.monster.rotspawn.*;
import quek.undergarden.entity.monster.stoneborn.Stoneborn;
import quek.undergarden.entity.projectile.Blisterbomb;
import quek.undergarden.entity.projectile.MinionProjectile;
import quek.undergarden.entity.projectile.RotbelcherProjectile;
import quek.undergarden.entity.projectile.ThrownJavelin;
import quek.undergarden.entity.projectile.slingshot.SlingshotProjectile;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class UGEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Undergarden.MODID);
	public static final Map<Holder<EntityType<?>>, Supplier<AttributeSupplier.Builder>> ATTRIBUTES = new HashMap<>();
	public static final Map<Holder<EntityType<?>>, SpawnPlacementInfo<?>> SPAWN_PREDICATES = new HashMap<>();

	//misc
	public static final DeferredHolder<EntityType<?>, EntityType<Boomgourd>> BOOMGOURD = registerMisc("boomgourd", EntityType.Builder.<Boomgourd>of(Boomgourd::new, MobCategory.MISC).fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<SlingshotProjectile>> SLINGSHOT_PROJECTILE = registerMisc("slingshot_projectile", EntityType.Builder.<SlingshotProjectile>of(SlingshotProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F));
	public static final DeferredHolder<EntityType<?>, EntityType<Blisterbomb>> BLISTERBOMB = registerMisc("blisterbomb", EntityType.Builder.<Blisterbomb>of(Blisterbomb::new, MobCategory.MISC).sized(0.5F, 0.5F));
	public static final DeferredHolder<EntityType<?>, EntityType<ThrownJavelin>> JAVELIN = registerMisc("javelin", EntityType.Builder.<ThrownJavelin>of(ThrownJavelin::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
	public static final DeferredHolder<EntityType<?>, EntityType<MinionProjectile>> MINION_PROJECTILE = registerMisc("minion_projectile", EntityType.Builder.<MinionProjectile>of(MinionProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F));
	public static final DeferredHolder<EntityType<?>, EntityType<Minion>> MINION = registerMisc("minion", EntityType.Builder.of(Minion::new, MobCategory.MISC).sized(1.0F, 1.6F).eyeHeight(1.0F));
	public static final DeferredHolder<EntityType<?>, EntityType<RotbelcherProjectile>> ROTBELCHER_PROJECTILE = registerMisc("rotbelcher_projectile", EntityType.Builder.<RotbelcherProjectile>of(RotbelcherProjectile::new, MobCategory.MISC).sized(0.5F, 0.5F));

	//boats
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> WIGGLEWOOD_BOAT = registerMisc("wigglewood_boat", EntityType.Builder.of(boatFactory(UGItems.WIGGLEWOOD_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> WIGGLEWOOD_CHEST_BOAT = registerMisc("wigglewood_chest_boat", EntityType.Builder.of(chestBoatFactory(UGItems.WIGGLEWOOD_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> SMOGSTEM_BOAT = registerMisc("smogstem_boat", EntityType.Builder.of(boatFactory(UGItems.SMOGSTEM_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> SMOGSTEM_CHEST_BOAT = registerMisc("smogstem_chest_boat", EntityType.Builder.of(chestBoatFactory(UGItems.SMOGSTEM_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> GRONGLE_BOAT = registerMisc("grongle_boat", EntityType.Builder.of(boatFactory(UGItems.GRONGLE_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> GRONGLE_CHEST_BOAT = registerMisc("grongle_chest_boat", EntityType.Builder.of(chestBoatFactory(UGItems.GRONGLE_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> ANCIENT_ROOT_BOAT = registerMisc("ancient_root_boat", EntityType.Builder.of(boatFactory(UGItems.ANCIENT_ROOT_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> ANCIENT_ROOT_CHEST_BOAT = registerMisc("ancient_root_chest_boat", EntityType.Builder.of(chestBoatFactory(UGItems.ANCIENT_ROOT_CHEST_BOAT), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));

	//normal
	public static final DeferredHolder<EntityType<?>, EntityType<Rotling>> ROTLING = registerWithEgg("rotling", EntityType.Builder.of(Rotling::new, MobCategory.MONSTER).notInPeaceful().sized(0.6F, 1.0F), Rotling::registerAttributes, new SpawnPlacementInfo<>(RotspawnMonster::canRotspawnSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Rotwalker>> ROTWALKER = registerWithEgg("rotwalker", EntityType.Builder.of(Rotwalker::new, MobCategory.MONSTER).notInPeaceful().sized(0.8F, 2.5F), Rotwalker::registerAttributes, new SpawnPlacementInfo<>(RotspawnMonster::canRotspawnSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Rotbeast>> ROTBEAST = registerWithEgg("rotbeast", EntityType.Builder.of(Rotbeast::new, MobCategory.MONSTER).notInPeaceful().sized(1.5F, 3.0F), Rotbeast::registerAttributes, new SpawnPlacementInfo<>(RotspawnMonster::canRotspawnSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Rotbelcher>> ROTBELCHER = registerWithEgg("rotbelcher", EntityType.Builder.of(Rotbelcher::new, MobCategory.MONSTER).notInPeaceful().sized(0.8F, 2.5F), Rotbelcher::registerAttributes, new SpawnPlacementInfo<>(RotspawnMonster::canRotspawnSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Dweller>> DWELLER = registerWithEgg("dweller", EntityType.Builder.of(Dweller::new, MobCategory.CREATURE).sized(1.2F, 1.8F), Dweller::registerAttributes, new SpawnPlacementInfo<>(Animal::checkAnimalSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<GreaterDweller>> GREATER_DWELLER = registerWithEgg("greater_dweller", EntityType.Builder.of(GreaterDweller::new, MobCategory.valueOf("UNDERGARDEN_STUPID_MOB_CATEGORY")).sized(2.0F, 3.0F).eyeHeight(2.1F), GreaterDweller::registerAttributes, new SpawnPlacementInfo<>(GreaterDweller::checkGreaterDwellerSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<Gwibling>> GWIBLING = registerWithEgg("gwibling", EntityType.Builder.of(Gwibling::new, MobCategory.WATER_AMBIENT).sized(.5F, .3F), Gwibling::createAttributes, new SpawnPlacementInfo<>(Gwibling::canGwiblingSpawn, SpawnPlacementTypes.IN_WATER));
	public static final DeferredHolder<EntityType<?>, EntityType<Brute>> BRUTE = registerWithEgg("brute", EntityType.Builder.of(Brute::new, MobCategory.CREATURE).sized(1.0F, 2).eyeHeight(1.9F), Brute::registerAttributes, new SpawnPlacementInfo<>(Animal::checkAnimalSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<Scintling>> SCINTLING = registerWithEgg("scintling", EntityType.Builder.of(Scintling::new, MobCategory.AMBIENT).sized(1.0F, 0.5F), Scintling::registerAttributes, new SpawnPlacementInfo<>(Scintling::canScintlingSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Gloomper>> GLOOMPER = registerWithEgg("gloomper", EntityType.Builder.of(Gloomper::new, MobCategory.CREATURE).sized(0.99F, 0.99F), Gloomper::registerAttributes, new SpawnPlacementInfo<>(Animal::checkAnimalSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<Stoneborn>> STONEBORN = registerWithEgg("stoneborn", EntityType.Builder.of(Stoneborn::new, MobCategory.MONSTER).sized(1.0F, 2.6F), Stoneborn::registerAttributes, new SpawnPlacementInfo<>(Stoneborn::canStonebornSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Nargoyle>> NARGOYLE = registerWithEgg("nargoyle", EntityType.Builder.of(Nargoyle::new, MobCategory.MONSTER).notInPeaceful().sized(1.0F, 1.5F), Nargoyle::registerAttributes, new SpawnPlacementInfo<>(CavernMonster::canCreatureSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Muncher>> MUNCHER = registerWithEgg("muncher", EntityType.Builder.of(Muncher::new, MobCategory.MONSTER).notInPeaceful().sized(0.8F, 0.8F), Muncher::registerAttributes, new SpawnPlacementInfo<>(CavernMonster::canCreatureSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Sploogie>> SPLOOGIE = registerWithEgg("sploogie", EntityType.Builder.of(Sploogie::new, MobCategory.MONSTER).notInPeaceful().sized(0.8F, 0.8F).eyeHeight(0.3F), Sploogie::registerAttributes, new SpawnPlacementInfo<>(CavernMonster::canCreatureSpawn));
	public static final DeferredHolder<EntityType<?>, EntityType<Gwib>> GWIB = registerWithEgg("gwib", EntityType.Builder.of(Gwib::new, MobCategory.WATER_CREATURE).notInPeaceful().sized(1.0F, 0.5F).eyeHeight(0.25F), Gwib::registerAttributes, new SpawnPlacementInfo<>(Gwib::canGwibSpawn, SpawnPlacementTypes.IN_WATER));
	public static final DeferredHolder<EntityType<?>, EntityType<Mog>> MOG = registerWithEgg("mog", EntityType.Builder.of(Mog::new, MobCategory.CREATURE).sized(1.0F, 1.0F).eyeHeight(0.2F), Mog::registerAttributes, new SpawnPlacementInfo<>(Animal::checkAnimalSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<SmogMog>> SMOG_MOG = registerWithEgg("smog_mog", EntityType.Builder.of(SmogMog::new, MobCategory.CREATURE).sized(0.75F, 1.8F).eyeHeight(0.4F), SmogMog::registerAttributes, new SpawnPlacementInfo<>(SmogMog::checkSmogMogSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<Forgotten>> FORGOTTEN = registerWithEgg("forgotten", EntityType.Builder.of(Forgotten::new, MobCategory.MONSTER).notInPeaceful().sized(0.7F, 2.2F), Forgotten::registerAttributes, new SpawnPlacementInfo<>(Monster::checkAnyLightMonsterSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<Denizen>> DENIZEN = registerWithEgg("denizen", EntityType.Builder.of(Denizen::new, MobCategory.MONSTER).notInPeaceful().sized(0.7F, 2.0F), Denizen::registerAttributes, new SpawnPlacementInfo<>(Monster::checkAnyLightMonsterSpawnRules));
	public static final DeferredHolder<EntityType<?>, EntityType<MysteriousPot>> MYSTERIOUS_POT = registerWithAttributes("mysterious_pot", EntityType.Builder.of(MysteriousPot::new, MobCategory.CREATURE).sized(0.8F, 1.0F), MysteriousPot::registerAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<Undergar>> UNDERGAR = registerWithEgg("undergar", EntityType.Builder.of(Undergar::new, MobCategory.WATER_CREATURE).sized(1.5F, 0.5F).eyeHeight(0.25F), Undergar::createAttributes, new SpawnPlacementInfo<>(Undergar::canUndergarSpawn, SpawnPlacementTypes.IN_WATER));

	//bosses
	public static final DeferredHolder<EntityType<?>, EntityType<ForgottenGuardian>> FORGOTTEN_GUARDIAN = registerWithEgg("forgotten_guardian", EntityType.Builder.of(ForgottenGuardian::new, MobCategory.MONSTER).notInPeaceful().sized(1.0F, 3.8F), ForgottenGuardian::registerAttributes, null);

	private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> boatItem) {
		return (entityType, level) -> new Boat(entityType, level, boatItem);
	}

	private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> dropItem) {
		return (entityType, level) -> new ChestBoat(entityType, level, dropItem);
	}

	public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerMisc(String name, EntityType.Builder<E> builder) {
		return ENTITY_TYPES.register(name, () -> builder.noLootTable().build(createIDFor(name)));
	}

	public static <E extends LivingEntity> DeferredHolder<EntityType<?>, EntityType<E>> registerWithAttributes(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(createIDFor(name)));
		ATTRIBUTES.put(ret, attributes);
		return ret;
	}

	public static <E extends LivingEntity> DeferredHolder<EntityType<?>, EntityType<E>> registerWithPlacement(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes, @Nullable SpawnPlacementInfo<E> info) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = registerWithAttributes(name, builder, attributes);
		if (info != null) {
			SPAWN_PREDICATES.put(ret, info);
		}
		return ret;
	}

	public static <E extends Mob> DeferredHolder<EntityType<?>, EntityType<E>> registerWithEgg(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes, @Nullable SpawnPlacementInfo<E> info) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = registerWithPlacement(name, builder, attributes, info);
		UGItems.register(name + "_spawn_egg", SpawnEggItem::new, () -> new Item.Properties().spawnEgg(ret.get()));
		return ret;
	}

	private static ResourceKey<EntityType<?>> createIDFor(String name) {
		return ResourceKey.create(Registries.ENTITY_TYPE, Undergarden.prefix(name));
	}

	public record SpawnPlacementInfo<E extends Entity>(SpawnPlacements.SpawnPredicate<E> predicate, Heightmap.Types heightmap, SpawnPlacementType placement) {

		public SpawnPlacementInfo(SpawnPlacements.SpawnPredicate<E> predicate) {
			this(predicate, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnPlacementTypes.ON_GROUND);
		}

		public SpawnPlacementInfo(SpawnPlacements.SpawnPredicate<E> predicate, SpawnPlacementType placement) {
			this(predicate, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, placement);
		}
	}
}