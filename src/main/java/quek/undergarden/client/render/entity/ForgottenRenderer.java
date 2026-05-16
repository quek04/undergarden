package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.ForgottenModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.entity.monster.Forgotten;

public class ForgottenRenderer extends HumanoidMobRenderer<Forgotten, HumanoidRenderState, ForgottenModel> {

	private static final Identifier FORGOTTEN = Undergarden.prefix("textures/entity/forgotten.png");
	private static final RenderType FORGOTTEN_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/forgotten_eyes.png"));

	public ForgottenRenderer(EntityRendererProvider.Context context) {
		super(context, new ForgottenModel(context.bakeLayer(UGModelLayers.FORGOTTEN)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, ArmorModelSet.bake(UGModelLayers.FORGOTTEN_ARMOR, context.getModelSet(), ForgottenModel::new), context.getEquipmentRenderer()));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
		this.addLayer(new BasicEyesLayer<>(this, FORGOTTEN_EYES));
	}

	@Override
	public HumanoidRenderState createRenderState() {
		return new HumanoidRenderState();
	}

	@Override
	public Identifier getTextureLocation(HumanoidRenderState state) {
		return FORGOTTEN;
	}

	@Override
	protected void scale(HumanoidRenderState state, PoseStack stack) {
		stack.scale(1.1F, 1.1F, 1.1F);
	}
}