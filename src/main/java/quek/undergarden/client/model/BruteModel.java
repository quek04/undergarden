package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.BruteEntity;

public class BruteModel<T extends BruteEntity> extends AgeableListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, "brute"), "main");

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
				.texOffs(78, 22).addBox(0.0F, -7.5F, 4.0F, 0.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 1.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition lowertorso = uppertorso.addOrReplaceChild("lowertorso", CubeListBuilder.create().texOffs(0, 23).addBox(-6.0F, -5.9973F, -2.76F, 12.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(90, 29).addBox(0.0F, -5.9973F, 2.24F, 0.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.4973F, -1.24F, -0.5236F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(44, 0).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(45, 14).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -3.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create().texOffs(48, 41).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(48, 41).mirror().addBox(3.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, -6.0F, 1.2217F, 0.0F, 0.0F));

		PartDefinition horns2 = horns.addOrReplaceChild("horns2", CubeListBuilder.create().texOffs(32, 45).addBox(-5.5F, 1.0F, -5.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(32, 45).mirror().addBox(3.5F, 1.0F, -5.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(34, 23).addBox(0.0F, -4.0F, -3.0F, 3.0F, 17.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -2.0F, -1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition leftarm2 = leftarm.addOrReplaceChild("leftarm2", CubeListBuilder.create().texOffs(50, 24).addBox(0.1F, -2.0F, -2.5F, 3.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition lefthand = leftarm.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(64, 24).addBox(-1.0F, -4.5F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 24.0F, -9.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(34, 23).mirror().addBox(-3.0F, -4.0F, -3.0F, 3.0F, 17.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, -2.0F, -1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition rightarm2 = rightarm.addOrReplaceChild("rightarm2", CubeListBuilder.create().texOffs(50, 24).mirror().addBox(-0.1F, -2.0F, -2.5F, 3.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 13.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition righthand = rightarm.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(64, 24).mirror().addBox(-1.0F, -4.5F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 24.0F, -9.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 40).addBox(-1.9F, -1.0F, -1.7412F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 10.0F, 9.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition leftleg2 = leftleg.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(16, 40).addBox(-1.5F, -3.6274F, -3.7294F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 8.1933F, 2.8294F, 0.2618F, 0.0F, 0.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-2.1F, -1.0341F, -1.7412F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 10.0F, 9.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition rightleg2 = rightleg.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(16, 40).mirror().addBox(-1.5F, -3.6274F, -3.7294F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 8.1933F, 2.8294F, 0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
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

		this.leftLeg.xRot = -0.2618F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = -0.2618F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftArm.xRot = 0.2618F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = 0.2618F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}
}