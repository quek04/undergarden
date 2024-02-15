package quek.undergarden.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SmogMogModel<E extends Entity> extends AgeableListModel<E> {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart frontLegLeft;
    private final ModelPart frontLegRight;
    private final ModelPart backLegLeft;
    private final ModelPart backLegRight;

    public SmogMogModel(ModelPart root) {
        this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.frontLegLeft = root.getChild("left_front_leg");
		this.frontLegRight = root.getChild("right_front_leg");
		this.backLegLeft = root.getChild("left_back_leg");
		this.backLegRight = root.getChild("right_back_leg");
}

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -16.0F, -7.0F, 14.0F, 12.0F, 14.0F)
                .texOffs(0, 26).addBox(-5.0F, -28.0F, -5.0F, 10.0F, 12.0F, 10.0F)
                .texOffs(42, 0).addBox(-8.0F, -17.0F, -8.0F, 5.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 24.0F, 0.0F));

        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 26).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 19.0F, -7.0F));

        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 0.0F, -4.0F, 4.0F, 5.0F, 4.0F), PartPose.offset(4.0F, 19.0F, -4.0F));

        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, 0.0F, -4.0F, 4.0F, 5.0F, 4.0F), PartPose.offset(-4.0F, 19.0F, -4.0F));

        partdefinition.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(0.0F, 0.0F, -4.0F, 4.0F, 5.0F, 4.0F).mirror(false), PartPose.offset(4.0F, 19.0F, 8.0F));

        partdefinition.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 5.0F, 4.0F), PartPose.offset(-4.0F, 19.0F, 4.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.head, this.body, this.frontLegLeft, this.frontLegRight, this.backLegLeft, this.backLegRight);
    }

    @Override
    public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);

        this.body.zRot = 0.1F * Mth.sin(limbSwing * 2.0F) * 4.0F * limbSwingAmount;

        this.frontLegLeft.xRot = Mth.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
        this.frontLegRight.xRot = Mth.cos(limbSwing * 2.0F + (float) Math.PI) * 4.0F * limbSwingAmount;
        this.backLegLeft.xRot = Mth.cos(limbSwing * 2.0F + (float) Math.PI) * 4.0F * limbSwingAmount;
        this.backLegRight.xRot = Mth.cos(limbSwing * 2.0F) * 4.0F * limbSwingAmount;
    }
}
