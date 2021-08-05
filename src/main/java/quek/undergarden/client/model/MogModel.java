package quek.undergarden.client.model;
// Made with Blockbench 3.9.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.MogEntity;

@OnlyIn(Dist.CLIENT)
public class MogModel<E extends MogEntity> extends AgeableModel<E> {
	private final ModelRenderer frontLegLeft;
	private final ModelRenderer frontLegRight;
	private final ModelRenderer backLegRight;
	private final ModelRenderer backLegLeft;
	private final ModelRenderer head;
	private final ModelRenderer body;

	public MogModel() {
		texWidth = 64;
		texHeight = 64;

		frontLegLeft = new ModelRenderer(this);
		frontLegLeft.setPos(2.0F, 20.0F, -2.0F);
		frontLegLeft.texOffs(32, 36).addBox(0.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);

		frontLegRight = new ModelRenderer(this);
		frontLegRight.setPos(-2.0F, 20.0F, -2.0F);
		frontLegRight.texOffs(20, 36).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		backLegRight = new ModelRenderer(this);
		backLegRight.setPos(2.0F, 20.0F, 2.0F);
		backLegRight.texOffs(0, 0).addBox(-7.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		backLegLeft = new ModelRenderer(this);
		backLegLeft.setPos(-2.0F, 20.0F, 2.0F);
		backLegLeft.texOffs(36, 0).addBox(4.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 20.0F, -6.0F);
		head.texOffs(26, 26).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 35).addBox(0.0F, -21.0F, -4.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(-6.0F, -17.0F, -6.0F, 12.0F, 14.0F, 12.0F, 0.0F, false);
		body.texOffs(0, 26).addBox(-4.0F, -19.0F, -3.0F, 6.0F, 2.0F, 7.0F, 0.0F, false);
	}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(this.head, this.body, this.frontLegLeft, this.frontLegRight, this.backLegLeft, this.backLegRight);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.body.zRot = 0.1F * MathHelper.sin(limbSwing * 2.0F) * 4.0F * limbSwingAmount;

		this.frontLegLeft.xRot = MathHelper.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
		this.frontLegRight.xRot = MathHelper.cos(limbSwing * 2.0F + (float)Math.PI) * 4.0F * limbSwingAmount;
		this.backLegLeft.xRot = MathHelper.cos(limbSwing * 2.0F + (float)Math.PI) * 4.0F * limbSwingAmount;
		this.backLegRight.xRot = MathHelper.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
	}
}