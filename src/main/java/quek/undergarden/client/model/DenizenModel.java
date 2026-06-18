package quek.undergarden.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.HumanoidArm;
import quek.undergarden.client.state.entity.DenizenRenderState;
import quek.undergarden.entity.monster.denizen.Denizen;

public class DenizenModel extends HumanoidModel<DenizenRenderState> {

	public DenizenModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F), PartPose.offset(0.0F, -3.0F, -2.0F));

		head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.ZERO);

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 34).addBox(-5.0F, -1.0F, -3.75F, 10.0F, 8.0F, 9.0F), PartPose.offset(0.0F, 4.0F, 0.75F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 16).addBox(-7.0F, -4.0F, -5.0F, 14.0F, 8.0F, 10.0F), PartPose.offsetAndRotation(0.0F, -3.0F, -0.75F, 0.3491F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 16).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 16.0F, 4.0F).mirror(false), PartPose.offset(7.0F, 1.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 16).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 16.0F, 4.0F), PartPose.offset(-7.0F, 1.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 36).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F).mirror(false), PartPose.offset(3.0F, 11.0F, 2.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F), PartPose.offset(-3.0F, 11.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createTallBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 8.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -22.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

		head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(34, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.ZERO);

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 12.0F, 9.0F), PartPose.offset(0.0F, -9.0F, 2.0F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, -4.0F, -5.0F, 12.0F, 8.0F, 10.0F), PartPose.offsetAndRotation(0.0F, -10.0F, -2.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 24.0F, 4.0F).mirror(false), PartPose.offset(7.0F, -18.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(66, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 24.0F, 4.0F), PartPose.offset(-7.0F, -18.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(82, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 29.0F, 4.0F).mirror(false), PartPose.offset(3.0F, -5.0F, 2.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(82, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 29.0F, 4.0F), PartPose.offset(-3.0F, -5.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void translateToHand(DenizenRenderState state, HumanoidArm arm, PoseStack stack) {
		float yOffset = state.variant == Denizen.Type.SHORT ? 3.0F : 11.0F;
		this.getArm(arm).translateAndRotate(stack);
		float xOffset = state.variant == Denizen.Type.SHORT ? 1.0F : 0.0F;
		stack.translate((arm == HumanoidArm.LEFT ? xOffset : -xOffset) / 16, yOffset / 16, 0.0F);
	}
}