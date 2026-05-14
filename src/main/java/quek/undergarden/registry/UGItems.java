package quek.undergarden.registry;

import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;
import quek.undergarden.item.*;
import quek.undergarden.item.armor.CloggrumBootsItem;
import quek.undergarden.item.armor.FroststeelBootsItem;
import quek.undergarden.item.bucket.UGBucketItem;
import quek.undergarden.item.tool.*;
import quek.undergarden.registry.custom.UGSlingshotAmmoSettings;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class UGItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Undergarden.MODID);

	public static final Rarity ROGDORIUM_RARITY = Rarity.valueOf("UNDERGARDEN_ROGDORIUM");
	public static final Rarity FORGOTTEN_RARITY = Rarity.valueOf("UNDERGARDEN_FORGOTTEN");
	public static final Rarity UTHERIUM_RARITY = Rarity.valueOf("UNDERGARDEN_UTHERIUM");

	//discs
	public static final DeferredItem<Item> MAMMOTH_DISC = register("music_disc_mammoth", Item::new, () -> new Item.Properties().rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(UGJukeboxSongs.MAMMOTH));
	public static final DeferredItem<Item> LIMAX_MAXIMUS_DISC = register("music_disc_limax_maximus", Item::new, () -> new Item.Properties().rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(UGJukeboxSongs.LIMAX_MAXIMUS));
	public static final DeferredItem<Item> RELICT_DISC = register("music_disc_relict", Item::new, () -> new Item.Properties().rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(UGJukeboxSongs.RELICT));
	public static final DeferredItem<Item> GLOOMPER_ANTHEM_DISC = register("music_disc_gloomper_anthem", Item::new, () -> new Item.Properties().rarity(Rarity.RARE).stacksTo(1).jukeboxPlayable(UGJukeboxSongs.GLOOMPER_ANTHEM));
	public static final DeferredItem<Item> GLOOMPER_SECRET_DISC = register("music_disc_gloomper_secret", Item::new, () -> new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).jukeboxPlayable(UGJukeboxSongs.GLOOMPER_SECRET));

	//crafting materials
	public static final DeferredItem<Item> FORGOTTEN_UPGRADE_TEMPLATE = register("forgotten_upgrade_smithing_template", ForgottenSmithingTemplateItem::new, () -> new Item.Properties().rarity(FORGOTTEN_RARITY));

	public static final DeferredItem<Item> RAW_CLOGGRUM = register("raw_cloggrum", Item::new, Item.Properties::new);
	public static final DeferredItem<Item> CLOGGRUM_INGOT = register("cloggrum_ingot", Item::new, () -> new Item.Properties().trimMaterial(UGTrimMaterials.CLOGGRUM));
	public static final DeferredItem<Item> CLOGGRUM_NUGGET = register("cloggrum_nugget", Item::new, Item.Properties::new);

	public static final DeferredItem<Item> RAW_FROSTSTEEL = register("raw_froststeel", Item::new, Item.Properties::new);
	public static final DeferredItem<Item> FROSTSTEEL_INGOT = register("froststeel_ingot", Item::new, () -> new Item.Properties().trimMaterial(UGTrimMaterials.FROSTSTEEL));
	public static final DeferredItem<Item> FROSTSTEEL_NUGGET = register("froststeel_nugget", Item::new, Item.Properties::new);

	public static final DeferredItem<Item> UTHERIC_SHARD = register("utheric_shard", Item::new, () -> new Item.Properties().rarity(UTHERIUM_RARITY));
	public static final DeferredItem<Item> UTHERIC_CLUSTER = register("utheric_cluster", Item::new, () -> new Item.Properties().rarity(UTHERIUM_RARITY));
	public static final DeferredItem<Item> UTHERIUM_CRYSTAL = register("utherium_crystal", Item::new, () -> new Item.Properties().rarity(UTHERIUM_RARITY).trimMaterial(UGTrimMaterials.UTHERIUM));

	public static final DeferredItem<Item> REGALIUM_CRYSTAL = register("regalium_crystal", Item::new, () -> new Item.Properties().rarity(Rarity.UNCOMMON).trimMaterial(UGTrimMaterials.REGALIUM));

	public static final DeferredItem<Item> ROGDORIUM = register("rogdorium", (properties) -> new HoverTextItem("tooltip.undergarden.soothes_infection", properties), () -> new Item.Properties().rarity(ROGDORIUM_RARITY).component(DataComponents.CONSUMABLE, UGConsumables.ROGDORIUM).trimMaterial(UGTrimMaterials.ROGDORIUM));
	public static final DeferredItem<Item> ROGDORIUM_NUGGET = register("rogdorium_nugget", (properties) -> new HoverTextItem("tooltip.undergarden.soothes_infection", properties), () -> new Item.Properties().rarity(ROGDORIUM_RARITY).component(DataComponents.CONSUMABLE, UGConsumables.ROGDORIUM_NUGGET));

	public static final DeferredItem<Item> FORGOTTEN_INGOT = register("forgotten_ingot", Item::new, () -> new Item.Properties().rarity(FORGOTTEN_RARITY).trimMaterial(UGTrimMaterials.FORGOTTEN));
	public static final DeferredItem<Item> FORGOTTEN_NUGGET = register("forgotten_nugget", Item::new, () -> new Item.Properties().rarity(FORGOTTEN_RARITY));

	public static final DeferredItem<Item> DEPTHROCK_PEBBLE = register("depthrock_pebble", (properties) -> new BlockItem(UGBlocks.DEPTHROCK_PEBBLES.get(), properties), () -> new Item.Properties().component(UGDataComponents.SLINGSHOT_AMMO.get(), UGSlingshotAmmoSettings.DEPTHROCK_PEBBLE));
	public static final DeferredItem<Item> TWISTYTWIG = register("twistytwig", Item::new, Item.Properties::new);
	public static final DeferredItem<Item> DITCHBULB = register("ditchbulb", (properties) -> new BlockItem(UGBlocks.DITCHBULB_PLANT.get(), properties), Item.Properties::new);
	public static final DeferredItem<Item> DITCHBULB_PASTE = register("ditchbulb_paste", DitchbulbPasteItem::new, Item.Properties::new);
	public static final DeferredItem<Item> BRUTE_TUSK = register("brute_tusk", Item::new, Item.Properties::new);
	public static final DeferredItem<Item> MOGMOSS = register("mogmoss", Item::new, Item.Properties::new);
	public static final DeferredItem<Item> BLUE_MOGMOSS = register("blue_mogmoss", Item::new, Item.Properties::new);
	public static final DeferredItem<Item> THORNREED = register("thornreed", (properties) -> new BlockItem(UGBlocks.THORNREED.get(), properties), Item.Properties::new);

	//tools
	public static final DeferredItem<Item> CLOGGRUM_BATTLEAXE = register("cloggrum_battleaxe", BattleaxeItem::new, () -> BattleaxeItem.createBattleaxeProperties(UGItemTiers.CLOGGRUM, 7, -3.4F, new Item.Properties().rarity(Rarity.EPIC).component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.WHACK, 12)).component(DataComponents.MINIMUM_ATTACK_CHARGE, 1.0F)));
	public static final DeferredItem<Item> CLOGGRUM_SWORD = register("cloggrum_sword", Item::new, () -> new Item.Properties().sword(UGItemTiers.CLOGGRUM, 3, -2.4F));
	public static final DeferredItem<Item> CLOGGRUM_PICKAXE = register("cloggrum_pickaxe", Item::new, () -> new Item.Properties().pickaxe(UGItemTiers.CLOGGRUM, 1, -2.8F));
	public static final DeferredItem<Item> CLOGGRUM_AXE = register("cloggrum_axe", Item::new, () -> new Item.Properties().axe(UGItemTiers.CLOGGRUM, 5.0F, -3.1F));
	public static final DeferredItem<Item> CLOGGRUM_SHOVEL = register("cloggrum_shovel", Item::new, () -> new Item.Properties().shovel(UGItemTiers.CLOGGRUM, 1.5F, -3.0F));
	public static final DeferredItem<Item> CLOGGRUM_HOE = register("cloggrum_hoe", Item::new, () -> new Item.Properties().hoe(UGItemTiers.CLOGGRUM, -3, -1.0F));
	public static final DeferredItem<Item> CLOGGRUM_SHIELD = register("cloggrum_shield", ShieldItem::new, () -> new Item.Properties()
		.durability(672)
		.component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
		.repairable(UGTags.Items.CLOGGRUM_TOOL_MATERIALS)
		.equippableUnswappable(EquipmentSlot.OFFHAND)
		.delayedComponent(
			DataComponents.BLOCKS_ATTACKS,
			context -> new BlocksAttacks(
				0.25F,
				1.0F,
				List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
				new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
				Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
				Optional.of(SoundEvents.SHIELD_BLOCK),
				Optional.of(SoundEvents.SHIELD_BREAK)
			)
		)
		.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));

	public static final DeferredItem<Item> FROSTSTEEL_SWORD = register("froststeel_sword", (properties) -> new HoverTextItem(HoverTextItem.FROSTSTEEL_WEAPON, properties), () -> new Item.Properties().sword(UGItemTiers.FROSTSTEEL, 3, -2.4F));
	public static final DeferredItem<Item> FROSTSTEEL_PICKAXE = register("froststeel_pickaxe", (properties) -> new HoverTextItem(HoverTextItem.FROSTSTEEL_WEAPON, properties), () -> new Item.Properties().pickaxe(UGItemTiers.FROSTSTEEL, 1, -2.8F));
	public static final DeferredItem<Item> FROSTSTEEL_AXE = register("froststeel_axe", (properties) -> new HoverTextItem(HoverTextItem.FROSTSTEEL_WEAPON, properties), () -> new Item.Properties().axe(UGItemTiers.FROSTSTEEL, 6.0F, -3.0F));
	public static final DeferredItem<Item> FROSTSTEEL_SHOVEL = register("froststeel_shovel", (properties) -> new HoverTextItem(HoverTextItem.FROSTSTEEL_WEAPON, properties), () -> new Item.Properties().shovel(UGItemTiers.FROSTSTEEL, 1.5F, -3.0F));
	public static final DeferredItem<Item> FROSTSTEEL_HOE = register("froststeel_hoe", (properties) -> new HoverTextItem(HoverTextItem.FROSTSTEEL_WEAPON, properties), () -> new Item.Properties().hoe(UGItemTiers.FROSTSTEEL, -2, -0.5F));

	public static final DeferredItem<Item> UTHERIUM_SWORD = register("utherium_sword", (properties) -> new HoverTextItem(HoverTextItem.UTHERIUM_WEAPON, properties), () -> new Item.Properties().rarity(UTHERIUM_RARITY).sword(UGItemTiers.UTHERIUM, 3, -2.4F));
	public static final DeferredItem<Item> UTHERIUM_PICKAXE = register("utherium_pickaxe", Item::new, () -> new Item.Properties().rarity(UTHERIUM_RARITY).pickaxe(UGItemTiers.UTHERIUM, 1, -2.8F));
	public static final DeferredItem<Item> UTHERIUM_AXE = register("utherium_axe", (properties) -> new HoverTextItem(HoverTextItem.UTHERIUM_WEAPON, properties), () -> new Item.Properties().rarity(UTHERIUM_RARITY).axe(UGItemTiers.UTHERIUM, 5.0F, -3.0F));
	public static final DeferredItem<Item> UTHERIUM_SHOVEL = register("utherium_shovel", Item::new, () -> new Item.Properties().rarity(UTHERIUM_RARITY).shovel(UGItemTiers.UTHERIUM, 1.5F, -3.0F));
	public static final DeferredItem<Item> UTHERIUM_HOE = register("utherium_hoe", Item::new, () -> new Item.Properties().rarity(UTHERIUM_RARITY).hoe(UGItemTiers.UTHERIUM, -3, 0.0F));

	public static final DeferredItem<Item> FORGOTTEN_BATTLEAXE = register("forgotten_battleaxe", ForgottenBattleaxeItem::new, () -> BattleaxeItem.createBattleaxeProperties(UGItemTiers.FORGOTTEN, 7, -3.4F, new Item.Properties().rarity(Rarity.EPIC).component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.WHACK, 12)).component(DataComponents.MINIMUM_ATTACK_CHARGE, 1.0F)));
	public static final DeferredItem<Item> FORGOTTEN_SWORD = register("forgotten_sword", (properties) -> new HoverTextItem(HoverTextItem.FORGOTTEN_WEAPON, properties), () -> new Item.Properties().sword(UGItemTiers.FORGOTTEN, 3, -2.4F).rarity(FORGOTTEN_RARITY));
	public static final DeferredItem<Item> FORGOTTEN_PICKAXE = register("forgotten_pickaxe", (properties) -> new HoverTextItem(HoverTextItem.FORGOTTEN_TOOL, properties), () -> new Item.Properties().pickaxe(UGItemTiers.FORGOTTEN, 1, -2.8F).rarity(FORGOTTEN_RARITY));
	public static final DeferredItem<Item> FORGOTTEN_AXE = register("forgotten_axe", (properties) -> new HoverTextItem(List.of(HoverTextItem.FORGOTTEN_WEAPON, HoverTextItem.FORGOTTEN_TOOL), properties), () -> new Item.Properties().axe(UGItemTiers.FORGOTTEN, 5.0F, -3.0F).rarity(FORGOTTEN_RARITY));
	public static final DeferredItem<Item> FORGOTTEN_SHOVEL = register("forgotten_shovel", (properties) -> new HoverTextItem(HoverTextItem.FORGOTTEN_TOOL, properties), () -> new Item.Properties().shovel(UGItemTiers.FORGOTTEN, 1.5F, -3.0F).rarity(FORGOTTEN_RARITY));
	public static final DeferredItem<Item> FORGOTTEN_HOE = register("forgotten_hoe", (properties) -> new HoverTextItem(HoverTextItem.FORGOTTEN_TOOL, properties), () -> new Item.Properties().hoe(UGItemTiers.FORGOTTEN, -3, 0.0F).rarity(FORGOTTEN_RARITY));

	//TODO maybe tweak spear values?
	public static final DeferredItem<Item> CLOGGRUM_SPEAR = register("cloggrum_spear", Item::new, () -> new Item.Properties().spear(UGItemTiers.CLOGGRUM, 0.95F, 0.95F, 0.6F, 2.5F, 11.0F, 6.75F, 5.1F, 11.25F, 4.6F));
	public static final DeferredItem<Item> FROSTSTEEL_SPEAR = register("froststeel_spear", (properties -> new HoverTextItem(HoverTextItem.FROSTSTEEL_WEAPON, properties)), () -> new Item.Properties().spear(UGItemTiers.FROSTSTEEL, 0.95F, 0.95F, 0.6F, 2.5F, 11.0F, 6.75F, 5.1F, 11.25F, 4.6F));
	public static final DeferredItem<Item> UTHERIUM_SPEAR = register("utherium_spear", (properties -> new HoverTextItem(HoverTextItem.UTHERIUM_WEAPON, properties)), () -> new Item.Properties().spear(UGItemTiers.UTHERIUM, 1.05F, 1.075F, 0.5F, 3.0F, 10.0F, 6.5F, 5.1F, 10.0F, 4.6F).rarity(UTHERIUM_RARITY));
	public static final DeferredItem<Item> FORGOTTEN_SPEAR = register("forgotten_spear", (properties -> new HoverTextItem(HoverTextItem.FORGOTTEN_WEAPON, properties)), () -> new Item.Properties().spear(UGItemTiers.FORGOTTEN, 1.15F, 1.2F, 0.4F, 2.5F, 9.0F, 5.5F, 5.1F, 8.75F, 4.6F).rarity(FORGOTTEN_RARITY));

	//misc tools
	public static final DeferredItem<Item> CATALYST = register("catalyst", CatalystItem::new, () -> new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
	public static final DeferredItem<Item> CRUMBLING_CATALYST = register("crumbling_catalyst", CrumblingCatalystItem::new, () -> new Item.Properties().durability(1).stacksTo(1).rarity(Rarity.RARE));
	public static final DeferredItem<Item> SLINGSHOT = register("slingshot", SlingshotItem::new, () -> new Item.Properties().durability(192).enchantable(1).repairable(ItemTags.PLANKS).rarity(Rarity.UNCOMMON));
	public static final DeferredItem<Item> JAVELIN = register("javelin", JavelinItem::new, () -> new Item.Properties()
		.delayedHolderComponent(DataComponents.DAMAGE_TYPE, DamageTypes.SPEAR)
		.component(DataComponents.PIERCING_WEAPON, new PiercingWeapon(true, false, Optional.of(SoundEvents.SPEAR_ATTACK), Optional.of(SoundEvents.SPEAR_HIT)))
		.component(DataComponents.ATTACK_RANGE, new AttackRange(2.0F, 4.5F, 2.0F, 6.5F, 0.125F, 0.5F))
		.component(DataComponents.MINIMUM_ATTACK_CHARGE, 1.0F)
		.component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, 20))
		.component(DataComponents.USE_EFFECTS, new UseEffects(false, false, 0.2F))
		.component(DataComponents.WEAPON, new Weapon(1))
		.attributes(
			ItemAttributeModifiers.builder()
				.add(
					Attributes.ATTACK_DAMAGE,
					new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 3.0F, AttributeModifier.Operation.ADD_VALUE),
					EquipmentSlotGroup.MAINHAND
				)
				.add(
					Attributes.ATTACK_SPEED,
					new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -3.0F, AttributeModifier.Operation.ADD_VALUE),
					EquipmentSlotGroup.MAINHAND
				)
				.build()
		)
		.rarity(Rarity.UNCOMMON)
		.stacksTo(16)
	);
	public static final DeferredItem<Item> BLISTERBOMB = register("blisterbomb", BlisterbombItem::new, () -> new Item.Properties().stacksTo(8));
	public static final DeferredItem<Item> UNDERBEAN_STICK = register("underbean_on_a_stick", (properties) -> new UnderbeanOnAStickItem<>(UGEntityTypes.DWELLER.get(), 1, properties), () -> new Item.Properties().stacksTo(1).durability(100));

	public static final DeferredItem<Item> SMOGSTEM_BOAT = register("smogstem_boat", (properties) -> new BoatItem(UGEntityTypes.SMOGSTEM_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> SMOGSTEM_CHEST_BOAT = register("smogstem_chest_boat", (properties) -> new BoatItem(UGEntityTypes.SMOGSTEM_CHEST_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> WIGGLEWOOD_BOAT = register("wigglewood_boat", (properties) -> new BoatItem(UGEntityTypes.WIGGLEWOOD_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> WIGGLEWOOD_CHEST_BOAT = register("wigglewood_chest_boat", (properties) -> new BoatItem(UGEntityTypes.WIGGLEWOOD_CHEST_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> GRONGLE_BOAT = register("grongle_boat", (properties) -> new BoatItem(UGEntityTypes.GRONGLE_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> GRONGLE_CHEST_BOAT = register("grongle_chest_boat", (properties) -> new BoatItem(UGEntityTypes.GRONGLE_CHEST_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> ANCIENT_ROOT_BOAT = register("ancient_root_boat", (properties) -> new BoatItem(UGEntityTypes.ANCIENT_ROOT_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> ANCIENT_ROOT_CHEST_BOAT = register("ancient_root_chest_boat", (properties) -> new BoatItem(UGEntityTypes.ANCIENT_ROOT_CHEST_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> VIRULENT_MIX_BUCKET = register("virulent_mix_bucket", (properties) -> new BucketItem(UGFluids.VIRULENT_MIX_SOURCE.get(), properties), () -> new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET));
	public static final DeferredItem<Item> GWIBLING_BUCKET = register("gwibling_bucket", (properties) -> new MobBucketItem(UGEntityTypes.GWIBLING.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, properties), () -> new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY));

	public static final DeferredItem<Item> CLOGGRUM_BUCKET = register("cloggrum_bucket", UGBucketItem::new, () -> new Item.Properties().component(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY).stacksTo(16));

	//armors
	public static final DeferredItem<Item> CLOGGRUM_HELMET = register("cloggrum_helmet", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.CLOGGRUM, ArmorType.HELMET));
	public static final DeferredItem<Item> CLOGGRUM_CHESTPLATE = register("cloggrum_chestplate", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.CLOGGRUM, ArmorType.CHESTPLATE));
	public static final DeferredItem<Item> CLOGGRUM_LEGGINGS = register("cloggrum_leggings", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.CLOGGRUM, ArmorType.LEGGINGS));
	public static final DeferredItem<Item> CLOGGRUM_BOOTS = register("cloggrum_boots", CloggrumBootsItem::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.CLOGGRUM, ArmorType.BOOTS));

	public static final DeferredItem<Item> FROSTSTEEL_HELMET = register("froststeel_helmet", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.FROSTSTEEL, ArmorType.HELMET).attributes(FroststeelBootsItem.createFroststeelAttributes(ArmorType.HELMET)));
	public static final DeferredItem<Item> FROSTSTEEL_CHESTPLATE = register("froststeel_chestplate", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.FROSTSTEEL, ArmorType.CHESTPLATE).attributes(FroststeelBootsItem.createFroststeelAttributes(ArmorType.CHESTPLATE)));
	public static final DeferredItem<Item> FROSTSTEEL_LEGGINGS = register("froststeel_leggings", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.FROSTSTEEL, ArmorType.LEGGINGS).attributes(FroststeelBootsItem.createFroststeelAttributes(ArmorType.LEGGINGS)));
	public static final DeferredItem<Item> FROSTSTEEL_BOOTS = register("froststeel_boots", FroststeelBootsItem::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.FROSTSTEEL, ArmorType.BOOTS).attributes(FroststeelBootsItem.createFroststeelAttributes(ArmorType.BOOTS)));

	public static final DeferredItem<Item> UTHERIUM_HELMET = register("utherium_helmet", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.UTHERIUM, ArmorType.HELMET).rarity(UTHERIUM_RARITY));
	public static final DeferredItem<Item> UTHERIUM_CHESTPLATE = register("utherium_chestplate", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.UTHERIUM, ArmorType.CHESTPLATE).rarity(UTHERIUM_RARITY));
	public static final DeferredItem<Item> UTHERIUM_LEGGINGS = register("utherium_leggings", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.UTHERIUM, ArmorType.LEGGINGS).rarity(UTHERIUM_RARITY));
	public static final DeferredItem<Item> UTHERIUM_BOOTS = register("utherium_boots", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.UTHERIUM, ArmorType.BOOTS).rarity(UTHERIUM_RARITY));

	public static final DeferredItem<Item> ANCIENT_HELMET = register("ancient_helmet", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.ANCIENT, ArmorType.HELMET));
	public static final DeferredItem<Item> ANCIENT_CHESTPLATE = register("ancient_chestplate", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.ANCIENT, ArmorType.CHESTPLATE));
	public static final DeferredItem<Item> ANCIENT_LEGGINGS = register("ancient_leggings", Item::new, () -> new Item.Properties().humanoidArmor(UGArmorMaterials.ANCIENT, ArmorType.LEGGINGS));

	public static final DeferredItem<Item> DENIZEN_MASK = register("denizen_mask", Item::new, () -> new Item.Properties()
		.stacksTo(1)
		.rarity(Rarity.UNCOMMON)
		.component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).setAsset(UGEquipmentAssets.DENIZEN_MASK).build()));

	//foods/plants
	public static final DeferredItem<Item> DROOPFRUIT = register("droopvine_item", (properties) -> new DroopfruitItem(UGBlocks.DROOPVINE.get(), properties), () -> new Item.Properties().food(UGFoods.DROOPFRUIT, UGConsumables.MOB_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.GLOWING, 600))));
	public static final DeferredItem<Item> UNDERBEANS = register("underbeans", (properties) -> new BlockItem(UGBlocks.UNDERBEAN_BUSH.get(), properties), () -> new Item.Properties().food(UGFoods.UNDERBEANS, UGConsumables.UNDERBEANS));
	public static final DeferredItem<Item> ROASTED_UNDERBEANS = register("roasted_underbeans", Item::new, () -> new Item.Properties().food(UGFoods.ROASTED_UNDERBEANS));
	public static final DeferredItem<Item> BLISTERBERRY = register("blisterberry", (properties) -> new BlockItem(UGBlocks.BLISTERBERRY_BUSH.get(), properties), () -> new Item.Properties().food(UGFoods.BLISTERBERRY));
	public static final DeferredItem<Item> ROTTEN_BLISTERBERRY = register("rotten_blisterberry", RottenBlisterberryItem::new, () -> new Item.Properties().food(UGFoods.ROTTEN_BLISTERBERRY, UGConsumables.MOB_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.HUNGER, 600))).component(UGDataComponents.SLINGSHOT_AMMO.get(), UGSlingshotAmmoSettings.BLISTERBERRY));
	public static final DeferredItem<Item> GOO_BALL = register("goo_ball", Item::new, () -> new Item.Properties().food(UGFoods.GOO_BALL, UGConsumables.MOB_EFFECT_CONSUMABLE.apply(new MobEffectInstance(UGEffects.GOOEY, 600))).component(UGDataComponents.SLINGSHOT_AMMO.get(), UGSlingshotAmmoSettings.GOO_BALL));
	public static final DeferredItem<Item> GLOOMGOURD_PIE = register("gloomgourd_pie", Item::new, () -> new Item.Properties().food(UGFoods.GLOOMGOURD_PIE, UGConsumables.MOB_EFFECT_CONSUMABLE.apply(new MobEffectInstance(UGEffects.VIRULENT_RESISTANCE, 600))));
	public static final DeferredItem<Item> RAW_DWELLER_MEAT = register("raw_dweller_meat", Item::new, () -> new Item.Properties().food(UGFoods.RAW_DWELLER));
	public static final DeferredItem<Item> DWELLER_STEAK = register("dweller_steak", Item::new, () -> new Item.Properties().food(UGFoods.COOKED_DWELLER));
	public static final DeferredItem<Item> RAW_GWIBLING = register("raw_gwibling", Item::new, () -> new Item.Properties().food(UGFoods.RAW_GWIBLING));
	public static final DeferredItem<Item> COOKED_GWIBLING = register("cooked_gwibling", Item::new, () -> new Item.Properties().food(UGFoods.COOKED_GWIBLING));
	public static final DeferredItem<Item> RAW_UNDERGAR_FILLET = register("raw_undergar_fillet", Item::new, () -> new Item.Properties().food(UGFoods.RAW_UNDERGAR_FILLET));
	public static final DeferredItem<Item> COOKED_UNDERGAR_FILLET = register("cooked_undergar_fillet", Item::new, () -> new Item.Properties().food(UGFoods.COOKED_UNDERGAR_FILLET));
	public static final DeferredItem<Item> RAW_GLOOMPER_LEG = register("raw_gloomper_leg", Item::new, () -> new Item.Properties().food(UGFoods.RAW_GLOOMPER_LEG));
	public static final DeferredItem<Item> GLOOMPER_LEG = register("gloomper_leg", Item::new, () -> new Item.Properties().food(UGFoods.GLOOMPER_LEG, UGConsumables.MOB_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.JUMP_BOOST, 600))));
	public static final DeferredItem<Item> GLITTERKELP = register("glitterkelp", (properties) -> new BlockItem(UGBlocks.GLITTERKELP.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix());
	public static final DeferredItem<Item> GLOOMGOURD_SEEDS = register("gloomgourd_seeds", (properties) -> new BlockItem(UGBlocks.GLOOMGOURD_STEM.get(), properties), Item.Properties::new);
	public static final DeferredItem<Item> BLOOD_GLOBULE = register("blood_globule", Item::new, () -> new Item.Properties().food(UGFoods.BLOOD_GLOBULE, UGConsumables.MOB_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.REGENERATION, 20, 3))));
	public static final DeferredItem<Item> BLOODY_STEW = register("bloody_stew", Item::new, () -> new Item.Properties().food(UGFoods.BLOODY_STEW, UGConsumables.STEW_CONSUMABLE.apply(new MobEffectInstance(MobEffects.STRENGTH, 600), new MobEffectInstance(UGEffects.BRITTLENESS, 600))).stacksTo(1).usingConvertsTo(Items.BOWL));
	public static final DeferredItem<Item> INKY_STEW = register("inky_stew", Item::new, () -> new Item.Properties().food(UGFoods.INKY_STEW, UGConsumables.STEW_CONSUMABLE.apply(new MobEffectInstance(MobEffects.BLINDNESS, 600), new MobEffectInstance(MobEffects.RESISTANCE, 600))).stacksTo(1).usingConvertsTo(Items.BOWL));
	public static final DeferredItem<Item> INDIGO_STEW = register("indigo_stew", Item::new, () -> new Item.Properties().food(UGFoods.INDIGO_STEW, UGConsumables.STEW_CONSUMABLE.apply(new MobEffectInstance(MobEffects.NIGHT_VISION, 600), new MobEffectInstance(MobEffects.SLOWNESS, 600))).stacksTo(1).usingConvertsTo(Items.BOWL));
	public static final DeferredItem<Item> VEILED_STEW = register("veiled_stew", Item::new, () -> new Item.Properties().food(UGFoods.VEILED_STEW, UGConsumables.STEW_CONSUMABLE.apply(new MobEffectInstance(MobEffects.SLOW_FALLING, 600), new MobEffectInstance(UGEffects.FEATHERWEIGHT, 600))).stacksTo(1).usingConvertsTo(Items.BOWL));
	public static final DeferredItem<Item> SLOP_BOWL = register("slop_bowl", Item::new, () -> new Item.Properties().food(UGFoods.SLOP_BOWL, UGConsumables.MOB_EFFECT_CONSUMABLE.apply(new MobEffectInstance(MobEffects.NAUSEA, 200))).stacksTo(1).usingConvertsTo(Items.BOWL));

	//blocks
	private static final Equippable grongEquip = Equippable.builder(EquipmentSlot.HEAD)
		.setEquipSound(UGSoundEvents.GRONGLET_AMBIENT)
		.setSwappable(false)
		.setDispensable(false)
		.setEquipOnInteract(false)
		.build();
	public static final DeferredItem<Item> GRONGLET = register("gronglet", properties -> new GrongletItem(UGBlocks.GRONGLET.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().food(UGFoods.GRONGLET, UGConsumables.GRONGLET).component(DataComponents.EQUIPPABLE, grongEquip).component(UGDataComponents.SLINGSHOT_AMMO.get(), UGSlingshotAmmoSettings.GRONGLET.apply("")));
	public static final DeferredItem<Item> ROGDORIC_GRONGLET = register("rogdoric_gronglet", properties -> new GrongletItem(UGBlocks.ROGDORIC_GRONGLET.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().food(UGFoods.GRONGLET, UGConsumables.ROGDORIC_GRONGLET).component(DataComponents.EQUIPPABLE, grongEquip).component(UGDataComponents.SLINGSHOT_AMMO.get(), UGSlingshotAmmoSettings.GRONGLET.apply("rogdoric_")).rarity(ROGDORIUM_RARITY));
	public static final DeferredItem<Item> UTHERIC_GRONGLET = register("utheric_gronglet", properties -> new GrongletItem(UGBlocks.UTHERIC_GRONGLET.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().food(UGFoods.GRONGLET, UGConsumables.UTHERIC_GRONGLET).component(DataComponents.EQUIPPABLE, grongEquip).component(UGDataComponents.SLINGSHOT_AMMO.get(), UGSlingshotAmmoSettings.GRONGLET.apply("utheric_")).rarity(UTHERIUM_RARITY));

	public static final DeferredItem<Item> SHARD_TORCH = register("shard_torch", properties -> new StandingAndWallBlockItem(UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get(), Direction.DOWN, properties), () -> new Item.Properties().useBlockDescriptionPrefix().rarity(UTHERIUM_RARITY));
	public static final DeferredItem<Item> DEPTHROCK_BED = register("depthrock_bed", properties -> new BedItem(UGBlocks.DEPTHROCK_BED.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(1));
	public static final DeferredItem<Item> WIGGLEWOOD_SIGN = register("wigglewood_sign", properties -> new SignItem(UGBlocks.WIGGLEWOOD_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));
	public static final DeferredItem<Item> SMOGSTEM_SIGN = register("smogstem_sign", properties -> new SignItem(UGBlocks.SMOGSTEM_SIGN.get(), UGBlocks.SMOGSTEM_WALL_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));
	public static final DeferredItem<Item> GRONGLE_SIGN = register("grongle_sign", properties -> new SignItem(UGBlocks.GRONGLE_SIGN.get(), UGBlocks.GRONGLE_WALL_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));
	public static final DeferredItem<Item> ANCIENT_ROOT_SIGN = register("ancient_root_sign", properties -> new SignItem(UGBlocks.ANCIENT_ROOT_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));
	public static final DeferredItem<Item> WIGGLEWOOD_HANGING_SIGN = register("wigglewood_hanging_sign", properties -> new SignItem(UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));
	public static final DeferredItem<Item> SMOGSTEM_HANGING_SIGN = register("smogstem_hanging_sign", properties -> new SignItem(UGBlocks.SMOGSTEM_HANGING_SIGN.get(), UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));
	public static final DeferredItem<Item> GRONGLE_HANGING_SIGN = register("grongle_hanging_sign", properties -> new SignItem(UGBlocks.GRONGLE_HANGING_SIGN.get(), UGBlocks.GRONGLE_WALL_HANGING_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));
	public static final DeferredItem<Item> ANCIENT_ROOT_HANGING_SIGN = register("ancient_root_hanging_sign", properties -> new SignItem(UGBlocks.ANCIENT_ROOT_HANGING_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN.get(), properties), () -> new Item.Properties().useBlockDescriptionPrefix().stacksTo(16));

	public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
		return ITEMS.register(name, () -> item.apply(properties.get().setId(ResourceKey.create(Registries.ITEM, Undergarden.prefix(name)))));
	}
}