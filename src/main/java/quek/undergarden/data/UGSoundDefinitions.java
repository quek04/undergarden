package quek.undergarden.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGSoundEvents;

public class UGSoundDefinitions extends SoundDefinitionsProvider {

	public UGSoundDefinitions(PackOutput output, ExistingFileHelper helper) {
		super(output, Undergarden.MODID, helper);
	}

	@Override
	public void registerSounds() {
		this.add(UGSoundEvents.UNDERGARDEN_AMBIENCE, definition().with(
				sound("undergarden:ambient/undergarden_ambience").stream()
		));
		this.add(UGSoundEvents.ABYSS_AMBIENCE, definition().with(
				sound("undergarden:ambient/abyss_ambience").stream()
		));
		this.add(UGSoundEvents.SPIRES_AMBIENCE, definition().with(
				sound("undergarden:ambient/spires_ambience").stream()
		));
		this.add(UGSoundEvents.FROST_AMBIENCE, definition().with(
				sound("undergarden:ambient/frost_ambience").stream()
		));
		this.add(UGSoundEvents.SEA_AMBIENCE, definition().with(
				sound("undergarden:ambient/sea_ambience").stream()
		));

		this.add(UGSoundEvents.ABYSS_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/abyss_1"),
				sound("undergarden:ambient/additions/abyss_2"),
				sound("undergarden:ambient/additions/abyss_3"),
				sound("undergarden:ambient/additions/abyss_4"),
				sound("undergarden:ambient/additions/abyss_5"),
				sound("undergarden:ambient/additions/abyss_6"),
				sound("undergarden:ambient/additions/abyss_7"),
				sound("undergarden:ambient/additions/abyss_8"),
				sound("undergarden:ambient/additions/abyss_9"),
				sound("undergarden:ambient/additions/abyss_10"),
				sound("undergarden:ambient/additions/abyss_11"),
				sound("undergarden:ambient/additions/abyss_12"),
				sound("undergarden:ambient/additions/abyss_13")
		));
		this.add(UGSoundEvents.BOG_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/bog_1"),
				sound("undergarden:ambient/additions/bog_2"),
				sound("undergarden:ambient/additions/bog_3"),
				sound("undergarden:ambient/additions/bog_4"),
				sound("undergarden:ambient/additions/bog_5"),
				sound("undergarden:ambient/additions/bog_6"),
				sound("undergarden:ambient/additions/bog_7"),
				sound("undergarden:ambient/additions/bog_8"),
				sound("undergarden:ambient/additions/bog_9"),
				sound("undergarden:ambient/additions/bog_10"),
				sound("undergarden:ambient/additions/bog_11"),
				sound("undergarden:ambient/additions/bog_12"),
				sound("undergarden:ambient/additions/bog_13"),
				sound("undergarden:ambient/additions/bog_14"),
				sound("undergarden:ambient/additions/bog_15")
		));
		this.add(UGSoundEvents.FIELDS_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/fields_1"),
				sound("undergarden:ambient/additions/fields_2"),
				sound("undergarden:ambient/additions/fields_3"),
				sound("undergarden:ambient/additions/fields_4"),
				sound("undergarden:ambient/additions/fields_5"),
				sound("undergarden:ambient/additions/fields_6"),
				sound("undergarden:ambient/additions/fields_7"),
				sound("undergarden:ambient/additions/fields_8"),
				sound("undergarden:ambient/additions/fields_9"),
				sound("undergarden:ambient/additions/fields_10"),
				sound("undergarden:ambient/additions/fields_11"),
				sound("undergarden:ambient/additions/fields_12"),
				sound("undergarden:ambient/additions/fields_13"),
				sound("undergarden:ambient/additions/fields_14"),
				sound("undergarden:ambient/additions/fields_15"),
				sound("undergarden:ambient/additions/fields_16"),
				sound("undergarden:ambient/additions/fields_17"),
				sound("undergarden:ambient/additions/fields_18")
		));
		this.add(UGSoundEvents.SMOGSTEM_FOREST_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/forest_1"),
				sound("undergarden:ambient/additions/forest_2"),
				sound("undergarden:ambient/additions/forest_3"),
				sound("undergarden:ambient/additions/forest_4"),
				sound("undergarden:ambient/additions/forest_5"),
				sound("undergarden:ambient/additions/forest_6"),
				sound("undergarden:ambient/additions/forest_7"),
				sound("undergarden:ambient/additions/forest_8"),
				sound("undergarden:ambient/additions/smogstem_1"),
				sound("undergarden:ambient/additions/smogstem_2"),
				sound("undergarden:ambient/additions/smogstem_3"),
				sound("undergarden:ambient/additions/smogstem_4"),
				sound("undergarden:ambient/additions/smogstem_5"),
				sound("undergarden:ambient/additions/smogstem_6")
		));
		this.add(UGSoundEvents.WIGGLEWOOD_FOREST_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/forest_1"),
				sound("undergarden:ambient/additions/forest_2"),
				sound("undergarden:ambient/additions/forest_3"),
				sound("undergarden:ambient/additions/forest_4"),
				sound("undergarden:ambient/additions/forest_5"),
				sound("undergarden:ambient/additions/forest_6"),
				sound("undergarden:ambient/additions/forest_7"),
				sound("undergarden:ambient/additions/forest_8"),
				sound("undergarden:ambient/additions/wigglewood_1"),
				sound("undergarden:ambient/additions/wigglewood_2"),
				sound("undergarden:ambient/additions/wigglewood_3"),
				sound("undergarden:ambient/additions/wigglewood_4"),
				sound("undergarden:ambient/additions/wigglewood_5"),
				sound("undergarden:ambient/additions/wigglewood_6"),
				sound("undergarden:ambient/additions/wigglewood_7"),
				sound("undergarden:ambient/additions/wigglewood_8"),
				sound("undergarden:ambient/additions/wigglewood_9")
		));
		this.add(UGSoundEvents.GRONGLEGROWTH_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/forest_1"),
				sound("undergarden:ambient/additions/forest_2"),
				sound("undergarden:ambient/additions/forest_3"),
				sound("undergarden:ambient/additions/forest_4"),
				sound("undergarden:ambient/additions/forest_5"),
				sound("undergarden:ambient/additions/forest_6"),
				sound("undergarden:ambient/additions/forest_7"),
				sound("undergarden:ambient/additions/forest_8"),
				sound("undergarden:ambient/additions/grongle_1"),
				sound("undergarden:ambient/additions/grongle_2"),
				sound("undergarden:ambient/additions/grongle_3"),
				sound("undergarden:ambient/additions/grongle_4"),
				sound("undergarden:ambient/additions/grongle_5"),
				sound("undergarden:ambient/additions/grongle_6")
		));
		this.add(UGSoundEvents.DENSE_FOREST_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/forest_1"),
				sound("undergarden:ambient/additions/forest_2"),
				sound("undergarden:ambient/additions/forest_3"),
				sound("undergarden:ambient/additions/forest_4"),
				sound("undergarden:ambient/additions/forest_5"),
				sound("undergarden:ambient/additions/forest_6"),
				sound("undergarden:ambient/additions/forest_7"),
				sound("undergarden:ambient/additions/forest_8"),
				sound("undergarden:ambient/additions/dense_1"),
				sound("undergarden:ambient/additions/dense_2"),
				sound("undergarden:ambient/additions/dense_3"),
				sound("undergarden:ambient/additions/dense_4"),
				sound("undergarden:ambient/additions/dense_5"),
				sound("undergarden:ambient/additions/dense_6")
		));
		this.add(UGSoundEvents.FROSTFIELDS_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/frostfields_1"),
				sound("undergarden:ambient/additions/frostfields_2"),
				sound("undergarden:ambient/additions/frostfields_3"),
				sound("undergarden:ambient/additions/frostfields_4"),
				sound("undergarden:ambient/additions/frostfields_5"),
				sound("undergarden:ambient/additions/frostfields_6"),
				sound("undergarden:ambient/additions/frostfields_7"),
				sound("undergarden:ambient/additions/frostfields_8"),
				sound("undergarden:ambient/additions/frostfields_9"),
				sound("undergarden:ambient/additions/frostfields_10"),
				sound("undergarden:ambient/additions/frostfields_11"),
				sound("undergarden:ambient/additions/frostfields_12"),
				sound("undergarden:ambient/additions/frostfields_13"),
				sound("undergarden:ambient/additions/frostfields_14"),
				sound("undergarden:ambient/additions/frostfields_15"),
				sound("undergarden:ambient/additions/frostfields_16"),
				sound("undergarden:ambient/additions/frostfields_17")
		));
		this.add(UGSoundEvents.SPIRES_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/spires_1"),
				sound("undergarden:ambient/additions/spires_2"),
				sound("undergarden:ambient/additions/spires_3"),
				sound("undergarden:ambient/additions/spires_4"),
				sound("undergarden:ambient/additions/spires_5"),
				sound("undergarden:ambient/additions/spires_6"),
				sound("undergarden:ambient/additions/spires_7"),
				sound("undergarden:ambient/additions/spires_8"),
				sound("undergarden:ambient/additions/spires_9"),
				sound("undergarden:ambient/additions/spires_10")
		));
		this.add(UGSoundEvents.SEA_AMBIENT_ADDITION, definition().with(
				sound("undergarden:ambient/additions/sea_1"),
				sound("undergarden:ambient/additions/sea_2"),
				sound("undergarden:ambient/additions/sea_3"),
				sound("undergarden:ambient/additions/sea_4"),
				sound("undergarden:ambient/additions/sea_5"),
				sound("undergarden:ambient/additions/sea_6"),
				sound("undergarden:ambient/additions/sea_7"),
				sound("undergarden:ambient/additions/sea_8"),
				sound("undergarden:ambient/additions/sea_9")
		));

		this.add(UGSoundEvents.MOOD, definition().with(
				sound("undergarden:ambient/moods/mood_1"),
				sound("undergarden:ambient/moods/mood_2"),
				sound("undergarden:ambient/moods/mood_3"),
				sound("undergarden:ambient/moods/mood_4"),
				sound("undergarden:ambient/moods/mood_5"),
				sound("undergarden:ambient/moods/mood_6"),
				sound("undergarden:ambient/moods/mood_7"),
				sound("undergarden:ambient/moods/mood_8"),
				sound("undergarden:ambient/moods/mood_9"),
				sound("undergarden:ambient/moods/mood_10"),
				sound("undergarden:ambient/moods/mood_11"),
				sound("undergarden:ambient/moods/mood_12"),
				sound("undergarden:ambient/moods/mood_13"),
				sound("undergarden:ambient/moods/mood_13"),
				sound("undergarden:ambient/moods/mood_14"),
				sound("undergarden:ambient/moods/mood_15")
		));
		this.add(UGSoundEvents.FROST_MOOD, definition().with(
				sound("undergarden:ambient/moods/frost_mood_1"),
				sound("undergarden:ambient/moods/frost_mood_2"),
				sound("undergarden:ambient/moods/frost_mood_3"),
				sound("undergarden:ambient/moods/frost_mood_4"),
				sound("undergarden:ambient/moods/frost_mood_5"),
				sound("undergarden:ambient/moods/frost_mood_6"),
				sound("undergarden:ambient/moods/frost_mood_7"),
				sound("undergarden:ambient/moods/frost_mood_8")
		));

		this.add(UGSoundEvents.UNDERGARDEN_MUSIC, definition().with(
				sound("undergarden:music/acasta_gneiss").stream(),
				sound("undergarden:music/all_that_wiggles_is_wood").stream(),
				sound("undergarden:music/brotherhood").stream(),
				sound("undergarden:music/caps").stream(),
				sound("undergarden:music/grongletune").stream(),
				sound("undergarden:music/monument").stream(),
				sound("undergarden:music/pit_of_shivers").stream(),
				sound("undergarden:music/smog").stream(),
				sound("undergarden:music/wrought").stream()
		));

		this.add(UGSoundEvents.MAMMOTH_DISC, definition().with(
				sound("undergarden:music/disc/mammoth").stream()
		));
		this.add(UGSoundEvents.LIMAX_MAXIMUS_DISC, definition().with(
				sound("undergarden:music/disc/limax_maximus").stream()
		));
		this.add(UGSoundEvents.RELICT_DISC, definition().with(
				sound("undergarden:music/disc/relict").stream()
		));
		this.add(UGSoundEvents.GLOOMPER_ANTHEM_DISC, definition().with(
				sound("undergarden:music/disc/gloomper_anthem").stream()
		));
		this.add(UGSoundEvents.GLOOMPER_SECRET_DISC, definition().with(
				sound("undergarden:music/disc/gloomper_secret").stream()
		));

		this.add(UGSoundEvents.UNDERGARDEN_PORTAL_AMBIENT, definition().with(
				sound("undergarden:block/undergarden_portal_ambient")
		).subtitle("subtitles.block.undergarden_portal.ambient"));
		this.add(UGSoundEvents.UNDERGARDEN_PORTAL_ACTIVATE, definition().with(
				sound("undergarden:block/undergarden_portal_activate")
		).subtitle("subtitles.block.undergarden_portal.activate"));
		this.add(UGSoundEvents.UNDERGARDEN_PORTAL_TRAVEL, definition().with(
				sound("undergarden:block/undergarden_portal_travel")
		).subtitle("subtitles.block.undergarden_portal.travel"));

		this.add(UGSoundEvents.VIRULENT_FLOW, definition().with(
				sound("undergarden:liquid/virulent_flowing_1"),
				sound("undergarden:liquid/virulent_flowing_2")
		).subtitle("subtitles.block.virulent.flow"));
		this.add(UGSoundEvents.VIRULENT_BUBBLE, definition().with(
				sound("undergarden:liquid/virulent_bubble_1"),
				sound("undergarden:liquid/virulent_bubble_2"),
				sound("undergarden:liquid/virulent_bubble_3"),
				sound("undergarden:liquid/virulent_bubble_4")
		).subtitle("subtitles.block.virulent.bubble"));

		this.add(UGSoundEvents.GRONGLET_AMBIENT, definition().with(
				sound("undergarden:block/gronglet/gronglet_ambient_1"),
				sound("undergarden:block/gronglet/gronglet_ambient_2"),
				sound("undergarden:block/gronglet/gronglet_ambient_3")
		).subtitle("subtitles.block.gronglet.ambient"));
		this.add(UGSoundEvents.GRONGLET_BREAK, definition().with(
				sound("undergarden:block/gronglet/gronglet_1"),
				sound("undergarden:block/gronglet/gronglet_2"),
				sound("undergarden:block/gronglet/gronglet_3"),
				sound("undergarden:block/gronglet/gronglet_4"),
				sound("undergarden:block/gronglet/gronglet_5"),
				sound("undergarden:block/gronglet/gronglet_6")
		).subtitle("subtitles.block.generic.break"));
		this.add(UGSoundEvents.GRONGLET_PLACE, definition().with(
				sound("undergarden:block/gronglet/gronglet_1"),
				sound("undergarden:block/gronglet/gronglet_2"),
				sound("undergarden:block/gronglet/gronglet_3"),
				sound("undergarden:block/gronglet/gronglet_4"),
				sound("undergarden:block/gronglet/gronglet_5"),
				sound("undergarden:block/gronglet/gronglet_6")
		).subtitle("subtitles.block.generic.place"));
		this.add(UGSoundEvents.GRONGLET_BURN, definition().with(
				sound("undergarden:block/gronglet/gronglet_burn")
		).subtitle("subtitles.block.gronglet.burn"));

		this.add(UGSoundEvents.BLISTERBOMB_THROW, definition().with(
				sound("undergarden:item/blisterbomb")
		).subtitle("subtitles.item.blisterbomb"));

		this.add(UGSoundEvents.SLINGSHOT_SHOOT, definition().with(
				sound("undergarden:item/slingshot_shoot")
		).subtitle("subtitles.item.slingshot.shoot"));
		this.add(UGSoundEvents.SLINGSHOT_DRAW, definition().with(
				sound("undergarden:item/slingshot_draw_1"),
				sound("undergarden:item/slingshot_draw_2")
		).subtitle("subtitles.item.slingshot.draw"));
		this.add(UGSoundEvents.GRONGLET_SHOOT, definition().with(
				sound("undergarden:item/gronglet_shoot_1"),
				sound("undergarden:item/gronglet_shoot_2")
		).subtitle("subtitles.item.slingshot.gronglet_shoot"));

		this.add(UGSoundEvents.PICK_BLISTERBERRY_BUSH, definition().with(
				sound("minecraft:item/sweet_berries/pick_from_bush1"),
				sound("minecraft:item/sweet_berries/pick_from_bush2")
		).subtitle("subtitles.item.blisterberry_bush.pick"));

		this.add(UGSoundEvents.BUCKET_FILL_VIRULENT, definition().with(
				sound("undergarden:item/bucket/fill_virulent_mix_1"),
				sound("undergarden:item/bucket/fill_virulent_mix_2"),
				sound("undergarden:item/bucket/fill_virulent_mix_3")
		).subtitle("subtitles.item.bucket.fill"));
		this.add(UGSoundEvents.BUCKET_EMPTY_VIRULENT, definition().with(
				sound("undergarden:item/bucket/empty_virulent_mix_1"),
				sound("undergarden:item/bucket/empty_virulent_mix_2"),
				sound("undergarden:item/bucket/empty_virulent_mix_3")
		).subtitle("subtitles.item.bucket.empty"));

		this.add(UGSoundEvents.DITCHBULB_PASTE_USE, definition().with(
				sound("minecraft:item/ink_sac/ink_sac1"),
				sound("minecraft:item/ink_sac/ink_sac2"),
				sound("minecraft:item/ink_sac/ink_sac3")
		).subtitle("subtitles.item.ditchbulb_paste.use"));

		this.add(UGSoundEvents.BOOMGOURD_PRIMED, definition().with(
				sound("undergarden:entity/boomgourd_primed")
		).subtitle("subtitles.entity.boomgourd.primed"));

		this.add(UGSoundEvents.DWELLER_AMBIENT, definition().with(
				sound("undergarden:entity/dweller_ambient_1"),
				sound("undergarden:entity/dweller_ambient_2"),
				sound("undergarden:entity/dweller_ambient_3")
		).subtitle("subtitles.entity.dweller.ambient"));
		this.add(UGSoundEvents.DWELLER_HURT, definition().with(
				sound("undergarden:entity/dweller_hurt_1"),
				sound("undergarden:entity/dweller_hurt_2"),
				sound("undergarden:entity/dweller_hurt_3")
		).subtitle("subtitles.entity.dweller.hurt"));
		this.add(UGSoundEvents.DWELLER_DEATH, definition().with(
				sound("undergarden:entity/dweller_death")
		).subtitle("subtitles.entity.dweller.death"));
		this.add(UGSoundEvents.DWELLER_JUMP, definition().with(
				sound("undergarden:entity/dweller_jump")
		).subtitle("subtitles.entity.dweller.jump"));
		this.add(UGSoundEvents.DWELLER_STEP, definition().with(
				sound("minecraft:mob/cow/step1").pitch(0.5F),
				sound("minecraft:mob/cow/step2").pitch(0.5F),
				sound("minecraft:mob/cow/step3").pitch(0.5F),
				sound("minecraft:mob/cow/step4").pitch(0.5F)
		).subtitle("subtitles.block.generic.footsteps"));
		this.add(UGSoundEvents.DWELLER_SADDLE_REMOVE, definition().with(
				sound("minecraft:item/bundle/drop_contents1").pitch(0.95F),
				sound("minecraft:item/bundle/drop_contents2").pitch(0.95F),
				sound("minecraft:item/bundle/drop_contents3").pitch(0.95F)
		).subtitle("subtitles.entity.dweller.remove_saddle"));

		this.add(UGSoundEvents.GREATER_DWELLER_AMBIENT, definition().with(
			sound("undergarden:entity/dweller_ambient_1").pitch(0.5F),
			sound("undergarden:entity/dweller_ambient_2").pitch(0.5F),
			sound("undergarden:entity/dweller_ambient_3").pitch(0.5F)
		).subtitle("subtitles.entity.greater_dweller.ambient"));
		this.add(UGSoundEvents.GREATER_DWELLER_HURT, definition().with(
			sound("undergarden:entity/dweller_hurt_1").pitch(0.5F),
			sound("undergarden:entity/dweller_hurt_2").pitch(0.5F),
			sound("undergarden:entity/dweller_hurt_3").pitch(0.5F)
		).subtitle("subtitles.entity.greater_dweller.hurt"));
		this.add(UGSoundEvents.GREATER_DWELLER_DEATH, definition().with(
			sound("undergarden:entity/dweller_death").pitch(0.5F)
		).subtitle("subtitles.entity.greater_dweller.death"));

		this.add(UGSoundEvents.ROTLING_AMBIENT, definition().with(
				sound("undergarden:entity/rotling_ambient_1"),
				sound("undergarden:entity/rotling_ambient_2"),
				sound("undergarden:entity/rotling_ambient_3")
		).subtitle("subtitles.entity.rotling.ambient"));
		this.add(UGSoundEvents.ROTLING_HURT, definition().with(
				sound("undergarden:entity/rotling_hurt_1"),
				sound("undergarden:entity/rotling_hurt_2")
		).subtitle("subtitles.entity.rotling.hurt"));
		this.add(UGSoundEvents.ROTLING_DEATH, definition().with(
				sound("undergarden:entity/rotling_death")
		).subtitle("subtitles.entity.rotling.death"));
		this.add(UGSoundEvents.ROTLING_STEP, definition().with(
				sound("minecraft:mob/zombie/step1").pitch(1.5F),
				sound("minecraft:mob/zombie/step2").pitch(1.5F),
				sound("minecraft:mob/zombie/step3").pitch(1.5F),
				sound("minecraft:mob/zombie/step4").pitch(1.5F),
				sound("minecraft:mob/zombie/step5").pitch(1.5F)
		).subtitle("subtitles.block.generic.footsteps"));

		this.add(UGSoundEvents.ROTWALKER_AMBIENT, definition().with(
				sound("undergarden:entity/rotwalker_ambient_1"),
				sound("undergarden:entity/rotwalker_ambient_2"),
				sound("undergarden:entity/rotwalker_ambient_3")
		).subtitle("subtitles.entity.rotwalker.ambient"));
		this.add(UGSoundEvents.ROTWALKER_HURT, definition().with(
				sound("undergarden:entity/rotwalker_hurt_1"),
				sound("undergarden:entity/rotwalker_hurt_2"),
				sound("undergarden:entity/rotwalker_hurt_3")
		).subtitle("subtitles.entity.rotwalker.hurt"));
		this.add(UGSoundEvents.ROTWALKER_DEATH, definition().with(
				sound("undergarden:entity/rotwalker_death")
		).subtitle("subtitles.entity.rotwalker.death"));
		this.add(UGSoundEvents.ROTWALKER_STEP, definition().with(
				sound("minecraft:mob/zombie/step1").pitch(0.5F),
				sound("minecraft:mob/zombie/step2").pitch(0.5F),
				sound("minecraft:mob/zombie/step3").pitch(0.5F),
				sound("minecraft:mob/zombie/step4").pitch(0.5F),
				sound("minecraft:mob/zombie/step5").pitch(0.5F)
		).subtitle("subtitles.block.generic.footsteps"));

		this.add(UGSoundEvents.ROTBEAST_AMBIENT, definition().with(
				sound("undergarden:entity/rotbeast_ambient_1"),
				sound("undergarden:entity/rotbeast_ambient_2"),
				sound("undergarden:entity/rotbeast_ambient_3")
		).subtitle("subtitles.entity.rotbeast.ambient"));
		this.add(UGSoundEvents.ROTBEAST_HURT, definition().with(
				sound("undergarden:entity/rotbeast_hurt_1"),
				sound("undergarden:entity/rotbeast_hurt_2"),
				sound("undergarden:entity/rotbeast_hurt_3"),
				sound("undergarden:entity/rotbeast_hurt_4")
		).subtitle("subtitles.entity.rotbeast.hurt"));
		this.add(UGSoundEvents.ROTBEAST_DEATH, definition().with(
				sound("undergarden:entity/rotbeast_death")
		).subtitle("subtitles.entity.rotbeast.death"));
		this.add(UGSoundEvents.ROTBEAST_STEP, definition().with(
				sound("undergarden:entity/rotbeast_step_1"),
				sound("undergarden:entity/rotbeast_step_2"),
				sound("undergarden:entity/rotbeast_step_3")
		).subtitle("subtitles.block.generic.footsteps"));
		this.add(UGSoundEvents.ROTBEAST_ATTACK, definition().with(
				sound("undergarden:entity/rotbeast_attack_1"),
				sound("undergarden:entity/rotbeast_attack_2")
		).subtitle("subtitles.entity.rotbeast.attack"));

		this.add(UGSoundEvents.BRUTE_AMBIENT, definition().with(
				sound("undergarden:entity/brute_ambient_1"),
				sound("undergarden:entity/brute_ambient_2")
		).subtitle("subtitles.entity.brute.ambient"));
		this.add(UGSoundEvents.BRUTE_HURT, definition().with(
				sound("undergarden:entity/brute_hurt_1"),
				sound("undergarden:entity/brute_hurt_2"),
				sound("undergarden:entity/brute_hurt_3"),
				sound("undergarden:entity/brute_hurt_4")
		).subtitle("subtitles.entity.brute.hurt"));
		this.add(UGSoundEvents.BRUTE_DEATH, definition().with(
				sound("undergarden:entity/brute_hurt_1"),
				sound("undergarden:entity/brute_hurt_2"),
				sound("undergarden:entity/brute_hurt_3"),
				sound("undergarden:entity/brute_hurt_4")
		).subtitle("subtitles.entity.brute.death"));

		this.add(UGSoundEvents.GLOOMPER_AMBIENT, definition().with(
				sound("undergarden:entity/gloomper_ambient_1"),
				sound("undergarden:entity/gloomper_ambient_2")
		).subtitle("subtitles.entity.gloomper.ambient"));
		this.add(UGSoundEvents.GLOOMPER_HURT, definition().with(
				sound("undergarden:entity/gloomper_hurt_1"),
				sound("undergarden:entity/gloomper_hurt_2")
		).subtitle("subtitles.entity.gloomper.hurt"));
		this.add(UGSoundEvents.GLOOMPER_DEATH, definition().with(
				sound("undergarden:entity/gloomper_death")
		).subtitle("subtitles.entity.gloomper.death"));
		this.add(UGSoundEvents.GLOOMPER_HOP, definition().with(
				sound("minecraft:entity/fish/flop1").volume(0.3F),
				sound("minecraft:entity/fish/flop2").volume(0.3F),
				sound("minecraft:entity/fish/flop3").volume(0.3F),
				sound("minecraft:entity/fish/flop4").volume(0.3F)
		).subtitle("subtitles.entity.gloomper.hop"));
		this.add(UGSoundEvents.GLOOMPER_FART, definition().with(
				sound("minecraft:entity/pufferfish/blow_up1").volume(0.45F),
				sound("minecraft:entity/pufferfish/blow_up2").volume(0.45F)
		).subtitle("subtitles.entity.gloomper.fart"));

		this.add(UGSoundEvents.STONEBORN_STEP, definition().with(
				sound("undergarden:entity/stoneborn_step_1"),
				sound("undergarden:entity/stoneborn_step_2")
		).subtitle("subtitles.block.generic.footsteps"));
		this.add(UGSoundEvents.STONEBORN_SPEAKING, definition().with(
				sound("undergarden:entity/stoneborn_speaking_1"),
				sound("undergarden:entity/stoneborn_speaking_2"),
				sound("undergarden:entity/stoneborn_speaking_3")
		).subtitle("subtitles.entity.stoneborn.speaking"));
		this.add(UGSoundEvents.STONEBORN_PLEASED, definition().with(
				sound("undergarden:entity/stoneborn_pleased_1"),
				sound("undergarden:entity/stoneborn_pleased_2"),
				sound("undergarden:entity/stoneborn_pleased_3")
		).subtitle("subtitles.entity.stoneborn.pleased"));
		this.add(UGSoundEvents.STONEBORN_HURT, definition().with(
				sound("undergarden:entity/stoneborn_hurt_1"),
				sound("undergarden:entity/stoneborn_hurt_2")
		).subtitle("subtitles.entity.stoneborn.hurt"));
		this.add(UGSoundEvents.STONEBORN_ANGRY, definition().with(
				sound("undergarden:entity/stoneborn_angry_1"),
				sound("undergarden:entity/stoneborn_angry_2")
		).subtitle("subtitles.entity.stoneborn.angry"));
		this.add(UGSoundEvents.STONEBORN_CONFUSED, definition().with(
				sound("undergarden:entity/stoneborn_confused_1"),
				sound("undergarden:entity/stoneborn_confused_2"),
				sound("undergarden:entity/stoneborn_confused_3")
		).subtitle("subtitles.entity.stoneborn.confused"));
		this.add(UGSoundEvents.STONEBORN_CHANT, definition().with(
				sound("undergarden:entity/stoneborn_chant_1"),
				sound("undergarden:entity/stoneborn_chant_2")
		).subtitle("subtitles.entity.stoneborn.chant"));
		this.add(UGSoundEvents.STONEBORN_DEATH, definition().with(
				sound("undergarden:entity/stoneborn_death")
		).subtitle("subtitles.entity.stoneborn.death"));

		this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_AMBIENT, definition().with(
				sound("undergarden:entity/fguardian_ambient_1"),
				sound("undergarden:entity/fguardian_ambient_2"),
				sound("undergarden:entity/fguardian_ambient_3")
		).subtitle("subtitles.entity.forgotten_guardian.ambient"));
		this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_HURT, definition().with(
				sound("undergarden:entity/fguardian_hurt_1"),
				sound("undergarden:entity/fguardian_hurt_2"),
				sound("undergarden:entity/fguardian_hurt_3")
		).subtitle("subtitles.entity.forgotten_guardian.hurt"));
		this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_DEATH, definition().with(
				sound("undergarden:entity/fguardian_death")
		).subtitle("subtitles.entity.forgotten_guardian.death"));
		this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_ATTACK, definition().with(
				sound("undergarden:entity/fguardian_attack_1"),
				sound("undergarden:entity/fguardian_attack_2")
		).subtitle("subtitles.entity.forgotten_guardian.attack"));
		this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_DEFLECT, definition().with(
				sound("undergarden:entity/fguardian_deflect")
		).subtitle("subtitles.entity.forgotten_guardian.deflect"));
		this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_STEP, definition().with(
				sound("undergarden:entity/fguardian_step_1"),
				sound("undergarden:entity/fguardian_step_2"),
				sound("undergarden:entity/fguardian_step_3")
		).subtitle("subtitles.block.generic.footsteps"));

		this.add(UGSoundEvents.MINION_SHOOT, definition().with(
				sound("undergarden:entity/minion_shoot")
		).subtitle("subtitles.entity.minion.shoot"));
		this.add(UGSoundEvents.MINION_DEATH, definition().with(
				sound("undergarden:entity/minion_death")
		).subtitle("subtitles.entity.minion.death"));
		this.add(UGSoundEvents.MINION_REPAIR, definition().with(
				sound("minecraft:mob/irongolem/repair")
		).subtitle("subtitles.entity.minion.repair"));

		this.add(UGSoundEvents.NARGOYLE_HURT, definition().with(
				sound("undergarden:entity/nargoyle_hurt_1"),
				sound("undergarden:entity/nargoyle_hurt_2"),
				sound("undergarden:entity/nargoyle_hurt_3")
		).subtitle("subtitles.entity.nargoyle.hurt"));
		this.add(UGSoundEvents.NARGOYLE_DEATH, definition().with(
				sound("undergarden:entity/nargoyle_death")
		).subtitle("subtitles.entity.nargoyle.death"));
		this.add(UGSoundEvents.NARGOYLE_ATTACK, definition().with(
				sound("undergarden:entity/nargoyle_attack_1"),
				sound("undergarden:entity/nargoyle_attack_2"),
				sound("undergarden:entity/nargoyle_attack_3")
		).subtitle("subtitles.entity.nargoyle.attack"));

		this.add(UGSoundEvents.MUNCHER_AMBIENT, definition().with(
				sound("undergarden:entity/muncher_ambient_1"),
				sound("undergarden:entity/muncher_ambient_2"),
				sound("undergarden:entity/muncher_ambient_3"),
				sound("undergarden:entity/muncher_ambient_4"),
				sound("undergarden:entity/muncher_ambient_5"),
				sound("undergarden:entity/muncher_ambient_6")
		).subtitle("subtitles.entity.muncher.ambient"));
		this.add(UGSoundEvents.MUNCHER_HURT, definition().with(
				sound("undergarden:entity/muncher_hurt_1"),
				sound("undergarden:entity/muncher_hurt_2")
		).subtitle("subtitles.entity.muncher.hurt"));
		this.add(UGSoundEvents.MUNCHER_DEATH, definition().with(
				sound("undergarden:entity/muncher_death")
		).subtitle("subtitles.entity.muncher.death"));
		this.add(UGSoundEvents.MUNCHER_CHEW, definition().with(
				sound("undergarden:entity/muncher_chew_1"),
				sound("undergarden:entity/muncher_chew_2")
		).subtitle("subtitles.entity.muncher.chew"));

		this.add(UGSoundEvents.SPLOOGIE_AMBIENT, definition().with(
				sound("undergarden:entity/sploogie_ambient_1"),
				sound("undergarden:entity/sploogie_ambient_2"),
				sound("undergarden:entity/sploogie_ambient_3")
		).subtitle("subtitles.entity.sploogie.ambient"));
		this.add(UGSoundEvents.SPLOOGIE_HURT, definition().with(
				sound("undergarden:entity/sploogie_hurt_1"),
				sound("undergarden:entity/sploogie_hurt_2")
		).subtitle("subtitles.entity.sploogie.hurt"));
		this.add(UGSoundEvents.SPLOOGIE_DEATH, definition().with(
				sound("undergarden:entity/sploogie_death")
		).subtitle("subtitles.entity.sploogie.death"));
		this.add(UGSoundEvents.SPLOOGIE_SPIT, definition().with(
				sound("undergarden:entity/sploogie_spit_1"),
				sound("undergarden:entity/sploogie_spit_2"),
				sound("undergarden:entity/sploogie_spit_3")
		).subtitle("subtitles.entity.sploogie.spit"));

		this.add(UGSoundEvents.GWIB_HURT, definition().with(
				sound("minecraft:entity/fish/hurt1"),
				sound("minecraft:entity/fish/hurt2"),
				sound("minecraft:entity/fish/hurt3"),
				sound("minecraft:entity/fish/hurt4")
		).subtitle("subtitles.entity.gwib.hurt"));
		this.add(UGSoundEvents.GWIB_DEATH, definition().with(
				sound("minecraft:entity/fish/hurt1"),
				sound("minecraft:entity/fish/hurt2"),
				sound("minecraft:entity/fish/hurt3"),
				sound("minecraft:entity/fish/hurt4")
		).subtitle("subtitles.entity.gwib.death"));
		this.add(UGSoundEvents.GWIB_FLOP, definition().with(
				sound("minecraft:entity/fish/flop1").volume(0.5F),
				sound("minecraft:entity/fish/flop2").volume(0.5F),
				sound("minecraft:entity/fish/flop3").volume(0.5F),
				sound("minecraft:entity/fish/flop4").volume(0.5F)
		).subtitle("subtitles.entity.gwib.flop"));

		this.add(UGSoundEvents.GWIBLING_HURT, definition().with(
				sound("minecraft:entity/fish/hurt1"),
				sound("minecraft:entity/fish/hurt2"),
				sound("minecraft:entity/fish/hurt3"),
				sound("minecraft:entity/fish/hurt4")
		).subtitle("subtitles.entity.gwibling.hurt"));
		this.add(UGSoundEvents.GWIBLING_DEATH, definition().with(
				sound("minecraft:entity/fish/hurt1"),
				sound("minecraft:entity/fish/hurt2"),
				sound("minecraft:entity/fish/hurt3"),
				sound("minecraft:entity/fish/hurt4")
		).subtitle("subtitles.entity.gwibling.death"));
		this.add(UGSoundEvents.GWIBLING_FLOP, definition().with(
				sound("minecraft:entity/fish/flop1").volume(0.3F),
				sound("minecraft:entity/fish/flop2").volume(0.3F),
				sound("minecraft:entity/fish/flop3").volume(0.3F),
				sound("minecraft:entity/fish/flop4").volume(0.3F)
		).subtitle("subtitles.entity.gwibling.flop"));

		this.add(UGSoundEvents.MOG_AMBIENT, definition().with(
				sound("undergarden:entity/mog_ambient_1"),
				sound("undergarden:entity/mog_ambient_2"),
				sound("undergarden:entity/mog_ambient_3")
		).subtitle("subtitles.entity.mog.ambient"));
		this.add(UGSoundEvents.MOG_HURT, definition().with(
				sound("undergarden:entity/mog_hurt_1"),
				sound("undergarden:entity/mog_hurt_2"),
				sound("undergarden:entity/mog_hurt_3")
		).subtitle("subtitles.entity.mog.hurt"));
		this.add(UGSoundEvents.MOG_DEATH, definition().with(
				sound("undergarden:entity/mog_death")
		).subtitle("subtitles.entity.mog.death"));

		this.add(UGSoundEvents.SMOG_MOG_AMBIENT, definition().with(
				sound("undergarden:entity/mog_ambient_1").pitch(0.5F),
				sound("undergarden:entity/mog_ambient_2").pitch(0.5F),
				sound("undergarden:entity/mog_ambient_3").pitch(0.5F)
		).subtitle("subtitles.entity.smog_mog.ambient"));
		this.add(UGSoundEvents.SMOG_MOG_HURT, definition().with(
				sound("undergarden:entity/mog_hurt_1").pitch(0.5F),
				sound("undergarden:entity/mog_hurt_2").pitch(0.5F),
				sound("undergarden:entity/mog_hurt_3").pitch(0.5F)
		).subtitle("subtitles.entity.smog_mog.hurt"));
		this.add(UGSoundEvents.SMOG_MOG_DEATH, definition().with(
				sound("undergarden:entity/mog_death").pitch(0.5F)
		).subtitle("subtitles.entity.smog_mog.death"));

		this.add(UGSoundEvents.SCINTLING_HURT, definition().with(
				sound("minecraft:block/honeyblock/break1"),
				sound("minecraft:block/honeyblock/break2"),
				sound("minecraft:block/honeyblock/break3"),
				sound("minecraft:block/honeyblock/break4"),
				sound("minecraft:block/honeyblock/break5")
		).subtitle("subtitles.entity.scintling.hurt"));
		this.add(UGSoundEvents.SCINTLING_DEATH, definition().with(
				sound("minecraft:block/honeyblock/break1"),
				sound("minecraft:block/honeyblock/break2"),
				sound("minecraft:block/honeyblock/break3"),
				sound("minecraft:block/honeyblock/break4"),
				sound("minecraft:block/honeyblock/break5")
		).subtitle("subtitles.entity.scintling.death"));
		this.add(UGSoundEvents.SCINTLING_STEP, definition().with(
				sound("minecraft:block/honeyblock/slide1"),
				sound("minecraft:block/honeyblock/slide2"),
				sound("minecraft:block/honeyblock/slide3"),
				sound("minecraft:block/honeyblock/slide4")
		));

		this.add(UGSoundEvents.FORGOTTEN_AMBIENT, definition().with(
				sound("undergarden:entity/forgotten_ambient_1"),
				sound("undergarden:entity/forgotten_ambient_2"),
				sound("undergarden:entity/forgotten_ambient_3")
		).subtitle("subtitles.entity.forgotten.ambient"));
		this.add(UGSoundEvents.FORGOTTEN_HURT, definition().with(
				sound("undergarden:entity/forgotten_hurt_1"),
				sound("undergarden:entity/forgotten_hurt_2"),
				sound("undergarden:entity/forgotten_hurt_3"),
				sound("undergarden:entity/forgotten_hurt_4")
		).subtitle("subtitles.entity.forgotten.hurt"));
		this.add(UGSoundEvents.FORGOTTEN_DEATH, definition().with(
				sound("undergarden:entity/forgotten_death")
		).subtitle("subtitles.entity.forgotten.death"));
		this.add(UGSoundEvents.FORGOTTEN_STEP, definition().with(
				sound("mob/wither_skeleton/step1"),
				sound("mob/wither_skeleton/step2"),
				sound("mob/wither_skeleton/step3"),
				sound("mob/wither_skeleton/step4")
		).subtitle("subtitles.block.generic.footsteps"));
	}
}