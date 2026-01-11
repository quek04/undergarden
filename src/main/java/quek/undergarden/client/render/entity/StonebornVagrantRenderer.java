package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.monster.stoneborn.StonebornVagrant;

public class StonebornVagrantRenderer extends MobRenderer<StonebornVagrant, StonebornModel<StonebornVagrant>> {

	private static final ResourceLocation STONEBORN_VAGRANT = Undergarden.prefix("textures/entity/stoneborn_vagrant.png");
	private static final RenderType STONEBORN_EYES = RenderType.eyes(Undergarden.prefix("textures/entity/stoneborn_eyes.png"));

	public StonebornVagrantRenderer(EntityRendererProvider.Context context) {
		super(context, new StonebornModel<>(context.bakeLayer(UGModelLayers.STONEBORN_VAGRANT)), 0.6F);
		this.addLayer(new BasicEyesLayer<>(this, STONEBORN_EYES));
	}

	@Override
	public ResourceLocation getTextureLocation(StonebornVagrant entity) {
		return STONEBORN_VAGRANT;
	}

	@Override
	protected boolean isShaking(StonebornVagrant stoneborn) {
		return super.isShaking(stoneborn) || (!stoneborn.inUndergarden() && !stoneborn.isNoAi());
	}
}