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

public class RotwalkerModel extends EntityModel<ArmedEntityRenderState> {

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public RotwalkerModel(ModelPart root) {
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

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.0F, -4.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition upperJaw = head.addOrReplaceChild("upperJaw", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.5F, -8.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(25, 6).addBox(-4.0F, -0.5F, -8.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 4.0F));

		PartDefinition lowerJaw = head.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(22, 19).addBox(-4.0F, 0.0F, -8.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 4.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 27).addBox(-4.0F, -8.0F, -1.0F, 8.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(-5.0F, -16.0F, -2.0F, 10.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(22, 29).mirror().addBox(0.0F, -1.0F, -1.5F, 2.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -6.0F, -2.5F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(22, 29).addBox(-2.0F, -1.0F, -1.5F, 2.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -6.0F, -2.5F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 39).mirror().addBox(-0.9F, 0.0F, -2.0F, 2.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 8.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 39).addBox(-1.1F, 0.0F, -2.0F, 2.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 8.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(ArmedEntityRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = 0.0873F + state.xRot * Mth.DEG_TO_RAD;

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
			this.rightArm.z = Mth.sin(this.body.yRot) * 6.0F * ageScale - 2.5F;
			this.rightArm.x = -Mth.cos(this.body.yRot) * 7.0F * ageScale;
			this.leftArm.z = -Mth.sin(this.body.yRot) * 6.0F * ageScale - 2.5F;
			this.leftArm.x = Mth.cos(this.body.yRot) * 7.0F * ageScale;
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