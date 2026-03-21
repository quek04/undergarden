package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;

import java.util.function.Predicate;

public class BasicEyesLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends EyesLayer<S, M> {

	private final RenderType type;
	private final Predicate<S> visible;

	public BasicEyesLayer(RenderLayerParent<S, M> renderer, RenderType type) {
		this(renderer, type, state -> true);
	}

	public BasicEyesLayer(RenderLayerParent<S, M> renderer, RenderType type, Predicate<S> visible) {
		super(renderer);
		this.type = type;
		this.visible = visible;
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
		if (this.visible.test(state)) {
			super.submit(poseStack, submitNodeCollector, lightCoords, state, yRot, xRot);
		}
	}

	@Override
	public RenderType renderType() {
		return this.type;
	}
}
