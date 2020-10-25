package quek.undergarden.block.world;

import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CarvedGloomgourdBlock extends CarvedPumpkinBlock {

    public CarvedGloomgourdBlock() {
        super(Properties.create(Material.GOURD)
                .hardnessAndResistance(1.0F)
                .sound(SoundType.WOOD)
        );
    }

    public CarvedGloomgourdBlock(int light) {
        super(Properties.create(Material.GOURD)
                .hardnessAndResistance(1.0F)
                .sound(SoundType.WOOD)
                .setLightLevel((state) -> light)
        );
    }
}
