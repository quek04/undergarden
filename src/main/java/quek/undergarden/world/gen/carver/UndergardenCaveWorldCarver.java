package quek.undergarden.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.function.Function;

public class UndergardenCaveWorldCarver extends CaveWorldCarver {

    public UndergardenCaveWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49929_1_) {
        super(p_i49929_1_, 128);
        this.carvableBlocks = ImmutableSet.of(
                UndergardenBlocks.depthrock.get(),
                UndergardenBlocks.deepturf_block.get(),
                UndergardenBlocks.deepsoil.get(),
                UndergardenBlocks.coal_ore.get(),
                UndergardenBlocks.cloggrum_ore.get(),
                UndergardenBlocks.froststeel_ore.get(),
                UndergardenBlocks.utherium_ore.get()
        );
        this.carvableFluids = ImmutableSet.of(
                Fluids.WATER
        );
    }
}
