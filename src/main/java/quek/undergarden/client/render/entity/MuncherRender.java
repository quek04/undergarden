package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MuncherModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.MuncherEyesLayer;
import quek.undergarden.entity.monster.cavern.Muncher;

public class MuncherRender extends MobRenderer<Muncher, MuncherModel<Muncher>> {

	public MuncherRender(EntityRendererProvider.Context context) {
		super(context, new MuncherModel<>(context.bakeLayer(UGModelLayers.MUNCHER)), 0.5F);
		this.addLayer(new MuncherEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Muncher entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/muncher.png");
	}
}