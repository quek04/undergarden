package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import quek.undergarden.Undergarden;
import quek.undergarden.criterion.StonebornTradeTrigger;
import quek.undergarden.registry.*;

import java.util.List;
import java.util.function.Consumer;

public class UndergardenAdvancements implements Consumer<Consumer<Advancement>> {
    private static final List<ResourceKey<Biome>> UNDERGARDEN_BIOMES = ImmutableList.of(UGBiomes.ANCIENT_SEA, UGBiomes.BARREN_ABYSS, UGBiomes.DENSE_FOREST, UGBiomes.FORGOTTEN_FIELD, UGBiomes.FROSTFIELDS, UGBiomes.GRONGLEGROWTH, UGBiomes.ICY_SEA, UGBiomes.MUSHROOM_BOG, UGBiomes.SMOG_SPIRES, UGBiomes.SMOGSTEM_FOREST, UGBiomes.WIGGLEWOOD_FOREST);
    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.advancement()
                .display(
                        UGBlocks.DEEPTURF_BLOCK.get(),
                        new TranslatableComponent("advancement.undergarden.root.title"),
                        new TranslatableComponent(""),
                        new ResourceLocation(Undergarden.MODID, "textures/block/depthrock_bricks.png"),
                        FrameType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("tick", new TickTrigger.TriggerInstance(EntityPredicate.Composite.ANY))
                .save(consumer, "undergarden:undergarden/root");

        Advancement catalyst = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        UGItems.CATALYST.get(),
                        new TranslatableComponent("advancement.undergarden.catalyst.title"),
                        new TranslatableComponent("advancement.undergarden.catalyst.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_catalyst", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.CATALYST.get()))
                .save(consumer, "undergarden:undergarden/catalyst");

