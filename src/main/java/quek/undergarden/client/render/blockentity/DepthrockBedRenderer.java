package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.state.BedRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.util.Unit;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;
import quek.undergarden.Undergarden;
import quek.undergarden.block.DepthrockBedBlock;
import quek.undergarden.block.entity.DepthrockBedBlockEntity;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.registry.UGBlockEntities;

import java.util.Map;
import java.util.function.Consumer;

public class DepthrockBedRenderer implements BlockEntityRenderer<DepthrockBedBlockEntity, BedRenderState> {

	private static final Map<Direction, Transformation> TRANSFORMATIONS = Util.makeEnumMap(Direction.class, DepthrockBedRenderer::createModelTransform);
	private final SpriteGetter sprites;
	private final Model.Simple headModel;
	private final Model.Simple footModel;

	public DepthrockBedRenderer(BlockEntityRendererProvider.Context context) {
		this(context.sprites(), context.entityModelSet());
	}

	public DepthrockBedRenderer(SpecialModelRenderer.BakingContext context) {
		this(context.sprites(), context.entityModelSet());
	}

	public DepthrockBedRenderer(SpriteGetter sprites, EntityModelSet set) {
		this.sprites = sprites;
		this.headModel = new Model.Simple(set.bakeLayer(UGModelLayers.DEPTHROCK_BED_HEAD), RenderTypes::entitySolid);
		this.footModel = new Model.Simple(set.bakeLayer(UGModelLayers.DEPTHROCK_BED_FOOT), RenderTypes::entitySolid);
	}

	public static LayerDefinition createHeadLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F), PartPose.ZERO);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	public static LayerDefinition createFootLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F), PartPose.ZERO);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public BedRenderState createRenderState() {
		return new BedRenderState();
	}

	@Override
	public void extractRenderState(DepthrockBedBlockEntity blockEntity, BedRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.facing = blockEntity.getBlockState().getValue(DepthrockBedBlock.FACING);
		state.part = blockEntity.getBlockState().getValue(DepthrockBedBlock.PART);
		if (blockEntity.getLevel() != null) {
			DoubleBlockCombiner.NeighborCombineResult<? extends DepthrockBedBlockEntity> combineResult = DoubleBlockCombiner.combineWithNeigbour(
				UGBlockEntities.DEPTHROCK_BED.get(),
				DepthrockBedBlock::getBlockType,
				DepthrockBedBlock::getConnectedDirection,
				DepthrockBedBlock.FACING,
				blockEntity.getBlockState(),
				blockEntity.getLevel(),
				blockEntity.getBlockPos(),
				(_, _) -> false
			);
			state.lightCoords = combineResult.apply(new BrightnessCombiner<>()).get(state.lightCoords);
		}
	}

	@Override
	public void submit(BedRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		SpriteId sprite = Sheets.BED_MAPPER.apply(Undergarden.prefix("depthrock"));
		poseStack.pushPose();
		poseStack.mulPose(modelTransform(state.facing));
		this.submitPiece(state.part, sprite, poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.breakProgress, 0);
		poseStack.popPose();
	}

	public void submitPiece(BedPart part, SpriteId sprite, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress, int outlineColor) {
		Model.Simple model = this.getPieceModel(part);
		submitNodeCollector.submitModel(model, Unit.INSTANCE, poseStack, lightCoords, overlayCoords, -1, sprite, this.sprites, outlineColor, breakProgress);
	}

	private Model.Simple getPieceModel(BedPart part) {
		return switch (part) {
			case HEAD -> this.headModel;
			case FOOT -> this.footModel;
		};
	}

	private static Transformation createModelTransform(Direction direction) {
		return new Transformation(
			new Matrix4f()
				.translation(0.0F, 0.5625F, 0.0F)
				.rotate(Axis.XP.rotationDegrees(90.0F))
				.rotateAround(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()), 0.5F, 0.5F, 0.5F)
		);
	}

	public static Transformation modelTransform(Direction direction) {
		return TRANSFORMATIONS.get(direction);
	}

	public void getExtents(BedPart part, Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.getPieceModel(part).root().getExtentsForGui(poseStack, output);
	}
}