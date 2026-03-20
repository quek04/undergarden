package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MogModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.animal.Mog;

public class MogRenderer extends MobRenderer<Mog, MogModel<Mog>> {

	private static final Identifier MOG = Undergarden.prefix("textures/entity/mog.png");
	private static final Identifier MOG_NAKED = Undergarden.prefix("textures/entity/mog_naked.png");
	private static final RenderType MOG_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/mog_eyes.png"));

	public MogRenderer(EntityRendererProvider.Context context) {
		super(context, new MogModel<>(context.bakeLayer(UGModelLayers.MOG)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, MOG_EYES));
	}

	@Override
	public Identifier getTextureLocation(Mog entity) {
		return entity.hasMoss() ? MOG : MOG_NAKED;
	}
}