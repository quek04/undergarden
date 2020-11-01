package quek.undergarden.client.model;
// Made with Blockbench 3.7.0
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.NargoyleEntity;

@OnlyIn(Dist.CLIENT)
public class NargoyleModel<T extends NargoyleEntity> extends SegmentedModel<T> {
	private final ModelRenderer nargoyle;
	private final ModelRenderer body_r1;
	private final ModelRenderer body;
	private final ModelRenderer lowerbody_r1;
	private final ModelRenderer upperbody_r1;
	private final ModelRenderer upperbody;
	private final ModelRenderer arms_r1;
	private final ModelRenderer head_r1;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer arms;
	private final ModelRenderer upperarmright_r1;
	private final ModelRenderer upperarmleft;
	private final ModelRenderer lowerarmleft_r1;
	private final ModelRenderer lowerarmleft;
	private final ModelRenderer upperarmright;
	private final ModelRenderer lowerarmright_r1;
	private final ModelRenderer lowerarmright;
	private final ModelRenderer lowerbody;
	private final ModelRenderer legs_r1;
	private final ModelRenderer legs;
	private final ModelRenderer upperleftleg_r1;
	private final ModelRenderer upperrightleg;
	private final ModelRenderer lowerrightleg_r1;
	private final ModelRenderer lowerrightleg;
	private final ModelRenderer upperleftleg;
	private final ModelRenderer lowerleftleg_r1;
	private final ModelRenderer lowerleftleg;

	public NargoyleModel() {
		textureWidth = 64;
		textureHeight = 64;

		nargoyle = new ModelRenderer(this);
		nargoyle.setRotationPoint(0.0F, 1.0F, 0.0F);

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 2.0F, 0.0F);
		nargoyle.addChild(body_r1);
		setRotationAngle(body_r1, 0.0873F, 0.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 2.0F, 0.0F);
		nargoyle.addChild(body);
		setRotationAngle(body, 0.0873F, 0.0F, 0.0F);

		lowerbody_r1 = new ModelRenderer(this);
		lowerbody_r1.setRotationPoint(0.0F, 2.0F, 5.0F);
		body.addChild(lowerbody_r1);
		setRotationAngle(lowerbody_r1, -1.2217F, 0.0F, 0.0F);

		upperbody_r1 = new ModelRenderer(this);
		upperbody_r1.setRotationPoint(0.0F, 6.0F, 4.0F);
		body.addChild(upperbody_r1);
		setRotationAngle(upperbody_r1, 1.0472F, 0.0F, 0.0F);

		upperbody = new ModelRenderer(this);
		upperbody.setRotationPoint(0.0F, 6.0F, 4.0F);
		body.addChild(upperbody);
		setRotationAngle(upperbody, 1.0472F, 0.0F, 0.0F);
		upperbody.setTextureOffset(0, 0).addBox(-5.0F, -11.0F, 0.0F, 10.0F, 11.0F, 7.0F, 0.0F, false);

