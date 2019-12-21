package quek.undergarden.block.world;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraftforge.common.ToolType;

import java.util.List;
import java.util.Random;

public class UndergardenGrass extends GrassBlock {
    public UndergardenGrass() {
        super(Properties.create(Material.ORGANIC)
                .hardnessAndResistance(0.6f, 3f)
                .sound(SoundType.PLANT)
                .harvestLevel(0)
                .harvestTool(ToolType.SHOVEL)
                .tickRandomly()
        );
    }
}
