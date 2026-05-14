package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.model.UndergarModel;
import quek.undergarden.client.render.layer.UndergarEyesLayer;
import quek.undergarden.client.state.entity.UndergarRenderState;
import quek.undergarden.entity.animal.Undergar;

public class UndergarRenderer extends MobRenderer<Undergar, UndergarRenderState, UndergarModel> {

	public static final Identifier UNDERGAR = Undergarden.prefix("textures/entity/undergar.png");

	public UndergarRenderer(EntityRendererProvider.Context context) {
		super(context, new UndergarModel(context.bakeLayer(UGModelLayers.UNDERGAR)), 0.5F);
		this.addLayer(new UndergarEyesLayer(this));
	}

	@Override
	public Identifier getTextureLocation(UndergarRenderState state) {
		return UNDERGAR;
	}

	@Override
	public UndergarRenderState createRenderState() {
		return new UndergarRenderState();
	}

	@Override
	public void extractRenderState(Undergar entity, UndergarRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isAggressive = entity.isAggressive();
		state.attackAnimTime = entity.getBiteAnim(partialTicks);
	}

	//gars slightly stick out of their bounding box, and also rotate up and down, so we don't want them to unrender if slightly offscreen in those cases
	@Override
	protected AABB getBoundingBoxForCulling(Undergar entity) {
		return super.getBoundingBoxForCulling(entity).inflate(1.0D);
	}
}
