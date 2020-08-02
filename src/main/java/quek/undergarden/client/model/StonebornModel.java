package quek.undergarden.client.model;
// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.StonebornEntity;

public class StonebornModel<T extends StonebornEntity> extends AgeableModel<T> {
	private final ModelRenderer stoneborn;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer jaw;
	private final ModelRenderer body;
	private final ModelRenderer robe;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;

	public StonebornModel() {
		textureWidth = 88;
		textureHeight = 67;

		stoneborn = new ModelRenderer(this);
		stoneborn.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -29.0F, -1.0F);
		stoneborn.addChild(head);
		head.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 12.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 20).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(32, 8).addBox(-7.0F, -10.0F, 0.0F, 14.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(32, 12).addBox(7.0F, -13.0F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(40, 12).addBox(-9.0F, -15.0F, 0.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(48, 12).addBox(4.0F, -8.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, -7.0F, -3.0F);
		head.addChild(nose);
		setRotationAngle(nose, -0.2182F, 0.0F, 0.0F);
		nose.setTextureOffset(24, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.0F, -1.0F);
		head.addChild(jaw);
		setRotationAngle(jaw, 0.2182F, 0.0F, 0.0F);
		jaw.setTextureOffset(36, 0).addBox(-3.0F, -3.4329F, -2.9526F, 6.0F, 4.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -22.0F, 1.0F);
		stoneborn.addChild(body);
		body.setTextureOffset(0, 26).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, 0.0F, false);

		robe = new ModelRenderer(this);
		robe.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(robe);
		robe.setTextureOffset(40, 23).addBox(-7.0F, -8.0F, -5.0F, 14.0F, 24.0F, 10.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(-6.0F, -26.5F, 1.0F);
		stoneborn.addChild(leftarm);
		leftarm.setTextureOffset(0, 48).addBox(-4.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(7.0F, -26.5F, 1.0F);
		stoneborn.addChild(rightarm);
		rightarm.setTextureOffset(0, 48).addBox(-1.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-3.0F, -15.0F, 0.0F);
		stoneborn.addChild(leftleg);
		leftleg.setTextureOffset(16, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(3.0F, -15.0F, 0.0F);
		stoneborn.addChild(rightleg);
		rightleg.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = 0.0873F + headPitch * ((float)Math.PI / 180F);

		this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableSet.of(stoneborn);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}