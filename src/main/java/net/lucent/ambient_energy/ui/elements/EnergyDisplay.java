package net.lucent.ambient_energy.ui.elements;

import net.lucent.ambient_energy.energy.ChunkEnergy;
import net.lucent.ambient_energy.energy.EnergyInstance;
import net.lucent.ambient_energy.network.server_bound.RequestClientSideChunkEnergyDataUpdate;
import net.lucent.ambient_energy.util.ModAttachments;
import net.lucent.ambient_energy.util.ModItemTags;
import net.lucent.easygui.elements.containers.EmptyContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.neoforge.network.PacketDistributor;

public class EnergyDisplay extends EmptyContainer {

    public EnergyDisplay(int x,int y){

        this.setX(x);
        this.setY(y);
        this.setCull(false);
    }

    @Override
    public void renderSelf(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderSelf(guiGraphics, mouseX, mouseY, partialTick);

        //verify player has head piece
        if(!Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.HEAD).is(ModItemTags.DETECTION_ITEM))return;
        //handle sync
        //packet will arrive after this function is run so there will be a miniscule delay
        ChunkPos chunkPos = Minecraft.getInstance().player.chunkPosition();
        PacketDistributor.sendToServer(new RequestClientSideChunkEnergyDataUpdate(chunkPos.x,chunkPos.z));


        ItemStack item = Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.HEAD);


        ChunkEnergy data = Minecraft.getInstance().level.getChunk(chunkPos.x, chunkPos.z).getData(ModAttachments.CHUNK_ENERGY);
        guiGraphics.pose().pushPose();
        for (EnergyInstance instance : data.energy.values()){
            MutableComponent text = Component.literal("");
            if(item.is(ModItemTags.TIER_1_DETECTION_ITEM)) text.append(instance.name);
            if(item.is(ModItemTags.TIER_2_DETECTION_ITEM)) text.append(": "+instance.currentEnergy);

            //todo break it up?
            guiGraphics.drawString(Minecraft.getInstance().font,text,0,0,-16777216,false);
            guiGraphics.pose().translate(0,Minecraft.getInstance().font.lineHeight,0);
        }
        guiGraphics.pose().popPose();
    }
}
