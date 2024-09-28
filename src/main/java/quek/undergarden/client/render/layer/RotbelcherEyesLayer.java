package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbelcherModel;
import quek.undergarden.entity.monster.rotspawn.Rotbelcher;

public class RotbelcherEyesLayer<T extends Rotbelcher, M extends RotbelcherModel<T>> extends EyesLayer<T, M> {

	private static final RenderType ROTBELCHER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/rotbelcher_eyes.png"));

	public RotbelcherEyesLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public RenderType renderType() {
		return ROTBELCHER_EYES;
	}
}
