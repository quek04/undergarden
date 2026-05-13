package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class UndergarModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart head;
	private final ModelPart topJaw;
	private final ModelPart lowerJaw;
	private final ModelPart body;
	private final ModelPart tail;

	public UndergarModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.topJaw = this.head.getChild("topJaw");
		this.lowerJaw = this.head.getChild("lowerJaw");
		this.body = root.getChild("body");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(38, 64).addBox(-4.0F, -3.5F, -8.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, -10.0F));

		PartDefinition topJaw = head.addOrReplaceChild("topJaw", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -2.0F, -18.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 107).addBox(-1.0F, 0.0F, -18.0F, 2.0F, 3.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -8.0F));

		PartDefinition lowerJaw = head.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(40, 45).addBox(-1.0F, 0.0F, -16.0F, 2.0F, 2.0F, 17.0F, new CubeDeformation(-0.01F))
		.texOffs(0, 48).addBox(-1.0F, -2.0F, -16.0F, 2.0F, 2.0F, 17.0F, new CubeDeformation(-0.02F)), PartPose.offset(0.0F, 1.5F, -9.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -10.0F, 14.0F, 8.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(78, 45).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 1.5F, -9.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(78, 45).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 1.5F, -9.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 67).addBox(0.0F, -2.5F, -2.5F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 1.5F, 0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(26, 67).addBox(0.0F, -2.5F, -2.5F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 1.5F, 0.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(40, 28).addBox(-5.0F, -3.5F, 0.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 67).addBox(-4.0F, -2.5F, 10.0F, 8.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(68, 0).addBox(0.0F, -5.5F, 15.0F, 0.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(70, 64).addBox(0.0F, -8.5F, 5.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 77).addBox(0.0F, 3.5F, 5.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 10.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);
//		this.body.xRot = state.xRot * Mth.DEG_TO_RAD;
//		this.body.yRot = state.yRot * Mth.DEG_TO_RAD;
		float f = 1.0F;
		if (!state.isInWater) {
			f = 1.5F;
		}

		this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * state.ageInTicks);
	}
}