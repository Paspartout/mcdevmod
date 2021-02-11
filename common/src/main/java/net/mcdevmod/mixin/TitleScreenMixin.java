package net.mcdevmod.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Text title) {
        super(title);
    }
    private boolean opened = false;

    @Inject(at = @At("TAIL"), method = "init()V")
    private void init(CallbackInfo info) {
        if (!opened) {
            assert client != null;
            client.openScreen(new SelectWorldScreen(this));
            opened = true;
        }
    }
}
