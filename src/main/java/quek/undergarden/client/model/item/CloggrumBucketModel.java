package quek.undergarden.client.model.item;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.math.Transformation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.model.CompositeModel;
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;
import net.neoforged.neoforge.client.model.QuadTransformers;
import net.neoforged.neoforge.client.model.SimpleModelState;
import net.neoforged.neoforge.client.model.geometry.*;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import quek.undergarden.Undergarden;
import quek.undergarden.item.bucket.UGBucketItem;
import quek.undergarden.registry.UGDataComponents;

import java.util.Map;
import java.util.function.Function;

//copy of DynamicFluidContainerModel that allows for the addition of mob and solid block covers
public class CloggrumBucketModel implements IUnbakedGeometry<CloggrumBucketModel> {
	private static final Map<ResourceLocation, ResourceLocation> TEXTURE_MAP = Maps.newHashMap();
	private static final Transformation DEPTH_OFFSET_TRANSFORM = new Transformation(new Vector3f(), new Quaternionf(), new Vector3f(1.002F, 1.002F, 1.002F), new Quaternionf());

	private final Fluid fluid;
	@Nullable
	private final ResourceLocation otherContent;
	private final boolean flipGas;
	private final boolean applyFluidLuminosity;
	private final boolean isLower;

	private CloggrumBucketModel(Fluid fluid, @Nullable ResourceLocation otherContent, boolean flipGas, boolean applyFluidLuminosity, boolean isLower) {
		this.fluid = fluid;
		this.otherContent = otherContent;
		this.flipGas = flipGas;
		this.applyFluidLuminosity = applyFluidLuminosity;
		this.isLower = isLower;
	}

	public CloggrumBucketModel withFluid(Fluid newFluid) {
		return new CloggrumBucketModel(newFluid, this.otherContent, this.flipGas, this.applyFluidLuminosity, this.isLower);
	}

	public CloggrumBucketModel withOtherContent(ResourceLocation otherContent, boolean isLower) {
		return new CloggrumBucketModel(Fluids.EMPTY, otherContent, this.flipGas, this.applyFluidLuminosity, isLower);
	}

	//textures are fetched from the item/bucket_content folder as other mods use this directory thanks to BucketLib
	public static ResourceLocation getContentTexture(ResourceLocation otherContentLocation) {
		ResourceLocation texture = TEXTURE_MAP.get(otherContentLocation);
		if (texture == null) {
			String textureLocation = String.format("item/bucket_content/%s", otherContentLocation.getPath());
			texture = otherContentLocation.withPath(textureLocation);
			TEXTURE_MAP.put(otherContentLocation, texture);
		}
		return texture;
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
		Material particleLocation = context.hasMaterial("particle") ? context.getMaterial("particle") : null;
		Material baseLocation = context.hasMaterial("base") ? context.getMaterial("base") : null;

		Material otherContentLocation = null;
		Material fluidLocation = null;
		Material fluidMaskLocation = null;
		if (this.otherContent != null) {
			otherContentLocation = new Material(InventoryMenu.BLOCK_ATLAS, getContentTexture(this.otherContent));
		}
		if (this.fluid != Fluids.EMPTY) {
			fluidLocation = new Material(InventoryMenu.BLOCK_ATLAS, getContentTexture(BuiltInRegistries.FLUID.getKey(this.fluid)));
			if (context.hasMaterial("fluid")) {
				fluidMaskLocation = context.getMaterial("fluid");
			}
		}

		if (otherContentLocation == null && fluidLocation != null && !MissingTextureAtlasSprite.getLocation().equals(spriteGetter.apply(fluidLocation).contents().name())) {
			otherContentLocation = fluidLocation;
		}

		TextureAtlasSprite baseSprite = baseLocation != null ? spriteGetter.apply(baseLocation) : null;
		TextureAtlasSprite otherContentSprite = null;
		if (otherContentLocation != null) {
			otherContentSprite = spriteGetter.apply(otherContentLocation);
		}
		TextureAtlasSprite fluidSprite = this.fluid != Fluids.EMPTY ? spriteGetter.apply(ClientHooks.getBlockMaterial(IClientFluidTypeExtensions.of(this.fluid).getStillTexture())) : null;
		TextureAtlasSprite particleSprite = particleLocation != null ? spriteGetter.apply(particleLocation) : null;
		if (particleSprite == null) particleSprite = baseSprite;
		if (particleSprite == null) particleSprite = otherContentSprite;
		if (particleSprite == null) particleSprite = fluidSprite;

		// if the fluid is lighter than air, will manipulate the initial state to be rotated 180deg to turn it upside down
		boolean flip = this.flipGas && this.fluid != Fluids.EMPTY && this.fluid.getFluidType().isLighterThanAir();
		modelState = new SimpleModelState(modelState.getRotation().compose(new Transformation(null, flip ? new Quaternionf(0.0F, 0.0F, 1.0F, 0.0F) : null, null, null)));

		// We need to disable GUI 3D and block lighting for this to render properly
		var itemContext = StandaloneGeometryBakingContext.builder(context).withGui3d(false).withUseBlockLight(false).build(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "cloggrum_bucket"));
		var modelBuilder = CompositeModel.Baked.builder(itemContext, particleSprite, new ContainedFluidOverrideHandler(overrides, baker, itemContext, this), context.getTransforms());

		var normalRenderTypes = DynamicFluidContainerModel.getLayerRenderTypes(false);

