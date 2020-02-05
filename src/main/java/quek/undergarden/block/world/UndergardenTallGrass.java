package quek.undergarden.block.world;

import net.minecraft.block.SoundType;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.block.material.Material;

public class UndergardenTallGrass extends TallGrassBlock {
    public UndergardenTallGrass() {
        super(Properties.create(Material.TALL_PLANTS)
                .hardnessAndResistance(0f)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement()
        );
    }
}
