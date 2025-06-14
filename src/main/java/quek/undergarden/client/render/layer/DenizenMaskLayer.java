package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGItems;

public class DenizenMaskLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/models/armor/denizen_mask.png");
	private final A model;

	public DenizenMaskLayer(RenderLayerParent<T, M> renderer, A model) {
		super(renderer);
		this.model = model;
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.HEAD);
		if (itemStack.getItem() == UGItems.DENIZEN_MASK.get()) {
			VertexConsumer consumer = buffer.getBuffer(RenderType.armorCutoutNoCull(TEXTURE));
			this.getParentModel().copyPropertiesTo(model);
			model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
			//model.renderToBuffer(stack, consumer, 1, 1, 1);
		}
	}
}
