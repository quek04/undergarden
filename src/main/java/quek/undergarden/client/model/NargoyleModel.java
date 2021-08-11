package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.cavern.NargoyleEntity;

public class NargoyleModel<T extends NargoyleEntity> extends SegmentedModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer upperbody;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer ear1;
	private final ModelRenderer ear2;
	private final ModelRenderer arms;
	private final ModelRenderer upperarmleft;
	private final ModelRenderer lowerarmleft;
	private final ModelRenderer upperarmright;
	private final ModelRenderer lowerarmright;
	private final ModelRenderer lowerbody;
	private final ModelRenderer hips;
	private final ModelRenderer legs;
	private final ModelRenderer upperrightleg;
	private final ModelRenderer lowerrightleg;
	private final ModelRenderer upperleftleg;
	private final ModelRenderer lowerleftleg;

	public NargoyleModel() {
		texWidth = 64;
		texHeight = 64;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 3.0F, -3.0F);
		setRotationAngle(body, 0.0873F, 0.0F, 0.0F);

		upperbody = new ModelRenderer(this);
		upperbody.setPos(0.0F, 6.0F, 4.0F);
		body.addChild(upperbody);
		setRotationAngle(upperbody, 1.0472F, 0.0F, 0.0F);
		upperbody.texOffs(0, 0).addBox(-5.0F, -11.0F, 0.0F, 10.0F, 11.0F, 7.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -10.5F, 3.0F);
		upperbody.addChild(head);
		setRotationAngle(head, 0.4363F, 0.0F, 0.0F);
		head.texOffs(42, 51).addBox(-4.0F, -9.5F, -2.0F, 8.0F, 10.0F, 3.0F, 0.0F, false);
		head.texOffs(16, 28).addBox(-3.0F, -8.5F, -4.0F, 6.0F, 0.0F, 2.0F, 0.0F, false);
		head.texOffs(45, 49).addBox(-2.0F, -9.5F, 1.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setPos(0.0F, 0.0F, -2.0F);
		head.addChild(jaw);
		jaw.texOffs(34, 35).addBox(-4.0F, -7.5F, -1.0F, 8.0F, 7.0F, 1.0F, 0.0F, false);
		jaw.texOffs(26, 0).addBox(-2.0F, -6.5F, 0.0F, 4.0F, 0.0F, 1.0F, 0.0F, false);

		ear1 = new ModelRenderer(this);
		ear1.setPos(0.0F, -1.0F, 0.0F);
		head.addChild(ear1);
		setRotationAngle(ear1, 0.0F, 0.0F, -0.2618F);
		ear1.texOffs(0, 54).addBox(-5.0F, -1.0F, -1.0F, 3.0F, 0.0F, 10.0F, 0.0F, false);

		ear2 = new ModelRenderer(this);
		ear2.setPos(3.5F, -1.0F, 5.0F);
		head.addChild(ear2);
		setRotationAngle(ear2, 0.0F, 0.0F, 0.2618F);
		ear2.texOffs(0, 54).addBox(-1.5F, 0.0F, -6.0F, 3.0F, 0.0F, 10.0F, 0.0F, true);

		arms = new ModelRenderer(this);
		arms.setPos(0.0F, -7.4927F, 3.3309F);
		upperbody.addChild(arms);
		setRotationAngle(arms, 1.5708F, 0.0F, 0.0F);

		upperarmleft = new ModelRenderer(this);
		upperarmleft.setPos(0.0F, -0.0073F, -0.0309F);
		arms.addChild(upperarmleft);
		setRotationAngle(upperarmleft, 0.8727F, 0.0F, 0.0F);
		upperarmleft.texOffs(34, 0).addBox(5.0F, -9.9927F, -0.9691F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		lowerarmleft = new ModelRenderer(this);
		lowerarmleft.setPos(7.0F, -9.9927F, 1.0309F);
		upperarmleft.addChild(lowerarmleft);
		setRotationAngle(lowerarmleft, -1.1345F, 0.0F, 0.0F);
		lowerarmleft.texOffs(34, 43).addBox(-1.5F, -14.7194F, -1.2086F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		upperarmright = new ModelRenderer(this);
		upperarmright.setPos(0.0F, -0.0073F, -0.3309F);
		arms.addChild(upperarmright);
		setRotationAngle(upperarmright, 0.8727F, 0.0F, 0.0F);
		upperarmright.texOffs(34, 0).addBox(-8.0F, -9.9927F, -0.6691F, 3.0F, 12.0F, 3.0F, 0.0F, true);

		lowerarmright = new ModelRenderer(this);
		lowerarmright.setPos(-7.0F, -9.9927F, 1.3309F);
		upperarmright.addChild(lowerarmright);
		setRotationAngle(lowerarmright, -1.1345F, 0.0F, 0.0F);
		lowerarmright.texOffs(34, 43).addBox(-0.5F, -14.7194F, -1.2086F, 2.0F, 16.0F, 2.0F, 0.0F, true);

		lowerbody = new ModelRenderer(this);
		lowerbody.setPos(0.0F, 2.0F, 5.0F);
		body.addChild(lowerbody);
		setRotationAngle(lowerbody, -1.2217F, 0.0F, 0.0F);

		hips = new ModelRenderer(this);
		hips.setPos(0.0F, 2.7189F, -1.2679F);
		lowerbody.addChild(hips);
		setRotationAngle(hips, 0.48F, 0.0F, 0.0F);
		hips.texOffs(21, 21).addBox(-3.0F, -4.0F, 2.0F, 6.0F, 5.0F, 9.0F, 0.0F, false);

		legs = new ModelRenderer(this);
		legs.setPos(0.0F, -2.527F, 6.4479F);
		lowerbody.addChild(legs);
		setRotationAngle(legs, -1.7017F, 0.0F, 0.0F);

		upperrightleg = new ModelRenderer(this);
		upperrightleg.setPos(0.0F, 0.027F, -0.9479F);
		legs.addChild(upperrightleg);
		setRotationAngle(upperrightleg, -0.8727F, 0.0F, 0.0F);
		upperrightleg.texOffs(0, 18).addBox(-5.0F, -9.027F, -1.0521F, 2.0F, 10.0F, 3.0F, 0.0F, true);

		lowerrightleg = new ModelRenderer(this);
		lowerrightleg.setPos(-3.0F, -8.2609F, -0.6949F);
		upperrightleg.addChild(lowerrightleg);
		setRotationAngle(lowerrightleg, 1.2217F, 0.0F, 0.0F);
		lowerrightleg.texOffs(0, 31).addBox(-1.5F, -8.8618F, -0.8628F, 1.0F, 10.0F, 2.0F, 0.0F, false);

		upperleftleg = new ModelRenderer(this);
		upperleftleg.setPos(0.0F, 0.027F, -0.9479F);
		legs.addChild(upperleftleg);
		setRotationAngle(upperleftleg, -0.8727F, 0.0F, 0.0F);
		upperleftleg.texOffs(0, 18).addBox(3.0F, -9.027F, -1.0521F, 2.0F, 10.0F, 3.0F, 0.0F, false);

		lowerleftleg = new ModelRenderer(this);
		lowerleftleg.setPos(3.0F, -8.2609F, -0.6949F);
		upperleftleg.addChild(lowerleftleg);
		setRotationAngle(lowerleftleg, 1.2217F, 0.0F, 0.0F);
		lowerleftleg.texOffs(0, 31).addBox(0.5F, -8.8618F, -0.8628F, 1.0F, 10.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.zRot = headPitch * ((float)Math.PI / 180F);
		this.head.xRot = 0.4363F + headPitch * ((float)Math.PI / 180F);

		this.arms.xRot = 1.5708F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.legs.xRot = -1.7017F + -(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);

		if(entity.isAggressive()) {
			this.jaw.xRot = 0.3491F;
		}
		else {
			this.jaw.xRot = 0.0F;
		}
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableSet.of(body);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}