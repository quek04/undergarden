package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotlingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.RotlingRenderState;
import quek.undergarden.entity.monster.rotspawn.Rotling;

public class RotlingRenderer extends MobRenderer<Rotling, RotlingRenderState, RotlingModel> {

	private static final Identifier ROTLING = Undergarden.prefix("textures/entity/rotling.png");
	private static final RenderType ROTLING_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/rotling_eyes.png"));

	public RotlingRenderer(EntityRendererProvider.Context context) {
		super(context, new RotlingModel(context.bakeLayer(UGModelLayers.ROTLING)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, ROTLING_EYES));
	}

	@Override
	public RotlingRenderState createRenderState() {
		return new RotlingRenderState();
	}

	@Override
	public void extractRenderState(Rotling entity, RotlingRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.aggressive = entity.isAggressive();
	}

	@Override
	public Identifier getTextureLocation(RotlingRenderState state) {
		return ROTLING;
	}
}