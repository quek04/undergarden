package quek.undergarden.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class PotModel extends Model<Unit> {

	public PotModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("pot", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-8.0F, -16.0F, 0.0F, 8.0F, 3.0F, 8.0F)
				.texOffs(0, 0).addBox(-10.0F, -13.0F, -2.0F, 12.0F, 13.0F, 12.0F),
			PartPose.offset(4.0F, 24.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}