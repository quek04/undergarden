package quek.undergarden.block;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

import java.util.function.Supplier;

public class UndergardenStairsBlock extends StairsBlock {

    public UndergardenStairsBlock(Supplier<Block> state) {
        super(() -> state.get().getDefaultState(), Properties.from(state.get()));
    }
}
