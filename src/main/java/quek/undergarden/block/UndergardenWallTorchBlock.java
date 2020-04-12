package quek.undergarden.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;

public class UndergardenWallTorchBlock extends WallTorchBlock {

    public UndergardenWallTorchBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0F, 0F)
                .lightValue(13)
                .sound(SoundType.WOOD)
        );
    }
}
