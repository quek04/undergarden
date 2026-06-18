package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import quek.undergarden.client.UGRenderTypes;
import quek.undergarden.client.UndergardenClient;
import quek.undergarden.client.event.UGOverlayEvents;
import quek.undergarden.event.UthericInfectionEvents;

public class UthericInfectionLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

	public UthericInfectionLayer(RenderLayerParent<S, M> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
		Float infectionLevel = state.getRenderData(UndergardenClient.UTHERIUM_INFECTION);
		if (infectionLevel != null && infectionLevel > 0) {
			float alpha = infectionLevel / UthericInfectionEvents.MAX_INFECTION;
			submitNodeCollector.order(1).submitModel(this.getParentModel(), state, poseStack, UGRenderTypes.entityDecalTranslucent(UGOverlayEvents.UTHERIC_INFECTION_OVERLAY), state.lightCoords, OverlayTexture.NO_OVERLAY,  ARGB.colorFromFloat(Mth.clamp(alpha, 0.0F, 0.75F), alpha, alpha, alpha), null, state.outlineColor, null);
		}
	}
}