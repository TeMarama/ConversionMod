package nitis.conversion.world.biome;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import nitis.conversion.ConversionMod;

public class ConversionBiomes implements ModInitializer {

    private static final Biome MAPLE_FOREST = createMapleForest();

    @Override
    public void onInitialize() {
        Registry.register(BuiltinRegistries.BIOME, BiomeRegistry.MAPLE_FOREST.getValue(), MAPLE_FOREST);
    }
    public static Biome createMapleForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultGrass(generationSettings);
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings);
        DefaultBiomeFeatures.addExtraDefaultFlowers(generationSettings);

        return new Biome.Builder()
                .category(Biome.Category.NONE)
                .temperature(0.3f).temperatureModifier(Biome.TemperatureModifier.NONE)
                .downfall(0.2f).precipitation(Biome.Precipitation.RAIN)
                .effects(createEffects())
                .generationSettings(generationSettings.build())
                .spawnSettings(spawnSettings.build())
                .build();
    }
    public static BiomeEffects createEffects() {
        return new BiomeEffects.Builder()
                .waterColor(4159204)
                .waterFogColor(329011)

                .skyColor(0x333ccc)
                .fogColor(12638463)

                .foliageColor(0x5580aa)
                .grassColor(0x33aaaa)
                //.moodSound(BiomeMoodSound.CAVE)
                .build();
    }
}
