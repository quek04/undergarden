package quek.undergarden.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import quek.undergarden.registry.UGBlocks;

public abstract class AbstractUndergardenStructure extends Structure<NoFeatureConfig> {

    public AbstractUndergardenStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static abstract class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public BlockPos getLowestLand(ChunkGenerator chunkGenerator) {
            BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(this.bounds.func_215126_f().getX(), 32, this.bounds.func_215126_f().getZ());
            IBlockReader blockView = chunkGenerator.func_230348_a_(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate = blockView.getBlockState(mutable);
            while (mutable.getY() <= 250) {

                if(blockView.getBlockState(mutable).getMaterial() != Material.AIR && blockView.getBlockState(mutable.up()).getMaterial() == Material.AIR && blockView.getBlockState(mutable.up(5)).getMaterial() == Material.AIR && isValidBlock(currentBlockstate)) {
                    mutable.move(Direction.UP);
                    break;
                }

                mutable.move(Direction.UP);
                currentBlockstate = blockView.getBlockState(mutable);
            }

            return mutable;
        }

        public BlockPos getHighestLand(ChunkGenerator chunkGenerator) {
            BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(this.bounds.func_215126_f().getX(), 250, this.bounds.func_215126_f().getZ());
            IBlockReader blockView = chunkGenerator.func_230348_a_(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate;
            while (mutable.getY() > 32) {
                currentBlockstate = blockView.getBlockState(mutable);
                if (!currentBlockstate.isNormalCube(blockView, mutable)) {
                    mutable.move(Direction.DOWN);
                    continue;
                }
                else if (blockView.getBlockState(mutable.add(0, 3, 0)).getMaterial() == Material.AIR && isValidBlock(currentBlockstate)) {
                    break;
                }
                mutable.move(Direction.DOWN);
            }

            return mutable;
        }

        private boolean isValidBlock(BlockState currentBlockstate) {
            return currentBlockstate.isIn(UGBlocks.DEEPTURF_BLOCK.get());
        }
    }
}
