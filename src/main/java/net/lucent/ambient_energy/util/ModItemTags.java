package net.lucent.ambient_energy.util;

import net.lucent.ambient_energy.AmbientEnergy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> DETECTION_ITEM = create("detection_item");
    public static final TagKey<Item> TIER_1_DETECTION_ITEM = create("tier_1_detection_item");
    public static final TagKey<Item> TIER_2_DETECTION_ITEM = create("tier_2_detection_item");

    public static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AmbientEnergy.MOD_ID,name));
    }

}
