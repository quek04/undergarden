package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.boss.MasticatorEntity;

public class MasticatorModel<T extends MasticatorEntity> extends ListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, "masticator"), "main");
	private final ModelPart head;
	private final ModelPart lowerJaw;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;

	public MasticatorModel(ModelPart root) {
		this.head = root.getChild("head");
		this.lowerJaw = head.getChild("lowerjaw");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftarm");
		this.rightArm = root.getChild("rightarm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(70, 46).addBox(7.0F, -6.0F, -4.0F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(51, 83).addBox(-7.0F, -6.0F, -4.0F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -9.0F));

		PartDefinition upperjaw = head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(0, 20).addBox(-6.0F, 0.0F, -17.0F, 12.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(51, 62).addBox(6.0F, 0.0F, -17.0F, 0.0F, 3.0F, 17.0F, new CubeDeformation(0.0F))
				.texOffs(51, 65).addBox(-6.0F, 0.0F, -17.0F, 0.0F, 3.0F, 17.0F, new CubeDeformation(0.0F))
				.texOffs(86, 0).addBox(-9.0F, -6.0F, -18.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(70, 51).addBox(-18.0F, -5.0F, -17.0F, 36.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 1.0F));

		PartDefinition lowerjaw = head.addOrReplaceChild("lowerjaw", CubeListBuilder.create().texOffs(112, 46).addBox(-5.0F, -3.0F, -16.0F, 10.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(88, 56).addBox(5.0F, -3.0F, -16.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(51, 69).addBox(-5.0F, -3.0F, -16.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(112, 24).addBox(-8.0F, 0.0F, -18.0F, 16.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));

		PartDefinition innerupperjaw = head.addOrReplaceChild("innerupperjaw", CubeListBuilder.create().texOffs(86, 0).addBox(-4.0F, -1.0F, -7.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 57).addBox(4.0F, -1.0F, -7.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 15).addBox(-4.0F, -1.0F, -7.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition innerlowerjaw = head.addOrReplaceChild("innerlowerjaw", CubeListBuilder.create().texOffs(86, 2).addBox(-4.0F, -1.0F, -8.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 59).addBox(4.0F, -1.0F, -8.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(70, 59).addBox(-4.0F, -1.0F, -8.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 1.0F));

		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 79).addBox(-1.0F, -9.0F, -10.0F, 16.0F, 19.0F, 19.0F, new CubeDeformation(0.0F))
				.texOffs(37, 141).addBox(0.0F, 9.8177F, -2.0838F, 14.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -29.0F, 7.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition lowerarm2 = leftarm.addOrReplaceChild("lowerarm2", CubeListBuilder.create().texOffs(0, 117).addBox(-8.0F, -1.0F, -5.5F, 15.0F, 21.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 26.8053F, 2.7052F, -0.6981F, 0.0F, 0.0F));

		PartDefinition frontfingers2 = lowerarm2.addOrReplaceChild("frontfingers2", CubeListBuilder.create().texOffs(140, 0).addBox(-6.5F, -1.0718F, -1.2052F, 14.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 20.0F, -4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition backfinger2 = frontfingers2.addOrReplaceChild("backfinger2", CubeListBuilder.create().texOffs(10, 145).addBox(-5.5F, -13.1232F, -1.1605F, 6.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 9.1736F, 9.0152F, 0.3491F, 0.0F, 0.0F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(51, 103).addBox(-15.0F, -9.0F, -10.0F, 16.0F, 19.0F, 19.0F, new CubeDeformation(0.0F))
				.texOffs(79, 141).addBox(-14.0F, 9.8177F, -2.0838F, 14.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -29.0F, 7.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition lowerarm = rightarm.addOrReplaceChild("lowerarm", CubeListBuilder.create().texOffs(121, 121).addBox(-7.0F, -1.0F, -5.5F, 15.0F, 21.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, 26.8053F, 2.7052F, -0.6981F, 0.0F, 0.0F));

		PartDefinition frontfingers = lowerarm.addOrReplaceChild("frontfingers", CubeListBuilder.create().texOffs(144, 67).addBox(-7.5F, -1.0718F, -1.2052F, 14.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 20.0F, -4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition backfinger = frontfingers.addOrReplaceChild("backfinger", CubeListBuilder.create().texOffs(121, 149).addBox(-0.5F, -13.1232F, -1.1605F, 6.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 9.1736F, 9.0152F, 0.3491F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(75, 75).addBox(-14.0F, -6.0F, -18.0F, 28.0F, 15.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(0, 51).addBox(-13.0F, 7.0F, -7.0F, 26.0F, 10.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -27.0F, 10.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -11.0F, 0.0F, 30.0F, 25.0F, 26.0F, new CubeDeformation(0.0F))
				.texOffs(88, 43).addBox(0.0F, -16.0F, 2.0F, 0.0F, 5.0F, 24.0F, new CubeDeformation(0.0F))
				.texOffs(0, 140).addBox(0.0F, -16.0F, 26.0F, 0.0F, 25.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -13.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition waist2 = body.addOrReplaceChild("waist2", CubeListBuilder.create().texOffs(102, 103).addBox(-10.0F, -4.2F, -1.5F, 20.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 17.0F, -4.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone2 = rightleg.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 51).addBox(-1.5F, -1.5111F, -1.1177F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 2.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(141, 149).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 17.0F, -4.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone = leftleg.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 79).addBox(-1.5F, -1.5111F, -1.1177F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 2.0F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		float chew = Mth.sin((entity.tickCount) * 0.3F) * 0.3F;
		this.lowerJaw.xRot = chew * 0.70F;

		this.leftArm.xRot = Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;

		this.rightArm.xRot = Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.head, this.body, this.leftArm, this.rightArm);
	}
}