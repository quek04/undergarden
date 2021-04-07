package quek.undergarden.client.model;
// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.stoneborn.StonebornEntity;

@OnlyIn(Dist.CLIENT)
public class StonebornModel<T extends StonebornEntity> extends AgeableModel<T> {
	private final ModelRenderer stoneborn;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer jaw;
	private final ModelRenderer body;
	private final ModelRenderer robe;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;

	public StonebornModel() {
		texWidth = 88;
		texHeight = 67;

		stoneborn = new ModelRenderer(this);
		stoneborn.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -29.0F, -1.0F);
		stoneborn.addChild(head);
		head.texOffs(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 12.0F, 8.0F, 0.0F, false);
		head.texOffs(0, 20).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);
		head.texOffs(32, 8).addBox(-7.0F, -10.0F, 0.0F, 14.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(32, 12).addBox(7.0F, -13.0F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		head.texOffs(40, 12).addBox(-9.0F, -15.0F, 0.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		head.texOffs(48, 12).addBox(4.0F, -8.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);

		nose = new ModelRenderer(this);
		nose.setPos(0.0F, -7.0F, -3.0F);
		head.addChild(nose);
		setRotationAngle(nose, -0.2182F, 0.0F, 0.0F);
		nose.texOffs(24, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setPos(0.0F, 0.0F, -1.0F);
		head.addChild(jaw);
		setRotationAngle(jaw, 0.2182F, 0.0F, 0.0F);
		jaw.texOffs(36, 0).addBox(-3.0F, -3.4329F, -2.9526F, 6.0F, 4.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -22.0F, 1.0F);
		stoneborn.addChild(body);
		body.texOffs(0, 26).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 14.0F, 8.0F, 0.0F, false);

		robe = new ModelRenderer(this);
		robe.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(robe);
		robe.texOffs(40, 23).addBox(-7.0F, -8.0F, -5.0F, 14.0F, 24.0F, 10.0F, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setPos(-6.0F, -26.5F, 1.0F);
		stoneborn.addChild(leftarm);
		leftarm.texOffs(0, 48).addBox(-4.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setPos(7.0F, -26.5F, 1.0F);
		stoneborn.addChild(rightarm);
		rightarm.texOffs(0, 48).addBox(-1.0F, -0.5F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(-3.0F, -15.0F, 0.0F);
		stoneborn.addChild(leftleg);
		leftleg.texOffs(16, 48).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(3.0F, -15.0F, 0.0F);
		stoneborn.addChild(rightleg);
		rightleg.texOffs(16, 48).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 15.0F, 4.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.leftarm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightarm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.leftleg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return ImmutableSet.of();
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableSet.of(stoneborn);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}