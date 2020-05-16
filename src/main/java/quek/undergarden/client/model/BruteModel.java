package quek.undergarden.client.model;
// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.BruteEntity;

@OnlyIn(Dist.CLIENT)
public class BruteModel<T extends BruteEntity> extends AgeableModel<T> {
	private final ModelRenderer brute;
	private final ModelRenderer uppertorso;
	private final ModelRenderer lowertorso;
	private final ModelRenderer head;
	private final ModelRenderer horns;
	private final ModelRenderer horns2;
	private final ModelRenderer leftarm;
	private final ModelRenderer leftarm2;
	private final ModelRenderer lefthand;
	private final ModelRenderer rightarm;
	private final ModelRenderer rightarm2;
	private final ModelRenderer righthand;
	private final ModelRenderer leftleg;
	private final ModelRenderer leftleg2;
	private final ModelRenderer rightleg;
	private final ModelRenderer rightleg2;

	public BruteModel() {
		textureWidth = 128;
		textureHeight = 128;

		brute = new ModelRenderer(this);
		brute.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		uppertorso = new ModelRenderer(this);
		uppertorso.setRotationPoint(0.0F, -27.5F, 1.0F);
		brute.addChild(uppertorso);
		setRotationAngle(uppertorso, 0.8727F, 0.0F, 0.0F);
		uppertorso.setTextureOffset(0, 0).addBox(-7.0F, -7.5F, -4.0F, 14.0F, 15.0F, 8.0F, 0.0F, false);
		uppertorso.setTextureOffset(78, 22).addBox(0.0F, -7.5F, 4.0F, 0.0F, 14.0F, 6.0F, 0.0F, false);

		lowertorso = new ModelRenderer(this);
		lowertorso.setRotationPoint(0.0F, 10.4973F, -1.24F);
		uppertorso.addChild(lowertorso);
		setRotationAngle(lowertorso, -0.5236F, 0.0F, 0.0F);
		lowertorso.setTextureOffset(0, 23).addBox(-6.0F, -5.9973F, -2.76F, 12.0F, 12.0F, 5.0F, 0.0F, false);
		lowertorso.setTextureOffset(90, 29).addBox(0.0F, -5.9973F, 2.24F, 0.0F, 9.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -28.0F, -3.0F);
		brute.addChild(head);
		head.setTextureOffset(44, 0).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 5.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(45, 14).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 2.0F, 8.0F, 0.0F, false);

		horns = new ModelRenderer(this);
		horns.setRotationPoint(0.0F, 4.0F, -6.0F);
		head.addChild(horns);
		setRotationAngle(horns, 1.2217F, 0.0F, 0.0F);
		horns.setTextureOffset(48, 41).addBox(-5.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);
		horns.setTextureOffset(48, 41).addBox(3.5F, -2.0F, -3.0F, 2.0F, 3.0F, 8.0F, 0.0F, true);

		horns2 = new ModelRenderer(this);
		horns2.setRotationPoint(0.0F, 0.0F, -2.0F);
		horns.addChild(horns2);
		setRotationAngle(horns2, -1.5708F, 0.0F, 0.0F);
		horns2.setTextureOffset(32, 45).addBox(-5.5F, 1.0F, -5.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		horns2.setTextureOffset(32, 45).addBox(3.5F, 1.0F, -5.0F, 2.0F, 1.0F, 6.0F, 0.0F, true);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(7.0F, -26.0F, -1.0F);
		brute.addChild(leftarm);
		setRotationAngle(leftarm, 0.2618F, 0.0F, 0.0F);
		leftarm.setTextureOffset(34, 23).addBox(0.0F, -4.0F, -3.0F, 3.0F, 17.0F, 5.0F, 0.0F, false);

		leftarm2 = new ModelRenderer(this);
		leftarm2.setRotationPoint(0.0F, 13.0F, 0.0F);
		leftarm.addChild(leftarm2);
		setRotationAngle(leftarm2, -0.7854F, 0.0F, 0.0F);
		leftarm2.setTextureOffset(50, 24).addBox(0.1F, -2.0F, -2.5F, 3.0F, 13.0F, 4.0F, 0.0F, false);

		lefthand = new ModelRenderer(this);
		lefthand.setRotationPoint(1.0F, 24.0F, -9.0F);
		leftarm.addChild(lefthand);
		setRotationAngle(lefthand, -0.2618F, 0.0F, 0.0F);
		lefthand.setTextureOffset(64, 24).addBox(-1.0F, -4.5F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-7.0F, -26.0F, -1.0F);
		brute.addChild(rightarm);
		setRotationAngle(rightarm, 0.2618F, 0.0F, 0.0F);
		rightarm.setTextureOffset(34, 23).addBox(-3.0F, -4.0F, -3.0F, 3.0F, 17.0F, 5.0F, 0.0F, true);

		rightarm2 = new ModelRenderer(this);
		rightarm2.setRotationPoint(-3.0F, 13.0F, 0.0F);
		rightarm.addChild(rightarm2);
		setRotationAngle(rightarm2, -0.7854F, 0.0F, 0.0F);
		rightarm2.setTextureOffset(50, 24).addBox(-0.1F, -2.0F, -2.5F, 3.0F, 13.0F, 4.0F, 0.0F, true);

		righthand = new ModelRenderer(this);
		righthand.setRotationPoint(-2.0F, 24.0F, -9.0F);
		rightarm.addChild(righthand);
		setRotationAngle(righthand, -0.2618F, 0.0F, 0.0F);
		righthand.setTextureOffset(64, 24).addBox(-1.0F, -4.5F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(4.0F, -14.0F, 9.0F);
		brute.addChild(leftleg);
		setRotationAngle(leftleg, -0.2618F, 0.0F, 0.0F);
		leftleg.setTextureOffset(0, 40).addBox(-1.9F, -1.0F, -1.7412F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		leftleg2 = new ModelRenderer(this);
		leftleg2.setRotationPoint(-0.5F, 8.1933F, 2.8294F);
		leftleg.addChild(leftleg2);
		setRotationAngle(leftleg2, 0.2618F, 0.0F, 0.0F);
		leftleg2.setTextureOffset(16, 40).addBox(-1.5F, -3.6274F, -3.7294F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-4.0F, -14.0F, 9.0F);
		brute.addChild(rightleg);
		setRotationAngle(rightleg, -0.2618F, 0.0F, 0.0F);
		rightleg.setTextureOffset(0, 40).addBox(-2.1F, -1.0341F, -1.7412F, 4.0F, 8.0F, 4.0F, 0.0F, true);

		rightleg2 = new ModelRenderer(this);
		rightleg2.setRotationPoint(-0.5F, 8.1933F, 2.8294F);
		rightleg.addChild(rightleg2);
		setRotationAngle(rightleg2, 0.2618F, 0.0F, 0.0F);
		rightleg2.setTextureOffset(16, 40).addBox(-1.5F, -3.6274F, -3.7294F, 4.0F, 9.0F, 4.0F, 0.0F, true);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableSet.of(brute);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);

		this.leftleg.rotateAngleX = -0.2618F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.rotateAngleX = -0.2618F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftarm.rotateAngleX = 0.2618F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.rotateAngleX = 0.2618F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		brute.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}