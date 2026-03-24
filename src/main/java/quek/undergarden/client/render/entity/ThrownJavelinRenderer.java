package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.JavelinModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.state.entity.ThrownJavelinRenderState;
import quek.undergarden.entity.projectile.ThrownJavelin;

public class ThrownJavelinRenderer extends EntityRenderer<ThrownJavelin, ThrownJavelinRenderState> {

	public static final Identifier TEXTURE = Undergarden.prefix("textures/entity/javelin.png");
	private final JavelinModel model;

	public ThrownJavelinRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new JavelinModel(context.bakeLayer(UGModelLayers.JAVELIN));
	}

	@Override
	public ThrownJavelinRenderState createRenderState() {
		return new ThrownJavelinRenderState();
	}

	@Override
	public void extractRenderState(ThrownJavelin entity, ThrownJavelinRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.yRot = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
		state.xRot = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
	}

	@Override
	public void submit(ThrownJavelinRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
		stack.mulPose(Axis.ZP.rotationDegrees(state.xRot + 90.0F));
		collector.order(0).submitModel(this.model, Unit.INSTANCE, stack, TEXTURE, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);

		stack.popPose();
		super.submit(state, stack, collector, camera);
	}
}