package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class BruteModel extends EntityModel<LivingEntityRenderState> {

	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public BruteModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.leftArm = root.getChild("left_arm");
		this.rightArm = root.getChild("right_arm");
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition upper_torso = partdefinition.addOrReplaceChild("upper_torso", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-7.0F, -7.5F, -4.0F, 14.0F, 15.0F, 8.0F)
				.texOffs(36, 37).addBox(0.0F, -10.5F, 1.0F, 0.0F, 18.0F, 9.0F),
			PartPose.offsetAndRotation(0.0F, -3.5F, 1.0F, 0.8727F, 0.0F, 0.0F));

		upper_torso.addOrReplaceChild("lower_torso", CubeListBuilder.create()
				.texOffs(36, 29).addBox(-6.0F, -5.9973F, -2.76F, 12.0F, 12.0F, 5.0F)
				.texOffs(46, 60).addBox(0.0F, -5.9973F, 2.24F, 0.0F, 9.0F, 5.0F),
			PartPose.offsetAndRotation(0.0F, 10.4973F, -1.24F, -0.5236F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-5.0F, -6.0F, -8.0F, 10.0F, 6.0F, 9.0F)
				.texOffs(44, 0).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 3.0F, 9.0F),
			PartPose.offset(0.0F, -4.0F, -3.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create()
				.texOffs(62, 57).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F)
				.texOffs(16, 58).addBox(3.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F),
			PartPose.offsetAndRotation(0.0F, 4.0F, -6.0F, 1.2217F, 0.0F, 0.0F));

		horns.addOrReplaceChild("horns2", CubeListBuilder.create()
				.texOffs(30, 64).addBox(-5.5F, 1.0F, -5.0F, 2.0F, 2.0F, 6.0F)
				.texOffs(36, 0).addBox(3.5F, 1.0F, -5.0F, 2.0F, 2.0F, 6.0F),
			PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.texOffs(18, 23).addBox(0.0F, -4.0F, -3.0F, 4.0F, 30.0F, 5.0F),
			PartPose.offset(7.0F, -2.0F, -1.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(0, 23).addBox(-4.0F, -4.0F, -3.0F, 4.0F, 30.0F, 5.0F),
			PartPose.offset(-7.0F, -2.0F, -1.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(0, 58).addBox(-1.8F, -1.0F, -2.0F, 4.0F, 15.0F, 4.0F),
			PartPose.offset(4.0F, 10.0F, 10.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(54, 46).addBox(-2.2F, -1.0F, -2.0F, 4.0F, 15.0F, 4.0F),
			PartPose.offset(-4.0F, 10.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public static LayerDefinition createBaby() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("torso", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-5.0F, -6.0F, -3.0F, 10.0F, 14.0F, 6.0F)
				.texOffs(46, -3).addBox(0.0F, -7.0F, 2.0F, 0.0F, 14.0F, 3.0F),
			PartPose.offsetAndRotation(0.0F, 10.0F, 1.0F, 1.1345F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 7.0F),
			PartPose.offset(0.0F, 9.0F, -3.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(0, 33).addBox(-1.7F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F),
			PartPose.offset(-3.5F, 13.0F, 6.5F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(12, 33).addBox(-1.3F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F),
			PartPose.offset(3.5F, 13.0F, 6.5F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(26, 20).addBox(-3.0F, -2.0F, -2.0F, 3.0F, 16.0F, 4.0F),
			PartPose.offset(-5.0F, 10.0F, -2.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.texOffs(32, 0).addBox(0.0F, -2.0F, -2.0F, 3.0F, 16.0F, 4.0F),
			PartPose.offset(5.0F, 10.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;

		this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
		this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;

		this.leftArm.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;
		this.rightArm.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;
	}
}