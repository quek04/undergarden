package quek.undergarden.block.world;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.KelpBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import quek.undergarden.registry.UGBlocks;

public class GlowingKelpBlock extends KelpBlock {

    private final GlowingKelpTopBlock top;

    public GlowingKelpBlock(GlowingKelpTopBlock topBlock) {
        super(Properties.create(Material.OCEAN_PLANT)
                .doesNotBlockMovement()
                .hardnessAndResistance(0F)
                .sound(SoundType.WET_GRASS)
                .setLightLevel((state) -> 10)
        );
        this.top = topBlock;
    }

    @Override
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return UGBlocks.glowing_kelp.get();
    }
}
