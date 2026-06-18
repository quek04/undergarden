package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class ScintlingModel extends EntityModel<LivingEntityRenderState> {

	private final ModelPart head;
	private final ModelPart leftStalk;
	private final ModelPart rightStalk;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart tail;

	public ScintlingModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.leftStalk = this.head.getChild("left_stalk");
		this.rightStalk = this.head.getChild("right_stalk");
		this.body1 = root.getChild("body1");
		this.body2 = this.body1.getChild("body2");
		this.tail = this.body2.getChild("tail");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 5.0F, 13.0F), PartPose.offset(0.0F, 22.0F, -7.0F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 4.0F, 5.0F), PartPose.offset(0.0F, 1.0F, 12.0F));

		body2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 28).addBox(-3.0F, -1.5F, 0.0F, 6.0F, 3.0F, 4.0F), PartPose.offset(0.0F, -0.5F, 5.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 18).addBox(-3.0F, -2.0F, -7.0F, 6.0F, 4.0F, 6.0F), PartPose.offset(0.0F, 22.0F, -7.0F));

		head.addOrReplaceChild("right_stalk", CubeListBuilder.create().texOffs(20, 28).addBox(-0.5F, -7.0F, -0.5F, 1.0F, 7.0F, 1.0F), PartPose.offset(-2.5F, -2.0F, -6.5F));

		head.addOrReplaceChild("left_stalk", CubeListBuilder.create().texOffs(20, 28).addBox(-0.5F, -7.0F, -0.5F, 1.0F, 7.0F, 1.0F), PartPose.offset(2.5F, -2.0F, -6.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createBaby() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 3.0F, 10.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

		head.addOrReplaceChild("left_stalk", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(1.5F, -3.0F, -5.5F, -0.1745F, 0.0F, 0.0873F));

		head.addOrReplaceChild("right_stalk", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(-1.5F, -3.0F, -5.5F, -0.1745F, 0.0F, -0.0873F));

		PartDefinition body1 = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, 4.0F, 2.0F, 2.0F, 3.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create(), PartPose.ZERO);

		body2.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);

		if (state.isBaby) {
			state.walkAnimationPos *= 0.75F;
			state.walkAnimationSpeed *= 0.75F;
		}

		this.body1.yRot += Mth.cos(state.walkAnimationPos * 0.65F + Mth.PI) * state.walkAnimationSpeed;
		this.body2.yRot += Mth.cos(state.walkAnimationPos * 0.65F) * state.walkAnimationSpeed;
		this.tail.yRot += Mth.cos(state.walkAnimationPos * 0.65F + Mth.PI) * state.walkAnimationSpeed;

		float wiggle = Mth.sin((state.ageInTicks) * 0.3F) * 0.3F;

		this.leftStalk.xRot = wiggle;
		this.rightStalk.xRot = -wiggle;
	}
}