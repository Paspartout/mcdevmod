package net.mcdevmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(SelectWorldScreen.class)
public class SelectWorldScreenMixin {

    @Shadow
    private WorldListWidget levelList;

    private boolean secondInit = false;

    @Inject(at = @At("TAIL"), method = "init()V")
    private void init(CallbackInfo info) {
        if (!secondInit) {
            secondInit = true;
            return;
        }
        try {
            levelList.setSelected(levelList.children().get(0));
            levelList.method_20159().ifPresent(WorldListWidget.Entry::play);
        } catch(IndexOutOfBoundsException ignored) {
        }
    }
}

