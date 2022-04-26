package nitis.conversion.mixins;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import nitis.conversion.ConversionMod;
import nitis.conversion.world.biome.BiomeRegistry;
import nitis.conversion.world.biome.ConversionBiomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;

@Mixin(BiomeSource.class)
public class BiomeSourceMixin {

    @Shadow @Final private Set<RegistryEntry<Biome>> biomes;

    @Inject(method = "<init>(Ljava/util/List;)V", at = @At("TAIL"))
    public void init(List<RegistryEntry<Biome>> biomes, CallbackInfo ci) {

        for (RegistryEntry<Biome> biome : biomes) {
            ConversionMod.log(biome);
        }
    }

}
