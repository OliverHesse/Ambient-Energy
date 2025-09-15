package net.lucent.ambient_energy.network.server_bound;

import io.netty.buffer.ByteBuf;
import net.lucent.ambient_energy.AmbientEnergy;
import net.lucent.ambient_energy.energy.ChunkEnergy;
import net.lucent.ambient_energy.network.client_bound.ChunkEnergyDataPayload;
import net.lucent.ambient_energy.util.ModAttachments;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record RequestClientSideChunkEnergyDataUpdate(int x, int z) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<RequestClientSideChunkEnergyDataUpdate> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AmbientEnergy.MOD_ID, "request_client_chunk_energy"));
    public static final StreamCodec<ByteBuf, RequestClientSideChunkEnergyDataUpdate> STREAM_CODEC = StreamCodec.composite(

            ByteBufCodecs.INT,
            RequestClientSideChunkEnergyDataUpdate::x,
            ByteBufCodecs.INT,
            RequestClientSideChunkEnergyDataUpdate::z,
            RequestClientSideChunkEnergyDataUpdate::new
    );
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
    public static void handlePayload(RequestClientSideChunkEnergyDataUpdate payload, IPayloadContext context) {
        ChunkEnergy data = context.player().level().getChunk(payload.x,payload.z).getData(ModAttachments.CHUNK_ENERGY);
        PacketDistributor.sendToPlayer((ServerPlayer) context.player(),new ChunkEnergyDataPayload(payload.x,payload.z,data.toHashSet()));
    }

}