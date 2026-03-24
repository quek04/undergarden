package quek.undergarden.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import org.joml.Vector3fc;
import quek.undergarden.client.model.JavelinModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.entity.ThrownJavelinRenderer;

import java.util.function.Consumer;

public class JavelinSpecialRenderer implements NoDataSpecialModelRenderer {

	private final JavelinModel model;

	public JavelinSpecialRenderer(JavelinModel model) {
		this.model = model;
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		collector.submitModelPart(this.model.root(), stack, this.model.renderType(ThrownJavelinRenderer.TEXTURE), lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor);
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
		public static final JavelinSpecialRenderer.Unbaked INSTANCE = new JavelinSpecialRenderer.Unbaked();
		public static final MapCodec<JavelinSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

		@Override
		public MapCodec<JavelinSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		public JavelinSpecialRenderer bake(BakingContext context) {
			return new JavelinSpecialRenderer(new JavelinModel(context.entityModelSet().bakeLayer(UGModelLayers.JAVELIN)));
		}
	}
}
