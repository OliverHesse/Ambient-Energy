package net.lucent.ambient_energy.items;

import net.minecraft.resources.ResourceLocation;

import java.math.BigDecimal;

public interface GeneratorFuel {


    ResourceLocation getEnergyOutputType();
    BigDecimal getEnergyOutput();
}
