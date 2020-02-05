package quek.undergarden.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import quek.undergarden.entity.TestEntity;

public class TestModel extends EntityModel<TestEntity> {

    public RendererModel body;

    public TestModel() {
        body = new RendererModel(this, 0, 0);
        body.addBox(-3,-3,-3, 6, 6, 6);
    }

    @Override
    public void render(TestEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        body.render(scale);
    }

    @Override
    public void setRotationAngles(TestEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    }



}
