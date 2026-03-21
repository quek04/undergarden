package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SmogMogModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.MogRenderState;
import quek.undergarden.entity.animal.SmogMog;

public class SmogMogRenderer extends MobRenderer<SmogMog, MogRenderState, SmogMogModel> {

	private static final Identifier SMOG_MOG = Undergarden.prefix("textures/entity/smog_mog.png");
	private static final Identifier SMOG_MOG_NAKED = Undergarden.prefix("textures/entity/smog_mog_naked.png");
	private static final RenderType SMOG_MOG_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/smog_mog_eyes.png"));

	public SmogMogRenderer(EntityRendererProvider.Context context) {
		super(context, new SmogMogModel(context.bakeLayer(UGModelLayers.SMOG_MOG)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, SMOG_MOG_EYES));
	}

	@Override
	public MogRenderState createRenderState() {
		return new MogRenderState();
	}

	@Override
	public void extractRenderState(SmogMog entity, MogRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.hasMoss = entity.hasMoss();
	}

	@Override
	public Identifier getTextureLocation(MogRenderState state) {
		return state.hasMoss ? SMOG_MOG : SMOG_MOG_NAKED;
	}
}
