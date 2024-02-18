package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SmogMogModel;
import quek.undergarden.entity.animal.SmogMog;

public class SmogMogEyesLayer<E extends SmogMog, M extends SmogMogModel<E>> extends EyesLayer<E, M> {

	public SmogMogEyesLayer(RenderLayerParent<E, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/smog_mog_eyes.png"));
	}
}
