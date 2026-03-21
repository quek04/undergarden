package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MuncherModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.monster.cavern.Muncher;

public class MuncherRenderer extends MobRenderer<Muncher, LivingEntityRenderState, MuncherModel> {

	private static final Identifier MUNCHER = Undergarden.prefix("textures/entity/muncher.png");
	private static final RenderType MUNCHER_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/muncher_eyes.png"));

	public MuncherRenderer(EntityRendererProvider.Context context) {
		super(context, new MuncherModel(context.bakeLayer(UGModelLayers.MUNCHER)), 0.5F);
		this.addLayer(new BasicEyesLayer<>(this, MUNCHER_EYES));
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return MUNCHER;
	}
}