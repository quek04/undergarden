package quek.undergarden.client.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.util.Ease;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import quek.undergarden.client.state.entity.StonebornRenderState;

public class StonebornModel extends EntityModel<StonebornRenderState> {

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public StonebornModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(32, 8).addBox(-7.0F, -10.0F, 0.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(32, 12).addBox(7.0F, -13.0F, 0.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 12).addBox(-9.0F, -15.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 12).addBox(4.0F, -8.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -1.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.75F, -3.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 26).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));

		PartDefinition robe = body.addOrReplaceChild("robe", CubeListBuilder.create().texOffs(40, 21).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -2.5F, 1.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-1.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.0F, -2.5F, 1.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(16, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(70, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.24F)), PartPose.offset(-3.0F, 9.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(70, 48).mirror().addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.24F)).mirror(false), PartPose.offset(3.0F, 9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 88, 67);
	}

	@Override
	public void setupAnim(StonebornRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;

		this.setupAttackAnimation(state);
		if (state.attackTime <= 0.0F) {
			this.leftArm.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;
			this.rightArm.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
			this.rightArm.zRot = -0.0436F;
			this.leftArm.zRot = 0.0436F;
			AnimationUtils.bobArms(this.rightArm, this.leftArm, state.ageInTicks);
		}

		this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
		this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;
	}

	protected void setupAttackAnimation(ArmedEntityRenderState state) {
		float attackTime = state.attackTime;
		if (!(attackTime <= 0.0F)) {
			this.body.yRot = Mth.sin(Mth.sqrt(attackTime) * Mth.TWO_PI) * 0.2F;
			if (state.attackArm == HumanoidArm.LEFT) {
				this.body.yRot *= -1.0F;
			}

			float ageScale = 1.0F;
			this.rightArm.z = Mth.sin(this.body.yRot) * 12.0F * ageScale + 1.0F;
			this.rightArm.x = -Mth.cos(this.body.yRot) * 9.0F * ageScale;
			this.leftArm.z = -Mth.sin(this.body.yRot) * 12.0F * ageScale + 1.0F;
			this.leftArm.x = Mth.cos(this.body.yRot) * 10.0F * ageScale;
			this.rightArm.yRot = this.rightArm.yRot + this.body.yRot;
			this.leftArm.yRot = this.leftArm.yRot + this.body.yRot;
			this.leftArm.xRot = this.leftArm.xRot + this.body.yRot;

			float swing = Ease.outQuart(attackTime);
			float aa = Mth.sin(swing * Mth.PI);
			float bb = Mth.sin(attackTime * Mth.PI) * -(this.head.xRot - 0.7F) * 0.75F;
			ModelPart attackArm = state.attackArm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
			attackArm.xRot -= aa * 1.2F + bb;
			attackArm.yRot = attackArm.yRot + this.body.yRot * 2.0F;
			attackArm.zRot = attackArm.zRot + Mth.sin(attackTime * Mth.PI) * -0.4F;
		}
	}
}