package quek.undergarden.client.state.block;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import quek.undergarden.Undergarden;

public class GrongletRenderState extends BlockEntityRenderState {

	public int yaw;
	public Identifier texture = Undergarden.prefix("textures/entity/gronglet/gronglet.png");
	public Direction facing = Direction.NORTH;
}
