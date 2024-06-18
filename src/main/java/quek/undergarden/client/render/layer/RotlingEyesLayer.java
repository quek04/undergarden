package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotlingModel;
import quek.undergarden.entity.rotspawn.Rotling;

public class RotlingEyesLayer<T extends Rotling, M extends RotlingModel<T>> extends EyesLayer<T, M> {

	private static final RenderType ROTLING_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/rotling_eyes.png"));

	public RotlingEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return ROTLING_EYES;
	}

}
