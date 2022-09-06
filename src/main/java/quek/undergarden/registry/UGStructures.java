package quek.undergarden.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import quek.undergarden.Undergarden;

public class UGStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, Undergarden.MODID);

    public static final ResourceKey<Structure> CATACOMBS_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(Undergarden.MODID, "catacombs"));
}