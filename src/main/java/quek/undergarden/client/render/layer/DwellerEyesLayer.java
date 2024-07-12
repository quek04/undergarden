package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.entity.animal.dweller.Dweller;

public class DwellerEyesLayer<T extends Dweller, M extends DwellerModel<T>> extends EyesLayer<T, M> {

	private final static RenderType DWELLER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/dweller_eyes.png"));

	public DwellerEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return DWELLER_EYES;
	}
}
