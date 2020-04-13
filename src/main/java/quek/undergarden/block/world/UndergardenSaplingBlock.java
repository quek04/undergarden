package quek.undergarden.block.world;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class UndergardenSaplingBlock extends SaplingBlock {

    public UndergardenSaplingBlock(Tree tree) {
        super(tree, Properties.create(Material.PLANTS)
                .hardnessAndResistance(0F)
                .tickRandomly()
                .sound(SoundType.PLANT)
                .notSolid()
                .doesNotBlockMovement()
        );
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (worldIn.getLight(pos.up()) >= 0 && rand.nextInt(7) == 0) {
            this.func_226942_a_(worldIn, pos, state, rand);
        }

    }

}
