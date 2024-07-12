package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.packs.VanillaAdventureAdvancements;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;
import quek.undergarden.criterion.DenizenCampfireDestroyedTrigger;
import quek.undergarden.criterion.SlingshotFireTrigger;
import quek.undergarden.criterion.StonebornTradeTrigger;
import quek.undergarden.criterion.UthericInfectionTrigger;
import quek.undergarden.registry.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class UndergardenAdvancements implements AdvancementProvider.AdvancementGenerator {
	private static final List<ResourceKey<Biome>> UNDERGARDEN_BIOMES = ImmutableList.of(UGBiomes.ANCIENT_SEA, UGBiomes.BARREN_ABYSS, UGBiomes.DEAD_SEA, UGBiomes.DENSE_FOREST, UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS, UGBiomes.FROSTY_SMOGSTEM_FOREST, UGBiomes.GRONGLEGROWTH, UGBiomes.ICY_SEA, UGBiomes.BLOOD_MUSHROOM_BOG, UGBiomes.SMOG_SPIRES, UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST, UGBiomes.INDIGO_MUSHROOM_BOG, UGBiomes.INK_MUSHROOM_BOG, UGBiomes.VEIL_MUSHROOM_BOG, UGBiomes.DEPTHS, UGBiomes.INFECTED_DEPTHS, UGBiomes.PUFF_MUSHROOM_FOREST);

	@SuppressWarnings("unused")
	@Override
	public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
		AdvancementHolder root = Advancement.Builder.advancement()
				.display(
						UGBlocks.DEEPTURF_BLOCK.get(),
						Component.translatable("advancement.undergarden.root.title"),
						Component.empty(),
						ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/block/depthrock_bricks.png"),
						AdvancementType.TASK,
						false,
						false,
						false
				)
				.addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
				.save(consumer, "undergarden:undergarden/root");

		AdvancementHolder catalyst = Advancement.Builder.advancement()
				.parent(root)
				.display(
						UGItems.CATALYST.get(),
						Component.translatable("advancement.undergarden.catalyst.title"),
						Component.translatable("advancement.undergarden.catalyst.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("has_catalyst", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.CATALYST.get()))
				.save(consumer, "undergarden:undergarden/catalyst");

		AdvancementHolder enter_undergarden = Advancement.Builder.advancement()
				.parent(catalyst)
				.display(
						UGBlocks.DEEPTURF_BLOCK.get(),
						Component.translatable("advancement.undergarden.enter_undergarden.title"),
						Component.translatable("advancement.undergarden.enter_undergarden.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("enter_undergarden", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(UGDimensions.UNDERGARDEN_LEVEL))
				.save(consumer, "undergarden:undergarden/enter_undergarden");

		AdvancementHolder slingshot = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.SLINGSHOT.get(),
						Component.translatable("advancement.undergarden.slingshot.title"),
						Component.translatable("advancement.undergarden.slingshot.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("has_slingshot", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.SLINGSHOT.get()))
				.save(consumer, "undergarden:undergarden/slingshot");

		AdvancementHolder shoot_slingshot = Advancement.Builder.advancement()
				.parent(slingshot)
				.display(
						UGItems.DEPTHROCK_PEBBLE.get(),
						Component.translatable("advancement.undergarden.shoot_slingshot.title"),
						Component.translatable("advancement.undergarden.shoot_slingshot.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("shoot_slingshot", SlingshotFireTrigger.TriggerInstance.shotItem(UGItems.DEPTHROCK_PEBBLE.get()))
				.save(consumer, "undergarden:undergarden/shoot_slingshot");

		AdvancementHolder shoot_slingshot_goo = Advancement.Builder.advancement()
				.parent(slingshot)
				.display(
						UGItems.GOO_BALL.get(),
						Component.translatable("advancement.undergarden.shoot_slingshot_goo.title"),
						Component.translatable("advancement.undergarden.shoot_slingshot_goo.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("shoot_slingshot_goo", SlingshotFireTrigger.TriggerInstance.shotItem(UGItems.GOO_BALL.get()))
				.save(consumer, "undergarden:undergarden/shoot_slingshot_goo");

		AdvancementHolder shoot_slingshot_rotten_blisterberry = Advancement.Builder.advancement()
				.parent(slingshot)
				.display(
						UGItems.ROTTEN_BLISTERBERRY.get(),
						Component.translatable("advancement.undergarden.shoot_slingshot_rotten_blisterberry.title"),
						Component.translatable("advancement.undergarden.shoot_slingshot_rotten_blisterberry.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("shoot_slingshot_rotten_blisterberry", SlingshotFireTrigger.TriggerInstance.shotItem(UGItems.ROTTEN_BLISTERBERRY.get()))
				.save(consumer, "undergarden:undergarden/shoot_slingshot_rotten_blisterberry");

		AdvancementHolder slingshot_20_damage = Advancement.Builder.advancement()
				.parent(shoot_slingshot)
				.display(
						UGItems.DEPTHROCK_PEBBLE.get(),
						Component.translatable("advancement.undergarden.slingshot_20_damage.title"),
						Component.translatable("advancement.undergarden.slingshot_20_damage.desc"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.addCriterion("20_damage", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().dealtDamage(MinMaxBounds.Doubles.atLeast(20.0D)).type(DamageSourcePredicate.Builder.damageType().direct(EntityPredicate.Builder.entity().of(UGEntityTypes.DEPTHROCK_PEBBLE.get()))), Optional.empty()))
				.save(consumer, "undergarden:undergarden/slingshot_20_damage");

		AdvancementHolder shoot_slingshot_gronglet = Advancement.Builder.advancement()
				.parent(slingshot)
				.display(
						UGBlocks.GRONGLET.get(),
						Component.translatable("advancement.undergarden.shoot_slingshot_gronglet.title"),
						Component.translatable("advancement.undergarden.shoot_slingshot_gronglet.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("shoot_slingshot_gronglet", SlingshotFireTrigger.TriggerInstance.shotItem(UGBlocks.GRONGLET.get()))
				.save(consumer, "undergarden:undergarden/shoot_slingshot_gronglet");

		AdvancementHolder underbeans = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.UNDERBEANS.get(),
						Component.translatable("advancement.undergarden.underbeans.title"),
						Component.translatable("advancement.undergarden.underbeans.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("has_underbeans", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.UNDERBEANS.get()))
				.save(consumer, "undergarden:undergarden/underbeans");

		AdvancementHolder stoneborn_trade = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.REGALIUM_CRYSTAL.get(),
						Component.translatable("advancement.undergarden.stoneborn_trade.title"),
						Component.translatable("advancement.undergarden.stoneborn_trade.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("trade_with_stoneborn", StonebornTradeTrigger.TriggerInstance.tradeWithStoneborn())
				.save(consumer, "undergarden:undergarden/stoneborn_trade");

		AdvancementHolder mine_ore = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.RAW_CLOGGRUM.get(),
						Component.translatable("advancement.undergarden.mine_ore.title"),
						Component.translatable("advancement.undergarden.mine_ore.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.requirements(AdvancementRequirements.Strategy.OR)
				.addCriterion("has_raw_cloggrum", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.RAW_CLOGGRUM.get()))
				.addCriterion("has_raw_froststeel", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.RAW_FROSTSTEEL.get()))
				.addCriterion("has_utherium", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.UTHERIUM_CRYSTAL.get()))
				.addCriterion("has_regalium_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.REGALIUM_CRYSTAL.get()))
				.addCriterion("has_rogdorium_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.ROGDORIUM_CRYSTAL.asItem()))
				.addCriterion("has_depthrock_cloggrum", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get()))
				.addCriterion("has_shiverstone_cloggrum", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get()))
				.addCriterion("has_shiverstone_froststeel", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get()))
				.addCriterion("has_depthrock_utherium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get()))
				.addCriterion("has_shiverstone_utherium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get()))
				.addCriterion("has_dreadrock_utherium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DREADROCK_UTHERIUM_ORE.get()))
				.addCriterion("has_depthrock_regalium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DEPTHROCK_REGALIUM_ORE.get()))
				.addCriterion("has_shiverstone_regalium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_REGALIUM_ORE.get()))
				.addCriterion("has_dreadrock_rogdorium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DREADROCK_ROGDORIUM_ORE.get()))
				.save(consumer, "undergarden:undergarden/mine_ore");

		AdvancementHolder all_ore_blocks = Advancement.Builder.advancement()
				.parent(mine_ore)
				.display(
						UGBlocks.REGALIUM_BLOCK.get(),
						Component.translatable("advancement.undergarden.all_ore_blocks.title"),
						Component.translatable("advancement.undergarden.all_ore_blocks.desc"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.addCriterion("has_all_ore_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get()))
				.save(consumer, "undergarden:undergarden/all_ore_blocks");

		AdvancementHolder cloggrum_armor = Advancement.Builder.advancement()
				.parent(mine_ore)
				.display(
						UGItems.CLOGGRUM_CHESTPLATE.get(),
						Component.translatable("advancement.undergarden.cloggrum_armor.title"),
						Component.translatable("advancement.undergarden.cloggrum_armor.desc"),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("has_cloggrum_armor", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.CLOGGRUM_HELMET.get(), UGItems.CLOGGRUM_CHESTPLATE.get(), UGItems.CLOGGRUM_LEGGINGS.get(), UGItems.CLOGGRUM_BOOTS.get()))
				.save(consumer, "undergarden:undergarden/cloggrum_armor");

		VanillaAdventureAdvancements.addBiomes(Advancement.Builder.advancement(), provider, UNDERGARDEN_BIOMES)
				.parent(enter_undergarden)
				.display(
						UGItems.CLOGGRUM_BOOTS.get(),
						Component.translatable("advancement.undergarden.all_undergarden_biomes.title"),
						Component.translatable("advancement.undergarden.all_undergarden_biomes.desc"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.save(consumer, "undergarden:undergarden/all_undergarden_biomes");

		AdvancementHolder plant_gloomgourd = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.GLOOMGOURD_SEEDS.get(),
						Component.translatable("advancement.undergarden.plant_gloomgourd.title"),
						Component.translatable("advancement.undergarden.plant_gloomgourd.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("plant_gloomgourd", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(UGBlocks.GLOOMGOURD_STEM.get()))
				.save(consumer, "undergarden:undergarden/plant_gloomgourd");

		AdvancementHolder stack_of_gloomgourds = Advancement.Builder.advancement()
				.parent(plant_gloomgourd)
				.display(
						UGBlocks.GLOOMGOURD.get(),
						Component.translatable("advancement.undergarden.stack_of_gloomgourds.title"),
						Component.translatable("advancement.undergarden.stack_of_gloomgourds.desc"),
						null,
						AdvancementType.GOAL,
						true,
						true,
						true
				)
				.addCriterion("has_64_gloomgourds", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(UGBlocks.GLOOMGOURD.get()).withCount(MinMaxBounds.Ints.exactly(64)).build()))
				.save(consumer, "undergarden:undergarden/stack_of_gloomgourds");

		AdvancementHolder catch_gwibling = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.GWIBLING_BUCKET.get(),
						Component.translatable("advancement.undergarden.catch_gwibling.title"),
						Component.translatable("advancement.undergarden.catch_gwibling.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("has_gwibling_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.GWIBLING_BUCKET.get()))
				.save(consumer, "undergarden:undergarden/catch_gwibling");

		AdvancementHolder kill_rotling = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.UTHERIC_SHARD.get(),
						Component.translatable("advancement.undergarden.kill_rotling.title"),
						Component.translatable("advancement.undergarden.kill_rotling.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("kill_rotling", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTLING.get()).build())))
				.save(consumer, "undergarden:undergarden/kill_rotling");

		AdvancementHolder shard_torch = Advancement.Builder.advancement()
				.parent(kill_rotling)
				.display(
						UGBlocks.SHARD_TORCH.get(),
						Component.translatable("advancement.undergarden.shard_torch.title"),
						Component.translatable("advancement.undergarden.shard_torch.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("has_shard_torch", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHARD_TORCH.get()))
				.save(consumer, "undergarden:undergarden/shard_torch");

		AdvancementHolder kill_all_rotspawn = Advancement.Builder.advancement()
				.parent(kill_rotling)
				.display(
						UGItems.UTHERIUM_SWORD.get(),
						Component.translatable("advancement.undergarden.kill_all_rotspawn.title"),
						Component.translatable("advancement.undergarden.kill_all_rotspawn.desc"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.requirements(AdvancementRequirements.Strategy.AND)
				.addCriterion("kill_rotling", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTLING.get()).build())))
				.addCriterion("kill_rotwalker", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTWALKER.get()).build())))
				.addCriterion("kill_rotbeast", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTBEAST.get()).build())))
				.save(consumer, "undergarden:undergarden/kill_all_rotspawn");

		AdvancementHolder kill_scintling = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.GOO_BALL.get(),
						Component.translatable("advancement.undergarden.kill_scintling.title"),
						Component.translatable("advancement.undergarden.kill_scintling.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						true
				)
				.addCriterion("kill_scintling", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(EntityPredicate.Builder.entity().of(UGEntityTypes.SCINTLING.get()).build())))
				.save(consumer, "undergarden:undergarden/kill_scintling");

		AdvancementHolder catacombs = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGBlocks.DEPTHROCK_BRICK_STAIRS.get(),
						Component.translatable("advancement.undergarden.catacombs.title"),
						Component.translatable("advancement.undergarden.catacombs.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("enter_catacombs", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(provider.lookupOrThrow(Registries.STRUCTURE).getOrThrow(UGStructures.CATACOMBS))))
				.save(consumer, "undergarden:undergarden/catacombs");

		AdvancementHolder cloggrum_battleaxe = Advancement.Builder.advancement()
				.parent(catacombs)
				.display(
						UGItems.CLOGGRUM_BATTLEAXE.get(),
						Component.translatable("advancement.undergarden.cloggrum_battleaxe.title"),
						Component.translatable("advancement.undergarden.cloggrum_battleaxe.desc"),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("has_cloggrum_battleaxe", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.CLOGGRUM_BATTLEAXE.get()))
				.save(consumer, "undergarden:undergarden/cloggrum_battleaxe");

		AdvancementHolder kill_guardian = Advancement.Builder.advancement()
				.parent(catacombs)
				.display(
						UGBlocks.FORGOTTEN_BLOCK.get(),
						Component.translatable("advancement.undergarden.kill_forgotten_guardian.title"),
						Component.translatable("advancement.undergarden.kill_forgotten_guardian.desc"),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("kill_forgotten_guardian", KilledTrigger.TriggerInstance.playerKilledEntity(Optional.of(EntityPredicate.Builder.entity().of(UGEntityTypes.FORGOTTEN_GUARDIAN.get()).build())))
				.save(consumer, "undergarden:undergarden/kill_forgotten_guardian");

		AdvancementHolder forgotten_ingot = Advancement.Builder.advancement()
				.parent(kill_guardian)
				.display(
						UGItems.FORGOTTEN_INGOT.get(),
						Component.translatable("advancement.undergarden.forgotten_ingot.title"),
						Component.translatable("advancement.undergarden.forgotten_ingot.desc"),
						null,
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("has_forgotten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.FORGOTTEN_INGOT.get()))
				.save(consumer, "undergarden:undergarden/forgotten_ingot");

		AdvancementHolder forgotten_tools = Advancement.Builder.advancement()
				.parent(forgotten_ingot)
				.display(
						UGItems.FORGOTTEN_PICKAXE.get(),
						Component.translatable("advancement.undergarden.forgotten_tools.title"),
						Component.translatable("advancement.undergarden.forgotten_tools.desc"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.addCriterion("has_forgotten_tools", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.FORGOTTEN_BATTLEAXE.get(), UGItems.FORGOTTEN_SWORD.get(), UGItems.FORGOTTEN_PICKAXE.get(), UGItems.FORGOTTEN_AXE.get(), UGItems.FORGOTTEN_SHOVEL.get(), UGItems.FORGOTTEN_HOE.get()))
				.save(consumer, "undergarden:undergarden/forgotten_tools");

		AdvancementHolder forgotten_battleaxe = Advancement.Builder.advancement()
				.parent(cloggrum_battleaxe)
				.display(
						UGItems.FORGOTTEN_BATTLEAXE.get(),
						Component.translatable("advancement.undergarden.forgotten_battleaxe.title"),
						Component.translatable("advancement.undergarden.forgotten_battleaxe.desc"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.addCriterion("has_forgotten_battleaxe", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.FORGOTTEN_BATTLEAXE.get()))
				.save(consumer, "undergarden:undergarden/forgotten_battleaxe");

		AdvancementHolder summon_minion = Advancement.Builder.advancement()
				.parent(forgotten_ingot)
				.display(
						UGBlocks.CARVED_GLOOMGOURD.get(),
						Component.translatable("advancement.undergarden.summon_minion.title"),
						Component.translatable("advancement.undergarden.summon_minion.desc"),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("summoned_minion", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.MINION.get())))
				.save(consumer, "undergarden:undergarden/summon_minion");

		AdvancementHolder gloomper_secret_disc = Advancement.Builder.advancement()
				.parent(enter_undergarden)
				.display(
						UGItems.GLOOMPER_SECRET_DISC.get(),
						Component.translatable("advancement.undergarden.gloomper_secret_disc.title"),
						Component.translatable("advancement.undergarden.gloomper_secret_disc.desc"),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						true
				)
				.addCriterion("has_disc", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.GLOOMPER_SECRET_DISC.get()))
				.save(consumer, "undergarden:undergarden/gloomper_secret_disc");

		AdvancementHolder enter_depths = Advancement.Builder.advancement()
			.parent(enter_undergarden)
			.display(
				UGBlocks.DREADROCK.get(),
				Component.translatable("advancement.undergarden.enter_depths.title"),
				Component.translatable("advancement.undergarden.enter_depths.desc"),
				null,
				AdvancementType.GOAL,
				true,
				true,
				false
			)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("has_entered_depths", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(provider.lookupOrThrow(Registries.BIOME).getOrThrow(UGBiomes.DEPTHS))))
			.addCriterion("has_entered_infected_depths", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(provider.lookupOrThrow(Registries.BIOME).getOrThrow(UGBiomes.INFECTED_DEPTHS))))
			.addCriterion("has_entered_puff_mushroom_forest", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(provider.lookupOrThrow(Registries.BIOME).getOrThrow(UGBiomes.PUFF_MUSHROOM_FOREST))))
			.save(consumer, "undergarden:undergarden/enter_depths");

		AdvancementHolder contract_utheric_infection = Advancement.Builder.advancement()
			.parent(enter_depths)
			.display(
				UGItems.UTHERIUM_CRYSTAL.get(),
				Component.translatable("advancement.undergarden.contract_utheric_infection.title"),
				Component.translatable("advancement.undergarden.contract_utheric_infection.desc"),
				null,
				AdvancementType.TASK,
				true,
				true,
				false
			)
			.addCriterion("has_contracted_utheric_infection", UthericInfectionTrigger.TriggerInstance.isInfected())
			.save(consumer, "undergarden:undergarden/contract_utheric_infection");

		AdvancementHolder cure_utheric_infection = Advancement.Builder.advancement()
			.parent(contract_utheric_infection)
			.display(
				UGItems.ROGDORIUM_CRYSTAL.get(),
				Component.translatable("advancement.undergarden.cure_utheric_infection.title"),
				Component.translatable("advancement.undergarden.cure_utheric_infection.desc"),
				null,
				AdvancementType.TASK,
				true,
				true,
				false
			)
			.addCriterion("has_purity_effect", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.Builder.effects().and(UGEffects.PURITY)))
			.save(consumer, "undergarden:undergarden/cure_utheric_infection");

		AdvancementHolder enter_denizen_camp = Advancement.Builder.advancement()
			.parent(enter_depths)
			.display(
				UGItems.SPEAR.get(),
				Component.translatable("advancement.undergarden.enter_denizen_camp.title"),
				Component.translatable("advancement.undergarden.enter_denizen_camp.desc"),
				null,
				AdvancementType.TASK,
				true,
				true,
				false
			)
			.addCriterion("has_entered_denizen_camp", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(provider.lookupOrThrow(Registries.STRUCTURE).getOrThrow(UGStructures.DENIZEN_CAMP))))
			.save(consumer, "undergarden:undergarden/enter_denizen_camp");

		AdvancementHolder obtain_denizen_mask = Advancement.Builder.advancement()
			.parent(enter_denizen_camp)
			.display(
				UGItems.DENIZEN_MASK.get(),
				Component.translatable("advancement.undergarden.obtain_denizen_mask.title"),
				Component.translatable("advancement.undergarden.obtain_denizen_mask.desc"),
				null,
				AdvancementType.GOAL,
				true,
				true,
				false
			)
			.addCriterion("has_denizen_mask", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.DENIZEN_MASK.get()))
			.save(consumer, "undergarden:undergarden/obtain_denizen_mask");

		AdvancementHolder break_denizen_campfire = Advancement.Builder.advancement()
			.parent(obtain_denizen_mask)
			.display(
				Blocks.CAMPFIRE,
				Component.translatable("advancement.undergarden.break_denizen_campfire.title"),
				Component.translatable("advancement.undergarden.break_denizen_campfire.desc"),
				null,
				AdvancementType.TASK,
				true,
				true,
				true
			)
			.addCriterion("has_broken_denizen_campfire", DenizenCampfireDestroyedTrigger.TriggerInstance.destroyedCampfire(Blocks.CAMPFIRE))
			.save(consumer, "undergarden:undergarden/break_denizen_campfire");
	}
}