package quek.undergarden.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import org.joml.Vector3fc;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.CloggrumShieldModel;
import quek.undergarden.client.model.SpearModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.entity.ThrownSpearRenderer;

import java.util.function.Consumer;

public class SpearSpecialRenderer implements NoDataSpecialModelRenderer {

	private final SpearModel model;

	public SpearSpecialRenderer(SpearModel model) {
		this.model = model;
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		collector.submitModelPart(this.model.root(), stack, this.model.renderType(ThrownSpearRenderer.TEXTURE), lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor);
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
		public static final SpearSpecialRenderer.Unbaked INSTANCE = new SpearSpecialRenderer.Unbaked();
		public static final MapCodec<SpearSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

		@Override
		public MapCodec<SpearSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		public SpearSpecialRenderer bake(BakingContext context) {
			return new SpearSpecialRenderer(new SpearModel(context.entityModelSet().bakeLayer(UGModelLayers.SPEAR)));
		}
	}
}
