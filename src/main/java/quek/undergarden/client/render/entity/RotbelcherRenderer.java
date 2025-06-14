package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotbelcherModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.RotbelcherEyesLayer;
import quek.undergarden.entity.monster.rotspawn.Rotbelcher;

public class RotbelcherRenderer extends MobRenderer<Rotbelcher, RotbelcherModel<Rotbelcher>> {

	private static final ResourceLocation ROTBELCHER = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/rotbelcher.png");

	public RotbelcherRenderer(EntityRendererProvider.Context context) {
		super(context, new RotbelcherModel<>(context.bakeLayer(UGModelLayers.ROTBELCHER)), 0.6F);
		this.addLayer(new RotbelcherEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Rotbelcher entity) {
		return ROTBELCHER;
	}

	@Override
	public void setupRotations(Rotbelcher entity, PoseStack stack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
		super.setupRotations(entity, stack, ageInTicks, rotationYaw, partialTicks, scale);
		if (!((double) entity.walkAnimation.speed() < 0.01D)) {
			float f1 = entity.walkAnimation.position() - entity.walkAnimation.speed() * (1.0F - partialTicks) + 6.0F;
			float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
			stack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
		}
	}
}
