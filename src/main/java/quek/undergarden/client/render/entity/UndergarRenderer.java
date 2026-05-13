package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.model.UndergarModel;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Undergar;

public class UndergarRenderer extends MobRenderer<Undergar, LivingEntityRenderState, UndergarModel> {

	public static final Identifier UNDERGAR = Undergarden.prefix("textures/entity/undergar.png");
	private static final RenderType UNDERGAR_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/undergar_eyes.png"));

	public UndergarRenderer(EntityRendererProvider.Context context) {
		super(context, new UndergarModel(context.bakeLayer(UGModelLayers.UNDERGAR)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, UNDERGAR_EYES));
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return UNDERGAR;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
