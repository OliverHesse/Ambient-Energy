package net.lucent.ambient_energy.energy;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.Nullable;

public class ChunkEnergyProvider  implements IAttachmentSerializer<CompoundTag, ChunkEnergy> {
    @Override
    public ChunkEnergy read(IAttachmentHolder iAttachmentHolder, CompoundTag compoundTag, HolderLookup.Provider provider) {
        ChunkEnergy data = new ChunkEnergy();
        data.loadNBTData(compoundTag,provider);
        return data;
    }

    @Override
    public @Nullable CompoundTag write(ChunkEnergy chunkEnergy, HolderLookup.Provider provider) {
        var tag = new CompoundTag();
        chunkEnergy.saveNBTData(tag, provider);
        return tag;
    }
}
