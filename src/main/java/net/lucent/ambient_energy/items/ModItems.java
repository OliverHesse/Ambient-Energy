package net.lucent.ambient_energy.items;

import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.items.custom.BasicDetection;
import net.lucent.ambient_energy.util.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AmbientEnergy.MOD_ID);

    public static final DeferredItem<Item> TIER_1_GOGGLES = ITEMS.register("tier_1_detection_goggles_item",()->
            new BasicDetection(
                    ModArmorMaterials.DETECTION_GOGGLES_TIER_1,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(50)
            ));
    public static final DeferredItem<Item> TIER_2_GOGGLES = ITEMS.register("tier_2_detection_goggles_item",()->
            new BasicDetection(
                    ModArmorMaterials.DETECTION_GOGGLES_TIER_2,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(50)
            ));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
