package net.mcdevmod.mod;

import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static final KeyBinding DEBUG_UI = new KeyBinding(
            "key.mcdevmod.opengui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.mcdevmod.debug"
    );
}
