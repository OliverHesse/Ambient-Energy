package net.lucent.ambient_energy.energy;

import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.util.Codecs;
import net.lucent.ambient_energy.util.ModAttachments;
import net.lucent.ambient_energy.util.ModBiomeConfigs;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = AmbientEnergy.MOD_ID)
public class ChunkEventHandler {
    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event){
        if(!event.isNewChunk()) return;
        if (!(event.getChunk() instanceof LevelChunk levelChunk)) return;
        ChunkEnergy data = levelChunk.getData(ModAttachments.CHUNK_ENERGY);
        for(ResourceKey<Biome> biome :ChunkEventHandler.getAllBiomesInChunk(levelChunk)){
            if(EnergyBiomeModifier.getDataForBiome(biome) == null) continue;
            for(Codecs.BiomeEnergyCodec biomeEnergyCodec : EnergyBiomeModifier.getDataForBiome(biome).energies()){

                data.addEnergy(new EnergyInstance(biomeEnergyCodec));
            }

        }
        levelChunk.setUnsaved(false);
    }
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event){
        for (Level level : event.getServer().getAllLevels()){
            if(level.isClientSide) continue;
            //System.out.println("number of loaded chunks: "+level.getChunkSource().getLoadedChunksCount());
            if(level.getChunkSource() instanceof ServerChunkCache serverChunkCache){
                int count = 0;
                for(ChunkHolder holder:serverChunkCache.chunkMap.getChunks()){
                    count += 1;
                    if(holder.getTickingChunk() == null) continue;
                    if(!holder.getTickingChunk().hasData(ModAttachments.CHUNK_ENERGY)) return;
                    //TODO potentially change to check with has. and then attach using load.
                    //TODO that would allow for more control on what chunks can have ether
                    ChunkEnergy data = holder.getTickingChunk().getData(ModAttachments.CHUNK_ENERGY);
                    data.gatherEnergy();
                    data.decayEnergy();
                    //System.out.println("mana("+data.getEnergy(ResourceLocation.parse("ambient_energy:mana")).currentEnergy+")");
                }
                //System.out.println("number of chunks in map: "+count);

            }
        }
    }
    public static Set<ResourceKey<Biome>> getAllBiomesInChunk(ChunkAccess chunk) {
        Set<ResourceKey<Biome>> biomes = new HashSet<>();
        int biomeY = chunk.getHeight() / 4;

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < biomeY; y++) {
                for (int z = 0; z < 4; z++) {
                    Holder<Biome> biomeHolder = chunk.getNoiseBiome(x, y, z);
                    biomeHolder.unwrapKey().ifPresent(biomes::add);
                }
            }
        }

        return biomes;
    }

}
