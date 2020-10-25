package quek.undergarden.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.KelpTopBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import quek.undergarden.registry.UGBlocks;

public class GlowingKelpTopBlock extends KelpTopBlock {

    public GlowingKelpTopBlock() {
        super(Properties.create(Material.OCEAN_PLANT)
                .doesNotBlockMovement()
                .hardnessAndResistance(0F)
                .sound(SoundType.WET_GRASS)
                .setLightLevel((state) -> 10)
        );
    }

    @Override
    protected Block getBodyPlantBlock() {
        return UGBlocks.glowing_kelp_plant.get();
    }
}
