package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.boss.ForgottenGuardianEntity;

public class ForgottenGuardianModel<T extends ForgottenGuardianEntity> extends SegmentedModel<T> {
	private final ModelRenderer forgottenGuardian;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer chest;
	private final ModelRenderer torso;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;

	public ForgottenGuardianModel() {
		texWidth = 128;
		texHeight = 128;

		forgottenGuardian = new ModelRenderer(this);
		forgottenGuardian.setPos(0.0F, -23.0F, 6.0F);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(4.0F, 14.0F, -6.0F);
		forgottenGuardian.addChild(leftLeg);
		leftLeg.texOffs(20, 73).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);
		leftLeg.texOffs(0, 45).addBox(-2.0F, 11.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-4.0F, 14.0F, -6.0F);
		forgottenGuardian.addChild(rightLeg);
		rightLeg.texOffs(67, 76).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);
		rightLeg.texOffs(24, 45).addBox(-4.0F, 11.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 13.0F, -6.0F);
		forgottenGuardian.addChild(body);
		body.texOffs(48, 11).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 4.0F, 8.0F, 0.0F, false);
		body.texOffs(48, 45).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 17.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -14.0F, 1.0F);
		body.addChild(head);
		head.texOffs(41, 69).addBox(-3.0F, -13.0F, -5.0F, 6.0F, 9.0F, 7.0F, 0.0F, false);
		head.texOffs(76, 0).addBox(-3.0F, -13.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		head.texOffs(42, 0).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 7.0F, 3.0F, 0.0F, false);

		chest = new ModelRenderer(this);
		chest.setPos(0.0F, -14.0F, 6.0F);
		body.addChild(chest);

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, 0.0F, 0.0F);
		chest.addChild(torso);
		setRotationAngle(torso, 0.7854F, 0.0F, 0.0F);
		torso.texOffs(0, 0).addBox(-8.0F, -9.0F, -9.0F, 16.0F, 9.0F, 10.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-8.0F, -19.0F, 1.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 0.0F, 0.0436F);
		rightArm.texOffs(64, 60).addBox(-7.0F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, 0.0F, false);
		rightArm.texOffs(0, 73).addBox(-5.0F, 6.0F, -3.0F, 4.0F, 12.0F, 6.0F, 0.0F, false);
		rightArm.texOffs(28, 19).addBox(-6.0F, 18.0F, -4.0F, 6.0F, 18.0F, 8.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(8.0F, -19.0F, 1.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, -0.0436F);
		leftArm.texOffs(56, 23).addBox(0.0F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, 0.0F, false);
		leftArm.texOffs(72, 39).addBox(1.0F, 6.0F, -3.0F, 4.0F, 12.0F, 6.0F, 0.0F, false);
		leftArm.texOffs(0, 19).addBox(0.0F, 18.0F, -4.0F, 6.0F, 18.0F, 8.0F, 0.0F, false);}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.leftLeg.xRot = -1.5F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.xRot = 1.5F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int attackTimer = entityIn.getAttackTimer();
		if (attackTimer > 0) {
			this.rightArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float) attackTimer - partialTick, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * MathHelper.triangleWave((float) attackTimer - partialTick, 10.0F);
		}
		else {
			this.rightArm.xRot = (-0.2F + 1.5F * MathHelper.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftArm.xRot = (-0.2F - 1.5F * MathHelper.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableSet.of(forgottenGuardian);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}