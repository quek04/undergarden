package quek.undergarden.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.TestEntity;
import quek.undergarden.entity.model.TestModel;

import javax.annotation.Nullable;

public class TestRender extends MobRenderer<TestEntity, TestModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Undergarden.MODID, "textures/entities/rotwalker.png");

    public TestRender(EntityRendererManager manager) {
        super(manager, new TestModel(), .5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(TestEntity entity) {
        return null;
    }
}
