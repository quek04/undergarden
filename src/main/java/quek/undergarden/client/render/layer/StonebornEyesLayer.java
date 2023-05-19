package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.entity.stoneborn.Stoneborn;

public class StonebornEyesLayer<T extends Stoneborn, M extends StonebornModel<T>> extends EyesLayer<T, M> {

	public StonebornEyesLayer(RenderLayerParent<T, M> rendererIn) {
		super(rendererIn);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/stoneborn_eyes.png"));
	}

}
