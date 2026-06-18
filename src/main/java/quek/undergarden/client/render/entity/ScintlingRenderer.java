package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Scintling;

public class ScintlingRenderer extends MobWithBabyRenderer<Scintling, LivingEntityRenderState, ScintlingModel> {

	private static final Identifier SCINTLING = Undergarden.prefix("textures/entity/scintling.png");
	private static final Identifier BABY_SCINTLING = Undergarden.prefix("textures/entity/scintling_baby.png");
	private static final RenderType SCINTLING_GLOW = RenderTypes.eyes(Undergarden.prefix("textures/entity/scintling_glow.png"));
	private static final RenderType BABY_SCINTLING_GLOW = RenderTypes.eyes(Undergarden.prefix("textures/entity/scintling_baby_glow.png"));

	public ScintlingRenderer(EntityRendererProvider.Context context) {
		super(context, new ScintlingModel(context.bakeLayer(UGModelLayers.SCINTLING)), new ScintlingModel(context.bakeLayer(UGModelLayers.SCINTLING_BABY)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, SCINTLING_GLOW, BABY_SCINTLING_GLOW));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	protected Identifier getAdultTexture(LivingEntityRenderState state) {
		return SCINTLING;
	}

	@Override
	protected Identifier getBabyTexture(LivingEntityRenderState state) {
		return BABY_SCINTLING;
	}
}