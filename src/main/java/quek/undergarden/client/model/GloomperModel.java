package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.GloomperEntity;

public class GloomperModel<T extends GloomperEntity> extends AgeableListModel<T> {
	private final ModelPart gloomper;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart arms;
	private final ModelPart left;
	private final ModelPart right;
	private final ModelPart feet;

	private float jumpRotation;

	public GloomperModel() {
		texWidth = 64;
		texHeight = 64;

		gloomper = new ModelPart(this);
		gloomper.setPos(0.0F, 24.0F, 0.0F);

		body = new ModelPart(this);
		body.setPos(0.0F, -10.0F, 3.0F);
		gloomper.addChild(body);
		setRotationAngle(body, -0.3491F, 0.0F, 0.0F);
		body.texOffs(0, 0).addBox(-8.0F, -5.0F, -8.0F, 16.0F, 13.0F, 16.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, -10.5F, -5.0F);
		gloomper.addChild(head);
		head.texOffs(0, 31).addBox(-5.0F, -4.5F, -2.0F, 10.0F, 8.0F, 4.0F, 0.0F, false);

		jaw = new ModelPart(this);
		jaw.setPos(0.0F, 3.5F, -2.0F);
		head.addChild(jaw);
		jaw.texOffs(0, 43).addBox(-5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);

		arms = new ModelPart(this);
		arms.setPos(0.5F, -4.5F, -3.5F);
		gloomper.addChild(arms);


		left = new ModelPart(this);
		left.setPos(6.0F, -0.5F, -1.0F);
		arms.addChild(left);
		setRotationAngle(left, -0.5672F, -0.4363F, 0.0F);
		left.texOffs(22, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, true);

		right = new ModelPart(this);
		right.setPos(-7.0F, -0.5F, -1.0F);
		arms.addChild(right);
		setRotationAngle(right, -0.5672F, 0.4363F, 0.0F);
		right.texOffs(22, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		feet = new ModelPart(this);
		feet.setPos(0.0F, -2.0F, 8.0F);
		gloomper.addChild(feet);
		feet.texOffs(28, 32).addBox(7.0F, 0.0F, -7.0F, 4.0F, 2.0F, 7.0F, 0.0F, true);
		feet.texOffs(28, 32).addBox(-11.0F, 0.0F, -7.0F, 4.0F, 2.0F, 7.0F, 0.0F, false);
		feet.texOffs(0, 56).addBox(-11.0F, -5.0F, -3.0F, 4.0F, 5.0F, 3.0F, 0.0F, false);
		feet.texOffs(0, 56).addBox(7.0F, -5.0F, -3.0F, 4.0F, 5.0F, 3.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		float age = ageInTicks - (float)entity.tickCount;
		this.jumpRotation = Mth.sin(entity.getJumpCompletion(age) * (float)Math.PI);

		this.arms.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
		this.feet.xRot = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.jumpRotation = Mth.sin(entityIn.getJumpCompletion(partialTick) * (float)Math.PI);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableSet.of(gloomper);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}