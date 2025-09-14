package net.lucent.ambient_energy.util;

import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.energy.ChunkEnergy;
import net.lucent.ambient_energy.energy.ChunkEnergyProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, AmbientEnergy.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ChunkEnergy>> CHUNK_ENERGY = ATTACHMENT_TYPES.register("chunk_energy_data",
            () -> AttachmentType.builder((holder) -> new ChunkEnergy()).serialize(new ChunkEnergyProvider()).build());


    public static void register(IEventBus modEventBus){
        ATTACHMENT_TYPES.register(modEventBus);
    }
}
