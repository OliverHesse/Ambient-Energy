package net.lucent.ambient_energy.blocks.block_entities.custom;

import net.lucent.ambient_energy.blocks.block_entities.ModBlockEntities;
import net.lucent.ambient_energy.blocks.custom.PassiveGeneratorBlock;
import net.lucent.ambient_energy.util.ModAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

public class PassiveGeneratorBlockEntity extends BlockEntity {
    public PassiveGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }
    public PassiveGeneratorBlockEntity( BlockPos pos, BlockState blockState) {
        this(ModBlockEntities.PASSIVE_GENERATOR_BE.get(), pos, blockState);
        System.out.println("created passive generator");
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        ChunkAccess chunkAccess = level.getChunk(blockPos);

        PassiveGeneratorBlock block = (PassiveGeneratorBlock) blockState.getBlock();

        chunkAccess.getData(ModAttachments.CHUNK_ENERGY).increaseEnergy(block.outputId,block.name,block.outputPerTick);

        chunkAccess.setUnsaved(true);
        System.out.println("ticking");
    }
}
