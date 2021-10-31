package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.entity.cavern.NargoyleEntity;

public class NargoyleModel<T extends NargoyleEntity> extends ListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("undergarden", "nargoyle"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart legs;
	private final ModelPart jaw;

	public NargoyleModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.arms = root.getChild("arms");
		this.legs = root.getChild("legs");
		this.jaw = root.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.0F, -3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition upperbody = body.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.0F, 0.0F, 10.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 4.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition head = upperbody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(42, 51).addBox(-4.0F, -9.5F, -2.0F, 8.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(16, 28).addBox(-3.0F, -8.5F, -4.0F, 6.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(45, 49).addBox(-2.0F, -9.5F, 1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, 3.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(34, 35).addBox(-4.0F, -7.5F, -1.0F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 0).addBox(-2.0F, -6.5F, 0.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition ear1 = head.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(0, 54).addBox(-5.0F, -1.0F, -1.0F, 3.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition ear2 = head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(-1.5F, 0.0F, -6.0F, 3.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -1.0F, 5.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition arms = upperbody.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.4927F, 3.3309F, 1.5708F, 0.0F, 0.0F));

		PartDefinition upperarmleft = arms.addOrReplaceChild("upperarmleft", CubeListBuilder.create().texOffs(34, 0).addBox(5.0F, -9.9927F, -0.9691F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0073F, -0.0309F, 0.8727F, 0.0F, 0.0F));

		PartDefinition lowerarmleft = upperarmleft.addOrReplaceChild("lowerarmleft", CubeListBuilder.create().texOffs(34, 43).addBox(-1.5F, -14.7194F, -1.2086F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -9.9927F, 1.0309F, -1.1345F, 0.0F, 0.0F));

		PartDefinition upperarmright = arms.addOrReplaceChild("upperarmright", CubeListBuilder.create().texOffs(34, 0).mirror().addBox(-8.0F, -9.9927F, -0.6691F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.0073F, -0.3309F, 0.8727F, 0.0F, 0.0F));

		PartDefinition lowerarmright = upperarmright.addOrReplaceChild("lowerarmright", CubeListBuilder.create().texOffs(34, 43).mirror().addBox(-0.5F, -14.7194F, -1.2086F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, -9.9927F, 1.3309F, -1.1345F, 0.0F, 0.0F));

		PartDefinition lowerbody = body.addOrReplaceChild("lowerbody", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.0F, 5.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition hips = lowerbody.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(21, 21).addBox(-3.0F, -4.0F, 2.0F, 6.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.7189F, -1.2679F, 0.48F, 0.0F, 0.0F));

		PartDefinition legs = lowerbody.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.527F, 6.4479F, -1.7017F, 0.0F, 0.0F));

		PartDefinition upperrightleg = legs.addOrReplaceChild("upperrightleg", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-5.0F, -9.027F, -1.0521F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.027F, -0.9479F, -0.8727F, 0.0F, 0.0F));

		PartDefinition lowerrightleg = upperrightleg.addOrReplaceChild("lowerrightleg", CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, -8.8618F, -0.8628F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -8.2609F, -0.6949F, 1.2217F, 0.0F, 0.0F));

		PartDefinition upperleftleg = legs.addOrReplaceChild("upperleftleg", CubeListBuilder.create().texOffs(0, 18).addBox(3.0F, -9.027F, -1.0521F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.027F, -0.9479F, -0.8727F, 0.0F, 0.0F));

		PartDefinition lowerleftleg = upperleftleg.addOrReplaceChild("lowerleftleg", CubeListBuilder.create().texOffs(0, 31).addBox(0.5F, -8.8618F, -0.8628F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -8.2609F, -0.6949F, 1.2217F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.zRot = headPitch * ((float)Math.PI / 180F);
		this.head.xRot = 0.4363F + headPitch * ((float)Math.PI / 180F);

		this.arms.xRot = 1.5708F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.legs.xRot = -1.7017F + -(Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);

		if(entity.isAggressive()) {
			this.jaw.xRot = 0.3491F;
		}
		else {
			this.jaw.xRot = 0.0F;
		}
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(body);
	}
}