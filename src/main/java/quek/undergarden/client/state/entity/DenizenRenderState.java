package quek.undergarden.client.state.entity;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import quek.undergarden.entity.monster.denizen.Denizen;

public class DenizenRenderState extends HumanoidRenderState {

	public Denizen.Type variant = Denizen.Type.SHORT;
}
