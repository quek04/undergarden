package quek.undergarden.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;

public class UndergardenLootTables extends LootTableProvider {

    public UndergardenLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public String getName() {
        return "Undergarden LootTables";
    }


}

