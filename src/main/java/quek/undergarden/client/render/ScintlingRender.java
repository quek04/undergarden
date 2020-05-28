package quek.undergarden.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.ScintlingModel;
import quek.undergarden.client.render.layer.ScintlingGlowLayer;
import quek.undergarden.entity.RotwalkerEntity;
import quek.undergarden.entity.ScintlingEntity;

@OnlyIn(Dist.CLIENT)
public class ScintlingRender extends MobRenderer<ScintlingEntity, ScintlingModel<ScintlingEntity>> {

    private static final ResourceLocation texture = new ResourceLocation(UndergardenMod.MODID, "textures/entities/scintling.png");

    public ScintlingRender(EntityRendererManager manager) {
        super(manager, new ScintlingModel<>(), 0.5F);
        this.addLayer(new ScintlingGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(ScintlingEntity entity) {
        return texture;
    }

}
