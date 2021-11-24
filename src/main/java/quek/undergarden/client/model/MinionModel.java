package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.MinionEntity;

public class MinionModel<T extends MinionEntity> extends ListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Undergarden.MODID, "minion"), "main");
	private final ModelPart shell;
	private final ModelPart body;
	private final ModelPart backRightLeg;
	private final ModelPart backLeftLeg;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontRightLeg;

	public MinionModel(ModelPart root) {
		this.shell = root.getChild("shell");
		this.body = root.getChild("body");
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
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.frontLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.frontRightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.backLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.backRightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(this.shell, this.body);
	}
}