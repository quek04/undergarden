package quek.undergarden.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class GwibModel extends EntityModel<LivingEntityRenderState> {

	private final ModelPart tail;
	private final ModelPart body;

	public GwibModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -2.0F, 10.0F, 5.0F, 13.0F, new CubeDeformation(0.01F))
				.texOffs(0, 14).addBox(0.0F, -11.0F, -6.0F, 0.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-5.0F, -2.0F, -9.0F, 10.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -2.0F));

		PartDefinition eastWhisker = head.addOrReplaceChild("eastWhisker", CubeListBuilder.create().texOffs(31, 38).addBox(-6.0F, 0.0F, -6.0F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 3.0F, -5.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition westWhisker = head.addOrReplaceChild("westWhisker", CubeListBuilder.create().texOffs(19, 38).addBox(0.0F, 0.0F, -6.0F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 3.0F, -5.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition eastFin = body.addOrReplaceChild("eastFin", CubeListBuilder.create().texOffs(27, 18).addBox(-9.0F, 0.0F, -5.5F, 9.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -5.0F, 3.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition westFin = body.addOrReplaceChild("westFin", CubeListBuilder.create().texOffs(22, 0).addBox(0.0F, 0.0F, -5.5F, 9.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -5.0F, 3.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 37).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(30, 32).addBox(-6.0F, 0.0F, 8.0F, 12.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 11.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		super.setupAnim(state);
		this.body.xRot = state.xRot * Mth.DEG_TO_RAD;
		this.body.yRot = state.yRot * Mth.DEG_TO_RAD;
		float f = 1.0F;
		if (!state.isInWater) {
			f = 1.5F;
		}

		this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * state.ageInTicks);
	}
}