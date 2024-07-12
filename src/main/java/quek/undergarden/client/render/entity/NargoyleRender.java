package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.NargoyleModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.entity.monster.cavern.Nargoyle;

public class NargoyleRender extends MobRenderer<Nargoyle, NargoyleModel<Nargoyle>> {

	private static final ResourceLocation NARGOYLE = ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "textures/entity/nargoyle.png");

	public NargoyleRender(EntityRendererProvider.Context context) {
		super(context, new NargoyleModel<>(context.bakeLayer(UGModelLayers.NARGOYLE)), 0.8F);
	}

	@Override
	public ResourceLocation getTextureLocation(Nargoyle entity) {
		return NARGOYLE;
	}
}