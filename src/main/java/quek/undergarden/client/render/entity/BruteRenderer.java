package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.BruteModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Brute;

public class BruteRenderer extends MobWithBabyRenderer<Brute, LivingEntityRenderState, BruteModel> {

	private static final Identifier BRUTE = Undergarden.prefix("textures/entity/brute.png");
	private static final RenderType BRUTE_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/brute_eyes.png"));
	private static final Identifier BRUTE_BABY = Undergarden.prefix("textures/entity/brute_baby.png");
	private static final RenderType BRUTE_BABY_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/brute_baby_eyes.png"));

	public BruteRenderer(EntityRendererProvider.Context context) {
		super(context, new BruteModel(context.bakeLayer(UGModelLayers.BRUTE)), new BruteModel(context.bakeLayer(UGModelLayers.BRUTE_BABY)), 0.7F);
		this.addLayer(new BasicEyesLayer<>(this, BRUTE_EYES, BRUTE_BABY_EYES));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}


	@Override
	protected Identifier getAdultTexture(LivingEntityRenderState state) {
		return BRUTE;
	}

	@Override
	protected Identifier getBabyTexture(LivingEntityRenderState state) {
		return BRUTE_BABY;
	}
}
