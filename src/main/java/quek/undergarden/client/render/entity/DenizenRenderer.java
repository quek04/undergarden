package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.component.SwingAnimation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DenizenModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.DenizenEyesLayer;
import quek.undergarden.client.state.entity.DenizenRenderState;
import quek.undergarden.entity.monster.denizen.Denizen;
import quek.undergarden.registry.UGItems;

public class DenizenRenderer extends MobRenderer<Denizen, DenizenRenderState, DenizenModel> {

	private final DenizenModel shortModel;
	private final DenizenModel tallModel;

	private static final Identifier DENIZEN = Undergarden.prefix("textures/entity/denizen.png");
	private static final Identifier DENIZEN2 = Undergarden.prefix("textures/entity/denizen2.png");

	public DenizenRenderer(EntityRendererProvider.Context context) {
		super(context, new DenizenModel(context.bakeLayer(UGModelLayers.DENIZEN_2)), 0.5F);
		this.shortModel = new DenizenModel(context.bakeLayer(UGModelLayers.DENIZEN));
		this.tallModel = new DenizenModel(context.bakeLayer(UGModelLayers.DENIZEN_2));
		this.addLayer(new DenizenEyesLayer<>(this));
		this.addLayer(new ItemInHandLayer<>(this));
	}

	@Override
	public DenizenRenderState createRenderState() {
		return new DenizenRenderState();
	}

	@Override
	public void extractRenderState(Denizen entity, DenizenRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		HumanoidMobRenderer.extractHumanoidRenderState(entity, state, partialTicks, this.itemModelResolver);
		state.isPassenger |= state.hasPose(Pose.SITTING);
		state.variant = entity.getVariant();
		state.leftArmPose = this.getArmPose(entity, HumanoidArm.LEFT);
		state.rightArmPose = this.getArmPose(entity, HumanoidArm.RIGHT);
	}

	protected HumanoidModel.ArmPose getArmPose(Denizen entity, HumanoidArm arm) {
		ItemStack stack = entity.getItemHeldByArm(arm);
		if (stack.is(UGItems.JAVELIN) && entity.isAggressive()) {
			return HumanoidModel.ArmPose.THROW_TRIDENT;
		}

		SwingAnimation anim = stack.get(DataComponents.SWING_ANIMATION);
		if (anim != null && anim.type() == SwingAnimationType.STAB && entity.swinging) {
			return HumanoidModel.ArmPose.SPEAR;
		} else {
			return stack.is(ItemTags.SPEARS) ? HumanoidModel.ArmPose.SPEAR : HumanoidModel.ArmPose.EMPTY;
		}
	}

	@Override
	public Identifier getTextureLocation(DenizenRenderState state) {
		return switch (state.variant) {
			case SHORT -> DENIZEN;
			case TALL -> DENIZEN2;
		};
	}

	@Override
	public void submit(DenizenRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		this.model = switch (state.variant) {
			case SHORT -> this.shortModel;
			case TALL -> this.tallModel;
		};
		super.submit(state, poseStack, submitNodeCollector, camera);
	}

	@Override
	protected void setupRotations(DenizenRenderState state, PoseStack stack, float bodyRot, float entityScale) {
		super.setupRotations(state, stack, bodyRot, entityScale);
		if (state.hasPose(Pose.SITTING)) {
			stack.translate(0.0D, state.variant == Denizen.Type.TALL ? -1.55F : -0.65F, 0.0D);
		}
	}
}