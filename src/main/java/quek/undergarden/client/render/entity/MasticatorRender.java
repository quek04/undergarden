package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.MasticatorModel;
import quek.undergarden.client.render.layer.MasticatorEyesLayer;
import quek.undergarden.entity.boss.MasticatorEntity;

@OnlyIn(Dist.CLIENT)
public class MasticatorRender extends MobRenderer<MasticatorEntity, MasticatorModel<MasticatorEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(UGMod.MODID, "textures/entity/masticator.png");

    public MasticatorRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MasticatorModel<>(), 2.0F);
        this.addLayer(new MasticatorEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(MasticatorEntity entity) {
        return TEXTURE;
    }
}
