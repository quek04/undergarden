package quek.undergarden.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class ChillyEffect extends MobEffect {

	public static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("EC6F9365-42AA-4D06-AB22-36D799177F32");

	public ChillyEffect() {
		super(MobEffectCategory.HARMFUL, 9154528);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_MODIFIER_UUID.toString(), -0.15D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}
}
