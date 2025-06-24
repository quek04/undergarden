package quek.undergarden.data.provider;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.FunctionUserBuilder;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import quek.undergarden.block.Droopvine;
import quek.undergarden.registry.UGItems;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class UGBlockLootTableProvider extends BlockLootSubProvider {

	private static final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
	private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(net.minecraft.world.level.block.Blocks.DRAGON_EGG, net.minecraft.world.level.block.Blocks.BEACON, net.minecraft.world.level.block.Blocks.CONDUIT, net.minecraft.world.level.block.Blocks.SKELETON_SKULL, net.minecraft.world.level.block.Blocks.WITHER_SKELETON_SKULL, net.minecraft.world.level.block.Blocks.PLAYER_HEAD, net.minecraft.world.level.block.Blocks.ZOMBIE_HEAD, net.minecraft.world.level.block.Blocks.CREEPER_HEAD, net.minecraft.world.level.block.Blocks.DRAGON_HEAD, net.minecraft.world.level.block.Blocks.SHULKER_BOX, net.minecraft.world.level.block.Blocks.BLACK_SHULKER_BOX, net.minecraft.world.level.block.Blocks.BLUE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.BROWN_SHULKER_BOX, net.minecraft.world.level.block.Blocks.CYAN_SHULKER_BOX, net.minecraft.world.level.block.Blocks.GRAY_SHULKER_BOX, net.minecraft.world.level.block.Blocks.GREEN_SHULKER_BOX, net.minecraft.world.level.block.Blocks.LIGHT_BLUE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.LIGHT_GRAY_SHULKER_BOX, net.minecraft.world.level.block.Blocks.LIME_SHULKER_BOX, net.minecraft.world.level.block.Blocks.MAGENTA_SHULKER_BOX, net.minecraft.world.level.block.Blocks.ORANGE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.PINK_SHULKER_BOX, net.minecraft.world.level.block.Blocks.PURPLE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.RED_SHULKER_BOX, net.minecraft.world.level.block.Blocks.WHITE_SHULKER_BOX, net.minecraft.world.level.block.Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(ImmutableSet.toImmutableSet());

	protected UGBlockLootTableProvider(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), new HashMap<>(), provider);
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
		this.add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
	}

	public void ore(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
		this.add(block.get(), (result) -> createOreDrop(result, drop.get()));
	}

	public void ore(Supplier<? extends Block> block, Item drop) {
		this.add(block.get(), (result) -> createOreDrop(result, drop));
	}

	public void nuggetOre(Supplier<? extends Block> block, Item drop) {
		HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		this.add(block.get(), (ore) -> createSilkTouchDispatchTable(ore, applyExplosionDecay(ore, LootItem.lootTableItem(drop).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))).apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))));
	}

	public static LootTable.Builder droopvine(Block block) {
		return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(UGItems.DROOPFRUIT.get())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Droopvine.GLOWY, true))));
	}

	public static LootTable.Builder tallGrassDrop(Block originalBlock, Block newBlock) {
		LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(newBlock).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).when(SHEARS);
		return LootTable.lootTable().withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))), new BlockPos(0, 1, 0)))).withPool(LootPool.lootPool().add(builder).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))).when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(originalBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))), new BlockPos(0, -1, 0))));
	}

	public static LootTable.Builder dropSeedsForStem(Block stem, Item stemSeed) {
		return LootTable.lootTable().withPool(withExplosionDecay(stem, LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(stemSeed).apply(SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(3, 0.53333336F))))));
	}

	public static <T extends FunctionUserBuilder<T>> T withExplosionDecay(ItemLike item, FunctionUserBuilder<T> function) {
		return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? function.apply(ApplyExplosionDecay.explosionDecay()) : function.unwrap();
	}
}