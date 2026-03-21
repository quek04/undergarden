package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import quek.undergarden.client.state.entity.GloomperRenderState;

public class GloomperModel extends EntityModel<GloomperRenderState> {

	private final ModelPart arms;
	private final ModelPart feet;

	public GloomperModel(ModelPart root) {
		super(root);
		this.arms = root.getChild("arms");
		this.feet = root.getChild("feet");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -5.0F, -8.0F, 16.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 3.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 31).addBox(-5.0F, -4.5F, -2.0F, 10.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.5F, -5.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, -2.0F));

		PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.5F, 19.5F, -3.5F));

		PartDefinition left = arms.addOrReplaceChild("left", CubeListBuilder.create().texOffs(22, 43).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -0.5F, -1.0F, -0.5672F, -0.4363F, 0.0F));

		PartDefinition right = arms.addOrReplaceChild("right", CubeListBuilder.create().texOffs(22, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -0.5F, -1.0F, -0.5672F, 0.4363F, 0.0F));

		PartDefinition feet = partdefinition.addOrReplaceChild("feet", CubeListBuilder.create().texOffs(28, 32).mirror().addBox(7.0F, 5.0F, -7.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(28, 32).addBox(-11.0F, 5.0F, -7.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 56).addBox(-11.0F, 0.0F, -3.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 56).mirror().addBox(7.0F, 0.0F, -3.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 17.0F, 8.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(GloomperRenderState state) {
		super.setupAnim(state);
		float jumpRotation = Mth.sin(state.jumpCompletion * Mth.PI);

		this.arms.xRot = (jumpRotation * -40.0F - 11.0F) * Mth.DEG_TO_RAD;
		this.feet.xRot = jumpRotation * 50.0F * Mth.DEG_TO_RAD;
	}
}