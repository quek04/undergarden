package quek.undergarden.client.model;
// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RotlingModel<T extends Entity> extends SegmentedModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;

	public RotlingModel() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.5F, 0.0F);
		body.setTextureOffset(0, 17).addBox(-4.0F, -10.5F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 38).addBox(-4.0F, -12.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -11.5F, 4.0F);
		body.addChild(head);
		setRotationAngle(head, -0.1745F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 5.0F, 8.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-4.0F, -3.5F, 1.0F);
		body.addChild(rightLeg);
		rightLeg.setTextureOffset(24, 2).addBox(0.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(4.0F, -3.5F, 1.0F);
		body.addChild(leftLeg);
		leftLeg.setTextureOffset(24, 2).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-3.0F, -10.5F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 0.0F, 0.4363F);
		rightArm.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(4.0F, -11.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, -0.4363F);
		leftArm.setTextureOffset(0, 0).addBox(-1.2817F, 0.0977F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(body);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}