package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.state.entity.UndergarRenderState;

public class UndergarModel extends EntityModel<UndergarRenderState> {

	private final ModelPart head;
	private final ModelPart bottomJaw;
	private final ModelPart tail;
	private final ModelPart tail1;

	public UndergarModel(ModelPart root) {
		super(root.getChild("root"));
		this.head = this.root().getChild("head");
		this.bottomJaw = this.head.getChild("bottom_jaw");
		this.tail = this.root().getChild("tail");
		this.tail1 = this.tail.getChild("tail1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(),
			PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 28).addBox(-4.0F, -3.5F, -7.0F, 8.0F, 7.0F, 8.0F),
			PartPose.offset(0.0F, 1.5F, -11.0F));

		head.addOrReplaceChild("top_jaw", CubeListBuilder.create()
				.texOffs(39, 44).addBox(-1.0F, -2.0F, -18.0F, 2.0F, 2.0F, 18.0F)
				.texOffs(87, 41).addBox(-1.0F, 0.0F, -18.0F, 2.0F, 3.0F, 18.0F),
			PartPose.offset(0.0F, 1.5F, -7.0F));

		head.addOrReplaceChild("bottom_jaw", CubeListBuilder.create()
			.texOffs(0, 45).addBox(-1.0F, 0.0F, -16.0F, 2.0F, 2.0F, 17.0F, new CubeDeformation(-0.01F))
			.texOffs(90, 20).addBox(-1.0F, -2.0F, -16.0F, 2.0F, 2.0F, 17.0F, new CubeDeformation(-0.02F)), PartPose.offset(0.0F, 1.5F, -8.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-7.0F, -8.0F, -10.0F, 14.0F, 8.0F, 20.0F),
			PartPose.offset(0.0F, 5.0F, 0.0F));

		body.addOrReplaceChild("left_pectoral_fin", CubeListBuilder.create()
				.texOffs(33, 40).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F),
			PartPose.offsetAndRotation(5.0F, 1.5F, -9.5F, 0.0F, 0.0F, -0.7854F));

		body.addOrReplaceChild("right_pectoral_fin", CubeListBuilder.create()
				.texOffs(33, 40).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F),
			PartPose.offsetAndRotation(-5.0F, 1.5F, -9.5F, 0.0F, 0.0F, 0.7854F));

		body.addOrReplaceChild("left_pelvic_fin", CubeListBuilder.create()
				.texOffs(32, 47).addBox(0.0F, -2.5F, -2.5F, 0.0F, 8.0F, 5.0F),
			PartPose.offsetAndRotation(6.0F, 1.5F, 0.5F, 0.0F, 0.0F, -0.7854F));

		body.addOrReplaceChild("right_pelvic_fin", CubeListBuilder.create()
				.texOffs(32, 47).addBox(0.0F, -2.5F, -2.5F, 0.0F, 8.0F, 5.0F),
			PartPose.offsetAndRotation(-6.0F, 1.5F, 0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create()
				.texOffs(33, 22).addBox(0.0F, -8.5F, 5.0F, 0.0F, 5.0F, 8.0F)
				.texOffs(33, 29).addBox(0.0F, 3.5F, 5.0F, 0.0F, 5.0F, 8.0F)
				.texOffs(88, 0).addBox(-5.0F, -3.5F, 0.0F, 10.0F, 7.0F, 10.0F),
			PartPose.offset(0.0F, 1.5F, 10.0F));

		tail.addOrReplaceChild("tail1", CubeListBuilder.create()
				.texOffs(48, 0).addBox(-4.0F, -2.5F, 0.0F, 8.0F, 5.0F, 5.0F)
				.texOffs(0, -8).addBox(0.0F, -6.5F, 4.0F, 0.0F, 12.0F, 8.0F),
			PartPose.offset(0.0F, 0.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(UndergarRenderState state) {
		super.setupAnim(state);
		this.root.xRot = state.xRot * Mth.DEG_TO_RAD;

		if (state.isAggressive) {
			if (state.attackAnimTime > 0.0F) {
				this.bottomJaw.xRot = (30.0F - (state.attackAnimTime * 30.0F)) * Mth.DEG_TO_RAD;
			} else {
				this.bottomJaw.xRot = 30.0F * Mth.DEG_TO_RAD;
			}
		} else {
			this.head.xRot = state.xRot * Mth.DEG_TO_RAD / 4;
			this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
			this.bottomJaw.xRot = Mth.cos(state.ageInTicks * 0.1F) * 0.1F + (10.0F * Mth.DEG_TO_RAD);
		}

		float f = 1.0F;
		if (!state.isInWater) {
			f = 1.5F;
		} else if (state.walkAnimationSpeed > 0.01D){
			//shimmy
			this.root.yRot = (30.0F * state.walkAnimationSpeed) * Mth.sin(0.6F * state.ageInTicks) * Mth.DEG_TO_RAD;
			this.head.yRot += -(30.0F * state.walkAnimationSpeed) * Mth.sin(0.6F * state.ageInTicks) * Mth.DEG_TO_RAD;
		}

		this.tail.yRot = -f * 0.2F * Mth.sin(0.6F * state.ageInTicks);
		this.tail1.yRot = -f * 0.2F * Mth.sin(0.6F * state.ageInTicks);
	}
}