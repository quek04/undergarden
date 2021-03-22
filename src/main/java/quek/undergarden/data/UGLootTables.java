package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.*;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import quek.undergarden.block.*;
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

    private static final ILootCondition.IBuilder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(net.minecraft.block.Blocks.DRAGON_EGG, net.minecraft.block.Blocks.BEACON, net.minecraft.block.Blocks.CONDUIT, net.minecraft.block.Blocks.SKELETON_SKULL, net.minecraft.block.Blocks.WITHER_SKELETON_SKULL, net.minecraft.block.Blocks.PLAYER_HEAD, net.minecraft.block.Blocks.ZOMBIE_HEAD, net.minecraft.block.Blocks.CREEPER_HEAD, net.minecraft.block.Blocks.DRAGON_HEAD, net.minecraft.block.Blocks.SHULKER_BOX, net.minecraft.block.Blocks.BLACK_SHULKER_BOX, net.minecraft.block.Blocks.BLUE_SHULKER_BOX, net.minecraft.block.Blocks.BROWN_SHULKER_BOX, net.minecraft.block.Blocks.CYAN_SHULKER_BOX, net.minecraft.block.Blocks.GRAY_SHULKER_BOX, net.minecraft.block.Blocks.GREEN_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_BLUE_SHULKER_BOX, net.minecraft.block.Blocks.LIGHT_GRAY_SHULKER_BOX, net.minecraft.block.Blocks.LIME_SHULKER_BOX, net.minecraft.block.Blocks.MAGENTA_SHULKER_BOX, net.minecraft.block.Blocks.ORANGE_SHULKER_BOX, net.minecraft.block.Blocks.PINK_SHULKER_BOX, net.minecraft.block.Blocks.PURPLE_SHULKER_BOX, net.minecraft.block.Blocks.RED_SHULKER_BOX, net.minecraft.block.Blocks.WHITE_SHULKER_BOX, net.minecraft.block.Blocks.YELLOW_SHULKER_BOX).map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public UGLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public String getName() {
        return "Undergarden LootTables";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK), Pair.of(Entities::new, LootParameterSets.ENTITY));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
    }

    public static class Blocks extends UGBlockLootTableProvider {

        @Override
        protected void addTables() {
            dropSelf(UGBlocks.DEPTHROCK);
            dropSelf(UGBlocks.DEEPSOIL);
            dropWithSilk(UGBlocks.DEEPSOIL_FARMLAND, UGBlocks.DEEPSOIL);
            this.add(UGBlocks.UNDERBEAN_BUSH.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.UNDERBEAN_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UnderbeanBushBlock.AGE, 3))).add(ItemLootEntry.lootTableItem(UGItems.UNDERBEANS.get())).apply(SetCount.setCount(RandomValueRange.between(2.0F, 3.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.UNDERBEAN_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UnderbeanBushBlock.AGE, 2))).add(ItemLootEntry.lootTableItem(UGItems.UNDERBEANS.get())).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
            this.add(UGBlocks.BLISTERBERRY_BUSH.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 3))).add(ItemLootEntry.lootTableItem(UGItems.BLISTERBERRY.get())).apply(SetCount.setCount(RandomValueRange.between(2.0F, 3.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 2))).add(ItemLootEntry.lootTableItem(UGItems.BLISTERBERRY.get())).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 3))).add(ItemLootEntry.lootTableItem(UGItems.ROTTEN_BLISTERBERRY.get())).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 2))).add(ItemLootEntry.lootTableItem(UGItems.ROTTEN_BLISTERBERRY.get())).apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
            this.add(UGBlocks.DITCHBULB_PLANT.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.DITCHBULB_PLANT.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DitchbulbBlock.AGE, 1))).add(ItemLootEntry.lootTableItem(UGItems.DITCHBULB.get())).apply(SetCount.setCount(ConstantRange.exactly(1))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
            );
            dropWithSilk(UGBlocks.DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
            this.add(UGBlocks.TALL_DEEPTURF.get(), (block) -> droppingSeedsTall(block, UGBlocks.DEEPTURF.get()));
            this.add(UGBlocks.TALL_SHIMMERWEED.get(), (block) -> droppingSeedsTall(block, UGBlocks.SHIMMERWEED.get()));
            this.add(UGBlocks.DEEPTURF.get(), BlockLootTables::createShearsOnlyDrop);
            this.add(UGBlocks.SHIMMERWEED.get(), BlockLootTables::createShearsOnlyDrop);
            this.add(UGBlocks.ASHEN_DEEPTURF.get(), BlockLootTables::createShearsOnlyDrop);
            dropSelf(UGBlocks.SMOGSTEM_PLANKS);
            dropSelf(UGBlocks.WIGGLEWOOD_PLANKS);
            dropSelf(UGBlocks.SMOGSTEM_LOG);
            dropSelf(UGBlocks.WIGGLEWOOD_LOG);
            dropWithFortune(UGBlocks.COAL_ORE, Items.COAL);
            dropSelf(UGBlocks.CLOGGRUM_ORE);
            dropSelf(UGBlocks.FROSTSTEEL_ORE);
            dropWithFortune(UGBlocks.UTHERIUM_ORE, UGItems.UTHERIUM_CHUNK);
            dropWithFortune(UGBlocks.OTHERSIDE_UTHERIUM_ORE, UGItems.UTHERIUM_CHUNK);
            dropSelf(UGBlocks.REGALIUM_ORE);
            dropSelf(UGBlocks.SMOGSTEM_SAPLING);
            this.add(UGBlocks.SMOGSTEM_LEAVES.get(), (leaves) -> createLeavesDrops(leaves, UGBlocks.SMOGSTEM_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
            dropSelf(UGBlocks.WIGGLEWOOD_SAPLING);
            this.add(UGBlocks.WIGGLEWOOD_LEAVES.get(), (leaves) -> createLeavesDrops(leaves, UGBlocks.WIGGLEWOOD_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
            dropSelf(UGBlocks.INDIGO_MUSHROOM);
            dropSelf(UGBlocks.VEIL_MUSHROOM);
            dropSelf(UGBlocks.INK_MUSHROOM);
            dropSelf(UGBlocks.BLOOD_MUSHROOM);
            dropSelf(UGBlocks.DEPTHROCK_BRICKS);
            dropSelf(UGBlocks.CRACKED_DEPTHROCK_BRICKS);
            dropSelf(UGBlocks.GLOOMGOURD);
            dropSelf(UGBlocks.CARVED_GLOOMGOURD);
            this.add(UGBlocks.DEPTHROCK_PEBBLES.get(), (pebble) -> createSingleItemTable(UGItems.DEPTHROCK_PEBBLE.get(), RandomValueRange.between(1.0F, 3.0F)));
            dropSelf(UGBlocks.GLOOM_O_LANTERN);
            dropSelf(UGBlocks.SHARD_O_LANTERN);
            dropSelf(UGBlocks.DEPTHROCK_STAIRS);
            dropSelf(UGBlocks.DEPTHROCK_BRICK_STAIRS);
            dropSelf(UGBlocks.SMOGSTEM_STAIRS);
            dropSelf(UGBlocks.WIGGLEWOOD_STAIRS);
            slab(UGBlocks.DEPTHROCK_SLAB);
            slab(UGBlocks.DEPTHROCK_BRICK_SLAB);
            slab(UGBlocks.SMOGSTEM_SLAB);
            slab(UGBlocks.WIGGLEWOOD_SLAB);
            dropSelf(UGBlocks.DEPTHROCK_BRICK_WALL);
            dropSelf(UGBlocks.SMOGSTEM_FENCE);
            dropSelf(UGBlocks.WIGGLEWOOD_FENCE);
            dropSelf(UGBlocks.CLOGGRUM_BLOCK);
            dropSelf(UGBlocks.FROSTSTEEL_BLOCK);
            dropSelf(UGBlocks.UTHERIUM_BLOCK);
            dropSelf(UGBlocks.CLOGGRUM_BARS);
            dropOther(UGBlocks.GLOWING_KELP, UGItems.GLOWING_KELP.get());
            dropOther(UGBlocks.GLOWING_KELP_PLANT, UGItems.GLOWING_KELP.get());
            this.add(UGBlocks.SMOGSTEM_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            this.add(UGBlocks.WIGGLEWOOD_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(UGBlocks.SMOGSTEM_TRAPDOOR);
            dropSelf(UGBlocks.WIGGLEWOOD_TRAPDOOR);
            dropWithSilk(UGBlocks.SMOG_VENT, UGBlocks.DEPTHROCK);
            this.add(UGBlocks.GOO.get(), (bookshelf) -> createSingleItemTableWithSilkTouch(bookshelf, UGItems.GOO_BALL.get(), RandomValueRange.between(1.0F, 4.0F)));
            dropWithSilk(UGBlocks.ASHEN_DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
            dropSelf(UGBlocks.SHIVERSTONE);
            dropSelf(UGBlocks.SHIVERSTONE_BRICKS);
            slab(UGBlocks.SHIVERSTONE_SLAB);
            slab(UGBlocks.SHIVERSTONE_BRICK_SLAB);
            dropSelf(UGBlocks.SHIVERSTONE_BRICK_WALL);
            dropSelf(UGBlocks.SHIVERSTONE_STAIRS);
            dropSelf(UGBlocks.SHIVERSTONE_BRICK_STAIRS);
            dropSelf(UGBlocks.REGALIUM_BLOCK);
            dropSelf(UGBlocks.TREMBLECRUST);
            dropSelf(UGBlocks.TREMBLECRUST_BRICKS);
            dropSelf(UGBlocks.CRACKED_TREMBLECRUST_BRICKS);
            dropSelf(UGBlocks.SMOGSTEM_WOOD);
            dropSelf(UGBlocks.WIGGLEWOOD_WOOD);
            dropOther(UGBlocks.SHARD_TORCH, UGItems.SHARD_TORCH.get());
            dropOther(UGBlocks.SHARD_WALL_TORCH, UGItems.SHARD_TORCH.get());
            this.add(UGBlocks.IRON_ORE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(Items.IRON_NUGGET).apply(SetCount.setCount(RandomValueRange.between(2.0F, 6.0F))).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
            this.add(UGBlocks.GOLD_ORE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(Items.GOLD_NUGGET).apply(SetCount.setCount(RandomValueRange.between(2.0F, 6.0F))).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
            dropWithFortune(UGBlocks.DIAMOND_ORE, Items.DIAMOND);
            dropOther(UGBlocks.DROOPVINE, UGItems.DROOPFRUIT.get());
            this.add(UGBlocks.DROOPVINE.get(), LootTable.lootTable()
                    .withPool(LootPool.lootPool().when(BlockStateProperty.hasBlockStateProperties(UGBlocks.DROOPVINE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DroopvineBlock.GLOWY, true))).add(ItemLootEntry.lootTableItem(UGItems.DROOPFRUIT.get())).apply(SetCount.setCount(ConstantRange.exactly(1))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
            );
            dropSelf(UGBlocks.SMOGSTEM_FENCE_GATE);
            dropSelf(UGBlocks.WIGGLEWOOD_FENCE_GATE);
            dropSelf(UGBlocks.COARSE_DEEPSOIL);
            dropSelf(UGBlocks.SMOGSTEM_BUTTON);
            dropSelf(UGBlocks.WIGGLEWOOD_BUTTON);
            dropSelf(UGBlocks.DEPTHROCK_BUTTON);
            dropSelf(UGBlocks.SHIVERSTONE_BUTTON);
            dropSelf(UGBlocks.SMOGSTEM_PRESSURE_PLATE);
            dropSelf(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE);
            dropSelf(UGBlocks.DEPTHROCK_PRESSURE_PLATE);
            dropSelf(UGBlocks.SHIVERSTONE_PRESSURE_PLATE);
            dropSelf(UGBlocks.GRONGLET);
            dropWithFortune(UGBlocks.GRONGLE_CAP, UGBlocks.GRONGLET.get().asItem());
            dropSelf(UGBlocks.GRONGLE_STEM);
            dropSelf(UGBlocks.GRONGLE_PLANKS);
            dropSelf(UGBlocks.GRONGLE_HYPHAE);
            dropSelf(UGBlocks.GRONGLE_STAIRS);
            slab(UGBlocks.GRONGLE_SLAB);
            dropSelf(UGBlocks.GRONGLE_FENCE);
            dropSelf(UGBlocks.GRONGLE_FENCE_GATE);
            this.add(UGBlocks.GRONGLE_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(UGBlocks.GRONGLE_TRAPDOOR);
            dropSelf(UGBlocks.GRONGLE_BUTTON);
            dropSelf(UGBlocks.GRONGLE_PRESSURE_PLATE);
            dropSelf(UGBlocks.STRIPPED_SMOGSTEM_LOG);
            dropSelf(UGBlocks.STRIPPED_WIGGLEWOOD_LOG);
            dropSelf(UGBlocks.STRIPPED_GRONGLE_STEM);
            dropSelf(UGBlocks.STRIPPED_SMOGSTEM_WOOD);
            dropSelf(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD);
            dropSelf(UGBlocks.STRIPPED_GRONGLE_HYPHAE);
            this.add(UGBlocks.GLOOMGOURD_STEM.get(), (stem) -> createStemDrops(stem, UGItems.GLOOMGOURD_SEEDS.get()));
            this.add(UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(), (stem) -> dropSeedsForStem(stem, UGItems.GLOOMGOURD_SEEDS.get()));
            dropSelf(UGBlocks.CRACKED_SHIVERSTONE_BRICKS);
            dropSelf(UGBlocks.DEPTHROCK_WALL);
            dropSelf(UGBlocks.SHIVERSTONE_WALL);
            this.add(UGBlocks.BLOOD_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.BLOOD_MUSHROOM.get()));
            dropSelf(UGBlocks.BLOOD_MUSHROOM_GLOBULE);
            dropAsSilk(UGBlocks.BLOOD_MUSHROOM_STALK);
            this.add(UGBlocks.INDIGO_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.INDIGO_MUSHROOM.get()));
            dropAsSilk(UGBlocks.INDIGO_MUSHROOM_STALK);
            this.add(UGBlocks.VEIL_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.VEIL_MUSHROOM.get()));
            dropAsSilk(UGBlocks.VEIL_MUSHROOM_STALK);
            this.add(UGBlocks.INK_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.INK_MUSHROOM.get()));
            dropSelf(UGBlocks.FORGOTTEN_BLOCK);
            dropSelf(UGBlocks.CHISELED_DEPTHROCK_BRICKS);
            dropSelf(UGBlocks.CHISELED_SHIVERSTONE_BRICKS);
            dropPottedContents(UGBlocks.POTTED_SMOGSTEM_SAPLING.get());
            dropPottedContents(UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get());
            dropPottedContents(UGBlocks.POTTED_SHIMMERWEED.get());
            dropPottedContents(UGBlocks.POTTED_INDIGO_MUSHROOM.get());
            dropPottedContents(UGBlocks.POTTED_VEIL_MUSHROOM.get());
            dropPottedContents(UGBlocks.POTTED_INK_MUSHROOM.get());
            dropPottedContents(UGBlocks.POTTED_BLOOD_MUSHROOM.get());
            dropPottedContents(UGBlocks.POTTED_GRONGLET.get());
            dropWithSilk(UGBlocks.FROZEN_DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
            this.add(UGBlocks.FROZEN_DEEPTURF.get(), BlockLootTables::createShearsOnlyDrop);
            dropSelf(UGBlocks.CHISELED_TREMBLECRUST_BRICKS);
            dropSelf(UGBlocks.TREMBLECRUST_STAIRS);
            dropSelf(UGBlocks.TREMBLECRUST_BRICK_STAIRS);
            slab(UGBlocks.TREMBLECRUST_SLAB);
            slab(UGBlocks.TREMBLECRUST_BRICK_SLAB);
            dropSelf(UGBlocks.TREMBLECRUST_WALL);
            dropSelf(UGBlocks.TREMBLECRUST_BRICK_WALL);
            dropSelf(UGBlocks.TREMBLECRUST_BUTTON);
            dropSelf(UGBlocks.TREMBLECRUST_PRESSURE_PLATE);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return UGBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    private static LootTable.Builder droppingSeedsTall(Block originalBlock, Block newBlock) {
        LootEntry.Builder<?> builder = ItemLootEntry.lootTableItem(newBlock).apply(SetCount.setCount(ConstantRange.exactly(1))).when(SHEARS);
        return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(BlockStateProperty.hasBlockStateProperties(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UGDoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UGDoublePlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(BlockStateProperty.hasBlockStateProperties(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UGDoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UGDoublePlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }

    private static LootTable.Builder dropSeedsForStem(Block stem, Item stemSeed) {
        return LootTable.lootTable().withPool(withExplosionDecay(stem, LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(stemSeed).apply(SetCount.setCount(BinomialRange.binomial(3, 0.53333336F))))));
    }

    protected static <T> T withExplosionDecay(IItemProvider item, ILootFunctionConsumer<T> function) {
        return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? function.apply(ExplosionDecay.explosionDecay()) : function.unwrap();
    }

    public static class Entities extends EntityLootTables {

        @Override
        protected void addTables() {
            this.add(UGEntityTypes.ROTLING.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.UTHERIC_SHARD.get()).apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))).when(KilledByPlayer.killedByPlayer()))));
            this.add(UGEntityTypes.ROTWALKER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.UTHERIC_SHARD.get()).apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))).when(KilledByPlayer.killedByPlayer()))));
            this.add(UGEntityTypes.ROTBEAST.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.UTHERIC_SHARD.get()).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))).when(KilledByPlayer.killedByPlayer()))));
            this.add(UGEntityTypes.DWELLER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(Items.LEATHER).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.RAW_DWELLER_MEAT.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))).apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));
            this.add(UGEntityTypes.ROTDWELLER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(Items.LEATHER).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));
            this.add(UGEntityTypes.GWIBLING.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.RAW_GWIBLING.get()).apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))).when(KilledByPlayer.killedByPlayer())).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(Items.BONE_MEAL)).when(RandomChance.randomChance(0.05F))));
            this.add(UGEntityTypes.BRUTE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.BRUTE_TUSK.get()).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));
            this.add(UGEntityTypes.SCINTLING.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.GOO_BALL.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 2.0F))))));
            this.add(UGEntityTypes.GLOOMPER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(Items.LEATHER).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.RAW_GLOOMPER_LEG.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));
            this.add(UGEntityTypes.STONEBORN.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get()).apply(SetCount.setCount(RandomValueRange.between(3.0F, 6.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 2.0F))))));
            this.add(UGEntityTypes.NARGOYLE.get(), LootTable.lootTable());
            this.add(UGEntityTypes.MUNCHER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(RandomValueRange.between(0.0F, 3.0F)).add(ItemLootEntry.lootTableItem(UGItems.CLOGGRUM_NUGGET.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))))).withPool(LootPool.lootPool().setRolls(RandomValueRange.between(0.0F, 1.0F)).add(ItemLootEntry.lootTableItem(UGItems.FROSTSTEEL_NUGGET.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))))));
            this.add(UGEntityTypes.SPLOOGIE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get()).apply(SetCount.setCount(RandomValueRange.between(3.0F, 6.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1.0F, 2.0F))))));
            this.add(UGEntityTypes.GWIB.get(), LootTable.lootTable());

            this.add(UGEntityTypes.MASTICATOR.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.MASTICATOR_SCALES.get()).when(KilledByPlayer.killedByPlayer()).apply(SetCount.setCount(RandomValueRange.between(4.0F, 8.0F))))));
            this.add(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(UGItems.FORGOTTEN_NUGGET.get()).when(KilledByPlayer.killedByPlayer()).apply(SetCount.setCount(RandomValueRange.between(0.0F, 16.0F))))));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return UGEntityTypes.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}