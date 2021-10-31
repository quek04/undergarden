package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.entity.boss.ForgottenGuardianEntity;

public class ForgottenGuardianModel<T extends ForgottenGuardianEntity> extends ListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("undergarden", "forgotten_guardian"), "main");
	//private final ModelPart forgottenGuardian;
	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public ForgottenGuardianModel(ModelPart root) {
		//this.forgottenGuardian = root.getChild("forgottenGuardian");
		this.head = root.getChild("head");
		this.torso = root.getChild("torso");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		//PartDefinition forgottenGuardian = partdefinition.addOrReplaceChild("forgottenGuardian", CubeListBuilder.create(), PartPose.offset(0.0F, -23.0F, 6.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(20, 73).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 45).addBox(-2.0F, 11.0F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 14.0F, -6.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(67, 76).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(24, 45).addBox(-4.0F, 11.0F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 14.0F, -6.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(48, 11).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(48, 45).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -6.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(41, 69).addBox(-3.0F, -13.0F, -5.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(76, 0).addBox(-3.0F, -13.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(42, 0).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 1.0F));

		//PartDefinition chest = body.addOrReplaceChild("chest", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 6.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -9.0F, -9.0F, 16.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(64, 60).addBox(-7.0F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 73).addBox(-5.0F, 6.0F, -3.0F, 4.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(28, 19).addBox(-6.0F, 18.0F, -4.0F, 6.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -19.0F, 1.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(56, 23).addBox(0.0F, -2.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(72, 39).addBox(1.0F, 6.0F, -3.0F, 4.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(0.0F, 18.0F, -4.0F, 6.0F, 18.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -19.0F, 1.0F, 0.0F, 0.0F, -0.0436F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.leftLeg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int attackTimer = entityIn.getAttackTimer();
		if (attackTimer > 0) {
			this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTick, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTick, 10.0F);
		}
		else {
			this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.head, this.torso, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
	}
}