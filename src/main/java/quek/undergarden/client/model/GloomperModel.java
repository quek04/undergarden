package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.state.entity.GloomperRenderState;

public class GloomperModel extends EntityModel<GloomperRenderState> {

	private final ModelPart arms;
	//	private final ModelPart feet;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public GloomperModel(ModelPart root) {
		super(root);
		this.arms = root.getChild("arms");
//		this.feet = root.getChild("feet");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -5.0F, -8.0F, 16.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 3.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 31).addBox(-5.0F, -4.5F, -2.0F, 10.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.5F, -5.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, -2.0F));

		PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.5F, 19.5F, -3.5F));

		PartDefinition leftArm = arms.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(22, 43).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -0.5F, -1.0F, -0.5672F, -0.4363F, 0.0F));

		PartDefinition rightArm = arms.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(22, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -0.5F, -1.0F, -0.5672F, 0.4363F, 0.0F));

//		PartDefinition feet = partdefinition.addOrReplaceChild("feet", CubeListBuilder.create()
//				.texOffs(28, 32).mirror().addBox(7.0F, 5.0F, -7.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
//				.texOffs(28, 32).addBox(-11.0F, 5.0F, -7.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
//				.texOffs(0, 56).addBox(-11.0F, 0.0F, -3.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
//				.texOffs(0, 56).mirror().addBox(7.0F, 0.0F, -3.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
//			PartPose.offset(0.0F, 17.0F, 8.0F)
//		);

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create()
				.texOffs(28, 32).mirror().addBox(7.0F, 5.0F, -7.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(28, 32).addBox(-11.0F, 5.0F, -7.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, 17.0F, 8.0F)
		);

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create()
				.texOffs(0, 56).addBox(-11.0F, 0.0F, -3.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 56).mirror().addBox(7.0F, 0.0F, -3.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
			PartPose.offset(0.0F, 17.0F, 8.0F)
		);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createBaby() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.5F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(0, 14).addBox(-2.0F, -1.0F, 2.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(22, 20).addBox(-2.0F, 1.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(14, 20).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 21.0F, 2.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(22, 24).addBox(0.0F, 1.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(20, 14).addBox(0.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 21.0F, 2.0F));

		PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(GloomperRenderState state) {
		super.setupAnim(state);
		float jumpRotation = Mth.sin(state.jumpCompletion * Mth.PI);

		this.arms.xRot = (jumpRotation * -40.0F - 11.0F) * Mth.DEG_TO_RAD;
		this.rightLeg.xRot = jumpRotation * 50.0F * Mth.DEG_TO_RAD;
		this.leftLeg.xRot = jumpRotation * 50.0F * Mth.DEG_TO_RAD;
	}
}