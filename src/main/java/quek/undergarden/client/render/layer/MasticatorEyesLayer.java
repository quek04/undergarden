package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MasticatorModel;
import quek.undergarden.entity.boss.Masticator;

public class MasticatorEyesLayer<T extends Masticator, M extends MasticatorModel<T>> extends EyesLayer<T, M> {

	public MasticatorEyesLayer(RenderLayerParent<T, M> rendererIn) {
		super(rendererIn);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/masticator_eyes.png"));
	}
}
