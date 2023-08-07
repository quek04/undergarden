package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.BruteModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BruteEyesLayer;
import quek.undergarden.entity.animal.Brute;

public class BruteRender extends MobRenderer<Brute, BruteModel<Brute>> {

	public BruteRender(EntityRendererProvider.Context context) {
		super(context, new BruteModel<>(context.bakeLayer(UGModelLayers.BRUTE)), 0.7F);
		this.addLayer(new BruteEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Brute entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/brute.png");
	}
}
