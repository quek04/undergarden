package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.cavern.MuncherEntity;

public class MuncherModel<T extends MuncherEntity> extends ListModel<T> {
	private final ModelPart muncher;
	private final ModelPart lowerjaw;
	private final ModelPart upperjaw;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart leftarm;
	private final ModelPart rightarm;

	public MuncherModel() {
		texWidth = 64;
		texHeight = 64;

		muncher = new ModelPart(this);
		muncher.setPos(0.0F, 16.0F, 5.0F);

		lowerjaw = new ModelPart(this);
		lowerjaw.setPos(0.0F, 5.0F, -5.0F);
		muncher.addChild(lowerjaw);
		lowerjaw.texOffs(0, 15).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		lowerjaw.texOffs(0, 29).addBox(-8.0F, -5.0F, 3.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);

		upperjaw = new ModelPart(this);
		upperjaw.setPos(0.0F, 0.0F, 0.0F);
		muncher.addChild(upperjaw);
		upperjaw.texOffs(0, 0).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);
		upperjaw.texOffs(0, 25).addBox(0.0F, -6.0F, -6.0F, 0.0F, 1.0F, 6.0F, 0.0F, false);

		leftleg = new ModelPart(this);
		leftleg.setPos(-3.0F, 4.0F, -4.0F);
		muncher.addChild(leftleg);
		leftleg.texOffs(30, 15).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		rightleg = new ModelPart(this);
		rightleg.setPos(3.0F, 4.0F, -4.0F);
		muncher.addChild(rightleg);
		rightleg.texOffs(30, 0).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		leftarm = new ModelPart(this);
		leftarm.setPos(5.0F, 2.0F, -3.0F);
		muncher.addChild(leftarm);
		leftarm.texOffs(0, 15).addBox(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		rightarm = new ModelPart(this);
		rightarm.setPos(-5.0F, 2.0F, -3.0F);
		muncher.addChild(rightarm);
		rightarm.texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.upperjaw.xRot = (Mth.sin((entity.tickCount) * 0.5F) * 0.9F) * 0.3F;

		this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.muncher);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}