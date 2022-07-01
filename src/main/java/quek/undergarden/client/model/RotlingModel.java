package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.rotspawn.Rotling;

public class RotlingModel<T extends Rotling> extends ListModel<T> {

	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public RotlingModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.rightLeg = body.getChild("rightLeg");
		this.leftLeg = body.getChild("leftLeg");
		this.rightArm = body.getChild("rightArm");
		this.leftArm = body.getChild("leftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -10.5F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 38).addBox(-4.0F, -12.5F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.5F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.5F, 4.0F));

		PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(24, 2).addBox(0.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -3.5F, 1.0F));

		PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(24, 2).mirror().addBox(-3.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -3.5F, 1.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -10.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.2817F, 0.0977F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -11.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = entity.isAggressive() ? -0.5235F : -0.1745F;

		this.leftArm.xRot = entity.isAggressive() ? -1.5F : Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = entity.isAggressive() ? -1.5F : Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightArm.zRot = 0.0F;
		this.leftArm.zRot = 0.0F;
		AnimationUtils.bobArms(this.rightArm, this.leftArm, ageInTicks);

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(body);
	}
}