package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Predicate;

public class BasicEyesLayer<T extends LivingEntity, M extends EntityModel<T>> extends EyesLayer<T, M> {

	private final RenderType type;
	private final Predicate<T> visible;

	public BasicEyesLayer(RenderLayerParent<T, M> renderer, RenderType type) {
		this(renderer, type, entity -> true);
	}

	public BasicEyesLayer(RenderLayerParent<T, M> renderer, RenderType type, Predicate<T> visible) {
		super(renderer);
		this.type = type;
		this.visible = visible;
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (this.visible.test(entity)) {
			super.render(stack, buffer, light, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
		}
	}

	@Override
	public RenderType renderType() {
		return this.type;
	}
}
