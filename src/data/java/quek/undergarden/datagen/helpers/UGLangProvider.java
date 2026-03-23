package quek.undergarden.datagen.helpers;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import net.minecraft.core.Holder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import quek.undergarden.Undergarden;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class UGLangProvider extends LanguageProvider {

	private final PackOutput output;
	public final Map<String, String> upsideDownEntries = new HashMap<>();

	public UGLangProvider(PackOutput output) {
		super(output, Undergarden.MODID, "en_us");
		this.output = output;
	}

	@Override
	public void add(String key, String value) {
		super.add(key, value);
		List<LangFormatSplitter.Component> splitEnglish = LangFormatSplitter.split(value);
		this.upsideDownEntries.put(key, LangConversionHelper.convertComponents(splitEnglish));
	}

	protected void addAdvTitle(String advancementTitle, String name) {
		add("advancement.undergarden." + advancementTitle + ".title", name);
	}

	protected void addAdvDesc(String advancementTitle, String name) {
		add("advancement.undergarden." + advancementTitle + ".desc", name);
	}

	public void addEntityAndEgg(DeferredHolder<EntityType<?>, ? extends EntityType<?>> entity, String name) {
		this.addEntityType(entity, name);
		this.add("item.undergarden." + entity.getId().getPath() + "_spawn_egg", name + " Spawn Egg");
	}

	protected void addSubtitle(String category, String subtitleName, String name) {
		add("subtitles." + category + "." + subtitleName, name);
	}

	public void addBiome(ResourceKey<Biome> biomeKey, String name) {
		add("biome.undergarden." + biomeKey.identifier().getPath(), name);
	}

	protected void addDeath(String deathName, String name) {
		add("death.attack." + deathName, name);
	}

	protected void addPotion(Holder<Potion> potion, String name) {
		String potionKey = potion.getKey().identifier().getPath();
		add("item.minecraft.potion.effect." + potionKey, "Potion of " + name);
		add("item.minecraft.splash_potion.effect." + potionKey, "Splash Potion of " + name);
		add("item.minecraft.lingering_potion.effect." + potionKey, "Lingering Potion of " + name);
		add("item.minecraft.tipped_arrow.effect." + potionKey, "Arrow of " + name);
	}

	protected void addConfig(String configName, String name) {
		add("config.undergarden." + configName, name);
	}

	protected void addEnchantment(ResourceKey<Enchantment> enchantment, String name) {
		add("enchantment.undergarden." + enchantment.identifier().getPath(), name);
	}

	protected void addJukeboxSong(ResourceKey<JukeboxSong> song, String name) {
		add("jukebox_song.undergarden." + song.identifier().getPath(), name);
	}

	protected void addContainer(String containerName, String name) {
		add("container.undergarden." + containerName, name);
	}

	protected void addEmiItemTag(TagKey<Item> tag, String name) {
		add("tag.item.undergarden." + tag.location().getPath().replace('/', '.'), name);
	}

	protected void addEmiCommonItemTag(TagKey<Item> tag, String name) {
		add("tag.item.c." + tag.location().getPath().replace('/', '.'), name);
	}

	protected void addEmiFluidTag(TagKey<Fluid> tag, String name) {
		add("tag.fluid.undergarden." + tag.location().getPath().replace('/', '.'), name);
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cache) {
		//generate normal lang file
		CompletableFuture<?> languageGen = super.run(cache);
		ImmutableList.Builder<CompletableFuture<?>> futuresBuilder = new ImmutableList.Builder<>();
		futuresBuilder.add(languageGen);

		//generate en_ud file
		JsonObject upsideDownFile = new JsonObject();
		this.upsideDownEntries.forEach(upsideDownFile::addProperty);
		futuresBuilder.add(DataProvider.saveStable(cache, upsideDownFile, this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(Undergarden.MODID).resolve("lang").resolve("en_ud.json")));

		return CompletableFuture.allOf(futuresBuilder.build().toArray(CompletableFuture[]::new));
	}
}
