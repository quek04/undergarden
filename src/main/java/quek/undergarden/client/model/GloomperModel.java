package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.state.entity.GloomperRenderState;

public class GloomperModel extends EntityModel<GloomperRenderState> {

	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public GloomperModel(ModelPart root) {
		super(root);
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 31).addBox(-5.0F, -16.0F, -9.0F, 10.0F, 8.0F, 4.0F)
				.texOffs(0, 43).addBox(-5.0F, -12.0F, -10.0F, 10.0F, 4.0F, 1.0F),
			PartPose.offset(0.0F, 24.0F, 0.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-8.0F, -8.0F, -5.0F, 16.0F, 13.0F, 16.0F),
			PartPose.offsetAndRotation(0.0F, 15.0F, -3.0F, -0.3491F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.texOffs(22, 43).mirror().addBox(-1.0F, 0.0F, -1.0F, 3.0F, 6.0F, 3.0F),
			PartPose.offsetAndRotation(6.0F, 18.0F, -8.0F, -0.5672F, -0.4363F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(22, 43).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 6.0F, 3.0F),
			PartPose.offsetAndRotation(-6.0F, 18.0F, -8.0F, -0.5672F, 0.4363F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(28, 32).mirror().addBox(-1.0F, 4.0F, -5.0F, 4.0F, 2.0F, 7.0F)
				.texOffs(0, 56).mirror().addBox(-1.0F, -1.0F, -1.0F, 4.0F, 5.0F, 3.0F),
			PartPose.offset(8.0F, 18.0F, 4.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(28, 32).addBox(-3.0F, 4.0F, -5.0F, 4.0F, 2.0F, 7.0F)
				.texOffs(0, 56).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 5.0F, 3.0F),
			PartPose.offset(-8.0F, 18.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createBaby() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F),
			PartPose.offsetAndRotation(0.0F, 19.5F, 0.0F, -0.2618F, 0.0F, 0.0F));

		body.addOrReplaceChild("tail", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 5.0F)
				.texOffs(0, 14).addBox(-2.0F, -1.0F, 2.0F, 4.0F, 0.0F, 6.0F),
			PartPose.offsetAndRotation(0.0F, 2.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(22, 20).addBox(-2.0F, 1.0F, -3.0F, 2.0F, 2.0F, 2.0F)
				.texOffs(14, 20).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F),
			PartPose.offset(-3.0F, 21.0F, 2.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(22, 24).addBox(0.0F, 1.0F, -3.0F, 2.0F, 2.0F, 2.0F)
				.texOffs(20, 14).addBox(0.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F),
			PartPose.offset(3.0F, 21.0F, 2.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(GloomperRenderState state) {
		super.setupAnim(state);
		float jumpRotation = Mth.sin(state.jumpCompletion * Mth.PI);

		this.rightArm.xRot = (jumpRotation * -40.0F - 11.0F) * Mth.DEG_TO_RAD;
		this.leftArm.xRot = (jumpRotation * -40.0F - 11.0F) * Mth.DEG_TO_RAD;
		this.rightLeg.xRot = jumpRotation * 50.0F * Mth.DEG_TO_RAD;
		this.leftLeg.xRot = jumpRotation * 50.0F * Mth.DEG_TO_RAD;
	}
}