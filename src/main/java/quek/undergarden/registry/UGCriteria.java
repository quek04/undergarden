package quek.undergarden.registry;

import net.minecraft.advancements.CriteriaTriggers;
import quek.undergarden.criterion.StonebornTradeTrigger;

public class UGCriteria {

    public static final StonebornTradeTrigger stoneborn_trade = new StonebornTradeTrigger();

    public static void register() {
        CriteriaTriggers.register(stoneborn_trade);
    }
}
