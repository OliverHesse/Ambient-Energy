package net.lucent.ambient_energy.energy;

import io.netty.buffer.ByteBuf;
import net.lucent.ambient_energy.util.Codecs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.function.Function;

public class EnergyInstance {
    private static final Map<String, Function<String, String>> dispatchMap = Map.of(
            "translatable", s -> Component.translatable(s).getString(),
            "value", s -> s
    );

    public final ResourceLocation id;
    public final String name;
    public BigDecimal currentEnergy = BigDecimal.ZERO;
    public BigDecimal gatherRate = BigDecimal.ZERO;
    public BigDecimal decayRate = BigDecimal.ZERO;
    public EnergyInstance(Codecs.BiomeEnergyCodec data){
        this(
                ResourceLocation.bySeparator(data.id(),':'),
                dispatchMap.get(data.name().type()).apply(data.name().data()),
                new BigDecimal(data.energyGain()),
                new BigDecimal(data.energyDecay())
        );
        this.currentEnergy = new BigDecimal(data.initialEnergy());
    }
    public EnergyInstance(ByteBuf buffer) {
        this.id = ResourceLocation.bySeparator(buffer.readCharSequence(buffer.readInt(),Charset.defaultCharset()).toString(),':');
        name = buffer.readCharSequence(buffer.readInt(),Charset.defaultCharset()).toString();
        currentEnergy = new BigDecimal(buffer.readCharSequence(buffer.readInt(),Charset.defaultCharset()).toString());
    }


    public EnergyInstance(EnergyInstance instance){
        this(
                ResourceLocation.bySeparator(instance.id.toString(),':'),
                String.copyValueOf(instance.name.toCharArray()),
                new BigDecimal(instance.gatherRate.toPlainString()),
                new BigDecimal(instance.decayRate.toPlainString())
        );
        this.currentEnergy = new BigDecimal(instance.currentEnergy.toPlainString());
    }
    public EnergyInstance(CompoundTag tag){
        this(
                ResourceLocation.bySeparator(tag.getString("id"),':'),
                tag.getString("name"),
                new BigDecimal(tag.getString("gather_rate")),
                new BigDecimal(tag.getString("decay_rate"))
        );
        this.currentEnergy = new BigDecimal(tag.getString("current_energy"));

    }
    public EnergyInstance(ResourceLocation id, String name){
        this.id = id;
        this.name = name;
    }
    public EnergyInstance(ResourceLocation id,String name, BigDecimal gatherRate){
        this(id,name);
        this.gatherRate = gatherRate;
    }
    public EnergyInstance(ResourceLocation id,String name,BigDecimal gatherRate,BigDecimal decayRate){
        this(id,name,gatherRate);
        this.decayRate = decayRate;
    }



    public void gather(){
        currentEnergy = currentEnergy.add(gatherRate);
    }
    public void decay(){
        currentEnergy = BigDecimal.ZERO.max(currentEnergy.subtract(decayRate));
    }
    public boolean reduceEnergy(BigDecimal amount){
        if(currentEnergy.compareTo(amount) < 0) return false;
        currentEnergy = currentEnergy.subtract(amount);
        return true;
    }
    public void increaseEnergy(BigDecimal amount){
        this.currentEnergy = this.currentEnergy.add(amount);
    }
    public void setEnergy(BigDecimal amount){
        this.currentEnergy = amount;
    }

    public CompoundTag serialize(){
        CompoundTag tag = new CompoundTag();
        tag.putString("id",id.toString());
        tag.putString("name",name);
        tag.putString("current_energy",currentEnergy.toPlainString());
        tag.putString("gather_rate",gatherRate.toPlainString());
        tag.putString("decay_rate",decayRate.toPlainString());
        return tag;
    }
    //client does not need gain/decay

    public static void encode(ByteBuf buffer, EnergyInstance energyInstance) {

        buffer.writeInt(energyInstance.id.toString().length());
        buffer.writeCharSequence(energyInstance.id.toString(), Charset.defaultCharset());
        buffer.writeInt(energyInstance.name.length());
        buffer.writeCharSequence(energyInstance.name, Charset.defaultCharset());
        buffer.writeInt(energyInstance.currentEnergy.toString().length());
        buffer.writeCharSequence(energyInstance.currentEnergy.toString(), Charset.defaultCharset());
    }

}
