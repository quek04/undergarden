package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenModel;
import quek.undergarden.entity.Forgotten;

public class ForgottenEyesLayer<T extends Forgotten, M extends ForgottenModel<T>> extends EyesLayer<T, M> {

	private static final RenderType FORGOTTEN_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/forgotten_eyes.png"));

	public ForgottenEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return FORGOTTEN_EYES;
	}
}