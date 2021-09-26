package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.MogEntity;

public class MogModel<E extends MogEntity> extends AgeableListModel<E> {
	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart backLegRight;
	private final ModelPart backLegLeft;
	private final ModelPart head;
	private final ModelPart body;

	public MogModel() {
		texWidth = 64;
		texHeight = 64;

		frontLegLeft = new ModelPart(this);
		frontLegLeft.setPos(2.0F, 20.0F, -2.0F);
		frontLegLeft.texOffs(32, 36).addBox(0.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);

		frontLegRight = new ModelPart(this);
		frontLegRight.setPos(-2.0F, 20.0F, -2.0F);
		frontLegRight.texOffs(20, 36).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		backLegRight = new ModelPart(this);
		backLegRight.setPos(2.0F, 20.0F, 2.0F);
		backLegRight.texOffs(0, 0).addBox(-7.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		backLegLeft = new ModelPart(this);
		backLegLeft.setPos(-2.0F, 20.0F, 2.0F);
		backLegLeft.texOffs(36, 0).addBox(4.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);

		head = new ModelPart(this);
		head.setPos(0.0F, 20.0F, -6.0F);
		head.texOffs(26, 26).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		body = new ModelPart(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 35).addBox(0.0F, -21.0F, -4.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(-6.0F, -17.0F, -6.0F, 12.0F, 14.0F, 12.0F, 0.0F, false);
		body.texOffs(0, 26).addBox(-4.0F, -19.0F, -3.0F, 6.0F, 2.0F, 7.0F, 0.0F, false);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.head, this.body, this.frontLegLeft, this.frontLegRight, this.backLegLeft, this.backLegRight);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.body.zRot = 0.1F * Mth.sin(limbSwing * 2.0F) * 4.0F * limbSwingAmount;

		this.frontLegLeft.xRot = Mth.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
		this.frontLegRight.xRot = Mth.cos(limbSwing * 2.0F + (float)Math.PI) * 4.0F * limbSwingAmount;
		this.backLegLeft.xRot = Mth.cos(limbSwing * 2.0F + (float)Math.PI) * 4.0F * limbSwingAmount;
		this.backLegRight.xRot = Mth.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
	}
}