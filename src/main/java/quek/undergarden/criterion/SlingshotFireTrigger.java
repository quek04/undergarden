package quek.undergarden.criterion;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGItems;

public class SlingshotFireTrigger extends SimpleCriterionTrigger<SlingshotFireTrigger.TriggerInstance> {

	private static final ResourceLocation ID = new ResourceLocation(Undergarden.MODID, "slingshot_fire");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected SlingshotFireTrigger.TriggerInstance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext context) {
		ItemPredicate slingshotPredicate = ItemPredicate.fromJson(json.get("slingshot"));
		ItemPredicate ammoPredicate = ItemPredicate.fromJson(json.get("ammo"));
		return new SlingshotFireTrigger.TriggerInstance(entityPredicate, slingshotPredicate, ammoPredicate);
	}

	public void trigger(ServerPlayer shooter, ItemStack slingshot, ItemStack ammo) {
		this.trigger(shooter, (triggerInstance) -> triggerInstance.matches(slingshot, ammo));
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		private final ItemPredicate slingshot;
		private final ItemPredicate ammo;

		public TriggerInstance(EntityPredicate.Composite player, ItemPredicate slingshot, ItemPredicate ammo) {
			super(SlingshotFireTrigger.ID, player);
			this.slingshot = slingshot;
			this.ammo = ammo;
		}

		public static SlingshotFireTrigger.TriggerInstance shotItem(ItemLike slingshot, ItemLike ammo) {
			return new SlingshotFireTrigger.TriggerInstance(EntityPredicate.Composite.ANY, ItemPredicate.Builder.item().of(slingshot).build(), ItemPredicate.Builder.item().of(ammo).build());
		}

		public static SlingshotFireTrigger.TriggerInstance shotItem(ItemLike ammo) {
			return shotItem(UGItems.SLINGSHOT.get(), ammo);
		}

		public boolean matches(ItemStack slingshot, ItemStack ammo) {
			return this.slingshot.matches(slingshot) && this.ammo.matches(ammo);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext conditions) {
			JsonObject object = super.serializeToJson(conditions);
			object.add("slingshot", this.slingshot.serializeToJson());
			object.add("ammo", this.ammo.serializeToJson());
			return object;
		}
	}
}
