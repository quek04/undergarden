package quek.undergarden.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.RotwalkerEntity;
import quek.undergarden.entity.model.RotwalkerModel;

public class RotwalkerRender extends MobRenderer<RotwalkerEntity, RotwalkerModel<RotwalkerEntity>> {

    private static final ResourceLocation texture = new ResourceLocation(UndergardenMod.MODID, "textures/entities/rotwalker.png");

    public RotwalkerRender(EntityRendererManager manager) {
        super(manager, new RotwalkerModel(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(RotwalkerEntity entity) {
        return texture;
    }
}
