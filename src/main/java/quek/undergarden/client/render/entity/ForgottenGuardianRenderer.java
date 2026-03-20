package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenGuardianModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.monster.boss.ForgottenGuardian;

public class ForgottenGuardianRenderer extends MobRenderer<ForgottenGuardian, ForgottenGuardianModel<ForgottenGuardian>> {

	private static final Identifier FORGOTTEN_GUARDIAN = Undergarden.prefix("textures/entity/forgotten_guardian.png");
	private static final RenderType FORGOTTEN_GUARDIAN_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/forgotten_guardian_eyes.png"));

	public ForgottenGuardianRenderer(EntityRendererProvider.Context context) {
		super(context, new ForgottenGuardianModel<>(context.bakeLayer(UGModelLayers.FORGOTTEN_GUARDIAN)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, FORGOTTEN_GUARDIAN_EYES));
	}

	@Override
	public Identifier getTextureLocation(ForgottenGuardian entity) {
		return FORGOTTEN_GUARDIAN;
	}

	@Override
	public void setupRotations(ForgottenGuardian entity, PoseStack stack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
		super.setupRotations(entity, stack, ageInTicks, rotationYaw, partialTicks, scale);
		if (!((double) entity.walkAnimation.speed() < 0.01D)) {
			float f1 = entity.walkAnimation.position() - entity.walkAnimation.speed() * (1.0F - partialTicks) + 6.0F;
			float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
			stack.mulPose(Axis.ZP.rotationDegrees(6.5F * f2));
		}
	}
}