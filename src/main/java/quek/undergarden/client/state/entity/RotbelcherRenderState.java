package quek.undergarden.client.state.entity;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class RotbelcherRenderState extends LivingEntityRenderState {

	public boolean isCharging;

	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState shootAnimationState = new AnimationState();

}
