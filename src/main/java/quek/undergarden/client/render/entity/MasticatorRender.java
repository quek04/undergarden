package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MasticatorModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.MasticatorEyesLayer;
import quek.undergarden.entity.boss.Masticator;

public class MasticatorRender extends MobRenderer<Masticator, MasticatorModel<Masticator>> {

	public MasticatorRender(EntityRendererProvider.Context context) {
		super(context, new MasticatorModel<>(context.bakeLayer(UGModelLayers.MASTICATOR)), 2.0F);
		this.addLayer(new MasticatorEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Masticator entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/masticator.png");
	}
}