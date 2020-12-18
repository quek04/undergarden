package quek.undergarden.client.model;
// Made with Blockbench 3.7.4

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import quek.undergarden.entity.MuncherEntity;

public class MuncherModel<T extends MuncherEntity> extends SegmentedModel<T> {
	private final ModelRenderer muncher;
	private final ModelRenderer lowerjaw;
	private final ModelRenderer upperjaw;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;

	public MuncherModel() {
		textureWidth = 64;
		textureHeight = 64;

		muncher = new ModelRenderer(this);
		muncher.setRotationPoint(0.0F, 16.0F, 5.0F);

		lowerjaw = new ModelRenderer(this);
		lowerjaw.setRotationPoint(0.0F, 5.0F, -5.0F);
		muncher.addChild(lowerjaw);
		lowerjaw.setTextureOffset(0, 15).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		lowerjaw.setTextureOffset(0, 29).addBox(-8.0F, -5.0F, 3.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);

		upperjaw = new ModelRenderer(this);
		upperjaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		muncher.addChild(upperjaw);
		upperjaw.setTextureOffset(0, 0).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);
		upperjaw.setTextureOffset(0, 25).addBox(0.0F, -6.0F, -6.0F, 0.0F, 1.0F, 6.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-3.0F, 4.0F, -4.0F);
		muncher.addChild(leftleg);
		leftleg.setTextureOffset(30, 15).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(3.0F, 4.0F, -4.0F);
		muncher.addChild(rightleg);
		rightleg.setTextureOffset(30, 0).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 2.0F, -3.0F);
		muncher.addChild(leftarm);
		leftarm.setTextureOffset(0, 15).addBox(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, 2.0F, -3.0F);
		muncher.addChild(rightarm);
		rightarm.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		muncher.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(this.muncher);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}