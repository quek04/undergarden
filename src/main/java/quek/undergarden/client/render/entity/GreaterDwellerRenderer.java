package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GreaterDwellerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.GreaterDwellerEyesLayer;
import quek.undergarden.entity.animal.GreaterDweller;

public class GreaterDwellerRenderer extends MobRenderer<GreaterDweller, GreaterDwellerModel<GreaterDweller>> {

	private static final ResourceLocation GREATER_DWELLER = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/greater_dweller.png");

	public GreaterDwellerRenderer(EntityRendererProvider.Context context) {
		super(context, new GreaterDwellerModel<>(context.bakeLayer(UGModelLayers.GREATER_DWELLER)), 1.0F);
		this.addLayer(new GreaterDwellerEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(GreaterDweller entity) {
		return GREATER_DWELLER;
	}
}