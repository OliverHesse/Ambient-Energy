package net.lucent.ambient_energy.datagen;


import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.items.ModItems;
import net.lucent.ambient_energy.util.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, blockTags, AmbientEnergy.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModItemTags.DETECTION_ITEM)
                .add(ModItems.TIER_1_GOGGLES.get())
                .add(ModItems.TIER_2_GOGGLES.get());

        this.tag(ModItemTags.TIER_1_DETECTION_ITEM)
                .add(ModItems.TIER_1_GOGGLES.get())
                .add(ModItems.TIER_2_GOGGLES.get());
        this.tag(ModItemTags.TIER_2_DETECTION_ITEM)
                .add(ModItems.TIER_2_GOGGLES.get());

    }
}