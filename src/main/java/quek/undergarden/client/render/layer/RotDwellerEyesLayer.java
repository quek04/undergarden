package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quek.undergarden.UGMod;
import quek.undergarden.client.model.RotDwellerModel;
import quek.undergarden.entity.rotspawn.RotDwellerEntity;

@OnlyIn(Dist.CLIENT)
public class RotDwellerEyesLayer<T extends RotDwellerEntity, M extends RotDwellerModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(UGMod.MODID, "textures/entities/rotdweller_eyes.png"));

    public RotDwellerEyesLayer(IEntityRenderer<T, M> p_i226039_1_) {
        super(p_i226039_1_);
    }

    @Override
    public RenderType getRenderType() {
        return TEXTURE;
    }
}
