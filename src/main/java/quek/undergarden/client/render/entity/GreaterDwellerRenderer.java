package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GreaterDwellerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.GreaterDweller;

public class GreaterDwellerRenderer extends MobRenderer<GreaterDweller, GreaterDwellerModel<GreaterDweller>> {

	private static final Identifier GREATER_DWELLER = Undergarden.prefix("textures/entity/greater_dweller.png");
	private final static RenderType GREATER_DWELLER_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/greater_dweller_eyes.png"));

	public GreaterDwellerRenderer(EntityRendererProvider.Context context) {
		super(context, new GreaterDwellerModel<>(context.bakeLayer(UGModelLayers.GREATER_DWELLER)), 1.0F);
		this.addLayer(new BasicEyesLayer<>(this, GREATER_DWELLER_EYES));
	}

	@Override
	public Identifier getTextureLocation(GreaterDweller entity) {
		return GREATER_DWELLER;
	}
}