package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotlingModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.monster.rotspawn.Rotling;

public class RotlingRenderer extends MobRenderer<Rotling, RotlingModel<Rotling>> {

	private static final Identifier ROTLING = Undergarden.prefix("textures/entity/rotling.png");
	private static final RenderType ROTLING_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/rotling_eyes.png"));

	public RotlingRenderer(EntityRendererProvider.Context context) {
		super(context, new RotlingModel<>(context.bakeLayer(UGModelLayers.ROTLING)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, ROTLING_EYES));
	}

	@Override
	public Identifier getTextureLocation(Rotling entity) {
		return ROTLING;
	}
}