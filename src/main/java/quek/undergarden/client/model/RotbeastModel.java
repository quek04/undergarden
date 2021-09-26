package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.rotspawn.RotbeastEntity;

public class RotbeastModel<T extends RotbeastEntity> extends ListModel<T> {
	private final ModelPart rotbeast;
	private final ModelPart torso;
	private final ModelPart lowerTorso;
	private final ModelPart upperTorso;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public RotbeastModel() {
		texWidth = 128;
		texHeight = 128;

		rotbeast = new ModelPart(this);
		rotbeast.setPos(0.0F, -2.5F, 5.0F);

		torso = new ModelPart(this);
		torso.setPos(15.0F, 26.5F, -5.0F);
		rotbeast.addChild(torso);

		lowerTorso = new ModelPart(this);
		lowerTorso.setPos(-15.0F, -16.75F, 5.5F);
		torso.addChild(lowerTorso);
		lowerTorso.texOffs(50, 46).addBox(-13.0F, -14.25F, -6.0F, 26.0F, 14.0F, 13.0F, 0.0F, false);

		upperTorso = new ModelPart(this);
		upperTorso.setPos(0.0F, -9.75F, -0.5F);
		lowerTorso.addChild(upperTorso);
		setRotationAngle(upperTorso, 0.48F, 0.0F, 0.0F);
		upperTorso.texOffs(32, 91).addBox(-15.0F, -19.0F, -8.5F, 30.0F, 19.0F, 18.0F, 0.0F, false);

		rightLeg = new ModelPart(this);
		rightLeg.setPos(-7.5F, 7.5F, 0.5F);
		rotbeast.addChild(rightLeg);
		setRotationAngle(rightLeg, -0.3054F, 0.0F, 0.0F);
		rightLeg.texOffs(56, 10).addBox(-3.5F, 0.0F, -4.0F, 9.0F, 19.0F, 9.0F, 0.0F, true);

		leftLeg = new ModelPart(this);
		leftLeg.setPos(7.5F, 7.5F, 0.5F);
		rotbeast.addChild(leftLeg);
		setRotationAngle(leftLeg, 0.3054F, 0.0F, 0.0F);
		leftLeg.texOffs(56, 10).addBox(-5.5F, 0.0F, -4.0F, 9.0F, 19.0F, 9.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, -12.0F, -12.0F);
		rotbeast.addChild(head);
		head.texOffs(80, 26).addBox(-6.0F, -6.5F, -13.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);

		jaw = new ModelPart(this);
		jaw.setPos(0.0F, 2.0F, -1.0F);
		head.addChild(jaw);
		jaw.texOffs(0, 15).addBox(-6.0F, -0.5F, -12.0F, 12.0F, 5.0F, 12.0F, 0.25F, false);
		jaw.texOffs(0, 1).addBox(-6.0F, -3.0F, -12.0F, 12.0F, 2.0F, 12.0F, 0.25F, false);

		rightArm = new ModelPart(this);
		rightArm.setPos(-15.0F, -14.5F, -3.0F);
		rotbeast.addChild(rightArm);
		rightArm.texOffs(2, 86).addBox(-7.0F, -1.0F, -4.0F, 7.0F, 34.0F, 8.0F, 0.0F, false);

		leftArm = new ModelPart(this);
		leftArm.setPos(15.0F, -14.5F, -3.0F);
		rotbeast.addChild(leftArm);
		setRotationAngle(leftArm, -0.1309F, 0.0F, 0.0F);
		leftArm.texOffs(2, 86).addBox(0.0F, -1.0F, -4.0F, 7.0F, 34.0F, 8.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.jaw.xRot = entity.isAggressive() ? 0.3491F : 0.0F;

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int attackTimer = entityIn.getAttackTimer();
		if (attackTimer > 0) {
			this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTick, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) attackTimer - partialTick, 10.0F);
		}
		else {
			this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(rotbeast);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}