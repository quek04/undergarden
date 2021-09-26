package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.DwellerEntity;

public class DwellerModel<T extends DwellerEntity> extends AgeableListModel<T> {
	private final ModelPart torso;
	private final ModelPart mane;
	private final ModelPart head;
	private final ModelPart trunk;
	private final ModelPart trunk2;
	private final ModelPart tail;
	private final ModelPart rightLeg;
	private final ModelPart rightFoot;
	private final ModelPart leftLeg;
	private final ModelPart leftFoot;

	public DwellerModel(float torsoInflate) {
		texWidth = 128;
		texHeight = 128;

		torso = new ModelPart(this);
		torso.setPos(0.0F, 2.0F, 3.0F);
		setRotationAngle(torso, -0.4363F, 0.0F, 0.0F);
		torso.texOffs(0, 23).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 8.0F, 15.0F, torsoInflate, false);

		mane = new ModelPart(this);
		mane.setPos(0.0F, 0.0F, 0.0F);
		torso.addChild(mane);
		mane.texOffs(41, 10).addBox(-1.0F, -6.0F, -8.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
		mane.texOffs(0, 0).addBox(-1.0F, -3.0F, -8.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, 1.0F, -2.0F);
		head.texOffs(0, 46).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 8.0F, 6.0F, 0.0F, false);

		trunk = new ModelPart(this);
		trunk.setPos(0.0F, 8.0F, -1.0F);
		head.addChild(trunk);
		setRotationAngle(trunk, 2.618F, 0.0F, 0.0F);
		trunk.texOffs(39, 26).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 9.0F, 0.0F, false);

		trunk2 = new ModelPart(this);
		trunk2.setPos(0.0F, 1.0F, -9.0F);
		trunk.addChild(trunk2);
		setRotationAngle(trunk2, 0.5236F, 0.0F, 0.0F);
		trunk2.texOffs(39, 0).addBox(-1.0F, 0.0F, -7.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);

		tail = new ModelPart(this);
		tail.setPos(0.0F, -3.0F, 9.0F);
		torso.addChild(tail);
		setRotationAngle(tail, 0.5236F, 0.0F, 0.0F);
		tail.texOffs(52, 57).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 2.0F, 0.0F, false);

		rightLeg = new ModelPart(this);
		rightLeg.setPos(-5.0F, 5.0F, 6.0F);
		rightLeg.texOffs(52, 40).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 11.0F, 6.0F, 0.0F, false);

		rightFoot = new ModelPart(this);
		rightFoot.setPos(-2.0F, 9.0F, 3.0F);
		rightLeg.addChild(rightFoot);
		setRotationAngle(rightFoot, 0.6109F, 0.0F, 0.0F);
		rightFoot.texOffs(0, 60).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 13.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelPart(this);
		leftLeg.setPos(5.0F, 5.0F, 6.0F);
		leftLeg.texOffs(32, 46).addBox(0.0F, -2.0F, -3.0F, 4.0F, 11.0F, 6.0F, 0.0F, false);

		leftFoot = new ModelPart(this);
		leftFoot.setPos(2.0F, 9.0F, 3.0F);
		leftLeg.addChild(leftFoot);
		setRotationAngle(leftFoot, 0.6109F, 0.0F, 0.0F);
		leftFoot.texOffs(58, 0).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 13.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = -1.3963F + headPitch * ((float)Math.PI / 180F);

		this.leftLeg.xRot = -0.6109F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.rightLeg.xRot = -0.6109F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.head, this.torso, this.rightLeg, this.leftLeg);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void prepareMobModel(T entity, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		this.mane.visible = !entity.isSaddled();
		super.prepareMobModel(entity, p_212843_2_, p_212843_3_, p_212843_4_);
	}
}