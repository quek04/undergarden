package quek.undergarden.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import quek.undergarden.Undergarden;
import quek.undergarden.world.gen.UGNoiseBasedChunkGenerator;

import java.util.List;
import java.util.OptionalLong;

public class UGDimensions {

	public static final ResourceKey<Level> UNDERGARDEN_LEVEL = ResourceKey.create(Registries.DIMENSION, name("undergarden"));

	public static final ResourceKey<NoiseGeneratorSettings> UNDERGARDEN_NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS, name("undergarden"));

	public static final ResourceKey<DimensionType> UNDERGARDEN_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, name("undergarden"));

	public static final ResourceKey<LevelStem> UNDERGARDEN_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, name("undergarden"));

	public static final ResourceKey<Level> OTHERSIDE_LEVEL = ResourceKey.create(Registries.DIMENSION, name("otherside"));

	public static final ResourceKey<NoiseGeneratorSettings> OTHERSIDE_NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS, name("otherside"));

	public static final ResourceKey<DimensionType> OTHERSIDE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, name("otherside"));

	public static final ResourceKey<LevelStem> OTHERSIDE_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, name("otherside"));

	private static ResourceLocation name(String name) {
		return new ResourceLocation(Undergarden.MODID, name);
	}

	public static void bootstrapType(BootstapContext<DimensionType> context) {
		context.register(UNDERGARDEN_DIM_TYPE, new DimensionType(
				OptionalLong.of(18000L), //fixed time
				false, //skylight
				true, //ceiling
				false, //ultrawarm
				true, //natural
				4.0D, //coordinate scale
				true, //bed works
				false, //respawn anchor works
				-64, // Minimum Y Level
				192, // Height + Min Y = Max Y
				128, // Logical Height
				BlockTags.INFINIBURN_OVERWORLD, //infiniburn
				name("undergarden"), // DimensionRenderInfo
				0.1F, // ambient light
				new DimensionType.MonsterSettings(true, false, UniformInt.of(0, 7), 0)));
		context.register(OTHERSIDE_DIM_TYPE, new DimensionType(
			OptionalLong.of(6000L), //fixed time
			true, //skylight
			false, //ceiling
			false, //ultrawarm
			true, //natural
			1.0D, //coordinate scale
			false, //bed works
			false, //respawn anchor works
			0, // Minimum Y Level
			320, // Height + Min Y = Max Y
			320, // Logical Height
			BlockTags.INFINIBURN_OVERWORLD, //infiniburn
			name("otherside"), // DimensionRenderInfo
			0.0F, // ambient light
			new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 0)));
	}

	public static void bootstrapStem(BootstapContext<LevelStem> context) {
		HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
		HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
		HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
		context.register(UNDERGARDEN_LEVEL_STEM, new LevelStem(dimTypes.getOrThrow(UNDERGARDEN_DIM_TYPE),
				new UGNoiseBasedChunkGenerator(UGBiomes.buildBiomeSource(biomeRegistry), noiseGenSettings.getOrThrow(UNDERGARDEN_NOISE_GEN))));
		context.register(OTHERSIDE_LEVEL_STEM, new LevelStem(dimTypes.getOrThrow(OTHERSIDE_DIM_TYPE),
			new NoiseBasedChunkGenerator(UGBiomes.buildOthersideBiomeSource(biomeRegistry), noiseGenSettings.getOrThrow(OTHERSIDE_NOISE_GEN))));
	}

	public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {
		HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
		HolderGetter<NormalNoise.NoiseParameters> noises = context.lookup(Registries.NOISE);
		DensityFunction xShift = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_X);
		DensityFunction zShift = NoiseRouterData.getFunction(functions, NoiseRouterData.SHIFT_Z);

		context.register(UNDERGARDEN_NOISE_GEN, new NoiseGeneratorSettings(
				NoiseSettings.create(-64, 192, 2, 2),
				UGBlocks.DEPTHROCK.get().defaultBlockState(),
				Blocks.WATER.defaultBlockState(),
				new NoiseRouter(
						DensityFunctions.zero(), //barrier
						DensityFunctions.zero(), //fluid level floodedness
						DensityFunctions.zero(), //fluid level spread
						DensityFunctions.zero(), //lava
						DensityFunctions.shiftedNoise2d(xShift, zShift, 0.25D, noises.getOrThrow(Noises.TEMPERATURE)), //temperature
						DensityFunctions.shiftedNoise2d(xShift, zShift, 0.25D, noises.getOrThrow(Noises.VEGETATION)), //vegetation
						NoiseRouterData.getFunction(functions, NoiseRouterData.CONTINENTS), //continents
						NoiseRouterData.getFunction(functions, NoiseRouterData.EROSION), //erosion
						DensityFunctions.rangeChoice(
								NoiseRouterData.getFunction(functions, NoiseRouterData.Y),
								0.0D,
								32.0D,
								DensityFunctions.constant(-1.0D),
								DensityFunctions.rangeChoice(
										NoiseRouterData.getFunction(functions, NoiseRouterData.Y),
										-64.0D,
										0.0D,
										DensityFunctions.constant(-2.0D),
										DensityFunctions.constant(0.0D)
								)
						), //depth
						NoiseRouterData.getFunction(functions, NoiseRouterData.RIDGES), //ridges
						DensityFunctions.zero(), //initial density
						DensityFunctions.mul(
								//DensityFunctions.constant(0.64D),
								DensityFunctions.interpolated(
										DensityFunctions.blendDensity(
												DensityFunctions.add(
														DensityFunctions.constant(2.5D),
														DensityFunctions.mul(
																DensityFunctions.yClampedGradient(24, -8, 0.0D, 1.0D),
																DensityFunctions.add(
																		DensityFunctions.constant(-2.5D),
																		DensityFunctions.add(
																				DensityFunctions.constant(0.5D),
																				DensityFunctions.mul(
																						DensityFunctions.yClampedGradient(-54, -64, 1.0D, 0.0D),
																						DensityFunctions.add(
																								DensityFunctions.constant(-0.5F),
																								BlendedNoise.createUnseeded(10.0D, 0.1D, 0.1D, 10.0D, 8.0D)
																						)
																				)
																		)
																)
														)
												)
										)
								),
								DensityFunctions.interpolated(
										DensityFunctions.blendDensity(
												DensityFunctions.add(
														DensityFunctions.constant(2.5D),
														DensityFunctions.mul(
																DensityFunctions.yClampedGradient(-8, 24, 0.0D, 1.0D),
																DensityFunctions.add(
																		DensityFunctions.constant(-2.5D),
																		DensityFunctions.add(
																				DensityFunctions.constant(0.5D),
																				DensityFunctions.mul(
																						DensityFunctions.yClampedGradient(110, 128, 1.0D, 0.0D),
																						DensityFunctions.add(
																								DensityFunctions.constant(-0.5F),
																								BlendedNoise.createUnseeded(0.1D, 0.3D, 80.0D, 60.0D, 1.0D)
																						)
																				)
																		)
																)
														)
												)
										)
								)
						).squeeze(), //final density
						DensityFunctions.zero(), //vein toggle
						DensityFunctions.zero(), //vein ridged
						DensityFunctions.zero() //vein gap
				),
				SurfaceRules.sequence(
						//bedrock floor
						SurfaceRules.ifTrue(SurfaceRules.verticalGradient("minecraft:bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
						//bedrock ceiling
						SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("minecraft:bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
						//filler depthrock
						SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0), SurfaceRules.state(UGBlocks.DEPTHROCK.get().defaultBlockState())),
						//sediment
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR), SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(33), 0)), SurfaceRules.state(UGBlocks.SEDIMENT.get().defaultBlockState())))),
						//mix coarse deepsoil into blood bog
						SurfaceRules.ifTrue(
								SurfaceRules.isBiome(UGBiomes.BLOOD_MUSHROOM_BOG),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D, 1.8D),
														SurfaceRules.state(UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState())
												),
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
														SurfaceRules.state(UGBlocks.DEEPTURF_BLOCK.get().defaultBlockState())
												)
										)
								)
						),
						//mix coarse deepsoil into smog spires
						SurfaceRules.ifTrue(
								SurfaceRules.isBiome(UGBiomes.SMOG_SPIRES),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D, 1.8D),
														SurfaceRules.state(UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState())
												),
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
														SurfaceRules.state(UGBlocks.ASHEN_DEEPTURF_BLOCK.get().defaultBlockState())
												)
										)
								)
						),
						//mix coarse deepsoil into barren biomes
						SurfaceRules.ifTrue(
								SurfaceRules.isBiome(UGBiomes.BARREN_ABYSS, UGBiomes.DEAD_SEA),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.noiseCondition(noises.getOrThrow(Noises.NETHER_STATE_SELECTOR).key(), 0.0D, 1.8D),
														SurfaceRules.state(UGBlocks.COARSE_DEEPSOIL.get().defaultBlockState())
												),
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
														SurfaceRules.state(UGBlocks.DEPTHROCK.get().defaultBlockState())
												),
												SurfaceRules.state(UGBlocks.DEPTHROCK.get().defaultBlockState())
										)
								)
						),
						//mix powder snow into icy biomes
						SurfaceRules.ifTrue(
								SurfaceRules.isBiome(UGBiomes.FROSTFIELDS, UGBiomes.ICY_SEA, UGBiomes.FROSTY_SMOGSTEM_FOREST),
								SurfaceRules.ifTrue(
										SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
										SurfaceRules.sequence(
												SurfaceRules.ifTrue(
														SurfaceRules.noiseCondition(noises.getOrThrow(Noises.POWDER_SNOW).key(), 0.45D, 0.58D),
														SurfaceRules.state(Blocks.POWDER_SNOW.defaultBlockState())
												),
												SurfaceRules.ifTrue(
														SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
														SurfaceRules.state(UGBlocks.FROZEN_DEEPTURF_BLOCK.get().defaultBlockState())
												)
										)
								)
						),
						//cover the ground in deepturf
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR), SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0), SurfaceRules.state(UGBlocks.DEEPTURF_BLOCK.get().defaultBlockState()))),
						//add deepsoil underneath
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0), SurfaceRules.state(UGBlocks.DEEPSOIL.get().defaultBlockState()))),
						//add shiverstone to icy biomes
						SurfaceRules.ifTrue(SurfaceRules.isBiome(UGBiomes.FROSTFIELDS, UGBiomes.ICY_SEA, UGBiomes.FROSTY_SMOGSTEM_FOREST), SurfaceRules.state(UGBlocks.SHIVERSTONE.get().defaultBlockState())),
                        //add dreadrock to bottom of world
                        SurfaceRules.ifTrue(SurfaceRules.verticalGradient("undergarden:dreadrock", VerticalAnchor.absolute(0), VerticalAnchor.absolute(5)), SurfaceRules.state(UGBlocks.DREADROCK.get().defaultBlockState()))
				        ),
				List.of(), //spawn targets
				32,
				false,
				false,
				false,
				false
		));
		context.register(OTHERSIDE_NOISE_GEN, new NoiseGeneratorSettings(
			NoiseSettings.create(0, 320, 2, 1),
			UGBlocks.TREMBLECRUST.get().defaultBlockState(),
			Blocks.AIR.defaultBlockState(),
			new NoiseRouter(
				DensityFunctions.zero(), //barrier
				DensityFunctions.zero(), //fluid level floodedness
				DensityFunctions.zero(), //fluid level spread
				DensityFunctions.zero(), //lava
				DensityFunctions.shiftedNoise2d(xShift, zShift, 0.25D, noises.getOrThrow(Noises.TEMPERATURE)), //temperature
				DensityFunctions.shiftedNoise2d(xShift, zShift, 0.25D, noises.getOrThrow(Noises.VEGETATION)), //vegetation
				DensityFunctions.zero(), //continents
				DensityFunctions.zero(), //erosion
				DensityFunctions.zero(), //depth
				DensityFunctions.zero(), //ridges
				DensityFunctions.zero(), //initial density
				DensityFunctions.mul(
					DensityFunctions.constant(0.64D),
					DensityFunctions.interpolated(
						DensityFunctions.blendDensity(
							DensityFunctions.add(
								DensityFunctions.constant(-0.234375),
								DensityFunctions.mul(
									DensityFunctions.yClampedGradient(4, 32, 0.0D, 1.0D),
									DensityFunctions.add(
										DensityFunctions.constant(0.234375),
										DensityFunctions.add(
											DensityFunctions.constant(-23.4375),
											DensityFunctions.mul(
												DensityFunctions.yClampedGradient(64, 440, 1.0D, 0.0D),
												DensityFunctions.add(
													DensityFunctions.constant(23.4375),
													BlendedNoise.createUnseeded(0.25D, 0.25D, 80, 160, 4)
												)
											)
										)
									)
								)
							)
						)
					)
				).squeeze(), //final density
				DensityFunctions.zero(), //vein toggle
				DensityFunctions.zero(), //vein ridged
				DensityFunctions.zero() //vein gap
			),
			SurfaceRules.state(UGBlocks.TREMBLECRUST.get().defaultBlockState()),
			List.of(),
			-64,
			false,
			false,
			false,
			false
		));
	}
}