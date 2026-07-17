package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import quek.undergarden.Undergarden;
import quek.undergarden.client.render.UGRenderTypes;
import quek.undergarden.event.UndergardenClientEvents;
import quek.undergarden.event.UthericInfectionEvents;
import quek.undergarden.registry.UGAttachments;

public class UthericInfectionLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

	public UthericInfectionLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
		double infectionLevel = entity.getData(UGAttachments.UTHERIC_INFECTION.get());
		if (infectionLevel > 0) {
			VertexConsumer consumer = buffer.getBuffer(UGRenderTypes.entityDecalTranslucent(UndergardenClientEvents.UTHERIC_INFECTION_OVERLAY));
			float alpha = (float) (infectionLevel / UthericInfectionEvents.MAX_INFECTION);
			this.getParentModel().renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, FastColor.ARGB32.colorFromFloat(Mth.clamp(alpha, 0.0F, 0.75F), alpha, alpha, alpha));
		}
	}
}