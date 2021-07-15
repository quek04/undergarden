package quek.undergarden.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import quek.undergarden.Undergarden;
import quek.undergarden.block.tileentity.DepthrockBedTE;
import quek.undergarden.registry.UGTileEntities;

public class DepthrockBedRender extends TileEntityRenderer<DepthrockBedTE> {

    private final ModelRenderer headPiece;
    private final ModelRenderer footPiece;

    public DepthrockBedRender(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
        this.headPiece = new ModelRenderer(64, 64, 0, 0);
        this.headPiece.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F, 0.0F);
        this.footPiece = new ModelRenderer(64, 64, 0, 25);
        this.footPiece.addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 9.0F, 0.0F);
    }

    @Override
    public void render(DepthrockBedTE bed, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int combinedLight, int combinedOverlay) {
        World world = bed.getLevel();
        if (world != null) {
            BlockState blockstate = bed.getBlockState();
            TileEntityMerger.ICallbackWrapper<? extends DepthrockBedTE> icallbackwrapper = TileEntityMerger.combineWithNeigbour(UGTileEntities.DEPTHROCK_BED.get(), BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, blockstate, world, bed.getBlockPos(), (iWorld, pos) -> false);
            int i = icallbackwrapper.<Int2IntFunction>apply(new DualBrightnessCallback<>()).get(combinedLight);
            this.renderPiece(matrixStack, renderTypeBuffer, blockstate.getValue(BedBlock.PART) == BedPart.HEAD, blockstate.getValue(BedBlock.FACING), i, combinedOverlay, false);
        } else {
            //this.renderPiece(matrixStack, renderTypeBuffer, true, Direction.SOUTH, combinedLight, combinedOverlay, false);
            //this.renderPiece(matrixStack, renderTypeBuffer, false, Direction.SOUTH, combinedLight, combinedOverlay, true);
        }
    }

    private void renderPiece(MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, boolean isBedHead, Direction direction, int combinedLight, int combinedOverlay, boolean isBedFoot) {
        this.headPiece.visible = isBedHead;
        this.footPiece.visible = !isBedHead;
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.5625D, isBedFoot ? -1.0D : 0.0D);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        matrixStack.translate(0.5D, 0.5D, 0.5D);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F + direction.toYRot()));
        matrixStack.translate(-0.5D, -0.5D, -0.5D);
        IVertexBuilder ivertexbuilder = renderTypeBuffer.getBuffer(RenderType.entitySolid(new ResourceLocation(Undergarden.MODID, "textures/block/depthrock_bed.png")));
        this.headPiece.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay);
        this.footPiece.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay);
        matrixStack.popPose();
    }
}
