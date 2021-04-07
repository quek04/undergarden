package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.RotlingModel;
import quek.undergarden.client.render.layer.RotlingEyesLayer;
import quek.undergarden.entity.rotspawn.RotlingEntity;

@OnlyIn(Dist.CLIENT)
public class RotlingRender extends MobRenderer<RotlingEntity, RotlingModel<RotlingEntity>> {

    private static final ResourceLocation texture = new ResourceLocation(Undergarden.MODID, "textures/entity/rotling.png");
    private static final ResourceLocation otherside_texture = new ResourceLocation(Undergarden.MODID, "textures/entity/rotling_otherside.png");

    public RotlingRender(EntityRendererManager manager) {
        super(manager, new RotlingModel<>(), 0.6F);
        this.addLayer(new RotlingEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RotlingEntity entity) {
        //if(OthersideDimension.isTheOtherside(entity.world)) {
        //    return otherside_texture;
        //}
        //else
        return texture;
    }
}
