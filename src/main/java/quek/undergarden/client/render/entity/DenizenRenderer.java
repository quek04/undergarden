package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DenizenModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.DenizenEyesLayer;
import quek.undergarden.client.state.entity.DenizenRenderState;
import quek.undergarden.entity.monster.denizen.Denizen;
import quek.undergarden.registry.UGItems;

public class DenizenRenderer extends HumanoidMobRenderer<Denizen, DenizenRenderState, DenizenModel> {

	private final DenizenModel shortModel = this.getModel();
	private final DenizenModel tallModel;

	private static final Identifier DENIZEN = Undergarden.prefix("textures/entity/denizen.png");
	private static final Identifier DENIZEN2 = Undergarden.prefix("textures/entity/denizen2.png");

	public DenizenRenderer(EntityRendererProvider.Context context) {
		super(context, new DenizenModel(context.bakeLayer(UGModelLayers.DENIZEN)), 0.5F);
		this.tallModel = new DenizenModel(context.bakeLayer(UGModelLayers.DENIZEN_2));
		this.addLayer(new DenizenEyesLayer<>(this));
	}

	@Override
	public DenizenRenderState createRenderState() {
		return null;
	}

	@Override
	public void extractRenderState(Denizen entity, DenizenRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);

		ItemStack itemstack = entity.getMainHandItem();
		if (itemstack.is(UGItems.SPEAR) && entity.isAggressive()) {
			if (state.mainArm == HumanoidArm.RIGHT) {
				state.rightArmPose = HumanoidModel.ArmPose.THROW_TRIDENT;
			} else {
				state.leftArmPose = HumanoidModel.ArmPose.THROW_TRIDENT;
			}
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
		switch (state.variant) {
			case SHORT -> this.model = this.shortModel;
			case TALL -> this.model = this.tallModel;
		}
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