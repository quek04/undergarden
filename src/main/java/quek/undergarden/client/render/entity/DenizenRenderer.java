package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DenizenModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.DenizenEyesLayer;
import quek.undergarden.entity.monster.Denizen;

public class DenizenRenderer extends HumanoidMobRenderer<Denizen, DenizenModel<Denizen>> {

	public DenizenRenderer(EntityRendererProvider.Context context) {
		super(context, new DenizenModel<>(context.bakeLayer(UGModelLayers.DENIZEN)), 0.5F);
		this.addLayer(new DenizenEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Denizen entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/denizen.png");
	}
}