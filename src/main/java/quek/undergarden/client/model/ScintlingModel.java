package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class ScintlingModel extends EntityModel<LivingEntityRenderState> {

	private final ModelPart head;
	private final ModelPart leftStalk;
	private final ModelPart rightStalk;
	private final ModelPart torso;
	private final ModelPart tail;

	public ScintlingModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.leftStalk = head.getChild("leftStalk");
		this.rightStalk = head.getChild("rightStalk");
		this.torso = root.getChild("torso");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-3.0F, -1.0F, 5.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 6.0F));

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -7.0F, 8.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 18).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -7.0F));

		PartDefinition leftStalk = head.addOrReplaceChild("leftStalk", CubeListBuilder.create().texOffs(20, 28).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -5.0F, 0.3491F, 0.0F, 0.1745F));

		PartDefinition rightStalk = head.addOrReplaceChild("rightStalk", CubeListBuilder.create().texOffs(20, 28).mirror().addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -2.0F, -5.0F, 0.3491F, 0.0F, -0.1745F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;

		this.torso.yRot = Mth.cos(state.walkAnimationPos * 0.5F + Mth.PI) * 0.5F * state.walkAnimationSpeed;

		this.tail.yRot = Mth.cos(state.walkAnimationPos * 0.5F) * 0.5F * state.walkAnimationSpeed;

		float wiggle = Mth.sin((state.ageInTicks) * 0.3F) * 0.3F;

		this.leftStalk.xRot = wiggle;
		this.rightStalk.xRot = -wiggle;
	}
}