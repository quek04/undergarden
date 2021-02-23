package quek.undergarden.event;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.registry.UGDimensions;

@Mod.EventBusSubscriber
public class UndergardenEventHandler {

    @SubscribeEvent
    public static void rightClickEvent(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);

        if (player.getEntityWorld().getDimensionKey() == UGDimensions.UNDERGARDEN_WORLD) {
            if (state.getBlock() == Blocks.RESPAWN_ANCHOR) {
                if (!world.isRemote()) {
                    world.removeBlock(pos, false);
                    world.createExplosion(null, DamageSource.func_233546_a_(), null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 5.0F, true, Explosion.Mode.DESTROY);
                }
                player.swingArm(Hand.MAIN_HAND);
                event.setCanceled(true);
            }
        }
    }
}
