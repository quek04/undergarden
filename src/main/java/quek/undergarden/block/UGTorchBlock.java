package quek.undergarden.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;

import net.minecraft.block.AbstractBlock.Properties;

public class UGTorchBlock extends TorchBlock {

    public UGTorchBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0F)
                .setLightLevel((state) -> 14)
                .sound(SoundType.WOOD),
                ParticleTypes.FLAME
        );
    }
}
