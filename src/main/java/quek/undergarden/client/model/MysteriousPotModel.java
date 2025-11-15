package quek.undergarden.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import quek.undergarden.entity.animal.MysteriousPot;

public class MysteriousPotModel extends HierarchicalModel<MysteriousPot> {
	private final ModelPart root;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	private final ModelPart back_right_leg;
	private final ModelPart back_left_leg;

	public MysteriousPotModel(ModelPart root) {
		this.root = root;
		this.front_right_leg = root.getChild("front_right_leg");
		this.front_left_leg = root.getChild("front_left_leg");
		this.back_right_leg = root.getChild("back_right_leg");
		this.back_left_leg = root.getChild("back_left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("pot", CubeListBuilder.create()
				.texOffs(32, 29).addBox(-8.0F, -16.0F, 0.0F, 8.0F, 3.0F, 8.0F)
				.texOffs(0, 23).addBox(-9.0F, -2.0F, -1.0F, 10.0F, 4.0F, 10.0F)
				.texOffs(0, 0).addBox(-10.0F, -13.0F, -2.0F, 12.0F, 11.0F, 12.0F)
				.texOffs(0, 42).addBox(-6.0F, -3.0F, -2.0F, 4.0F, 1.0F, 1.0F),
			PartPose.offset(4.0F, 19.0F, -4.0F));

		partdefinition.addOrReplaceChild("front_right_leg", CubeListBuilder.create()
				.texOffs(30, 23).addBox(-1.0F, 0.0F, -0.25F, 2.0F, 3.0F, 3.0F)
				.texOffs(8, 37).addBox(-1.0F, 3.0F, -0.25F, 2.0F, 3.0F, 2.0F),
			PartPose.offsetAndRotation(-5.0F, 18.0F, -4.75F, -0.1745F, -2.3562F, 0.0F));

		partdefinition.addOrReplaceChild("front_left_leg", CubeListBuilder.create()
				.texOffs(0, 23).mirror().addBox(-1.0F, 0.0F, -0.25F, 2.0F, 3.0F, 3.0F).mirror(false)
				.texOffs(8, 37).mirror().addBox(-1.0F, 3.0F, -0.25F, 2.0F, 3.0F, 2.0F).mirror(false),
			PartPose.offsetAndRotation(5.0F, 18.0F, -4.75F, -0.1745F, 2.3562F, 0.0F));

		partdefinition.addOrReplaceChild("back_right_leg", CubeListBuilder.create()
				.texOffs(0, 6).addBox(-1.0F, 0.0F, -2.75F, 2.0F, 3.0F, 3.0F)
				.texOffs(36, 5).addBox(-1.0F, 3.0F, -1.75F, 2.0F, 3.0F, 2.0F),
			PartPose.offsetAndRotation(-5.0F, 18.0F, 4.75F, 0.1745F, 2.3562F, 0.0F));

		partdefinition.addOrReplaceChild("back_left_leg", CubeListBuilder.create()
				.texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -2.75F, 2.0F, 3.0F, 3.0F).mirror(false)
				.texOffs(36, 0).mirror().addBox(-1.0F, 3.0F, -1.75F, 2.0F, 3.0F, 2.0F).mirror(false),
			PartPose.offsetAndRotation(5.0F, 18.0F, 4.75F, 0.1745F, -2.3562F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(MysteriousPot entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root.getAllParts().forEach(ModelPart::resetPose);
		this.front_right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 3;
		this.front_left_leg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount / 3;
		this.back_right_leg.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.4F * limbSwingAmount / 3;
		this.back_left_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 3;
	}
}