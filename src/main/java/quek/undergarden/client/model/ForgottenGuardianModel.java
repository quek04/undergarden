package quek.undergarden.client.model;
// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.boss.ForgottenGuardianEntity;

public class ForgottenGuardianModel<T extends ForgottenGuardianEntity> extends SegmentedModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public ForgottenGuardianModel() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -20.0F, 0.0F);
		head.setTextureOffset(42, 10).addBox(-3.0F, -15.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(60, 4).addBox(-2.0F, -15.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-8.0F, -51.0F, -4.0F, 16.0F, 8.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 27).addBox(-6.0F, -43.0F, -3.0F, 12.0F, 13.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 16).addBox(-7.0F, -30.0F, -4.0F, 14.0F, 3.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(36, 36).addBox(-3.0F, -27.0F, -4.0F, 6.0F, 3.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(30, 27).addBox(-10.0F, -49.0F, -1.0F, 20.0F, 2.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(74, 21).addBox(-1.0F, -51.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(10.0F, -24.0F, 0.0F);
		leftArm.setTextureOffset(0, 64).addBox(0.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
		leftArm.setTextureOffset(0, 46).addBox(-1.0F, 15.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-10.0F, -24.0F, 0.0F);
		rightArm.setTextureOffset(0, 64).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, true);
		rightArm.setTextureOffset(0, 46).addBox(-3.0F, 15.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-3.0F, -2.0F, 0.0F);
		leftLeg.setTextureOffset(8, 64).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, true);
		leftLeg.setTextureOffset(16, 46).addBox(-3.0F, 13.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(3.0F, -2.0F, 0.0F);
		rightLeg.setTextureOffset(8, 64).addBox(0.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);
		rightLeg.setTextureOffset(16, 46).addBox(-1.0F, 13.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.leftLeg.rotateAngleX = -1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.rotateAngleX = 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.rotateAngleY = 0.0F;
		this.rightLeg.rotateAngleY = 0.0F;
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int attackTimer = entityIn.getAttackTimer();
		if (attackTimer > 0) {
			this.rightArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) attackTimer - partialTick, 10.0F);
			this.leftArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) attackTimer - partialTick, 10.0F);
		}
		else {
			this.rightArm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftArm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(head, body, leftArm, rightArm, leftLeg, rightLeg);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}