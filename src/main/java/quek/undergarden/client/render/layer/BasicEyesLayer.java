package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;

import java.util.function.Predicate;

public class BasicEyesLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

	private final RenderType adultType;
	private final RenderType babyType;
	private final Predicate<S> visible;

	public BasicEyesLayer(RenderLayerParent<S, M> renderer, RenderType type) {
		this(renderer, type, type, state -> true);
	}

	public BasicEyesLayer(RenderLayerParent<S, M> renderer, RenderType adultType, RenderType babyType) {
		this(renderer, adultType, babyType, s -> true);
	}

	public BasicEyesLayer(RenderLayerParent<S, M> renderer, RenderType type, Predicate<S> visible) {
		this(renderer, type, type, visible);
	}

	public BasicEyesLayer(RenderLayerParent<S, M> renderer, RenderType adultType, RenderType babyType, Predicate<S> visible) {
		super(renderer);
		this.adultType = adultType;
		this.babyType = babyType;
		this.visible = visible;
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int light, S state, float yRot, float xRot) {
		if (this.visible.test(state)) {
			collector.order(1).submitModel(this.getParentModel(), state, stack, state.isBaby ? this.babyType : this.adultType, light, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		}
	}
}
