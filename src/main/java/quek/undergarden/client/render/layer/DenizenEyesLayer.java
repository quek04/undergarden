package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import quek.undergarden.Undergarden;
import quek.undergarden.client.state.entity.DenizenRenderState;

public class DenizenEyesLayer<S extends DenizenRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
	private static final RenderType DENIZEN_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/denizen_eyes.png"));
	private static final RenderType DENIZEN2_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/denizen2_eyes.png"));

	public DenizenEyesLayer(RenderLayerParent<S, M> parent) {
		super(parent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
		RenderType type = switch (state.variant) {
			case SHORT -> DENIZEN_EYES;
			case TALL -> DENIZEN2_EYES;
		};
		submitNodeCollector.order(1).submitModel(this.getParentModel(), state, poseStack, type, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
	}
}