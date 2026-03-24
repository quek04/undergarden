package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemDisplayContext;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SpearModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.state.entity.ThrownSpearRenderState;
import quek.undergarden.entity.projectile.ThrownSpear;

public class ThrownSpearRenderer extends EntityRenderer<ThrownSpear, ThrownSpearRenderState> {

	public static final Identifier TEXTURE = Undergarden.prefix("textures/entity/spear.png");
	private final SpearModel model;

	public ThrownSpearRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SpearModel(context.bakeLayer(UGModelLayers.SPEAR));
	}

	@Override
	public ThrownSpearRenderState createRenderState() {
		return new ThrownSpearRenderState();
	}

	@Override
	public void extractRenderState(ThrownSpear entity, ThrownSpearRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.yRot = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
		state.xRot = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
		state.isFoil = entity.isFoil();
	}

	@Override
	public void submit(ThrownSpearRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
		stack.mulPose(Axis.ZP.rotationDegrees(state.xRot + 90.0F));
		collector.order(0).submitModel(this.model, Unit.INSTANCE, stack, TEXTURE, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		if (state.isFoil) {
			collector.order(1).submitModel(this.model, Unit.INSTANCE, stack, ItemFeatureRenderer.getFoilRenderType(this.model.renderType(TEXTURE), false), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		}

		stack.popPose();
		super.submit(state, stack, collector, camera);
	}
}