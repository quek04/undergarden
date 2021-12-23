package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import quek.undergarden.registry.UGSoundEvents;

public class UGSoundDefinitions extends SoundDefinitionsProvider {

    public UGSoundDefinitions(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, "undergarden", helper);
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

        this.add(UGSoundEvents.ABYSS_AMBIENT_ADDITION, definition().with(
                sound("undergarden:ambient/additions/abyss_1"),
                sound("undergarden:ambient/additions/abyss_2"),
                sound("undergarden:ambient/additions/abyss_3"),
                sound("undergarden:ambient/additions/abyss_4"),
                sound("undergarden:ambient/additions/abyss_5"),
                sound("undergarden:ambient/additions/abyss_6")
        ));
        this.add(UGSoundEvents.BOG_AMBIENT_ADDITION, definition().with(
                sound("undergarden:ambient/additions/bog_1"),
                sound("undergarden:ambient/additions/bog_2"),
                sound("undergarden:ambient/additions/bog_3"),
                sound("undergarden:ambient/additions/bog_4")
        ));
        this.add(UGSoundEvents.FIELDS_AMBIENT_ADDITION, definition().with(
                sound("undergarden:ambient/additions/fields_1"),
                sound("undergarden:ambient/additions/fields_2"),
                sound("undergarden:ambient/additions/fields_3"),
                sound("undergarden:ambient/additions/fields_4"),
                sound("undergarden:ambient/additions/fields_5"),
                sound("undergarden:ambient/additions/fields_6")
        ));
        this.add(UGSoundEvents.FOREST_AMBIENT_ADDITION, definition().with(
                sound("undergarden:ambient/additions/forest_1"),
                sound("undergarden:ambient/additions/forest_2"),
                sound("undergarden:ambient/additions/forest_3"),
                sound("undergarden:ambient/additions/forest_4"),
                sound("undergarden:ambient/additions/forest_5")
        ));
        this.add(UGSoundEvents.GRONGLE_AMBIENT_ADDITION, definition().with(
                sound("undergarden:ambient/additions/grongle_1"),
                sound("undergarden:ambient/additions/grongle_2"),
                sound("undergarden:ambient/additions/grongle_3"),
                sound("undergarden:ambient/additions/grongle_4"),
                sound("undergarden:ambient/additions/grongle_5")
        ));
        this.add(UGSoundEvents.FROSTFIELDS_AMBIENT_ADDITION, definition().with(
                sound("undergarden:ambient/additions/frostfields_1"),
                sound("undergarden:ambient/additions/frostfields_2"),
                sound("undergarden:ambient/additions/frostfields_3"),
                sound("undergarden:ambient/additions/frostfields_4"),
                sound("undergarden:ambient/additions/frostfields_5"),
                sound("undergarden:ambient/additions/frostfields_6")
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

        this.add(UGSoundEvents.ABYSS_MOOD, definition().with(
                sound("undergarden:ambient/moods/abyss_mood_1"),
                sound("undergarden:ambient/moods/abyss_mood_2")
        ));

        this.add(UGSoundEvents.UNDERGARDEN_MUSIC, definition().with(
                sound("undergarden:music/acasta_gneiss").stream(),
                sound("undergarden:music/all_that_wiggles_is_wood").stream(),
                sound("undergarden:music/brotherhood").stream(),
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
        ).subtitle("subtitles.block.undergarden_portal_ambient"));
        this.add(UGSoundEvents.UNDERGARDEN_PORTAL_ACTIVATE, definition().with(
                sound("undergarden:block/undergarden_portal_activate")
        ).subtitle("subtitles.block.undergarden_portal_activate"));
        this.add(UGSoundEvents.UNDERGARDEN_PORTAL_TRAVEL, definition().with(
                sound("undergarden:block/undergarden_portal_travel")
        ).subtitle("subtitles.block.undergarden_portal_travel"));

        this.add(UGSoundEvents.BLISTERBOMB_THROW, definition().with(
                sound("undergarden:item/blisterbomb")
        ).subtitle("subtitles.item.blisterbomb"));

        this.add(UGSoundEvents.SLINGSHOT_SHOOT, definition().with(
                sound("undergarden:item/slingshot_shoot")
        ).subtitle("subtitles.item.slingshot_shoot"));
        this.add(UGSoundEvents.SLINGSHOT_DRAW, definition().with(
                sound("undergarden:item/slingshot_draw_1"),
                sound("undergarden:item/slingshot_draw_2")
        ).subtitle("subtitles.item.slingshot_shoot"));

        this.add(UGSoundEvents.PICK_BLISTERBERRY_BUSH, definition().with(
                sound("minecraft:item/sweet_berries/pick_from_bush1"),
                sound("minecraft:item/sweet_berries/pick_from_bush2")
        ).subtitle("subtitles.item.pick_blisterberry_bush"));

        this.add(UGSoundEvents.DWELLER_AMBIENT, definition().with(
                sound("undergarden:entity/dweller_ambient_1"),
                sound("undergarden:entity/dweller_ambient_2"),
                sound("undergarden:entity/dweller_ambient_3")
        ).subtitle("subtitles.entity.dweller_ambient"));
        this.add(UGSoundEvents.DWELLER_HURT, definition().with(
                sound("undergarden:entity/dweller_hurt_1"),
                sound("undergarden:entity/dweller_hurt_2"),
                sound("undergarden:entity/dweller_hurt_3")
        ).subtitle("subtitles.entity.dweller_hurt"));
        this.add(UGSoundEvents.DWELLER_DEATH, definition().with(
                sound("undergarden:entity/dweller_death")
        ).subtitle("subtitles.entity.dweller_death"));
        this.add(UGSoundEvents.DWELLER_STEP, definition().with(
                sound("minecraft:mob/cow/step1"),
                sound("minecraft:mob/cow/step2"),
                sound("minecraft:mob/cow/step3"),
                sound("minecraft:mob/cow/step4")
        ).subtitle("subtitles.block.generic.footsteps"));

        this.add(UGSoundEvents.ROTLING_AMBIENT, definition().with(
                sound("undergarden:entity/rotling_ambient_1"),
                sound("undergarden:entity/rotling_ambient_2"),
                sound("undergarden:entity/rotling_ambient_3")
        ).subtitle("subtitles.entity.rotling_ambient"));
        this.add(UGSoundEvents.ROTLING_HURT, definition().with(
                sound("undergarden:entity/rotling_hurt_1"),
                sound("undergarden:entity/rotling_hurt_2")
        ).subtitle("subtitles.entity.rotling_hurt"));
        this.add(UGSoundEvents.ROTLING_DEATH, definition().with(
                sound("undergarden:entity/rotling_death")
        ).subtitle("subtitles.entity.rotling_death"));
        this.add(UGSoundEvents.ROTLING_STEP, definition().with(
                sound("minecraft:mob/zombie/step1"),
                sound("minecraft:mob/zombie/step2"),
                sound("minecraft:mob/zombie/step3"),
                sound("minecraft:mob/zombie/step4"),
                sound("minecraft:mob/zombie/step5")
        ).subtitle("subtitles.block.generic.footsteps"));

        this.add(UGSoundEvents.ROTWALKER_AMBIENT, definition().with(
                sound("undergarden:entity/rotwalker_ambient_1"),
                sound("undergarden:entity/rotwalker_ambient_2"),
                sound("undergarden:entity/rotwalker_ambient_3")
        ).subtitle("subtitles.entity.rotwalker_ambient"));
        this.add(UGSoundEvents.ROTWALKER_HURT, definition().with(
                sound("undergarden:entity/rotwalker_hurt_1"),
                sound("undergarden:entity/rotwalker_hurt_2"),
                sound("undergarden:entity/rotwalker_hurt_3")
        ).subtitle("subtitles.entity.rotwalker_hurt"));
        this.add(UGSoundEvents.ROTWALKER_DEATH, definition().with(
                sound("undergarden:entity/rotwalker_death")
        ).subtitle("subtitles.entity.rotwalker_death"));
        this.add(UGSoundEvents.ROTWALKER_STEP, definition().with(
                sound("minecraft:mob/zombie/step1"),
                sound("minecraft:mob/zombie/step2"),
                sound("minecraft:mob/zombie/step3"),
                sound("minecraft:mob/zombie/step4"),
                sound("minecraft:mob/zombie/step5")
        ).subtitle("subtitles.block.generic.footsteps"));

        this.add(UGSoundEvents.ROTBEAST_AMBIENT, definition().with(
                sound("undergarden:entity/rotbeast_ambient_1"),
                sound("undergarden:entity/rotbeast_ambient_2"),
                sound("undergarden:entity/rotbeast_ambient_3")
        ).subtitle("subtitles.entity.rotbeast_ambient"));
        this.add(UGSoundEvents.ROTBEAST_HURT, definition().with(
                sound("undergarden:entity/rotbeast_hurt_1"),
                sound("undergarden:entity/rotbeast_hurt_2"),
                sound("undergarden:entity/rotbeast_hurt_3")
        ).subtitle("subtitles.entity.rotbeast_hurt"));
        this.add(UGSoundEvents.ROTBEAST_DEATH, definition().with(
                sound("undergarden:entity/rotbeast_death")
        ).subtitle("subtitles.entity.rotbeast_death"));
        this.add(UGSoundEvents.ROTBEAST_STEP, definition().with(
                sound("minecraft:mob/zombie/step1"),
                sound("minecraft:mob/zombie/step2"),
                sound("minecraft:mob/zombie/step3"),
                sound("minecraft:mob/zombie/step4"),
                sound("minecraft:mob/zombie/step5")
        ).subtitle("subtitles.block.generic.footsteps"));
        this.add(UGSoundEvents.ROTBEAST_ATTACK, definition().with(
                sound("minecraft:mob/irongolem/throw")
        ).subtitle("subtitles.entity.rotbeast_attack"));

        this.add(UGSoundEvents.BRUTE_AMBIENT, definition().with(
                sound("undergarden:entity/brute_ambient_1"),
                sound("undergarden:entity/brute_ambient_2")
        ).subtitle("subtitles.entity.brute_ambient"));
        this.add(UGSoundEvents.BRUTE_HURT, definition().with(
                sound("undergarden:entity/brute_hurt_1"),
                sound("undergarden:entity/brute_hurt_2"),
                sound("undergarden:entity/brute_hurt_3"),
                sound("undergarden:entity/brute_hurt_4")
        ).subtitle("subtitles.entity.brute_hurt"));
        this.add(UGSoundEvents.BRUTE_DEATH, definition().with(
                sound("undergarden:entity/brute_hurt_1"),
                sound("undergarden:entity/brute_hurt_2"),
                sound("undergarden:entity/brute_hurt_3"),
                sound("undergarden:entity/brute_hurt_4")
        ).subtitle("subtitles.entity.brute_death"));

        this.add(UGSoundEvents.GLOOMPER_AMBIENT, definition().with(
                sound("undergarden:entity/gloomper_ambient_1"),
                sound("undergarden:entity/gloomper_ambient_2")
        ).subtitle("subtitles.entity.gloomper_ambient"));
        this.add(UGSoundEvents.GLOOMPER_HURT, definition().with(
                sound("undergarden:entity/gloomper_hurt_1"),
                sound("undergarden:entity/gloomper_hurt_2")
        ).subtitle("subtitles.entity.gloomper_hurt"));
        this.add(UGSoundEvents.GLOOMPER_DEATH, definition().with(
                sound("undergarden:entity/gloomper_death")
        ).subtitle("subtitles.entity.gloomper_death"));
        this.add(UGSoundEvents.GLOOMPER_HOP, definition().with(
                sound("minecraft:entity/fish/flop1").volume(0.3F),
                sound("minecraft:entity/fish/flop2").volume(0.3F),
                sound("minecraft:entity/fish/flop3").volume(0.3F),
                sound("minecraft:entity/fish/flop4").volume(0.3F)
        ).subtitle("subtitles.entity.gloomper_hop"));
        this.add(UGSoundEvents.GLOOMPER_FART, definition().with(
                sound("minecraft:entity/pufferfish/blow_up1").volume(0.45F),
                sound("minecraft:entity/pufferfish/blow_up2").volume(0.45F)
        ).subtitle("subtitles.entity.gloomper_fart"));

        this.add(UGSoundEvents.STONEBORN_STEP, definition().with(
                sound("undergarden:entity/stoneborn_step_1"),
                sound("undergarden:entity/stoneborn_step_2")
        ).subtitle("subtitles.block.generic.footsteps"));
        this.add(UGSoundEvents.STONEBORN_SPEAKING, definition().with(
                sound("undergarden:entity/stoneborn_speaking_1"),
                sound("undergarden:entity/stoneborn_speaking_2"),
                sound("undergarden:entity/stoneborn_speaking_3")
        ).subtitle("subtitles.entity.stoneborn_speaking"));
        this.add(UGSoundEvents.STONEBORN_PLEASED, definition().with(
                sound("undergarden:entity/stoneborn_pleased_1"),
                sound("undergarden:entity/stoneborn_pleased_2"),
                sound("undergarden:entity/stoneborn_pleased_3")
        ).subtitle("subtitles.entity.stoneborn_pleased"));
        this.add(UGSoundEvents.STONEBORN_HURT, definition().with(
                sound("undergarden:entity/stoneborn_hurt_1"),
                sound("undergarden:entity/stoneborn_hurt_2")
        ).subtitle("subtitles.entity.stoneborn_hurt"));
        this.add(UGSoundEvents.STONEBORN_ANGRY, definition().with(
                sound("undergarden:entity/stoneborn_angry_1"),
                sound("undergarden:entity/stoneborn_angry_2")
        ).subtitle("subtitles.entity.stoneborn_angry"));
        this.add(UGSoundEvents.STONEBORN_CONFUSED, definition().with(
                sound("undergarden:entity/stoneborn_confused_1"),
                sound("undergarden:entity/stoneborn_confused_2"),
                sound("undergarden:entity/stoneborn_confused_3")
        ).subtitle("subtitles.entity.stoneborn_confused"));
        this.add(UGSoundEvents.STONEBORN_CHANT, definition().with(
                sound("undergarden:entity/stoneborn_chant_1"),
                sound("undergarden:entity/stoneborn_chant_2")
        ).subtitle("subtitles.entity.stoneborn_chant"));
        this.add(UGSoundEvents.STONEBORN_DEATH, definition().with(
                sound("undergarden:entity/stoneborn_death")
        ).subtitle("subtitles.entity.stoneborn_death"));

        this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_AMBIENT, definition().with(
                sound("undergarden:entity/fguardian_ambient_1"),
                sound("undergarden:entity/fguardian_ambient_2"),
                sound("undergarden:entity/fguardian_ambient_3")
        ).subtitle("subtitles.entity.forgotten_guardian_ambient"));
        this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_HURT, definition().with(
                sound("undergarden:entity/fguardian_hurt_1"),
                sound("undergarden:entity/fguardian_hurt_2"),
                sound("undergarden:entity/fguardian_hurt_3")
        ).subtitle("subtitles.entity.forgotten_guardian_hurt"));
        this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_DEATH, definition().with(
                sound("undergarden:entity/fguardian_death")
        ).subtitle("subtitles.entity.forgotten_guardian_death"));
        this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_ATTACK, definition().with(
                sound("undergarden:entity/fguardian_attack_1"),
                sound("undergarden:entity/fguardian_attack_2")
        ).subtitle("subtitles.entity.forgotten_guardian_attack"));
        this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_DEFLECT, definition().with(
                sound("undergarden:entity/fguardian_deflect")
        ).subtitle("subtitles.entity.forgotten_guardian_deflect"));
        this.add(UGSoundEvents.FORGOTTEN_GUARDIAN_STEP, definition().with(
                sound("undergarden:entity/fguardian_step_1"),
                sound("undergarden:entity/fguardian_step_2"),
                sound("undergarden:entity/fguardian_step_3")
        ).subtitle("subtitles.block.generic.footsteps"));

        this.add(UGSoundEvents.MINION_SHOOT, definition().with(
                sound("undergarden:entity/minion_shoot")
        ).subtitle("subtitles.entity.minion_shoot"));
        this.add(UGSoundEvents.MINION_DEATH, definition().with(
                sound("undergarden:entity/minion_death")
        ).subtitle("subtitles.entity.minion_death"));
        this.add(UGSoundEvents.MINION_REPAIR, definition().with(
                sound("minecraft:mob/irongolem/repair")
        ).subtitle("subtitles.entity.minion_repair"));

        this.add(UGSoundEvents.NARGOYLE_HURT, definition().with(
                sound("undergarden:entity/nargoyle_hurt_1"),
                sound("undergarden:entity/nargoyle_hurt_2"),
                sound("undergarden:entity/nargoyle_hurt_3")
        ).subtitle("subtitles.entity.nargoyle_hurt"));
        this.add(UGSoundEvents.NARGOYLE_DEATH, definition().with(
                sound("undergarden:entity/nargoyle_death")
        ).subtitle("subtitles.entity.nargoyle_death"));
        this.add(UGSoundEvents.NARGOYLE_ATTACK, definition().with(
                sound("undergarden:entity/nargoyle_attack_1"),
                sound("undergarden:entity/nargoyle_attack_2"),
                sound("undergarden:entity/nargoyle_attack_3")
        ).subtitle("subtitles.entity.nargoyle_attack"));

        this.add(UGSoundEvents.MUNCHER_AMBIENT, definition().with(
                sound("undergarden:entity/muncher_ambient_1"),
                sound("undergarden:entity/muncher_ambient_2"),
                sound("undergarden:entity/muncher_ambient_3"),
                sound("undergarden:entity/muncher_ambient_4"),
                sound("undergarden:entity/muncher_ambient_5"),
                sound("undergarden:entity/muncher_ambient_6")
        ).subtitle("subtitles.entity.muncher_ambient"));
        this.add(UGSoundEvents.MUNCHER_HURT, definition().with(
                sound("undergarden:entity/muncher_hurt_1"),
                sound("undergarden:entity/muncher_hurt_2")
        ).subtitle("subtitles.entity.muncher_hurt"));
        this.add(UGSoundEvents.MUNCHER_DEATH, definition().with(
                sound("undergarden:entity/muncher_death")
        ).subtitle("subtitles.entity.muncher_death"));
        this.add(UGSoundEvents.MUNCHER_CHEW, definition().with(
                sound("undergarden:entity/muncher_chew_1"),
                sound("undergarden:entity/muncher_chew_2")
        ).subtitle("subtitles.entity.muncher_chew"));

        this.add(UGSoundEvents.SPLOOGIE_AMBIENT, definition().with(
                sound("undergarden:entity/sploogie_ambient_1"),
                sound("undergarden:entity/sploogie_ambient_2"),
                sound("undergarden:entity/sploogie_ambient_3")
        ).subtitle("subtitles.entity.sploogie_ambient"));
        this.add(UGSoundEvents.SPLOOGIE_HURT, definition().with(
                sound("undergarden:entity/sploogie_hurt_1"),
                sound("undergarden:entity/sploogie_hurt_2")
        ).subtitle("subtitles.entity.sploogie_hurt"));
        this.add(UGSoundEvents.SPLOOGIE_DEATH, definition().with(
                sound("undergarden:entity/sploogie_death")
        ).subtitle("subtitles.entity.sploogie_death"));
        this.add(UGSoundEvents.SPLOOGIE_SPIT, definition().with(
                sound("undergarden:entity/sploogie_spit_1"),
                sound("undergarden:entity/sploogie_spit_2"),
                sound("undergarden:entity/sploogie_spit_3")
        ).subtitle("subtitles.entity.sploogie_spit"));

        this.add(UGSoundEvents.MASTICATOR_AMBIENT, definition().with(
                sound("undergarden:entity/masticator_ambient_1"),
                sound("undergarden:entity/masticator_ambient_2"),
                sound("undergarden:entity/masticator_ambient_3"),
                sound("undergarden:entity/masticator_ambient_4")
        ).subtitle("subtitles.entity.masticator_ambient"));
        this.add(UGSoundEvents.MASTICATOR_HURT, definition().with(
                sound("undergarden:entity/masticator_hurt_1"),
                sound("undergarden:entity/masticator_hurt_2"),
                sound("undergarden:entity/masticator_hurt_3")
        ).subtitle("subtitles.entity.masticator_hurt"));
        this.add(UGSoundEvents.MASTICATOR_DEATH, definition().with(
                sound("undergarden:entity/masticator_death")
        ).subtitle("subtitles.entity.masticator_death"));
        this.add(UGSoundEvents.MASTICATOR_EAT, definition().with(
                sound("undergarden:entity/masticator_eat_1"),
                sound("undergarden:entity/masticator_eat_2"),
                sound("undergarden:entity/masticator_eat_3")
        ).subtitle("subtitles.entity.masticator_eat"));
        this.add(UGSoundEvents.MASTICATOR_STEP, definition().with(
                sound("minecraft:mob/ravager/step1"),
                sound("minecraft:mob/ravager/step2"),
                sound("minecraft:mob/ravager/step3"),
                sound("minecraft:mob/ravager/step4"),
                sound("minecraft:mob/ravager/step5")
        ).subtitle("subtitles.block.generic.footsteps"));

        this.add(UGSoundEvents.GWIB_HURT, definition().with(
                sound("minecraft:entity/fish/hurt1"),
                sound("minecraft:entity/fish/hurt2"),
                sound("minecraft:entity/fish/hurt3"),
                sound("minecraft:entity/fish/hurt4")
        ).subtitle("subtitles.entity.gwib_hurt"));
        this.add(UGSoundEvents.GWIB_DEATH, definition().with(
                sound("minecraft:entity/fish/hurt1"),
                sound("minecraft:entity/fish/hurt2"),
                sound("minecraft:entity/fish/hurt3"),
                sound("minecraft:entity/fish/hurt4")
        ).subtitle("subtitles.entity.gwib_death"));
        this.add(UGSoundEvents.GWIB_FLOP, definition().with(
                sound("minecraft:entity/fish/flop1").volume(0.5F),
                sound("minecraft:entity/fish/flop2").volume(0.5F),
                sound("minecraft:entity/fish/flop3").volume(0.5F),
                sound("minecraft:entity/fish/flop4").volume(0.5F)
        ).subtitle("subtitles.entity.gwib_flop"));

        this.add(UGSoundEvents.GWIBLING_HURT, definition().with(
                sound("minecraft:entity/fish/hurt1"),
                sound("minecraft:entity/fish/hurt2"),
                sound("minecraft:entity/fish/hurt3"),
                sound("minecraft:entity/fish/hurt4")
        ).subtitle("subtitles.entity.gwibling_hurt"));
        this.add(UGSoundEvents.GWIBLING_DEATH, definition().with(
                sound("minecraft:entity/fish/hurt1"),
                sound("minecraft:entity/fish/hurt2"),
                sound("minecraft:entity/fish/hurt3"),
                sound("minecraft:entity/fish/hurt4")
        ).subtitle("subtitles.entity.gwibling_death"));
        this.add(UGSoundEvents.GWIBLING_FLOP, definition().with(
                sound("minecraft:entity/fish/flop1").volume(0.3F),
                sound("minecraft:entity/fish/flop2").volume(0.3F),
                sound("minecraft:entity/fish/flop3").volume(0.3F),
                sound("minecraft:entity/fish/flop4").volume(0.3F)
        ).subtitle("subtitles.entity.gwibling_flop"));

        this.add(UGSoundEvents.MOG_AMBIENT, definition().with(
                sound("undergarden:entity/mog_ambient_1"),
                sound("undergarden:entity/mog_ambient_2"),
                sound("undergarden:entity/mog_ambient_3")
        ).subtitle("subtitles.entity.mog_ambient"));
        this.add(UGSoundEvents.MOG_HURT, definition().with(
                sound("undergarden:entity/mog_hurt_1"),
                sound("undergarden:entity/mog_hurt_2"),
                sound("undergarden:entity/mog_hurt_3")
        ).subtitle("subtitles.entity.mog_hurt"));
        this.add(UGSoundEvents.MOG_DEATH, definition().with(
                sound("undergarden:entity/mog_death")
        ).subtitle("subtitles.entity.mog_death"));

        this.add(UGSoundEvents.FORGOTTEN_AMBIENT, definition().with(
                sound("undergarden:entity/forgotten_ambient_1"),
                sound("undergarden:entity/forgotten_ambient_2"),
                sound("undergarden:entity/forgotten_ambient_3")
        ).subtitle("subtitles.entity.forgotten_ambient"));
        this.add(UGSoundEvents.FORGOTTEN_HURT, definition().with(
                sound("undergarden:entity/forgotten_hurt_1"),
                sound("undergarden:entity/forgotten_hurt_2"),
                sound("undergarden:entity/forgotten_hurt_3"),
                sound("undergarden:entity/forgotten_hurt_4")
        ).subtitle("subtitles.entity.forgotten_hurt"));
        this.add(UGSoundEvents.FORGOTTEN_DEATH, definition().with(
                sound("undergarden:entity/forgotten_death")
        ).subtitle("subtitles.entity.forgotten_death"));
        this.add(UGSoundEvents.FORGOTTEN_STEP, definition().with(
                sound("mob/wither_skeleton/step1"),
                sound("mob/wither_skeleton/step2"),
                sound("mob/wither_skeleton/step3"),
                sound("mob/wither_skeleton/step4")
        ).subtitle("subtitles.block.generic.footsteps"));
    }
}