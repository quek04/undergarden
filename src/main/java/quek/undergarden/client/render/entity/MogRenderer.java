package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MogModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.MogRenderState;
import quek.undergarden.entity.animal.Mog;

public class MogRenderer extends MobWithBabyRenderer<Mog, MogRenderState, MogModel> {

	private static final Identifier MOG = Undergarden.prefix("textures/entity/mog.png");
	private static final Identifier BABY_MOG = Undergarden.prefix("textures/entity/mog_baby.png");
	private static final Identifier MOG_NAKED = Undergarden.prefix("textures/entity/mog_naked.png");
	private static final RenderType MOG_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/mog_eyes.png"));
	private static final RenderType BABY_MOG_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/mog_baby_eyes.png"));

	public MogRenderer(EntityRendererProvider.Context context) {
		super(context, new MogModel(context.bakeLayer(UGModelLayers.MOG)), new MogModel(context.bakeLayer(UGModelLayers.MOG_BABY)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, MOG_EYES, BABY_MOG_EYES));
	}

	@Override
	public MogRenderState createRenderState() {
		return new MogRenderState();
	}

	@Override
	public void extractRenderState(Mog entity, MogRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.hasMoss = entity.hasMoss();
	}

	@Override
	public Identifier getAdultTexture(MogRenderState state) {
		return state.hasMoss ? MOG : MOG_NAKED;
	}

	@Override
	public Identifier getBabyTexture(MogRenderState state) {
		return BABY_MOG;
	}
}