		arms_r1 = new ModelRenderer(this);
		arms_r1.setRotationPoint(0.0F, -9.5073F, 3.3309F);
		upperbody.addChild(arms_r1);
		setRotationAngle(arms_r1, -1.2217F, 0.0F, 0.0F);

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, -10.5265F, 2.907F);
		upperbody.addChild(head_r1);
		setRotationAngle(head_r1, 0.4363F, 0.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -10.5265F, 2.907F);
		upperbody.addChild(head);
		setRotationAngle(head, 0.4363F, 0.0F, 0.0F);
		head.setTextureOffset(0, 32).addBox(-4.0F, -9.4735F, -1.907F, 8.0F, 10.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(16, 28).addBox(-3.0F, -8.4735F, -3.907F, 6.0F, 0.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 18).addBox(-5.0F, -1.4735F, -0.907F, 10.0F, 0.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(6, 28).addBox(-2.0F, -9.4735F, -0.907F, 4.0F, 0.0F, 4.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.067F, -1.8264F);
		head.addChild(jaw);
		jaw.setTextureOffset(34, 35).addBox(-4.0F, -7.5405F, -1.0806F, 8.0F, 7.0F, 1.0F, 0.0F, false);
		jaw.setTextureOffset(26, 0).addBox(-2.0F, -6.5405F, -0.0806F, 4.0F, 0.0F, 1.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, -9.5073F, 3.3309F);
		upperbody.addChild(arms);
		setRotationAngle(arms, -1.2217F, 0.0F, 0.0F);

		upperarmright_r1 = new ModelRenderer(this);
		upperarmright_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arms.addChild(upperarmright_r1);
		setRotationAngle(upperarmright_r1, -0.8727F, 0.0F, 0.0F);

		upperarmleft = new ModelRenderer(this);
		upperarmleft.setRotationPoint(0.0F, 0.0F, 0.0F);
		arms.addChild(upperarmleft);
		setRotationAngle(upperarmleft, -0.8727F, 0.0F, 0.0F);
		upperarmleft.setTextureOffset(34, 0).addBox(5.0F, -2.0F, -1.0F, 3.0F, 15.0F, 3.0F, 0.0F, false);

		lowerarmleft_r1 = new ModelRenderer(this);
		lowerarmleft_r1.setRotationPoint(7.0F, 12.0F, 1.0F);
		upperarmleft.addChild(lowerarmleft_r1);
		setRotationAngle(lowerarmleft_r1, 1.1345F, 0.0F, 0.0F);

		lowerarmleft = new ModelRenderer(this);
		lowerarmleft.setRotationPoint(7.0F, 12.0F, 1.0F);
		upperarmleft.addChild(lowerarmleft);
		setRotationAngle(lowerarmleft, 1.1345F, 0.0F, 0.0F);
		lowerarmleft.setTextureOffset(34, 43).addBox(-1.5F, -1.2806F, -1.2086F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		upperarmright = new ModelRenderer(this);
		upperarmright.setRotationPoint(0.0F, 0.0F, 0.0F);
		arms.addChild(upperarmright);
		setRotationAngle(upperarmright, -0.8727F, 0.0F, 0.0F);
		upperarmright.setTextureOffset(22, 35).addBox(-8.0F, -2.0F, -1.0F, 3.0F, 15.0F, 3.0F, 0.0F, false);

		lowerarmright_r1 = new ModelRenderer(this);
		lowerarmright_r1.setRotationPoint(-7.0F, 12.0F, 1.0F);
		upperarmright.addChild(lowerarmright_r1);
		setRotationAngle(lowerarmright_r1, 1.1345F, 0.0F, 0.0F);

		lowerarmright = new ModelRenderer(this);
		lowerarmright.setRotationPoint(-7.0F, 12.0F, 1.0F);
		upperarmright.addChild(lowerarmright);
		setRotationAngle(lowerarmright, 1.1345F, 0.0F, 0.0F);
		lowerarmright.setTextureOffset(42, 43).addBox(-0.5F, -1.2806F, -1.2086F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		lowerbody = new ModelRenderer(this);
		lowerbody.setRotationPoint(0.0F, 2.0F, 5.0F);
		body.addChild(lowerbody);
		setRotationAngle(lowerbody, -1.2217F, 0.0F, 0.0F);
		lowerbody.setTextureOffset(21, 21).addBox(-3.0F, -2.2811F, -1.2679F, 6.0F, 5.0F, 9.0F, 0.0F, false);

		legs_r1 = new ModelRenderer(this);
		legs_r1.setRotationPoint(0.0F, 0.527F, 6.4479F);
		lowerbody.addChild(legs_r1);
		setRotationAngle(legs_r1, 1.2217F, 0.0F, 0.0F);

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 0.527F, 6.4479F);
		lowerbody.addChild(legs);
		setRotationAngle(legs, 1.2217F, 0.0F, 0.0F);

		upperleftleg_r1 = new ModelRenderer(this);
		upperleftleg_r1.setRotationPoint(0.0F, 0.0F, -1.0F);
		legs.addChild(upperleftleg_r1);
		setRotationAngle(upperleftleg_r1, 0.8727F, 0.0F, 0.0F);

		upperrightleg = new ModelRenderer(this);
		upperrightleg.setRotationPoint(0.0F, 0.0F, -1.0F);
		legs.addChild(upperrightleg);
		setRotationAngle(upperrightleg, 0.8727F, 0.0F, 0.0F);
		upperrightleg.setTextureOffset(43, 15).addBox(-5.0F, -1.0F, -1.0F, 2.0F, 10.0F, 3.0F, 0.0F, false);

		lowerrightleg_r1 = new ModelRenderer(this);
		lowerrightleg_r1.setRotationPoint(-3.0F, 8.234F, -0.6428F);
		upperrightleg.addChild(lowerrightleg_r1);
		setRotationAngle(lowerrightleg_r1, -1.2217F, 0.0F, 0.0F);

		lowerrightleg = new ModelRenderer(this);
		lowerrightleg.setRotationPoint(-3.0F, 8.234F, -0.6428F);
		upperrightleg.addChild(lowerrightleg);
		setRotationAngle(lowerrightleg, -1.2217F, 0.0F, 0.0F);
		lowerrightleg.setTextureOffset(6, 45).addBox(-1.5F, -1.1382F, -0.8628F, 1.0F, 10.0F, 2.0F, 0.0F, false);

		upperleftleg = new ModelRenderer(this);
		upperleftleg.setRotationPoint(0.0F, 0.0F, -1.0F);
		legs.addChild(upperleftleg);
		setRotationAngle(upperleftleg, 0.8727F, 0.0F, 0.0F);
		upperleftleg.setTextureOffset(0, 18).addBox(3.0F, -1.0F, -1.0F, 2.0F, 10.0F, 3.0F, 0.0F, false);

		lowerleftleg_r1 = new ModelRenderer(this);
		lowerleftleg_r1.setRotationPoint(3.0F, 8.234F, -0.6428F);
		upperleftleg.addChild(lowerleftleg_r1);
		setRotationAngle(lowerleftleg_r1, -1.2217F, 0.0F, 0.0F);

		lowerleftleg = new ModelRenderer(this);
		lowerleftleg.setRotationPoint(3.0F, 8.234F, -0.6428F);
		upperleftleg.addChild(lowerleftleg);
		setRotationAngle(lowerleftleg, -1.2217F, 0.0F, 0.0F);
		lowerleftleg.setTextureOffset(0, 45).addBox(0.5F, -1.1382F, -0.8628F, 1.0F, 10.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleZ = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleX = 0.4363F + headPitch * ((float)Math.PI / 180F);

		this.arms.rotateAngleX = -0.8727F + MathHelper.cos(limbSwing * 0.20F) * 1.4F * limbSwingAmount;

		this.legs.rotateAngleX = 0.8727F + -(MathHelper.cos(limbSwing * 0.40F) * 1.4F * limbSwingAmount);

		if(entity.isAggressive()) {
			this.jaw.rotateAngleX = this.head.rotateAngleX + MathHelper.sin((entity.ticksExisted) * 0.2F) * 0.2F;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		nargoyle.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(nargoyle);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}