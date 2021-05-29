package quek.undergarden.client.model;
// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.MinionEntity;

public class MinionModel<T extends MinionEntity> extends SegmentedModel<T> {
	private final ModelRenderer forgottenMinion;
	private final ModelRenderer shell;
	private final ModelRenderer body;
	private final ModelRenderer backRightLeg;
	private final ModelRenderer backLeftLeg;
	private final ModelRenderer frontLeftLeg;
	private final ModelRenderer frontRightLeg;

	public MinionModel() {
		texWidth = 128;
		texHeight = 128;

		forgottenMinion = new ModelRenderer(this);
		forgottenMinion.setPos(0.0F, 11.0F, -3.0F);

		shell = new ModelRenderer(this);
		shell.setPos(0.0F, 0.0F, 0.0F);
		forgottenMinion.addChild(shell);
		setRotationAngle(shell, -0.7854F, 0.0F, 0.0F);
		shell.texOffs(0, 0).addBox(-6.0F, -14.0F, -8.0F, 12.0F, 17.0F, 15.0F, 0.0F, false);
		shell.texOffs(40, 32).addBox(-5.0F, -13.0F, -7.0F, 10.0F, 13.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		forgottenMinion.addChild(body);
		body.texOffs(0, 32).addBox(-4.0F, -6.0F, -3.0F, 8.0F, 13.0F, 12.0F, 0.0F, false);
		body.texOffs(27, 53).addBox(-3.0F, -6.0F, -16.0F, 6.0F, 6.0F, 13.0F, 0.0F, false);

		backRightLeg = new ModelRenderer(this);
		backRightLeg.setPos(0.0F, -1.0F, 5.0F);
		body.addChild(backRightLeg);
		backRightLeg.texOffs(39, 0).addBox(-7.0F, 7.0F, 2.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

		backLeftLeg = new ModelRenderer(this);
		backLeftLeg.setPos(0.0F, -1.0F, 5.0F);
		body.addChild(backLeftLeg);
		backLeftLeg.texOffs(52, 53).addBox(2.0F, 7.0F, 2.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

		frontLeftLeg = new ModelRenderer(this);
		frontLeftLeg.setPos(0.0F, -1.0F, 1.0F);
		body.addChild(frontLeftLeg);
		frontLeftLeg.texOffs(54, 7).addBox(2.0F, 7.0F, -7.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

		frontRightLeg = new ModelRenderer(this);
		frontRightLeg.setPos(0.0F, -1.0F, 1.0F);
		body.addChild(frontRightLeg);
		frontRightLeg.texOffs(54, 19).addBox(-7.0F, 7.0F, -7.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//this.shell.yRot = netHeadYaw * ((float)Math.PI / 180F);
		//this.shell.xRot = headPitch * ((float)Math.PI / 180F);

		this.frontLeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.frontRightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.backLeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.backRightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(forgottenMinion);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}