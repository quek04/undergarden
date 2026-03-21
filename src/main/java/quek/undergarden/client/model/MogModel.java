package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
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
		this.frontLegLeft = root.getChild("frontLegLeft");
		this.frontLegRight = root.getChild("frontLegRight");
		this.backLegRight = root.getChild("backLegRight");
		this.backLegLeft = root.getChild("backLegLeft");
		this.head = root.getChild("head");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition frontLegLeft = partdefinition.addOrReplaceChild("frontLegLeft", CubeListBuilder.create().texOffs(32, 36).mirror().addBox(0.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 20.0F, -2.0F));

		PartDefinition frontLegRight = partdefinition.addOrReplaceChild("frontLegRight", CubeListBuilder.create().texOffs(20, 36).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 20.0F, -2.0F));

		PartDefinition backLegRight = partdefinition.addOrReplaceChild("backLegRight", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 20.0F, 2.0F));

		PartDefinition backLegLeft = partdefinition.addOrReplaceChild("backLegLeft", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(4.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 20.0F, 2.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 26).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, -6.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 35).addBox(0.0F, -21.0F, -4.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -17.0F, -6.0F, 12.0F, 14.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 26).addBox(-4.0F, -19.0F, -3.0F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

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