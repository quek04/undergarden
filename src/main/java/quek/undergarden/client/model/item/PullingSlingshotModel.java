package quek.undergarden.client.model.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.dispatch.BlockModelRotation;
import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.renderer.item.*;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.cuboid.ItemModelGenerator;
import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4fc;
import org.jspecify.annotations.Nullable;
import quek.undergarden.registry.UGDataComponents;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class PullingSlingshotModel implements ItemModel {

	private static final ModelDebugName DEBUG_NAME = () -> "PullingSlingshotModel";

	private final Unbaked unbakedModel;
	private final BakingContext bakingContext;
	private final Matrix4fc transformation;
	private final ItemTransforms itemTransforms;
	private final Map<Material, ItemModel> cache = new IdentityHashMap<>(); // contains all the baked models since they'll never change

	private PullingSlingshotModel(Unbaked unbakedModel, BakingContext bakingContext, Matrix4fc transformation) {
		this.unbakedModel = unbakedModel;
		this.bakingContext = bakingContext;
		this.transformation = transformation;
		// Source ItemTransforms from the base item model
		var baseItemModel = bakingContext.blockModelBaker().getModel(Identifier.withDefaultNamespace("item/generated"));
		this.itemTransforms = baseItemModel.getTopTransforms();
	}

	private ItemModel bake(Material texture) {
		ModelBaker baker = this.bakingContext.blockModelBaker();
		MaterialBaker materials = baker.materials();
		Material.Baked defaultTex = materials.get(texture, DEBUG_NAME);
		ModelRenderProperties renderProperties = new ModelRenderProperties(false, defaultTex, this.itemTransforms);
		ModelState state = BlockModelRotation.IDENTITY;

		QuadCollection quads = baker.compute(new ItemModelGenerator.ItemLayerKey(defaultTex, state, 0));
		return new CuboidItemModelWrapper(List.of(), quads, renderProperties, this.transformation);
	}

	@Override
	public void update(ItemStackRenderState state, ItemStack stack, ItemModelResolver resolver, ItemDisplayContext context, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
		Material chargeTex = this.unbakedModel.defaultTexture();
		if (owner instanceof LivingEntity entity) {
			ItemStack ammo = entity.getProjectile(stack);
			if (ammo.has(UGDataComponents.SLINGSHOT_AMMO)) {
				List<Identifier> chargeTextures = ammo.get(UGDataComponents.SLINGSHOT_AMMO).chargeTextures();
				if (!chargeTextures.isEmpty() && chargeTextures.size() >= this.unbakedModel.index()) {
					chargeTex = new Material(chargeTextures.get(this.unbakedModel.index()));
				}
			}
		}

		ItemModel bakedModel = this.cache.computeIfAbsent(chargeTex, this::bake);
		bakedModel.update(state, stack, resolver, context, level, owner, seed);
	}

	public record Unbaked(Material defaultTexture, int index) implements ItemModel.Unbaked {
		public static final MapCodec<PullingSlingshotModel.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Material.CODEC.fieldOf("default_texture").forGetter(PullingSlingshotModel.Unbaked::defaultTexture),
			Codec.INT.fieldOf("index").forGetter(PullingSlingshotModel.Unbaked::index)
		).apply(instance, PullingSlingshotModel.Unbaked::new));

		@Override
		public MapCodec<? extends ItemModel.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public ItemModel bake(BakingContext context, Matrix4fc transformation) {
			return new PullingSlingshotModel(this, context, transformation);
		}

		@Override
		public void resolveDependencies(Resolver resolver) {
			//No dependencies
		}
	}
}
