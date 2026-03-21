package quek.undergarden.client.state.block;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import quek.undergarden.Undergarden;

public class GrongletRenderState extends BlockEntityRenderState {

	public int yaw;
	public SpriteId texture = Sheets.BLOCK_ENTITIES_MAPPER.apply(Undergarden.prefix("gronglet/gronglet"));
	public Direction facing = Direction.NORTH;
}
