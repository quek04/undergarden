package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.NargoyleModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.entity.monster.cavern.Nargoyle;

public class NargoyleRenderer extends MobRenderer<Nargoyle, NargoyleModel<Nargoyle>> {

	private static final Identifier NARGOYLE = Undergarden.prefix("textures/entity/nargoyle.png");

	public NargoyleRenderer(EntityRendererProvider.Context context) {
		super(context, new NargoyleModel<>(context.bakeLayer(UGModelLayers.NARGOYLE)), 0.8F);
	}

	@Override
	public Identifier getTextureLocation(Nargoyle entity) {
		return NARGOYLE;
	}
}