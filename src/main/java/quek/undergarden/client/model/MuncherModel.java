package quek.undergarden.client.model;
// Made with Blockbench 3.7.4

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.cavern.MuncherEntity;

public class MuncherModel<T extends MuncherEntity> extends SegmentedModel<T> {
	private final ModelRenderer muncher;
	private final ModelRenderer lowerjaw;
	private final ModelRenderer upperjaw;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;

	public MuncherModel() {
		texWidth = 64;
		texHeight = 64;

		muncher = new ModelRenderer(this);
		muncher.setPos(0.0F, 16.0F, 5.0F);

		lowerjaw = new ModelRenderer(this);
		lowerjaw.setPos(0.0F, 5.0F, -5.0F);
		muncher.addChild(lowerjaw);
		lowerjaw.texOffs(0, 15).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		lowerjaw.texOffs(0, 29).addBox(-8.0F, -5.0F, 3.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);

		upperjaw = new ModelRenderer(this);
		upperjaw.setPos(0.0F, 0.0F, 0.0F);
		muncher.addChild(upperjaw);
		upperjaw.texOffs(0, 0).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);
		upperjaw.texOffs(0, 25).addBox(0.0F, -6.0F, -6.0F, 0.0F, 1.0F, 6.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(-3.0F, 4.0F, -4.0F);
		muncher.addChild(leftleg);
		leftleg.texOffs(30, 15).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(3.0F, 4.0F, -4.0F);
		muncher.addChild(rightleg);
		rightleg.texOffs(30, 0).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setPos(5.0F, 2.0F, -3.0F);
		muncher.addChild(leftarm);
		leftarm.texOffs(0, 15).addBox(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setPos(-5.0F, 2.0F, -3.0F);
		muncher.addChild(rightarm);
		rightarm.texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.upperjaw.xRot = (MathHelper.sin((entity.tickCount) * 0.5F) * 0.9F) * 0.3F;

		this.leftarm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftleg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		muncher.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableSet.of(this.muncher);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}