package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import quek.undergarden.entity.RotbeastEntity;

public class RotbeastModel <T extends RotbeastEntity> extends SegmentedModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer leftArm;
	private final ModelRenderer lowerLeftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer lowerRightArm;
	private final ModelRenderer torso;
	private final ModelRenderer torsoLower;
	private final ModelRenderer leftLeg;
	private final ModelRenderer lowerLeftLeg;
	private final ModelRenderer rightLeg;
	private final ModelRenderer lowerRightLeg;

	public RotbeastModel() {
		textureWidth = 128;
		textureHeight = 128;

		this.head = new ModelRenderer(this, 0, 22);
		this.head.setRotationPoint(0.0F, -35.0F, -8.0F);
		this.head.addBox(0, 22, -5.0F, -9.1566F, -7.1895F, 10, 6, 7, 0.0F);

		this.jaw = new ModelRenderer(this, 22, 66);
		this.jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addChild(jaw);
		this.jaw.addBox(22, 66, -5.0F, -3.0F, -6.0F, 10, 1, 6, 0.0F);

		this.leftArm = new ModelRenderer(this, 34, 32);
		this.leftArm.setRotationPoint(10.0F, -32.0F, -4.0F);
		this.leftArm.addBox(56, 22, 0.0F, -2.0F, -3.0F, 5, 18, 6, 0.0F);

		this.lowerLeftArm = new ModelRenderer(this, 0, 46);
		this.lowerLeftArm.setRotationPoint(2.0F, 16.0F, 0.0F);
		this.leftArm.addChild(lowerLeftArm);
		this.lowerLeftArm.addBox(22, 46, -2.0F, -1.0F, -3.0F, 5, 14, 6, 0.0F);

		this.rightArm = new ModelRenderer(this, 34, 32);
		this.rightArm.setRotationPoint(-10.0F, -32.0F, -4.0F);
		this.rightArm.addBox(34, 22, -5.0F, -2.0F, -3.0F, 5, 18, 6, 0.0F);

		this.lowerRightArm = new ModelRenderer(this, 0, 46);
		this.lowerRightArm.setRotationPoint(-2.0F, 16.0F, 0.0F);
		this.rightArm.addChild(lowerRightArm);
		this.lowerRightArm.addBox(0, 46, -3.0F, -1.0F, -3.0F, 5, 14, 6, 0.0F);

		this.torso = new ModelRenderer(this, 0, 0);
		this.torso.setRotationPoint(0.0F, -30.0F, -3.0F);
		this.torso.addBox(0, 0, -10.0F, -7.0F, -5.0F, 20, 12, 10, 0.0F);

		this.torsoLower = new ModelRenderer(this, 60, 0);
		this.torsoLower.setRotationPoint(0.0F, 8.0F, -2.0F);
		this.torso.addChild(torsoLower);
		this.torsoLower.addBox(60, 0, -8.0F, -5.0F, -1.0F, 16, 11, 6, 0.0F);

		this.leftLeg = new ModelRenderer(this, 54, 66);
		this.leftLeg.setRotationPoint(4.0F, -17.0F, 3.0F);
		this.leftLeg.addBox(76, 66, -2.0F, -1.0F, -3.0F, 5, 8, 6, 0.0F);

		this.lowerLeftLeg = new ModelRenderer(this, 44, 46);
		this.lowerLeftLeg.setRotationPoint(0.0F, 7.0F, 1.0F);
		this.leftLeg.addChild(lowerLeftLeg);
		this.lowerLeftLeg.addBox(0, 66, -2.0F, -0.9199F, -3.5456F, 5, 11, 6, 0.0F);

		this.rightLeg = new ModelRenderer(this, 54, 66);
		this.rightLeg.setRotationPoint(-4.0F, -17.0F, 3.0F);
		this.rightLeg.addBox(54, 66, -3.0F, -1.0F, -3.0F, 5, 8, 6, 0.0F);

		this.lowerRightLeg = new ModelRenderer(this, 44, 46);
		this.lowerRightLeg.setRotationPoint(0.0F, 7.0F, 1.0F);
		this.rightLeg.addChild(lowerRightLeg);
		this.lowerRightLeg.addBox(44, 46, -3.0F, -0.9199F, -3.5456F, 5, 11, 6, 0.0F);
	}

	@Override
	public void setRotationAngles(T t, float v, float v1, float v2, float v3, float v4) {

	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.head, this.jaw, this.leftArm, this.lowerLeftArm, this.rightArm, this.lowerRightArm, this.torso, this.torsoLower, this.leftLeg, this.lowerLeftLeg, this.rightLeg, this.lowerRightLeg);
	}


}