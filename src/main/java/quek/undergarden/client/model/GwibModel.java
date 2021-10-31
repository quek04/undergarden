package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.entity.GwibEntity;

public class GwibModel<T extends GwibEntity> extends ListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("undergarden", "gwib"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tail;

	public GwibModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -16.0F, -10.0F, 16.0F, 16.0F, 20.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.0F, -17.0F, -11.0F, 18.0F, 10.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(80, 14).addBox(-6.0F, -12.0F, -17.0F, 12.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(74, 74).addBox(-7.0F, -13.0F, -18.0F, 14.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(73, 43).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 10.0F));

		PartDefinition tailfin = tail.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(44, 53).addBox(0.0F, -14.0F, -1.0F, 0.0F, 23.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 6.0F));

		PartDefinition rightfin = body.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(44, 0).addBox(-23.0F, -6.0F, -8.0F, 15.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftfin = body.addOrReplaceChild("leftfin", CubeListBuilder.create().texOffs(38, 38).addBox(16.0F, 0.0F, -8.0F, 15.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -6.0F, 0.0F));

		PartDefinition backfin = body.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -14.0F, -8.0F, 0.0F, 15.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}

		this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.body);
	}
}