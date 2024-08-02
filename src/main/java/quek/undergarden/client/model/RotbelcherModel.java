package quek.undergarden.client.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.model.animation.RotbelcherAnimation;
import quek.undergarden.entity.monster.rotspawn.Rotbelcher;

public class RotbelcherModel<T extends Rotbelcher> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart torso;
	private final ModelPart gut;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart head;
	private final ModelPart jaw;

	public RotbelcherModel(ModelPart root) {
		this.root = root.getChild("root");
		this.rightLeg = this.root.getChild("rightLeg");
		this.leftLeg = this.root.getChild("leftLeg");
		this.torso = this.root.getChild("torso");
		this.gut = this.torso.getChild("gut");
		this.rightArm = this.torso.getChild("rightArm");
		this.leftArm = this.torso.getChild("leftArm");
		this.head = this.torso.getChild("head");
		this.jaw = this.head.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition rightLeg = root.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(42, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.0F, 0.0F));

		PartDefinition leftLeg = root.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 4.0F, 0.0F));

		PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 1.0F));

		PartDefinition cube_r1 = torso.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 27).addBox(-5.0F, -14.0F, -2.0F, 10.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition gut = torso.addOrReplaceChild("gut", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -1.0F));

		PartDefinition rightArm = torso.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -14.0F, -2.0F));

		PartDefinition leftArm = torso.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -14.0F, -2.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, -1.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition cube_r3 = jaw.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -3.0F, 0.4363F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Rotbelcher entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root.getAllParts().forEach(ModelPart::resetPose);

		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = 0.0873F + headPitch * ((float) Math.PI / 180F);

		if (!entity.isCharging()) {
			this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
			this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			AnimationUtils.bobArms(this.rightArm, this.leftArm, ageInTicks);
		}

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		this.animate(entity.attackAnimation, RotbelcherAnimation.ATTACK, ageInTicks);
		this.animate(entity.shootAnimation, RotbelcherAnimation.SHOOT, ageInTicks);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}