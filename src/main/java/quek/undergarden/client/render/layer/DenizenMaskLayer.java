package quek.undergarden.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGItems;

@Deprecated
public class DenizenMaskLayer<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

	private static final Identifier TEXTURE = Undergarden.prefix("textures/models/armor/denizen_mask.png");

	public DenizenMaskLayer(RenderLayerParent<S, M> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
		ItemStack itemStack = state.headEquipment;
		if (itemStack.is(UGItems.DENIZEN_MASK)) {
			submitNodeCollector.order(1).submitModel(this.getParentModel(), state, poseStack, RenderTypes.armorCutoutNoCull(TEXTURE), lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		}
	}
}
