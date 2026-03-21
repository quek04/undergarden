package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class MinionModel extends EntityModel<LivingEntityRenderState> {

	private final ModelPart backRightLeg;
	private final ModelPart backLeftLeg;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontRightLeg;

	public MinionModel(ModelPart root) {
		super(root);
		ModelPart body = root.getChild("body");
		this.backRightLeg = body.getChild("backRightLeg");
		this.backLeftLeg = body.getChild("backLeftLeg");
		this.frontLeftLeg = body.getChild("frontLeftLeg");
		this.frontRightLeg = body.getChild("frontRightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition shell = partdefinition.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -14.0F, -8.0F, 12.0F, 17.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(40, 32).addBox(-5.0F, -13.0F, -7.0F, 10.0F, 13.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, -3.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -6.0F, -3.0F, 8.0F, 13.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(27, 53).addBox(-3.0F, -6.0F, -16.0F, 6.0F, 6.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, -3.0F));

		PartDefinition backRightLeg = body.addOrReplaceChild("backRightLeg", CubeListBuilder.create().texOffs(39, 0).addBox(-5.0F, 0.0F, 0.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, 7.0F));

		PartDefinition backLeftLeg = body.addOrReplaceChild("backLeftLeg", CubeListBuilder.create().texOffs(52, 53).addBox(0.0F, 0.0F, 0.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.0F, 7.0F));

		PartDefinition frontLeftLeg = body.addOrReplaceChild("frontLeftLeg", CubeListBuilder.create().texOffs(54, 7).addBox(0.0F, 0.0F, -5.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.0F, -1.0F));

		PartDefinition frontRightLeg = body.addOrReplaceChild("frontRightLeg", CubeListBuilder.create().texOffs(54, 19).addBox(-5.0F, 0.0F, -5.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);
		this.frontLeftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
		this.frontRightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;

		this.backLeftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F + Mth.PI) * 1.4F * state.walkAnimationSpeed;
		this.backRightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
	}
}