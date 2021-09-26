package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.ScintlingEntity;

public class ScintlingModel<T extends ScintlingEntity> extends AgeableListModel<T> {
	private final ModelPart scintling;
	private final ModelPart head;
	private final ModelPart leftStalk;
	private final ModelPart rightStalk;
	private final ModelPart torso;
	private final ModelPart tail;

	public ScintlingModel() {
		texWidth = 64;
		texHeight = 64;

		scintling = new ModelPart(this);
		scintling.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelPart(this);
		head.setPos(0.0F, -2.0F, -7.0F);
		scintling.addChild(head);
		head.texOffs(26, 18).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		leftStalk = new ModelPart(this);
		leftStalk.setPos(2.0F, -2.0F, -5.0F);
		head.addChild(leftStalk);
		setRotationAngle(leftStalk, 0.3491F, 0.0F, 0.1745F);
		leftStalk.texOffs(20, 28).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		rightStalk = new ModelPart(this);
		rightStalk.setPos(-2.0F, -2.0F, -5.0F);
		head.addChild(rightStalk);
		setRotationAngle(rightStalk, 0.3491F, 0.0F, -0.1745F);
		rightStalk.texOffs(20, 28).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, true);

		torso = new ModelPart(this);
		torso.setPos(0.0F, 0.0F, 0.0F);
		scintling.addChild(torso);
		torso.texOffs(0, 0).addBox(-4.0F, -5.0F, -7.0F, 8.0F, 5.0F, 13.0F, 0.0F, false);

		tail = new ModelPart(this);
		tail.setPos(0.0F, -2.0F, 6.0F);
		scintling.addChild(tail);
		tail.texOffs(0, 18).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 4.0F, 5.0F, 0.0F, false);
		tail.texOffs(0, 28).addBox(-3.0F, -1.0F, 5.0F, 6.0F, 3.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.torso.yRot = Mth.cos(limbSwing * 0.5F + (float)Math.PI) * 0.5F * limbSwingAmount;

		this.tail.yRot = Mth.cos(limbSwing * 0.5F) * 0.5F * limbSwingAmount;

		float wiggle = Mth.sin((entity.tickCount) * 0.3F) * 0.3F;

		this.leftStalk.xRot = wiggle;
		this.rightStalk.xRot = -wiggle;
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.scintling);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}