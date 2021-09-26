package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.BruteEntity;

public class BruteModel<T extends BruteEntity> extends AgeableListModel<T> {
	private final ModelPart brute;
	private final ModelPart uppertorso;
	private final ModelPart lowertorso;
	private final ModelPart head;
	private final ModelPart horns;
	private final ModelPart horns2;
	private final ModelPart leftarm;
	private final ModelPart leftarm2;
	private final ModelPart lefthand;
	private final ModelPart rightarm;
	private final ModelPart rightarm2;
	private final ModelPart righthand;
	private final ModelPart leftleg;
	private final ModelPart leftleg2;
	private final ModelPart rightleg;
	private final ModelPart rightleg2;

	public BruteModel() {
		texWidth = 128;
		texHeight = 128;

		brute = new ModelPart(this);
		brute.setPos(0.0F, 24.0F, 0.0F);

		uppertorso = new ModelPart(this);
		uppertorso.setPos(0.0F, -27.5F, 1.0F);
		brute.addChild(uppertorso);
		setRotationAngle(uppertorso, 0.8727F, 0.0F, 0.0F);
		uppertorso.texOffs(0, 0).addBox(-7.0F, -7.5F, -4.0F, 14.0F, 15.0F, 8.0F, 0.0F, false);
		uppertorso.texOffs(78, 22).addBox(0.0F, -7.5F, 4.0F, 0.0F, 14.0F, 6.0F, 0.0F, false);

		lowertorso = new ModelPart(this);
		lowertorso.setPos(0.0F, 10.4973F, -1.24F);
		uppertorso.addChild(lowertorso);
		setRotationAngle(lowertorso, -0.5236F, 0.0F, 0.0F);
		lowertorso.texOffs(0, 23).addBox(-6.0F, -5.9973F, -2.76F, 12.0F, 12.0F, 5.0F, 0.0F, false);
		lowertorso.texOffs(90, 29).addBox(0.0F, -5.9973F, 2.24F, 0.0F, 9.0F, 4.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, -28.0F, -3.0F);
		brute.addChild(head);
		head.texOffs(44, 0).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 5.0F, 9.0F, 0.0F, false);
		head.texOffs(45, 14).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 2.0F, 8.0F, 0.0F, false);

		horns = new ModelPart(this);
		horns.setPos(0.0F, 4.0F, -6.0F);
		head.addChild(horns);
		setRotationAngle(horns, 1.2217F, 0.0F, 0.0F);
		horns.texOffs(48, 41).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);
		horns.texOffs(48, 41).addBox(3.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		horns2 = new ModelPart(this);
		horns2.setPos(0.0F, 0.0F, -2.0F);
		horns.addChild(horns2);
		setRotationAngle(horns2, -1.5708F, 0.0F, 0.0F);
		horns2.texOffs(32, 45).addBox(-5.5F, 1.0F, -5.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		horns2.texOffs(32, 45).addBox(3.5F, 1.0F, -5.0F, 2.0F, 1.0F, 6.0F, 0.0F, true);

		leftarm = new ModelPart(this);
		leftarm.setPos(7.0F, -26.0F, -1.0F);
		brute.addChild(leftarm);
		setRotationAngle(leftarm, 0.2618F, 0.0F, 0.0F);
		leftarm.texOffs(34, 23).addBox(0.0F, -4.0F, -3.0F, 3.0F, 17.0F, 5.0F, 0.0F, false);

		leftarm2 = new ModelPart(this);
		leftarm2.setPos(0.0F, 13.0F, 0.0F);
		leftarm.addChild(leftarm2);
		setRotationAngle(leftarm2, -0.7854F, 0.0F, 0.0F);
		leftarm2.texOffs(50, 24).addBox(0.1F, -2.0F, -2.5F, 3.0F, 13.0F, 4.0F, 0.0F, false);

		lefthand = new ModelPart(this);
		lefthand.setPos(1.0F, 24.0F, -9.0F);
		leftarm.addChild(lefthand);
		setRotationAngle(lefthand, -0.2618F, 0.0F, 0.0F);
		lefthand.texOffs(64, 24).addBox(-1.0F, -4.5F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);

		rightarm = new ModelPart(this);
		rightarm.setPos(-7.0F, -26.0F, -1.0F);
		brute.addChild(rightarm);
		setRotationAngle(rightarm, 0.2618F, 0.0F, 0.0F);
		rightarm.texOffs(34, 23).addBox(-3.0F, -4.0F, -3.0F, 3.0F, 17.0F, 5.0F, 0.0F, true);

		rightarm2 = new ModelPart(this);
		rightarm2.setPos(-3.0F, 13.0F, 0.0F);
		rightarm.addChild(rightarm2);
		setRotationAngle(rightarm2, -0.7854F, 0.0F, 0.0F);
		rightarm2.texOffs(50, 24).addBox(-0.1F, -2.0F, -2.5F, 3.0F, 13.0F, 4.0F, 0.0F, true);

		righthand = new ModelPart(this);
		righthand.setPos(-2.0F, 24.0F, -9.0F);
		rightarm.addChild(righthand);
		setRotationAngle(righthand, -0.2618F, 0.0F, 0.0F);
		righthand.texOffs(64, 24).addBox(-1.0F, -4.5F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, true);

		leftleg = new ModelPart(this);
		leftleg.setPos(4.0F, -14.0F, 9.0F);
		brute.addChild(leftleg);
		setRotationAngle(leftleg, -0.2618F, 0.0F, 0.0F);
		leftleg.texOffs(0, 40).addBox(-1.9F, -1.0F, -1.7412F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		leftleg2 = new ModelPart(this);
		leftleg2.setPos(-0.5F, 8.1933F, 2.8294F);
		leftleg.addChild(leftleg2);
		setRotationAngle(leftleg2, 0.2618F, 0.0F, 0.0F);
		leftleg2.texOffs(16, 40).addBox(-1.5F, -3.6274F, -3.7294F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		rightleg = new ModelPart(this);
		rightleg.setPos(-4.0F, -14.0F, 9.0F);
		brute.addChild(rightleg);
		setRotationAngle(rightleg, -0.2618F, 0.0F, 0.0F);
		rightleg.texOffs(0, 40).addBox(-2.1F, -1.0341F, -1.7412F, 4.0F, 8.0F, 4.0F, 0.0F, true);

		rightleg2 = new ModelPart(this);
		rightleg2.setPos(-0.5F, 8.1933F, 2.8294F);
		rightleg.addChild(rightleg2);
		setRotationAngle(rightleg2, 0.2618F, 0.0F, 0.0F);
		rightleg2.texOffs(16, 40).addBox(-1.5F, -3.6274F, -3.7294F, 4.0F, 9.0F, 4.0F, 0.0F, true);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableSet.of(brute);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.leftleg.xRot = -0.2618F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = -0.2618F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftarm.xRot = 0.2618F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.xRot = 0.2618F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}