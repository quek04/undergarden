package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MinionModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.MinionEyesLayer;
import quek.undergarden.entity.Minion;

public class MinionRender extends MobRenderer<Minion, MinionModel<Minion>> {

	public MinionRender(EntityRendererProvider.Context renderContext) {
		super(renderContext, new MinionModel<>(renderContext.bakeLayer(UGModelLayers.MINION)), 0.5F);
		this.addLayer(new MinionEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Minion entity) {
		return new ResourceLocation(Undergarden.MODID, "textures/entity/minion.png");
	}
}