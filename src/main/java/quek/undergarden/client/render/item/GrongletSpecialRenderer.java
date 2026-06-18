package quek.undergarden.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import org.joml.Vector3fc;
import quek.undergarden.client.model.GrongletModel;
import quek.undergarden.client.model.UGModelLayers;

import java.util.function.Consumer;

public class GrongletSpecialRenderer implements NoDataSpecialModelRenderer {

	private final GrongletModel model;
	private final Identifier texture;

	public GrongletSpecialRenderer(GrongletModel model, Identifier texture) {
		this.model = model;
		this.texture = texture;
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		stack.pushPose();
		stack.translate(0.5D, 1.5D, 0.5D);
		stack.mulPose(Axis.ZP.rotationDegrees(180F));
		collector.submitModel(this.model, Unit.INSTANCE, stack, this.texture, lightCoords, overlayCoords, outlineColor, null);
		stack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack stack = new PoseStack();
		this.model.root().getExtentsForGui(stack, output);
	}

	public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

		public static final MapCodec<GrongletSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
				Identifier.CODEC.fieldOf("texture").forGetter(GrongletSpecialRenderer.Unbaked::texture))
			.apply(i, GrongletSpecialRenderer.Unbaked::new));

		@Override
		public SpecialModelRenderer<Void> bake(BakingContext context) {
			return new GrongletSpecialRenderer(new GrongletModel(context.entityModelSet().bakeLayer(UGModelLayers.GRONGLET)), this.texture());
		}

		@Override
		public MapCodec<? extends NoDataSpecialModelRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
