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

public class JavelinModel extends Model<Unit> {

	public JavelinModel(ModelPart root) {
		super(root, RenderTypes::entityCutout);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("javelin", CubeListBuilder.create()
			.texOffs(0, 0).addBox(-8.5F, -24.0F, 7.5F, 1.0F, 24.0F, 1.0F)
			.texOffs(4, 0).addBox(-9.5F, -25.0F, 6.5F, 3.0F, 1.0F, 3.0F)
			.texOffs(4, 1).addBox(-8.0F, -29.0F, 6.5F, 0.0F, 4.0F, 3.0F)
			.texOffs(4, 4).addBox(-9.5F, -29.0F, 8.0F, 3.0F, 4.0F, 0.0F),
			PartPose.offset(8.0F, 24.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}
