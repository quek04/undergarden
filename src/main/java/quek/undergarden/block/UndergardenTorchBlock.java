package quek.undergarden.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;

public class UndergardenTorchBlock extends TorchBlock {

    public UndergardenTorchBlock() {
        super(Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0F)
                .setLightLevel((state) -> 14)
                .sound(SoundType.WOOD),
                ParticleTypes.FLAME
        );
    }

    public UndergardenTorchBlock(BasicParticleType particleType, int light) {
        super(Properties.create(Material.MISCELLANEOUS)
                        .doesNotBlockMovement()
                        .hardnessAndResistance(0F)
                        .setLightLevel((state) -> 14)
                        .sound(SoundType.WOOD),
               particleType
        );
    }
}
