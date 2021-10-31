package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import quek.undergarden.entity.GwiblingEntity;

public class GwiblingModel<T extends GwiblingEntity> extends ListModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("undergarden", "gwibling"), "main");
	//private final ModelPart gwibling;
	private final ModelPart body;
	private final ModelPart rightFin;
	private final ModelPart leftFin;
	private final ModelPart tail;

	public GwiblingModel(ModelPart root) {
		//this.gwibling = root.getChild("gwibling");
		this.body = root.getChild("body");
		this.rightFin = root.getChild("rightfin");
		this.leftFin = root.getChild("leftfin");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		//PartDefinition gwibling = partdefinition.addOrReplaceChild("gwibling", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(0.0F, -4.0F, -2.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightfin = body.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(0, 7).addBox(1.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition leftfin = body.addOrReplaceChild("leftfin", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 20, 12);
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