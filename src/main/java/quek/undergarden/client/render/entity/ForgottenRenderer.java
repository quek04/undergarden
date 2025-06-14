package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.ForgottenEyesLayer;
import quek.undergarden.entity.monster.Forgotten;

public class ForgottenRenderer extends HumanoidMobRenderer<Forgotten, ForgottenModel<Forgotten>> {

	private static final ResourceLocation FORGOTTEN = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/forgotten.png");

	public ForgottenRenderer(EntityRendererProvider.Context context) {
		super(context, new ForgottenModel<>(context.bakeLayer(UGModelLayers.FORGOTTEN)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new ForgottenModel<>(context.bakeLayer(UGModelLayers.FORGOTTEN_INNER_ARMOR)), new ForgottenModel<>(context.bakeLayer(UGModelLayers.FORGOTTEN_OUTER_ARMOR)), context.getModelManager()));
		this.addLayer(new ForgottenEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Forgotten entity) {
		return FORGOTTEN;
	}

	@Override
	protected void scale(Forgotten entity, PoseStack stack, float partialTicks) {
		stack.scale(1.1F, 1.1F, 1.1F);
	}
}