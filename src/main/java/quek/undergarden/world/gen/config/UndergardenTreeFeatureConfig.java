package quek.undergarden.world.gen.config;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.List;

public class UndergardenTreeFeatureConfig extends BaseTreeFeatureConfig {

    protected UndergardenTreeFeatureConfig(BlockStateProvider trunkProviderIn, BlockStateProvider leavesProviderIn, List<TreeDecorator> decoratorsIn, int baseHeightIn) {
        super(trunkProviderIn, leavesProviderIn, decoratorsIn, baseHeightIn);
    }

    @Override
    protected UndergardenTreeFeatureConfig setSapling(net.minecraftforge.common.IPlantable sapling) {
        super.setSapling(sapling);
        return this;
    }

    public static <T> UndergardenTreeFeatureConfig deserialize(Dynamic<T> data) {
        BaseTreeFeatureConfig config = BaseTreeFeatureConfig.deserialize(data);
        return new UndergardenTreeFeatureConfig(config.trunkProvider, config.leavesProvider, config.decorators, config.baseHeight);
    }

    public static <T> UndergardenTreeFeatureConfig deserializeSmogstem(Dynamic<T> data) {
        return deserialize(data).setSapling(UndergardenBlocks.smogstem_sapling.get());
    }

    //TODO: deserializeWiggleWood

    public static class Builder extends BaseTreeFeatureConfig.Builder {
        private List<TreeDecorator> decorators = Lists.newArrayList();
        private int baseHeight = 0;

        public Builder(BlockStateProvider trunkProviderIn, BlockStateProvider leavesProviderIn) {
            super(trunkProviderIn, leavesProviderIn);
        }

        public UndergardenTreeFeatureConfig.Builder baseHeight(int baseHeightIn) {
            this.baseHeight = baseHeightIn;
            return this;
        }

        @Override
        public UndergardenTreeFeatureConfig.Builder setSapling(net.minecraftforge.common.IPlantable value) {
            super.setSapling(sapling);
            return this;
        }

        @Override
        public UndergardenTreeFeatureConfig build() {
            return new UndergardenTreeFeatureConfig(trunkProvider, leavesProvider, decorators, this.baseHeight);
        }
    }

}
