package quek.undergarden.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.PotDecorations;
import quek.undergarden.Undergarden;
import quek.undergarden.block.entity.DepthrockPotBlockEntity;
import quek.undergarden.client.model.PotModel;
import quek.undergarden.client.model.UGModelLayers;

public class DepthrockPotRenderer implements BlockEntityRenderer<DepthrockPotBlockEntity> {

	public static final ResourceLocation TEXTURE = Undergarden.prefix("textures/entity/pot.png");
	private final PotModel pot;

	public DepthrockPotRenderer(BlockEntityRendererProvider.Context context) {
		this.pot = new PotModel(context.bakeLayer(UGModelLayers.POT));
	}

	@Override
	public void render(DepthrockPotBlockEntity entity, float partialTick, PoseStack stack, MultiBufferSource buffer, int light, int overlay) {
		stack.pushPose();
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(-0.5F, -1.501F, 0.5F);
		this.pot.renderToBuffer(stack, buffer.getBuffer(RenderType.entitySolid(TEXTURE)), light, overlay);
		stack.popPose();
	}
}
