package quek.undergarden.datagen.helpers.builder;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import quek.undergarden.Undergarden;

public class CloggrumBucketModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {

	public static <T extends ModelBuilder<T>> CloggrumBucketModelBuilder<T> begin(T parent, ExistingFileHelper existingFileHelper) {
		return new CloggrumBucketModelBuilder<>(parent, existingFileHelper);
	}

	private ResourceLocation fluid;
	private ResourceLocation content;
	private Boolean flipGas;
	private Boolean applyTint;
	private Boolean applyFluidLuminosity;

	private CloggrumBucketModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
		super(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "cloggrum_bucket"), parent, existingFileHelper, false);
	}

	public CloggrumBucketModelBuilder<T> fluid(Fluid fluid) {
		Preconditions.checkNotNull(fluid, "fluid must not be null");
		this.fluid = BuiltInRegistries.FLUID.getKey(fluid);
		return this;
	}

	public CloggrumBucketModelBuilder<T> content(ResourceLocation content) {
		this.content = content;
		return this;
	}

	public CloggrumBucketModelBuilder<T> flipGas(boolean flip) {
		this.flipGas = flip;
		return this;
	}

	public CloggrumBucketModelBuilder<T> applyTint(boolean tint) {
		this.applyTint = tint;
		return this;
	}

	public CloggrumBucketModelBuilder<T> applyFluidLuminosity(boolean applyFluidLuminosity) {
		this.applyFluidLuminosity = applyFluidLuminosity;
		return this;
	}

	@Override
	public JsonObject toJson(JsonObject json) {
		json = super.toJson(json);

		Preconditions.checkNotNull(this.fluid, "fluid must not be null");

		json.addProperty("fluid", this.fluid.toString());

		if (this.content != null) {
			json.addProperty("content", this.content.toString());
		}

		if (this.flipGas != null)
			json.addProperty("flip_gas", this.flipGas);

		if (this.applyTint != null)
			json.addProperty("apply_tint", this.applyTint);

		if (this.applyFluidLuminosity != null)
			json.addProperty("apply_fluid_luminosity", this.applyFluidLuminosity);

		return json;
	}
}
