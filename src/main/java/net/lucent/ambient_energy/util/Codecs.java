package net.lucent.ambient_energy.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class Codecs {
    public record BiomeEnergyCodec(String id,BiomeNameCodec name,String initialEnergy,String energyGain,String energyDecay){}
    public record BiomeNameCodec(String type,String data){}
    public static final Codec<BiomeNameCodec> BIOME_NAME_CODEC = RecordCodecBuilder.create(instance->
            instance.group(
                    Codec.STRING.fieldOf("type").forGetter(BiomeNameCodec::type),
                    Codec.STRING.fieldOf("data").forGetter(BiomeNameCodec::data)
            ).apply(instance,BiomeNameCodec::new)
    );
    public static final Codec<BiomeEnergyCodec> BIOME_ENERGY_CODEC = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    Codec.STRING.fieldOf("id").forGetter(BiomeEnergyCodec::id),
                    BIOME_NAME_CODEC.fieldOf("name").forGetter(BiomeEnergyCodec::name),
                    Codec.STRING.optionalFieldOf("initial_energy","0").forGetter(BiomeEnergyCodec::initialEnergy),
                    Codec.STRING.optionalFieldOf("energy_gain","0").forGetter(BiomeEnergyCodec::energyGain),
                    Codec.STRING.optionalFieldOf("energy_decay","0").forGetter(BiomeEnergyCodec::energyDecay)
            ).apply(instance, BiomeEnergyCodec::new) // Define how to create the object
    );
}
