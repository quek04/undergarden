package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import quek.undergarden.entity.MinionEntity;

public class MinionModel<T extends MinionEntity> extends ListModel<T> {
	private final ModelPart forgottenMinion;
	private final ModelPart shell;
	private final ModelPart body;
	private final ModelPart backRightLeg;
	private final ModelPart backLeftLeg;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontRightLeg;

	public MinionModel() {
		texWidth = 128;
		texHeight = 128;

		forgottenMinion = new ModelPart(this);
		forgottenMinion.setPos(0.0F, 11.0F, -3.0F);

		shell = new ModelPart(this);
		shell.setPos(0.0F, 0.0F, 0.0F);
		forgottenMinion.addChild(shell);
		setRotationAngle(shell, -0.7854F, 0.0F, 0.0F);
		shell.texOffs(0, 0).addBox(-6.0F, -14.0F, -8.0F, 12.0F, 17.0F, 15.0F, 0.0F, false);
		shell.texOffs(40, 32).addBox(-5.0F, -13.0F, -7.0F, 10.0F, 13.0F, 8.0F, 0.0F, false);

		body = new ModelPart(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		forgottenMinion.addChild(body);
		body.texOffs(0, 32).addBox(-4.0F, -6.0F, -3.0F, 8.0F, 13.0F, 12.0F, 0.0F, false);
		body.texOffs(27, 53).addBox(-3.0F, -6.0F, -16.0F, 6.0F, 6.0F, 13.0F, 0.0F, false);

		backRightLeg = new ModelPart(this);
		backRightLeg.setPos(-2.0F, 6.0F, 7.0F);
		body.addChild(backRightLeg);
		backRightLeg.texOffs(39, 0).addBox(-5.0F, 0.0F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

		backLeftLeg = new ModelPart(this);
		backLeftLeg.setPos(2.0F, 6.0F, 7.0F);
		body.addChild(backLeftLeg);
		backLeftLeg.texOffs(52, 53).addBox(0.0F, 0.0F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

		frontLeftLeg = new ModelPart(this);
		frontLeftLeg.setPos(2.0F, 6.0F, -1.0F);
		body.addChild(frontLeftLeg);
		frontLeftLeg.texOffs(54, 7).addBox(0.0F, 0.0F, -5.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

		frontRightLeg = new ModelPart(this);
		frontRightLeg.setPos(-2.0F, 6.0F, -1.0F);
		body.addChild(frontRightLeg);
		frontRightLeg.texOffs(54, 19).addBox(-5.0F, 0.0F, -5.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.frontLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.frontRightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.backLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.backRightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(forgottenMinion);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}