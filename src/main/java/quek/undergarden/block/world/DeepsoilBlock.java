package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.ToolType;

public class DeepsoilBlock extends Block {

    public DeepsoilBlock() {
        super(Properties.create(Material.EARTH)
                .hardnessAndResistance(0.6f, 3f)
                .sound(SoundType.GROUND)
                .harvestTool(ToolType.SHOVEL)
        );
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        boolean hasWater = world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.south()).getMaterial() == Material.WATER;
        return plantable.getPlantType(world, pos.offset(facing)) == PlantType.Plains ||
                plantable.getPlantType(world, pos.offset(facing)) == PlantType.Beach && hasWater;
    }
}
