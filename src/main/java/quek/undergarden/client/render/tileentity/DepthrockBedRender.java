package quek.undergarden.client.render.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.Level;
import quek.undergarden.Undergarden;
import quek.undergarden.block.tileentity.DepthrockBedTE;
import quek.undergarden.registry.UGTileEntities;

public class DepthrockBedRender extends BlockEntityRenderer<DepthrockBedTE> {

    private final ModelPart headPiece;
    private final ModelPart footPiece;

    public DepthrockBedRender(BlockEntityRenderDispatcher rendererDispatcher) {
        super(rendererDispatcher);
        this.headPiece = new ModelPart(64, 64, 0, 0);
        this.headPiece.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F, 0.0F);
        this.footPiece = new ModelPart(64, 64, 0, 25);
        this.footPiece.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F, 0.0F);
    }

    @Override
    public void render(DepthrockBedTE bed, float partialTicks, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int combinedLight, int combinedOverlay) {
        Level world = bed.getLevel();
        if (world != null) {
            BlockState blockstate = bed.getBlockState();
            DoubleBlockCombiner.NeighborCombineResult<? extends DepthrockBedTE> icallbackwrapper = DoubleBlockCombiner.combineWithNeigbour(UGTileEntities.DEPTHROCK_BED.get(), BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, blockstate, world, bed.getBlockPos(), (iWorld, pos) -> false);
            int i = icallbackwrapper.<Int2IntFunction>apply(new BrightnessCombiner<>()).get(combinedLight);
            this.renderPiece(matrixStack, renderTypeBuffer, blockstate.getValue(BedBlock.PART) == BedPart.HEAD, blockstate.getValue(BedBlock.FACING), i, combinedOverlay, false);
        }
        else {
            this.renderPiece(matrixStack, renderTypeBuffer, true, Direction.SOUTH, combinedLight, combinedOverlay, false);
            this.renderPiece(matrixStack, renderTypeBuffer, false, Direction.SOUTH, combinedLight, combinedOverlay, true);
        }
    }

    private void renderPiece(PoseStack matrixStack, MultiBufferSource renderTypeBuffer, boolean isBedHead, Direction direction, int combinedLight, int combinedOverlay, boolean isBedFoot) {
        this.headPiece.visible = isBedHead;
        this.footPiece.visible = !isBedHead;
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.5625D, isBedFoot ? -1.0D : 0.0D);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        matrixStack.translate(0.5D, 0.5D, 0.5D);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F + direction.toYRot()));
        matrixStack.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer ivertexbuilder = renderTypeBuffer.getBuffer(RenderType.entitySolid(new ResourceLocation(Undergarden.MODID, "textures/block/depthrock_bed.png")));
        this.headPiece.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay);
        this.footPiece.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay);
        matrixStack.popPose();
    }
}
