package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GreaterDwellerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.GreaterDwellerRenderState;
import quek.undergarden.entity.animal.GreaterDweller;

public class GreaterDwellerRenderer extends MobRenderer<GreaterDweller, GreaterDwellerRenderState, GreaterDwellerModel> {

	private static final Identifier GREATER_DWELLER = Undergarden.prefix("textures/entity/greater_dweller.png");
	private final static RenderType GREATER_DWELLER_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/greater_dweller_eyes.png"));

	public GreaterDwellerRenderer(EntityRendererProvider.Context context) {
		super(context, new GreaterDwellerModel(context.bakeLayer(UGModelLayers.GREATER_DWELLER)), 1.0F);
		this.addLayer(new BasicEyesLayer<>(this, GREATER_DWELLER_EYES));
	}

	@Override
	public GreaterDwellerRenderState createRenderState() {
		return new GreaterDwellerRenderState();
	}

	@Override
	public void extractRenderState(GreaterDweller entity, GreaterDwellerRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.attackTimer = entity.getAttackTimer() > 0.0F ? entity.getAttackTimer() - partialTicks : 0.0F;
	}

	@Override
	public Identifier getTextureLocation(GreaterDwellerRenderState state) {
		return GREATER_DWELLER;
	}
}