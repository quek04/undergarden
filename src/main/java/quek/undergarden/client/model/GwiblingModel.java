package quek.undergarden.client.model;
// Made with Blockbench 3.5.0
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.GwiblingEntity;

public class GwiblingModel<T extends GwiblingEntity> extends SegmentedModel<T> {
	private final ModelRenderer gwibling;
	private final ModelRenderer body;
	private final ModelRenderer rightfin;
	private final ModelRenderer leftfin;
	private final ModelRenderer tail;

	public GwiblingModel() {
		textureWidth = 20;
		textureHeight = 12;

		gwibling = new ModelRenderer(this);
		gwibling.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		gwibling.addChild(body);
		body.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 2.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 3).addBox(0.0F, -4.0F, -2.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);

		rightfin = new ModelRenderer(this);
		rightfin.setRotationPoint(-3.0F, 0.0F, -2.0F);
		body.addChild(rightfin);
		setRotationAngle(rightfin, 0.0F, -0.2618F, 0.0F);
		rightfin.setTextureOffset(0, 7).addBox(1.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		leftfin = new ModelRenderer(this);
		leftfin.setRotationPoint(2.0F, 0.0F, -2.0F);
		body.addChild(leftfin);
		setRotationAngle(leftfin, 0.0F, 0.2618F, 0.0F);
		leftfin.setTextureOffset(0, 7).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -1.0F, 3.0F);
		body.addChild(tail);
		tail.setTextureOffset(0, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		gwibling.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(this.gwibling);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}