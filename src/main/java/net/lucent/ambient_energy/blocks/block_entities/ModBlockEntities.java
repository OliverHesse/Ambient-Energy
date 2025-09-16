package net.lucent.ambient_energy.blocks.block_entities;

import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.blocks.ModBlocks;
import net.lucent.ambient_energy.blocks.block_entities.custom.ConversionBlockEntity;
import net.lucent.ambient_energy.blocks.block_entities.custom.GeneratorBlockEntity;
import net.lucent.ambient_energy.blocks.block_entities.custom.PassiveGeneratorBlockEntity;
import net.lucent.ambient_energy.blocks.custom.ConversionBlock;
import net.lucent.ambient_energy.blocks.custom.GeneratorBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AmbientEnergy.MOD_ID);

    public static  final Supplier<BlockEntityType<ConversionBlockEntity>> CONVERSION_BE =
            BLOCK_ENTITIES.register("conversion_block_be",() -> BlockEntityType.Builder.of(
                    ConversionBlockEntity::new
                   // ModBlocks.TEST_CONVERSION_BLOCK.get()
            ).build(null));
    public static  final Supplier<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BE =
            BLOCK_ENTITIES.register("generator_block_be",() -> BlockEntityType.Builder.of(
                    GeneratorBlockEntity::new).build(null));
    public static  final Supplier<BlockEntityType<PassiveGeneratorBlockEntity>> PASSIVE_GENERATOR_BE =
            BLOCK_ENTITIES.register("passive_generator_block_be",() -> BlockEntityType.Builder.of(
                    PassiveGeneratorBlockEntity::new
                   // ModBlocks.TEST_PASSIVE_GENERATOR.get()
            ).build(null));

    public static void register(IEventBus eventBus){

        BLOCK_ENTITIES.register(eventBus);

    }
}
