package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.MinionModel;
import quek.undergarden.client.render.layer.MinionEyesLayer;
import quek.undergarden.entity.MinionEntity;

@OnlyIn(Dist.CLIENT)
public class MinionRender extends MobRenderer<MinionEntity, MinionModel<MinionEntity>> {

    public MinionRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MinionModel<>(), 0.5F);
        this.addLayer(new MinionEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(MinionEntity entity) {
        return new ResourceLocation(UGMod.MODID, "textures/entity/minion.png");
    }
}
