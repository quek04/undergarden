package quek.undergarden.client.model;
// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.boss.MasticatorEntity;

@OnlyIn(Dist.CLIENT)
public class MasticatorModel<T extends MasticatorEntity> extends SegmentedModel<T> {
	private final ModelRenderer masticator;
	private final ModelRenderer head;
	private final ModelRenderer upperjaw;
	private final ModelRenderer innerupperjaw;
	private final ModelRenderer lowerjaw;
	private final ModelRenderer innerlowerjaw;
	private final ModelRenderer body;
	private final ModelRenderer torso;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;

	public MasticatorModel() {
		textureWidth = 256;
		textureHeight = 256;

		masticator = new ModelRenderer(this);
		masticator.setRotationPoint(0.0F, -21.0F, -10.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, 1.0F);
		masticator.addChild(head);
		head.setTextureOffset(0, 46).addBox(7.0F, -6.0F, -4.0F, 0.0F, 10.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(0, 62).addBox(-7.0F, -6.0F, -4.0F, 0.0F, 10.0F, 5.0F, 0.0F, false);

		upperjaw = new ModelRenderer(this);
		upperjaw.setRotationPoint(0.0F, -2.0F, 1.0F);
		head.addChild(upperjaw);
		upperjaw.setTextureOffset(0, 23).addBox(-6.0F, 0.0F, -17.0F, 12.0F, 3.0F, 0.0F, 0.0F, false);
		upperjaw.setTextureOffset(34, 78).addBox(6.0F, 0.0F, -17.0F, 0.0F, 3.0F, 17.0F, 0.0F, false);
		upperjaw.setTextureOffset(34, 81).addBox(-6.0F, 0.0F, -17.0F, 0.0F, 3.0F, 17.0F, 0.0F, false);
		upperjaw.setTextureOffset(78, 51).addBox(-9.0F, -6.0F, -18.0F, 18.0F, 6.0F, 18.0F, 0.0F, false);
		upperjaw.setTextureOffset(0, 51).addBox(-18.0F, -5.0F, -17.0F, 36.0F, 4.0F, 12.0F, 0.0F, false);

		innerupperjaw = new ModelRenderer(this);
		innerupperjaw.setRotationPoint(0.0F, 1.0F, -1.0F);
		upperjaw.addChild(innerupperjaw);
		innerupperjaw.setTextureOffset(86, 0).addBox(-4.0F, -1.0F, -7.0F, 8.0F, 2.0F, 0.0F, 0.0F, false);
		innerupperjaw.setTextureOffset(82, 72).addBox(4.0F, -1.0F, -7.0F, 0.0F, 2.0F, 8.0F, 0.0F, false);
		innerupperjaw.setTextureOffset(69, 59).addBox(-4.0F, -1.0F, -7.0F, 0.0F, 2.0F, 8.0F, 0.0F, false);

		lowerjaw = new ModelRenderer(this);
		lowerjaw.setRotationPoint(0.0F, 2.0F, 1.0F);
		head.addChild(lowerjaw);
		lowerjaw.setTextureOffset(112, 44).addBox(-5.0F, -3.0F, -16.0F, 10.0F, 3.0F, 0.0F, 0.0F, false);
		lowerjaw.setTextureOffset(86, 6).addBox(5.0F, -3.0F, -16.0F, 0.0F, 3.0F, 16.0F, 0.0F, false);
		lowerjaw.setTextureOffset(34, 85).addBox(-5.0F, -3.0F, -16.0F, 0.0F, 3.0F, 16.0F, 0.0F, false);
		lowerjaw.setTextureOffset(86, 0).addBox(-8.0F, 0.0F, -18.0F, 16.0F, 4.0F, 18.0F, 0.0F, false);

		innerlowerjaw = new ModelRenderer(this);
		innerlowerjaw.setRotationPoint(0.0F, -1.0F, 0.0F);
		lowerjaw.addChild(innerlowerjaw);
		innerlowerjaw.setTextureOffset(86, 2).addBox(-4.0F, -1.0F, -8.0F, 8.0F, 2.0F, 0.0F, 0.0F, false);
		innerlowerjaw.setTextureOffset(86, 0).addBox(4.0F, -1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 0.0F, false);
		innerlowerjaw.setTextureOffset(86, 2).addBox(-4.0F, -1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -6.0F, 20.0F);
		masticator.addChild(body);
		body.setTextureOffset(0, 67).addBox(-14.0F, -6.0F, -18.0F, 28.0F, 15.0F, 13.0F, 0.0F, false);
		body.setTextureOffset(112, 22).addBox(-9.0F, 9.0F, -7.0F, 18.0F, 8.0F, 14.0F, 0.0F, false);

		torso = new ModelRenderer(this);
		torso.setRotationPoint(0.0F, 4.0F, -13.0F);
		body.addChild(torso);
		setRotationAngle(torso, 0.4363F, 0.0F, 0.0F);
		torso.setTextureOffset(0, 0).addBox(-15.0F, -11.0F, 0.0F, 30.0F, 25.0F, 26.0F, 0.0F, false);
		torso.setTextureOffset(69, 51).addBox(0.0F, -16.0F, 2.0F, 0.0F, 5.0F, 24.0F, 0.0F, false);
		torso.setTextureOffset(46, 99).addBox(0.0F, -16.0F, 26.0F, 0.0F, 25.0F, 5.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(6.0F, 14.0F, 2.0F);
		body.addChild(leftleg);
		leftleg.setTextureOffset(63, 99).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 18.0F, 5.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-6.0F, 14.0F, 2.0F);
		body.addChild(rightleg);
		rightleg.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 18.0F, 5.0F, 0.0F, true);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(15.0F, -7.0F, 19.0F);
		masticator.addChild(leftarm);
		leftarm.setTextureOffset(128, 75).addBox(0.0F, -6.0F, -7.0F, 14.0F, 13.0F, 14.0F, 0.0F, false);
		leftarm.setTextureOffset(0, 95).addBox(1.0F, 7.0F, -6.0F, 11.0F, 45.0F, 12.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-15.0F, -7.0F, 19.0F);
		masticator.addChild(rightarm);
		rightarm.setTextureOffset(114, 125).addBox(-14.0F, -6.0F, -7.0F, 14.0F, 13.0F, 14.0F, 0.0F, false);
		rightarm.setTextureOffset(82, 82).addBox(-12.0F, 7.0F, -6.0F, 11.0F, 45.0F, 12.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);

		float flap = MathHelper.sin((entity.ticksExisted) * 0.3F) * 0.3F;

		//this.upperjaw.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * limbSwingAmount;
		this.lowerjaw.rotateAngleX = flap * 0.70F;

		this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;

		this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		masticator.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(masticator);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}