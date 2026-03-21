package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.NargoyleModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.state.entity.NargoyleRenderState;
import quek.undergarden.entity.monster.cavern.Nargoyle;

public class NargoyleRenderer extends MobRenderer<Nargoyle, NargoyleRenderState, NargoyleModel> {

	private static final Identifier NARGOYLE = Undergarden.prefix("textures/entity/nargoyle.png");

	public NargoyleRenderer(EntityRendererProvider.Context context) {
		super(context, new NargoyleModel(context.bakeLayer(UGModelLayers.NARGOYLE)), 0.8F);
	}

	@Override
	public NargoyleRenderState createRenderState() {
		return new NargoyleRenderState();
	}

	@Override
	public void extractRenderState(Nargoyle entity, NargoyleRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.aggressive = entity.isAggressive();
	}

	@Override
	public Identifier getTextureLocation(NargoyleRenderState state) {
		return NARGOYLE;
	}
}