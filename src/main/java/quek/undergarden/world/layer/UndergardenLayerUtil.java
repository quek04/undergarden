package quek.undergarden.world.layer;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLModIdMappingEvent;
import quek.undergarden.utils.LazyInt;
import quek.undergarden.utils.VoroniZoomLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

public class UndergardenLayerUtil
{
	private static final List<LazyInt> CACHES = new ArrayList<>();
	public static LazyInt lazyId(RegistryObject<Biome> biome)
	{
		LazyInt lazyInt = new LazyInt(biome.lazyMap(Registry.BIOME::getId));
		CACHES.add(lazyInt);
		return lazyInt;
	}

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> ImmutableList<IAreaFactory<T>> makeLayers(LongFunction<C> contextFactory)
	{
		IAreaFactory<T> biomes = new UndergardenBiomeLayer().apply(contextFactory.apply(1L));

		biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomes);
		biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
		biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
		biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
		biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);
		biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomes);
		biomes = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);
		IAreaFactory<T> genlayervoronoizoom = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10L), biomes);
		return ImmutableList.of(biomes, genlayervoronoizoom, biomes);
	}

	public static Layer[] makeLayers(long seed)
	{
		ImmutableList<IAreaFactory<LazyArea>> immutablelist = makeLayers((context) -> new LazyAreaLayerContext(25, seed, context));
		Layer layer = new Layer(immutablelist.get(0));
		Layer layer1 = new Layer(immutablelist.get(1));
		Layer layer2 = new Layer(immutablelist.get(2));
		return new Layer[]{layer, layer1, layer2};
	}

	@SubscribeEvent
	public static void onModIdMapped(FMLModIdMappingEvent e)
	{
		CACHES.forEach(LazyInt::invalidate);
	}
}
