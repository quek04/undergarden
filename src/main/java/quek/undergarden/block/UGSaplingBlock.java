package quek.undergarden.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class UGSaplingBlock extends SaplingBlock {

    public UGSaplingBlock(AbstractTreeGrower tree) {
        super(tree, Properties.of(Material.PLANT)
                .strength(0F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .noCollission()
        );
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        super.randomTick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        this.advanceTree(worldIn, pos, state, rand);
    }
}