package net.lucent.ambient_energy.blocks.block_entities.custom;

import net.lucent.ambient_energy.blocks.block_entities.ModBlockEntities;
import net.lucent.ambient_energy.blocks.custom.ConversionBlock;
import net.lucent.ambient_energy.energy.ChunkEnergy;
import net.lucent.ambient_energy.util.ModAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ConversionBlockEntity extends BlockEntity {
    public ConversionBlockEntity(BlockEntityType<?> type,BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }
    public ConversionBlockEntity(BlockPos pos, BlockState blockState) {
        this(ModBlockEntities.CONVERSION_BE.get(), pos, blockState);
        System.out.println("Block entity made");
    }
    //T is this block entity
    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        ConversionBlock block = (ConversionBlock) blockState.getBlock();
        ChunkEnergy data = level.getChunk(blockPos).getData(ModAttachments.CHUNK_ENERGY);

        if(!data.reduceEnergy(block.inputEnergy,block.conversionInput)) return;

        data.increaseEnergy(block.outputEnergy,block.outputEnergyName.getString(),block.conversionOutput);
        level.getChunk(blockPos).setUnsaved(true);
        //System.out.println("energy converted");
    }
}
