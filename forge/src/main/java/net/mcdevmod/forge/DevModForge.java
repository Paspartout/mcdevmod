package net.mcdevmod.forge;

import net.mcdevmod.DevMod;
import net.mcdevmod.mod.DebugUI;
import net.mcdevmod.mod.ModKeyBindings;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(DevMod.MOD_ID)
public class DevModForge {
    public DevModForge() {
        DevMod.init();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModSetup {
        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
        }
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            ClientRegistry.registerKeyBinding(ModKeyBindings.DEBUG_UI);
        }
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void handleInput(InputEvent.KeyInputEvent event) {
            if (ModKeyBindings.DEBUG_UI.isPressed()) {
                DebugUI.open();
            }
        }
    }
}
