package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.EntityHasProperty;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.Smelt;
import quek.undergarden.block.world.BeanBushBlock;
import quek.undergarden.data.provider.UndergardenBlockLootTableProvider;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItems;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UndergardenLootTables extends LootTableProvider {

    public UndergardenLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public String getName() {
        return "Undergarden LootTables";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables()
    {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK), Pair.of(Entities::new, LootParameterSets.ENTITY));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {

    }

    public static class Blocks extends UndergardenBlockLootTableProvider {

        @Override
        protected void addTables() {
            dropWithSilk(UndergardenBlocks.depthrock, UndergardenBlocks.cobbled_depthrock);
            dropSelf(UndergardenBlocks.cobbled_depthrock);
            dropSelf(UndergardenBlocks.deepsoil);
            this.registerLootTable(UndergardenBlocks.underbean_bush.get(),
                    LootTable.builder().addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.underbean_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BeanBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UndergardenItems.underbeans.get())).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))).addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.underbean_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BeanBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UndergardenItems.underbeans.get())).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
            dropWithSilk(UndergardenBlocks.deepturf_block, UndergardenBlocks.deepsoil);
            dropAsSilk(UndergardenBlocks.tall_deepturf);
            dropSelf(UndergardenBlocks.shimmerweed);
            dropSelf(UndergardenBlocks.smogstem_planks);
            dropSelf(UndergardenBlocks.wigglewood_planks);
            dropSelf(UndergardenBlocks.smogstem_log);
            dropSelf(UndergardenBlocks.wigglewood_log);
            dropWithFortune(UndergardenBlocks.coal_ore, Items.COAL);
            dropSelf(UndergardenBlocks.cloggrum_ore);
            dropSelf(UndergardenBlocks.froststeel_ore);
            dropWithFortune(UndergardenBlocks.utherium_ore, UndergardenItems.utherium_chunk);
            dropSelf(UndergardenBlocks.smogstem_sapling);
            dropChanceAdditional(UndergardenBlocks.smogstem_leaves, UndergardenBlocks.smogstem_sapling, UndergardenItems.smogstem_stick, 0.05F);
            dropSelf(UndergardenBlocks.wigglewood_sapling);
            dropChance(UndergardenBlocks.wigglewood_leaves, UndergardenBlocks.wigglewood_sapling, 0.05F);
            dropSelf(UndergardenBlocks.indigo_mushroom);
            dropSelf(UndergardenBlocks.veil_mushroom);
            dropSelf(UndergardenBlocks.ink_mushroom);
            dropSelf(UndergardenBlocks.blood_mushroom);
            dropSelf(UndergardenBlocks.depthrock_bricks);
            dropSelf(UndergardenBlocks.cracked_depthrock_bricks);
            dropOther(UndergardenBlocks.smogstem_torch, UndergardenItems.smogstem_torch.get());
            dropOther(UndergardenBlocks.smogstem_wall_torch, UndergardenItems.smogstem_torch.get());
            dropSelf(UndergardenBlocks.gloomgourd);
            dropSelf(UndergardenBlocks.carved_gloomgourd);
            dropSelf(UndergardenBlocks.gloom_o_lantern);
            dropWithSilk(UndergardenBlocks.ditchbulb_plant, UndergardenItems.ditchbulb);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return UndergardenBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    public static class Entities extends EntityLootTables {

        @Override
        protected void addTables() {
            this.registerLootTable(UndergardenEntities.rotwalker, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.rotbeast, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))))));
            this.registerLootTable(UndergardenEntities.dweller, LootTable.builder()/*.addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))*/.addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.raw_dweller_meat.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.rotdweller, LootTable.builder()/*.addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))*/.addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.raw_dweller_meat.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.gwibling, LootTable.builder());
            this.registerLootTable(UndergardenEntities.skiz_swarmer, LootTable.builder());
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return UndergardenEntities.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

    }
}

