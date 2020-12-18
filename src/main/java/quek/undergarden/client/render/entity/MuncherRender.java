package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.MuncherModel;
import quek.undergarden.client.render.layer.MuncherEyesLayer;
import quek.undergarden.entity.MuncherEntity;

@OnlyIn(Dist.CLIENT)
public class MuncherRender extends MobRenderer<MuncherEntity, MuncherModel<MuncherEntity>> {

    public MuncherRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MuncherModel<>(), 0.5F);
        this.addLayer(new MuncherEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(MuncherEntity entity) {
        return new ResourceLocation(UGMod.MODID, "textures/entity/muncher.png");
    }
}
