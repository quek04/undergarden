package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;

public abstract class MobWithBabyRenderer<T extends Mob, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends MobRenderer<T, S, M> {

	private final AdultAndBabyModelPair<M> models;

	public MobWithBabyRenderer(EntityRendererProvider.Context context, M model, M babyModel, float shadow) {
		super(context, model, shadow);
		this.models = new AdultAndBabyModelPair<>(model, babyModel);
	}

	@Override
	public void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		this.model = this.models.getModel(state.isBaby);
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	public final Identifier getTextureLocation(S state) {
		return state.isBaby ? this.getBabyTexture(state) : this.getAdultTexture(state);
	}

	protected abstract Identifier getAdultTexture(S state);

	protected abstract Identifier getBabyTexture(S state);
}
