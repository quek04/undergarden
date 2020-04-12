package quek.undergarden.block.world;

import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class UndergardenLogBlock extends LogBlock {
    public UndergardenLogBlock() {
        super(MaterialColor.WOOD, Properties.create(Material.WOOD)
                .hardnessAndResistance(1.5F, 10F)
                .sound(SoundType.WOOD)
                .harvestTool(ToolType.AXE)
        );
    }
}
