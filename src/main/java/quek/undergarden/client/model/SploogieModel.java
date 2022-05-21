package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.entity.cavern.Sploogie;

public class SploogieModel<T extends Sploogie> extends ListModel<T> {

	private final ModelPart head;
	private final ModelPart bodySegment1;
	private final ModelPart bodySegment2;
	private final ModelPart bodySegment3;

	public SploogieModel(ModelPart root) {
		this.head = root.getChild("head");
		this.bodySegment1 = root.getChild("bodySegment1");
		this.bodySegment2 = root.getChild("bodySegment2");
		this.bodySegment3 = root.getChild("bodySegment3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 30).addBox(4.0F, -2.0F, -9.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 30).mirror().addBox(-8.0F, -2.0F, -9.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).addBox(-5.0F, -4.0F, -8.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition spine1 = head.addOrReplaceChild("spine1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -7.0F, 0.0F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(12, 34).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -8.0F));

		PartDefinition bodySegment1 = partdefinition.addOrReplaceChild("bodySegment1", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition spine2 = bodySegment1.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 3.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition bodySegment2 = partdefinition.addOrReplaceChild("bodySegment2", CubeListBuilder.create().texOffs(24, 24).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 7.0F));

		PartDefinition spine3 = bodySegment2.addOrReplaceChild("spine3", CubeListBuilder.create().texOffs(23, 16).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 3.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition bodySegment3 = partdefinition.addOrReplaceChild("bodySegment3", CubeListBuilder.create().texOffs(30, 16).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 13.0F));

		PartDefinition spine4 = bodySegment3.addOrReplaceChild("spine4", CubeListBuilder.create().texOffs(23, 20).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, -1.309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.head.x = Mth.cos(limbSwing * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + limbSwingAmount);

		this.bodySegment1.yRot = Mth.cos(limbSwing * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + limbSwingAmount);
		this.bodySegment2.yRot = Mth.cos(limbSwing * 0.9F + 1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(1 - 2) + limbSwingAmount);
		this.bodySegment3.yRot = Mth.cos(limbSwing * 0.9F + 0 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(-2) + limbSwingAmount);

		this.bodySegment1.x = Mth.cos(limbSwing * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + limbSwingAmount);
		this.bodySegment2.x = Mth.cos(limbSwing * 0.9F + 1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(1 - 2) + limbSwingAmount);
		this.bodySegment3.x = Mth.cos(limbSwing * 0.9F + 0 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(-2) + limbSwingAmount);
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(this.head, this.bodySegment1, this.bodySegment2, this.bodySegment3);
	}
}