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

public class CloggrumShieldModel extends Model<Unit> {
	public CloggrumShieldModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("shield", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-1.0F, -15.5F, -3.0F, 2.0F, 6.0F, 6.0F)
				.texOffs(36, 0).addBox(-6.0F, -22.5F, -5.0F, 12.0F, 19.0F, 2.0F),
			PartPose.offset(0.0F, 19.5F, 0.0F));

		partdefinition.addOrReplaceChild("top", CubeListBuilder.create()
				.texOffs(0, 24).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 4.0F, 2.0F),
			PartPose.offsetAndRotation(0.0F, -3.0F, -5.0F, -0.3927F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 8.0F, 2.0F),
			PartPose.offsetAndRotation(0.0F, 16.0F, -5.0F, 0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}
