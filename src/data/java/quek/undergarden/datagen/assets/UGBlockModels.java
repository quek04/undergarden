package quek.undergarden.datagen.assets;

import com.google.common.collect.ImmutableMap;
import com.mojang.math.Transformation;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.joml.Vector3f;
import quek.undergarden.Undergarden;
import quek.undergarden.block.*;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.client.render.item.DepthrockBedSpecialRenderer;
import quek.undergarden.client.render.item.DepthrockPotSpecialRenderer;
import quek.undergarden.client.render.item.GrongletSpecialRenderer;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UGBlockModels extends BlockModelGenerators {

	public UGBlockModels(Consumer<BlockModelDefinitionGenerator> stateOutput, ItemModelOutput itemOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
		super(stateOutput, itemOutput, modelOutput);
	}

	public static final Map<Block, TexturedModel> UG_TEXTURED_MODELS = ImmutableMap.<Block, TexturedModel>builder()
		.put(UGBlocks.SEDIMENT_STONE.get(), TexturedModel.COLUMN_WITH_WALL.get(UGBlocks.SEDIMENT_STONE.get()))
		.put(UGBlocks.CHISELED_SEDIMENT_STONE.get(), TexturedModel.COLUMN.get(UGBlocks.CHISELED_SEDIMENT_STONE.get()).updateTextures(m -> {
			m.put(TextureSlot.END, TextureMapping.getBlockTexture(UGBlocks.SEDIMENT_STONE.get(), "_top"));
			m.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(UGBlocks.CHISELED_SEDIMENT_STONE.get()));
		}))
		.put(UGBlocks.SMOOTH_SEDIMENT_STONE.get(), TexturedModel.createAllSame(TextureMapping.getBlockTexture(UGBlocks.SEDIMENT_STONE.get(), "_top")))
		.build();

	@Override
	public void run() {
		UGBlockFamilies.getAllFamilies()
			.filter(BlockFamily::shouldGenerateModel)
			.forEach(blockFamily -> this.family(blockFamily.getBaseBlock()).generateFor(blockFamily));
		this.generateBlockItem(UGBlocks.DEPTHROCK.get());
		this.generateBlockItem(UGBlocks.POLISHED_DEPTHROCK.get());
		this.generateBlockItem(UGBlocks.DEPTHROCK_BRICKS.get());
		this.generateBlockItem(UGBlocks.CRACKED_DEPTHROCK_BRICKS.get());
		this.generateBlockItem(UGBlocks.CHISELED_DEPTHROCK_BRICKS.get());
		this.generateBlockItem(UGBlocks.DEPTHROCK_TILES.get());
		this.generateBlockItem(UGBlocks.DEPTHROCK_PRESSURE_PLATE.get());

		this.generateBlockItem(UGBlocks.SHIVERSTONE.get());
		this.generateBlockItem(UGBlocks.SHIVERSTONE_BRICKS.get());
		this.generateBlockItem(UGBlocks.CRACKED_SHIVERSTONE_BRICKS.get());
		this.generateBlockItem(UGBlocks.CHISELED_SHIVERSTONE_BRICKS.get());
		this.generateBlockItem(UGBlocks.SHIVERSTONE_PRESSURE_PLATE.get());

		this.generateBlockItem(UGBlocks.SEDIMENT_STONE.get());
		this.generateBlockItem(UGBlocks.POLISHED_SEDIMENT_STONE.get());
		this.generateBlockItem(UGBlocks.SEDIMENT_STONE_BRICKS.get());
		this.generateBlockItem(UGBlocks.CRACKED_SEDIMENT_STONE_BRICKS.get());
		this.generateBlockItem(UGBlocks.CHISELED_SEDIMENT_STONE.get());
		this.generateBlockItem(UGBlocks.SMOOTH_SEDIMENT_STONE.get());

		this.generateBlockItem(UGBlocks.TREMBLECRUST.get());
		this.generateBlockItem(UGBlocks.TREMBLECRUST_BRICKS.get());
		this.generateBlockItem(UGBlocks.CRACKED_TREMBLECRUST_BRICKS.get());
		this.generateBlockItem(UGBlocks.CHISELED_TREMBLECRUST_BRICKS.get());
		this.generateBlockItem(UGBlocks.TREMBLECRUST_PRESSURE_PLATE.get());

		this.generateBlockItem(UGBlocks.DREADROCK.get());
		this.generateBlockItem(UGBlocks.DREADROCK_BRICKS.get());
		this.generateBlockItem(UGBlocks.DREADROCK_PRESSURE_PLATE.get());

		this.generateBlockItem(UGBlocks.CLOGGRUM_TILES.get());

		this.generateBlockItem(UGBlocks.SMOGSTEM_PLANKS.get());
		this.generateBlockItem(UGBlocks.SMOGSTEM_FENCE_GATE.get());
		this.generateBlockItem(UGBlocks.SMOGSTEM_PRESSURE_PLATE.get());

		this.generateBlockItem(UGBlocks.WIGGLEWOOD_PLANKS.get());
		this.generateBlockItem(UGBlocks.WIGGLEWOOD_FENCE_GATE.get());
		this.generateBlockItem(UGBlocks.WIGGLEWOOD_PRESSURE_PLATE.get());

		this.generateBlockItem(UGBlocks.GRONGLE_PLANKS.get());
		this.generateBlockItem(UGBlocks.GRONGLE_FENCE_GATE.get());
		this.generateBlockItem(UGBlocks.GRONGLE_PRESSURE_PLATE.get());

		this.generateBlockItem(UGBlocks.ANCIENT_ROOT_PLANKS.get());
		this.generateBlockItem(UGBlocks.ANCIENT_ROOT_FENCE_GATE.get());
		this.generateBlockItem(UGBlocks.ANCIENT_ROOT_PRESSURE_PLATE.get());

		this.createShardTorch(UGBlocks.SHARD_TORCH.get(), UGBlocks.SHARD_WALL_TORCH.get());
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
		this.createPlantWithDefaultItem(UGBlocks.INDIGO_MUSHROOM.get(), UGBlocks.POTTED_INDIGO_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.VEIL_MUSHROOM.get(), UGBlocks.POTTED_VEIL_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.INK_MUSHROOM.get(), UGBlocks.POTTED_INK_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.BLOOD_MUSHROOM.get(), UGBlocks.POTTED_BLOOD_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.createGrowingPlant(UGBlocks.GLITTERKELP.get(), UGBlocks.GLITTERKELP_PLANT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.registerSimpleFlatItemModel(UGItems.GLITTERKELP.get());
		this.wrapBlockItem(UGBlocks.DEPTHROCK_REGALIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.TREMBLECRUST_UTHERIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.LOOSE_TREMBLECRUST.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_IRON_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_GOLD_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DEPTHROCK_DIAMOND_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.COARSE_DEEPSOIL.get(), this::createTrivialCube);
		this.woodProvider(UGBlocks.GRONGLE_LOG.get()).logWithHorizontal(UGBlocks.GRONGLE_LOG.get()).wood(UGBlocks.GRONGLE_WOOD.get());
		this.woodProvider(UGBlocks.STRIPPED_GRONGLE_LOG.get()).logWithHorizontal(UGBlocks.STRIPPED_GRONGLE_LOG.get()).wood(UGBlocks.STRIPPED_GRONGLE_WOOD.get());
		this.createPlantWithDefaultItem(UGBlocks.GRONGLE_SAPLING.get(), UGBlocks.POTTED_GRONGLE_SAPLING.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.WIGGLEWOOD_LEAVES.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SMOGSTEM_LEAVES.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.GRONGLE_LEAVES.get(), this::createTrivialCube);
		this.createCrossBlockWithDefaultItem(UGBlocks.SEEPING_INK.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.FORGOTTEN_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CLOGGRUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.FROSTSTEEL_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.UTHERIUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.REGALIUM_BLOCK.get(), block -> this.createTrivialBlock(block, TexturedModel.COLUMN));
		this.wrapBlockItem(UGBlocks.SEDIMENT.get(), this::createTrivialCube);
		this.createGlassBlocks(UGBlocks.SEDIMENT_GLASS.get(), UGBlocks.SEDIMENT_GLASS_PANE.get());
		this.generateBlockItem(UGBlocks.SEDIMENT_GLASS.get());
		this.createCrossBlockWithDefaultItem(UGBlocks.FROZEN_DEEPTURF.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.MOGMOSS_RUG.get(), this::createCarpet);
		this.wrapBlockItem(UGBlocks.BLUE_MOGMOSS_RUG.get(), this::createCarpet);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_COAL_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_IRON_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_DIAMOND_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_CLOGGRUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_UTHERIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.SHIVERSTONE_REGALIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.RAW_CLOGGRUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.RAW_FROSTSTEEL_BLOCK.get(), this::createTrivialCube);
		this.createPlantWithDefaultItem(UGBlocks.AMOROUS_BRISTLE.get(), UGBlocks.POTTED_AMOROUS_BRISTLE.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.MISERABELL.get(), UGBlocks.POTTED_MISERABELL.get(), PlantType.NOT_TINTED);
		this.createPlantWithDefaultItem(UGBlocks.BUTTERBUNCH.get(), UGBlocks.POTTED_BUTTERBUNCH.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.INDIGO_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.INDIGO_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.VEIL_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.VEIL_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.INK_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.INK_MUSHROOM_STEM.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, variant(plainModel(ModelTemplates.CUBE_ALL.create(block, TextureMapping.cube(TextureMapping.getBlockTexture(Blocks.MUSHROOM_STEM)), this.modelOutput))))));
		this.wrapBlockItem(UGBlocks.BLOOD_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.BLOOD_MUSHROOM_STEM.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.ENGORGED_BLOOD_MUSHROOM_CAP.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, new MultiVariant(WeightedList.of(IntStream.range(1, 4)
			.mapToObj(i -> new Weighted<>(plainModel(TexturedModel.CUBE.createWithSuffix(block, i == 1 ? "" : "_" + i, this.modelOutput)), 1)).collect(Collectors.toList()))))));
		this.wrapBlockItem(UGBlocks.DREADROCK_ROGDORIUM_ORE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.ROGDORIUM_BLOCK.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.UTHERIUM_GROWTH.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.DREADROCK_UTHERIUM_ORE.get(), this::createTrivialCube);
		this.woodProvider(UGBlocks.ANCIENT_ROOT.get()).logWithHorizontal(UGBlocks.ANCIENT_ROOT.get());
		this.wrapBlockItem(UGBlocks.ROGDORIC_ANCIENT_ROOT.get(), block -> this.createAxisAlignedPillarBlock(block, TexturedModel.COLUMN_ALT.updateTexture(mapping -> mapping.put(TextureSlot.END, TextureMapping.getBlockTexture(UGBlocks.ANCIENT_ROOT.get(), "_top")))));

		var denizenTotemTexture = TextureMapping.cubeBottomTop(UGBlocks.DENIZEN_TOTEM.get()).copyAndUpdate(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.ANCIENT_ROOT.get(), "_top"));
		this.wrapBlockItem(UGBlocks.DENIZEN_TOTEM.get(), block -> this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(createBooleanModelDispatch(DenizenTotemBlock.ACTIVE,
			plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(block, "_active", denizenTotemTexture.copyAndUpdate(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side_active")), this.modelOutput)),
			plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, denizenTotemTexture, this.modelOutput))
		))));
		this.createPlantWithDefaultItem(UGBlocks.PUFF_MUSHROOM.get(), UGBlocks.POTTED_PUFF_MUSHROOM.get(), PlantType.NOT_TINTED);
		this.wrapBlockItem(UGBlocks.PUFF_MUSHROOM_CAP.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.PUFF_MUSHROOM_STEM.get(), this::createTrivialCube);

		this.wrapBlockItem(UGBlocks.ASHEN_DEEPTURF_BLOCK.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, createRandomRotatedModel(plainModel(TexturedModel.CUBE_TOP_BOTTOM
			.get(block)
			.updateTextures(m -> m.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEEPSOIL.get())))
			.create(block, this.modelOutput))))));

		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.BLISTERBERRY_BUSH.get()).with(
			PropertyDispatch.initial(BlisterberryBushBlock.AGE).generate(age ->
				plainVariant(this.createSuffixedVariant(UGBlocks.BLISTERBERRY_BUSH.get(), "_stage" + age, ModelTemplates.CROSS, TextureMapping::cross)))));

		this.wrapBlockItem(UGBlocks.BOOMGOURD.get(), block -> this.simpleBlockBuilder(block, ModelTemplates.CUBE_BOTTOM_TOP, b -> TextureMapping.cubeBottomTop(block).copyAndUpdate(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.GLOOMGOURD.get(), "_top"))));
		this.wrapBlockItem(UGBlocks.GLOOMGOURD.get(), block -> this.createTrivialBlock(block, TexturedModel.COLUMN));
		this.wrapBlockItem(UGBlocks.CARVED_GLOOMGOURD.get(), block -> this.createPumpkinVariant(block, TextureMapping.column(UGBlocks.GLOOMGOURD.get())));
		this.wrapBlockItem(UGBlocks.GLOOM_O_LANTERN.get(), block -> this.createPumpkinVariant(block, TextureMapping.column(UGBlocks.GLOOMGOURD.get())));
		this.wrapBlockItem(UGBlocks.SHARD_O_LANTERN.get(), block -> this.createPumpkinVariant(block, TextureMapping.column(UGBlocks.GLOOMGOURD.get())));
		this.createGloomgourdStems(UGBlocks.GLOOMGOURD_STEM.get(), UGBlocks.GLOOMGOURD_STEM_ATTACHED.get());

		this.createCrossBlock(UGBlocks.DEEPTURF.get(), PlantType.TINTED);
		this.createItemWithUGTint(UGBlocks.DEEPTURF.get());

		this.wrapTintedBlockItem(UGBlocks.DEEPTURF_BLOCK.get(), ItemModelUtils.constantTint(UndergardenClient.DEFAULT_TINT_COLOR), block -> this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(
			createBooleanModelDispatch(SpreadingDeepturfBlock.SNOWY,
				plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(block, "_snowy", new TextureMapping()
					.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), "_side"))
					.put(TextureSlot.TOP, TextureMapping.getBlockTexture(Blocks.SNOW))
					.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEEPSOIL.get())), this.modelOutput)),
				createRandomRotatedModel(plainModel(ModelLocationUtils.getModelLocation(block)))))));

		this.wrapBlockItem(UGBlocks.FROZEN_DEEPTURF_BLOCK.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, createRandomRotatedModel(plainModel(TexturedModel.CUBE_TOP_BOTTOM
			.get(block)
			.updateTextures(m -> m.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEEPSOIL.get())))
			.create(block, this.modelOutput))))));

		this.wrapBlockItem(UGBlocks.GOO_BLOCK.get(), block -> this.simpleBlockBuilder(block, ModelTemplates.CUBE_BOTTOM_TOP, TextureMapping::cubeBottomTop));

		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.HANGING_GRONGLE_LEAVES.get())
			.with(PropertyDispatch.initial(HangingGrongleLeavesBlock.HALF)
				.select(DoubleBlockHalf.LOWER, plainVariant(PlantType.NOT_TINTED.getCross().create(UGBlocks.HANGING_GRONGLE_LEAVES.get(), TextureMapping.cross(UGBlocks.HANGING_GRONGLE_LEAVES.get()), this.modelOutput)))
				.select(DoubleBlockHalf.UPPER, plainVariant(PlantType.NOT_TINTED.getCross().createWithSuffix(UGBlocks.HANGING_GRONGLE_LEAVES.get(), "_top", TextureMapping.cross(TextureMapping.getBlockTexture(UGBlocks.HANGING_GRONGLE_LEAVES.get(), "_top")), this.modelOutput)))));
		this.registerSimpleFlatItemModel(UGBlocks.HANGING_GRONGLE_LEAVES.get());

		this.createHangingSign(UGBlocks.STRIPPED_WIGGLEWOOD_LOG.get(), UGBlocks.WIGGLEWOOD_HANGING_SIGN.get(), UGBlocks.WIGGLEWOOD_WALL_HANGING_SIGN.get());
		this.createHangingSign(UGBlocks.STRIPPED_SMOGSTEM_LOG.get(), UGBlocks.SMOGSTEM_HANGING_SIGN.get(), UGBlocks.SMOGSTEM_WALL_HANGING_SIGN.get());
		this.createHangingSign(UGBlocks.STRIPPED_GRONGLE_LOG.get(), UGBlocks.GRONGLE_HANGING_SIGN.get(), UGBlocks.GRONGLE_WALL_HANGING_SIGN.get());
		this.createHangingSign(UGBlocks.ANCIENT_ROOT.get(), UGBlocks.ANCIENT_ROOT_HANGING_SIGN.get(), UGBlocks.ANCIENT_ROOT_WALL_HANGING_SIGN.get());

		Identifier baseModel = ModelTemplates.BED_INVENTORY.create(ModelLocationUtils.getModelLocation(UGItems.DEPTHROCK_BED.get()), TextureMapping.particle(UGBlocks.DEPTHROCK.get()), this.modelOutput);
		Transformation headTransformation = BedRenderer.modelTransform(Direction.SOUTH);
		ItemModel.Unbaked headPart = ItemModelUtils.specialModel(baseModel, headTransformation, new DepthrockBedSpecialRenderer.Unbaked(BedPart.HEAD));
		Transformation footTransformation = new Transformation(new Vector3f(0.0F, 0.0F, -1.0F), null, null, null).compose(headTransformation);
		ItemModel.Unbaked footPart = ItemModelUtils.specialModel(baseModel, footTransformation, new DepthrockBedSpecialRenderer.Unbaked(BedPart.FOOT));
		this.generateSpecialModel(UGBlocks.DEPTHROCK_BED, UGBlocks.DEPTHROCK, baseModel, id -> ItemModelUtils.composite(headPart, footPart));
		this.generateSpecialModel(UGBlocks.DEPTHROCK_POT, UGBlocks.DEPTHROCK, Undergarden.prefix("item/depthrock_pot"), id -> ItemModelUtils.specialModel(id, new DepthrockPotSpecialRenderer.Unbaked()));

		this.generateGronglet(UGBlocks.GRONGLET);
		this.generateGronglet(UGBlocks.UTHERIC_GRONGLET);
		this.generateGronglet(UGBlocks.ROGDORIC_GRONGLET);

		this.createBarsAndItem(UGBlocks.CLOGGRUM_BARS.get());

		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.UNDERGARDEN_PORTAL.get()).with(
			PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_AXIS)
				.select(Direction.Axis.X, plainVariant(ModelLocationUtils.getModelLocation(UGBlocks.UNDERGARDEN_PORTAL.get(), "_ns")))
				.select(Direction.Axis.Z, plainVariant(ModelLocationUtils.getModelLocation(UGBlocks.UNDERGARDEN_PORTAL.get(), "_ew")))
		));

		this.wrapBlockItem(UGBlocks.DEEPSOIL_FARMLAND.get(), block -> this.generateFarmlandBlock(block, UGBlocks.DEEPSOIL.get()));
		this.wrapBlockItem(UGBlocks.GOO.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(ModelLocationUtils.getModelLocation(block)))));
		this.wrapBlockItem(UGBlocks.SMOG_VENT.get(), block -> this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, TextureMapping.cubeBottomTop(block).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(UGBlocks.DEPTHROCK.get())), this.modelOutput)))));

		MultiVariant floorLantern = plainVariant(ModelLocationUtils.getModelLocation(UGBlocks.CLOGGRUM_LANTERN.get(), "_floor"));
		MultiVariant wallLantern = plainVariant(ModelLocationUtils.getModelLocation(UGBlocks.CLOGGRUM_LANTERN.get(), "_wall"));
		MultiVariant ceilingLantern = plainVariant(ModelLocationUtils.getModelLocation(UGBlocks.CLOGGRUM_LANTERN.get(), "_ceiling"));
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.CLOGGRUM_LANTERN.get())
			.with(PropertyDispatch.initial(CloggrumLanternBlock.FACE, CloggrumLanternBlock.FACING)
				.select(AttachFace.FLOOR, Direction.NORTH, floorLantern)
				.select(AttachFace.FLOOR, Direction.EAST, floorLantern.with(Y_ROT_90))
				.select(AttachFace.FLOOR, Direction.SOUTH, floorLantern.with(Y_ROT_180))
				.select(AttachFace.FLOOR, Direction.WEST, floorLantern.with(Y_ROT_270))
				.select(AttachFace.WALL, Direction.NORTH, wallLantern)
				.select(AttachFace.WALL, Direction.EAST, wallLantern.with(Y_ROT_90))
				.select(AttachFace.WALL, Direction.SOUTH, wallLantern.with(Y_ROT_180))
				.select(AttachFace.WALL, Direction.WEST, wallLantern.with(Y_ROT_270))
				.select(AttachFace.CEILING, Direction.NORTH, ceilingLantern)
				.select(AttachFace.CEILING, Direction.EAST, ceilingLantern.with(Y_ROT_90))
				.select(AttachFace.CEILING, Direction.SOUTH, ceilingLantern.with(Y_ROT_180))
				.select(AttachFace.CEILING, Direction.WEST, ceilingLantern.with(Y_ROT_270))
			));
		this.registerSimpleFlatItemModel(UGBlocks.CLOGGRUM_LANTERN.asItem());

		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.UNDERBEAN_BUSH.get()).with(
			PropertyDispatch.initial(UnderbeanBushBlock.AGE).generate(age ->
				plainVariant(this.createSuffixedVariant(UGBlocks.UNDERBEAN_BUSH.get(), "_stage" + age, ModelTemplates.CROSS, TextureMapping::cross)))));

		this.createDoublePlant(UGBlocks.TALL_DEEPTURF.get(), PlantType.TINTED);
		this.createDoublePlantItemWithUGTint(UGBlocks.TALL_DEEPTURF.get());

		this.generateTintedEmissiveCross(UGBlocks.SHIMMERWEED.get(), UGBlocks.POTTED_SHIMMERWEED.get(), UndergardenClient.DEFAULT_TINT_COLOR);
		this.createDoublePlantWithBulb(UGBlocks.TALL_SHIMMERWEED.get(), UndergardenClient.DEFAULT_TINT_COLOR);
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.DITCHBULB_PLANT.get())
			.with(PropertyDispatch.initial(DitchbulbBlock.AGE)
				.select(0, this.createTintedEmissiveCross(UGBlocks.DITCHBULB_PLANT.get(), "_ungrown", "_ungrown"))
				.select(1, this.createTintedEmissiveCross(UGBlocks.DITCHBULB_PLANT.get(), "", ""))));
		this.registerSimpleFlatItemModel(UGBlocks.DITCHBULB_PLANT.asItem());

		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UGBlocks.DEPTHROCK_PEBBLES.get())
			.with(PropertyDispatch.initial(DepthrockPebblesBlock.PEBBLES)
				.select(1, variant(plainModel(Undergarden.prefix("block/depthrock_pebble"))))
				.select(2, variant(plainModel(ModelLocationUtils.getModelLocation(UGBlocks.DEPTHROCK_PEBBLES.get()))))));
		this.createDroopfruitVines();

		ModelTemplate infuser = ModelTemplates.create("undergarden:template_infuser", TextureSlot.TOP);
		this.wrapBlockItem(UGBlocks.INFUSER.get(), block -> this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
			.with(PropertyDispatch.initial(InfuserBlock.STATE)
				.select(InfuserState.INACTIVE, variant(plainModel(infuser.create(block, new TextureMapping().put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")), this.modelOutput))))
				.select(InfuserState.INFUSING_ROGDORIUM, variant(plainModel(infuser.createWithSuffix(block, "_rogdorium", new TextureMapping().put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_rogdorium")), this.modelOutput))))
				.select(InfuserState.INFUSING_UTHERIUM, variant(plainModel(infuser.createWithSuffix(block, "_utherium", new TextureMapping().put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top_utherium")), this.modelOutput)))))));

		this.createParticleOnlyBlock(UGBlocks.VIRULENT_MIX.get());
		this.blockStateOutput.accept(createSimpleBlock(UGBlocks.VIRULENT_MIX_CAULDRON.get(), plainVariant(
			ModelTemplates.CAULDRON_FULL.create(UGBlocks.VIRULENT_MIX_CAULDRON.get(), TextureMapping.cauldron(TextureMapping.getBlockTexture(UGBlocks.VIRULENT_MIX.get(), "_still")), this.modelOutput))));

		this.registerSimpleFlatItemModel(UGBlocks.MUSHROOM_VEIL.get(), "_end");

		this.createCrossBlock(UGBlocks.THORNREED.get(), PlantType.NOT_TINTED);
		this.registerSimpleFlatItemModel(UGItems.THORNREED.get());

		this.wrapBlockItem(UGBlocks.CLOGGRUM_PILLAR.get(), block -> this.createAxisAlignedPillarBlock(block, TexturedModel.COLUMN));
		this.wrapBlockItem(UGBlocks.CLOGGRUM_GRATE.get(), this::createTrivialCube);
		this.wrapBlockItem(UGBlocks.CLOGGRUM_LADDER.get(), this::createNonTemplateHorizontalBlock);

		this.wrapBlockItem(UGBlocks.DIRTY_SEDIMENT_STONE_BRICKS.get(), this::createTrivialCube);

		this.createCrossBlockWithDefaultItem(UGBlocks.TWISTYBUSH.get(), PlantType.NOT_TINTED);

//		this.wrapBlockItem(UGBlocks.SEDIMENT_STONE.get(), block -> this.createTrivialBlock(block, TexturedModel.COLUMN_WITH_WALL));
//		this.wrapBlockItem(UGBlocks.POLISHED_SEDIMENT_STONE.get(), this::createTrivialCube);
//		this.wrapBlockItem(UGBlocks.SEDIMENT_STONE_BRICKS.get(), this::createTrivialCube);
//		this.wrapBlockItem(UGBlocks.CHISELED_SEDIMENT_STONE.get(), block -> this.createTrivialBlock(block, TexturedModel.COLUMN_ALT.updateTexture(mapping -> mapping.put(TextureSlot.END, TextureMapping.getBlockTexture(UGBlocks.SEDIMENT_STONE.get(), "_top")))));
	}

	@Override
	public UGBlockFamilyProvider family(Block block) {
		TexturedModel model = UG_TEXTURED_MODELS.getOrDefault(block, TexturedModel.CUBE.get(block));
		return (UGBlockFamilyProvider) new UGBlockFamilyProvider(model.getMapping()).fullBlock(block, model.getTemplate());
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

	public void generateGronglet(Holder<Block> gronglet) {
		this.generateSpecialModel(gronglet, Blocks.NETHER_WART_BLOCK.builtInRegistryHolder(), Undergarden.prefix("item/gronglet"), id -> ItemModelUtils.specialModel(id, new GrongletSpecialRenderer.Unbaked(Undergarden.prefix("textures/entity/gronglet/" + gronglet.getKey().identifier().getPath() + ".png"))));
	}

	public <B extends Holder<Block>> void generateSpecialModel(B block, Holder<Block> particleBlock, Identifier parent, Function<Identifier, ItemModel.Unbaked> itemModel) {
		this.createParticleOnlyBlock(block.value(), particleBlock.value());
		Item item = block.value().asItem();
		this.itemModelOutput.accept(item, itemModel.apply(parent));
	}

	private void createCarpet(Block carpet) {
		MultiVariant model = plainVariant(TexturedModel.CARPET.get(carpet).create(carpet, this.modelOutput));
		this.blockStateOutput.accept(createSimpleBlock(carpet, model));
	}

	public void simpleBlockBuilder(Block block, ModelTemplate template, Function<Block, TextureMapping> mapping) {
		this.blockStateOutput.accept(createSimpleBlock(block, plainVariant(template.create(block, mapping.apply(block), this.modelOutput))));
	}

	public MultiVariant createRandomRotatedModel(Variant model) {
		return variants(model, model.with(Y_ROT_270), model.with(Y_ROT_180), model.with(Y_ROT_90));
	}

	private void generateFarmlandBlock(Block farmland, Block dirt) {
		TextureMapping dryTextures = new TextureMapping()
			.put(TextureSlot.DIRT, TextureMapping.getBlockTexture(dirt))
			.put(TextureSlot.TOP, TextureMapping.getBlockTexture(farmland));
		TextureMapping moistTextures = new TextureMapping()
			.put(TextureSlot.DIRT, TextureMapping.getBlockTexture(dirt))
			.put(TextureSlot.TOP, TextureMapping.getBlockTexture(farmland, "_moist"));
		MultiVariant dryModel = plainVariant(ModelTemplates.FARMLAND.create(farmland, dryTextures, this.modelOutput));
		MultiVariant moistModel = plainVariant(
			ModelTemplates.FARMLAND.create(ModelLocationUtils.getModelLocation(farmland, "_moist"), moistTextures, this.modelOutput)
		);
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(farmland).with(createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, moistModel, dryModel)));
	}

	private void generateTintedEmissiveCross(Block plant, Block potted, int color) {
		this.blockStateOutput.accept(createSimpleBlock(plant, this.createTintedEmissiveCross(plant, "", "")));
		this.blockStateOutput.accept(createSimpleBlock(potted, this.createPottedTintedEmissiveCross(plant, potted)));
		this.registerSimpleTintedItemModel(plant, this.createFlatItemModelWithBlockTextureAndOverlay(plant.asItem(), plant, "_emissive"), ItemModelUtils.constantTint(color));
	}

	private MultiVariant createTintedEmissiveCross(Block plant, String suffix, String textureSuffix) {
		ModelTemplate template = ModelTemplates.create("undergarden:tinted_cross_emissive", TextureSlot.CROSS, TextureSlot.CROSS_EMISSIVE);
		return plainVariant(template.createWithSuffix(plant, suffix, crossEmissive(plant, textureSuffix), this.modelOutput));
	}

	private MultiVariant createPottedTintedEmissiveCross(Block plant, Block potted) {
		ModelTemplate pottedTemplate = ModelTemplates.create("undergarden:flower_pot_tinted_cross_emissive", TextureSlot.PLANT, TextureSlot.CROSS_EMISSIVE);
		return plainVariant(pottedTemplate.create(potted, TextureMapping.plantEmissive(plant), this.modelOutput));
	}

	public void createDoublePlantWithBulb(Block block, int tintColor) {
		MultiVariant topModel = this.createTintedEmissiveCross(block, "_top", "_top");
		MultiVariant bottomModel = plainVariant(this.createSuffixedVariant(block, "_bottom", PlantType.TINTED.getCross(), TextureMapping::cross));
		this.createDoubleBlock(block, topModel, bottomModel);
		this.registerSimpleTintedItemModel(block, ModelTemplates.TWO_LAYERED_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layered(TextureMapping.getBlockTexture(block, "_top"), TextureMapping.getBlockTexture(block, "_top_emissive")), this.modelOutput), ItemModelUtils.constantTint(tintColor));
	}

	public void createDroopfruitVines() {
		Block head = UGBlocks.DROOPVINE.get();
		MultiVariant offHead = plainVariant(this.createSuffixedVariant(head, "", ModelTemplates.TINTED_CROSS, TextureMapping::cross));
		MultiVariant onHead = this.createTintedEmissiveCross(head, "_glowy", "");
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(head).with(createBooleanModelDispatch(Droopvine.GLOWY, onHead, offHead)));
		Block body = UGBlocks.DROOPVINE_PLANT.get();
		MultiVariant offBody = plainVariant(this.createSuffixedVariant(body, "", ModelTemplates.TINTED_CROSS, TextureMapping::cross));
		MultiVariant onBody = this.createTintedEmissiveCross(body, "_glowy", "");
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(body).with(createBooleanModelDispatch(Droopvine.GLOWY, onBody, offBody)));
	}

	public void createGloomgourdStems(Block growingStem, Block attachedStem) {
		this.registerSimpleFlatItemModel(growingStem.asItem());
		TextureMapping growingMapping = TextureMapping.stem(Blocks.MELON_STEM);
		TextureMapping attachedMapping = TextureMapping.attachedStem(Blocks.MELON_STEM, Blocks.ATTACHED_MELON_STEM);
		MultiVariant attachedStemModel = plainVariant(ModelTemplates.ATTACHED_STEM.create(attachedStem, attachedMapping, this.modelOutput));
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(attachedStem, attachedStemModel).with(
			PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
				.select(Direction.WEST, NOP)
				.select(Direction.SOUTH, Y_ROT_270)
				.select(Direction.NORTH, Y_ROT_90)
				.select(Direction.EAST, Y_ROT_180)));
		this.blockStateOutput.accept(MultiVariantGenerator.dispatch(growingStem).with(
			PropertyDispatch.initial(BlockStateProperties.AGE_7).generate(i -> plainVariant(ModelTemplates.STEMS[i].create(growingStem, growingMapping, this.modelOutput)))));
	}

	public static TextureMapping crossEmissive(Block block, String suffix) {
		return new TextureMapping().put(TextureSlot.CROSS, TextureMapping.getBlockTexture(block, suffix)).put(TextureSlot.CROSS_EMISSIVE, TextureMapping.getBlockTexture(block, suffix + "_emissive"));
	}

	public void createShardTorch(Block ground, Block wall) {
		TextureMapping textures = TextureMapping.torch(ground);
		this.blockStateOutput.accept(createSimpleBlock(ground, plainVariant(Undergarden.prefix("block/shard_torch"))));
		this.blockStateOutput
			.accept(MultiVariantGenerator.dispatch(wall, plainVariant(Undergarden.prefix("block/shard_wall_torch"))).with(ROTATION_TORCH));
		this.registerSimpleFlatItemModel(ground);
	}

	public void createItemWithUGTint(Block block) {
		Identifier itemModel = this.createFlatItemModelWithBlockTexture(block.asItem(), block);
		this.registerSimpleTintedItemModel(block, itemModel, ItemModelUtils.constantTint(UndergardenClient.DEFAULT_TINT_COLOR));
	}

	public void createDoublePlantItemWithUGTint(Block block) {
		Identifier itemModel = this.createFlatItemModelWithBlockTexture(block.asItem(), block, "_top");
		this.registerSimpleTintedItemModel(block, itemModel, ItemModelUtils.constantTint(UndergardenClient.DEFAULT_TINT_COLOR));
	}

	//this is probably stupid
	public class UGBlockFamilyProvider extends BlockModelGenerators.BlockFamilyProvider {

		public UGBlockFamilyProvider(TextureMapping mapping) {
			super(mapping);
		}

		@Override
		public BlockModelGenerators.BlockFamilyProvider fullBlockVariant(Block variant) {
			TexturedModel model = UG_TEXTURED_MODELS.getOrDefault(variant, TexturedModel.CUBE.get(variant));
			MultiVariant variantModel = BlockModelGenerators.plainVariant(model.create(variant, UGBlockModels.this.modelOutput));
			UGBlockModels.this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(variant, variantModel));
			return this;
		}
	}
}