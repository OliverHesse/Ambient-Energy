package net.lucent.ambient_energy.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.energy.EnergyBiomeModifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static net.lucent.ambient_energy.AmbientEnergy.MOD_ID;
import static net.lucent.ambient_energy.util.Codecs.BIOME_ENERGY_CODEC;

    public class ModBiomeConfigs {
        private static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS =
                DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MOD_ID);

        public static final ResourceKey<BiomeModifier> ENERGY_MODIFIER = ResourceKey.create(
                NeoForgeRegistries.Keys.BIOME_MODIFIERS, // The registry this key is for
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "energy_modifier") // The registry name
        );
        public static final Supplier<MapCodec<EnergyBiomeModifier>> ENERGY_BIOME_MODIFIER =
                BIOME_MODIFIERS.register("energy_biome_modifier", () -> RecordCodecBuilder.mapCodec(instance ->
                        instance.group(
                                Biome.LIST_CODEC.fieldOf("biomes").forGetter(EnergyBiomeModifier::biomes),
                                BIOME_ENERGY_CODEC.listOf().fieldOf("energies").forGetter(EnergyBiomeModifier::energies)
                        ).apply(instance, EnergyBiomeModifier::new)
                ));

        public static void register(IEventBus eventBus){
            BIOME_MODIFIERS.register(eventBus);
        }
    }
