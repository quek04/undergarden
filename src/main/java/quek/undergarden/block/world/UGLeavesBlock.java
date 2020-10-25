package quek.undergarden.block.world;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class UGLeavesBlock extends LeavesBlock {

    public UGLeavesBlock() {
        super(Properties.create(Material.LEAVES)
                .hardnessAndResistance(0.2F)
                .sound(SoundType.PLANT)
                .notSolid()
        );
    }
}

