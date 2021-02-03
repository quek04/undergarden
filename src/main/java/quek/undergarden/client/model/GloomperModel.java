package quek.undergarden.client.model;
// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.GloomperEntity;

@OnlyIn(Dist.CLIENT)
public class GloomperModel<T extends GloomperEntity> extends AgeableModel<T> {
	private final ModelRenderer gloomper;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer jaw;
	private final ModelRenderer arms;
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer feet;

	private float jumpRotation;

	public GloomperModel() {
		textureWidth = 64;
		textureHeight = 64;

		gloomper = new ModelRenderer(this);
		gloomper.setRotationPoint(0.0F, 24.0F, 0.0F);


		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -10.0F, 3.0F);
		gloomper.addChild(body);
		setRotationAngle(body, -0.3491F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-8.0F, -5.0F, -8.0F, 16.0F, 13.0F, 16.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -10.5F, -5.0F);
		gloomper.addChild(head);
		head.setTextureOffset(0, 31).addBox(-5.0F, -4.5F, -2.0F, 10.0F, 8.0F, 4.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 3.5F, -2.0F);
		head.addChild(jaw);
		jaw.setTextureOffset(0, 43).addBox(-5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.5F, -4.5F, -3.5F);
		gloomper.addChild(arms);


		left = new ModelRenderer(this);
		left.setRotationPoint(6.0F, -0.5F, -1.0F);
		arms.addChild(left);
		setRotationAngle(left, -0.5672F, -0.4363F, 0.0F);
		left.setTextureOffset(22, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, true);

		right = new ModelRenderer(this);
		right.setRotationPoint(-7.0F, -0.5F, -1.0F);
		arms.addChild(right);
		setRotationAngle(right, -0.5672F, 0.4363F, 0.0F);
		right.setTextureOffset(22, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		feet = new ModelRenderer(this);
		feet.setRotationPoint(0.0F, -2.0F, 8.0F);
		gloomper.addChild(feet);
		feet.setTextureOffset(28, 32).addBox(7.0F, 0.0F, -7.0F, 4.0F, 2.0F, 7.0F, 0.0F, true);
		feet.setTextureOffset(28, 32).addBox(-11.0F, 0.0F, -7.0F, 4.0F, 2.0F, 7.0F, 0.0F, false);
		feet.setTextureOffset(0, 56).addBox(-11.0F, -5.0F, -3.0F, 4.0F, 5.0F, 3.0F, 0.0F, false);
		feet.setTextureOffset(0, 56).addBox(7.0F, -5.0F, -3.0F, 4.0F, 5.0F, 3.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		float age = ageInTicks - (float)entity.ticksExisted;
		this.jumpRotation = MathHelper.sin(entity.getJumpCompletion(age) * (float)Math.PI);

		this.arms.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
		this.feet.rotateAngleX = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float)Math.PI);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableSet.of(gloomper);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}