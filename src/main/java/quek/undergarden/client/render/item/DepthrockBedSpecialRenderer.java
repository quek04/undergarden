package quek.undergarden.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.joml.Vector3fc;
import quek.undergarden.Undergarden;
import quek.undergarden.client.render.blockentity.DepthrockBedRenderer;

import java.util.function.Consumer;

public class DepthrockBedSpecialRenderer implements NoDataSpecialModelRenderer {

	private final DepthrockBedRenderer bedRenderer;
	private final SpriteId sprite;
	private final BedPart part;

	public DepthrockBedSpecialRenderer(DepthrockBedRenderer bedRenderer, SpriteId sprite, BedPart part) {
		this.bedRenderer = bedRenderer;
		this.sprite = sprite;
		this.part = part;
	}

	@Override
	public void submit(PoseStack stack, SubmitNodeCollector collector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		this.bedRenderer.submitPiece(this.part, this.sprite, stack, collector, lightCoords, overlayCoords, null, outlineColor);
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		this.bedRenderer.getExtents(this.part, output);
	}

	public record Unbaked(Identifier texture, BedPart part) implements NoDataSpecialModelRenderer.Unbaked {
		public static final MapCodec<DepthrockBedSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
				Identifier.CODEC.fieldOf("texture").forGetter(DepthrockBedSpecialRenderer.Unbaked::texture),
				BedPart.CODEC.fieldOf("part").forGetter(DepthrockBedSpecialRenderer.Unbaked::part))
			.apply(i, DepthrockBedSpecialRenderer.Unbaked::new));

		public Unbaked(BedPart part) {
			this(Undergarden.prefix("depthrock"), part);
		}

		@Override
		public MapCodec<DepthrockBedSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		public DepthrockBedSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new DepthrockBedSpecialRenderer(new DepthrockBedRenderer(context), Sheets.BED_MAPPER.apply(this.texture), this.part);
		}
	}
}
