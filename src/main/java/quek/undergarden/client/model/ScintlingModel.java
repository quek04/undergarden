package quek.undergarden.client.model;
// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.ScintlingEntity;

@OnlyIn(Dist.CLIENT)
public class ScintlingModel<T extends ScintlingEntity> extends AgeableModel<T> {
	private final ModelRenderer scintling;
	private final ModelRenderer head;
	private final ModelRenderer leftStalk;
	private final ModelRenderer rightStalk;
	private final ModelRenderer torso;
	private final ModelRenderer tail;

	public ScintlingModel() {
		texWidth = 64;
		texHeight = 64;

		scintling = new ModelRenderer(this);
		scintling.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -2.0F, -7.0F);
		scintling.addChild(head);
		head.texOffs(26, 18).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		leftStalk = new ModelRenderer(this);
		leftStalk.setPos(2.0F, -2.0F, -5.0F);
		head.addChild(leftStalk);
		setRotationAngle(leftStalk, 0.3491F, 0.0F, 0.1745F);
		leftStalk.texOffs(20, 28).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		rightStalk = new ModelRenderer(this);
		rightStalk.setPos(-2.0F, -2.0F, -5.0F);
		head.addChild(rightStalk);
		setRotationAngle(rightStalk, 0.3491F, 0.0F, -0.1745F);
		rightStalk.texOffs(20, 28).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, true);

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, 0.0F, 0.0F);
		scintling.addChild(torso);
		torso.texOffs(0, 0).addBox(-4.0F, -5.0F, -7.0F, 8.0F, 5.0F, 13.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -2.0F, 6.0F);
		scintling.addChild(tail);
		tail.texOffs(0, 18).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 4.0F, 5.0F, 0.0F, false);
		tail.texOffs(0, 28).addBox(-3.0F, -1.0F, 5.0F, 6.0F, 3.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.torso.yRot = MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 0.5F * limbSwingAmount;

		this.tail.yRot = MathHelper.cos(limbSwing * 0.5F) * 0.5F * limbSwingAmount;

		float wiggle = MathHelper.sin((entity.tickCount) * 0.3F) * 0.3F;

		this.leftStalk.xRot = wiggle;
		this.rightStalk.xRot = -wiggle;
	}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(this.scintling);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}