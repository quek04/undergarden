package quek.undergarden.data.provider;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;

import java.util.function.Function;
import java.util.function.Supplier;

public class UGBlockLootTableProvider extends BlockLootTables {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);

    public void registerTable(Supplier<? extends Block> block, Function<Block, LootTable.Builder> factory) {
        super.registerLootTable(block.get(), factory);
    }

    public void dropSelf(Supplier<? extends Block> block) {
        super.registerDropSelfLootTable(block.get());
    }

    public void dropOther(Supplier<? extends Block> brokenBlock, IItemProvider droppedBlock) {
        super.registerDropping(brokenBlock.get(), droppedBlock);
    }

    public void dropAsSilk(Supplier<? extends Block> block) {
        super.registerSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends IItemProvider> drop) {
        registerLootTable(block.get(), (result) -> droppingWithSilkTouch(result, drop.get()));
    }

    public void dropWithFortune(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        super.registerLootTable(block.get(), (result) -> droppingItemWithFortune(result, drop.get()));
    }

    public void dropWithFortune(Supplier<? extends Block> block, IItemProvider drop) {
        super.registerLootTable(block.get(), (result) -> droppingItemWithFortune(result, drop.asItem()));
    }

    public void dropChance(Supplier<? extends Block> block, Supplier<? extends Block> drop, float... chances) {
        registerLootTable(block.get(), (result) -> withChance(block.get(), drop.get(), chances));
    }

    public void dropChanceAdditional(Supplier<? extends Block> block, Supplier<? extends Block> drop, Supplier<? extends Item> item, float... chances) {
        registerLootTable(block.get(), (result) -> withChanceAdditional(block.get(), drop.get(), item.get(), chances));
    }

    protected static LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return droppingWithSilkTouchOrShears(block, withSurvivesExplosion(block, ItemLootEntry.builder(drop))
                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances)));
    }

    protected static LootTable.Builder withChanceAdditional(Block block, Block sapling, Item item, float... chances) {
        return droppingWithSilkTouchOrShears(block, withSurvivesExplosion(block, ItemLootEntry.builder(sapling))
                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances)))
                .addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
                        .acceptCondition(SILK_TOUCH_OR_SHEARS)
                        .addEntry(withExplosionDecay(block, ItemLootEntry.builder(item)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }
}
