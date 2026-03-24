package quek.undergarden.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.util.Unit;
import org.joml.Vector3fc;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.CloggrumShieldModel;
import quek.undergarden.client.model.UGModelLayers;

import java.util.function.Consumer;

public class CloggrumShieldSpecialRenderer implements NoDataSpecialModelRenderer {

	private final SpriteGetter sprites;
	private final CloggrumShieldModel model;

	public CloggrumShieldSpecialRenderer(SpriteGetter sprites, CloggrumShieldModel model) {
		this.sprites = sprites;
		this.model = model;
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		SpriteId sprite = Sheets.SHIELD_MAPPER.apply(Undergarden.prefix("cloggrum_shield"));
		collector.submitModel(this.model, Unit.INSTANCE, stack, lightCoords, overlayCoords, -1, sprite, this.sprites, outlineColor, null);

		if (hasFoil) {
			collector.submitModel(this.model, Unit.INSTANCE, stack, RenderTypes.entityGlint(), lightCoords, overlayCoords, -1, this.sprites.get(sprite), 0, null);
		}
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
		public static final CloggrumShieldSpecialRenderer.Unbaked INSTANCE = new CloggrumShieldSpecialRenderer.Unbaked();
		public static final MapCodec<CloggrumShieldSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

		@Override
		public MapCodec<CloggrumShieldSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		public CloggrumShieldSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new CloggrumShieldSpecialRenderer(context.sprites(), new CloggrumShieldModel(context.entityModelSet().bakeLayer(UGModelLayers.CLOGGRUM_SHIELD)));
		}
	}
}
