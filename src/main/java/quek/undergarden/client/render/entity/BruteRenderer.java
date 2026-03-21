package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.BruteModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Brute;

public class BruteRenderer extends MobRenderer<Brute, BruteModel<Brute>> {

	private static final ResourceLocation BRUTE = Undergarden.prefix("textures/entity/brute.png");
	private static final RenderType BRUTE_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/brute_eyes.png"));

	public BruteRenderer(EntityRendererProvider.Context context) {
		super(context, new BruteModel<>(context.bakeLayer(UGModelLayers.BRUTE)), 0.7F);
		this.addLayer(new BasicEyesLayer<>(this, BRUTE_EYES));
	}

	@Override
	public ResourceLocation getTextureLocation(Brute entity) {
		return BRUTE;
	}
}
