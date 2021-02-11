package net.mcdevmod.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.mcdevmod.DevMod;
import net.mcdevmod.mod.DebugUI;
import net.mcdevmod.mod.ModKeyBindings;
import net.minecraft.client.options.KeyBinding;

public class DevModClientFabric implements ClientModInitializer {
    private static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        DevMod.init();
        keyBinding = KeyBindingHelper.registerKeyBinding(ModKeyBindings.DEBUG_UI);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                DebugUI.open();
            }
        });
    }
}
