package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.monster.boss.ForgottenGuardian;

public class ForgottenGuardianModel<T extends ForgottenGuardian> extends ListModel<T> {

	private final ModelPart head;
	private final ModelPart body;
	//private final ModelPart torso;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public ForgottenGuardianModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		//this.torso = body.getChild("torso");
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
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.leftLeg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		int attackTimer = entity.getAttackTimer();
		if (attackTimer > 0) {
			this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTicks, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTicks, 10.0F);
		} else {
			this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.body, this.leftLeg, this.rightLeg);
	}
}