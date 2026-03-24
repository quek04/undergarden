package quek.undergarden.datagen.assets;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import quek.undergarden.block.BlisterberryBushBlock;
import quek.undergarden.block.DenizenTotemBlock;
import quek.undergarden.block.HangingGrongleLeavesBlock;
import quek.undergarden.block.SpreadingDeepturfBlock;
import quek.undergarden.registry.UGBlocks;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UGBlockStates extends BlockModelGenerators {

	public UGBlockStates(Consumer<BlockModelDefinitionGenerator> stateOutput, ItemModelOutput itemOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
		super(stateOutput, itemOutput, modelOutput);
	}

	@Override
	public void run() {
		UGBlockFamilies.getAllFamilies()
			.filter(BlockFamily::shouldGenerateModel)
			.forEach(blockFamily -> this.family(blockFamily.getBaseBlock()).generateFor(blockFamily));

		this.wrapBlockItem(UGBlocks.DREADROCK.get(), this::createTrivialCube);
		this.createNormalTorch(UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get());
		this.wrapBlockItem(UGBlocks.DEPTHROCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEEPSOIL.get(), this::createTrivialCube);
		this.createCrossBlockWithDefaultItem(UGBlocks.ASHEN_DEEPTURF.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_COAL_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_CLOGGRUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_FROSTSTEEL_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_UTHERIUM_ORE.get(), this::createTrivialCube);
		this.woodProvider(UGBlocks.SMOGSTEM_LOG.get()).logWithHorizontal(UGBlocks.SMOGSTEM_LOG.get()).wood(UGBlocks.SMOGSTEM_WOOD.get());
		this.woodProvider(UGBlocks.STRIPPED_SMOGSTEM_LOG.get()).logWithHorizontal(UGBlocks.STRIPPED_SMOGSTEM_LOG.get()).wood(UGBlocks.STRIPPED_SMOGSTEM_WOOD.get());
		this.createPlantWithDefaultItem(UGBlocks.SMOGSTEM_SAPLING.get(), UGBlocks.POTTED_SMOGSTEM_SAPLING.get(), PlantType.NOT_TINTED);
		this.woodProvider(UGBlocks.WIGGLEWOOD_LOG.get()).logWithHorizontal(UGBlocks.WIGGLEWOOD_LOG.get()).wood(UGBlocks.WIGGLEWOOD_WOOD.get());
		this.woodProvider(UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get()).logWithHorizontal(UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get()).wood(UGBlocks.STRIPPED_WIGGLEWOOD_WOOD.get());
		this.createPlantWithDefaultItem(UGBlocks.WIGGLEWOOD_SAPLING.get(), UGBlocks.POTTED_WIGGLEWOOD_SAPLING.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.SMOGSTEM_PLANKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.WIGGLEWOOD_PLANKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SMOGSTEM_LEAVES.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.WIGGLEWOOD_LEAVES.get(), this::createTrivialCube);
		this.createPlantWithDefaultItem(UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.POTTED_INDIGO_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.POTTED_VEIL_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.INK_MUSHROOM.get(), UGBlocks.POTTED_INK_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.BLOOD_MUSHROOM.get(), UGBlocks.POTTED_BLOOD_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_BRICKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CRACKED_DEPTHROCK_BRICKS.get(), this::createTrivialCube);
		this.createCrossBlockWithDefaultItem(UGBlocks.GLITTERKELP.get(), PlantType.NOT_TINTED);
		this.createCrossBlock(UGBlocks.GLITTERKELP_PLANT.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_BRICKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_REGALIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.TREMBLECRUST.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.TREMBLECRUST_BRICKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CRACKED_TREMBLECRUST_BRICKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.LOOSE_TREMBLECRUST.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_IRON_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_GOLD_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.COARSE_DEEPSOIL.get(), this::createTrivialCube);
		this.woodProvider(UGBlocks.GRONGLE_LOG.get()).logWithHorizontal(UGBlocks.GRONGLE_LOG.get()).wood(UGBlocks.GRONGLE_WOOD.get());
		this.woodProvider(UGBlocks.STRIPPED_GRONGLE_LOG.get()).logWithHorizontal(UGBlocks.STRIPPED_GRONGLE_LOG.get()).wood(UGBlocks.STRIPPED_GRONGLE_WOOD.get());
		this.createPlantWithDefaultItem(UGBlocks.GRONGLE_SAPLING.get(), UGBlocks.POTTED_GRONGLE_SAPLING.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.GRONGLE_LEAVES.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.GRONGLE_PLANKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get(), this::createTrivialCube);
		this.createCrossBlockWithDefaultItem(UGBlocks.SEEPING_INK.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.FORGOTTEN_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CLOGGRUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.FROSTSTEEL_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.UTHERIUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CHISELED_DEPTHROCK_BRICKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CHISELED_TREMBLECRUST_BRICKS.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SEDIMENT.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SEDIMENT_GLASS.get(), this::createTrivialCube);
		this.createCrossBlockWithDefaultItem(UGBlocks.FROZEN_DEEPTURF.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.CLOGGRUM_TILES.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_TILES.get(), this::createTrivialCube);
		this.createCarpet(UGBlocks.MOGMOSS_RUG.get());
		this.createCarpet(UGBlocks.BLUE_MOGMOSS_RUG.get());
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_COAL_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_IRON_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_DIAMOND_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_REGALIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.RAW_CLOGGRUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.RAW_FROSTSTEEL_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.POLISHED_DEPTHROCK.get(), this::createTrivialCube);
		this.createPlantWithDefaultItem(UGBlocks.AMOROUS_BRISTLE.get(), UGBlocks.POTTED_AMOROUS_BRISTLE.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.MISERABELL.get(), UGBlocks.POTTED_MISERABELL.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.BUTTERBUNCH.get(), UGBlocks.POTTED_BUTTERBUNCH.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.INDIGO_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.INDIGO_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.VEIL_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.VEIL_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.INK_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.INK_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.BLOOD_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.BLOOD_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, new MultiVariant(
			WeightedList.of(IntStream.range(1, 3)
				.mapToObj(i -> new Weighted<>(plainModel(TexturedModel.CUBE.createWithSuffix(block, i == 1 ? "" : String.valueOf(i), this.modelOutput)), 1))
				.collect(Collectors.toList())
			)))));
		this.wrapBlockItem(UGBlocks.DREADROCK_ROGDORIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.ROGDORIUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.UTHERIUM_GROWTH.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DREADROCK_UTHERIUM_ORE.get(), this::createTrivialCube);
		this.woodProvider(UGBlocks.ANCIENT_ROOT.get()).logWithHorizontal(UGBlocks.ANCIENT_ROOT.get());
		this.woodProvider(UGBlocks.ROGDORIC_ANCIENT_ROOT.get()).logWithHorizontal(UGBlocks.ROGDORIC_ANCIENT_ROOT.get());
		this.wrapBlockItem(UGBlocks.ANCIENT_ROOT_PLANKS.get(), this::createTrivialCube);

		var denizenTotemTexture = TextureMapping.cubeBottomTop(UGBlocks.DENIZEN_TOTEM.get()).copyAndUpdate(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.ANCIENT_ROOT.get(), "_end"));
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.DENIZEN_TOTEM.get()).with(createBooleanModelDispatch(DenizenTotemBlock.ACTIVE,
			plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(UGBlocks.DENIZEN_TOTEM.get(), "_active", denizenTotemTexture.copyAndUpdate(TextureSlot.SIDE, TextureMapping.getBlockTexture(UGBlocks.DENIZEN_TOTEM.get(), "_side_active")), this.modelOutput)),
			plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(UGBlocks.DENIZEN_TOTEM.get(), denizenTotemTexture, this.modelOutput))
		)));
		this.createCrossBlockWithDefaultItem(UGBlocks.PUFF_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.PUFF_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.PUFF_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DREADROCK_BRICKS.get(), this::createTrivialCube);

		this.blockStateOutput.accept(createSimpleBlock(UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), createRandomRotatedModel(plainModel(TexturedModel.CUBE_TOP_BOTTOM
			.get(UGBlocks.ASHEN_DEEPTURF_BLOCK.get())
			.updateTextures(m -> m.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEEPSOIL.get())))
			.create(UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), this.modelOutput)))));


		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.BLISTERBERRY_BUSH.get()).with(
			PropertyDispatch.initial(BlisterberryBushBlock.AGE).generate(age ->
				plainVariant(this.createSuffixedVariant(UGBlocks.BLISTERBERRY_BUSH.get(), "_stage" + age, ModelTemplates.CROSS, TextureMapping::cross)))));

		this.simpleBlockBuilder(UGBlocks.BOOMGOURD.get(), ModelTemplates.CUBE_BOTTOM_TOP, block -> TextureMapping.cubeBottomTop(block).copyAndUpdate(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.GLOOMGOURD.get(), "_top")));
		this.createPumpkinVariant(UGBlocks.CARVED_GLOOMGOURD.get(), TextureMapping.column(UGBlocks.GLOOMGOURD.get()));
		this.createPumpkinVariant(UGBlocks.GLOOM_O_LANTERN.get(), TextureMapping.column(UGBlocks.GLOOMGOURD.get()));
		this.createPumpkinVariant(UGBlocks.SHARD_O_LANTERN.get(), TextureMapping.column(UGBlocks.GLOOMGOURD.get()));
		this.createStems(UGBlocks.GLOOMGOURD_STEM.get(), UGBlocks.GLOOMGOURD_STEM_ATTACHED.get());

		this.createCrossBlockWithDefaultItem(UGBlocks.DEEPTURF.get(), PlantType.TINTED);

		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.DEEPTURF_BLOCK.get()).with(
			createBooleanModelDispatch(SpreadingDeepturfBlock.SNOWY,
				plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(UGBlocks.DEEPTURF_BLOCK.get(), "_snowy", new TextureMapping()
					.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), "_side"))
					.put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.SNOW))
					.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEEPSOIL.get())), this.modelOutput)),
				createRandomRotatedModel(plainModel(TexturedModel.CUBE_TOP_BOTTOM
					.get(UGBlocks.DEEPTURF_BLOCK.get())
					.updateTextures(m -> m.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEEPSOIL.get())))
					.create(UGBlocks.DEEPTURF_BLOCK.get(), this.modelOutput))))));

		this.blockStateOutput.accept(createSimpleBlock(UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), createRandomRotatedModel(plainModel(TexturedModel.CUBE_TOP_BOTTOM
			.get(UGBlocks.FROZEN_DEEPTURF_BLOCK.get())
			.updateTextures(m -> m.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEEPSOIL.get())))
			.create(UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), this.modelOutput)))));

		this.simpleBlockBuilder(UGBlocks.GOO_BLOCK.get(), ModelTemplates.CUBE_BOTTOM_TOP, TextureMapping::cubeBottomTop);

		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.HANGING_GRONGLE_LEAVES.get())
			.with(PropertyDispatch.initial(HangingGrongleLeavesBlock.HALF)
				.select(DoubleBlockHalf.LOWER, plainVariant(PlantType.NOT_TINTED.getCross().create(UGBlocks.HANGING_GRONGLE_LEAVES.get(), TextureMapping.cross(UGBlocks.HANGING_GRONGLE_LEAVES.get()), this.modelOutput)))
				.select(DoubleBlockHalf.UPPER, plainVariant(PlantType.NOT_TINTED.getCross().createWithSuffix(UGBlocks.HANGING_GRONGLE_LEAVES.get(), "_top", TextureMapping.cross(TextureMapping.getBlockTexture(UGBlocks.HANGING_GRONGLE_LEAVES.get(), "_top")), this.modelOutput)))));
	}

	public void wrapBlockItem(Block block, Consumer<Block> blockRegistry) {
		blockRegistry.accept(block);
		this.generateBlockItem(block);
	}

	public void wrapTintedBlockItem(Block block, ItemTintSource tint, Consumer<Block> blockRegistry) {
		blockRegistry.accept(block);
		this.generateTintedBlockItem(block, tint);
	}

	public void generateBlockItem(Block block) {
		this.registerSimpleItemModel(block, BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/"));
	}

	public void generateTintedBlockItem(Block block, ItemTintSource tint) {
		this.registerSimpleTintedItemModel(block, BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/"), tint);
	}

	public <B extends Block> void generateSpecialModel(B block, Block particleBlock, Function<B, ItemModel.Unbaked> itemModel) {
		this.createParticleOnlyBlock(block, particleBlock);
		this.itemModelOutput.accept(block.asItem(), itemModel.apply(block));
	}

	private void createCarpet(Block carpet) {
		MultiVariant model = plainVariant(TexturedModel.CARPET.get(carpet).create(carpet, this.modelOutput));
		this.blockStateOutput.accept(createSimpleBlock(carpet, model));
	}

	public void simpleBlockBuilder(Block block, ModelTemplate template, Function<Block, TextureMapping> mapping) {
		this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(template.create(block, mapping.apply(block), this.modelOutput))));
	}

	public MultiVariant createRandomRotatedModel(Variant model) {
		return variants(model, model.with(X_ROT_90), model.with(X_ROT_180), model.with(X_ROT_270));
	}
}