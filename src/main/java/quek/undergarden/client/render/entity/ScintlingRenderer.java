package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.ScintlingGlowLayer;
import quek.undergarden.entity.animal.Scintling;

public class ScintlingRenderer extends MobRenderer<Scintling, ScintlingModel<Scintling>> {

	private static final ResourceLocation SCINTLING = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/scintling.png");

	public ScintlingRenderer(EntityRendererProvider.Context context) {
		super(context, new ScintlingModel<>(context.bakeLayer(UGModelLayers.SCINTLING)), 0.5F);
		this.addLayer(new ScintlingGlowLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Scintling entity) {
		return SCINTLING;
	}

}