package net.lucent.ambient_energy.energy;

import com.mojang.serialization.MapCodec;
import net.lucent.ambient_energy.util.Codecs;
import net.lucent.ambient_energy.util.ModBiomeConfigs;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record EnergyBiomeModifier(HolderSet<Biome> biomes, List< Codecs.BiomeEnergyCodec> energies) implements BiomeModifier {
    public record EnergyBiomeData(List< Codecs.BiomeEnergyCodec> energies){}
    private static final HashMap<ResourceKey<Biome>,EnergyBiomeData> biomeDataMap = new HashMap<>();

    public static EnergyBiomeData getDataForBiome(ResourceKey<Biome> key){
        return biomeDataMap.get(key);

    }
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        biome.unwrapKey().ifPresent(biomeResourceKey -> {
            EnergyBiomeModifier.biomeDataMap.put(biomeResourceKey,new EnergyBiomeData(energies));
        });
    }



    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return ModBiomeConfigs.ENERGY_BIOME_MODIFIER.get();
    }
}