package quek.undergarden.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import quek.undergarden.entity.monster.Denizen;

public class Denizen2Model<T extends Denizen> extends FixedHumanoidModel<T> {
	public Denizen2Model(ModelPart part) {
		super(part, 4.0F);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(34, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -22.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 12.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 2.0F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, -4.0F, -5.0F, 12.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -2.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(66, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, -18.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(66, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -18.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(82, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 29.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -5.0F, 2.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(82, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 29.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}
}
