package quek.undergarden.client.model;
// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.rotspawn.RotlingEntity;

@OnlyIn(Dist.CLIENT)
public class RotlingModel<T extends RotlingEntity> extends SegmentedModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;

	public RotlingModel() {
		texWidth = 64;
		texHeight = 64;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.5F, 0.0F);
		body.texOffs(0, 17).addBox(-4.0F, -10.5F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
		body.texOffs(0, 38).addBox(-4.0F, -12.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -11.5F, 4.0F);
		body.addChild(head);
		head.texOffs(0, 0).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 5.0F, 8.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-4.0F, -3.5F, 1.0F);
		body.addChild(rightLeg);
		rightLeg.texOffs(24, 2).addBox(0.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(4.0F, -3.5F, 1.0F);
		body.addChild(leftLeg);
		leftLeg.texOffs(24, 2).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-3.0F, -10.5F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 0.0F, 0.4363F);
		rightArm.texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(4.0F, -11.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, -0.4363F);
		leftArm.texOffs(0, 0).addBox(-1.2817F, 0.0977F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = entity.isAggressive() ? -0.5235F : -0.1745F;

		this.leftArm.xRot = entity.isAggressive() ? -1.5F : MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = entity.isAggressive() ? -1.5F : MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(body);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}