package quek.undergarden.client.model;
// Made with Blockbench 3.5.0
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.rotspawn.RotbeastEntity;

@OnlyIn(Dist.CLIENT)
public class RotbeastModel<T extends RotbeastEntity> extends SegmentedModel<T> {
	private final ModelRenderer rotbeast;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm2;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm2;
	private final ModelRenderer torso;
	private final ModelRenderer torsoLower;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightLeg2;

	public RotbeastModel() {
		textureWidth = 128;
		textureHeight = 128;

		rotbeast = new ModelRenderer(this);
		rotbeast.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, -8.0F);
		rotbeast.addChild(head);
		setRotationAngle(head, 0.3491F, 0.0F, 0.0F);
		head.setTextureOffset(0, 22).addBox(-5.0F, -9.0F, -7.0F, 10.0F, 6.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(0, 83).addBox(-3.0F, -8.0F, -1.0F, 6.0F, 9.0F, 2.0F, 0.0F, true);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(jaw);
		setRotationAngle(jaw, 0.2618F, 0.0F, 0.0F);
		jaw.setTextureOffset(22, 66).addBox(-5.0F, -3.0F, -6.0F, 10.0F, 1.0F, 6.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(10.0F, -32.0F, -4.0F);
		rotbeast.addChild(leftArm);
		setRotationAngle(leftArm, 0.2618F, 0.0F, 0.0F);
		leftArm.setTextureOffset(34, 22).addBox(0.0F, -2.0F, -3.0F, 5.0F, 18.0F, 6.0F, 0.0F, false);

		leftArm2 = new ModelRenderer(this);
		leftArm2.setRotationPoint(2.0F, 16.0F, 0.0F);
		leftArm.addChild(leftArm2);
		setRotationAngle(leftArm2, -0.3491F, 0.0F, 0.0F);
		leftArm2.setTextureOffset(0, 46).addBox(-2.0F, -1.0F, -3.0F, 5.0F, 14.0F, 6.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-10.0F, -32.0F, -4.0F);
		rotbeast.addChild(rightArm);
		setRotationAngle(rightArm, 0.2618F, 0.0F, 0.0F);
		rightArm.setTextureOffset(34, 22).addBox(-5.0F, -2.0F, -3.0F, 5.0F, 18.0F, 6.0F, 0.0F, true);

		rightArm2 = new ModelRenderer(this);
		rightArm2.setRotationPoint(-2.0F, 16.0F, 0.0F);
		rightArm.addChild(rightArm2);
		setRotationAngle(rightArm2, -0.3491F, 0.0F, 0.0F);
		rightArm2.setTextureOffset(0, 46).addBox(-3.0F, -1.0F, -3.0F, 5.0F, 14.0F, 6.0F, 0.0F, true);

		torso = new ModelRenderer(this);
		torso.setRotationPoint(0.0F, -30.0F, -3.0F);
		rotbeast.addChild(torso);
		setRotationAngle(torso, 0.6109F, 0.0F, 0.0F);
		torso.setTextureOffset(0, 0).addBox(-10.0F, -7.0F, -5.0F, 20.0F, 12.0F, 10.0F, 0.0F, false);

		torsoLower = new ModelRenderer(this);
		torsoLower.setRotationPoint(0.0F, 8.0F, -2.0F);
		torso.addChild(torsoLower);
		setRotationAngle(torsoLower, -0.3491F, 0.0F, 0.0F);
		torsoLower.setTextureOffset(60, 0).addBox(-8.0F, -5.0F, -1.0F, 16.0F, 11.0F, 6.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(4.0F, -17.0F, 3.0F);
		rotbeast.addChild(leftLeg);
		setRotationAngle(leftLeg, -0.3491F, 0.0F, 0.0F);
		leftLeg.setTextureOffset(54, 66).addBox(-2.0F, -1.0F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		leftLeg2 = new ModelRenderer(this);
		leftLeg2.setRotationPoint(0.0F, 7.0F, 1.0F);
		leftLeg.addChild(leftLeg2);
		setRotationAngle(leftLeg2, 0.3491F, 0.0F, 0.0F);
		leftLeg2.setTextureOffset(44, 46).addBox(-2.0F, -1.0F, -4.0F, 5.0F, 11.0F, 6.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-4.0F, -17.0F, 3.0F);
		rotbeast.addChild(rightLeg);
		setRotationAngle(rightLeg, -0.3491F, 0.0F, 0.0F);
		rightLeg.setTextureOffset(54, 66).addBox(-3.0F, -1.0F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, true);

		rightLeg2 = new ModelRenderer(this);
		rightLeg2.setRotationPoint(0.0F, 7.0F, 1.0F);
		rightLeg.addChild(rightLeg2);
		setRotationAngle(rightLeg2, 0.3491F, 0.0F, 0.0F);
		rightLeg2.setTextureOffset(44, 46).addBox(-3.0F, -1.0F, -4.0F, 5.0F, 11.0F, 6.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = 0.3491F + headPitch * ((float)Math.PI / 180F);

		this.leftLeg.rotateAngleX = -0.3491F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.rotateAngleX = -0.3491F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(rotbeast);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}