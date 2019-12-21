package quek.undergarden.block.world;

import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class UndergardenOre extends OreBlock {
    public UndergardenOre(int harvestlvl) {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(3f,35f)
                .sound(SoundType.STONE)
                .harvestLevel(harvestlvl)
                .harvestTool(ToolType.PICKAXE)
        );
    }
}
