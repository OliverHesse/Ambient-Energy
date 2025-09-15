package net.lucent.ambient_energy.energy;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ChunkEnergy {
    public HashMap<ResourceLocation,EnergyInstance> energy = new HashMap<>();


    public void gatherEnergy(){
        for(EnergyInstance instance:energy.values()){
            instance.gather();
        }
    }
    public void decayEnergy(){
        for(EnergyInstance instance:energy.values()){
            instance.decay();
        }
    }
    public void gatherEnergy(ResourceLocation id){
        if(!energy.containsKey(id)) energy.put(id,new EnergyInstance(id, Component.translatable("energy."+id.getNamespace()+"."+id.getPath()).getString()));
        energy.get(id).gather();
    }
    public void decayEnergy(ResourceLocation id){
        if(!energy.containsKey(id)) return;
        energy.get(id).decay();
    }
    public EnergyInstance getEnergy(ResourceLocation id){
        if(!energy.containsKey(id)) energy.put(id,new EnergyInstance(id, Component.translatable("energy."+id.getNamespace()+"."+id.getPath()).getString()));
        return energy.get(id);
    }
    public void addEnergy(ResourceLocation id){
        if(energy.containsKey(id)) return;
        energy.put(id,new EnergyInstance(id, Component.translatable("energy."+id.getNamespace()+"."+id.getPath()).getString()));
    }
    public void addEnergy(ResourceLocation id,String name){
        if(energy.containsKey(id)) return;
        energy.put(id,new EnergyInstance(id, name));

    }
    public void addEnergy(EnergyInstance energyInstance){
        energy.put(energyInstance.id,energyInstance);
    }
    public HashSet<EnergyInstance> toHashSet(){
        return new HashSet<>(energy.values());
    }
    public void addEnergies(HashSet<EnergyInstance> instances){
        for(EnergyInstance instance : instances){
            energy.put(instance.id,instance);
        }
    }
    public void loadNBTData(CompoundTag tag, HolderLookup.Provider provider){
        ListTag listTag = tag.getList("energy",Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag instance = listTag.getCompound(i);
            EnergyInstance energyInstance = new EnergyInstance(instance);
            energy.put(energyInstance.id,energyInstance);
        }
    }
    public void saveNBTData(CompoundTag tag,HolderLookup.Provider provider){
        ListTag listTag = new ListTag();
        for(EnergyInstance instance:energy.values()){
            listTag.add(instance.serialize());
        }
        tag.put("energy",listTag);
    }

}
