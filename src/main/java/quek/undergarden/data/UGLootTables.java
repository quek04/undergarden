package quek.undergarden.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FlowerPotBlock;
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
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.UGDoublePlantBlock;
import quek.undergarden.block.UnderbeanBushBlock;
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
            this.registerLootTable(UGBlocks.UNDERBEAN_BUSH.get(),
                    LootTable.builder().addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.UNDERBEAN_BUSH.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(UnderbeanBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UGItems.UNDERBEANS.get())).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))).addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.UNDERBEAN_BUSH.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(UnderbeanBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UGItems.UNDERBEANS.get())).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
            this.registerLootTable(UGBlocks.BLISTERBERRY_BUSH.get(),
                    LootTable.builder()
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.BLISTERBERRY_BUSH.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UGItems.BLISTERBERRY.get())).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.BLISTERBERRY_BUSH.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UGItems.BLISTERBERRY.get())).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.BLISTERBERRY_BUSH.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 3))).addEntry(ItemLootEntry.builder(UGItems.ROTTEN_BLISTERBERRY.get())).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))
                            .addLootPool(LootPool.builder().acceptCondition(BlockStateProperty.builder(UGBlocks.BLISTERBERRY_BUSH.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BlisterberryBushBlock.AGE, 2))).addEntry(ItemLootEntry.builder(UGItems.ROTTEN_BLISTERBERRY.get())).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
            dropWithSilk(UGBlocks.DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
            this.registerLootTable(UGBlocks.TALL_DEEPTURF.get(), (block) -> droppingSeedsTall(block, UGBlocks.DEEPTURF.get()));
            this.registerLootTable(UGBlocks.TALL_SHIMMERWEED.get(), (block) -> droppingSeedsTall(block, UGBlocks.SHIMMERWEED.get()));
            this.registerLootTable(UGBlocks.DEEPTURF.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UGBlocks.SHIMMERWEED.get(), BlockLootTables::onlyWithShears);
            this.registerLootTable(UGBlocks.ASHEN_DEEPTURF.get(), BlockLootTables::onlyWithShears);
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
            this.registerLootTable(UGBlocks.SMOGSTEM_LEAVES.get(), (leaves) -> droppingWithChancesAndSticks(leaves, UGBlocks.SMOGSTEM_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
            dropSelf(UGBlocks.WIGGLEWOOD_SAPLING);
            this.registerLootTable(UGBlocks.WIGGLEWOOD_LEAVES.get(), (leaves) -> droppingWithChancesAndSticks(leaves, UGBlocks.WIGGLEWOOD_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
            dropSelf(UGBlocks.INDIGO_MUSHROOM);
            dropSelf(UGBlocks.VEIL_MUSHROOM);
            dropSelf(UGBlocks.INK_MUSHROOM);
            dropSelf(UGBlocks.BLOOD_MUSHROOM);
            dropSelf(UGBlocks.DEPTHROCK_BRICKS);
            dropSelf(UGBlocks.CRACKED_DEPTHROCK_BRICKS);
            dropSelf(UGBlocks.STONEBORN_EFFIGY);
            dropSelf(UGBlocks.GLOOMGOURD);
            dropSelf(UGBlocks.CARVED_GLOOMGOURD);
            this.registerLootTable(UGBlocks.DEPTHROCK_PEBBLES.get(), (pebble) -> droppingRandomly(UGItems.DEPTHROCK_PEBBLE.get(), RandomValueRange.of(1.0F, 3.0F)));
            dropSelf(UGBlocks.GLOOM_O_LANTERN);
            this.registerLootTable(UGBlocks.DITCHBULB_PLANT.get(), (ditchbulb) -> droppingWithShears(ditchbulb, withExplosionDecay(ditchbulb, ItemLootEntry.builder(UGItems.DITCHBULB.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))));
            dropSelf(UGBlocks.DEPTHROCK_STAIRS);
            dropSelf(UGBlocks.DEPTHROCK_BRICK_STAIRS);
            dropSelf(UGBlocks.SMOGSTEM_STAIRS);
            dropSelf(UGBlocks.WIGGLEWOOD_STAIRS);
            dropSelf(UGBlocks.DEPTHROCK_SLAB);
            dropSelf(UGBlocks.DEPTHROCK_BRICK_SLAB);
            dropSelf(UGBlocks.SMOGSTEM_SLAB);
            dropSelf(UGBlocks.WIGGLEWOOD_SLAB);
            dropSelf(UGBlocks.DEPTHROCK_BRICK_WALL);
            dropSelf(UGBlocks.SMOGSTEM_FENCE);
            dropSelf(UGBlocks.WIGGLEWOOD_FENCE);
            dropSelf(UGBlocks.CLOGGRUM_BLOCK);
            dropSelf(UGBlocks.FROSTSTEEL_BLOCK);
            dropSelf(UGBlocks.UTHERIUM_BLOCK);
            dropSelf(UGBlocks.CLOGGRUM_BARS);
            dropOther(UGBlocks.GLOWING_KELP, UGItems.GLOWING_KELP.get());
            dropOther(UGBlocks.GLOWING_KELP_PLANT, UGItems.GLOWING_KELP.get());
            this.registerLootTable(UGBlocks.SMOGSTEM_DOOR.get(), (block) -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            this.registerLootTable(UGBlocks.WIGGLEWOOD_DOOR.get(), (block) -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(UGBlocks.SMOGSTEM_TRAPDOOR);
            dropSelf(UGBlocks.WIGGLEWOOD_TRAPDOOR);
            dropWithSilk(UGBlocks.SMOG_VENT, UGBlocks.DEPTHROCK);
            this.registerLootTable(UGBlocks.GOO.get(), (bookshelf) -> droppingWithSilkTouchOrRandomly(bookshelf, UGItems.GOO_BALL.get(), RandomValueRange.of(1.0F, 4.0F)));
            dropWithSilk(UGBlocks.ASHEN_DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
            dropSelf(UGBlocks.SHIVERSTONE);
            dropSelf(UGBlocks.SHIVERSTONE_BRICKS);
            dropSelf(UGBlocks.SHIVERSTONE_SLAB);
            dropSelf(UGBlocks.SHIVERSTONE_BRICK_SLAB);
            dropSelf(UGBlocks.SHIVERSTONE_BRICK_WALL);
            dropSelf(UGBlocks.SHIVERSTONE_STAIRS);
            dropSelf(UGBlocks.SHIVERSTONE_BRICK_STAIRS);
            dropSelf(UGBlocks.REGALIUM_BLOCK);
            dropSelf(UGBlocks.TREMBLECRUST);
            dropSelf(UGBlocks.TREMBLECRUST_BRICKS);
            dropSelf(UGBlocks.SMOGSTEM_WOOD);
            dropSelf(UGBlocks.WIGGLEWOOD_WOOD);
            dropOther(UGBlocks.SHARD_TORCH, UGItems.SHARD_TORCH.get());
            dropOther(UGBlocks.SHARD_WALL_TORCH, UGItems.SHARD_TORCH.get());
            this.registerLootTable(UGBlocks.IRON_ORE.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.IRON_NUGGET).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
            this.registerLootTable(UGBlocks.GOLD_ORE.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.GOLD_NUGGET).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 6.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
            dropWithFortune(UGBlocks.DIAMOND_ORE, Items.DIAMOND);
            dropOther(UGBlocks.DROOPVINE_TOP, UGItems.DROOPVINE.get());
            dropOther(UGBlocks.DROOPVINE, UGItems.DROOPVINE.get());
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
            dropSelf(UGBlocks.GRONGLE_SLAB);
            dropSelf(UGBlocks.GRONGLE_FENCE);
            dropSelf(UGBlocks.GRONGLE_FENCE_GATE);
            this.registerLootTable(UGBlocks.GRONGLE_DOOR.get(), (block) -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(UGBlocks.GRONGLE_TRAPDOOR);
            dropSelf(UGBlocks.GRONGLE_BUTTON);
            dropSelf(UGBlocks.GRONGLE_PRESSURE_PLATE);
            dropSelf(UGBlocks.STRIPPED_SMOGSTEM_LOG);
            dropSelf(UGBlocks.STRIPPED_WIGGLEWOOD_LOG);
            dropSelf(UGBlocks.STRIPPED_GRONGLE_STEM);
            dropSelf(UGBlocks.STRIPPED_SMOGSTEM_WOOD);
            dropSelf(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD);
            dropSelf(UGBlocks.STRIPPED_GRONGLE_HYPHAE);
            this.registerLootTable(UGBlocks.GLOOMGOURD_STEM.get(), (stem) -> droppingByAge(stem, UGItems.GLOOMGOURD_SEEDS.get()));
            this.registerLootTable(UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(), (stem) -> dropSeedsForStem(stem, UGItems.GLOOMGOURD_SEEDS.get()));
            dropSelf(UGBlocks.CRACKED_SHIVERSTONE_BRICKS);
            dropSelf(UGBlocks.DEPTHROCK_WALL);
            dropSelf(UGBlocks.SHIVERSTONE_WALL);
            this.registerLootTable(UGBlocks.BLOOD_MUSHROOM_CAP.get(), (mushroom) -> droppingItemRarely(mushroom, UGBlocks.BLOOD_MUSHROOM.get()));
            dropSelf(UGBlocks.BLOOD_MUSHROOM_GLOBULE);
            dropAsSilk(UGBlocks.BLOOD_MUSHROOM_STALK);
            this.registerLootTable(UGBlocks.INDIGO_MUSHROOM_CAP.get(), (mushroom) -> droppingItemRarely(mushroom, UGBlocks.INDIGO_MUSHROOM.get()));
            dropAsSilk(UGBlocks.INDIGO_MUSHROOM_STALK);
            this.registerLootTable(UGBlocks.VEIL_MUSHROOM_CAP.get(), (mushroom) -> droppingItemRarely(mushroom, UGBlocks.VEIL_MUSHROOM.get()));
            dropAsSilk(UGBlocks.VEIL_MUSHROOM_STALK);
            this.registerLootTable(UGBlocks.INK_MUSHROOM_CAP.get(), (mushroom) -> droppingItemRarely(mushroom, UGBlocks.INK_MUSHROOM.get()));
            dropSelf(UGBlocks.FORGOTTEN_BLOCK);
            dropSelf(UGBlocks.CHISELED_DEPTHROCK_BRICKS);
            dropSelf(UGBlocks.CHISELED_SHIVERSTONE_BRICKS);
            registerFlowerPot(UGBlocks.POTTED_SMOGSTEM_SAPLING.get());
            registerFlowerPot(UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get());
            registerFlowerPot(UGBlocks.POTTED_SHIMMERWEED.get());
            registerFlowerPot(UGBlocks.POTTED_INDIGO_MUSHROOM.get());
            registerFlowerPot(UGBlocks.POTTED_VEIL_MUSHROOM.get());
            registerFlowerPot(UGBlocks.POTTED_INK_MUSHROOM.get());
            registerFlowerPot(UGBlocks.POTTED_BLOOD_MUSHROOM.get());
            registerFlowerPot(UGBlocks.POTTED_GRONGLET.get());
            registerFlowerPot(UGBlocks.POTTED_DITCHBULB.get());
            dropWithSilk(UGBlocks.FROZEN_DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
            this.registerLootTable(UGBlocks.FROZEN_DEEPTURF.get(), BlockLootTables::onlyWithShears);
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

    private static LootTable.Builder dropSeedsForStem(Block stem, Item stemSeed) {
        return LootTable.builder().addLootPool(withExplosionDecay(stem, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(stemSeed).acceptFunction(SetCount.builder(BinomialRange.of(3, 0.53333336F))))));
    }

    protected static <T> T withExplosionDecay(IItemProvider item, ILootFunctionConsumer<T> function) {
        return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? function.acceptFunction(ExplosionDecay.builder()) : function.cast();
    }

    public static class Entities extends EntityLootTables {

        @Override
        protected void addTables() {
            this.registerLootTable(UGEntityTypes.ROTLING.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.UTHERIC_SHARD.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))).acceptCondition(KilledByPlayer.builder()))));
            this.registerLootTable(UGEntityTypes.ROTWALKER.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.UTHERIC_SHARD.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))).acceptCondition(KilledByPlayer.builder()))));
            this.registerLootTable(UGEntityTypes.ROTBEAST.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.UTHERIC_SHARD.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))).acceptCondition(KilledByPlayer.builder()))));
            this.registerLootTable(UGEntityTypes.DWELLER.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.RAW_DWELLER_MEAT.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.ROTDWELLER.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.GWIBLING.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.RAW_GWIBLING.get()).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.BONE_MEAL)).acceptCondition(RandomChance.builder(0.05F))));
            this.registerLootTable(UGEntityTypes.BRUTE.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.BRUTE_TUSK.get()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.SCINTLING.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.GOO_BALL.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 2.0F))))));
            this.registerLootTable(UGEntityTypes.GLOOMPER.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.RAW_GLOOMPER_LEG.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
            this.registerLootTable(UGEntityTypes.STONEBORN.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.DEPTHROCK_PEBBLE.get()).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1.0F, 2.0F))))));
            this.registerLootTable(UGEntityTypes.NARGOYLE.get(), LootTable.builder());
            this.registerLootTable(UGEntityTypes.MUNCHER.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(RandomValueRange.of(0.0F, 3.0F)).addEntry(ItemLootEntry.builder(UGItems.CLOGGRUM_NUGGET.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))).addLootPool(LootPool.builder().rolls(RandomValueRange.of(0.0F, 1.0F)).addEntry(ItemLootEntry.builder(UGItems.FROSTSTEEL_NUGGET.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))));
            this.registerLootTable(UGEntityTypes.SPLOOGIE.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.DEPTHROCK_PEBBLE.get()).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1.0F, 2.0F))))));

            this.registerLootTable(UGEntityTypes.MASTICATOR.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.MASTICATOR_SCALES.get()).acceptCondition(KilledByPlayer.builder()).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 8.0F))))));
            this.registerLootTable(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(UGItems.FORGOTTEN_NUGGET.get()).acceptCondition(KilledByPlayer.builder()).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 16.0F))))));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return UGEntityTypes.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}