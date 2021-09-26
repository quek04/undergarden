package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.rotspawn.RotwalkerEntity;

public class RotwalkerModel<T extends RotwalkerEntity> extends ListModel<T> {
	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public RotwalkerModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelPart(this);
		head.setPos(0.0F, -8.0F, -1.0F);
		setRotationAngle(head, 0.1309F, 0.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		torso = new ModelPart(this);
		torso.setPos(0.0F, 1.0F, -1.0F);
		setRotationAngle(torso, 0.1745F, 0.0F, 0.0F);
		torso.texOffs(0, 16).addBox(-5.0F, -9.0F, -2.0F, 10.0F, 8.0F, 5.0F, 0.0F, false);
		torso.texOffs(0, 29).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 9.0F, 3.0F, 0.0F, false);

		leftArm = new ModelPart(this);
		leftArm.setPos(5.0F, -6.0F, -2.0F);
		leftArm.texOffs(32, 0).addBox(0.0F, -1.0F, -1.0F, 2.0F, 16.0F, 3.0F, 0.0F, true);

		rightArm = new ModelPart(this);
		rightArm.setPos(-5.0F, -6.0F, -2.0F);
		rightArm.texOffs(32, 0).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 16.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelPart(this);
		leftLeg.setPos(-2.0F, 8.0F, 1.0F);
		leftLeg.texOffs(32, 0).addBox(-2.1F, 0.0F, -2.0F, 2.0F, 16.0F, 3.0F, 0.0F, false);

		rightLeg = new ModelPart(this);
		rightLeg.setPos(2.0F, 8.0F, 1.0F);
		rightLeg.texOffs(32, 0).addBox(0.1F, 0.0F, -2.0F, 2.0F, 16.0F, 3.0F, 0.0F, true);}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = 0.1309F + headPitch * ((float)Math.PI / 180F);

		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(head, torso, leftArm, rightArm, leftLeg, rightLeg);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}