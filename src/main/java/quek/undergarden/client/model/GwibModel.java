package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.GwibEntity;

public class GwibModel<T extends GwibEntity> extends ListModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart tailfin;
	private final ModelPart rightfin;
	private final ModelPart leftfin;
	private final ModelPart backfin;

	public GwibModel() {
		texWidth = 128;
		texHeight = 128;

		body = new ModelPart(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 32).addBox(-8.0F, -16.0F, -10.0F, 16.0F, 16.0F, 20.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(-9.0F, -17.0F, -11.0F, 18.0F, 10.0F, 22.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.texOffs(80, 14).addBox(-6.0F, -12.0F, -17.0F, 12.0F, 8.0F, 7.0F, 0.0F, false);
		head.texOffs(74, 74).addBox(-7.0F, -13.0F, -18.0F, 14.0F, 10.0F, 8.0F, 0.0F, false);

		tail = new ModelPart(this);
		tail.setPos(0.0F, -8.0F, 10.0F);
		body.addChild(tail);
		tail.texOffs(73, 43).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 11.0F, 9.0F, 0.0F, false);

		tailfin = new ModelPart(this);
		tailfin.setPos(0.0F, -1.0F, 6.0F);
		tail.addChild(tailfin);
		tailfin.texOffs(44, 53).addBox(0.0F, -14.0F, -1.0F, 0.0F, 23.0F, 15.0F, 0.0F, false);

		rightfin = new ModelPart(this);
		rightfin.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(rightfin);
		rightfin.texOffs(44, 0).addBox(-23.0F, -6.0F, -8.0F, 15.0F, 0.0F, 14.0F, 0.0F, false);

		leftfin = new ModelPart(this);
		leftfin.setPos(-8.0F, -6.0F, 0.0F);
		body.addChild(leftfin);
		leftfin.texOffs(38, 38).addBox(16.0F, 0.0F, -8.0F, 15.0F, 0.0F, 14.0F, 0.0F, false);

		backfin = new ModelPart(this);
		backfin.setPos(0.0F, -18.0F, 0.0F);
		body.addChild(backfin);
		backfin.texOffs(0, 46).addBox(0.0F, -14.0F, -8.0F, 0.0F, 15.0F, 22.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}

		this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableSet.of(this.body);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}