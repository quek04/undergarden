package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.SeaGrassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GlowingSeaGrassBlock extends SeaGrassBlock {

    public GlowingSeaGrassBlock() {
        super(Properties.create(Material.SEA_GRASS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0F)
                .sound(SoundType.WET_GRASS)
                .setLightLevel((state) -> 7)
        );
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return false;
    }
}
