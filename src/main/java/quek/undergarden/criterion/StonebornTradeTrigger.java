package quek.undergarden.criterion;

import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.*;
import quek.undergarden.Undergarden;
import quek.undergarden.entity.stoneborn.StonebornEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ResourceLocation;

public class StonebornTradeTrigger extends AbstractCriterionTrigger<StonebornTradeTrigger.Instance> {

    private static final ResourceLocation ID = new ResourceLocation(Undergarden.MODID, "stoneborn_trade");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public StonebornTradeTrigger.Instance deserializeTrigger(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser conditionsParser) {
        EntityPredicate.AndPredicate entitypredicate$andpredicate = EntityPredicate.AndPredicate.deserializeJSONObject(json, "stoneborn", conditionsParser);
        ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
        return new StonebornTradeTrigger.Instance(entityPredicate, entitypredicate$andpredicate, itempredicate);
    }

    public void test(ServerPlayerEntity player, StonebornEntity stoneborn, ItemStack stack) {
        LootContext lootcontext = EntityPredicate.getLootContext(player, stoneborn);
        this.triggerListeners(player, (instance) -> instance.test(lootcontext, stack));
    }

    public static class Instance extends CriterionInstance {
        private final EntityPredicate.AndPredicate stoneborn;
        private final ItemPredicate item;

        public Instance(EntityPredicate.AndPredicate player, EntityPredicate.AndPredicate stoneborn, ItemPredicate stack) {
            super(StonebornTradeTrigger.ID, player);
            this.stoneborn = stoneborn;
            this.item = stack;
        }

        public static StonebornTradeTrigger.Instance any() {
            return new StonebornTradeTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND, EntityPredicate.AndPredicate.ANY_AND, ItemPredicate.ANY);
        }

        public boolean test(LootContext context, ItemStack stack) {
            if (!this.stoneborn.testContext(context)) {
                return false;
            } else {
                return this.item.test(stack);
            }
        }

        @Override
        public JsonObject serialize(ConditionArraySerializer conditions) {
            JsonObject jsonobject = super.serialize(conditions);
            jsonobject.add("item", this.item.serialize());
            jsonobject.add("stoneborn", this.stoneborn.serializeConditions(conditions));
            return jsonobject;
        }
    }
}
