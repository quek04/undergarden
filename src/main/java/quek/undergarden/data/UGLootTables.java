package quek.undergarden.data;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import quek.undergarden.Undergarden;
import quek.undergarden.block.*;
import quek.undergarden.data.provider.UGBlockLootTableProvider;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGItems;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UGLootTables extends LootTableProvider {

	private static final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
	private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(net.minecraft.world.level.block.Blocks.DRAGON_EGG, net.minecraft.world.level.block.Blocks.BEACON, net.minecraft.world.level.block.Blocks.CONDUIT, net.minecraft.world.level.block.Blocks.SKELETON_SKULL, net.minecraft.world.level.block.Blocks.WITHER_SKELETON_SKULL, net.minecraft.world.level.block.Blocks.PLAYER_HEAD, net.minecraft.world.level.block.Blocks.ZOMBIE_HEAD, net.minecraft.world.level.block.Blocks.CREEPER_HEAD, net.minecraft.world.level.block.Blocks.DRAGON_HEAD, net.minecraft.world.level.block.Blocks.SHULKER_BOX, net.minecraft.world.level.block.Blocks.BLACK_SHULKER_BOX, net.minecraft.world.level.block.Blocks.BLUE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.BROWN_SHULKER_BOX, net.minecraft.world.level.block.Blocks.CYAN_SHULKER_BOX, net.minecraft.world.level.block.Blocks.GRAY_SHULKER_BOX, net.minecraft.world.level.block.Blocks.GREEN_SHULKER_BOX, net.minecraft.world.level.block.Blocks.LIGHT_BLUE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.LIGHT_GRAY_SHULKER_BOX, net.minecraft.world.level.block.Blocks.LIME_SHULKER_BOX, net.minecraft.world.level.block.Blocks.MAGENTA_SHULKER_BOX, net.minecraft.world.level.block.Blocks.ORANGE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.PINK_SHULKER_BOX, net.minecraft.world.level.block.Blocks.PURPLE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.RED_SHULKER_BOX, net.minecraft.world.level.block.Blocks.WHITE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(ImmutableSet.toImmutableSet());
	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

	public UGLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, Set.of(), List.of(
				new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK),
				new SubProviderEntry(Chests::new, LootContextParamSets.CHEST),
				new SubProviderEntry(Entities::new, LootContextParamSets.ENTITY)), provider);
	}

	public static class Blocks extends UGBlockLootTableProvider {

		protected Blocks(HolderLookup.Provider provider) {
			super(provider);
		}

		@Override
		protected void generate() {
			HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
			dropSelf(UGBlocks.DREADROCK);
			dropSelf(UGBlocks.DEPTHROCK);
			dropSelf(UGBlocks.DEEPSOIL);
			dropWithSilk(UGBlocks.DEEPSOIL_FARMLAND, UGBlocks.DEEPSOIL);
			this.add(UGBlocks.UNDERBEAN_BUSH.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.UNDERBEAN_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UnderbeanBushBlock.AGE, 3))).add(LootItem.lootTableItem(UGItems.UNDERBEANS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.UNDERBEAN_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UnderbeanBushBlock.AGE, 2))).add(LootItem.lootTableItem(UGItems.UNDERBEANS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
			this.add(UGBlocks.BLISTERBERRY_BUSH.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 3))).add(LootItem.lootTableItem(UGItems.BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 2))).add(LootItem.lootTableItem(UGItems.BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 3))).add(LootItem.lootTableItem(UGItems.ROTTEN_BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.BLISTERBERRY_BUSH.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 2))).add(LootItem.lootTableItem(UGItems.ROTTEN_BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
			this.add(UGBlocks.DITCHBULB_PLANT.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.DITCHBULB_PLANT.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DitchbulbBlock.AGE, 1))).add(LootItem.lootTableItem(UGItems.DITCHBULB.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
			);
			dropWithSilk(UGBlocks.DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
			this.add(UGBlocks.TALL_DEEPTURF.get(), (block) -> tallGrassDrop(block, UGBlocks.DEEPTURF.get()));
			this.add(UGBlocks.TALL_SHIMMERWEED.get(), (block) -> tallGrassDrop(block, UGBlocks.SHIMMERWEED.get()));
			this.add(UGBlocks.DEEPTURF.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.add(UGBlocks.SHIMMERWEED.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.add(UGBlocks.ASHEN_DEEPTURF.get(), BlockLootSubProvider::createShearsOnlyDrop);
			dropSelf(UGBlocks.SMOGSTEM_PLANKS);
			dropSelf(UGBlocks.WIGGLEWOOD_PLANKS);
			dropSelf(UGBlocks.SMOGSTEM_LOG);
			dropSelf(UGBlocks.WIGGLEWOOD_LOG);
			dropSelf(UGBlocks.SMOGSTEM_SAPLING);
			this.add(UGBlocks.SMOGSTEM_LEAVES.get(), (leaves) -> createLeavesDrops(leaves, UGBlocks.SMOGSTEM_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
			dropSelf(UGBlocks.WIGGLEWOOD_SAPLING);
			this.add(UGBlocks.WIGGLEWOOD_LEAVES.get(), (leaves) -> createSilkTouchOrShearsDispatchTable(leaves, applyExplosionCondition(leaves, LootItem.lootTableItem(UGBlocks.WIGGLEWOOD_SAPLING.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(registryLookup.getOrThrow(Enchantments.FORTUNE), DEFAULT_SAPLING_DROP_RATES))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(this.doesNotHaveShearsOrSilkTouch()).add(applyExplosionDecay(leaves, LootItem.lootTableItem(UGItems.TWISTYTWIG.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F)))));
			this.add(UGBlocks.GRONGLE_LEAVES.get(), (leaves) -> createLeavesDrops(leaves, UGBlocks.GRONGLE_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
			dropSelf(UGBlocks.INDIGO_MUSHROOM);
			dropSelf(UGBlocks.VEIL_MUSHROOM);
			dropSelf(UGBlocks.INK_MUSHROOM);
			dropSelf(UGBlocks.BLOOD_MUSHROOM);
			dropSelf(UGBlocks.DEPTHROCK_BRICKS);
			dropSelf(UGBlocks.CRACKED_DEPTHROCK_BRICKS);
			dropSelf(UGBlocks.GLOOMGOURD);
			dropSelf(UGBlocks.CARVED_GLOOMGOURD);
			this.add(UGBlocks.DEPTHROCK_PEBBLES.get(), (pebble) -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(UGBlocks.DEPTHROCK_PEBBLES.get(), LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pebble).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DepthrockPebblesBlock.PEBBLES, 1)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pebble).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DepthrockPebblesBlock.PEBBLES, 2))))))));
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
			dropOther(UGBlocks.GLITTERKELP, UGItems.GLITTERKELP.get());
			dropOther(UGBlocks.GLITTERKELP_PLANT, UGItems.GLITTERKELP.get());
			this.add(UGBlocks.SMOGSTEM_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
			this.add(UGBlocks.WIGGLEWOOD_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
			dropSelf(UGBlocks.SMOGSTEM_TRAPDOOR);
			dropSelf(UGBlocks.WIGGLEWOOD_TRAPDOOR);
			dropWithSilk(UGBlocks.SMOG_VENT, UGBlocks.DEPTHROCK);
			this.add(UGBlocks.GOO.get(), (goo) -> createSingleItemTableWithSilkTouch(goo, UGItems.GOO_BALL.get(), UniformGenerator.between(1.0F, 4.0F)));
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
			dropSelf(UGBlocks.SHARD_TORCH);
			dropOther(UGBlocks.SHARD_WALL_TORCH, UGBlocks.SHARD_TORCH.get());
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
			dropSelf(UGBlocks.GRONGLE_SAPLING);
			dropSelf(UGBlocks.GRONGLE_LOG);
			dropSelf(UGBlocks.GRONGLE_PLANKS);
			dropSelf(UGBlocks.GRONGLE_WOOD);
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
			dropSelf(UGBlocks.STRIPPED_GRONGLE_LOG);
			dropSelf(UGBlocks.STRIPPED_SMOGSTEM_WOOD);
			dropSelf(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD);
			dropSelf(UGBlocks.STRIPPED_GRONGLE_WOOD);
			this.add(UGBlocks.GLOOMGOURD_STEM.get(), (stem) -> createStemDrops(stem, UGItems.GLOOMGOURD_SEEDS.get()));
			this.add(UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(), (stem) -> dropSeedsForStem(stem, UGItems.GLOOMGOURD_SEEDS.get()));
			dropSelf(UGBlocks.CRACKED_SHIVERSTONE_BRICKS);
			dropSelf(UGBlocks.DEPTHROCK_WALL);
			dropSelf(UGBlocks.SHIVERSTONE_WALL);
			this.add(UGBlocks.BLOOD_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.BLOOD_MUSHROOM.get()));
			this.add(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(), (block -> LootTable.lootTable()
							.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
									.add(LootItem.lootTableItem(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get()).when(this.hasSilkTouch())
											.otherwise(applyExplosionDecay(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(), LootItem.lootTableItem(UGItems.BLOOD_GLOBULE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F)))))
											.append(applyExplosionDecay(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(), LootItem.lootTableItem(UGBlocks.BLOOD_MUSHROOM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(-6.0F, 2.0F))).apply(LimitCount.limitCount(IntRange.lowerBound(0))))))
							)
					)
			);
			dropAsSilk(UGBlocks.BLOOD_MUSHROOM_STEM);
			this.add(UGBlocks.INDIGO_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.INDIGO_MUSHROOM.get()));
			dropAsSilk(UGBlocks.INDIGO_MUSHROOM_STEM);
			this.add(UGBlocks.VEIL_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.VEIL_MUSHROOM.get()));
			dropAsSilk(UGBlocks.VEIL_MUSHROOM_STEM);
			this.add(UGBlocks.INK_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.INK_MUSHROOM.get()));
			dropAsSilk(UGBlocks.INK_MUSHROOM_STEM);
			this.add(UGBlocks.SEEPING_INK.get(), BlockLootSubProvider::createShearsOnlyDrop);
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
			dropPottedContents(UGBlocks.POTTED_PUFF_MUSHROOM.get());
			dropPottedContents(UGBlocks.POTTED_GRONGLE_SAPLING.get());
			dropPottedContents(UGBlocks.POTTED_AMOROUS_BRISTLE.get());
			dropPottedContents(UGBlocks.POTTED_MISERABELL.get());
			dropPottedContents(UGBlocks.POTTED_BUTTERBUNCH.get());
			dropWithSilk(UGBlocks.FROZEN_DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
			this.add(UGBlocks.FROZEN_DEEPTURF.get(), BlockLootSubProvider::createShearsOnlyDrop);
			dropSelf(UGBlocks.CHISELED_TREMBLECRUST_BRICKS);
			dropSelf(UGBlocks.TREMBLECRUST_STAIRS);
			dropSelf(UGBlocks.TREMBLECRUST_BRICK_STAIRS);
			slab(UGBlocks.TREMBLECRUST_SLAB);
			slab(UGBlocks.TREMBLECRUST_BRICK_SLAB);
			dropSelf(UGBlocks.TREMBLECRUST_WALL);
			dropSelf(UGBlocks.TREMBLECRUST_BRICK_WALL);
			dropSelf(UGBlocks.TREMBLECRUST_BUTTON);
			dropSelf(UGBlocks.TREMBLECRUST_PRESSURE_PLATE);
			dropSelf(UGBlocks.SMOGSTEM_SIGN);
			dropOther(UGBlocks.SMOGSTEM_WALL_SIGN, UGBlocks.SMOGSTEM_SIGN.get());
			dropSelf(UGBlocks.WIGGLEWOOD_SIGN);
			dropOther(UGBlocks.WIGGLEWOOD_WALL_SIGN, UGBlocks.WIGGLEWOOD_SIGN.get());
			dropSelf(UGBlocks.GRONGLE_SIGN);
			dropOther(UGBlocks.GRONGLE_WALL_SIGN, UGBlocks.GRONGLE_SIGN.get());
			dropSelf(UGBlocks.SMOGSTEM_HANGING_SIGN);
			dropOther(UGBlocks.SMOGSTEM_WALL_HANGING_SIGN, UGBlocks.SMOGSTEM_HANGING_SIGN.get());
			dropSelf(UGBlocks.WIGGLEWOOD_HANGING_SIGN);
			dropOther(UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN, UGBlocks.WIGGLEWOOD_HANGING_SIGN.get());
			dropSelf(UGBlocks.GRONGLE_HANGING_SIGN);
			dropOther(UGBlocks.GRONGLE_WALL_HANGING_SIGN, UGBlocks.GRONGLE_HANGING_SIGN.get());
			dropSelf(UGBlocks.GOO_BLOCK);
			dropSelf(UGBlocks.SEDIMENT);
			dropAsSilk(UGBlocks.SEDIMENT_GLASS);
			dropAsSilk(UGBlocks.SEDIMENT_GLASS_PANE);
			dropSelf(UGBlocks.CLOGGRUM_TILES);
			dropSelf(UGBlocks.CLOGGRUM_TILE_STAIRS);
			dropSelf(UGBlocks.CLOGGRUM_TILE_SLAB);
			dropSelf(UGBlocks.DEPTHROCK_TILES);
			dropSelf(UGBlocks.DEPTHROCK_TILE_STAIRS);
			dropSelf(UGBlocks.DEPTHROCK_TILE_SLAB);
			this.add(UGBlocks.DEPTHROCK_BED.get(), (bed) -> createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
			dropSelf(UGBlocks.MOGMOSS_RUG);
			dropSelf(UGBlocks.BLUE_MOGMOSS_RUG);
			ore(UGBlocks.DEPTHROCK_COAL_ORE, Items.COAL);
			ore(UGBlocks.SHIVERSTONE_COAL_ORE, Items.COAL);
			nuggetOre(UGBlocks.DEPTHROCK_IRON_ORE, Items.IRON_NUGGET);
			nuggetOre(UGBlocks.SHIVERSTONE_IRON_ORE, Items.IRON_NUGGET);
			nuggetOre(UGBlocks.DEPTHROCK_GOLD_ORE, Items.GOLD_NUGGET);
			ore(UGBlocks.DEPTHROCK_DIAMOND_ORE, Items.DIAMOND);
			ore(UGBlocks.SHIVERSTONE_DIAMOND_ORE, Items.DIAMOND);
			ore(UGBlocks.DEPTHROCK_CLOGGRUM_ORE, UGItems.RAW_CLOGGRUM);
			ore(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE, UGItems.RAW_CLOGGRUM);
			ore(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE, UGItems.RAW_FROSTSTEEL);
			ore(UGBlocks.DEPTHROCK_UTHERIUM_ORE, UGItems.UTHERIUM_CRYSTAL);
			ore(UGBlocks.SHIVERSTONE_UTHERIUM_ORE, UGItems.UTHERIUM_CRYSTAL);
			ore(UGBlocks.TREMBLECRUST_UTHERIUM_ORE, UGItems.UTHERIUM_CRYSTAL);
			ore(UGBlocks.DEPTHROCK_REGALIUM_ORE, UGItems.REGALIUM_CRYSTAL);
			ore(UGBlocks.SHIVERSTONE_REGALIUM_ORE, UGItems.REGALIUM_CRYSTAL);
			dropSelf(UGBlocks.RAW_CLOGGRUM_BLOCK);
			dropSelf(UGBlocks.RAW_FROSTSTEEL_BLOCK);
			dropSelf(UGBlocks.CLOGGRUM_LANTERN);
			this.add(UGBlocks.HANGING_GRONGLE_LEAVES.get(), BlockLootSubProvider::createShearsOnlyDrop);
			this.add(UGBlocks.DROOPVINE.get(), (UGLootTables::droopvine));
			this.add(UGBlocks.DROOPVINE_PLANT.get(), (UGLootTables::droopvine));
			dropSelf(UGBlocks.GRONGLET);
			dropSelf(UGBlocks.BOOMGOURD);
			dropSelf(UGBlocks.POLISHED_DEPTHROCK);
			dropSelf(UGBlocks.POLISHED_DEPTHROCK_STAIRS);
			dropSelf(UGBlocks.POLISHED_DEPTHROCK_SLAB);
			dropSelf(UGBlocks.POLISHED_DEPTHROCK_WALL);
			dropSelf(UGBlocks.AMOROUS_BRISTLE);
			dropSelf(UGBlocks.MISERABELL);
			dropSelf(UGBlocks.BUTTERBUNCH);
			dropOther(UGBlocks.VIRULENT_MIX_CAULDRON, Items.CAULDRON);
			this.add(UGBlocks.MUSHROOM_VEIL.get(), BlockLootSubProvider::createShearsOnlyDrop);
			ore(UGBlocks.DREADROCK_ROGDORIUM_ORE, UGItems.ROGDORIUM_CRYSTAL);
			dropSelf(UGBlocks.ROGDORIUM_BLOCK);
			dropSelf(UGBlocks.UTHERIUM_GROWTH);
			ore(UGBlocks.DREADROCK_UTHERIUM_ORE, UGItems.UTHERIUM_CRYSTAL);
			dropSelf(UGBlocks.ANCIENT_ROOT);
			dropSelf(UGBlocks.ANCIENT_ROOT_PLANKS);
			dropSelf(UGBlocks.ANCIENT_ROOT_STAIRS);
			slab(UGBlocks.ANCIENT_ROOT_SLAB);
			dropSelf(UGBlocks.ANCIENT_ROOT_FENCE);
			dropSelf(UGBlocks.ANCIENT_ROOT_FENCE_GATE);
			this.add(UGBlocks.ANCIENT_ROOT_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
			dropSelf(UGBlocks.ANCIENT_ROOT_TRAPDOOR);
			dropSelf(UGBlocks.ANCIENT_ROOT_BUTTON);
			dropSelf(UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE);
			dropSelf(UGBlocks.ANCIENT_ROOT_SIGN);
			dropOther(UGBlocks.ANCIENT_ROOT_WALL_SIGN, UGBlocks.ANCIENT_ROOT_SIGN);
			dropSelf(UGBlocks.ANCIENT_ROOT_HANGING_SIGN);
			dropOther(UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN, UGBlocks.ANCIENT_ROOT_HANGING_SIGN);
			dropSelf(UGBlocks.DENIZEN_TOTEM);
			dropSelf(UGBlocks.PUFF_MUSHROOM);
			this.add(UGBlocks.PUFF_MUSHROOM_CAP.get(), (mushroom) -> createMushroomBlockDrop(mushroom, UGBlocks.PUFF_MUSHROOM.get()));
			dropAsSilk(UGBlocks.PUFF_MUSHROOM_STEM);
			this.add(UGBlocks.INFUSER.get(), this::createNameableBlockEntityTable);
			dropSelf(UGBlocks.DREADROCK_BRICKS);
			slab(UGBlocks.DREADROCK_SLAB);
			slab(UGBlocks.DREADROCK_BRICK_SLAB);
			dropSelf(UGBlocks.DREADROCK_STAIRS);
			dropSelf(UGBlocks.DREADROCK_BRICK_STAIRS);
			dropSelf(UGBlocks.DREADROCK_WALL);
			dropSelf(UGBlocks.DREADROCK_BRICK_WALL);
			dropSelf(UGBlocks.DREADROCK_BUTTON);
			dropSelf(UGBlocks.DREADROCK_PRESSURE_PLATE);
		}

		@Override
		protected Iterable<Block> getKnownBlocks() {
			return UGBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
		}
	}

	private static LootTable.Builder droopvine(Block block) {
		return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(UGItems.DROOPFRUIT.get())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Droopvine.GLOWY, true))));
	}

	private static LootTable.Builder tallGrassDrop(Block originalBlock, Block newBlock) {
		LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(newBlock).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).when(SHEARS);
		return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))), new BlockPos(0, -1, 0))));
	}

	private static LootTable.Builder dropSeedsForStem(Block stem, Item stemSeed) {
		return LootTable.lootTable().withPool(withExplosionDecay(stem, LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(stemSeed).apply(SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(3, 0.53333336F))))));
	}

	protected static <T extends FunctionUserBuilder<T>> T withExplosionDecay(ItemLike item, FunctionUserBuilder<T> function) {
		return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? function.apply(ApplyExplosionDecay.explosionDecay()) : function.unwrap();
	}

	public static class Entities extends EntityLootSubProvider {

		public Entities(HolderLookup.Provider provider) {
			super(FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.add(UGEntityTypes.ROTLING.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
									.when(LootItemKilledByPlayerCondition.killedByPlayer())
							)
					)
			);
			this.add(UGEntityTypes.ROTWALKER.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
									.when(LootItemKilledByPlayerCondition.killedByPlayer())
							)
					)
			);
			this.add(UGEntityTypes.ROTBEAST.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
									.when(LootItemKilledByPlayerCondition.killedByPlayer())
							)
					)
			);
			this.add(UGEntityTypes.ROTBELCHER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1))
					.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get())
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
					)
				)
			);
			this.add(UGEntityTypes.DWELLER.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.LEATHER)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
							)
					)
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.RAW_DWELLER_MEAT.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
									.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
					)
			);
			this.add(UGEntityTypes.GWIBLING.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.RAW_GWIBLING.get())
								.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
							.when(LootItemKilledByPlayerCondition.killedByPlayer())
					)
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.BONE_MEAL))
							.when(LootItemRandomChanceCondition.randomChance(0.05F))
					)
			);
			this.add(UGEntityTypes.BRUTE.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.BRUTE_TUSK.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
							)
					)
			);
			this.add(UGEntityTypes.SCINTLING.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.GOO_BALL.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 2.0F)))
							)
					)
			);
			this.add(UGEntityTypes.GLOOMPER.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.LEATHER)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
							)
					)
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.RAW_GLOOMPER_LEG.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
									.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
							)
					)
			);
			this.add(UGEntityTypes.STONEBORN.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
							)
					)
			);
			this.add(UGEntityTypes.NARGOYLE.get(), LootTable.lootTable());
			this.add(UGEntityTypes.MUNCHER.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(UniformGenerator.between(0.0F, 3.0F))
							.add(LootItem.lootTableItem(UGItems.CLOGGRUM_NUGGET.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
							)
					)
					.withPool(LootPool.lootPool()
							.setRolls(UniformGenerator.between(0.0F, 1.0F))
							.add(LootItem.lootTableItem(UGItems.FROSTSTEEL_NUGGET.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
							)
					)
			);
			this.add(UGEntityTypes.SPLOOGIE.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
							)
					)
			);
			this.add(UGEntityTypes.GWIB.get(), LootTable.lootTable());
			this.add(UGEntityTypes.MOG.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
							)
					)
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.MOGMOSS.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
							)
					)
			);

			this.add(UGEntityTypes.SMOG_MOG.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(1.0F, 2.0F)))
							)
					)
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.BLUE_MOGMOSS.get())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
							)
					)
			);

			this.add(UGEntityTypes.FORGOTTEN.get(), LootTable.lootTable());

			this.add(UGEntityTypes.DENIZEN.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(UGItems.DENIZEN_MASK.get()))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())
								.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
				)
			);

			this.add(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(UGItems.FORGOTTEN_NUGGET.get())
									.when(LootItemKilledByPlayerCondition.killedByPlayer())
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 16.0F)))
							)
					)
			);
		}

		@Override
		protected Stream<EntityType<?>> getKnownEntityTypes() {
			return UGEntityTypes.ENTITIES.getEntries().stream().map(Supplier::get);
		}
	}

	public record Chests(HolderLookup.Provider registries) implements LootTableSubProvider {

		@Override
		public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
			consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "chests/catacombs")), LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(2.0F, 5.0F))
					.add(LootItem.lootTableItem(UGItems.CLOGGRUM_NUGGET.get()).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
					.add(LootItem.lootTableItem(UGItems.FROSTSTEEL_NUGGET.get()).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
					.add(LootItem.lootTableItem(UGItems.UTHERIC_SHARD.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
					.add(LootItem.lootTableItem(UGItems.REGALIUM_CRYSTAL.get()).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
					.add(LootItem.lootTableItem(UGItems.SLOP_BOWL.get()).setWeight(40).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F)))))
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(2))
					.add(LootItem.lootTableItem(UGItems.CLOGGRUM_SWORD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(15.0F, 20.0F))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.6F, 0.9F))))
					.add(LootItem.lootTableItem(UGItems.CLOGGRUM_AXE.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries,UniformGenerator.between(15.0F, 20.0F))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.6F, 0.9F))))
					.add(LootItem.lootTableItem(UGItems.CLOGGRUM_SWORD.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(25.0F, 30.0F)).fromOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.TREASURE))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.2F, 0.5F))))
					.add(LootItem.lootTableItem(UGItems.CLOGGRUM_AXE.get()).setWeight(10).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(25.0F, 30.0F)).fromOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.TREASURE))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.2F, 0.5F)))))
				.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(EmptyLootItem.emptyItem().setWeight(8))
					.add(LootItem.lootTableItem(UGItems.MAMMOTH_DISC.get()).setWeight(4))
					.add(LootItem.lootTableItem(UGItems.RELICT_DISC.get()).setWeight(4))
					.add(LootItem.lootTableItem(UGItems.FORGOTTEN_UPGRADE_TEMPLATE.get()).setWeight(3))
					.add(LootItem.lootTableItem(UGItems.FORGOTTEN_NUGGET.get()).setWeight(1)))
			);
			consumer.accept(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "chests/denizen_camp")), LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(1.0F, 4.0F))
					.add(EmptyLootItem.emptyItem().setWeight(5))
					.add(LootItem.lootTableItem(UGItems.ROGDORIUM_CRYSTAL.get()).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 9.0F))))
					.add(LootItem.lootTableItem(UGBlocks.DENIZEN_TOTEM.asItem()).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
					.add(LootItem.lootTableItem(Items.STICK).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 16.0F))))
				)
			);
		}
	}
}