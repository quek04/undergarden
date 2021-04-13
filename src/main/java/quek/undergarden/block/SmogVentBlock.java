package quek.undergarden.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import quek.undergarden.registry.UGTileEntities;

import net.minecraft.block.AbstractBlock.Properties;

public class SmogVentBlock extends Block {

    public SmogVentBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.fireImmune() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn)) {
            entityIn.setSecondsOnFire(3);
        }

        super.stepOn(worldIn, pos, entityIn);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return UGTileEntities.SMOG_VENT.get().create();
    }
}