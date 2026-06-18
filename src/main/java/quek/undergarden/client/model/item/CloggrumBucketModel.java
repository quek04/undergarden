package quek.undergarden.client.model.item;

import com.google.common.collect.Maps;
import com.mojang.math.Transformation;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.block.dispatch.BlockModelRotation;
import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.item.*;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.cuboid.ItemModelGenerator;
import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.NeoForgeRenderTypes;
import net.neoforged.neoforge.client.color.item.FluidContentsTint;
import net.neoforged.neoforge.client.model.ComposedModelState;
import net.neoforged.neoforge.client.model.ExtraFaceData;
import net.neoforged.neoforge.client.model.UnbakedElementsHelper;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGDataComponents;

import java.util.*;

//copy of DynamicFluidContainerModel that allows for the addition of mob and solid block covers
public class CloggrumBucketModel implements ItemModel {
	private static final Map<Identifier, Identifier> TEXTURE_MAP = Maps.newHashMap();
	private static final Transformation DEPTH_OFFSET_TRANSFORM = new Transformation(new Vector3f(), new Quaternionf(), new Vector3f(1.002F, 1.002F, 1.002F), new Quaternionf());
	private static final ModelDebugName DEBUG_NAME = () -> "CloggrumBucketModel";

	private static final Material FALLBACK_CONTENT = new Material(getContentTexture(Undergarden.prefix("fallback")));

	private static final RenderType RENDER_TYPE_CUTOUT_UNLIT_BLOCK = NeoForgeRenderTypes.getItemCutoutUnlit(TextureAtlas.LOCATION_BLOCKS);
	private static final RenderType RENDER_TYPE_CUTOUT_UNLIT_ITEM = NeoForgeRenderTypes.getItemCutoutUnlit(TextureAtlas.LOCATION_ITEMS);

	private final Unbaked unbakedModel;
	private final BakingContext bakingContext;
	private final Matrix4fc transformation;
	private final ItemTransforms itemTransforms;
	private final Map<Identifier, ItemModel> cache = new IdentityHashMap<>(); // contains all the baked models since they'll never change

	private CloggrumBucketModel(Unbaked unbakedModel, BakingContext bakingContext, Matrix4fc transformation) {
		this.unbakedModel = unbakedModel;
		this.bakingContext = bakingContext;
		this.transformation = transformation;
		// Source ItemTransforms from the base item model
		var baseItemModel = bakingContext.blockModelBaker().getModel(Identifier.withDefaultNamespace("item/generated"));
		this.itemTransforms = baseItemModel.getTopTransforms();
	}

	//textures are fetched from the item/bucket_content folder as other mods use this directory thanks to BucketLib
	public static Identifier getContentTexture(Identifier otherContentLocation) {
		Identifier texture = TEXTURE_MAP.get(otherContentLocation);
		if (texture == null) {
			String textureLocation = String.format("item/bucket_content/%s", otherContentLocation.getPath());
			texture = otherContentLocation.withPath(textureLocation);
			TEXTURE_MAP.put(otherContentLocation, texture);
		}
		return texture;
	}

	public ItemModel bake(Fluid fluid, @Nullable Identifier content, boolean isLower) {
		ModelBaker baker = this.bakingContext.blockModelBaker();
		MaterialBaker materials = baker.materials();
		FluidModel fluidModel = Minecraft.getInstance()
			.getModelManager()
			.getFluidStateModelSet()
			.get(fluid.defaultFluidState());

		Material particleLocation = this.unbakedModel.textures.particle.orElse(null);
		Material baseLocation = this.unbakedModel.textures.base.orElse(null);
		Material fluidMaskLocation = this.unbakedModel.textures.fluid.orElse(null);
		Material otherContentLocation = content != null ? new Material(getContentTexture(content)) : null;

		Material.Baked baseSprite = baseLocation != null ? materials.get(baseLocation, DEBUG_NAME) : null;
		Material.Baked fluidSprite = fluid != Fluids.EMPTY ? fluidModel.stillMaterial() : null;
		Material.Baked otherContentSprite = otherContentLocation != null ? materials.get(otherContentLocation, DEBUG_NAME) : null;

		Material.Baked particleSprite = particleLocation != null ? materials.get(particleLocation, DEBUG_NAME) : null;

		if (otherContentSprite != null && MissingTextureAtlasSprite.getLocation().equals(otherContentSprite.sprite().contents().name())) {
			otherContentSprite = materials.get(this.unbakedModel.textures().defaultContent().orElse(FALLBACK_CONTENT), DEBUG_NAME);
		}

		if (particleSprite == null) particleSprite = baseSprite;
		if (particleSprite == null) particleSprite = otherContentSprite;
		if (particleSprite == null) particleSprite = fluidSprite;

		// if the fluid is lighter than air, will manipulate the initial state to be rotated 180deg to turn it upside down
		ModelState state = BlockModelRotation.IDENTITY;
		if (this.unbakedModel.flipGas && fluid != Fluids.EMPTY && fluid.getFluidType().isLighterThanAir()) {
			state = new ComposedModelState(state, new Transformation(null, new Quaternionf(0, 0, 1, 0), null, null));
		}

		List<ItemModel> subModels = new ArrayList<>();
		ModelRenderProperties renderProperties = new ModelRenderProperties(false, particleSprite, this.itemTransforms);

		if (baseLocation != null) {
			//lower bucket by a pixel to prevent the need of a 2nd texture
			Vector3f lowered = isLower ? new Vector3f(0.0F, -1.0F / 16.0F, 0.0F) : null;
			ModelState baseState = new ComposedModelState(state, new Transformation(lowered, null, null, null));
			QuadCollection quads = baker.compute(new ItemModelGenerator.ItemLayerKey(baseSprite, baseState, 0));
			subModels.add(new CuboidItemModelWrapper(List.of(), quads, renderProperties, this.transformation));
		}

		if (otherContentSprite != null) {
			ModelState transformedState = new ComposedModelState(state, DEPTH_OFFSET_TRANSFORM);
			QuadCollection quads = baker.compute(new ItemModelGenerator.ItemLayerKey(otherContentSprite, transformedState, 0));
			subModels.add(new CuboidItemModelWrapper(List.of(), quads, renderProperties, this.transformation));
		} else if (fluidMaskLocation != null && fluidSprite != null) {
			Material.Baked templateSprite = materials.get(fluidMaskLocation, DEBUG_NAME);
			// Fluid layer
			ModelState transformedState = new ComposedModelState(state, DEPTH_OFFSET_TRANSFORM);
			boolean emissive = this.unbakedModel.applyFluidLuminosity && fluid.getFluidType().getLightLevel() > 0;
			BakedQuad.MaterialInfo fluidInfo = baker.interner().materialInfo(new BakedQuad.MaterialInfo(
				fluidSprite.sprite(), ChunkSectionLayer.SOLID, computeFluidItemRenderType(fluidSprite, emissive), 0, !emissive, emissive ? Level.MAX_BRIGHTNESS : 0, !emissive));
			QuadCollection quads = UnbakedElementsHelper.bakeItemMaskQuads(baker, templateSprite, fluidInfo, transformedState, ExtraFaceData.DEFAULT); // Use template as mask

			subModels.add(new CuboidItemModelWrapper(List.of(FluidContentsTint.INSTANCE), quads, renderProperties, this.transformation));
		}

		return new CompositeModel(subModels);
	}

