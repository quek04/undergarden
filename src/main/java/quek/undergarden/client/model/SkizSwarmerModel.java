package quek.undergarden.client.model;
// Made with Blockbench 3.5.0
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.SkizSwarmerEntity;

@OnlyIn(Dist.CLIENT)
public class SkizSwarmerModel<T extends SkizSwarmerEntity> extends AgeableModel<T> {
	private final ModelRenderer skiz;
	private final ModelRenderer head;
	private final ModelRenderer lefteye;
	private final ModelRenderer righteye;
	private final ModelRenderer thorax;
	private final ModelRenderer rightwing;
	private final ModelRenderer leftwing;
	private final ModelRenderer rightlegs;
	private final ModelRenderer leftlegs;

	public SkizSwarmerModel() {
		textureWidth = 32;
		textureHeight = 32;

		skiz = new ModelRenderer(this);
		skiz.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, 2.0F);
		skiz.addChild(head);
		head.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 3.5F, 0.0F, false);

		lefteye = new ModelRenderer(this);
		lefteye.setRotationPoint(4.0F, 1.0F, -2.0F);
		head.addChild(lefteye);
		lefteye.setTextureOffset(15, 0).addBox(-3.0F, -3.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		righteye = new ModelRenderer(this);
		righteye.setRotationPoint(0.0F, 1.0F, -2.0F);
		head.addChild(righteye);
		righteye.setTextureOffset(16, 16).addBox(-3.0F, -3.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		thorax = new ModelRenderer(this);
		thorax.setRotationPoint(0.0F, -1.0F, 2.0F);
		skiz.addChild(thorax);
		setRotationAngle(thorax, 0.7854F, 0.0F, 0.0F);
		thorax.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 11.0F, 0.0F, false);

		rightwing = new ModelRenderer(this);
		rightwing.setRotationPoint(0.0F, -3.5355F, 3.5355F);
		thorax.addChild(rightwing);
		setRotationAngle(rightwing, 0.0F, 0.0F, -0.9599F);
		rightwing.setTextureOffset(8, 9).addBox(-1.0F, -8.0F, -3.0F, 0.0F, 8.0F, 4.0F, 0.0F, false);

		leftwing = new ModelRenderer(this);
		leftwing.setRotationPoint(0.0F, -3.5355F, 3.5355F);
		thorax.addChild(leftwing);
		setRotationAngle(leftwing, 0.0F, 0.0F, 0.9599F);
		leftwing.setTextureOffset(0, 9).addBox(1.0F, -8.0F, -3.0F, 0.0F, 8.0F, 4.0F, 0.0F, false);

		rightlegs = new ModelRenderer(this);
		rightlegs.setRotationPoint(-2.0F, -0.7071F, 0.7071F);
		thorax.addChild(rightlegs);
		setRotationAngle(rightlegs, 0.0F, 0.0F, 0.5236F);
		rightlegs.setTextureOffset(15, 0).addBox(1.0F, -1.2929F, -0.7071F, 0.0F, 4.0F, 5.0F, 0.0F, false);

		leftlegs = new ModelRenderer(this);
		leftlegs.setRotationPoint(2.0F, -0.7071F, 0.7071F);
		thorax.addChild(leftlegs);
		setRotationAngle(leftlegs, 0.0F, 0.0F, -0.5236F);
		leftlegs.setTextureOffset(0, 0).addBox(-1.0F, -1.2929F, -0.7071F, 0.0F, 4.0F, 5.0F, 0.0F, false);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableSet.of(skiz);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		skiz.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}