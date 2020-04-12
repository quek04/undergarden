package quek.undergarden.block.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.Random;

public class UndergardenOreBlock extends OreBlock {
    public UndergardenOreBlock(int harvestlvl) {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(3F,3F)
                .sound(SoundType.STONE)
                .harvestLevel(harvestlvl)
                .harvestTool(ToolType.PICKAXE)
        );
    }

    protected int getExperience(Random rando) {
        if (this == UndergardenBlocks.coal_ore.get()) {
            return MathHelper.nextInt(rando, 0, 2);
        } else {
            return this == UndergardenBlocks.utherium_ore.get() ? MathHelper.nextInt(rando, 4, 8) : 0;
        }
    }

    public void spawnAdditionalDrops(BlockState state, World worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
