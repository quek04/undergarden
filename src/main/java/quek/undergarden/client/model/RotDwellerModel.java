package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import quek.undergarden.entity.RotDwellerEntity;

public class RotDwellerModel<T extends RotDwellerEntity> extends AgeableModel<T> {

    private final ModelRenderer dweller;
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

    public RotDwellerModel() {
        textureWidth = 128;
        textureHeight = 128;

        dweller = new ModelRenderer(this);
        dweller.setRotationPoint(0.0F, 24.0F, 0.0F);


        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -25.0F, -2.0F);
        dweller.addChild(head);
        setRotationAngle(head, -0.8727F, 0.0F, 0.0F);
        head.setTextureOffset(0, 23).addBox(-5.0F, 1.0F, -4.0F, 10.0F, 8.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(8, 9).addBox(-1.0F, 6.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(8, 9).addBox(-1.0F, 3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        trunk = new ModelRenderer(this);
        trunk.setRotationPoint(0.0F, 7.0F, -2.0F);
        head.addChild(trunk);
        setRotationAngle(trunk, 2.5307F, 0.0F, 0.0F);
        trunk.setTextureOffset(65, 0).addBox(-2.0F, -2.0F, -9.0F, 4.0F, 3.0F, 9.0F, 0.0F, false);

        trunk2 = new ModelRenderer(this);
        trunk2.setRotationPoint(0.0F, 0.0F, -8.0F);
        trunk.addChild(trunk2);
        setRotationAngle(trunk2, 0.1745F, 0.0F, 0.0F);
        trunk2.setTextureOffset(82, 0).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        trunk3 = new ModelRenderer(this);
        trunk3.setRotationPoint(0.0F, -1.0F, -6.0F);
        trunk2.addChild(trunk3);
        setRotationAngle(trunk3, 0.2618F, 0.0F, 0.0F);
        trunk3.setTextureOffset(92, 0).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        torso = new ModelRenderer(this);
        torso.setRotationPoint(0.0F, -23.0F, 3.0F);
        dweller.addChild(torso);
        setRotationAngle(torso, -0.4363F, 0.0F, 0.0F);
        torso.setTextureOffset(0, 0).addBox(-7.0F, -3.0F, -6.0F, 14.0F, 8.0F, 15.0F, 0.0F, false);
        torso.setTextureOffset(32, 34).addBox(-2.0F, -1.0F, -9.0F, 4.0F, 3.0F, 5.0F, 0.0F, false);
        torso.setTextureOffset(8, 9).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        torso.setTextureOffset(0, 9).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        torso.setTextureOffset(8, 9).addBox(-1.0F, -4.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        leftleg = new ModelRenderer(this);
        leftleg.setRotationPoint(4.0F, -20.0F, 6.0F);
        dweller.addChild(leftleg);
        setRotationAngle(leftleg, -0.6981F, 0.0F, 0.0F);
        leftleg.setTextureOffset(0, 37).addBox(0.0F, -2.0F, -3.0F, 4.0F, 10.0F, 6.0F, 0.0F, false);
        leftleg.setTextureOffset(40, 23).addBox(0.0F, 8.0F, 1.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);

        leftfoot = new ModelRenderer(this);
        leftfoot.setRotationPoint(2.0F, 9.0F, 8.0F);
        leftleg.addChild(leftfoot);
        setRotationAngle(leftfoot, -0.8727F, 0.0F, 0.0F);
        leftfoot.setTextureOffset(0, 53).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 2.0F, 7.0F, 0.0F, false);

        rightleg = new ModelRenderer(this);
        rightleg.setRotationPoint(-4.0F, -20.0F, 6.0F);
        dweller.addChild(rightleg);
        setRotationAngle(rightleg, -0.6981F, 0.0F, 0.0F);
        rightleg.setTextureOffset(0, 37).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 10.0F, 6.0F, 0.0F, true);
        rightleg.setTextureOffset(40, 23).addBox(-4.0F, 8.0F, 1.0F, 4.0F, 3.0F, 8.0F, 0.0F, true);

        rightfoot = new ModelRenderer(this);
        rightfoot.setRotationPoint(-2.0F, 9.0F, 8.0F);
        rightleg.addChild(rightfoot);
        setRotationAngle(rightfoot, -0.8727F, 0.0F, 0.0F);
        rightfoot.setTextureOffset(0, 53).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 2.0F, 7.0F, 0.0F, true);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, -22.0F, 11.0F);
        dweller.addChild(tail);
        setRotationAngle(tail, 0.1745F, 0.0F, 0.0F);
        tail.setTextureOffset(43, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setRotationPoint(0.0F, 8.0F, 0.0F);
        tail.addChild(tail2);
        setRotationAngle(tail2, -0.2618F, 0.0F, 0.0F);
        tail2.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 8.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = -0.8727F + headPitch * ((float)Math.PI / 180F);

        this.leftleg.rotateAngleX = -0.6981F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.rightleg.rotateAngleX = -0.6981F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableSet.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableSet.of(this.dweller);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
