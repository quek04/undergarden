package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.entity.DwellerEntity;

public class DwellerModel<T extends DwellerEntity> extends AgeableListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("undergarden", "dweller"), "main");
	private final ModelPart torso;
	private final ModelPart mane;
	private final ModelPart head;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public DwellerModel(ModelPart root) {
		this.torso = root.getChild("torso");
		this.mane = root.getChild("mane");
		this.head = root.getChild("head");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer(float torsoInflate) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 23).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 8.0F, 15.0F, new CubeDeformation(torsoInflate)), PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition mane = torso.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(41, 10).addBox(-1.0F, -6.0F, -8.0F, 2.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -3.0F, -8.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(52, 57).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 9.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 46).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

		PartDefinition trunk = head.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(39, 26).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, -1.0F, 2.618F, 0.0F, 0.0F));

		PartDefinition trunk2 = trunk.addOrReplaceChild("trunk2", CubeListBuilder.create().texOffs(39, 0).addBox(-1.0F, 0.0F, -7.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -9.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(52, 40).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 5.0F, 6.0F));

		PartDefinition rightFoot = rightLeg.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 9.0F, 3.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(32, 46).addBox(0.0F, -2.0F, -3.0F, 4.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 5.0F, 6.0F));

		PartDefinition leftFoot = leftLeg.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(58, 0).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 9.0F, 3.0F, 0.6109F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = -1.3963F + headPitch * ((float)Math.PI / 180F);

		this.leftLeg.xRot = -0.6109F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.rightLeg.xRot = -0.6109F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.head, this.torso, this.rightLeg, this.leftLeg);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
		this.mane.visible = !entity.isSaddled();
		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
	}
}