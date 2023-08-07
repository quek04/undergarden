package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.DwellerEyesLayer;
import quek.undergarden.entity.animal.dweller.Dweller;

public class DwellerRender extends MobRenderer<Dweller, DwellerModel<Dweller>> {

	public DwellerRender(EntityRendererProvider.Context context) {
		super(context, new DwellerModel<>(context.bakeLayer(UGModelLayers.DWELLER)), 0.7F);
		this.addLayer(new DwellerEyesLayer<>(this));
		this.addLayer(new SaddleLayer<>(this, new DwellerModel<>(context.bakeLayer(UGModelLayers.DWELLER_SADDLE)), new ResourceLocation(Undergarden.MODID, "textures/entity/dweller_saddle.png")));
	}

	@Override
	public ResourceLocation getTextureLocation(Dweller entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/dweller.png");
	}
}