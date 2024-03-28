package quek.undergarden.client.render.layer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.Denizen;

public class DenizenEyesLayer<T extends Denizen, M extends EntityModel<T>> extends EyesLayer<T, M> {

	public DenizenEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/denizen_eyes.png"));
	}
}