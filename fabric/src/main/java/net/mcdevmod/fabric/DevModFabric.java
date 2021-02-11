package net.mcdevmod.fabric;

import net.mcdevmod.DevMod;
import net.fabricmc.api.ModInitializer;

public class DevModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DevMod.init();
    }
}
