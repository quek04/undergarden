package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.entity.animal.Scintling;

public class ScintlingGlowLayer<T extends Scintling, M extends ScintlingModel<T>> extends EyesLayer<T, M> {

	private static final RenderType SCINTLING_GLOW = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/scintling_glow.png"));

	public ScintlingGlowLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return SCINTLING_GLOW;
	}

}
