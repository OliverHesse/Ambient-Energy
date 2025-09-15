package net.lucent.ambient_energy.util;

import net.lucent.ambient_energy.AmbientEnergy;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static net.lucent.ambient_energy.AmbientEnergy.MOD_ID;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MOD_ID);
    public static void register(IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }
    public static DeferredHolder<ArmorMaterial, ArmorMaterial> DETECTION_GOGGLES_TIER_1 = ARMOR_MATERIALS.register("detection_goggles_1", ()-> new ArmorMaterial(
            Util.make(new EnumMap<ArmorItem.Type,Integer>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            0,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(Tags.Items.GEMS_EMERALD),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "tier_1_detection_goggles")
                    )
            ),
            0,
            0
    ));
    public static DeferredHolder<ArmorMaterial, ArmorMaterial> DETECTION_GOGGLES_TIER_2 = ARMOR_MATERIALS.register("detection_goggles_2", ()-> new ArmorMaterial(
            Util.make(new EnumMap<ArmorItem.Type,Integer>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            0,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(Tags.Items.GEMS_EMERALD),
            List.of(
                    new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "tier_2_detection_goggles")
                    )
            ),
            0,
            0
    ));

}
