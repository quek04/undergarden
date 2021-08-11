package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.rotspawn.RotwalkerEntity;

public class RotwalkerModel<T extends RotwalkerEntity> extends SegmentedModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer torso;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public RotwalkerModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
		head.setPos(0.0F, -8.0F, -1.0F);
		setRotationAngle(head, 0.1309F, 0.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, 1.0F, -1.0F);
		setRotationAngle(torso, 0.1745F, 0.0F, 0.0F);
		torso.texOffs(0, 16).addBox(-5.0F, -9.0F, -2.0F, 10.0F, 8.0F, 5.0F, 0.0F, false);
		torso.texOffs(0, 29).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 9.0F, 3.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(5.0F, -6.0F, -2.0F);
		leftArm.texOffs(32, 0).addBox(0.0F, -1.0F, -1.0F, 2.0F, 16.0F, 3.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-5.0F, -6.0F, -2.0F);
		rightArm.texOffs(32, 0).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 16.0F, 3.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(-2.0F, 8.0F, 1.0F);
		leftLeg.texOffs(32, 0).addBox(-2.1F, 0.0F, -2.0F, 2.0F, 16.0F, 3.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(2.0F, 8.0F, 1.0F);
		rightLeg.texOffs(32, 0).addBox(0.1F, 0.0F, -2.0F, 2.0F, 16.0F, 3.0F, 0.0F, true);}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = 0.1309F + headPitch * ((float)Math.PI / 180F);

		this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableSet.of(head, torso, leftArm, rightArm, leftLeg, rightLeg);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}