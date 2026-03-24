package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.state.entity.ForgottenGuardianRenderState;

public class ForgottenGuardianModel extends EntityModel<ForgottenGuardianRenderState> {

	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public ForgottenGuardianModel(ModelPart root) {
		super(root);
		ModelPart body = root.getChild("body");
		this.head = body.getChild("head");
		this.leftArm = body.getChild("leftArm");
		this.rightArm = body.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(48, 11).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(48, 45).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(41, 69).addBox(-3.0F, -13.0F, -5.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(76, 0).addBox(-3.0F, -13.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(42, 0).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 1.0F));

		PartDefinition chest = body.addOrReplaceChild("chest", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 6.0F));

		PartDefinition torso = chest.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -9.0F, -9.0F, 16.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(64, 60).addBox(-7.0F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 73).addBox(-5.0F, 6.0F, -3.0F, 4.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(28, 19).addBox(-6.0F, 18.0F, -4.0F, 6.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -19.0F, 1.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(56, 23).addBox(0.0F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(72, 39).addBox(1.0F, 6.0F, -3.0F, 4.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(0.0F, 18.0F, -4.0F, 6.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -19.0F, 1.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(67, 76).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(24, 45).addBox(-4.0F, 11.0F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(20, 73).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 45).addBox(-2.0F, 11.0F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(ForgottenGuardianRenderState state) {
		super.setupAnim(state);
		if (state.isActive) {
			this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
			this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
		} else {
			this.head.xRot = (float) Math.toRadians(30.0D);
		}
		this.leftLeg.xRot = -1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F) * state.walkAnimationSpeed;
		this.rightLeg.xRot = 1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F) * state.walkAnimationSpeed;

		float attackTimer = state.attackTimer;
		if (attackTimer > 0) {
			this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave(attackTimer, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave(attackTimer, 10.0F);
		} else {
			this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F)) * state.walkAnimationSpeed;
			this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F)) * state.walkAnimationSpeed;
		}
	}
}