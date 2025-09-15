package net.lucent.ambient_energy.blocks.custom;

import net.lucent.ambient_energy.blocks.block_entities.ModBlockEntities;
import net.lucent.ambient_energy.blocks.block_entities.custom.ConversionBlockEntity;
import net.lucent.ambient_energy.blocks.block_entities.custom.GeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GeneratorBlock extends Block implements EntityBlock {
    public final ResourceLocation outputId;
    public double BURN_SPEED_MULTIPLIER = 1;
    public GeneratorBlock(Properties properties,ResourceLocation outputId) {
        super(properties);
        this.outputId = outputId;
    }
    public GeneratorBlock setBurnSpeedMultiplier(double multiplier){
        BURN_SPEED_MULTIPLIER = multiplier;
        return this;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GeneratorBlockEntity(blockPos,blockState);
    }
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) return null;

        return blockEntityType == ModBlockEntities.GENERATOR_BE.get() ? (BlockEntityTicker<T>) GeneratorBlockEntity::tick : null;
    }
}
