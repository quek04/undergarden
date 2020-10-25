package quek.undergarden.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGFoods;
import quek.undergarden.registry.UGItemGroups;

public class DroopvineItem extends BlockItem {

    public DroopvineItem() {
        super(UGBlocks.droopvine_top.get(), (new Item.Properties()).group(UGItemGroups.GROUP).food(UGFoods.DROOPVINE));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemStack = super.onItemUseFinish(stack, worldIn, entityLiving);
        entityLiving.addPotionEffect(new EffectInstance(Effects.GLOWING, 600, 0, false, true));
        return itemStack;
    }
}
