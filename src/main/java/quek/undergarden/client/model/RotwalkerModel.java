package quek.undergarden.client.model;
// Made with Blockbench 3.5.0
// Exported for Minecraft version 1.15

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.rotspawn.RotwalkerEntity;

@OnlyIn(Dist.CLIENT)
public class RotwalkerModel<T extends RotwalkerEntity> extends BipedModel<T> {
	private final ModelRenderer rotwalker;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer neck;
	private final ModelRenderer torso1;
	private final ModelRenderer torso2;
	private final ModelRenderer pelvis;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public RotwalkerModel() {
		super(0F, -14F, 64, 64);
		textureWidth = 64;
		textureHeight = 64;

		rotwalker = new ModelRenderer(this);
		rotwalker.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -34.0F, 0.0F);
		rotwalker.addChild(head);
		setRotationAngle(head, 0.0873F, 0.0F, 0.0F);
		head.setTextureOffset(0, 13).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, -2.0F, 1.0F);
		head.addChild(jaw);
		setRotationAngle(jaw, -0.0873F, 0.0F, 0.0F);
		jaw.setTextureOffset(25, 25).addBox(-3.0F, -1.0F, -8.0F, 6.0F, 2.0F, 7.0F, 0.0F, false);

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, -3.0F, 1.0F);
		head.addChild(neck);
		setRotationAngle(neck, 0.1745F, 0.0F, 0.0F);
		neck.setTextureOffset(0, 13).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		torso1 = new ModelRenderer(this);
		torso1.setRotationPoint(0.0F, -31.0F, -1.0F);
		rotwalker.addChild(torso1);
		setRotationAngle(torso1, 0.0873F, 0.0F, 0.0F);
		torso1.setTextureOffset(0, 0).addBox(-6.0F, -3.0F, -2.0F, 12.0F, 7.0F, 6.0F, 0.0F, false);

		torso2 = new ModelRenderer(this);
		torso2.setRotationPoint(0.0F, 7.0F, 2.0F);
		torso1.addChild(torso2);
		setRotationAngle(torso2, -0.1745F, 0.0F, 0.0F);
		torso2.setTextureOffset(0, 28).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 8.0F, 4.0F, 0.0F, false);

		pelvis = new ModelRenderer(this);
		pelvis.setRotationPoint(0.0F, 11.0F, 0.0F);
		torso1.addChild(pelvis);
		setRotationAngle(pelvis, -0.0873F, 0.0F, 0.0F);
		pelvis.setTextureOffset(24, 13).addBox(-5.0F, 0.0F, -2.0F, 10.0F, 3.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-6.0F, -31.0F, -1.0F);
		rotwalker.addChild(rightArm);
		rightArm.setTextureOffset(24, 34).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 21.0F, 2.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(6.0F, -31.0F, 0.0F);
		rotwalker.addChild(leftArm);
		leftArm.setTextureOffset(24, 34).addBox(0.0F, -1.0F, -1.0F, 2.0F, 21.0F, 2.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-3.0F, -18.0F, 0.0F);
		rotwalker.addChild(rightLeg);
		rightLeg.setTextureOffset(0, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(3.0F, -18.0F, 0.0F);
		rotwalker.addChild(leftLeg);
		leftLeg.setTextureOffset(0, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = 0.0873F + headPitch * ((float)Math.PI / 180F);

		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		rotwalker.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}