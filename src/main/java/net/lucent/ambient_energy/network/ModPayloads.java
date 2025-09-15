package net.lucent.ambient_energy.network;

import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.network.client_bound.ChunkEnergyDataPayload;
import net.lucent.ambient_energy.network.server_bound.RequestClientSideChunkEnergyDataUpdate;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModPayloads {
    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar =event.registrar(AmbientEnergy.MOD_ID).versioned("1.0");
        registrar.playToServer(
                RequestClientSideChunkEnergyDataUpdate.TYPE,
                RequestClientSideChunkEnergyDataUpdate.STREAM_CODEC,
                RequestClientSideChunkEnergyDataUpdate::handlePayload

        );
        registrar.playToClient(
                ChunkEnergyDataPayload.TYPE,
                ChunkEnergyDataPayload.STREAM_CODEC,
                ChunkEnergyDataPayload::handlePayload
        );
    }
}
