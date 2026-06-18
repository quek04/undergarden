package quek.undergarden.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.util.Unit;
import org.joml.Vector3fc;
import quek.undergarden.client.model.PotModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.blockentity.DepthrockPotRenderer;

import java.util.function.Consumer;

public class DepthrockPotSpecialRenderer implements NoDataSpecialModelRenderer {

	private final PotModel model;

	public DepthrockPotSpecialRenderer(PotModel model) {
		this.model = model;
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		stack.pushPose();
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(-0.5F, -1.501F, 0.5F);
		collector.submitModel(this.model, Unit.INSTANCE, stack, DepthrockPotRenderer.TEXTURE, lightCoords, overlayCoords, outlineColor, null);
		stack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {

		public static final MapCodec<DepthrockPotSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

		@Override
		public MapCodec<DepthrockPotSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		public DepthrockPotSpecialRenderer bake(BakingContext context) {
			return new DepthrockPotSpecialRenderer(new PotModel(context.entityModelSet().bakeLayer(UGModelLayers.POT)));
		}
	}
}
