package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import quek.undergarden.Undergarden;
import quek.undergarden.client.UndergardenClient;

public class UthericInfectionLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

	private static final Identifier TEXTURE = Undergarden.prefix("textures/misc/utheric_infection_overlay.png");

	public UthericInfectionLayer(RenderLayerParent<S, M> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
		Float infectionLevel = state.getRenderData(UndergardenClient.UTHERIUM_INFECTION);
		if (infectionLevel != null && infectionLevel > 0) {
			submitNodeCollector.order(1).submitModel(this.getParentModel(), state, poseStack, RenderTypes.entityTranslucent(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, ARGB.color((int) (infectionLevel * 2) + 128, 255, 255, 255), null, state.outlineColor, null);
		}
	}
}