package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.MysteriousPotModel;
import quek.undergarden.client.model.PotModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.blockentity.DepthrockPotRenderer;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.MysteriousPotRenderState;
import quek.undergarden.entity.animal.MysteriousPot;

public class MysteriousPotRenderer extends MobRenderer<MysteriousPot, MysteriousPotRenderState, MysteriousPotModel> {

	private static final Identifier MYSTERIOUS_POT = Undergarden.prefix("textures/entity/potguy.png");
	private static final RenderType MYSTERIOUS_POT_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/potguy_eyes.png"));

	private final PotModel inactiveModel;

	public MysteriousPotRenderer(EntityRendererProvider.Context context) {
		super(context, new MysteriousPotModel(context.bakeLayer(UGModelLayers.LIVING_POT)), 0.3F);
		this.addLayer(new BasicEyesLayer<>(this, MYSTERIOUS_POT_EYES, state -> state.active));
		this.inactiveModel = new PotModel(context.bakeLayer(UGModelLayers.POT));
	}

	@Override
	public MysteriousPotRenderState createRenderState() {
		return new MysteriousPotRenderState();
	}

	@Override
	public void extractRenderState(MysteriousPot entity, MysteriousPotRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.active = entity.isActive();
		state.wiggleTicks = entity.getInactiveWiggleTicks(partialTicks);
	}

	@Override
	public void submit(MysteriousPotRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
		if (!state.active) {
			stack.pushPose();
			stack.scale(-1.0F, -1.0F, 1.0F);
			stack.translate(-0.5F, 0.0F, -0.5F);
			float wiggle = state.wiggleTicks / 10.0F;
			float f5 = Mth.sin(-wiggle * 4.0F * Mth.PI) * 0.125F;
			float f6 = 1.0F - wiggle;
			stack.rotateAround(Axis.YP.rotation(f5 * f6), 0.5F, 0.0F, 0.5F);
			stack.translate(0.5F, -1.501F, 0.5F);
			collector.submitModel(this.inactiveModel, Unit.INSTANCE, stack, RenderTypes.entitySolid(DepthrockPotRenderer.TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, null, 0, null);
			stack.popPose();
		} else {
			super.submit(state, stack, collector, camera);
		}
	}

	@Override
	public Identifier getTextureLocation(MysteriousPotRenderState state) {
		return MYSTERIOUS_POT;
	}
}
