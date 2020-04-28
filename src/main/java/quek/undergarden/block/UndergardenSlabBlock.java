package quek.undergarden.block;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class UndergardenSlabBlock extends SlabBlock {

    public UndergardenSlabBlock(Material material, float hardness, float resistance, SoundType sound, int level, ToolType tool) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance).sound(sound).harvestLevel(level).harvestTool(tool));
    }
}
