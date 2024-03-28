package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.Denizen2Model;
import quek.undergarden.client.model.DenizenModel;
import quek.undergarden.client.model.FixedHumanoidModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.DenizenEyesLayer;
import quek.undergarden.entity.monster.Denizen;

public class DenizenRenderer extends MobRenderer<Denizen, FixedHumanoidModel<Denizen>> {
	private final FixedHumanoidModel<Denizen> shortModel = this.getModel();
	private final FixedHumanoidModel<Denizen> tallModel;

	public DenizenRenderer(EntityRendererProvider.Context context) {
		super(context, new DenizenModel<>(context.bakeLayer(UGModelLayers.DENIZEN)), 0.5F);
		this.tallModel = new Denizen2Model<>(context.bakeLayer(UGModelLayers.DENIZEN_2));
		this.addLayer(new DenizenEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Denizen entity) {
		return switch (entity.getVariant()) {
			case SHORT -> new ResourceLocation(Undergarden.MODID, "textures/entity/denizen.png");
			case TALL -> new ResourceLocation(Undergarden.MODID, "textures/entity/denizen2.png");
		};
	}

	@Override
	public void render(Denizen entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		switch (entity.getVariant()) {
			case SHORT -> this.model = shortModel;
			case TALL -> this.model = tallModel;
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}
}