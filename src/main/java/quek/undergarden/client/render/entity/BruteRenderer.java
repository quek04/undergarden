package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.BruteModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Brute;

public class BruteRenderer extends MobRenderer<Brute, LivingEntityRenderState, BruteModel> {

	private static final Identifier BRUTE = Undergarden.prefix("textures/entity/brute.png");
	private static final RenderType BRUTE_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/brute_eyes.png"));

	public BruteRenderer(EntityRendererProvider.Context context) {
		super(context, new BruteModel(context.bakeLayer(UGModelLayers.BRUTE)), 0.7F);
		this.addLayer(new BasicEyesLayer<>(this, BRUTE_EYES));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return BRUTE;
	}
}
