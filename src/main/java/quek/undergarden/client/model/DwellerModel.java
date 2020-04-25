package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.DwellerEntity;

public class DwellerModel<T extends DwellerEntity> extends SegmentedModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer trunk;
	private final ModelRenderer trunk2;
	private final ModelRenderer trunk3;
	private final ModelRenderer torso;
	private final ModelRenderer leftleg;
	private final ModelRenderer leftfoot;
	private final ModelRenderer rightleg;
	private final ModelRenderer rightfoot;
	private final ModelRenderer tail;
	private final ModelRenderer tail2;

	public DwellerModel() {
		textureWidth = 128;
		textureHeight = 128;

		this.head = new ModelRenderer(this, 0, 23);
		this.head.setRotationPoint(0.0F, -1.0F, -2.0F);
		this.setRotationAngle(head, -0.8727F, 0.0F, 0.0F);
		this.head.addBox(-5.0F, 0.6869F, -4.6177F, 10, 8, 6);
		//this.head.setTextureOffset(8, 9).addBox(-1.0F, 5.8944F, -5.7851F, 2, 2, 1);
		//this.head.setTextureOffset(8, 9).addBox(-1.0F, 5.8944F, -5.7851F, 2, 2, 1);

		//head.cubeList.add(new ModelRenderer.ModelBox(head, 0, 23, -5.0F, 0.6869F, -4.6177F, 10, 8, 6, 0.0F, false));
		//head.cubeList.add(new ModelRenderer.ModelBox(head, 8, 9, -1.0F, 5.8944F, -5.7851F, 2, 2, 1, 0.0F, false));
		//head.cubeList.add(new ModelBox(head, 8, 9, -1.0F, 2.3107F, -5.3888F, 2, 2, 1, 0.0F, false));

		this.trunk = new ModelRenderer(this, 65, 0);
		this.trunk.setRotationPoint(0.0F, 6.8567F, -2.4037F);
		this.setRotationAngle(trunk, 2.5307F, 0.0F, 0.0F);
		this.head.addChild(trunk);
		this.trunk.addBox(-2.0F, -2.2003F, -8.9196F, 4, 3, 9);

		//trunk.cubeList.add(new ModelBox(trunk, 65, 0, -2.0F, -2.2003F, -8.9196F, 4, 3, 9, 0.0F, false));

		this.trunk2 = new ModelRenderer(this, 82, 0);
		this.trunk2.setRotationPoint(0.0F, -0.8126F, -7.8452F);
		this.setRotationAngle(trunk2, 0.1745F, 0.0F, 0.0F);
		this.trunk.addChild(trunk2);
		this.trunk2.addBox(-1.0F, -0.475F, -6.917F, 2, 2, 6);

		//trunk2.cubeList.add(new ModelBox(trunk2, 82, 0, -1.0F, -0.475F, -6.917F, 2, 2, 6, 0.0F, false));

		this.trunk3 = new ModelRenderer(this, 92, 0);
		this.trunk3.setRotationPoint(0.0F, 1.0F, -6.0F);
		this.setRotationAngle(trunk3, 0.2618F, 0.0F, 0.0F);
		this.trunk2.addChild(trunk3);
		this.trunk3.addBox(-1.0F, -0.7934F, -4.8723F, 2, 1, 4);

		//trunk3.cubeList.add(new ModelBox(trunk3, 92, 0, -1.0F, -0.7934F, -4.8723F, 2, 1, 4, 0.0F, false));

		this.torso = new ModelRenderer(this, 0, 0);
		this.torso.setRotationPoint(0.0F, 0.5F, 3.0F);
		this.setRotationAngle(torso, -0.4363F, 0.0F, 0.0F);
		this.torso.addBox(-7.0F, -2.5937F, -6.5774F, 14, 8, 15);
		//this.torso.setTextureOffset(32, 34).addBox(-2.0F, -1.1832F, -9.7057F, 4, 3, 5);
		//this.torso.setTextureOffset(8, 9).addBox(-1.0F, -4.1143F, -4.9257F, 2, 2, 2);
		//this.torso.setTextureOffset(0, 9).addBox(-1.0F, -5.8985F, -0.8779F, 2, 4, 2);
		//this.torso.setTextureOffset(8, 9).addBox(-1.0F, -4.1053F, 4.0763F, 2, 2, 2);

		//torso.cubeList.add(new ModelBox(torso, 0, 0, -7.0F, -2.5937F, -6.5774F, 14, 8, 15, 0.0F, false));
		//torso.cubeList.add(new ModelBox(torso, 32, 34, -2.0F, -1.1832F, -9.7057F, 4, 3, 5, 0.0F, false));
		//torso.cubeList.add(new ModelBox(torso, 8, 9, -1.0F, -4.1143F, -4.9257F, 2, 2, 1, 0.0F, false));
		//torso.cubeList.add(new ModelBox(torso, 0, 9, -1.0F, -5.8985F, -0.8779F, 2, 4, 1, 0.0F, false));
		//torso.cubeList.add(new ModelBox(torso, 8, 9, -1.0F, -4.1053F, 4.0763F, 2, 2, 1, 0.0F, false));

		this.leftleg = new ModelRenderer(this, 0, 37);
		this.leftleg.setRotationPoint(4.0F, 4.0F, 6.0F);
		this.setRotationAngle(leftleg, -0.6981F, 0.0F, 0.0F);
		this.leftleg.addBox(0.0F, -2.234F, -2.3572F, 4, 10, 6);
		this.leftleg.setTextureOffset(40, 23).addBox(0.0F, 7.5796F, 1.3874F, 4, 3, 8);

		//leftleg.cubeList.add(new ModelBox(leftleg, 0, 37, 0.0F, -2.234F, -2.3572F, 4, 10, 6, 0.0F, false));
		//leftleg.cubeList.add(new ModelBox(leftleg, 40, 23, 0.0F, 7.5796F, 1.3874F, 4, 3, 8, 0.0F, false));

		this.leftfoot = new ModelRenderer(this, 0, 53);
		this.leftfoot.setRotationPoint(2.0F, 9.4586F, 8.8562F);
		this.setRotationAngle(leftfoot, -0.8727F, 0.0F, 0.0F);
		this.leftleg.addChild(leftfoot);
		this.leftfoot.addBox(-2.0F, -1.5F, -0.5F, 4, 2, 7);

		//leftfoot.cubeList.add(new ModelBox(leftfoot, 0, 53, -2.0F, -1.5F, -0.5F, 4, 2, 7, 0.0F, false));

		this.rightleg = new ModelRenderer(this, 0, 37);
		this.rightleg.mirror = true;
		this.rightleg.setRotationPoint(-4.0F, 4.0F, 6.0F);
		this.setRotationAngle(rightleg, -0.6981F, 0.0F, 0.0F);
		this.rightleg.addBox(-4.0F, -2.234F, -2.3572F, 4, 10, 6);
		this.rightleg.setTextureOffset(40, 23).addBox(-4.0F, 7.5796F, 1.3874F, 4, 3, 8);

		//rightleg.cubeList.add(new ModelBox(rightleg, 0, 37, -4.0F, -2.234F, -2.3572F, 4, 10, 6, 0.0F, false));
		//rightleg.cubeList.add(new ModelBox(rightleg, 40, 23, -4.0F, 7.5796F, 1.3874F, 4, 3, 8, 0.0F, false));

		this.rightfoot = new ModelRenderer(this, 0, 53);
		this.leftfoot.mirror = true;
		this.rightfoot.setRotationPoint(-2.0F, 9.4586F, 8.8562F);
		this.setRotationAngle(rightfoot, -0.8727F, 0.0F, 0.0F);
		this.rightleg.addChild(rightfoot);
		this.rightfoot.addBox(-2.0F, -1.5F, -0.5F, 4, 2, 7);

		//rightfoot.cubeList.add(new ModelBox(rightfoot, 0, 53, -2.0F, -1.5F, -0.5F, 4, 2, 7, 0.0F, false));

		this.tail = new ModelRenderer(this, 43, 0);
		this.tail.setRotationPoint(0.0F, 2.0F, 11.5F);
		this.setRotationAngle(tail, 0.1745F, 0.0F, 0.0F);
		this.torso.addChild(tail);
		this.tail.addBox(-4.0F, -0.0608F, -2.1946F, 8, 8, 3);

		//tail.cubeList.add(new ModelBox(tail, 43, 0, -4.0F, -0.0608F, -2.1946F, 8, 8, 3, 0.0F, false));

		this.tail2 = new ModelRenderer(this, 0, 0);
		this.tail2.setRotationPoint(0.0F, 7.8827F, -0.4454F);
		this.setRotationAngle(tail2, -0.2618F, 0.0F, 0.0F);
		this.tail.addChild(tail2);
		this.tail2.addBox(-3.0F, -0.5F, -0.2508F, 6, 8, 1);

		//tail2.cubeList.add(new ModelBox(tail2, 0, 0, -3.0F, -0.5F, -0.2508F, 6, 8, 1, 0.0F, false));
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(
				this.head,
				this.trunk,
				this.trunk2,
				this.trunk3,
				this.torso,
				this.leftleg,
				this.leftfoot,
				this.rightleg,
				this.rightfoot,
				this.tail,
				this.tail2
		);

	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}