	@Override
	public void update(ItemStackRenderState state, ItemStack stack, ItemModelResolver resolver, ItemDisplayContext context, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
		boolean containsEntityType = false;
		Identifier content = null;
		if (stack.get(DataComponents.BUCKET_ENTITY_DATA) != null) {
			Identifier id = Identifier.tryParse(stack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag().getStringOr("id", ""));
			if (id != null) {
				content = Identifier.fromNamespaceAndPath(id.getNamespace(), id.getPath());
				containsEntityType = true;
			}
		} else if (stack.get(UGDataComponents.STORED_BLOCK) != null) {
			content = BuiltInRegistries.BLOCK.getKey(stack.get(UGDataComponents.STORED_BLOCK).getBlock());
		}
		SimpleFluidContent fluid = null;
		if (content == null) {
			fluid = stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY);
			Identifier location = BuiltInRegistries.FLUID.getKey(fluid.getFluid());
			content = (location != BuiltInRegistries.FLUID.getDefaultKey()) ? location : null;
		}
		ItemModel bakedModel = this.cache.get(content);
		if (bakedModel == null && content != null) {
			if (fluid == null) {
				bakedModel = this.bake(Fluids.EMPTY, content, containsEntityType);
			} else {
				bakedModel = this.bake(fluid.getFluid(), null, false);
			}
			this.cache.put(content, bakedModel);
		}
		bakedModel.update(state, stack, resolver, context, level, owner, seed);
	}

	private static RenderType computeFluidItemRenderType(Material.Baked material, boolean emissive) {
		if (material.sprite().atlasLocation().equals(TextureAtlas.LOCATION_BLOCKS)) {
			return emissive ? RENDER_TYPE_CUTOUT_UNLIT_BLOCK : Sheets.cutoutBlockItemSheet();
		} else {
			return emissive ? RENDER_TYPE_CUTOUT_UNLIT_ITEM : Sheets.cutoutItemSheet();
		}
	}

	public record Textures(Optional<Material> particle, Optional<Material> base, Optional<Material> fluid, Optional<Material> defaultContent) {
		public static final Codec<Textures> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Material.CODEC.optionalFieldOf("particle").forGetter(Textures::particle),
			Material.CODEC.optionalFieldOf("base").forGetter(Textures::base),
			Material.CODEC.optionalFieldOf("fluid").forGetter(Textures::fluid),
			Material.CODEC.optionalFieldOf("default_content").forGetter(Textures::defaultContent)
		).apply(instance, Textures::new));
	}

	public record Unbaked(Textures textures, Fluid fluid, boolean flipGas, boolean applyFluidLuminosity) implements ItemModel.Unbaked {
		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Textures.CODEC.fieldOf("textures").forGetter(Unbaked::textures),
			BuiltInRegistries.FLUID.byNameCodec().fieldOf("fluid").forGetter(Unbaked::fluid),
			Codec.BOOL.optionalFieldOf("flip_gas", true).forGetter(Unbaked::flipGas),
			Codec.BOOL.optionalFieldOf("apply_fluid_luminosity", true).forGetter(Unbaked::applyFluidLuminosity)
		).apply(instance, Unbaked::new));

		@Override
		public MapCodec<? extends ItemModel.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public ItemModel bake(BakingContext context, Matrix4fc transformation) {
			return new CloggrumBucketModel(this, context, transformation);
		}

		@Override
		public void resolveDependencies(Resolver resolver) {
			//No dependencies
		}
	}
}
