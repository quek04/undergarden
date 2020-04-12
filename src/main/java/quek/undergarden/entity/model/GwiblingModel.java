package quek.undergarden.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.GwiblingEntity;

public class GwiblingModel<T extends GwiblingEntity> extends SegmentedModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer topfin;
	private final ModelRenderer rightfin;
	private final ModelRenderer leftfin;
	private final ModelRenderer tail;

	public GwiblingModel() {
		textureWidth = 20;
		textureHeight = 12;

		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.body.addBox(-2.0F, -2.0F, -3.0F, 4, 2, 6);

		//body.cubeList.add(new ModelBox(body, 0, 0, -2.0F, -2.0F, -3.0F, 4, 2, 6, 0.0F, false));
		//body.cubeList.add(new ModelBox(body, 0, 3, 0.0F, -4.0F, -2.0F, 0, 2, 5, 0.0F, false));

		this.topfin = new ModelRenderer(this, 0, 3);
		this.topfin.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.body.addChild(topfin);
		this.topfin.addBox(0.0F, -4.0F, -2.0F, 0, 2, 5);

		this.rightfin = new ModelRenderer(this, 0, 7);
		this.rightfin.setRotationPoint(-3.0F, 0.0F, -2.0F);
		this.setRotationAngle(rightfin, 0.0F, -0.2618F, 0.0F);
		this.body.addChild(rightfin);
		this.rightfin.addBox(1.0F, -1.0F, 0.0F, 0, 2, 3);

		//rightfin.cubeList.add(new ModelBox(rightfin, 0, 7, 1.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, false));

		this.leftfin = new ModelRenderer(this, 0, 7);
		this.leftfin.setRotationPoint(2.0F, 0.0F, -2.0F);
		this.setRotationAngle(leftfin, 0.0F, 0.2618F, 0.0F);
		this.body.addChild(leftfin);
		this.leftfin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 3);

		//leftfin.cubeList.add(new ModelBox(leftfin, 0, 7, 0.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, false));

		this.tail = new ModelRenderer(this, 0 ,0);
		this.tail.setRotationPoint(0.0F, -1.0F, 3.0F);
		this.body.addChild(tail);
		this.tail.addBox(0.0F, -1.0F, 0.0F, 0, 3, 3);

		//tail.cubeList.add(new ModelBox(tail, 0, 0, 0.0F, -1.0F, 0.0F, 0, 3, 3, 0.0F, false));
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entityIn.isInWater()) {
			f = 1.5F;
		}

		this.tail.rotateAngleY = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(
				this.body,
				this.topfin,
				this.rightfin,
				this.leftfin,
				this.tail
		);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}