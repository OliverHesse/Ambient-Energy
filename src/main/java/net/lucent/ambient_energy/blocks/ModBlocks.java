package net.lucent.ambient_energy.blocks;

import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.blocks.custom.ConversionBlock;
import net.lucent.ambient_energy.blocks.custom.PassiveGeneratorBlock;
import net.lucent.ambient_energy.items.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.math.BigDecimal;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCK =
            DeferredRegister.createBlocks(AmbientEnergy.MOD_ID);
    /*
    public static final DeferredBlock<Block> TEST_CONVERSION_BLOCK = registerBlock("test_conversion_block",()->
            new ConversionBlock(BlockBehaviour.Properties.of(),
                    ResourceLocation.fromNamespaceAndPath(
                            AmbientEnergy.MOD_ID,
                            "mana"
                    ),
                    ResourceLocation.fromNamespaceAndPath(
                            AmbientEnergy.MOD_ID,
                            "pure_mana"
                    ),
                    Component.literal("Pure Mana"),
                    new BigDecimal("10"),
                    new BigDecimal("1")));

     */

    /*
    public static final DeferredBlock<Block> TEST_PASSIVE_GENERATOR = registerBlock("test_passive_generator",()->
            new PassiveGeneratorBlock(BlockBehaviour.Properties.of(),
                    ResourceLocation.fromNamespaceAndPath(
                            AmbientEnergy.MOD_ID,
                            "ether"
                    ),
                    "Ether",
                    new BigDecimal("0.1")));

    */
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCK.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
