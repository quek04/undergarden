package quek.undergarden.client.model;
// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.GwibEntity;

public class GwibModel<T extends GwibEntity> extends SegmentedModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer tail;
	private final ModelRenderer tailfin;
	private final ModelRenderer rightfin;
	private final ModelRenderer leftfin;
	private final ModelRenderer backfin;

	public GwibModel() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 32).addBox(-8.0F, -16.0F, -10.0F, 16.0F, 16.0F, 20.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-9.0F, -17.0F, -11.0F, 18.0F, 10.0F, 22.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(80, 14).addBox(-6.0F, -12.0F, -17.0F, 12.0F, 8.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(74, 74).addBox(-7.0F, -13.0F, -18.0F, 14.0F, 10.0F, 8.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -8.0F, 10.0F);
		body.addChild(tail);
		tail.setTextureOffset(73, 43).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 11.0F, 9.0F, 0.0F, false);

		tailfin = new ModelRenderer(this);
		tailfin.setRotationPoint(0.0F, -1.0F, 6.0F);
		tail.addChild(tailfin);
		tailfin.setTextureOffset(44, 53).addBox(0.0F, -14.0F, -1.0F, 0.0F, 23.0F, 15.0F, 0.0F, false);

		rightfin = new ModelRenderer(this);
		rightfin.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(rightfin);
		rightfin.setTextureOffset(44, 0).addBox(-23.0F, -6.0F, -8.0F, 15.0F, 0.0F, 14.0F, 0.0F, false);

		leftfin = new ModelRenderer(this);
		leftfin.setRotationPoint(-8.0F, -6.0F, 0.0F);
		body.addChild(leftfin);
		leftfin.setTextureOffset(38, 38).addBox(16.0F, 0.0F, -8.0F, 15.0F, 0.0F, 14.0F, 0.0F, false);

		backfin = new ModelRenderer(this);
		backfin.setRotationPoint(0.0F, -18.0F, 0.0F);
		body.addChild(backfin);
		backfin.setTextureOffset(0, 46).addBox(0.0F, -14.0F, -8.0F, 0.0F, 15.0F, 22.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}

		this.tail.rotateAngleY = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(this.body);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}