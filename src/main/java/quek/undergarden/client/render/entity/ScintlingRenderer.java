package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Scintling;

public class ScintlingRenderer extends MobRenderer<Scintling, LivingEntityRenderState, ScintlingModel> {

	private static final Identifier SCINTLING = Undergarden.prefix("textures/entity/scintling.png");
	private static final RenderType SCINTLING_GLOW = RenderTypes.eyes(Undergarden.prefix("textures/entity/scintling_glow.png"));

	public ScintlingRenderer(EntityRendererProvider.Context context) {
		super(context, new ScintlingModel(context.bakeLayer(UGModelLayers.SCINTLING)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, SCINTLING_GLOW));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return SCINTLING;
	}

}