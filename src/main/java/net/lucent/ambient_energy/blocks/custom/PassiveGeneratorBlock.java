package net.lucent.ambient_energy.blocks.custom;

import net.lucent.ambient_energy.blocks.block_entities.ModBlockEntities;
import net.lucent.ambient_energy.blocks.block_entities.custom.GeneratorBlockEntity;
import net.lucent.ambient_energy.blocks.block_entities.custom.PassiveGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

public class PassiveGeneratorBlock extends Block implements EntityBlock {
    public final ResourceLocation outputId;
    public final BigDecimal outputPerTick;
    public String name;
    public PassiveGeneratorBlock(Properties properties,ResourceLocation id,String name,BigDecimal outputPerTick) {
        super(properties);
        outputId = id;
        this.name = name;
        this.outputPerTick = outputPerTick;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PassiveGeneratorBlockEntity(blockPos,blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) return null;

        return blockEntityType == ModBlockEntities.PASSIVE_GENERATOR_BE.get() ? (BlockEntityTicker<T>) PassiveGeneratorBlockEntity::tick : null;

    }
}
