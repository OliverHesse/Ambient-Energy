package net.lucent.ambient_energy;

import net.lucent.ambient_energy.ui.elements.EnergyDisplay;
import net.lucent.easygui.elements.containers.View;
import net.lucent.easygui.overlays.EasyGuiOverlay;
import net.lucent.easygui.overlays.EasyGuiOverlayManager;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = AmbientEnergy.MOD_ID,dist = Dist.CLIENT)
public class AmbientEnergyClient {
    public AmbientEnergyClient(IEventBus modEventBus, ModContainer container){
        EasyGuiOverlayManager.addLayer(ResourceLocation.fromNamespaceAndPath(AmbientEnergy.MOD_ID,"cultivation_progress"),new EasyGuiOverlay((holder, overlay)->{
            View view = new View(overlay);
            view.setUseMinecraftScale(true);
            overlay.addView(view);
            System.out.println("setup overlay");
            view.addChild(new EnergyDisplay(0,0));
        }));
    }
}
