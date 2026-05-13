package quek.undergarden.datagen.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.Tags;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGEntityTypes;
import quek.undergarden.registry.UGTags;

import java.util.concurrent.CompletableFuture;

public class UGEntityTags extends EntityTypeTagsProvider {

	public UGEntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
		super(output, future, Undergarden.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//undergarden
		tag(UGTags.Entities.ROTSPAWN).add(UGEntityTypes.ROTLING.get(), UGEntityTypes.ROTWALKER.get(), UGEntityTypes.ROTBEAST.get(), UGEntityTypes.ROTBELCHER.get());
		tag(UGTags.Entities.CAVERN_CREATURE).add(UGEntityTypes.NARGOYLE.get(), UGEntityTypes.SCINTLING.get(), UGEntityTypes.MUNCHER.get(), UGEntityTypes.SPLOOGIE.get());
		tag(UGTags.Entities.IMMUNE_TO_VIRULENT_MIX).addTag(UGTags.Entities.ROTSPAWN).addTag(UGTags.Entities.CAVERN_CREATURE).addTag(Tags.EntityTypes.BOSSES).add(UGEntityTypes.STONEBORN.get(), UGEntityTypes.MOG.get(), UGEntityTypes.SMOG_MOG.get(), UGEntityTypes.GLOOMPER.get(), EntityType.WARDEN);
		tag(UGTags.Entities.IMMUNE_TO_SCINTLING_GOO).add(UGEntityTypes.SCINTLING.get(), UGEntityTypes.FORGOTTEN_GUARDIAN.get(), UGEntityTypes.DWELLER.get(), EntityType.WARDEN);
		tag(UGTags.Entities.IMMUNE_TO_BLISTERBERRY_BUSH).add(UGEntityTypes.SCINTLING.get(), UGEntityTypes.SMOG_MOG.get());
		tag(UGTags.Entities.IMMUNE_TO_INFECTION).addTag(UGTags.Entities.ROTSPAWN).addTag(EntityTypeTags.UNDEAD).add(UGEntityTypes.DENIZEN.get(), UGEntityTypes.FORGOTTEN.get(), UGEntityTypes.FORGOTTEN_GUARDIAN.get(), UGEntityTypes.MINION.get(), UGEntityTypes.GREATER_DWELLER.get(), UGEntityTypes.MYSTERIOUS_POT.get());
		tag(UGTags.Entities.IMMUNE_TO_GOOEY_EFFECT).add(UGEntityTypes.SCINTLING.get());
		tag(UGTags.Entities.IMMUNE_TO_THORNREED).add(UGEntityTypes.GWIBLING.get(), UGEntityTypes.GWIB.get(), UGEntityTypes.UNDERGAR.get());

		//forge
		tag(Tags.EntityTypes.BOSSES).add(UGEntityTypes.FORGOTTEN_GUARDIAN.get());

		//vanilla
		tag(EntityTypeTags.IMPACT_PROJECTILES).add(UGEntityTypes.BLISTERBOMB.get(), UGEntityTypes.SLINGSHOT_PROJECTILE.get(), UGEntityTypes.MINION_PROJECTILE.get(), UGEntityTypes.ROTBELCHER_PROJECTILE.get());
		tag(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(UGEntityTypes.SCINTLING.get(), UGEntityTypes.ROTLING.get(), UGEntityTypes.MUNCHER.get(), UGEntityTypes.SPLOOGIE.get(), UGEntityTypes.MOG.get(), UGEntityTypes.SMOG_MOG.get(), UGEntityTypes.FORGOTTEN_GUARDIAN.get(), UGEntityTypes.BRUTE.get());
		tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(UGEntityTypes.FORGOTTEN_GUARDIAN.get());
		tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(UGEntityTypes.FORGOTTEN_GUARDIAN.get(), UGEntityTypes.MINION.get());
		tag(EntityTypeTags.DISMOUNTS_UNDERWATER).add(UGEntityTypes.DWELLER.get());
		tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(UGEntityTypes.GLOOMPER.get());
		tag(EntityTypeTags.UNDEAD).add(UGEntityTypes.FORGOTTEN.get()).addTag(UGTags.Entities.ROTSPAWN);
		tag(EntityTypeTags.DEFLECTS_PROJECTILES).add(UGEntityTypes.FORGOTTEN_GUARDIAN.get());
		tag(EntityTypeTags.CAN_EQUIP_SADDLE).add(UGEntityTypes.DWELLER.get());
	}
}