        Advancement enter_undergarden = Advancement.Builder.advancement()
                .parent(catalyst)
                .display(
                        UGBlocks.DEEPTURF_BLOCK.get(),
                        new TranslatableComponent("advancement.undergarden.enter_undergarden.title"),
                        new TranslatableComponent("advancement.undergarden.enter_undergarden.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("enter_undergarden", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(UGDimensions.UNDERGARDEN_LEVEL))
                .save(consumer, "undergarden:undergarden/enter_undergarden");

        Advancement slingshot = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.SLINGSHOT.get(),
                        new TranslatableComponent("advancement.undergarden.slingshot.title"),
                        new TranslatableComponent("advancement.undergarden.slingshot.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_slingshot", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.SLINGSHOT.get()))
                .save(consumer, "undergarden:undergarden/slingshot");

        Advancement shoot_slingshot = Advancement.Builder.advancement()
                .parent(slingshot)
                .display(
                        UGItems.DEPTHROCK_PEBBLE.get(),
                        new TranslatableComponent("advancement.undergarden.shoot_slingshot.title"),
                        new TranslatableComponent("advancement.undergarden.shoot_slingshot.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("shoot_slingshot", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(DamagePredicate.Builder.damageInstance().type(DamageSourcePredicate.Builder.damageType().isProjectile(true).direct(EntityPredicate.Builder.entity().of(UGEntityTypes.SLINGSHOT_AMMO.get())))))
                .save(consumer, "undergarden:undergarden/shoot_slingshot");

        Advancement underbeans = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.UNDERBEANS.get(),
                        new TranslatableComponent("advancement.undergarden.underbeans.title"),
                        new TranslatableComponent("advancement.undergarden.underbeans.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_underbeans", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.UNDERBEANS.get()))
                .save(consumer, "undergarden:undergarden/underbeans");

        Advancement stoneborn_trade = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.REGALIUM_CRYSTAL.get(),
                        new TranslatableComponent("advancement.undergarden.stoneborn_trade.title"),
                        new TranslatableComponent("advancement.undergarden.stoneborn_trade.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("trade_with_stoneborn", StonebornTradeTrigger.TriggerInstance.tradeWithStoneborn())
                .save(consumer, "undergarden:undergarden/stoneborn_trade");

        Advancement mine_ore = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.RAW_CLOGGRUM.get(),
                        new TranslatableComponent("advancement.undergarden.mine_ore.title"),
                        new TranslatableComponent("advancement.undergarden.mine_ore.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(RequirementsStrategy.OR)
                .addCriterion("has_raw_cloggrum", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.RAW_CLOGGRUM.get()))
                .addCriterion("has_raw_froststeel", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.RAW_FROSTSTEEL.get()))
                .addCriterion("has_utherium", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.UTHERIUM_CRYSTAL.get()))
                .addCriterion("has_regalium_crystal", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.REGALIUM_CRYSTAL.get()))
                .addCriterion("has_depthrock_cloggrum", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get()))
                .addCriterion("has_shiverstone_cloggrum", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get()))
                .addCriterion("has_shiverstone_froststeel", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get()))
                .addCriterion("has_depthrock_utherium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get()))
                .addCriterion("has_shiverstone_utherium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get()))
                .addCriterion("has_depthrock_regalium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.DEPTHROCK_REGALIUM_ORE.get()))
                .addCriterion("has_shiverstone_regalium", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHIVERSTONE_REGALIUM_ORE.get()))
                .save(consumer, "undergarden:undergarden/mine_ore");

        Advancement all_ore_blocks = Advancement.Builder.advancement()
                .parent(mine_ore)
                .display(
                        UGBlocks.REGALIUM_BLOCK.get(),
                        new TranslatableComponent("advancement.undergarden.all_ore_blocks.title"),
                        new TranslatableComponent("advancement.undergarden.all_ore_blocks.desc"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("has_all_ore_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.CLOGGRUM_BLOCK.get(), UGBlocks.FROSTSTEEL_BLOCK.get(), UGBlocks.UTHERIUM_BLOCK.get(), UGBlocks.REGALIUM_BLOCK.get()))
                .save(consumer, "undergarden:undergarden/all_ore_blocks");

        Advancement cloggrum_armor = Advancement.Builder.advancement()
                .parent(mine_ore)
                .display(
                        UGItems.CLOGGRUM_CHESTPLATE.get(),
                        new TranslatableComponent("advancement.undergarden.cloggrum_armor.title"),
                        new TranslatableComponent("advancement.undergarden.cloggrum_armor.desc"),
                        null,
                        FrameType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("has_cloggrum_armor", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.CLOGGRUM_HELMET.get(), UGItems.CLOGGRUM_CHESTPLATE.get(), UGItems.CLOGGRUM_LEGGINGS.get(), UGItems.CLOGGRUM_BOOTS.get()))
                .save(consumer, "undergarden:undergarden/cloggrum_armor");

        addBiomes(Advancement.Builder.advancement(), UNDERGARDEN_BIOMES)
                .parent(enter_undergarden)
                .display(
                        UGItems.CLOGGRUM_BOOTS.get(),
                        new TranslatableComponent("advancement.undergarden.all_undergarden_biomes.title"),
                        new TranslatableComponent("advancement.undergarden.all_undergarden_biomes.desc"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .save(consumer, "undergarden:undergarden/all_undergarden_biomes");

        Advancement plant_gloomgourd = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.GLOOMGOURD_SEEDS.get(),
                        new TranslatableComponent("advancement.undergarden.plant_gloomgourd.title"),
                        new TranslatableComponent("advancement.undergarden.plant_gloomgourd.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("plant_gloomgourd", PlacedBlockTrigger.TriggerInstance.placedBlock(UGBlocks.GLOOMGOURD_STEM.get()))
                .save(consumer, "undergarden:undergarden/plant_gloomgourd");

        Advancement stack_of_gloomgourds = Advancement.Builder.advancement()
                .parent(plant_gloomgourd)
                .display(
                        UGBlocks.GLOOMGOURD.get(),
                        new TranslatableComponent("advancement.undergarden.stack_of_gloomgourds.title"),
                        new TranslatableComponent("advancement.undergarden.stack_of_gloomgourds.desc"),
                        null,
                        FrameType.GOAL,
                        true,
                        true,
                        true
                )
                .addCriterion("has_64_gloomgourds", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(UGBlocks.GLOOMGOURD.get()).withCount(MinMaxBounds.Ints.exactly(64)).build()))
                .save(consumer, "undergarden:undergarden/stack_of_gloomgourds");

        Advancement catch_gwibling = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.GWIBLING_BUCKET.get(),
                        new TranslatableComponent("advancement.undergarden.catch_gwibling.title"),
                        new TranslatableComponent("advancement.undergarden.catch_gwibling.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_gwibling_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.GWIBLING_BUCKET.get()))
                .save(consumer, "undergarden:undergarden/catch_gwibling");

        Advancement kill_rotling = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.UTHERIC_SHARD.get(),
                        new TranslatableComponent("advancement.undergarden.kill_rotling.title"),
                        new TranslatableComponent("advancement.undergarden.kill_rotling.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("kill_rotling", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTLING.get()).build()))
                .save(consumer, "undergarden:undergarden/kill_rotling");

        Advancement shard_torch = Advancement.Builder.advancement()
                .parent(kill_rotling)
                .display(
                        UGBlocks.SHARD_TORCH.get(),
                        new TranslatableComponent("advancement.undergarden.shard_torch.title"),
                        new TranslatableComponent("advancement.undergarden.shard_torch.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_shard_torch", InventoryChangeTrigger.TriggerInstance.hasItems(UGBlocks.SHARD_TORCH.get()))
                .save(consumer, "undergarden:undergarden/shard_torch");

        Advancement kill_all_rotspawn = Advancement.Builder.advancement()
                .parent(kill_rotling)
                .display(
                        UGItems.UTHERIUM_SWORD.get(),
                        new TranslatableComponent("advancement.undergarden.kill_all_rotspawn.title"),
                        new TranslatableComponent("advancement.undergarden.kill_all_rotspawn.desc"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .requirements(RequirementsStrategy.AND)
                .addCriterion("kill_rotling", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTLING.get()).build()))
                .addCriterion("kill_rotwalker", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTWALKER.get()).build()))
                .addCriterion("kill_rotbeast", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.ROTBEAST.get()).build()))
                .save(consumer, "undergarden:undergarden/kill_all_rotspawn");

        Advancement kill_scintling = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.GOO_BALL.get(),
                        new TranslatableComponent("advancement.undergarden.kill_scintling.title"),
                        new TranslatableComponent("advancement.undergarden.kill_scintling.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("kill_scintling", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.SCINTLING.get()).build()))
                .save(consumer, "undergarden:undergarden/kill_scintling");

        Advancement catacombs = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGBlocks.DEPTHROCK_BRICK_STAIRS.get(),
                        new TranslatableComponent("advancement.undergarden.catacombs.title"),
                        new TranslatableComponent("advancement.undergarden.catacombs.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("enter_catacombs", LocationTrigger.TriggerInstance.located(LocationPredicate.inFeature(UGStructures.CATACOMBS.get())))
                .save(consumer, "undergarden:undergarden/catacombs");

        Advancement kill_guardian = Advancement.Builder.advancement()
                .parent(catacombs)
                .display(
                        UGBlocks.FORGOTTEN_BLOCK.get(),
                        new TranslatableComponent("advancement.undergarden.kill_forgotten_guardian.title"),
                        new TranslatableComponent("advancement.undergarden.kill_forgotten_guardian.desc"),
                        null,
                        FrameType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("kill_forgotten_guardian", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.FORGOTTEN_GUARDIAN.get()).build()))
                .save(consumer, "undergarden:undergarden/kill_forgotten_guardian");

        Advancement forgotten_ingot = Advancement.Builder.advancement()
                .parent(kill_guardian)
                .display(
                        UGItems.FORGOTTEN_INGOT.get(),
                        new TranslatableComponent("advancement.undergarden.forgotten_ingot.title"),
                        new TranslatableComponent("advancement.undergarden.forgotten_ingot.desc"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_forgotten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.FORGOTTEN_INGOT.get()))
                .save(consumer, "undergarden:undergarden/forgotten_ingot");

        Advancement forgotten_tools = Advancement.Builder.advancement()
                .parent(forgotten_ingot)
                .display(
                        UGItems.FORGOTTEN_PICKAXE.get(),
                        new TranslatableComponent("advancement.undergarden.forgotten_tools.title"),
                        new TranslatableComponent("advancement.undergarden.forgotten_tools.desc"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("has_forgotten_tools", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.FORGOTTEN_BATTLEAXE.get(), UGItems.FORGOTTEN_SWORD.get(), UGItems.FORGOTTEN_PICKAXE.get(), UGItems.FORGOTTEN_AXE.get(), UGItems.FORGOTTEN_SHOVEL.get(), UGItems.FORGOTTEN_HOE.get()))
                .save(consumer, "undergarden:undergarden/forgotten_tools");

        Advancement forgotten_battleaxe = Advancement.Builder.advancement()
                .parent(forgotten_ingot)
                .display(
                        UGItems.FORGOTTEN_BATTLEAXE.get(),
                        new TranslatableComponent("advancement.undergarden.forgotten_battleaxe.title"),
                        new TranslatableComponent("advancement.undergarden.forgotten_battleaxe.desc"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("has_forgotten_battleaxe", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.FORGOTTEN_BATTLEAXE.get()))
                .save(consumer, "undergarden:undergarden/forgotten_battleaxe");

        Advancement summon_minion = Advancement.Builder.advancement()
                .parent(forgotten_ingot)
                .display(
                        UGBlocks.CARVED_GLOOMGOURD.get(),
                        new TranslatableComponent("advancement.undergarden.summon_minion.title"),
                        new TranslatableComponent("advancement.undergarden.summon_minion.desc"),
                        null,
                        FrameType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("summoned_minion", SummonedEntityTrigger.TriggerInstance.summonedEntity(EntityPredicate.Builder.entity().of(UGEntityTypes.MINION.get())))
                .save(consumer, "undergarden:undergarden/summon_minion");

        Advancement gloomper_secret_disc = Advancement.Builder.advancement()
                .parent(enter_undergarden)
                .display(
                        UGItems.GLOOMPER_SECRET_DISC.get(),
                        new TranslatableComponent("advancement.undergarden.gloomper_secret_disc.title"),
                        new TranslatableComponent("advancement.undergarden.gloomper_secret_disc.desc"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("has_disc", InventoryChangeTrigger.TriggerInstance.hasItems(UGItems.GLOOMPER_SECRET_DISC.get()))
                .save(consumer, "undergarden:undergarden/gloomper_secret_disc");
    }

    protected static Advancement.Builder addBiomes(Advancement.Builder builder, List<ResourceKey<Biome>> biomes) {
        for(ResourceKey<Biome> resourcekey : biomes) {
            builder.addCriterion(resourcekey.location().toString(), LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(resourcekey)));
        }

        return builder;
    }
}