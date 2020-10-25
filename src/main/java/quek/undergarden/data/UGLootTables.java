package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import quek.undergarden.block.world.UGDoublePlantBlock;
import net.minecraft.item.Item;
import net.minecraft.loot.conditions.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
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
import quek.undergarden.data.provider.UGBlockLootTableProvider;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UGLootTables extends LootTableProvider {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(net.minecraft.block.Blocks.DRAGON_EGG, net.minecraft.block.Blocks.BEACON, net.minecraft.block.Blocks.CONDUIT, net.minecraft.block.Blocks.SKELETON_SKULL, net.minecraft.block.Blocks.WITHER_SKELETON_SKULL, net.minecraft.block.Blocks.PLAYER_HEAD, net.minecraft.block.Blocks.ZOMBIE_HEAD, net.minecraft.block.Blocks.CREEPER_HEAD, net.minecraft.block.Blocks.DRAGON_HEAD, net.minecraft.block.Blocks.SHULKER_BOX, net.minecraft.block.Blocks.BLACK_SHULKER_BOX, net.minecraft.block.Blocks.BLUE_SHULKER_BOX, net.minecraft.block.Blocks.BROWN_SHULKER_BOX, net.minecraft.block.Blocks.CYAN_SHULKER_BOX, net.minecraft.block.Blocks.GRAY_SHULKER_BOX, net.minecraft.block.Blocks.GREEN_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_BLUE_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_GRAY_SHULKER_BOX, net.minecraft.block.Blocks.LIME_SHULKER_BOX, net.minecraft.block.Blocks.MAGENTA_SHULKER_BOX, net.minecraft.block.Blocks.ORANGE_SHULKER_BOX, net.minecraft.block.Blocks.PINK_SHULKER_BOX, net.minecraft.block.Blocks.PURPLE_SHULKER_BOX, net.minecraft.block.Blocks.RED_SHULKER_BOX, net.minecraft.block.Blocks.WHITE_SHULKER_BOX, net.minecraft.block.Blocks.YELLOW_SHULKER_BOX).map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    public UGLootTables(DataGenerator dataGeneratorIn) {
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

    public static class Blocks extends UGBlockLootTableProvider {

        @Override
        protected void addTables() {
            dropSelf(UGBlocks.depthrock);
            dropSelf(UGBlocks.deepsoil);
            dropWithSilk(UGBlocks.deepsoil_farmland, UGBlocks.deepsoil);
            this.registerLootTable(UGBlocks.underbean_bush.get(),
                    LootTable.builder().addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.underbean_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BeanBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UGItems.underbeans.get())).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))).addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.underbean_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BeanBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UGItems.underbeans.get())).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
            this.registerLootTable(UGBlocks.blisterberry_bush.get(),
                    LootTable.builder()
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UGItems.blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UGItems.blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UGItems.rotten_blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.blisterberry_bush.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UGItems.rotten_blisterberry.get())).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
            dropWithSilk(UGBlocks.deepturf_block, UGBlocks.deepsoil);
            this.registerLootTable(UGBlocks.double_deepturf.get(), (block) -> droppingSeedsTall(block, UGBlocks.tall_deepturf.get()));
            this.registerLootTable(UGBlocks.double_shimmerweed.get(), (block) -> droppingSeedsTall(block, UGBlocks.shimmerweed.get()));
            this.registerLootTable(UGBlocks.tall_deepturf.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UGBlocks.shimmerweed.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UGBlocks.ashen_tall_deepturf.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UGBlocks.glowing_sea_grass.get(), BlockLootTables::onlyWithShears);
            dropSelf(UGBlocks.smogstem_planks);
            dropSelf(UGBlocks.wigglewood_planks);
            dropSelf(UGBlocks.smogstem_log);
            dropSelf(UGBlocks.wigglewood_log);
            dropWithFortune(UGBlocks.coal_ore, Items.COAL);
            dropSelf(UGBlocks.cloggrum_ore);
            dropSelf(UGBlocks.froststeel_ore);
            dropWithFortune(UGBlocks.utherium_ore, UGItems.utherium_chunk);
            dropWithFortune(UGBlocks.otherside_utherium_ore, UGItems.utherium_chunk);
            dropSelf(UGBlocks.regalium_ore);
            dropSelf(UGBlocks.smogstem_sapling);
            dropChanceAdditional(UGBlocks.smogstem_leaves, UGBlocks.smogstem_sapling, UGItems.smogstem_stick, DEFAULT_SAPLING_DROP_RATES);
            dropSelf(UGBlocks.wigglewood_sapling);
            dropChanceAdditional(UGBlocks.wigglewood_leaves, UGBlocks.wigglewood_sapling, UGItems.twistytwig, DEFAULT_SAPLING_DROP_RATES);
            dropSelf(UGBlocks.indigo_mushroom);
            dropSelf(UGBlocks.veil_mushroom);
            dropSelf(UGBlocks.ink_mushroom);
            dropSelf(UGBlocks.blood_mushroom);
            dropSelf(UGBlocks.depthrock_bricks);
            dropSelf(UGBlocks.cracked_depthrock_bricks);
            dropOther(UGBlocks.smogstem_torch, UGItems.smogstem_torch.get());
            dropOther(UGBlocks.smogstem_wall_torch, UGItems.smogstem_torch.get());
            dropSelf(UGBlocks.gloomgourd);
            dropSelf(UGBlocks.carved_gloomgourd);
            this.registerLootTable(UGBlocks.depthrock_pebbles.get(), (bookshelf) -> droppingWithSilkTouchOrRandomly(bookshelf, UGItems.depthrock_pebble.get(), RandomValueRange.of(1.0F, 3.0F)));
            dropSelf(UGBlocks.gloom_o_lantern);
            this.registerLootTable(UGBlocks.ditchbulb_plant.get(), (deadBush) -> droppingWithShears(deadBush, withExplosionDecay(deadBush, ItemLootEntry.builder(UGItems.ditchbulb.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))));
            dropSelf(UGBlocks.depthrock_stairs);
            dropSelf(UGBlocks.depthrock_brick_stairs);
            dropSelf(UGBlocks.smogstem_stairs);
            dropSelf(UGBlocks.wigglewood_stairs);
            dropSelf(UGBlocks.depthrock_slab);
            dropSelf(UGBlocks.depthrock_brick_slab);
            dropSelf(UGBlocks.smogstem_slab);
            dropSelf(UGBlocks.wigglewood_slab);
            dropSelf(UGBlocks.depthrock_brick_wall);
            dropSelf(UGBlocks.smogstem_fence);
            dropSelf(UGBlocks.wigglewood_fence);
            dropSelf(UGBlocks.cloggrum_block);
            dropSelf(UGBlocks.froststeel_block);
            dropSelf(UGBlocks.utherium_block);
            dropSelf(UGBlocks.cloggrum_bars);
            dropOther(UGBlocks.glowing_kelp, UGItems.glowing_kelp.get());
            dropOther(UGBlocks.glowing_kelp_plant, UGItems.glowing_kelp.get());
            this.registerLootTable(UGBlocks.smogstem_door.get(), (block) -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            this.registerLootTable(UGBlocks.wigglewood_door.get(), (block) -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(UGBlocks.smogstem_trapdoor);
            dropSelf(UGBlocks.wigglewood_trapdoor);
            dropWithSilk(UGBlocks.smog_vent, UGBlocks.depthrock);
            this.registerLootTable(UGBlocks.goo.get(), (bookshelf) -> droppingWithSilkTouchOrRandomly(bookshelf, UGItems.goo_ball.get(), RandomValueRange.of(1.0F, 4.0F)));
            dropWithSilk(UGBlocks.ashen_deepturf, UGBlocks.deepsoil);
            dropSelf(UGBlocks.shiverstone);
            dropSelf(UGBlocks.shiverstone_bricks);
            dropSelf(UGBlocks.shiverstone_slab);
            dropSelf(UGBlocks.shiverstone_brick_slab);
            dropSelf(UGBlocks.shiverstone_brick_wall);
            dropSelf(UGBlocks.shiverstone_stairs);
            dropSelf(UGBlocks.shiverstone_brick_stairs);
            dropSelf(UGBlocks.regalium_block);
            dropSelf(UGBlocks.tremblecrust);
            dropSelf(UGBlocks.tremblecrust_bricks);
            dropSelf(UGBlocks.smogstem_wood);
            dropSelf(UGBlocks.wigglewood_wood);
            dropOther(UGBlocks.shard_torch, UGItems.shard_torch.get());
            dropOther(UGBlocks.shard_wall_torch, UGItems.shard_torch.get());
            this.registerLootTable(UGBlocks.iron_ore.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.IRON_NUGGET).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
            this.registerLootTable(UGBlocks.gold_ore.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.GOLD_NUGGET).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
            dropWithFortune(UGBlocks.diamond_ore, Items.DIAMOND);
            dropOther(UGBlocks.droopvine_top, UGItems.droopvine_item.get());
            dropOther(UGBlocks.droopvine, UGItems.droopvine_item.get());
            dropSelf(UGBlocks.smogstem_fence_gate);
            dropSelf(UGBlocks.wigglewood_fence_gate);
            dropSelf(UGBlocks.coarse_deepsoil);
            dropSelf(UGBlocks.smogstem_button);
            dropSelf(UGBlocks.wigglewood_button);
            dropSelf(UGBlocks.depthrock_button);
            dropSelf(UGBlocks.shiverstone_button);
            dropSelf(UGBlocks.smogstem_pressure_plate);
            dropSelf(UGBlocks.wigglewood_pressure_plate);
            dropSelf(UGBlocks.depthrock_pressure_plate);
            dropSelf(UGBlocks.shiverstone_pressure_plate);
            dropSelf(UGBlocks.gronglet);
            dropWithFortune(UGBlocks.grongle_cap, UGBlocks.gronglet.get().asItem());
            dropSelf(UGBlocks.grongle_stem);
            dropSelf(UGBlocks.grongle_planks);
            dropSelf(UGBlocks.grongle_hyphae);
            dropSelf(UGBlocks.grongle_stairs);
            dropSelf(UGBlocks.grongle_slab);
            dropSelf(UGBlocks.grongle_fence);
            dropSelf(UGBlocks.grongle_fence_gate);
            this.registerLootTable(UGBlocks.grongle_door.get(), (block) -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(UGBlocks.grongle_trapdoor);
            dropSelf(UGBlocks.grongle_button);
            dropSelf(UGBlocks.grongle_pressure_plate);
            dropSelf(UGBlocks.stripped_smogstem_log);
            dropSelf(UGBlocks.stripped_wigglewood_log);
            dropSelf(UGBlocks.stripped_grongle_stem);
            dropSelf(UGBlocks.stripped_smogstem_wood);
            dropSelf(UGBlocks.stripped_wigglewood_wood);
            dropSelf(UGBlocks.stripped_grongle_hyphae);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return UGBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    private static LootTable.Builder droppingSeedsTall(Block originalBlock, Block newBlock) {
        LootEntry.Builder<?> builder = ItemLootEntry.builder(newBlock).acceptFunction(SetCount.builder(ConstantRange.of(1))).acceptCondition(SHEARS);
        return LootTable.builder().addLootPool(LootPool.builder().addEntry(builder).acceptCondition(BlockStateProperty.builder(originalBlock).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(UGDoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.builder().block(BlockPredicate.Builder.createBuilder().setBlock(originalBlock).setStatePredicate(StatePropertiesPredicate.Builder.newBuilder().withProp(UGDoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).addLootPool(LootPool.builder().addEntry(builder).acceptCondition(BlockStateProperty.builder(originalBlock).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(UGDoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).acceptCondition(LocationCheck.func_241547_a_(LocationPredicate.Builder.builder().block(BlockPredicate.Builder.createBuilder().setBlock(originalBlock).setStatePredicate(StatePropertiesPredicate.Builder.newBuilder().withProp(UGDoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }

    public static class Entities extends EntityLootTables {

        @Override
        protected void addTables() {
            this.registerLootTable(UGEntityTypes.rotling, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.rotwalker, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.rotbeast, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.utheric_shard.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))))));
            this.registerLootTable(UGEntityTypes.dweller, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.raw_dweller_meat.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.rotdweller, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.gwibling, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.raw_gwibling.get()).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.BONE_MEAL)).acceptCondition(RandomChance.builder(0.05F))));
            this.registerLootTable(UGEntityTypes.brute, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.brute_tusk.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.scintling, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.goo_ball.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 2.0F))))));
            this.registerLootTable(UGEntityTypes.gloomper, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.stoneborn, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.depthrock_pebble.get()).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1.0F, 2.0F))))));

            this.registerLootTable(UGEntityTypes.masticator, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.masticator_scales.get()).acceptCondition(KilledByPlayer.builder()).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 8.0F))))));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return UGEntityTypes.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

    }
}

