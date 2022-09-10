package quek.undergarden.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import quek.undergarden.registry.UGCreativeModeTabs;
import quek.undergarden.registry.UGSoundEvents;

import javax.annotation.Nullable;

public class DitchbulbPasteItem extends Item {

    public DitchbulbPasteItem() {
        super(new Properties()
                .tab(UGCreativeModeTabs.GROUP)
        );
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 800;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        boolean success = false;
        if (!CampfireBlock.canLight(state) && !CandleBlock.canLight(state) && !CandleCakeBlock.canLight(state)) {
            pos = pos.relative(context.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, pos, context.getHorizontalDirection())) {
                this.playSound(level, pos);
                level.setBlockAndUpdate(pos, BaseFireBlock.getState(level, pos));
                level.gameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, pos);
                success = true;
            }
        } else {
            this.playSound(level, pos);
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE));
            level.gameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, pos);
            success = true;
        }

        if (success) {
            context.getItemInHand().shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.FAIL;
        }
    }

    private void playSound(Level level, BlockPos pos) {
        RandomSource random = level.getRandom();
        level.playSound(null, pos, UGSoundEvents.DITCHBULB_PASTE_USE.get(), SoundSource.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
    }
}