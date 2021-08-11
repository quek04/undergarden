package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.cavern.SploogieEntity;

public class SploogieModel<T extends SploogieEntity> extends SegmentedModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer spine1;
	private final ModelRenderer mouth;
	private final ModelRenderer bodySegment1;
	private final ModelRenderer spine2;
	private final ModelRenderer bodySegment2;
	private final ModelRenderer spine3;
	private final ModelRenderer bodySegment3;
	private final ModelRenderer spine4;

	public SploogieModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 20.0F, 0.0F);
		head.texOffs(0, 30).addBox(4.0F, -2.0F, -9.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.texOffs(0, 30).addBox(-8.0F, -2.0F, -9.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
		head.texOffs(0, 0).addBox(-5.0F, -4.0F, -8.0F, 10.0F, 8.0F, 8.0F, 0.0F, false);

		spine1 = new ModelRenderer(this);
		spine1.setPos(0.0F, -4.0F, -5.0F);
		head.addChild(spine1);
		setRotationAngle(spine1, -0.7854F, 0.0F, 0.0F);
		spine1.texOffs(0, 0).addBox(-2.0F, -7.0F, 0.0F, 4.0F, 7.0F, 0.0F, 0.0F, false);

		mouth = new ModelRenderer(this);
		mouth.setPos(0.0F, 4.0F, -8.0F);
		head.addChild(mouth);
		mouth.texOffs(12, 34).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		bodySegment1 = new ModelRenderer(this);
		bodySegment1.setPos(0.0F, 21.0F, 0.0F);
		bodySegment1.texOffs(0, 16).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 7.0F, 7.0F, 0.0F, false);

		spine2 = new ModelRenderer(this);
		spine2.setPos(0.0F, -4.0F, 3.0F);
		bodySegment1.addChild(spine2);
		setRotationAngle(spine2, -0.9599F, 0.0F, 0.0F);
		spine2.texOffs(0, 16).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 6.0F, 0.0F, 0.0F, false);

		bodySegment2 = new ModelRenderer(this);
		bodySegment2.setPos(0.0F, 21.0F, 7.0F);
		bodySegment2.texOffs(24, 24).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		spine3 = new ModelRenderer(this);
		spine3.setPos(0.0F, -3.0F, 3.0F);
		bodySegment2.addChild(spine3);
		setRotationAngle(spine3, -1.1345F, 0.0F, 0.0F);
		spine3.texOffs(23, 16).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);

		bodySegment3 = new ModelRenderer(this);
		bodySegment3.setPos(0.0F, 22.0F, 13.0F);
		bodySegment3.texOffs(30, 16).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		spine4 = new ModelRenderer(this);
		spine4.setPos(0.0F, -2.0F, 2.0F);
		bodySegment3.addChild(spine4);
		setRotationAngle(spine4, -1.309F, 0.0F, 0.0F);
		spine4.texOffs(23, 20).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.head.x = MathHelper.cos(limbSwing * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + limbSwingAmount);

		this.bodySegment1.yRot = MathHelper.cos(limbSwing * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + limbSwingAmount);
		this.bodySegment2.yRot = MathHelper.cos(limbSwing * 0.9F + 1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(1 - 2) + limbSwingAmount);
		this.bodySegment3.yRot = MathHelper.cos(limbSwing * 0.9F + 0 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(-2) + limbSwingAmount);

		this.bodySegment1.x = MathHelper.cos(limbSwing * 0.9F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + limbSwingAmount);
		this.bodySegment2.x = MathHelper.cos(limbSwing * 0.9F + 1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(1 - 2) + limbSwingAmount);
		this.bodySegment3.x = MathHelper.cos(limbSwing * 0.9F + 0 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (1 + Math.abs(-2) + limbSwingAmount);
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(this.head, this.bodySegment1, this.bodySegment2, this.bodySegment3);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}