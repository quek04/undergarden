package quek.undergarden.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SimpleEquipmentLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import quek.undergarden.Undergarden;
import quek.undergarden.client.model.DwellerModel;
import quek.undergarden.client.model.UGModelLayers;
import quek.undergarden.client.render.layer.BasicEyesLayer;
import quek.undergarden.client.state.entity.DwellerRenderState;
import quek.undergarden.entity.animal.dweller.Dweller;

public class DwellerRenderer extends MobWithBabyRenderer<Dweller, DwellerRenderState, DwellerModel> {

	private static final Identifier DWELLER = Undergarden.prefix("textures/entity/dweller.png");
	private static final Identifier BABY_DWELLER = Undergarden.prefix("textures/entity/dweller_baby.png");
	private final static RenderType DWELLER_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/dweller_eyes.png"));
	private final static RenderType BABY_DWELLER_EYES = RenderTypes.eyes(Undergarden.prefix("textures/entity/dweller_baby_eyes.png"));

	public DwellerRenderer(EntityRendererProvider.Context context) {
		super(context, new DwellerModel(context.bakeLayer(UGModelLayers.DWELLER)), new DwellerModel(context.bakeLayer(UGModelLayers.DWELLER_BABY)), 0.7F);
		this.addLayer(new BasicEyesLayer<>(this, DWELLER_EYES, BABY_DWELLER_EYES));
		this.addLayer(new SimpleEquipmentLayer<>(
			this,
			context.getEquipmentRenderer(),
			EquipmentClientInfo.LayerType.valueOf("UNDERGARDEN_DWELLER_SADDLE"),
			state -> state.saddle,
			new DwellerModel(context.bakeLayer(UGModelLayers.DWELLER_SADDLE)),
			null));
	}

	@Override
	public DwellerRenderState createRenderState() {
		return new DwellerRenderState();
	}

	@Override
	public void extractRenderState(Dweller entity, DwellerRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.saddle = entity.getItemBySlot(EquipmentSlot.SADDLE).copy();
	}

	@Override
	protected Identifier getAdultTexture(DwellerRenderState state) {
		return DWELLER;
	}

	@Override
	protected Identifier getBabyTexture(DwellerRenderState state) {
		return BABY_DWELLER;
	}
}