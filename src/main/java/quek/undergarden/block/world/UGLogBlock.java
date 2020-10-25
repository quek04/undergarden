package quek.undergarden.block.world;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraftforge.common.ToolType;

import net.minecraft.block.AbstractBlock.Properties;

public class UGLogBlock extends RotatedPillarBlock {
    public UGLogBlock() {
        super(Properties.create(Material.WOOD)
                .hardnessAndResistance(2F)
                .sound(SoundType.WOOD)
                .harvestTool(ToolType.AXE)
        );
    }
}
