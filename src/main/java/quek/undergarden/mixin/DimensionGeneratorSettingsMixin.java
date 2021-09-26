package quek.undergarden.mixin;

import net.minecraft.core.MappedRegistry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quek.undergarden.world.dimension.SeedBearer;

import java.util.Optional;

@Mixin(WorldGenSettings.class)
public class DimensionGeneratorSettingsMixin {

    @Inject(method = "<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;Ljava/util/Optional;)V", at = @At(value = "RETURN"))
    private void getSeedFromConstructor(long seed, boolean generateFeatures, boolean bonusChest, MappedRegistry<LevelStem> options, Optional<String> legacyOptions, CallbackInfo ci) {
        SeedBearer.putInSeed(seed);
    }
}