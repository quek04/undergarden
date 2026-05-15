package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import quek.undergarden.Undergarden;
import quek.undergarden.client.UGRenderTypes;
import quek.undergarden.client.model.UndergarModel;
import quek.undergarden.client.state.entity.UndergarRenderState;
import quek.undergarden.compat.IrisCompat;

//special case because shader mods suck ass
public class UndergarEyesLayer extends RenderLayer<UndergarRenderState, UndergarModel> {

	private static final RenderType UNDERGAR_EYES = UGRenderTypes.eyesNoCull(Undergarden.prefix("textures/entity/undergar_eyes.png"));
	private static final RenderType UNDERGAR_EYES_SHADERS = RenderTypes.eyes(Undergarden.prefix("textures/entity/undergar_eyes.png"));

	public UndergarEyesLayer(RenderLayerParent<UndergarRenderState, UndergarModel> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int light, UndergarRenderState state, float yRot, float xRot) {
		collector.order(1).submitModel(this.getParentModel(), state, stack, IrisCompat.areShadersLoaded() ? UNDERGAR_EYES_SHADERS : UNDERGAR_EYES, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
	}
}
