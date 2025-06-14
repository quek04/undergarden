package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.GreaterDwellerModel;
import quek.undergarden.entity.animal.GreaterDweller;

public class GreaterDwellerEyesLayer<T extends GreaterDweller, M extends GreaterDwellerModel<T>> extends EyesLayer<T, M> {

	private final static RenderType GREATER_DWELLER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/greater_dweller_eyes.png"));

	public GreaterDwellerEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return GREATER_DWELLER_EYES;
	}
}
