package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.state.entity.DwellerRenderState;

public class DwellerModel extends EntityModel<DwellerRenderState> {

	private final ModelPart mane;
	private final ModelPart head;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public DwellerModel(ModelPart root) {
		super(root);
		ModelPart torso = root.getChild("torso");
		this.mane = torso.getChild("mane");
		this.head = root.getChild("head");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition create(float torsoInflate) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create()
				.texOffs(0, 23).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 8.0F, 15.0F, new CubeDeformation(torsoInflate)),
			PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, -0.4363F, 0.0F, 0.0F));

		torso.addOrReplaceChild("mane", CubeListBuilder.create()
				.texOffs(41, 10).addBox(-1.0F, -6.0F, -8.0F, 2.0F, 3.0F, 13.0F)
				.texOffs(0, 0).addBox(-1.0F, -3.0F, -8.0F, 2.0F, 4.0F, 2.0F),
			PartPose.ZERO);

		torso.addOrReplaceChild("tail", CubeListBuilder.create()
				.texOffs(52, 57).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 2.0F),
			PartPose.offsetAndRotation(0.0F, -3.0F, 9.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 46).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 8.0F, 6.0F),
			PartPose.offset(0.0F, 1.0F, -2.0F));

		PartDefinition trunk = head.addOrReplaceChild("trunk", CubeListBuilder.create()
				.texOffs(39, 26).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 9.0F),
			PartPose.offsetAndRotation(0.0F, 8.0F, -1.0F, 2.618F, 0.0F, 0.0F));

		trunk.addOrReplaceChild("trunk2", CubeListBuilder.create()
				.texOffs(39, 0).addBox(-1.0F, 0.0F, -7.0F, 2.0F, 2.0F, 7.0F),
			PartPose.offsetAndRotation(0.0F, 1.0F, -9.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(52, 40).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 11.0F, 6.0F),
			PartPose.offset(-5.0F, 5.0F, 6.0F));

		rightLeg.addOrReplaceChild("right_foot", CubeListBuilder.create()
				.texOffs(0, 60).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 13.0F, 3.0F),
			PartPose.offsetAndRotation(-2.0F, 9.0F, 3.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(32, 46).addBox(0.0F, -2.0F, -3.0F, 4.0F, 11.0F, 6.0F),
			PartPose.offset(5.0F, 5.0F, 6.0F));

		leftLeg.addOrReplaceChild("left_foot", CubeListBuilder.create()
				.texOffs(58, 0).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 13.0F, 3.0F),
			PartPose.offsetAndRotation(2.0F, 9.0F, 3.0F, 0.6109F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public static LayerDefinition createBaby() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -3.0F, -2.0F, 8.0F, 6.0F, 11.0F),
			PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		torso.addOrReplaceChild("mane", CubeListBuilder.create(), PartPose.ZERO);

		torso.addOrReplaceChild("tail", CubeListBuilder.create()
				.texOffs(14, 29).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 2.0F),
			PartPose.offsetAndRotation(0.0F, -3.0F, 9.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(26, 17).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 5.0F),
			PartPose.offsetAndRotation(0.0F, 7.5F, -0.5F, -1.1345F, 0.0F, 0.0F));

		PartDefinition trunk = head.addOrReplaceChild("trunk", CubeListBuilder.create()
				.texOffs(0, 17).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 9.0F),
			PartPose.offsetAndRotation(0.0F, 6.0F, -0.5F, 2.7925F, 0.0F, 0.0F));

		trunk.addOrReplaceChild("trunk2", CubeListBuilder.create()
				.texOffs(26, 28).addBox(-1.0F, 0.5F, -7.5F, 2.0F, 2.0F, 7.0F),
			PartPose.offsetAndRotation(0.0F, 0.5F, -9.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(14, 37).addBox(0.0F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F),
			PartPose.offsetAndRotation(3.0F, 12.0F, 4.0F, -0.6109F, 0.0F, 0.0F));

		left_leg.addOrReplaceChild("left_foot", CubeListBuilder.create()
				.texOffs(34, 37).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 9.0F, 2.0F),
			PartPose.offsetAndRotation(2.0F, 6.0F, 2.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(0, 29).addBox(-3.0F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F),
			PartPose.offsetAndRotation(-3.0F, 12.0F, 4.0F, -0.6109F, 0.0F, 0.0F));

		right_leg.addOrReplaceChild("right_foot", CubeListBuilder.create()
				.texOffs(28, 37).addBox(0.0F, -3.0F, 0.0F, 1.0F, 9.0F, 2.0F),
			PartPose.offsetAndRotation(-2.0F, 6.0F, 2.0F, 0.6109F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(DwellerRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = -1.3963F + state.xRot * Mth.DEG_TO_RAD;

		this.leftLeg.xRot = -0.6109F + Mth.cos(state.walkAnimationPos * 0.6662F) * 0.66F * state.walkAnimationSpeed;
		this.rightLeg.xRot = -0.6109F + Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 0.66F * state.walkAnimationSpeed;
		this.mane.visible = state.saddle.isEmpty();
	}
}