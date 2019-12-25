package quek.undergarden.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.RotwalkerEntity;
import quek.undergarden.model.RotwalkerModel;

public class RotwalkerRender extends MobRenderer<RotwalkerEntity, RotwalkerModel> {

    private static final ResourceLocation texture = new ResourceLocation(Undergarden.MODID, "textures/entities/rotwalker.png");

    public RotwalkerRender(EntityRendererManager manager) {
        super(manager, new RotwalkerModel(), 0.6f);
    }

    @Override
    protected ResourceLocation getEntityTexture(RotwalkerEntity entity) {
        return texture;
    }
}
