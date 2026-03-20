package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.dweller.Dweller;

public class DwellerRenderer extends MobRenderer<Dweller, DwellerModel<Dweller>> {

	private static final Identifier DWELLER = Undergarden.prefix("textures/entity/dweller.png");
	private static final Identifier DWELLER_SADDLE = Undergarden.prefix("textures/entity/dweller_saddle.png");
	private final static RenderType DWELLER_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/dweller_eyes.png"));

	public DwellerRenderer(EntityRendererProvider.Context context) {
		super(context, new DwellerModel<>(context.bakeLayer(UGModelLayers.DWELLER)), 0.7F);
		this.addLayer(new BasicEyesLayer<>(this, DWELLER_EYES));
		this.addLayer(new SaddleLayer<>(this, new DwellerModel<>(context.bakeLayer(UGModelLayers.DWELLER_SADDLE)), DWELLER_SADDLE));
	}

	@Override
	public Identifier getTextureLocation(Dweller entity) {
		return DWELLER;
	}
}