package quek.undergarden.client.model;
// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class RotlingModel<T extends Entity> extends SegmentedModel<T> {
	private final ModelRenderer rotling;
	private final ModelRenderer head;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;

	public RotlingModel() {
		textureWidth = 64;
		textureHeight = 64;

		rotling = new ModelRenderer(this);
		rotling.setRotationPoint(0.0F, 15.0F, 0.0F);
		rotling.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 6.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 9.0F, 0.0F);
		rotling.addChild(head);
		head.setTextureOffset(0, 0).addBox(-4.0F, -17.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(4.0F, 1.0F, 0.0F);
		rotling.addChild(leftarm);
		leftarm.setTextureOffset(0, 26).addBox(0.0F, -1.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-4.0F, 1.0F, 0.0F);
		rotling.addChild(rightarm);
		rightarm.setTextureOffset(0, 26).addBox(-3.0F, -1.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(2.0F, 6.0F, 0.0F);
		rotling.addChild(leftleg);
		leftleg.setTextureOffset(14, 28).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-2.0F, 6.0F, 0.0F);
		rotling.addChild(rightleg);
		rightleg.setTextureOffset(14, 28).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);

		this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		rotling.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(rotling);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}