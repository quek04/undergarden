package quek.undergarden.client.render.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenGuardianModel;
import quek.undergarden.entity.monster.boss.ForgottenGuardian;

public class ForgottenGuardianEyesLayer<T extends ForgottenGuardian, M extends ForgottenGuardianModel<T>> extends EyesLayer<T, M> {

	public ForgottenGuardianEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(new ResourceLocation(Undergarden.MODID, "textures/entity/forgotten_guardian_eyes.png"));
	}

}
