package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SmogMogModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.SmogMog;

public class SmogMogRenderer extends MobRenderer<SmogMog, SmogMogModel<SmogMog>> {

	private static final ResourceLocation SMOG_MOG = Undergarden.prefix("textures/entity/smog_mog.png");
	private static final ResourceLocation SMOG_MOG_NAKED = Undergarden.prefix("textures/entity/smog_mog_naked.png");
	private static final RenderType SMOG_MOG_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/smog_mog_eyes.png"));

	public SmogMogRenderer(EntityRendererProvider.Context context) {
		super(context, new SmogMogModel<>(context.bakeLayer(UGModelLayers.SMOG_MOG)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, SMOG_MOG_EYES));
	}

	@Override
	public ResourceLocation getTextureLocation(SmogMog entity) {
		return entity.hasMoss() ? SMOG_MOG : SMOG_MOG_NAKED;
	}
}
