package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.rotspawn.RotlingEntity;

public class RotlingModel<T extends RotlingEntity> extends ListModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public RotlingModel() {
		texWidth = 64;
		texHeight = 64;

		body = new ModelPart(this);
		body.setPos(0.0F, 24.5F, 0.0F);
		body.texOffs(0, 17).addBox(-4.0F, -10.5F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
		body.texOffs(0, 38).addBox(-4.0F, -12.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, -11.5F, 4.0F);
		body.addChild(head);
		head.texOffs(0, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 5.0F, 8.0F, 0.0F, false);

		rightLeg = new ModelPart(this);
		rightLeg.setPos(-4.0F, -3.5F, 1.0F);
		body.addChild(rightLeg);
		rightLeg.texOffs(24, 2).addBox(0.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelPart(this);
		leftLeg.setPos(4.0F, -3.5F, 1.0F);
		body.addChild(leftLeg);
		leftLeg.texOffs(24, 2).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		rightArm = new ModelPart(this);
		rightArm.setPos(-3.0F, -10.5F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 0.0F, 0.4363F);
		rightArm.texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		leftArm = new ModelPart(this);
		leftArm.setPos(4.0F, -11.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, -0.4363F);
		leftArm.texOffs(0, 0).addBox(-1.2817F, 0.0977F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = entity.isAggressive() ? -0.5235F : -0.1745F;

		this.leftArm.xRot = entity.isAggressive() ? -1.5F : Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = entity.isAggressive() ? -1.5F : Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(body);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}