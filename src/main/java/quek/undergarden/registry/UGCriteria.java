package quek.undergarden.registry;

import net.minecraft.advancements.CriteriaTriggers;
import quek.undergarden.criterion.SlingshotFireTrigger;
import quek.undergarden.criterion.StonebornTradeTrigger;

public class UGCriteria {

	public static final StonebornTradeTrigger STONEBORN_TRADE = new StonebornTradeTrigger();
	public static final SlingshotFireTrigger SLINGSHOT_FIRE = new SlingshotFireTrigger();

	public static void register() {
		CriteriaTriggers.register(STONEBORN_TRADE);
		CriteriaTriggers.register(SLINGSHOT_FIRE);
	}
}