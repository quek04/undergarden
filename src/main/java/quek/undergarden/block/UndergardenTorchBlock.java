package quek.undergarden.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;

public class UndergardenTorchBlock extends TorchBlock {

    public UndergardenTorchBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0F, 0F)
                .lightValue(13)
                .sound(SoundType.WOOD)
        );
    }
}
