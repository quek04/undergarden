package quek.undergarden.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import quek.undergarden.entity.RotwalkerEntity;

public class RotwalkerModel <T extends RotwalkerEntity> extends SegmentedModel<T> {
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
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.head = new ModelRenderer(this);
		this.head.setRotationPoint(0.0F, -34.0F, 0.0F);
		this.head.addBox(0, 13, -4.0F, -10.2165F, -7.9595F, 8, 7, 8, 0.0F);

		this.jaw = new ModelRenderer(this);
		this.jaw.setRotationPoint(0.0F, -2.2165F, -0.9595F);
		this.head.addChild(jaw);
		this.jaw.addBox(25, 25, -3.0F, -1.1284F, -6.9619F, 6, 2, 7, 0.0F);

		this.neck = new ModelRenderer(this);
		this.neck.setRotationPoint(0.0F, -3.0757F, -0.7347F);
		this.head.addChild(neck);
		this.neck.addBox(0, 13, -1.0F, 0.0115F, -1.4384F, 2, 4, 2, 0.0F);

		this.torso1 = new ModelRenderer(this);
		this.torso1.setRotationPoint(0.0F, -31.0F, -1.0F);
		this.torso1.addBox(0, 0, -6.0F, -3.118F, -2.7018F, 12, 7, 6, 0.0F);

		this.torso2 = new ModelRenderer(this);
		this.torso2.setRotationPoint(0.0F, 6.965F, 0.3975F);
		this.torso1.addChild(torso2);
		this.torso2.addBox(0, 28, -4.0F, -4.0F, -2.0F, 8, 8, 4, 0.0F);

		this.pelvis = new ModelRenderer(this);
		this.pelvis.setRotationPoint(0.0F, 10.882F, 0.2982F);
		this.torso1.addChild(pelvis);
		this.pelvis.addBox(24, 13, -5.0F, -0.8146F, -2.2455F, 10, 3, 4, 0.0F);

		this.rightArm = new ModelRenderer(this);
		this.rightArm.setRotationPoint(-6.0F, -31.0F, -1.0F);
		this.rightArm.addBox(24, 34, -2.0F, -1.0F, -1.0F, 2, 21, 2, 0.0F);

		this.leftArm = new ModelRenderer(this);
		this.leftArm.setRotationPoint(6.0F, -31.0F, 0.0F);
		this.leftArm.addBox(24, 34, 0.0F, -1.0F, -1.0F, 2, 21, 2, 0.0F);

		this.rightLeg = new ModelRenderer(this);
		this.rightLeg.setRotationPoint(-3.0F, -18.0F, 0.0F);
		this.rightLeg.addBox(0, 40, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F);

		this.leftLeg = new ModelRenderer(this);
		this.leftLeg.setRotationPoint(3.0F, -18.0F, 0.0F);
		this.leftLeg.addBox(0, 40, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F);
	}

	@Override
	public void setRotationAngles(T t, float v, float v1, float v2, float v3, float v4) {

	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.head, this.jaw, this.neck, this.torso1, this.torso2, this.pelvis, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
	}
}