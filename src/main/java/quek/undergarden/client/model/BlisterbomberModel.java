package quek.undergarden.client.model;
// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BlisterbomberModel<T extends Entity> extends SegmentedModel<T> {
	private final ModelRenderer blisterbomber;
	private final ModelRenderer body;
	private final ModelRenderer blade;
	private final ModelRenderer head;

	public BlisterbomberModel() {
		textureWidth = 128;
		textureHeight = 128;

		blisterbomber = new ModelRenderer(this);
		blisterbomber.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		blisterbomber.addChild(body);
		body.setTextureOffset(0, 0).addBox(-1.0F, -37.0F, -1.0F, 2.0F, 37.0F, 2.0F, 0.0F, false);

		blade = new ModelRenderer(this);
		blade.setRotationPoint(0.0F, -21.0F, 0.0F);
		body.addChild(blade);
		blade.setTextureOffset(26, 14).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
		blade.setTextureOffset(0, 54).addBox(-27.0F, 0.0F, -2.0F, 54.0F, 0.0F, 4.0F, 0.0F, false);
		blade.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -27.0F, 4.0F, 0.0F, 54.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -31.0F, 0.0F);
		blisterbomber.addChild(head);
		head.setTextureOffset(26, 0).addBox(-3.0F, -4.0F, -7.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(26, 26).addBox(-7.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(8, 20).addBox(1.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(8, 8).addBox(-3.0F, -4.0F, 1.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableSet.of(blisterbomber);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.blade.rotateAngleY = entity.ticksExisted * 0.5F;
		this.blisterbomber.rotateAngleX = MathHelper.sin((entity.ticksExisted) * 0.3F) * 0.3F;
		this.blisterbomber.rotateAngleZ = MathHelper.sin((entity.ticksExisted) * 0.3F) * 0.3F;
		this.blisterbomber.rotateAngleY = MathHelper.sin((entity.ticksExisted) * 0.3F) * 0.3F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		blisterbomber.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}