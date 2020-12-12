package quek.undergarden.registry;

import net.minecraft.advancements.CriteriaTriggers;
import quek.undergarden.criterion.StonebornTradeTrigger;

public class UGCriteria {

    public static final StonebornTradeTrigger STONEBORN_TRADE = new StonebornTradeTrigger();

    public static void register() {
        CriteriaTriggers.register(STONEBORN_TRADE);
    }
}