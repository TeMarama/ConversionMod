package nitis.conversion.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import nitis.conversion.ConversionMod;

import java.util.function.Predicate;

public abstract class BiomeRegistry {
    public static final RegistryKey<Biome> MAPLE_FOREST = registerKey("maple_forest");

    public static final Predicate<BiomeSelectionContext> CONVERSION_BIOMES_SELECTOR = BiomeSelectors.includeByKey(
            MAPLE_FOREST);

    private static RegistryKey<Biome> registerKey(String name){
        return RegistryKey.of(Registry.BIOME_KEY, ConversionMod.idOf(name));
    }
    private BiomeRegistry() {

    }
}
