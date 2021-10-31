package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.entity.ScintlingEntity;

public class ScintlingModel<T extends ScintlingEntity> extends AgeableListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("undergarden", "scintling"), "main");
	//private final ModelPart scintling;
	private final ModelPart head;
	private final ModelPart leftStalk;
	private final ModelPart rightStalk;
	private final ModelPart torso;
	private final ModelPart tail;

	public ScintlingModel(ModelPart root) {
		//this.scintling = root.getChild("scintling");
		this.head = root.getChild("head");
		this.leftStalk = root.getChild("leftStalk");
		this.rightStalk = root.getChild("rightStalk");
		this.torso = root.getChild("torso");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		//PartDefinition scintling = partdefinition.addOrReplaceChild("scintling", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 18).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -7.0F));

		PartDefinition leftStalk = head.addOrReplaceChild("leftStalk", CubeListBuilder.create().texOffs(20, 28).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -5.0F, 0.3491F, 0.0F, 0.1745F));

		PartDefinition rightStalk = head.addOrReplaceChild("rightStalk", CubeListBuilder.create().texOffs(20, 28).mirror().addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -2.0F, -5.0F, 0.3491F, 0.0F, -0.1745F));

		PartDefinition torso = partdefinition.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -7.0F, 8.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-3.0F, -1.0F, 5.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 6.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.torso.yRot = Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 0.5F * limbSwingAmount;

		this.tail.yRot = Mth.cos(limbSwing * 0.5F) * 0.5F * limbSwingAmount;

		float wiggle = Mth.sin((entity.tickCount) * 0.3F) * 0.3F;

		this.leftStalk.xRot = wiggle;
		this.rightStalk.xRot = -wiggle;
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.head, this.torso, this.tail);
	}
}