package quek.undergarden.world.gen.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.common.IPlantable;
import quek.undergarden.registry.UndergardenBlocks;

import java.util.List;

public class UndergardenTreeFeatureConfig extends TreeFeatureConfig {

    public final FoliagePlacer foliagePlacer;
    public final int heightRandA;
    public final int heightRandB;
    public final int trunkHeight;
    public final int trunkHeightRandom;
    public final int trunkTopOffset;
    public final int trunkTopOffsetRandom;
    public final int foliageHeight;
    public final int foliageHeightRandom;
    public final int maxWaterDepth;
    public final boolean ignoreVines;

    public UndergardenTreeFeatureConfig(BlockStateProvider trunkProviderIn, BlockStateProvider leavesProviderIn, FoliagePlacer foliagePlacerIn, List<TreeDecorator> decoratorsIn, int baseHeightIn, int heightRandAIn, int heightRandBIn, int trunkHeightIn, int trunkHeightRandomIn, int trunkTopOffsetIn, int trunkTopOffsetRandomIn, int foliageHeightIn, int foliageHeightRandomIn, int maxWaterDepthIn, boolean ignoreVinesIn) {
        super(trunkProviderIn, leavesProviderIn, foliagePlacerIn, decoratorsIn, baseHeightIn, heightRandAIn, heightRandBIn, trunkHeightIn, trunkHeightRandomIn, trunkTopOffsetIn, trunkTopOffsetRandomIn, foliageHeightIn, foliageHeightRandomIn, maxWaterDepthIn, ignoreVinesIn);
        this.foliagePlacer = foliagePlacerIn;
        this.heightRandA = heightRandAIn;
        this.heightRandB = heightRandBIn;
        this.trunkHeight = trunkHeightIn;
        this.trunkHeightRandom = trunkHeightRandomIn;
        this.trunkTopOffset = trunkTopOffsetIn;
        this.trunkTopOffsetRandom = trunkTopOffsetRandomIn;
        this.foliageHeight = foliageHeightIn;
        this.foliageHeightRandom = foliageHeightRandomIn;
        this.maxWaterDepth = maxWaterDepthIn;
        this.ignoreVines = ignoreVinesIn;
    }

    @Override
    public UndergardenTreeFeatureConfig setSapling(IPlantable value) {
        super.setSapling(value);
        return this;
    }

    public static <T> UndergardenTreeFeatureConfig deserialize(Dynamic<T> data) {
        BaseTreeFeatureConfig basetreefeatureconfig = BaseTreeFeatureConfig.deserialize(data);
        FoliagePlacerType<?> foliageplacertype = Registry.FOLIAGE_PLACER_TYPE.getOrDefault(new ResourceLocation(data.get("foliage_placer").get("type").asString().orElseThrow(RuntimeException::new)));
        return new UndergardenTreeFeatureConfig(basetreefeatureconfig.trunkProvider, basetreefeatureconfig.leavesProvider, foliageplacertype.func_227391_a_(data.get("foliage_placer").orElseEmptyMap()), basetreefeatureconfig.decorators, basetreefeatureconfig.baseHeight, data.get("height_rand_a").asInt(0), data.get("height_rand_b").asInt(0), data.get("trunk_height").asInt(-1), data.get("trunk_height_random").asInt(0), data.get("trunk_top_offset").asInt(0), data.get("trunk_top_offset_random").asInt(0), data.get("foliage_height").asInt(-1), data.get("foliage_height_random").asInt(0), data.get("max_water_depth").asInt(0), data.get("ignore_vines").asBoolean(false));
    }

    public static <T> UndergardenTreeFeatureConfig deserializeJungle(Dynamic<T> data) {
        return deserialize(data).setSapling((net.minecraftforge.common.IPlantable)net.minecraft.block.Blocks.JUNGLE_SAPLING);
    }

    public static <T> UndergardenTreeFeatureConfig deserializeSmogstem(Dynamic<T> data) {
        return deserialize(data).setSapling(UndergardenBlocks.smogstem_sapling.get());
    }

    public static <T> UndergardenTreeFeatureConfig deserializeWigglewood(Dynamic<T> data) {
        return deserialize(data).setSapling(UndergardenBlocks.wigglewood_sapling.get());
    }

    public static class Builder extends BaseTreeFeatureConfig.Builder {
        private final FoliagePlacer foliagePlacer;
        private List<TreeDecorator> decorators = ImmutableList.of();
        private int baseHeight;
        private int heightRandA;
        private int heightRandB;
        private int trunkHeight = -1;
        private int trunkHeightRandom;
        private int trunkTopOffset;
        private int trunkTopOffsetRandom;
        private int foliageHeight = -1;
        private int foliageHeightRandom;
        private int maxWaterDepth;
        private boolean ignoreVines;

        public Builder(BlockStateProvider trunkProviderIn, BlockStateProvider leavesProviderIn, FoliagePlacer foliagePlacerIn) {
            super(trunkProviderIn, leavesProviderIn);
            this.foliagePlacer = foliagePlacerIn;
        }

        public UndergardenTreeFeatureConfig.Builder decorators(List<TreeDecorator> decoratorsIn) {
            this.decorators = decoratorsIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder baseHeight(int baseHeightIn) {
            this.baseHeight = baseHeightIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder heightRandA(int heightRandAIn) {
            this.heightRandA = heightRandAIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder heightRandB(int heightRandBIn) {
            this.heightRandB = heightRandBIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder trunkHeight(int trunkHeightIn) {
            this.trunkHeight = trunkHeightIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder trunkHeightRandom(int trunkHeightRandomIn) {
            this.trunkHeightRandom = trunkHeightRandomIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder trunkTopOffset(int trunkTopOffsetIn) {
            this.trunkTopOffset = trunkTopOffsetIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder trunkTopOffsetRandom(int trunkTopOffsetRandomIn) {
            this.trunkTopOffsetRandom = trunkTopOffsetRandomIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder foliageHeight(int foliageHeightIn) {
            this.foliageHeight = foliageHeightIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder foliageHeightRandom(int foliageHeightRandomIn) {
            this.foliageHeightRandom = foliageHeightRandomIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder maxWaterDepth(int maxWaterDepthIn) {
            this.maxWaterDepth = maxWaterDepthIn;
            return this;
        }

        public UndergardenTreeFeatureConfig.Builder ignoreVines() {
            this.ignoreVines = true;
            return this;
        }

        @Override
        public UndergardenTreeFeatureConfig.Builder setSapling(net.minecraftforge.common.IPlantable value) {
            super.setSapling(value);
            return this;
        }

        public UndergardenTreeFeatureConfig build() {
            return new UndergardenTreeFeatureConfig(this.trunkProvider, this.leavesProvider, this.foliagePlacer, this.decorators, this.baseHeight, this.heightRandA, this.heightRandB, this.trunkHeight, this.trunkHeightRandom, this.trunkTopOffset, this.trunkTopOffsetRandom, this.foliageHeight, this.foliageHeightRandom, this.maxWaterDepth, this.ignoreVines).setSapling(this.sapling);
        }
    }

}
