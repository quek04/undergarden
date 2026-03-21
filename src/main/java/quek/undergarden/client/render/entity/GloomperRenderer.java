package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GloomperModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.GloomperRenderState;
import quek.undergarden.entity.animal.Gloomper;

public class GloomperRenderer extends MobRenderer<Gloomper, GloomperRenderState, GloomperModel> {

	public static final Identifier GLOOMPER = Undergarden.prefix("textures/entity/gloomper.png");
	private static final RenderType GLOOMPER_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/gloomper_eyes.png"));

	public GloomperRenderer(EntityRendererProvider.Context context) {
		super(context, new GloomperModel(context.bakeLayer(UGModelLayers.GLOOMPER)), 1.0F);
		this.addLayer(new BasicEyesLayer<>(this, GLOOMPER_EYES));
	}

	@Override
	public GloomperRenderState createRenderState() {
		return new GloomperRenderState();
	}

	@Override
	public void extractRenderState(Gloomper entity, GloomperRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.jumpCompletion = entity.getJumpCompletion(partialTicks);
	}

	@Override
	public Identifier getTextureLocation(GloomperRenderState entity) {
		return GLOOMPER;
	}
}