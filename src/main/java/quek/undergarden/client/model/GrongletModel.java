package quek.undergarden.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class GrongletModel extends Model<Unit> {

	public GrongletModel(ModelPart root) {
		super(root, RenderTypes::entityCutout);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition burs = partdefinition.addOrReplaceChild("burs", CubeListBuilder.create(), PartPose.offset(4.0F, 24.0F, -6.0F));

		PartDefinition top_burs = burs.addOrReplaceChild("top_burs", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 6.0F));

		top_burs.addOrReplaceChild("lastbur", CubeListBuilder.create().texOffs(24, 22).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 4.0F));

		top_burs.addOrReplaceChild("middlebur", CubeListBuilder.create().texOffs(24, 20).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		top_burs.addOrReplaceChild("firstbur", CubeListBuilder.create().texOffs(24, 24).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -4.0F));

		PartDefinition side_burs = burs.addOrReplaceChild("side_burs", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.0F, 6.0F));

		side_burs.addOrReplaceChild("rightbur", CubeListBuilder.create().texOffs(8, 16).addBox(-2.0F, 0.0F, -6.0F, 2.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -0.0F, 0.0F));

		side_burs.addOrReplaceChild("leftbur", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, 0.0F, -6.0F, 2.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -0.0F, 0.0F));

		PartDefinition edge_burs = burs.addOrReplaceChild("edge_burs", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 6.0F));

		edge_burs.addOrReplaceChild("leftedgebur", CubeListBuilder.create().texOffs(4, 16).addBox(0.0F, 0.0F, -6.0F, 2.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		edge_burs.addOrReplaceChild("rightedgebur", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -6.0F, 2.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition limbs = partdefinition.addOrReplaceChild("limbs", CubeListBuilder.create(), PartPose.offset(0.0F, 23.99F, 0.0F));

		limbs.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(22, 16).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.0F, 6.0F));

		limbs.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(22, 18).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.0F, -6.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
