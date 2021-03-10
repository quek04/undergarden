package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenGuardianModel;
import quek.undergarden.entity.boss.ForgottenGuardianEntity;

public class ForgottenGuardianEyesLayer<T extends ForgottenGuardianEntity, M extends ForgottenGuardianModel<T>> extends AbstractEyesLayer<T, M> {

    private static final RenderType TEXTURE = RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/forgotten_guardian_eyes.png"));

    public ForgottenGuardianEyesLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return TEXTURE;
    }

}
