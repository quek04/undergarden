package quek.undergarden.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import quek.undergarden.entity.RotwalkerEntity;

//Made with Blockbench
public class RotwalkerModel extends EntityModel<RotwalkerEntity> {
	private final RendererModel rotwalker;
	private final RendererModel head;
	private final RendererModel jaw;
	private final RendererModel neck;
	private final RendererModel torso1;
	private final RendererModel torso2;
	private final RendererModel pelvis;
	private final RendererModel rightArm;
	private final RendererModel leftArm;
	private final RendererModel rightLeg;
	private final RendererModel leftLeg;

	public RotwalkerModel() {
		textureWidth = 64;
		textureHeight = 64;

		rotwalker = new RendererModel(this);
		rotwalker.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, -34.0F, 0.0F);
		setRotationAngle(head, 0.0873F, 0.0F, 0.0F);
		rotwalker.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 13, -4.0F, -10.2165F, -7.9595F, 8, 7, 8, 0.0F, false));

		jaw = new RendererModel(this);
		jaw.setRotationPoint(0.0F, -2.2165F, -0.9595F);
		setRotationAngle(jaw, -0.0873F, 0.0F, 0.0F);
		head.addChild(jaw);
		jaw.cubeList.add(new ModelBox(jaw, 25, 25, -3.0F, -1.1284F, -6.9619F, 6, 2, 7, 0.0F, false));

		neck = new RendererModel(this);
		neck.setRotationPoint(0.0F, -3.0757F, -0.7347F);
		setRotationAngle(neck, 0.1745F, 0.0F, 0.0F);
		head.addChild(neck);
		neck.cubeList.add(new ModelBox(neck, 0, 13, -1.0F, 0.0115F, -1.4384F, 2, 4, 2, 0.0F, false));

		torso1 = new RendererModel(this);
		torso1.setRotationPoint(0.0F, -31.0F, -1.0F);
		setRotationAngle(torso1, 0.0873F, 0.0F, 0.0F);
		rotwalker.addChild(torso1);
		torso1.cubeList.add(new ModelBox(torso1, 0, 0, -6.0F, -3.118F, -2.7018F, 12, 7, 6, 0.0F, false));

		torso2 = new RendererModel(this);
		torso2.setRotationPoint(0.0F, 6.965F, 0.3975F);
		setRotationAngle(torso2, -0.1745F, 0.0F, 0.0F);
		torso1.addChild(torso2);
		torso2.cubeList.add(new ModelBox(torso2, 0, 28, -4.0F, -4.0F, -2.0F, 8, 8, 4, 0.0F, false));

		pelvis = new RendererModel(this);
		pelvis.setRotationPoint(0.0F, 10.882F, 0.2982F);
		setRotationAngle(pelvis, -0.0873F, 0.0F, 0.0F);
		torso1.addChild(pelvis);
		pelvis.cubeList.add(new ModelBox(pelvis, 24, 13, -5.0F, -0.8146F, -2.2455F, 10, 3, 4, 0.0F, false));

		rightArm = new RendererModel(this);
		rightArm.setRotationPoint(-6.0F, -31.0F, -1.0F);
		rotwalker.addChild(rightArm);
		rightArm.cubeList.add(new ModelBox(rightArm, 24, 34, -2.0F, -1.0F, -1.0F, 2, 21, 2, 0.0F, false));

		leftArm = new RendererModel(this);
		leftArm.setRotationPoint(6.0F, -31.0F, 0.0F);
		rotwalker.addChild(leftArm);
		leftArm.cubeList.add(new ModelBox(leftArm, 24, 34, 0.0F, -1.0F, -1.0F, 2, 21, 2, 0.0F, false));

		rightLeg = new RendererModel(this);
		rightLeg.setRotationPoint(-3.0F, -18.0F, 0.0F);
		rotwalker.addChild(rightLeg);
		rightLeg.cubeList.add(new ModelBox(rightLeg, 0, 40, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		leftLeg = new RendererModel(this);
		leftLeg.setRotationPoint(3.0F, -18.0F, 0.0F);
		rotwalker.addChild(leftLeg);
		leftLeg.cubeList.add(new ModelBox(leftLeg, 0, 40, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
	}

	@Override
	public void render(RotwalkerEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		rotwalker.render(scale);
		head.render(headPitch);

	}

	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}