package net.lucent.ambient_energy.network.client_bound;

import io.netty.buffer.ByteBuf;
import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.energy.EnergyInstance;
import net.lucent.ambient_energy.network.server_bound.RequestClientSideChunkEnergyDataUpdate;
import net.lucent.ambient_energy.util.ModAttachments;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public record ChunkEnergyDataPayload(int x,int z,HashSet<EnergyInstance> instances) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ChunkEnergyDataPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AmbientEnergy.MOD_ID, "chunk_energy_data_payload"));
    public static final StreamCodec<ByteBuf, ChunkEnergyDataPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ChunkEnergyDataPayload::x,
            ByteBufCodecs.VAR_INT,
            ChunkEnergyDataPayload::z,
            ByteBufCodecs.collection(
                    HashSet::new,
                    StreamCodec.of(EnergyInstance::encode,EnergyInstance::new),
                    256),
            ChunkEnergyDataPayload::instances,

            ChunkEnergyDataPayload::new
    );
    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
    public static void handlePayload(ChunkEnergyDataPayload payload, IPayloadContext context) {
        context.player().level().getChunk(payload.x,payload.z).getData(ModAttachments.CHUNK_ENERGY).addEnergies(payload.instances);
    }

}
