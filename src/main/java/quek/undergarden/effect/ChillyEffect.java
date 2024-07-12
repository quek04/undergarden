package quek.undergarden.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import quek.undergarden.Undergarden;

public class ChillyEffect extends MobEffect {

	public ChillyEffect() {
		super(MobEffectCategory.HARMFUL, 9154528);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Undergarden.MODID, "effect.chilly_slowness"), -0.15D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}
}
