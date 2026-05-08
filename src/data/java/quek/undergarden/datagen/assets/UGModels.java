package quek.undergarden.datagen.assets;

import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import quek.undergarden.Undergarden;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGItems;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class UGModels extends ModelProvider {
	private final PackOutput.PathProvider blocks;
	private final PackOutput.PathProvider items;
	private final PackOutput.PathProvider models;

	public UGModels(PackOutput packOutput) {
		super(packOutput, Undergarden.MODID);
		this.blocks = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
		this.items = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
		this.models = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
	}

	@Override
	public CompletableFuture<?> run(CachedOutput output) {
		ItemInfoCollector itemModelOutput = new ItemInfoCollector(this::getKnownItems);
		BlockStateGeneratorCollector blockModelOutput = new BlockStateGeneratorCollector(this::getKnownBlocks);
		SimpleModelCollector modelOutput = new SimpleModelCollector();
		this.registerModels(new UGBlockModels(blockModelOutput, itemModelOutput, modelOutput), new UGItemModels(itemModelOutput, modelOutput));
//		blockModelOutput.validate();
//		itemModelOutput.finalizeAndValidate();
		return CompletableFuture.allOf(blockModelOutput.save(output, this.blocks), modelOutput.save(output, this.models), itemModelOutput.save(output, this.items));
	}

	@Override
	protected Stream<? extends Holder<Block>> getKnownBlocks() {
		return UGBlocks.BLOCKS.getEntries().stream();
	}

	@Override
	protected Stream<? extends Holder<Item>> getKnownItems() {
		return UGItems.ITEMS.getEntries().stream();
	}
}
