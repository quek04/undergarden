package quek.undergarden.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.AcolyteModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.entity.monster.Acolyte;

public class AcolyteRenderer extends HumanoidMobRenderer<Acolyte, HumanoidRenderState, AcolyteModel> {

	private static final Identifier ACOLYTE = Undergarden.prefix("textures/entity/acolyte.png");

	public AcolyteRenderer(EntityRendererProvider.Context context) {
		super(context, new AcolyteModel(context.bakeLayer(UGModelLayers.ACOLYTE)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, ArmorModelSet.bake(UGModelLayers.ACOLYTE_ARMOR, context.getModelSet(), AcolyteModel::new), context.getEquipmentRenderer()));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
	}

	@Override
	public Identifier getTextureLocation(HumanoidRenderState state) {
		return ACOLYTE;
	}

	@Override
	public HumanoidRenderState createRenderState() {
		return new HumanoidRenderState();
	}

	@Override
	protected void scale(HumanoidRenderState state, PoseStack stack) {
		stack.scale(1.1F, 1.1F, 1.1F);
	}
}