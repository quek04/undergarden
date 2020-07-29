package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class SmogVentBlock extends Block {

    public SmogVentBlock() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(3F, 6F)
                .sound(SoundType.STONE)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)
                .setLightLevel((state) -> 15)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double x = (double)pos.getX() + 0.5D;
        double y = (double)pos.getY() + 1D;
        double z = (double)pos.getZ() + 0.5D;

        for(int i = 0; i < 6; i++) {
            if(worldIn.isAirBlock(pos.up())) {
                worldIn.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, x, y, z, 0.0D, 0.05D, 0.0D);
            }
        }
    }

}
