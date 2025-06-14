package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.StonebornModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.StonebornEyesLayer;
import quek.undergarden.entity.monster.stoneborn.Stoneborn;

public class StonebornRenderer extends MobRenderer<Stoneborn, StonebornModel<Stoneborn>> {

	private static final ResourceLocation STONEBORN = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/stoneborn.png");

	public StonebornRenderer(EntityRendererProvider.Context context) {
		super(context, new StonebornModel<>(context.bakeLayer(UGModelLayers.STONEBORN)), 0.6F);
		this.addLayer(new StonebornEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Stoneborn entity) {
		return STONEBORN;
	}

	@Override
	protected boolean isShaking(Stoneborn stoneborn) {
		return super.isShaking(stoneborn) || (!stoneborn.inUndergarden() && !stoneborn.isNoAi());
	}
}