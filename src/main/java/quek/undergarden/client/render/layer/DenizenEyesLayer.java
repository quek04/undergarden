package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.monster.denizen.Denizen;

public class DenizenEyesLayer<T extends Denizen, M extends EntityModel<T>> extends RenderLayer<T, M> {
	private static final RenderType DENIZEN_EYES = RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/denizen_eyes.png"));
	private static final RenderType DENIZEN2_EYES = RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/denizen2_eyes.png"));;

	public DenizenEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		VertexConsumer consumer = buffer.getBuffer(switch (entity.getVariant()) {
			case SHORT -> DENIZEN_EYES;
			case TALL -> DENIZEN2_EYES;
		});
		this.getParentModel().renderToBuffer(poseStack, consumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}