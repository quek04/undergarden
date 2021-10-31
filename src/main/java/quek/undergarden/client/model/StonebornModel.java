package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class StonebornModel<T extends StonebornEntity> extends AgeableListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("undergarden", "stoneborn"), "main");
	//private final ModelPart stoneborn;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public StonebornModel(ModelPart root) {
		//this.stoneborn = root.getChild("stoneborn");
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		//PartDefinition stoneborn = partdefinition.addOrReplaceChild("stoneborn", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(32, 8).addBox(-7.0F, -10.0F, 0.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(32, 12).addBox(7.0F, -13.0F, 0.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 12).addBox(-9.0F, -15.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 12).addBox(4.0F, -8.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -29.0F, -1.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.75F, -3.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 26).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 1.0F));

		PartDefinition robe = body.addOrReplaceChild("robe", CubeListBuilder.create().texOffs(40, 21).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -26.5F, 1.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-1.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.0F, -26.5F, 1.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(16, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(70, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.24F)), PartPose.offset(-3.0F, -15.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(70, 48).mirror().addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, new CubeDeformation(0.24F)).mirror(false), PartPose.offset(3.0F, -15.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 88, 67);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableSet.of(this.head, this.body, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
	}
}