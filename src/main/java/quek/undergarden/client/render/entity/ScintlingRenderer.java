package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Scintling;

public class ScintlingRenderer extends MobRenderer<Scintling, ScintlingModel<Scintling>> {

	private static final ResourceLocation SCINTLING = Undergarden.prefix("textures/entity/scintling.png");
	private static final RenderType SCINTLING_GLOW = RenderType.eyes(Undergarden.prefix("textures/entity/scintling_glow.png"));

	public ScintlingRenderer(EntityRendererProvider.Context context) {
		super(context, new ScintlingModel<>(context.bakeLayer(UGModelLayers.SCINTLING)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, SCINTLING_GLOW));
	}

	@Override
	public ResourceLocation getTextureLocation(Scintling entity) {
		return SCINTLING;
	}

}