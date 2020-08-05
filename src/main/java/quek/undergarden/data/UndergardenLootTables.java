package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import quek.undergarden.block.world.UndergardenDoublePlantBlock;
import net.minecraft.item.Item;
import net.minecraft.loot.conditions.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import quek.undergarden.block.world.UndergardenDoublePlantBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.block.world.BeanBushBlock;
import quek.undergarden.block.world.BlisterberryBushBlock;
import quek.undergarden.data.provider.UndergardenBlockLootTableProvider;
import quek.undergarden.registry.UndergardenBlocks;
import quek.undergarden.registry.UndergardenEntities;
import quek.undergarden.registry.UndergardenItems;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UndergardenLootTables extends LootTableProvider {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(net.minecraft.block.Blocks.DRAGON_EGG, net.minecraft.block.Blocks.BEACON, net.minecraft.block.Blocks.CONDUIT, net.minecraft.block.Blocks.SKELETON_SKULL, net.minecraft.block.Blocks.WITHER_SKELETON_SKULL, net.minecraft.block.Blocks.PLAYER_HEAD, net.minecraft.block.Blocks.ZOMBIE_HEAD, net.minecraft.block.Blocks.CREEPER_HEAD, net.minecraft.block.Blocks.DRAGON_HEAD, net.minecraft.block.Blocks.SHULKER_BOX, net.minecraft.block.Blocks.BLACK_SHULKER_BOX, net.minecraft.block.Blocks.BLUE_SHULKER_BOX, net.minecraft.block.Blocks.BROWN_SHULKER_BOX, net.minecraft.block.Blocks.CYAN_SHULKER_BOX, net.minecraft.block.Blocks.GRAY_SHULKER_BOX, net.minecraft.block.Blocks.GREEN_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_BLUE_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_GRAY_SHULKER_BOX, net.minecraft.block.Blocks.LIME_SHULKER_BOX, net.minecraft.block.Blocks.MAGENTA_SHULKER_BOX, net.minecraft.block.Blocks.ORANGE_SHULKER_BOX, net.minecraft.block.Blocks.PINK_SHULKER_BOX, net.minecraft.block.Blocks.PURPLE_SHULKER_BOX, net.minecraft.block.Blocks.RED_SHULKER_BOX, net.minecraft.block.Blocks.WHITE_SHULKER_BOX, net.minecraft.block.Blocks.YELLOW_SHULKER_BOX).map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

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
            dropSelf(UndergardenBlocks.depthrock);
            dropSelf(UndergardenBlocks.deepsoil);
            dropWithSilk(UndergardenBlocks.deepsoil_farmland, UndergardenBlocks.deepsoil);
            this.registerLootTable(UndergardenBlocks.underbean_bush.get(),
                    LootTable.builder().addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.underbean_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BeanBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UndergardenItems.underbeans.get())).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))).addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.underbean_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BeanBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UndergardenItems.underbeans.get())).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
            this.registerLootTable(UndergardenBlocks.blisterberry_bush.get(),
                    LootTable.builder()
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UndergardenItems.blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UndergardenItems.blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UndergardenItems.rotten_blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UndergardenBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UndergardenItems.rotten_blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
            dropWithSilk(UndergardenBlocks.deepturf_block, UndergardenBlocks.deepsoil);
            this.registerLootTable(UndergardenBlocks.double_deepturf.get(), (block) -> func_241749_b_(block, UndergardenBlocks.tall_deepturf.get()));
            this.registerLootTable(UndergardenBlocks.double_shimmerweed.get(), (block) -> func_241749_b_(block, UndergardenBlocks.shimmerweed.get()));
            this.registerLootTable(UndergardenBlocks.tall_deepturf.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UndergardenBlocks.shimmerweed.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UndergardenBlocks.ashen_tall_deepturf.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UndergardenBlocks.glowing_sea_grass.get(), BlockLootTables::onlyWithShears);
            dropSelf(UndergardenBlocks.smogstem_planks);
            dropSelf(UndergardenBlocks.wigglewood_planks);
            dropSelf(UndergardenBlocks.smogstem_log);
            dropSelf(UndergardenBlocks.wigglewood_log);
            dropWithFortune(UndergardenBlocks.coal_ore, Items.COAL);
            dropSelf(UndergardenBlocks.cloggrum_ore);
            dropSelf(UndergardenBlocks.froststeel_ore);
            dropWithFortune(UndergardenBlocks.utherium_ore, UndergardenItems.utherium_chunk);
            dropWithFortune(UndergardenBlocks.otherside_utherium_ore, UndergardenItems.utherium_chunk);
            dropSelf(UndergardenBlocks.regalium_ore);
            dropSelf(UndergardenBlocks.smogstem_sapling);
            dropChanceAdditional(UndergardenBlocks.smogstem_leaves, UndergardenBlocks.smogstem_sapling, UndergardenItems.smogstem_stick, DEFAULT_SAPLING_DROP_RATES);
            dropSelf(UndergardenBlocks.wigglewood_sapling);
            dropChanceAdditional(UndergardenBlocks.wigglewood_leaves, UndergardenBlocks.wigglewood_sapling, UndergardenItems.twistytwig, DEFAULT_SAPLING_DROP_RATES);
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
            this.registerLootTable(UndergardenBlocks.depthrock_pebbles.get(), (p_218534_0_) -> droppingWithSilkTouchOrRandomly(p_218534_0_, UndergardenItems.depthrock_pebble.get(), RandomValueRange.of(1.0F, 3.0F)));
            dropSelf(UndergardenBlocks.gloom_o_lantern);
            this.registerLootTable(UndergardenBlocks.ditchbulb_plant.get(), (p_218525_0_) -> droppingWithShears(p_218525_0_, withExplosionDecay(p_218525_0_, ItemLootEntry.builder(UndergardenItems.ditchbulb.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))));
            dropSelf(UndergardenBlocks.depthrock_stairs);
            dropSelf(UndergardenBlocks.depthrock_brick_stairs);
            dropSelf(UndergardenBlocks.smogstem_stairs);
            dropSelf(UndergardenBlocks.wigglewood_stairs);
            dropSelf(UndergardenBlocks.depthrock_slab);
            dropSelf(UndergardenBlocks.depthrock_brick_slab);
            dropSelf(UndergardenBlocks.smogstem_slab);
            dropSelf(UndergardenBlocks.wigglewood_slab);
            dropSelf(UndergardenBlocks.depthrock_brick_wall);
            dropSelf(UndergardenBlocks.smogstem_fence);
            dropSelf(UndergardenBlocks.wigglewood_fence);
            dropSelf(UndergardenBlocks.cloggrum_block);
            dropSelf(UndergardenBlocks.froststeel_block);
            dropSelf(UndergardenBlocks.utherium_block);
            dropSelf(UndergardenBlocks.cloggrum_bars);
            dropOther(UndergardenBlocks.glowing_kelp, UndergardenItems.glowing_kelp.get());
            dropOther(UndergardenBlocks.glowing_kelp_plant, UndergardenItems.glowing_kelp.get());
            this.registerLootTable(UndergardenBlocks.smogstem_door.get(), (p_218483_0_) -> droppingWhen(p_218483_0_, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            this.registerLootTable(UndergardenBlocks.wigglewood_door.get(), (p_218483_0_) -> droppingWhen(p_218483_0_, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(UndergardenBlocks.smogstem_trapdoor);
            dropSelf(UndergardenBlocks.wigglewood_trapdoor);
            dropWithSilk(UndergardenBlocks.smog_vent, UndergardenBlocks.depthrock);
            this.registerLootTable(UndergardenBlocks.goo.get(), (p_218534_0_) -> droppingWithSilkTouchOrRandomly(p_218534_0_, UndergardenItems.goo_ball.get(), RandomValueRange.of(1.0F, 4.0F)));
            dropWithSilk(UndergardenBlocks.ashen_deepturf, UndergardenBlocks.deepsoil);
            dropSelf(UndergardenBlocks.shiverstone);
            dropSelf(UndergardenBlocks.shiverstone_bricks);
            dropSelf(UndergardenBlocks.shiverstone_slab);
            dropSelf(UndergardenBlocks.shiverstone_brick_slab);
            dropSelf(UndergardenBlocks.shiverstone_brick_wall);
            dropSelf(UndergardenBlocks.shiverstone_stairs);
            dropSelf(UndergardenBlocks.shiverstone_brick_stairs);
            dropSelf(UndergardenBlocks.regalium_block);
            dropSelf(UndergardenBlocks.tremblecrust);
            dropSelf(UndergardenBlocks.tremblecrust_bricks);
            dropSelf(UndergardenBlocks.smogstem_wood);
            dropSelf(UndergardenBlocks.wigglewood_wood);
            dropOther(UndergardenBlocks.shard_torch, UndergardenItems.shard_torch.get());
            dropOther(UndergardenBlocks.shard_wall_torch, UndergardenItems.shard_torch.get());
            this.registerLootTable(UndergardenBlocks.iron_ore.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.IRON_NUGGET).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
            this.registerLootTable(UndergardenBlocks.gold_ore.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.GOLD_NUGGET).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
            dropWithFortune(UndergardenBlocks.diamond_ore, Items.DIAMOND);
            dropOther(UndergardenBlocks.droopvine_top, UndergardenItems.droopvine_item.get());
            dropOther(UndergardenBlocks.droopvine, UndergardenItems.droopvine_item.get());
            dropSelf(UndergardenBlocks.smogstem_fence_gate);
            dropSelf(UndergardenBlocks.wigglewood_fence_gate);
            dropSelf(UndergardenBlocks.coarse_deepsoil);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return UndergardenBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    private static LootTable.Builder func_241749_b_(Block originalBlock, Block newBlock) {
        LootEntry.Builder<?> builder = ItemLootEntry.builder(newBlock).acceptFunction(SetCount.builder(ConstantRange.of(1))).acceptCondition(SHEARS);
        return LootTable.builder().addLootPool(LootPool.builder().addEntry(builder).acceptCondition(BlockStateProperty.builder(originalBlock).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(UndergardenDoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.func_226870_a_().func_235312_a_(BlockPredicate.Builder.func_226243_a_().func_233458_a_(originalBlock).func_233459_a_(StatePropertiesPredicate.Builder.newBuilder().withProp(UndergardenDoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).func_226245_b_()), new BlockPos(0, 1, 0)))).addLootPool(LootPool.builder().addEntry(builder).acceptCondition(BlockStateProperty.builder(originalBlock).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(UndergardenDoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.func_226870_a_().func_235312_a_(BlockPredicate.Builder.func_226243_a_().func_233458_a_(originalBlock).func_233459_a_(StatePropertiesPredicate.Builder.newBuilder().withProp(UndergardenDoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).func_226245_b_()), new BlockPos(0, -1, 0))));
    }

    public static class Entities extends EntityLootTables {

        @Override
        protected void addTables() {
            this.registerLootTable(UndergardenEntities.rotling, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.rotwalker, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.rotbeast, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))))));
            this.registerLootTable(UndergardenEntities.dweller, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.raw_dweller_meat.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.rotdweller, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.gwibling, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.raw_gwibling.get()).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.BONE_MEAL)).acceptCondition(RandomChance.builder(0.05F))));
            this.registerLootTable(UndergardenEntities.brute, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.brute_tusk.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UndergardenEntities.scintling, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.goo_ball.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 2.0F))))));
            this.registerLootTable(UndergardenEntities.blisterbomber, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.rotten_blisterberry.get()).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1.0F, 2.0F))))));
            this.registerLootTable(UndergardenEntities.gloomper, LootTable.builder());
            this.registerLootTable(UndergardenEntities.stoneborn, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.depthrock_pebble.get()).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1.0F, 2.0F))))));

            this.registerLootTable(UndergardenEntities.masticator, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UndergardenItems.masticator_scales.get()).acceptCondition(KilledByPlayer.builder()).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 8.0F))))));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return UndergardenEntities.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

    }
}

