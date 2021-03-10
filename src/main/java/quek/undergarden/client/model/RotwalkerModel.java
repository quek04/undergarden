package quek.undergarden.client.model;
// Made with Blockbench 3.5.0
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.rotspawn.RotwalkerEntity;

@OnlyIn(Dist.CLIENT)
public class RotwalkerModel<T extends RotwalkerEntity> extends SegmentedModel<T> {
	private final ModelRenderer rotwalker;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer neck;
	private final ModelRenderer torso1;
	private final ModelRenderer torso2;
	private final ModelRenderer pelvis;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public RotwalkerModel() {
		texWidth = 64;
		texHeight = 64;

		rotwalker = new ModelRenderer(this);
		rotwalker.setPos(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -34.0F, 0.0F);
		rotwalker.addChild(head);
		setRotationAngle(head, 0.0873F, 0.0F, 0.0F);
		head.texOffs(0, 13).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setPos(0.0F, -2.0F, 1.0F);
		head.addChild(jaw);
		setRotationAngle(jaw, -0.0873F, 0.0F, 0.0F);
		jaw.texOffs(25, 25).addBox(-3.0F, -1.0F, -8.0F, 6.0F, 2.0F, 7.0F, 0.0F, false);

		neck = new ModelRenderer(this);
		neck.setPos(0.0F, -3.0F, 1.0F);
		head.addChild(neck);
		setRotationAngle(neck, 0.1745F, 0.0F, 0.0F);
		neck.texOffs(0, 13).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		torso1 = new ModelRenderer(this);
		torso1.setPos(0.0F, -31.0F, -1.0F);
		rotwalker.addChild(torso1);
		setRotationAngle(torso1, 0.0873F, 0.0F, 0.0F);
		torso1.texOffs(0, 0).addBox(-6.0F, -3.0F, -2.0F, 12.0F, 7.0F, 6.0F, 0.0F, false);

		torso2 = new ModelRenderer(this);
		torso2.setPos(0.0F, 7.0F, 2.0F);
		torso1.addChild(torso2);
		setRotationAngle(torso2, -0.1745F, 0.0F, 0.0F);
		torso2.texOffs(0, 28).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 8.0F, 4.0F, 0.0F, false);

		pelvis = new ModelRenderer(this);
		pelvis.setPos(0.0F, 11.0F, 0.0F);
		torso1.addChild(pelvis);
		setRotationAngle(pelvis, -0.0873F, 0.0F, 0.0F);
		pelvis.texOffs(24, 13).addBox(-5.0F, 0.0F, -2.0F, 10.0F, 3.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-6.0F, -31.0F, -1.0F);
		rotwalker.addChild(rightArm);
		rightArm.texOffs(24, 34).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 21.0F, 2.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(6.0F, -31.0F, 0.0F);
		rotwalker.addChild(leftArm);
		leftArm.texOffs(24, 34).addBox(0.0F, -1.0F, -1.0F, 2.0F, 21.0F, 2.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-3.0F, -18.0F, 0.0F);
		rotwalker.addChild(rightLeg);
		rightLeg.texOffs(0, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(3.0F, -18.0F, 0.0F);
		rotwalker.addChild(leftLeg);
		leftLeg.texOffs(0, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = 0.0873F + headPitch * ((float)Math.PI / 180F);

		this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableSet.of(rotwalker);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}