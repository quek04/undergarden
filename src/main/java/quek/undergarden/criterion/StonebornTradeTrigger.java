package quek.undergarden.criterion;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.stoneborn.Stoneborn;

public class StonebornTradeTrigger extends SimpleCriterionTrigger<StonebornTradeTrigger.TriggerInstance> {

    private static final ResourceLocation ID = new ResourceLocation(Undergarden.MODID, "stoneborn_trade");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public TriggerInstance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext conditionsParser) {
        EntityPredicate.Composite entitypredicate$andpredicate = EntityPredicate.Composite.fromJson(json, "stoneborn", conditionsParser);
        ItemPredicate itempredicate = ItemPredicate.fromJson(json.get("item"));
        return new TriggerInstance(entityPredicate, entitypredicate$andpredicate, itempredicate);
    }

    public void test(ServerPlayer player, Stoneborn stoneborn, ItemStack stack) {
        LootContext lootcontext = EntityPredicate.createContext(player, stoneborn);
        this.trigger(player, (instance) -> instance.test(lootcontext, stack));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final EntityPredicate.Composite stoneborn;
        private final ItemPredicate item;

        public TriggerInstance(EntityPredicate.Composite player, EntityPredicate.Composite stoneborn, ItemPredicate stack) {
            super(StonebornTradeTrigger.ID, player);
            this.stoneborn = stoneborn;
            this.item = stack;
        }

        public static TriggerInstance tradeWithStoneborn() {
            return new TriggerInstance(EntityPredicate.Composite.ANY, EntityPredicate.Composite.ANY, ItemPredicate.ANY);
        }

        public boolean test(LootContext context, ItemStack stack) {
            if (!this.stoneborn.matches(context)) {
                return false;
            } else {
                return this.item.matches(stack);
            }
        }

        @Override
        public JsonObject serializeToJson(SerializationContext conditions) {
            JsonObject jsonobject = super.serializeToJson(conditions);
            jsonobject.add("item", this.item.serializeToJson());
            jsonobject.add("stoneborn", this.stoneborn.toJson(conditions));
            return jsonobject;
        }
    }
}