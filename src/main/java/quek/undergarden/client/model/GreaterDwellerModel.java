package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.animal.GreaterDweller;

public class GreaterDwellerModel<T extends GreaterDweller> extends AgeableListModel<T> {

	private final ModelPart head;
	private final ModelPart trunk;
	private final ModelPart torso;
	private final ModelPart tail;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public GreaterDwellerModel(ModelPart root) {
		this.head = root.getChild("head");
		this.trunk = head.getChild("trunk");
		this.torso = root.getChild("torso");
		this.tail = torso.getChild("tail");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, -21.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -10.0F, -9.0F, 14.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition trunk = head.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(0, 21).addBox(-4.0F, -2.0F, -8.0F, 8.0F, 20.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.0F));

		PartDefinition cube_r2 = trunk.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 70).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 31.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r3 = trunk.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 50).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -3.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, -13.6242F, -7.7995F));

		PartDefinition cube_r4 = torso.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(104, 45).addBox(-2.0F, -12.0F, -8.0F, 4.0F, 16.0F, 37.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.3758F, -12.2005F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r5 = torso.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 50).addBox(-10.0F, -5.0F, -16.0F, 20.0F, 16.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6242F, 1.7995F, -0.2618F, 0.0F, 0.0F));

		PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 98).addBox(-8.0F, 0.0F, -2.0F, 16.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.6242F, 17.7995F));

		PartDefinition cube_r6 = tail.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 112).addBox(-4.0F, -2.0F, -1.0F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, -1.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(50, 0).mirror().addBox(-2.0F, 19.0F, 5.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, -11.0F, -2.0F));

		PartDefinition cube_r7 = leftLeg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(110, 0).mirror().addBox(-3.0F, -24.0F, -5.0F, 6.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 20.0F, -5.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r8 = leftLeg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(-3.0F, -5.0F, -14.0F, 6.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 20.0F, 7.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, 19.0F, 5.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -11.0F, -2.0F));

		PartDefinition cube_r9 = rightLeg.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(110, 0).addBox(-3.0F, -24.0F, -5.0F, 6.0F, 25.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 20.0F, -5.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r10 = rightLeg.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(66, 0).addBox(-3.0F, -5.0F, -14.0F, 6.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 7.0F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		int attackTimer = entity.getAttackTimer();
		if (attackTimer == 0) {
			this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
			this.head.xRot = 0.1745F + headPitch * ((float) Math.PI / 180F);
		}

		this.leftLeg.xRot = -0.2618F + Mth.cos(limbSwing * 0.6662F) * 0.66F * limbSwingAmount;

		this.rightLeg.xRot = -0.2618F + Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.66F * limbSwingAmount;
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
		int attackTimer = entity.getAttackTimer();
		if (attackTimer > 0) {
			this.head.xRot = 1.5F * Mth.triangleWave((float) attackTimer - partialTick, 10.0F);
		}
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.head, this.torso, this.rightLeg, this.leftLeg);
	}
}