		if (baseSprite != null) {
			//lower bucket by a pixel to prevent the need of a 2nd texture
			Vector3f lowered = this.isLower ? new Vector3f(0.0F, -1.0F/16.0F, 0.0F) : null;
			ModelState baseState = new SimpleModelState(modelState.getRotation().compose(new Transformation(lowered, null, null, null)));
			var unbaked = UnbakedGeometryHelper.createUnbakedItemElements(0, baseSprite);
			var quads = UnbakedGeometryHelper.bakeElements(unbaked, $ -> baseSprite, baseState);
			modelBuilder.addQuads(normalRenderTypes, quads);
		}

		if (otherContentSprite != null) {
			var transformedState = new SimpleModelState(modelState.getRotation().compose(DEPTH_OFFSET_TRANSFORM), modelState.isUvLocked());
			var unbaked = UnbakedGeometryHelper.createUnbakedItemElements(2, otherContentSprite);
			TextureAtlasSprite finalOtherContentSprite = otherContentSprite;
			var quads = UnbakedGeometryHelper.bakeElements(unbaked, $ -> finalOtherContentSprite, transformedState);
			modelBuilder.addQuads(normalRenderTypes, quads);
		} else if (fluidMaskLocation != null && fluidSprite != null) {
			TextureAtlasSprite templateSprite = spriteGetter.apply(fluidMaskLocation);
			if (templateSprite != null) {
				// build liquid layer (inside)
				var transformedState = new SimpleModelState(modelState.getRotation().compose(DEPTH_OFFSET_TRANSFORM), modelState.isUvLocked());
				var unbaked = UnbakedGeometryHelper.createUnbakedItemMaskElements(1, templateSprite); // Use template as mask
				var quads = UnbakedGeometryHelper.bakeElements(unbaked, $ -> fluidSprite, transformedState); // Bake with fluid texture

				var emissive = this.applyFluidLuminosity && this.fluid.getFluidType().getLightLevel() > 0;
				var renderTypes = DynamicFluidContainerModel.getLayerRenderTypes(emissive);
				if (emissive) QuadTransformers.settingEmissivity(this.fluid.getFluidType().getLightLevel()).processInPlace(quads);

				modelBuilder.addQuads(renderTypes, quads);
			}
		}

		modelBuilder.setParticle(particleSprite);

		return modelBuilder.build();
	}

	public static final class Loader implements IGeometryLoader<CloggrumBucketModel> {
		public static final CloggrumBucketModel.Loader INSTANCE = new CloggrumBucketModel.Loader();

		private Loader() {}

		@Override
		public CloggrumBucketModel read(JsonObject jsonObject, JsonDeserializationContext context) {
			if (!jsonObject.has("fluid"))
				throw new RuntimeException("Bucket model requires 'fluid' value.");

			ResourceLocation fluidName = ResourceLocation.parse(jsonObject.get("fluid").getAsString());

			Fluid fluid = BuiltInRegistries.FLUID.get(fluidName);

			boolean flip = GsonHelper.getAsBoolean(jsonObject, "flip_gas", false);
			boolean applyFluidLuminosity = GsonHelper.getAsBoolean(jsonObject, "apply_fluid_luminosity", true);

			return new CloggrumBucketModel(fluid, null, flip, applyFluidLuminosity, false);
		}
	}

	private static final class ContainedFluidOverrideHandler extends ItemOverrides {

		private final Map<ResourceLocation, BakedModel> cache = Maps.newHashMap(); // contains all the baked models since they'll never change
		private final ItemOverrides nested;
		private final ModelBaker baker;
		private final IGeometryBakingContext owner;
		private final CloggrumBucketModel parent;

		private ContainedFluidOverrideHandler(ItemOverrides nested, ModelBaker baker, IGeometryBakingContext owner, CloggrumBucketModel parent)
		{
			this.nested = nested;
			this.baker = baker;
			this.owner = owner;
			this.parent = parent;
		}

		@Nullable
		@Override
		public BakedModel resolve(BakedModel originalModel, ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int number)
		{
			BakedModel overridden = this.nested.resolve(originalModel, stack, level, entity, number);
			if (overridden != originalModel) return overridden;
			if (stack.getItem() instanceof UGBucketItem) {
				boolean containsEntityType = false;
				ResourceLocation content = null;
				if (stack.get(DataComponents.BUCKET_ENTITY_DATA) != null) {
					ResourceLocation id = ResourceLocation.parse(stack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag().getString("id"));
					content = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath());
					containsEntityType = true;
				} else {
					if (stack.get(UGDataComponents.STORED_BLOCK) != null) {
						content = BuiltInRegistries.BLOCK.getKey(stack.get(UGDataComponents.STORED_BLOCK).getBlock());
					}
				}
				SimpleFluidContent fluid = null;
				if (content == null) {
					fluid = stack.getOrDefault(UGDataComponents.STORED_FLUID, SimpleFluidContent.EMPTY);
					ResourceLocation location = BuiltInRegistries.FLUID.getKey(fluid.getFluid());
					content = (location != BuiltInRegistries.FLUID.getDefaultKey()) ? location : null;
				}
				BakedModel bakedModel = this.cache.get(content);
				if (bakedModel == null && content != null) {
					CloggrumBucketModel unbaked = (fluid == null) ? this.parent.withOtherContent(content, containsEntityType) : this.parent.withFluid(fluid.getFluid());
					bakedModel = unbaked.bake(this.owner, this.baker, Material::sprite, BlockModelRotation.X0_Y0, this);
					this.cache.put(content, bakedModel);
				}
				return bakedModel;
			}
			return originalModel;
		}
	}
}
