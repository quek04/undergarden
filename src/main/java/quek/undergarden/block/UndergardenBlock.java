package quek.undergarden.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class UndergardenBlock extends Block {

    public UndergardenBlock(Material material, float hardness, float resist, SoundType sound, int harvestlvl, ToolType tool) {
        super(Properties.create(material)
                .hardnessAndResistance(hardness,resist)
                .sound(sound)
                .harvestLevel(harvestlvl)
                .harvestTool(tool)
        );
    }

    public UndergardenBlock(Material material, float hardness, float resist, SoundType sound, int harvestlvl, ToolType tool, int light) {
        super(Properties.create(material)
                .hardnessAndResistance(hardness,resist)
                .sound(sound)
                .harvestLevel(harvestlvl)
                .harvestTool(tool)
                .lightValue(light)
        );
    }

    public UndergardenBlock(Properties properties) {
        super(properties);
    }
}
