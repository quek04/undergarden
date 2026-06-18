package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import quek.undergarden.client.state.entity.MogRenderState;

public class MogModel extends EntityModel<MogRenderState> {

	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart backLegRight;
	private final ModelPart backLegLeft;
	private final ModelPart head;
	private final ModelPart body;

	public MogModel(ModelPart root) {
		super(root);
		this.frontLegLeft = root.getChild("left_front_leg");
		this.frontLegRight = root.getChild("right_front_leg");
		this.backLegRight = root.getChild("right_back_leg");
		this.backLegLeft = root.getChild("left_back_leg");
		this.head = root.getChild("head");
		this.body = root.getChild("body");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 35).addBox(0.0F, -17.0F, -4.0F, 5.0F, 4.0F, 5.0F)
				.texOffs(0, 0).addBox(-6.0F, -13.0F, -6.0F, 12.0F, 14.0F, 12.0F)
				.texOffs(0, 26).addBox(-4.0F, -15.0F, -3.0F, 6.0F, 2.0F, 7.0F),
			PartPose.offset(0.0F, 20.0F, 0.0F));

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(26, 26).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F),
			PartPose.offset(0.0F, 20.0F, -6.0F));

		partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
				.texOffs(32, 36).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F),
			PartPose.offset(3.5F, 20.0F, -3.5F));

		partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
				.texOffs(20, 36).addBox(-1.5F, 2.0F, -1.5F, 3.0F, 4.0F, 3.0F),
			PartPose.offset(-3.5F, 18.0F, -3.5F));

		partdefinition.addOrReplaceChild("right_back_leg", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F),
			PartPose.offset(-3.5F, 20.0F, 3.5F));

		partdefinition.addOrReplaceChild("left_back_leg", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F),
			PartPose.offset(3.5F, 20.0F, 3.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createBaby() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
				.texOffs(0, 16).addBox(-3.0F, -10.0F, -1.0F, 4.0F, 2.0F, 4.0F),
			PartPose.offset(0.0F, 22.0F, 0.0F));

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(16, 16).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F),
			PartPose.offset(0.0F, 21.0F, -4.0F));

		partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
				.texOffs(8, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F),
			PartPose.offset(2.0F, 22.0F, -2.0F));

		partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
				.texOffs(16, 20).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F),
			PartPose.offset(-2.0F, 22.0F, -2.0F));

		partdefinition.addOrReplaceChild("right_back_leg", CubeListBuilder.create()
				.texOffs(0, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F),
			PartPose.offset(-2.0F, 22.0F, 2.0F));

		partdefinition.addOrReplaceChild("left_back_leg", CubeListBuilder.create()
				.texOffs(24, 20).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F),
			PartPose.offset(2.0F, 24.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(MogRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;

		this.body.zRot = 0.1F * Mth.sin(state.walkAnimationPos * 2.0F) * 4.0F * state.walkAnimationSpeed;

		this.frontLegLeft.xRot = Mth.cos(state.walkAnimationPos * 2.0F) * 4.0F * state.walkAnimationSpeed;
		this.frontLegRight.xRot = Mth.cos(state.walkAnimationPos * 2.0F + Mth.PI) * 4.0F * state.walkAnimationSpeed;
		this.backLegLeft.xRot = Mth.cos(state.walkAnimationPos * 2.0F + Mth.PI) * 4.0F * state.walkAnimationSpeed;
		this.backLegRight.xRot = Mth.cos(state.walkAnimationPos * 2.0F) * 4.0F * state.walkAnimationSpeed;
	}
}