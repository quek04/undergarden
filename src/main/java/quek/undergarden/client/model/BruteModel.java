package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.animal.Brute;

public class BruteModel<T extends Brute> extends AgeableListModel<T> {

	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public BruteModel(ModelPart root) {
		this.torso = root.getChild("uppertorso");
		this.head = root.getChild("head");
		this.leftArm = root.getChild("leftarm");
		this.rightArm = root.getChild("rightarm");
		this.leftLeg = root.getChild("leftleg");
		this.rightLeg = root.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition uppertorso = partdefinition.addOrReplaceChild("uppertorso", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -7.5F, -4.0F, 14.0F, 15.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(36, 37).addBox(0.0F, -10.5F, 1.0F, 0.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 1.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition lowertorso = uppertorso.addOrReplaceChild("lowertorso", CubeListBuilder.create().texOffs(36, 29).addBox(-6.0F, -5.9973F, -2.76F, 12.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(46, 60).addBox(0.0F, -5.9973F, 2.24F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.4973F, -1.24F, -0.5236F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 14).addBox(-5.0F, -6.0F, -8.0F, 10.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(44, 0).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -3.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create().texOffs(62, 57).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(16, 58).addBox(3.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -6.0F, 1.2217F, 0.0F, 0.0F));

		PartDefinition horns2 = horns.addOrReplaceChild("horns2", CubeListBuilder.create().texOffs(30, 64).addBox(-5.5F, 1.0F, -5.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(36, 0).addBox(3.5F, 1.0F, -5.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(18, 23).addBox(0.0F, -4.0F, -3.0F, 4.0F, 30.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -2.0F, -1.0F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 23).addBox(-4.0F, -4.0F, -3.0F, 4.0F, 30.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -2.0F, -1.0F));

		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 58).addBox(-1.8F, -1.0F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 10.0F, 10.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(54, 46).addBox(-2.2F, -1.0F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 10.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableSet.of(this.head, this.torso, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}