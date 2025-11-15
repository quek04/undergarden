package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MinionModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.Minion;

public class MinionRenderer extends MobRenderer<Minion, MinionModel<Minion>> {

	private static final ResourceLocation MINION = Undergarden.prefix("textures/entity/minion.png");
	private static final RenderType MINION_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/minion_eye.png"));

	public MinionRenderer(EntityRendererProvider.Context context) {
		super(context, new MinionModel<>(context.bakeLayer(UGModelLayers.MINION)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, MINION_EYES));
	}

	@Override
	public ResourceLocation getTextureLocation(Minion entity) {
		return MINION;
	}
}