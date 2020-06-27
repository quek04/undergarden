package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UndergardenMod;
import quek.undergarden.client.model.SkizSwarmerModel;
import quek.undergarden.entity.SkizSwarmerEntity;

@OnlyIn(Dist.CLIENT)
public class SkizSwarmerRender extends MobRenderer<SkizSwarmerEntity, SkizSwarmerModel<SkizSwarmerEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UndergardenMod.MODID, "textures/entities/skiz_swarmer.png");

    public SkizSwarmerRender(EntityRendererManager rendererManager) {
        super(rendererManager, new SkizSwarmerModel<>(), .7F);
    }

    @Override
    public ResourceLocation getEntityTexture(SkizSwarmerEntity entity) {
        return TEXTURE;
    }
}
