package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MogModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.MogEyesLayer;
import quek.undergarden.entity.animal.Mog;

public class MogRenderer extends MobRenderer<Mog, MogModel<Mog>> {

	private static final ResourceLocation MOG = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/mog.png");
	private static final ResourceLocation MOG_NAKED = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/mog_naked.png");

	public MogRenderer(EntityRendererProvider.Context context) {
		super(context, new MogModel<>(context.bakeLayer(UGModelLayers.MOG)), 0.5F);
		this.addLayer(new MogEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Mog entity) {
		return entity.hasMoss() ? MOG : MOG_NAKED;
	}
}