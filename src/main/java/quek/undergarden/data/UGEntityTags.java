package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;
import quek.undergarden.UGMod;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGTags;

import javax.annotation.Nullable;

public class UGEntityTags extends EntityTypeTagsProvider {

    public UGEntityTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, UGMod.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Undergarden Entity Type Tags";
    }

    @Override
    protected void registerTags() {
        //undergarden
        tag(UGTags.Entities.ROTSPAWN).add(UGEntityTypes.ROTLING.get(), UGEntityTypes.ROTWALKER.get(), UGEntityTypes.ROTBEAST.get(), UGEntityTypes.ROTDWELLER.get());
        tag(UGTags.Entities.CAVERN_CREATURE).add(UGEntityTypes.NARGOYLE.get(), UGEntityTypes.SCINTLING.get(), UGEntityTypes.MUNCHER.get());

        //vanilla
        tag(EntityTypeTags.IMPACT_PROJECTILES).add(UGEntityTypes.BLISTERBOMB.get(), UGEntityTypes.ROTTEN_BLISTERBERRY.get(), UGEntityTypes.GOO_BALL.get(), UGEntityTypes.SLINGSHOT_AMMO.get());
    }

    private TagsProvider.Builder<EntityType<?>> tag(ITag.INamedTag<EntityType<?>> tag) {
        return this.getOrCreateBuilder(tag);
    }
}
