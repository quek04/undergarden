package quek.undergarden.block.world;

import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class UndergardenLog extends LogBlock {
    public UndergardenLog() {
        super(MaterialColor.WOOD, Properties.create(Material.WOOD)
                .hardnessAndResistance(1.5f, 2f)
                .sound(SoundType.WOOD)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE)
        );
    }
}
