package net.lucent.ambient_energy.util;

import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.blocks.ModBlocks;
import net.lucent.ambient_energy.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AmbientEnergy.MOD_ID);
    public static final Supplier<CreativeModeTab> AMBIENT_ENERGY_TAB = CREATIVE_MODE_TAB.register("mysticaltrees_main",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TIER_1_GOGGLES.get()))
                    .title(Component.literal("Ambient Energy"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (DeferredHolder<Block,?extends Block> blockHolder : ModBlocks.BLOCK.getEntries()){
                            output.accept(blockHolder.get());
                        }
                        for (DeferredHolder<Item,?extends Item> itemHolder : ModItems.ITEMS.getEntries()) {
                            output.accept(itemHolder.get());
                        }
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
