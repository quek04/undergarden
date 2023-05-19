package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.rotspawn.Rotbeast;

public class RotbeastModel<T extends Rotbeast> extends ListModel<T> {

	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart torso;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public RotbeastModel(ModelPart root) {
		this.head = root.getChild("head");
		this.jaw = head.getChild("jaw");
		this.torso = root.getChild("torso");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(15.0F, 24.0F, 0.0F));

		PartDefinition lowerTorso = torso.addOrReplaceChild("lowerTorso", CubeListBuilder.create().texOffs(50, 46).addBox(-13.0F, -14.25F, -6.0F, 26.0F, 14.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(-15.0F, -16.75F, 5.5F));

		PartDefinition upperTorso = lowerTorso.addOrReplaceChild("upperTorso", CubeListBuilder.create().texOffs(32, 91).addBox(-15.0F, -19.0F, -8.5F, 30.0F, 19.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.75F, -0.5F, 0.48F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(56, 10).mirror().addBox(-3.5F, 0.0F, -4.0F, 9.0F, 19.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 5.0F, 5.5F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(56, 10).addBox(-5.5F, 0.0F, -4.0F, 9.0F, 19.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 5.0F, 5.5F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(80, 26).addBox(-6.0F, -6.5F, -13.0F, 12.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.5F, -7.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 15).addBox(-6.0F, -0.5F, -12.0F, 12.0F, 5.0F, 12.0F, new CubeDeformation(0.25F))
				.texOffs(0, 1).addBox(-6.0F, -3.0F, -12.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 2.0F, -1.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(2, 86).addBox(-7.0F, -1.0F, -4.0F, 7.0F, 34.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-15.0F, -17.0F, 2.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(2, 86).mirror().addBox(0.0F, -1.0F, -4.0F, 7.0F, 34.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(15.0F, -17.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		this.rightArm.zRot = 0.0F;
		this.leftArm.zRot = 0.0F;
		AnimationUtils.bobArms(this.rightArm, this.leftArm, ageInTicks);

		this.jaw.xRot = entity.isAggressive() ? 0.3491F : 0.0F;

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int attackTimer = entityIn.getAttackTimer();
		if (attackTimer > 0) {
			this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTick, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTick, 10.0F);
		} else {
			this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.head, this.torso, this.rightLeg, this.leftLeg, this.rightArm, this.leftArm);
	}
}