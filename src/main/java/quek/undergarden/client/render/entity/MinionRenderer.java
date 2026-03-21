package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MinionModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.Minion;

public class MinionRenderer extends MobRenderer<Minion, LivingEntityRenderState, MinionModel> {

	private static final Identifier MINION = Undergarden.prefix("textures/entity/minion.png");
	private static final RenderType MINION_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/minion_eye.png"));

	public MinionRenderer(EntityRendererProvider.Context context) {
		super(context, new MinionModel(context.bakeLayer(UGModelLayers.MINION)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, MINION_EYES));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return MINION;
	}
}