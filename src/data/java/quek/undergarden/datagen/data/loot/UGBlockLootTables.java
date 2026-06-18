package quek.undergarden.datagen.data.loot;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.loot.CanItemPerformAbility;
import quek.undergarden.block.*;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UGBlockLootTables extends BlockLootSubProvider {

	private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};
	private static final LootItemCondition.Builder HAS_SHEARS = CanItemPerformAbility.canItemPerformAbility(ItemAbilities.SHEARS_DIG);

	protected UGBlockLootTables(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void generate() {
		HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		this.dropSelf(UGBlocks.DREADROCK);
		this.dropSelf(UGBlocks.DEPTHROCK);
		this.dropSelf(UGBlocks.DEEPSOIL);
		this.dropWithSilk(UGBlocks.DEEPSOIL_FARMLAND, UGBlocks.DEEPSOIL);
		this.add(UGBlocks.UNDERBEAN_BUSH.get(), block -> LootTable.lootTable()
			.withPool(this.applyExplosionDecay(block, LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UnderbeanBushBlock.AGE, 3))).add(LootItem.lootTableItem(UGItems.UNDERBEANS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))))
			.withPool(this.applyExplosionDecay(block, LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(UnderbeanBushBlock.AGE, 2))).add(LootItem.lootTableItem(UGItems.UNDERBEANS.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
		this.add(UGBlocks.BLISTERBERRY_BUSH.get(), block -> LootTable.lootTable()
			.withPool(this.applyExplosionDecay(block, LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 3))).add(LootItem.lootTableItem(UGItems.BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))))
			.withPool(this.applyExplosionDecay(block, LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 2))).add(LootItem.lootTableItem(UGItems.BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))))
			.withPool(this.applyExplosionDecay(block, LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 3))).add(LootItem.lootTableItem(UGItems.ROTTEN_BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))))
			.withPool(this.applyExplosionDecay(block, LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlisterberryBushBlock.AGE, 2))).add(LootItem.lootTableItem(UGItems.ROTTEN_BLISTERBERRY.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
		this.add(UGBlocks.DITCHBULB_PLANT.get(), block -> LootTable.lootTable().withPool(this.applyExplosionDecay(block, LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(UGBlocks.DITCHBULB_PLANT.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DitchbulbBlock.AGE, 1))).add(LootItem.lootTableItem(UGItems.DITCHBULB.get())).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
		this.dropWithSilk(UGBlocks.DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
		this.add(UGBlocks.TALL_DEEPTURF.get(), (block) -> this.createDoublePlantShearsDrop(UGBlocks.DEEPTURF.get()));
		this.add(UGBlocks.TALL_SHIMMERWEED.get(), (block) -> this.createDoublePlantShearsDrop(UGBlocks.SHIMMERWEED.get()));
		this.add(UGBlocks.DEEPTURF.get(), this::createShearsOnlyDrop);
		this.add(UGBlocks.SHIMMERWEED.get(), this::createShearsOnlyDrop);
		this.add(UGBlocks.ASHEN_DEEPTURF.get(), this::createShearsOnlyDrop);
		this.dropSelf(UGBlocks.SMOGSTEM_PLANKS);
		this.dropSelf(UGBlocks.WIGGLEWOOD_PLANKS);
		this.dropSelf(UGBlocks.SMOGSTEM_LOG);
		this.dropSelf(UGBlocks.WIGGLEWOOD_LOG);
		this.dropSelf(UGBlocks.SMOGSTEM_SAPLING);
		this.add(UGBlocks.SMOGSTEM_LEAVES.get(), (leaves) -> this.createLeavesDrops(leaves, UGBlocks.SMOGSTEM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.dropSelf(UGBlocks.WIGGLEWOOD_SAPLING);
		this.add(UGBlocks.WIGGLEWOOD_LEAVES.get(), (leaves) -> this.createLeavesDrops(leaves, UGBlocks.WIGGLEWOOD_SAPLING.get(), UGItems.TWISTYTWIG, NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(UGBlocks.GRONGLE_LEAVES.get(), (leaves) -> this.createLeavesDrops(leaves, UGBlocks.GRONGLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.dropSelf(UGBlocks.INDIGO_MUSHROOM);
		this.dropSelf(UGBlocks.VEIL_MUSHROOM);
		this.dropSelf(UGBlocks.INK_MUSHROOM);
		this.dropSelf(UGBlocks.BLOOD_MUSHROOM);
		this.dropSelf(UGBlocks.DEPTHROCK_BRICKS);
		this.dropSelf(UGBlocks.CRACKED_DEPTHROCK_BRICKS);
		this.dropSelf(UGBlocks.GLOOMGOURD);
		this.dropSelf(UGBlocks.CARVED_GLOOMGOURD);
		this.add(UGBlocks.DEPTHROCK_PEBBLES.get(), (pebble) -> LootTable.lootTable().withPool(this.applyExplosionDecay(pebble, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(UGBlocks.DEPTHROCK_PEBBLES.get(), LootItem.lootTableItem(UGItems.DEPTHROCK_PEBBLE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pebble).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DepthrockPebblesBlock.PEBBLES, 1)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pebble).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DepthrockPebblesBlock.PEBBLES, 2)))))))));
		this.dropSelf(UGBlocks.GLOOM_O_LANTERN);
		this.dropSelf(UGBlocks.SHARD_O_LANTERN);
		this.dropSelf(UGBlocks.DEPTHROCK_STAIRS);
		this.dropSelf(UGBlocks.DEPTHROCK_BRICK_STAIRS);
		this.dropSelf(UGBlocks.SMOGSTEM_STAIRS);
		this.dropSelf(UGBlocks.WIGGLEWOOD_STAIRS);
		this.slab(UGBlocks.DEPTHROCK_SLAB);
		this.slab(UGBlocks.DEPTHROCK_BRICK_SLAB);
		this.slab(UGBlocks.SMOGSTEM_SLAB);
		this.slab(UGBlocks.WIGGLEWOOD_SLAB);
		this.dropSelf(UGBlocks.DEPTHROCK_BRICK_WALL);
		this.dropSelf(UGBlocks.SMOGSTEM_FENCE);
		this.dropSelf(UGBlocks.WIGGLEWOOD_FENCE);
		this.dropSelf(UGBlocks.CLOGGRUM_BLOCK);
		this.dropSelf(UGBlocks.FROSTSTEEL_BLOCK);
		this.dropSelf(UGBlocks.UTHERIUM_BLOCK);
		this.dropSelf(UGBlocks.CLOGGRUM_BARS);
		this.dropOther(UGBlocks.GLITTERKELP, UGItems.GLITTERKELP.get());
		this.dropOther(UGBlocks.GLITTERKELP_PLANT, UGItems.GLITTERKELP.get());
		this.add(UGBlocks.SMOGSTEM_DOOR.get(), (block) -> this.createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(UGBlocks.WIGGLEWOOD_DOOR.get(), (block) -> this.createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.dropSelf(UGBlocks.SMOGSTEM_TRAPDOOR);
		this.dropSelf(UGBlocks.WIGGLEWOOD_TRAPDOOR);
		this.dropWithSilk(UGBlocks.SMOG_VENT, UGBlocks.DEPTHROCK);
		this.add(UGBlocks.GOO.get(), block -> LootTable.lootTable()
			.withPool(
				this.applyExplosionDecay(block, LootPool.lootPool()
					.when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS))
					.add(LootItem.lootTableItem(UGItems.GOO_BALL)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
						.when(this.doesNotHaveSilkTouch())
						.otherwise(LootItem.lootTableItem(UGBlocks.GOO))
					)
				)
			)
		);
		this.dropWithSilk(UGBlocks.ASHEN_DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
		this.dropSelf(UGBlocks.SHIVERSTONE);
		this.dropSelf(UGBlocks.SHIVERSTONE_BRICKS);
		this.slab(UGBlocks.SHIVERSTONE_SLAB);
		this.slab(UGBlocks.SHIVERSTONE_BRICK_SLAB);
		this.dropSelf(UGBlocks.SHIVERSTONE_BRICK_WALL);
		this.dropSelf(UGBlocks.SHIVERSTONE_STAIRS);
		this.dropSelf(UGBlocks.SHIVERSTONE_BRICK_STAIRS);
		this.dropSelf(UGBlocks.REGALIUM_BLOCK);
		this.dropSelf(UGBlocks.TREMBLECRUST);
		this.dropSelf(UGBlocks.TREMBLECRUST_BRICKS);
		this.dropSelf(UGBlocks.CRACKED_TREMBLECRUST_BRICKS);
		this.dropSelf(UGBlocks.SMOGSTEM_WOOD);
		this.dropSelf(UGBlocks.WIGGLEWOOD_WOOD);
		this.dropSelf(UGBlocks.SHARD_TORCH);
		this.dropOther(UGBlocks.SHARD_WALL_TORCH, UGBlocks.SHARD_TORCH.get());
		this.dropSelf(UGBlocks.SMOGSTEM_FENCE_GATE);
		this.dropSelf(UGBlocks.WIGGLEWOOD_FENCE_GATE);
		this.dropSelf(UGBlocks.COARSE_DEEPSOIL);
		this.dropSelf(UGBlocks.SMOGSTEM_BUTTON);
		this.dropSelf(UGBlocks.WIGGLEWOOD_BUTTON);
		this.dropSelf(UGBlocks.DEPTHROCK_BUTTON);
		this.dropSelf(UGBlocks.SHIVERSTONE_BUTTON);
		this.dropSelf(UGBlocks.SMOGSTEM_PRESSURE_PLATE);
		this.dropSelf(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE);
		this.dropSelf(UGBlocks.DEPTHROCK_PRESSURE_PLATE);
		this.dropSelf(UGBlocks.SHIVERSTONE_PRESSURE_PLATE);
		this.dropSelf(UGBlocks.GRONGLE_SAPLING);
		this.dropSelf(UGBlocks.GRONGLE_LOG);
		this.dropSelf(UGBlocks.GRONGLE_PLANKS);
		this.dropSelf(UGBlocks.GRONGLE_WOOD);
		this.dropSelf(UGBlocks.GRONGLE_STAIRS);
		this.slab(UGBlocks.GRONGLE_SLAB);
		this.dropSelf(UGBlocks.GRONGLE_FENCE);
		this.dropSelf(UGBlocks.GRONGLE_FENCE_GATE);
		this.add(UGBlocks.GRONGLE_DOOR.get(), (block) -> this.createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.dropSelf(UGBlocks.GRONGLE_TRAPDOOR);
		this.dropSelf(UGBlocks.GRONGLE_BUTTON);
		this.dropSelf(UGBlocks.GRONGLE_PRESSURE_PLATE);
		this.dropSelf(UGBlocks.STRIPPED_SMOGSTEM_LOG);
		this.dropSelf(UGBlocks.STRIPPED_WIGGLEWOOD_LOG);
		this.dropSelf(UGBlocks.STRIPPED_GRONGLE_LOG);
		this.dropSelf(UGBlocks.STRIPPED_SMOGSTEM_WOOD);
		this.dropSelf(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD);
		this.dropSelf(UGBlocks.STRIPPED_GRONGLE_WOOD);
		this.add(UGBlocks.GLOOMGOURD_STEM.get(), (stem) -> this.createStemDrops(stem, UGItems.GLOOMGOURD_SEEDS.get()));
		this.add(UGBlocks.GLOOMGOURD_STEM_ATTACHED.get(), (stem) -> this.createAttachedStemDrops(stem, UGItems.GLOOMGOURD_SEEDS.get()));
		this.dropSelf(UGBlocks.CRACKED_SHIVERSTONE_BRICKS);
		this.dropSelf(UGBlocks.DEPTHROCK_WALL);
		this.dropSelf(UGBlocks.SHIVERSTONE_WALL);
		this.add(UGBlocks.BLOOD_MUSHROOM_CAP.get(), (mushroom) -> this.createMushroomBlockDrop(mushroom, UGBlocks.BLOOD_MUSHROOM.get()));
		this.add(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(), block -> LootTable.lootTable()
			.withPool(this.applyExplosionDecay(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get()).when(this.hasSilkTouch())
					.otherwise(LootItem.lootTableItem(UGItems.BLOOD_GLOBULE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
					.append(LootItem.lootTableItem(UGBlocks.BLOOD_MUSHROOM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(-6.0F, 2.0F))).apply(LimitCount.limitCount(IntRange.lowerBound(0))))))));
		this.dropAsSilk(UGBlocks.BLOOD_MUSHROOM_STEM);
		this.mushroom(UGBlocks.INDIGO_MUSHROOM_CAP, UGBlocks.INDIGO_MUSHROOM);
		this.dropAsSilk(UGBlocks.INDIGO_MUSHROOM_STEM);
		this.mushroom(UGBlocks.VEIL_MUSHROOM_CAP, UGBlocks.VEIL_MUSHROOM);
		this.dropAsSilk(UGBlocks.VEIL_MUSHROOM_STEM);
		this.mushroom(UGBlocks.INK_MUSHROOM_CAP, UGBlocks.INK_MUSHROOM);
		this.dropAsSilk(UGBlocks.INK_MUSHROOM_STEM);
		this.add(UGBlocks.SEEPING_INK.get(), this::createShearsOnlyDrop);
		this.dropSelf(UGBlocks.FORGOTTEN_BLOCK);
		this.dropSelf(UGBlocks.CHISELED_DEPTHROCK_BRICKS);
		this.dropSelf(UGBlocks.CHISELED_SHIVERSTONE_BRICKS);
		this.dropPottedContents(UGBlocks.POTTED_SMOGSTEM_SAPLING.get());
		this.dropPottedContents(UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get());
		this.dropPottedContents(UGBlocks.POTTED_SHIMMERWEED.get());
		this.dropPottedContents(UGBlocks.POTTED_INDIGO_MUSHROOM.get());
		this.dropPottedContents(UGBlocks.POTTED_VEIL_MUSHROOM.get());
		this.dropPottedContents(UGBlocks.POTTED_INK_MUSHROOM.get());
		this.dropPottedContents(UGBlocks.POTTED_BLOOD_MUSHROOM.get());
		this.dropPottedContents(UGBlocks.POTTED_PUFF_MUSHROOM.get());
		this.dropPottedContents(UGBlocks.POTTED_GRONGLE_SAPLING.get());
		this.dropPottedContents(UGBlocks.POTTED_AMOROUS_BRISTLE.get());
		this.dropPottedContents(UGBlocks.POTTED_MISERABELL.get());
		this.dropPottedContents(UGBlocks.POTTED_BUTTERBUNCH.get());
		this.dropWithSilk(UGBlocks.FROZEN_DEEPTURF_BLOCK, UGBlocks.DEEPSOIL);
		this.add(UGBlocks.FROZEN_DEEPTURF.get(), this::createShearsOnlyDrop);
		this.dropSelf(UGBlocks.CHISELED_TREMBLECRUST_BRICKS);
		this.dropSelf(UGBlocks.TREMBLECRUST_STAIRS);
		this.dropSelf(UGBlocks.TREMBLECRUST_BRICK_STAIRS);
		this.slab(UGBlocks.TREMBLECRUST_SLAB);
		this.slab(UGBlocks.TREMBLECRUST_BRICK_SLAB);
		this.dropSelf(UGBlocks.TREMBLECRUST_WALL);
		this.dropSelf(UGBlocks.TREMBLECRUST_BRICK_WALL);
		this.dropSelf(UGBlocks.TREMBLECRUST_BUTTON);
		this.dropSelf(UGBlocks.TREMBLECRUST_PRESSURE_PLATE);
		this.dropSelf(UGBlocks.SMOGSTEM_SIGN);
		this.dropOther(UGBlocks.SMOGSTEM_WALL_SIGN, UGBlocks.SMOGSTEM_SIGN.get());
		this.dropSelf(UGBlocks.WIGGLEWOOD_SIGN);
		this.dropOther(UGBlocks.WIGGLEWOOD_WALL_SIGN, UGBlocks.WIGGLEWOOD_SIGN.get());
		this.dropSelf(UGBlocks.GRONGLE_SIGN);
		this.dropOther(UGBlocks.GRONGLE_WALL_SIGN, UGBlocks.GRONGLE_SIGN.get());
		this.dropSelf(UGBlocks.SMOGSTEM_HANGING_SIGN);
		this.dropOther(UGBlocks.SMOGSTEM_WALL_HANGING_SIGN, UGBlocks.SMOGSTEM_HANGING_SIGN.get());
		this.dropSelf(UGBlocks.WIGGLEWOOD_HANGING_SIGN);
		this.dropOther(UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN, UGBlocks.WIGGLEWOOD_HANGING_SIGN.get());
		this.dropSelf(UGBlocks.GRONGLE_HANGING_SIGN);
		this.dropOther(UGBlocks.GRONGLE_WALL_HANGING_SIGN, UGBlocks.GRONGLE_HANGING_SIGN.get());
		this.dropSelf(UGBlocks.GOO_BLOCK);
		this.dropSelf(UGBlocks.SEDIMENT);
		this.dropAsSilk(UGBlocks.SEDIMENT_GLASS);
		this.dropAsSilk(UGBlocks.SEDIMENT_GLASS_PANE);
		this.dropSelf(UGBlocks.CLOGGRUM_TILES);
		this.dropSelf(UGBlocks.CLOGGRUM_TILE_STAIRS);
		this.dropSelf(UGBlocks.CLOGGRUM_TILE_SLAB);
		this.dropSelf(UGBlocks.CLOGGRUM_PILLAR);
		this.dropSelf(UGBlocks.CLOGGRUM_GRATE);
		this.dropSelf(UGBlocks.CLOGGRUM_LADDER);
		this.dropSelf(UGBlocks.DEPTHROCK_TILES);
		this.dropSelf(UGBlocks.DEPTHROCK_TILE_STAIRS);
		this.dropSelf(UGBlocks.DEPTHROCK_TILE_SLAB);
		this.add(UGBlocks.DEPTHROCK_BED.get(), (bed) -> this.createSinglePropConditionTable(bed, BedBlock.PART, BedPart.HEAD));
		this.dropSelf(UGBlocks.MOGMOSS_RUG);
		this.dropSelf(UGBlocks.BLUE_MOGMOSS_RUG);
		this.ore(UGBlocks.DEPTHROCK_COAL_ORE, Items.COAL);
		this.ore(UGBlocks.SHIVERSTONE_COAL_ORE, Items.COAL);
		this.nuggetOre(UGBlocks.DEPTHROCK_IRON_ORE, Items.IRON_NUGGET);
		this.nuggetOre(UGBlocks.SHIVERSTONE_IRON_ORE, Items.IRON_NUGGET);
		this.nuggetOre(UGBlocks.DEPTHROCK_GOLD_ORE, Items.GOLD_NUGGET);
		this.ore(UGBlocks.DEPTHROCK_DIAMOND_ORE, Items.DIAMOND);
		this.ore(UGBlocks.SHIVERSTONE_DIAMOND_ORE, Items.DIAMOND);
		this.ore(UGBlocks.DEPTHROCK_CLOGGRUM_ORE, UGItems.RAW_CLOGGRUM);
		this.ore(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE, UGItems.RAW_CLOGGRUM);
		this.ore(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE, UGItems.RAW_FROSTSTEEL);
		this.ore(UGBlocks.DEPTHROCK_UTHERIUM_ORE, UGItems.UTHERIC_CLUSTER);
		this.ore(UGBlocks.SHIVERSTONE_UTHERIUM_ORE, UGItems.UTHERIC_CLUSTER);
		this.ore(UGBlocks.TREMBLECRUST_UTHERIUM_ORE, UGItems.UTHERIC_CLUSTER);
		this.ore(UGBlocks.DEPTHROCK_REGALIUM_ORE, UGItems.REGALIUM_CRYSTAL);
		this.ore(UGBlocks.SHIVERSTONE_REGALIUM_ORE, UGItems.REGALIUM_CRYSTAL);
		this.dropSelf(UGBlocks.RAW_CLOGGRUM_BLOCK);
		this.dropSelf(UGBlocks.RAW_FROSTSTEEL_BLOCK);
		this.dropSelf(UGBlocks.CLOGGRUM_LANTERN);
		this.add(UGBlocks.HANGING_GRONGLE_LEAVES.get(), this::createShearsOnlyDrop);
		this.add(UGBlocks.DROOPVINE.get(), this::droopvine);
		this.add(UGBlocks.DROOPVINE_PLANT.get(), this::droopvine);
		this.dropSelf(UGBlocks.GRONGLET);
		this.dropSelf(UGBlocks.UTHERIC_GRONGLET);
		this.dropSelf(UGBlocks.ROGDORIC_GRONGLET);
		this.dropSelf(UGBlocks.BOOMGOURD);
		this.dropSelf(UGBlocks.POLISHED_DEPTHROCK);
		this.dropSelf(UGBlocks.POLISHED_DEPTHROCK_STAIRS);
		this.dropSelf(UGBlocks.POLISHED_DEPTHROCK_SLAB);
		this.dropSelf(UGBlocks.POLISHED_DEPTHROCK_WALL);
		this.dropSelf(UGBlocks.AMOROUS_BRISTLE);
		this.dropSelf(UGBlocks.MISERABELL);
		this.dropSelf(UGBlocks.BUTTERBUNCH);
		this.dropOther(UGBlocks.VIRULENT_MIX_CAULDRON, Items.CAULDRON);
		this.add(UGBlocks.MUSHROOM_VEIL.get(), this::createShearsOnlyDrop);
		this.ore(UGBlocks.DREADROCK_ROGDORIUM_ORE, UGItems.ROGDORIUM);
		this.dropSelf(UGBlocks.ROGDORIUM_BLOCK);
		this.dropSelf(UGBlocks.UTHERIUM_GROWTH);
		this.ore(UGBlocks.DREADROCK_UTHERIUM_ORE, UGItems.UTHERIC_CLUSTER);
		this.dropSelf(UGBlocks.ANCIENT_ROOT);
		this.add(UGBlocks.ROGDORIC_ANCIENT_ROOT.get(), (block) -> this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(UGItems.ROGDORIUM_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
		this.dropSelf(UGBlocks.ANCIENT_ROOT_PLANKS);
		this.dropSelf(UGBlocks.ANCIENT_ROOT_STAIRS);
		slab(UGBlocks.ANCIENT_ROOT_SLAB);
		this.dropSelf(UGBlocks.ANCIENT_ROOT_FENCE);
		this.dropSelf(UGBlocks.ANCIENT_ROOT_FENCE_GATE);
		this.add(UGBlocks.ANCIENT_ROOT_DOOR.get(), (block) -> this.createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.dropSelf(UGBlocks.ANCIENT_ROOT_TRAPDOOR);
		this.dropSelf(UGBlocks.ANCIENT_ROOT_BUTTON);
		this.dropSelf(UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE);
		this.dropSelf(UGBlocks.ANCIENT_ROOT_SIGN);
		this.dropOther(UGBlocks.ANCIENT_ROOT_WALL_SIGN, UGBlocks.ANCIENT_ROOT_SIGN);
		this.dropSelf(UGBlocks.ANCIENT_ROOT_HANGING_SIGN);
		this.dropOther(UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN, UGBlocks.ANCIENT_ROOT_HANGING_SIGN);
		this.dropSelf(UGBlocks.DENIZEN_TOTEM);
		this.dropSelf(UGBlocks.PUFF_MUSHROOM);
		this.mushroom(UGBlocks.PUFF_MUSHROOM_CAP, UGBlocks.PUFF_MUSHROOM);
		this.dropAsSilk(UGBlocks.PUFF_MUSHROOM_STEM);
		this.add(UGBlocks.INFUSER.get(), this::createNameableBlockEntityTable);
		this.dropSelf(UGBlocks.DREADROCK_BRICKS);
		this.slab(UGBlocks.DREADROCK_SLAB);
		this.slab(UGBlocks.DREADROCK_BRICK_SLAB);
		this.dropSelf(UGBlocks.DREADROCK_STAIRS);
		this.dropSelf(UGBlocks.DREADROCK_BRICK_STAIRS);
		this.dropSelf(UGBlocks.DREADROCK_WALL);
		this.dropSelf(UGBlocks.DREADROCK_BRICK_WALL);
		this.dropSelf(UGBlocks.DREADROCK_BUTTON);
		this.dropSelf(UGBlocks.DREADROCK_PRESSURE_PLATE);
		this.add(UGBlocks.DEPTHROCK_POT.get(), noDrop());
		this.dropSelf(UGBlocks.THORNREED);
		this.dropSelf(UGBlocks.SEDIMENT_STONE);
		this.dropSelf(UGBlocks.POLISHED_SEDIMENT_STONE);
		this.dropSelf(UGBlocks.SEDIMENT_STONE_BRICKS);
		this.dropSelf(UGBlocks.CHISELED_SEDIMENT_STONE);
		this.dropSelf(UGBlocks.SMOOTH_SEDIMENT_STONE);
		this.dropSelf(UGBlocks.SEDIMENT_STONE_STAIRS);
		this.dropSelf(UGBlocks.POLISHED_SEDIMENT_STONE_STAIRS);
		this.dropSelf(UGBlocks.SEDIMENT_STONE_BRICK_STAIRS);
		this.dropSelf(UGBlocks.SMOOTH_SEDIMENT_STONE_STAIRS);
		this.slab(UGBlocks.SEDIMENT_STONE_SLAB);
		this.slab(UGBlocks.POLISHED_SEDIMENT_STONE_SLAB);
		this.slab(UGBlocks.SEDIMENT_STONE_BRICK_SLAB);
		this.slab(UGBlocks.SMOOTH_SEDIMENT_STONE_SLAB);
		this.dropSelf(UGBlocks.SEDIMENT_STONE_WALL);
		this.dropSelf(UGBlocks.POLISHED_SEDIMENT_STONE_WALL);
		this.dropSelf(UGBlocks.SEDIMENT_STONE_BRICK_WALL);
		this.dropSelf(UGBlocks.SMOOTH_SEDIMENT_STONE_WALL);
		this.dropSelf(UGBlocks.CRACKED_SEDIMENT_STONE_BRICKS);
		this.dropSelf(UGBlocks.DIRTY_SEDIMENT_STONE_BRICKS);
		this.add(UGBlocks.TWISTYBUSH.get(), block -> this.createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(UGItems.TWISTYTWIG).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))));
	}

	public void dropSelf(Supplier<? extends Block> block) {
		this.dropSelf(block.get());
	}

	public void slab(Supplier<? extends SlabBlock> slab) {
		this.add(slab.get(), this::createSlabItemTable);
	}

	public void dropOther(Supplier<? extends Block> brokenBlock, ItemLike droppedBlock) {
		this.dropOther(brokenBlock.get(), droppedBlock);
	}

	public void dropAsSilk(Supplier<? extends Block> block) {
		this.dropWhenSilkTouch(block.get());
	}

	public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends ItemLike> drop) {
		this.add(block.get(), (result) -> this.createSingleItemTableWithSilkTouch(result, drop.get()));
	}

	public void ore(Supplier<? extends Block> block, ItemLike drop) {
		this.add(block.get(), (result) -> this.createOreDrop(result, drop.asItem()));
	}

	public void mushroom(Supplier<? extends Block> block, ItemLike drop) {
		this.add(block.get(), (mushroom) -> this.createMushroomBlockDrop(mushroom, drop));
	}

	public void nuggetOre(Supplier<? extends Block> block, ItemLike drop) {
		HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		this.add(block.get(), (ore) -> this.createSilkTouchDispatchTable(ore, this.applyExplosionDecay(ore, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
	}

	private LootTable.Builder droopvine(Block block) {
		return LootTable.lootTable().withPool(this.applyExplosionDecay(block, LootPool.lootPool().add(LootItem.lootTableItem(UGItems.DROOPFRUIT.get())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Droopvine.GLOWY, true)))));
	}

	protected LootTable.Builder createLeavesDrops(Block leavesBlock, Block saplingBlock, ItemLike stick, float... chances) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return this.createSilkTouchOrShearsDispatchTable(leavesBlock, this.applyExplosionCondition(leavesBlock, LootItem.lootTableItem(saplingBlock))
				.when(BonusLevelTableCondition.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE), chances)))
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.when(this.doesNotHaveShearsOrSilkTouch())
					.add(this.applyExplosionDecay(leavesBlock, LootItem.lootTableItem(stick)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
						.when(BonusLevelTableCondition.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE), NORMAL_LEAVES_STICK_CHANCES)))
			);
	}

	//[VanillaCopy] of a few different methods from BlockLoot. These are here just so we can use the modded shears thing
	@Override
	protected LootTable.Builder createShearsDispatchTable(Block block, LootPoolEntryContainer.Builder<?> builder) {
		return createSelfDropDispatchTable(block, HAS_SHEARS, builder);
	}

	@Override
	protected LootTable.Builder createSilkTouchOrShearsDispatchTable(Block block, LootPoolEntryContainer.Builder<?> builder) {
		return createSelfDropDispatchTable(block, HAS_SHEARS.or(this.hasSilkTouch()), builder);
	}

	protected LootTable.Builder createShearsOnlyDrop(ItemLike item) {
		return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS).add(LootItem.lootTableItem(item)));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return UGBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
	}
}
