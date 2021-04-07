package quek.undergarden.client.model;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.entity.rotspawn.RotDwellerEntity;

@OnlyIn(Dist.CLIENT)
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
        texWidth = 128;
        texHeight = 128;

        dweller = new ModelRenderer(this);
        dweller.setPos(0.0F, 24.0F, 0.0F);


        head = new ModelRenderer(this);
        head.setPos(0.0F, -25.0F, -2.0F);
        dweller.addChild(head);
        setRotationAngle(head, -0.8727F, 0.0F, 0.0F);
        head.texOffs(0, 23).addBox(-5.0F, 1.0F, -4.0F, 10.0F, 8.0F, 6.0F, 0.0F, false);
        head.texOffs(8, 9).addBox(-1.0F, 6.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(8, 9).addBox(-1.0F, 3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        trunk = new ModelRenderer(this);
        trunk.setPos(0.0F, 7.0F, -2.0F);
        head.addChild(trunk);
        setRotationAngle(trunk, 2.5307F, 0.0F, 0.0F);
        trunk.texOffs(65, 0).addBox(-2.0F, -2.0F, -9.0F, 4.0F, 3.0F, 9.0F, 0.0F, false);

        trunk2 = new ModelRenderer(this);
        trunk2.setPos(0.0F, 0.0F, -8.0F);
        trunk.addChild(trunk2);
        setRotationAngle(trunk2, 0.1745F, 0.0F, 0.0F);
        trunk2.texOffs(82, 0).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        trunk3 = new ModelRenderer(this);
        trunk3.setPos(0.0F, -1.0F, -6.0F);
        trunk2.addChild(trunk3);
        setRotationAngle(trunk3, 0.2618F, 0.0F, 0.0F);
        trunk3.texOffs(92, 0).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        torso = new ModelRenderer(this);
        torso.setPos(0.0F, -23.0F, 3.0F);
        dweller.addChild(torso);
        setRotationAngle(torso, -0.4363F, 0.0F, 0.0F);
        torso.texOffs(0, 0).addBox(-7.0F, -3.0F, -6.0F, 14.0F, 8.0F, 15.0F, 0.0F, false);
        torso.texOffs(32, 34).addBox(-2.0F, -1.0F, -9.0F, 4.0F, 3.0F, 5.0F, 0.0F, false);
        torso.texOffs(8, 9).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        torso.texOffs(0, 9).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
        torso.texOffs(8, 9).addBox(-1.0F, -4.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        leftleg = new ModelRenderer(this);
        leftleg.setPos(4.0F, -20.0F, 6.0F);
        dweller.addChild(leftleg);
        setRotationAngle(leftleg, -0.6981F, 0.0F, 0.0F);
        leftleg.texOffs(0, 37).addBox(0.0F, -2.0F, -3.0F, 4.0F, 10.0F, 6.0F, 0.0F, false);
        leftleg.texOffs(40, 23).addBox(0.0F, 8.0F, 1.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);

        leftfoot = new ModelRenderer(this);
        leftfoot.setPos(2.0F, 9.0F, 8.0F);
        leftleg.addChild(leftfoot);
        setRotationAngle(leftfoot, -0.8727F, 0.0F, 0.0F);
        leftfoot.texOffs(0, 53).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 2.0F, 7.0F, 0.0F, false);

        rightleg = new ModelRenderer(this);
        rightleg.setPos(-4.0F, -20.0F, 6.0F);
        dweller.addChild(rightleg);
        setRotationAngle(rightleg, -0.6981F, 0.0F, 0.0F);
        rightleg.texOffs(0, 37).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 10.0F, 6.0F, 0.0F, true);
        rightleg.texOffs(40, 23).addBox(-4.0F, 8.0F, 1.0F, 4.0F, 3.0F, 8.0F, 0.0F, true);

        rightfoot = new ModelRenderer(this);
        rightfoot.setPos(-2.0F, 9.0F, 8.0F);
        rightleg.addChild(rightfoot);
        setRotationAngle(rightfoot, -0.8727F, 0.0F, 0.0F);
        rightfoot.texOffs(0, 53).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 2.0F, 7.0F, 0.0F, true);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, -22.0F, 11.0F);
        dweller.addChild(tail);
        setRotationAngle(tail, 0.1745F, 0.0F, 0.0F);
        tail.texOffs(43, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setPos(0.0F, 8.0F, 0.0F);
        tail.addChild(tail2);
        setRotationAngle(tail2, -0.2618F, 0.0F, 0.0F);
        tail2.texOffs(0, 0).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 8.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = -0.8727F + headPitch * ((float)Math.PI / 180F);

        this.leftleg.xRot = -0.6981F + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.rightleg.xRot = -0.6981F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableSet.of();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableSet.of(this.dweller);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

}
