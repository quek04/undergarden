package quek.undergarden.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.*;
import quek.undergarden.entity.animal.*;
import quek.undergarden.entity.animal.dweller.Dweller;
import quek.undergarden.entity.boss.ForgottenGuardian;
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
import quek.undergarden.entity.rotspawn.Rotwalker;
import quek.undergarden.entity.stoneborn.Stoneborn;

public class UGEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Undergarden.MODID);

	//misc
	public static final DeferredHolder<EntityType<?>, EntityType<UGBoat>> BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<UGBoat>of(UGBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
	public static final DeferredHolder<EntityType<?>, EntityType<UGChestBoat>> CHEST_BOAT = ENTITIES.register("chest_boat", () -> EntityType.Builder.<UGChestBoat>of(UGChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("chest_boat"));
	public static final DeferredHolder<EntityType<?>, EntityType<Boomgourd>> BOOMGOURD = ENTITIES.register("boomgourd", () -> EntityType.Builder.<Boomgourd>of(Boomgourd::new, MobCategory.MISC).fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10).updateInterval(10).build("boomgourd"));
	public static final DeferredHolder<EntityType<?>, EntityType<DepthrockPebble>> DEPTHROCK_PEBBLE = ENTITIES.register("depthrock_pebble", () -> EntityType.Builder.<DepthrockPebble>of(DepthrockPebble::new, MobCategory.MISC).sized(0.25F, 0.25F).build("depthrock_pebble"));
	public static final DeferredHolder<EntityType<?>, EntityType<GooBall>> GOO_BALL = ENTITIES.register("goo_ball", () -> EntityType.Builder.<GooBall>of(GooBall::new, MobCategory.MISC).sized(0.25F, 0.25F).build("goo_ball"));
	public static final DeferredHolder<EntityType<?>, EntityType<RottenBlisterberry>> ROTTEN_BLISTERBERRY = ENTITIES.register("rotten_blisterberry", () -> EntityType.Builder.<RottenBlisterberry>of(RottenBlisterberry::new, MobCategory.MISC).sized(0.25F, 0.25F).build("rotten_blisterberry"));
	public static final DeferredHolder<EntityType<?>, EntityType<Blisterbomb>> BLISTERBOMB = ENTITIES.register("blisterbomb", () -> EntityType.Builder.<Blisterbomb>of(Blisterbomb::new, MobCategory.MISC).sized(0.5F, 0.5F).build("blisterbomb"));
	public static final DeferredHolder<EntityType<?>, EntityType<Gronglet>> GRONGLET = ENTITIES.register("gronglet", () -> EntityType.Builder.<Gronglet>of(Gronglet::new, MobCategory.MISC).sized(0.5F, 0.5F).build("gronglet"));
	public static final DeferredHolder<EntityType<?>, EntityType<MinionProjectile>> MINION_PROJECTILE = ENTITIES.register("minion_projectile", () -> EntityType.Builder.<MinionProjectile>of(MinionProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F).build("minion_projectile"));
	public static final DeferredHolder<EntityType<?>, EntityType<Minion>> MINION = ENTITIES.register("minion", () -> EntityType.Builder.of(Minion::new, MobCategory.MISC).sized(1.0F, 1.6F).eyeHeight(1.0F).build("minion"));

	//normal
	public static final DeferredHolder<EntityType<?>, EntityType<Rotling>> ROTLING = ENTITIES.register("rotling", () -> EntityType.Builder.of(Rotling::new, MobCategory.MONSTER).sized(0.6F, 1.0F).build("rotling"));
	public static final DeferredHolder<EntityType<?>, EntityType<Rotwalker>> ROTWALKER = ENTITIES.register("rotwalker", () -> EntityType.Builder.of(Rotwalker::new, MobCategory.MONSTER).sized(0.8F, 2.5F).build("rotwalker"));
	public static final DeferredHolder<EntityType<?>, EntityType<Rotbeast>> ROTBEAST = ENTITIES.register("rotbeast", () -> EntityType.Builder.of(Rotbeast::new, MobCategory.MONSTER).sized(1.5F, 3.0F).build("rotbeast"));
	public static final DeferredHolder<EntityType<?>, EntityType<Dweller>> DWELLER = ENTITIES.register("dweller", () -> EntityType.Builder.of(Dweller::new, MobCategory.CREATURE).sized(1.2F, 1.8F).build("dweller"));
	public static final DeferredHolder<EntityType<?>, EntityType<Gwibling>> GWIBLING = ENTITIES.register("gwibling", () -> EntityType.Builder.of(Gwibling::new, MobCategory.WATER_AMBIENT).sized(.5F, .3F).build("gwibling"));
	public static final DeferredHolder<EntityType<?>, EntityType<Brute>> BRUTE = ENTITIES.register("brute", () -> EntityType.Builder.of(Brute::new, MobCategory.CREATURE).sized(1.0F, 2).eyeHeight(1.9F).build("brute"));
	public static final DeferredHolder<EntityType<?>, EntityType<Scintling>> SCINTLING = ENTITIES.register("scintling", () -> EntityType.Builder.of(Scintling::new, MobCategory.AMBIENT).sized(1.0F, .5F).build("scintling"));
	public static final DeferredHolder<EntityType<?>, EntityType<Gloomper>> GLOOMPER = ENTITIES.register("gloomper", () -> EntityType.Builder.of(Gloomper::new, MobCategory.CREATURE).sized(0.99F, 0.99F).build("gloomper"));
	public static final DeferredHolder<EntityType<?>, EntityType<Stoneborn>> STONEBORN = ENTITIES.register("stoneborn", () -> EntityType.Builder.of(Stoneborn::new, MobCategory.MONSTER).sized(1.0F, 2.6F).build("stoneborn"));
	public static final DeferredHolder<EntityType<?>, EntityType<Nargoyle>> NARGOYLE = ENTITIES.register("nargoyle", () -> EntityType.Builder.of(Nargoyle::new, MobCategory.MONSTER).sized(1.0F, 1.5F).build("nargoyle"));
	public static final DeferredHolder<EntityType<?>, EntityType<Muncher>> MUNCHER = ENTITIES.register("muncher", () -> EntityType.Builder.of(Muncher::new, MobCategory.MONSTER).sized(0.8F, 0.8F).build("muncher"));
	public static final DeferredHolder<EntityType<?>, EntityType<Sploogie>> SPLOOGIE = ENTITIES.register("sploogie", () -> EntityType.Builder.of(Sploogie::new, MobCategory.MONSTER).sized(0.8F, 0.8F).eyeHeight(0.3F).build("sploogie"));
	public static final DeferredHolder<EntityType<?>, EntityType<Gwib>> GWIB = ENTITIES.register("gwib", () -> EntityType.Builder.of(Gwib::new, MobCategory.WATER_CREATURE).sized(1.0F, 0.5F).eyeHeight(0.25F).build("gwib"));
	public static final DeferredHolder<EntityType<?>, EntityType<Mog>> MOG = ENTITIES.register("mog", () -> EntityType.Builder.of(Mog::new, MobCategory.CREATURE).sized(1.0F, 1.0F).eyeHeight(0.2F).build("mog"));
	public static final DeferredHolder<EntityType<?>, EntityType<SmogMog>> SMOG_MOG = ENTITIES.register("smog_mog", () -> EntityType.Builder.of(SmogMog::new, MobCategory.CREATURE).sized(0.75F, 1.8F).build("smog_mog"));
	public static final DeferredHolder<EntityType<?>, EntityType<Forgotten>> FORGOTTEN = ENTITIES.register("forgotten", () -> EntityType.Builder.of(Forgotten::new, MobCategory.MONSTER).sized(0.7F, 2.2F).build("forgotten"));

	//bosses
	public static final DeferredHolder<EntityType<?>, EntityType<ForgottenGuardian>> FORGOTTEN_GUARDIAN = ENTITIES.register("forgotten_guardian", () -> EntityType.Builder.of(ForgottenGuardian::new, MobCategory.MONSTER).sized(1.0F, 3.8F).build("forgotten_guardian"));
}