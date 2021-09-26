package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.GwiblingEntity;

public class GwiblingModel<T extends GwiblingEntity> extends ListModel<T> {
	private final ModelPart gwibling;
	private final ModelPart body;
	private final ModelPart rightfin;
	private final ModelPart leftfin;
	private final ModelPart tail;

	public GwiblingModel() {
		texWidth = 20;
		texHeight = 12;

		gwibling = new ModelPart(this);
		gwibling.setPos(0.0F, 24.0F, 0.0F);

		body = new ModelPart(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		gwibling.addChild(body);
		body.texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 2.0F, 6.0F, 0.0F, false);
		body.texOffs(0, 3).addBox(0.0F, -4.0F, -2.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);

		rightfin = new ModelPart(this);
		rightfin.setPos(-3.0F, 0.0F, -2.0F);
		body.addChild(rightfin);
		setRotationAngle(rightfin, 0.0F, -0.2618F, 0.0F);
		rightfin.texOffs(0, 7).addBox(1.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		leftfin = new ModelPart(this);
		leftfin.setPos(2.0F, 0.0F, -2.0F);
		body.addChild(leftfin);
		setRotationAngle(leftfin, 0.0F, 0.2618F, 0.0F);
		leftfin.texOffs(0, 7).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		tail = new ModelPart(this);
		tail.setPos(0.0F, -1.0F, 3.0F);
		body.addChild(tail);
		tail.texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
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
		return ImmutableSet.of(this.gwibling);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}