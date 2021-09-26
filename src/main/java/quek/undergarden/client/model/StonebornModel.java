package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.stoneborn.StonebornEntity;

public class StonebornModel<T extends StonebornEntity> extends AgeableListModel<T> {
	private final ModelPart stoneborn;
	private final ModelPart head;
	private final ModelPart nose;
	private final ModelPart jaw;
	private final ModelPart body;
	private final ModelPart robe;
	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public StonebornModel() {
		texWidth = 88;
		texHeight = 67;

		stoneborn = new ModelPart(this);
		stoneborn.setPos(0.0F, 24.0F, 0.0F);

		head = new ModelPart(this);
		head.setPos(0.0F, -29.0F, -1.0F);
		stoneborn.addChild(head);
		head.texOffs(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 12.0F, 8.0F, 0.0F, false);
		head.texOffs(0, 20).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);
		head.texOffs(32, 8).addBox(-7.0F, -10.0F, 0.0F, 14.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(32, 12).addBox(7.0F, -13.0F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		head.texOffs(40, 12).addBox(-9.0F, -15.0F, 0.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		head.texOffs(48, 12).addBox(4.0F, -8.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);

		nose = new ModelPart(this);
		nose.setPos(0.0F, -6.75F, -3.0F);
		head.addChild(nose);
		setRotationAngle(nose, -0.2182F, 0.0F, 0.0F);
		nose.texOffs(24, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		jaw = new ModelPart(this);
		jaw.setPos(0.0F, 0.0F, -2.0F);
		head.addChild(jaw);
		jaw.texOffs(36, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);

		body = new ModelPart(this);
		body.setPos(0.0F, -22.0F, 1.0F);
		stoneborn.addChild(body);
		body.texOffs(0, 26).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, 0.0F, false);

		robe = new ModelPart(this);
		robe.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(robe);
		robe.texOffs(40, 21).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, 0.25F, false);

		leftarm = new ModelPart(this);
		leftarm.setPos(-6.0F, -26.5F, 1.0F);
		stoneborn.addChild(leftarm);
		setRotationAngle(leftarm, 0.0F, 0.0F, 0.0436F);
		leftarm.texOffs(0, 48).addBox(-4.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);

		rightarm = new ModelPart(this);
		rightarm.setPos(7.0F, -26.5F, 1.0F);
		stoneborn.addChild(rightarm);
		setRotationAngle(rightarm, 0.0F, 0.0F, -0.0436F);
		rightarm.texOffs(0, 48).addBox(-1.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, true);

		leftleg = new ModelPart(this);
		leftleg.setPos(-3.0F, -15.0F, 0.0F);
		stoneborn.addChild(leftleg);
		leftleg.texOffs(16, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.0F, false);
		leftleg.texOffs(70, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.24F, false);

		rightleg = new ModelPart(this);
		rightleg.setPos(3.0F, -15.0F, 0.0F);
		stoneborn.addChild(rightleg);
		rightleg.texOffs(16, 48).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.0F, true);
		rightleg.texOffs(70, 48).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.24F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableSet.of(stoneborn);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}