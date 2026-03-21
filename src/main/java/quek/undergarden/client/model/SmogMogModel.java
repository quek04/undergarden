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

public class SmogMogModel extends EntityModel<MogRenderState> {

	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart backLegLeft;
	private final ModelPart backLegRight;

	public SmogMogModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.frontLegLeft = root.getChild("left_front_leg");
		this.frontLegRight = root.getChild("right_front_leg");
		this.backLegLeft = root.getChild("left_back_leg");
		this.backLegRight = root.getChild("right_back_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -16.0F, -7.0F, 14.0F, 12.0F, 14.0F)
				.texOffs(0, 26).addBox(-5.0F, -28.0F, -5.0F, 10.0F, 12.0F, 10.0F)
				.texOffs(42, 0).addBox(-8.0F, -17.0F, -8.0F, 5.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 26).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 19.0F, -7.0F));

		partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 0.0F, -4.0F, 4.0F, 5.0F, 4.0F), PartPose.offset(4.0F, 19.0F, -4.0F));

		partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, 0.0F, -4.0F, 4.0F, 5.0F, 4.0F), PartPose.offset(-4.0F, 19.0F, -4.0F));

		partdefinition.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(0.0F, 0.0F, -4.0F, 4.0F, 5.0F, 4.0F).mirror(false), PartPose.offset(4.0F, 19.0F, 8.0F));

		partdefinition.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 5.0F, 4.0F), PartPose.offset(-4.0F, 19.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
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
