package net.lucent.ambient_energy.blocks.custom;

import net.lucent.ambient_energy.blocks.block_entities.ModBlockEntities;
import net.lucent.ambient_energy.blocks.block_entities.custom.ConversionBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

public class ConversionBlock extends Block implements EntityBlock {
    public final ResourceLocation inputEnergy;
    public final ResourceLocation outputEnergy;
    public final Component outputEnergyName;
    public BigDecimal conversionOutput;
    public BigDecimal conversionInput;
    public ConversionBlock(Properties properties, ResourceLocation inputEnergy, ResourceLocation outputEnergy, Component outputEnergyName, BigDecimal conversionInput, BigDecimal conversionOutput) {
        super(properties);
        this.inputEnergy = inputEnergy;
        this.outputEnergy = outputEnergy;
        this.outputEnergyName = outputEnergyName;
        this.conversionInput = conversionInput;
        this.conversionOutput =conversionOutput;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ConversionBlockEntity(blockPos,blockState);
    }
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) return null;

        return blockEntityType == ModBlockEntities.CONVERSION_BE.get() ? (BlockEntityTicker<T>) ConversionBlockEntity::tick : null;
    }
}
