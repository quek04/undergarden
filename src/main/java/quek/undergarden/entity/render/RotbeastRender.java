package quek.undergarden.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.RotbeastEntity;
import quek.undergarden.entity.model.RotbeastModel;

public class RotbeastRender extends MobRenderer<RotbeastEntity, RotbeastModel<RotbeastEntity>> {

    private static final ResourceLocation texture = new ResourceLocation(UndergardenMod.MODID, "textures/entities/rotbeast.png");

    public RotbeastRender(EntityRendererManager manager) {
        super(manager, new RotbeastModel(), 0.6F);
    }

    @Override
    public ResourceLocation getEntityTexture(RotbeastEntity entity) {
        return texture;
    }

}
