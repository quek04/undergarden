package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.SploogieModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.entity.monster.cavern.Sploogie;

public class SploogieRenderer extends MobRenderer<Sploogie, SploogieModel<Sploogie>> {

	private static final Identifier SPLOOGIE = Undergarden.prefix("textures/entity/sploogie.png");

	public SploogieRenderer(EntityRendererProvider.Context context) {
		super(context, new SploogieModel<>(context.bakeLayer(UGModelLayers.SPLOOGIE)), 0.5F);
	}

	@Override
	public Identifier getTextureLocation(Sploogie entity) {
		return SPLOOGIE;
	}
}
