package quek.undergarden.client.model;
// Made with Blockbench 3.5.0
// Exported for Minecraft version 1.15

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.RotbeastEntity;

public class RotbeastModel<T extends RotbeastEntity> extends BipedModel<T> {
	private final ModelRenderer rotbeast;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer leftarm;
	private final ModelRenderer lowerarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer lowerarm2;
	private final ModelRenderer torso;
	private final ModelRenderer torsolower;
	private final ModelRenderer leftleg;
	private final ModelRenderer lowerleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer lowerleg2;

	public RotbeastModel() {
		super(0F, -14F, 128, 128);
		textureWidth = 128;
		textureHeight = 128;

		rotbeast = new ModelRenderer(this);
		rotbeast.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, -8.0F);
		rotbeast.addChild(head);
		setRotationAngle(head, 0.3491F, 0.0F, 0.0F);
		head.setTextureOffset(0, 22).addBox(-5.0F, -9.0F, -7.0F, 10.0F, 6.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(0, 83).addBox(-3.0F, -8.0F, -1.0F, 6.0F, 9.0F, 2.0F, 0.0F, true);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(jaw);
		setRotationAngle(jaw, 0.2618F, 0.0F, 0.0F);
		jaw.setTextureOffset(22, 66).addBox(-5.0F, -3.0F, -6.0F, 10.0F, 1.0F, 6.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(10.0F, -32.0F, -4.0F);
		rotbeast.addChild(leftarm);
		setRotationAngle(leftarm, 0.2618F, 0.0F, 0.0F);
		leftarm.setTextureOffset(34, 22).addBox(0.0F, -2.0F, -3.0F, 5.0F, 18.0F, 6.0F, 0.0F, false);

		lowerarm = new ModelRenderer(this);
		lowerarm.setRotationPoint(2.0F, 16.0F, 0.0F);
		leftarm.addChild(lowerarm);
		setRotationAngle(lowerarm, -0.3491F, 0.0F, 0.0F);
		lowerarm.setTextureOffset(0, 46).addBox(-2.0F, -1.0F, -3.0F, 5.0F, 14.0F, 6.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-10.0F, -32.0F, -4.0F);
		rotbeast.addChild(rightarm);
		setRotationAngle(rightarm, 0.2618F, 0.0F, 0.0F);
		rightarm.setTextureOffset(34, 22).addBox(-5.0F, -2.0F, -3.0F, 5.0F, 18.0F, 6.0F, 0.0F, true);

		lowerarm2 = new ModelRenderer(this);
		lowerarm2.setRotationPoint(-2.0F, 16.0F, 0.0F);
		rightarm.addChild(lowerarm2);
		setRotationAngle(lowerarm2, -0.3491F, 0.0F, 0.0F);
		lowerarm2.setTextureOffset(0, 46).addBox(-3.0F, -1.0F, -3.0F, 5.0F, 14.0F, 6.0F, 0.0F, true);

		torso = new ModelRenderer(this);
		torso.setRotationPoint(0.0F, -30.0F, -3.0F);
		rotbeast.addChild(torso);
		setRotationAngle(torso, 0.6109F, 0.0F, 0.0F);
		torso.setTextureOffset(0, 0).addBox(-10.0F, -7.0F, -5.0F, 20.0F, 12.0F, 10.0F, 0.0F, false);

		torsolower = new ModelRenderer(this);
		torsolower.setRotationPoint(0.0F, 8.0F, -2.0F);
		torso.addChild(torsolower);
		setRotationAngle(torsolower, -0.3491F, 0.0F, 0.0F);
		torsolower.setTextureOffset(60, 0).addBox(-8.0F, -5.0F, -1.0F, 16.0F, 11.0F, 6.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(4.0F, -17.0F, 3.0F);
		rotbeast.addChild(leftleg);
		setRotationAngle(leftleg, -0.3491F, 0.0F, 0.0F);
		leftleg.setTextureOffset(54, 66).addBox(-2.0F, -1.0F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		lowerleg = new ModelRenderer(this);
		lowerleg.setRotationPoint(0.0F, 7.0F, 1.0F);
		leftleg.addChild(lowerleg);
		setRotationAngle(lowerleg, 0.3491F, 0.0F, 0.0F);
		lowerleg.setTextureOffset(44, 46).addBox(-2.0F, -1.0F, -4.0F, 5.0F, 11.0F, 6.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-4.0F, -17.0F, 3.0F);
		rotbeast.addChild(rightleg);
		setRotationAngle(rightleg, -0.3491F, 0.0F, 0.0F);
		rightleg.setTextureOffset(54, 66).addBox(-3.0F, -1.0F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, true);

		lowerleg2 = new ModelRenderer(this);
		lowerleg2.setRotationPoint(0.0F, 7.0F, 1.0F);
		rightleg.addChild(lowerleg2);
		setRotationAngle(lowerleg2, 0.3491F, 0.0F, 0.0F);
		lowerleg2.setTextureOffset(44, 46).addBox(-3.0F, -1.0F, -4.0F, 5.0F, 11.0F, 6.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = 0.3491F + headPitch * ((float)Math.PI / 180F);

		this.leftleg.rotateAngleX = -0.3491F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.rightleg.rotateAngleX = -0.3491F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int i = entityIn.getAttackTimer();
		if (i > 0) {
			this.rightarm.rotateAngleX = 0.2618F + -2.0F + 1.5F * this.triangleWave((float)i - partialTick, 10.0F);
			this.leftarm.rotateAngleX = 0.2618F + -2.0F + 1.5F * this.triangleWave((float)i - partialTick, 10.0F);
		} else {
			this.rightarm.rotateAngleX = (0.2618F + -0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftarm.rotateAngleX = (0.2618F + -0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}
	}

	private float triangleWave(float p_78172_1_, float p_78172_2_) {
		return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		rotbeast.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}