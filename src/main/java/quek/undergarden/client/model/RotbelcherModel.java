package quek.undergarden.client.model;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.model.animation.RotbelcherAnimation;
import quek.undergarden.client.state.entity.RotbelcherRenderState;

public class RotbelcherModel extends EntityModel<RotbelcherRenderState> {

	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart head;

	private final KeyframeAnimation attackAnimation;
	private final KeyframeAnimation shootAnimation;

	public RotbelcherModel(ModelPart root) {
		super(root);
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		ModelPart torso = root.getChild("torso");
		this.rightArm = torso.getChild("rightArm");
		this.leftArm = torso.getChild("leftArm");
		this.head = torso.getChild("head");

		this.attackAnimation = RotbelcherAnimation.ATTACK.bake(root);
		this.shootAnimation = RotbelcherAnimation.SHOOT.bake(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(42, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 4.0F, 0.0F));

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 1.0F));

		PartDefinition cube_r1 = torso.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 27).addBox(-5.0F, -14.0F, -2.0F, 10.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition gut = torso.addOrReplaceChild("gut", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -1.0F));

		PartDefinition rightArm = torso.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -14.0F, -2.0F));

		PartDefinition leftArm = torso.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -14.0F, -2.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, -1.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition cube_r3 = jaw.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 3.0F, -4.0F, 0.4363F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(RotbelcherRenderState state) {
		super.setupAnim(state);

		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = 0.0873F + state.xRot * Mth.DEG_TO_RAD;

		if (!state.isCharging) {
			this.rightArm.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 2.0F * state.walkAnimationSpeed * 0.5F;
			this.leftArm.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 2.0F * state.walkAnimationSpeed * 0.5F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			AnimationUtils.bobArms(this.rightArm, this.leftArm, state.ageInTicks);
		}

		this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
		this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;

		this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
		this.shootAnimation.apply(state.shootAnimationState, state.ageInTicks);
	}
}