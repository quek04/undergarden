package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MinionModel;
import quek.undergarden.entity.Minion;

public class MinionEyesLayer<T extends Minion, M extends MinionModel<T>> extends EyesLayer<T, M> {

	private static final RenderType MINION_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/minion_eye.png"));

	public MinionEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return MINION_EYES;
	}
}
