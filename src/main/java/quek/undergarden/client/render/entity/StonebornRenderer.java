package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.StonebornRenderState;
import quek.undergarden.entity.monster.stoneborn.Stoneborn;

public class StonebornRenderer extends MobRenderer<Stoneborn, StonebornRenderState, StonebornModel> {

	private static final Identifier STONEBORN = Undergarden.prefix("textures/entity/stoneborn.png");
	private static final RenderType STONEBORN_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/stoneborn_eyes.png"));

	public StonebornRenderer(EntityRendererProvider.Context context) {
		super(context, new StonebornModel(context.bakeLayer(UGModelLayers.STONEBORN)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, STONEBORN_EYES));
	}

	@Override
	public StonebornRenderState createRenderState() {
		return new StonebornRenderState();
	}

	@Override
	public void extractRenderState(Stoneborn entity, StonebornRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isShaking = !entity.inUndergarden() && !entity.isNoAi();
	}

	@Override
	public Identifier getTextureLocation(StonebornRenderState state) {
		return STONEBORN;
	}

	@Override
	protected boolean isShaking(StonebornRenderState state) {
		return super.isShaking(state) || state.isShaking;
	}
}