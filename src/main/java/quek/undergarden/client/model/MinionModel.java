package quek.undergarden.client.model;
// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.MinionEntity;

public class MinionModel<T extends MinionEntity> extends SegmentedModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leftLeg1;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg1;
	private final ModelRenderer rightLeg2;

	public MinionModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 10.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.texOffs(27, 19).addBox(-1.0F, 1.0F, -7.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 17.5F, 0.0F);
		body.texOffs(0, 16).addBox(-3.0F, -3.5F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		leftLeg1 = new ModelRenderer(this);
		leftLeg1.setPos(2.0F, 21.0F, -2.0F);
		leftLeg1.texOffs(18, 16).addBox(0.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		leftLeg2 = new ModelRenderer(this);
		leftLeg2.setPos(2.0F, 21.0F, 2.0F);
		leftLeg2.texOffs(18, 16).addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		rightLeg1 = new ModelRenderer(this);
		rightLeg1.setPos(-2.0F, 21.0F, -2.0F);
		rightLeg1.texOffs(18, 16).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		rightLeg2 = new ModelRenderer(this);
		rightLeg2.setPos(-2.0F, 21.0F, 2.0F);
		rightLeg2.texOffs(18, 16).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.leftLeg1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.rightLeg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightLeg2.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(head, body, leftLeg1, leftLeg2, rightLeg1, rightLeg2);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}