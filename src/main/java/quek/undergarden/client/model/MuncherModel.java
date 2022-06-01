package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.cavern.Muncher;

public class MuncherModel<T extends Muncher> extends ListModel<T> {

	private final ModelPart lowerJaw;
	private final ModelPart upperJaw;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart leftArm;
	private final ModelPart rightArm;

	public MuncherModel(ModelPart root) {
		this.lowerJaw = root.getChild("lowerJaw");
		this.upperJaw = root.getChild("upperJaw");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition lowerJaw = partdefinition.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 29).addBox(-8.0F, -5.0F, 3.0F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition upperJaw = partdefinition.addOrReplaceChild("upperJaw", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).addBox(0.0F, -6.0F, -6.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 5.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(30, 15).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 20.0F, 1.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 20.0F, 1.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 18.0F, 2.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 18.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.upperJaw.xRot = (Mth.sin((entity.tickCount) * 0.5F) * 0.9F) * 0.3F;

		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.lowerJaw, this.upperJaw, this.leftLeg, this.rightLeg, this.leftArm, this.rightArm);
	}
}