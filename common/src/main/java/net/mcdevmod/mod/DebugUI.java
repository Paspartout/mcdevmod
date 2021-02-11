package net.mcdevmod.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;

public class DebugUI extends Screen {
    private static final int WIDTH = 180;
    private static final int HEIGHT = 150;

    protected DebugUI() {
        super(new TranslatableText("Debug"));
    }

    @Override
    protected void init() {
        int x = (this.width - WIDTH) / 2;
        int y = (this.height - HEIGHT) / 2;

        addButton(new ButtonWidget(x + 10, y + 10, 160, 20, new LiteralText("720P"), button -> resize_window(1280, 720)));
        addButton(new ButtonWidget(x + 10, y + 40, 160, 20, new LiteralText("1080P"), button -> resize_window(1920, 1080)));

        addButton(new ButtonWidget(x + 10, y + 70, 160, 20, new LiteralText("ModeToggle"), button -> toggleGameMode()));
        addButton(new ButtonWidget(x + 10, y + 100, 160, 20, new LiteralText("Heal"), button -> heal()));

        addButton(new ButtonWidget(x + 10, y + 130, 160, 20, new LiteralText("Kill NonPlayers"), button -> cmd("/kill @e[type=!minecraft:player]")));

    }

    private void heal() {
        if (client == null) {
            return;
        }
        ClientPlayerEntity player = client.player;
        if (player == null) {
            return;
        }

        String name = player.getName().getString();
        player.sendChatMessage(String.format("/effect give %s minecraft:saturation 10 10 true", name));
    }

    public static void resize_window(int width, int height) {
        Window window = MinecraftClient.getInstance().getWindow();
        GLFW.glfwSetWindowSize(window.getHandle(), width, height);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_R) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        int x = (this.width - WIDTH) / 2;
        int y = (this.height - WIDTH) / 2;

        assert client != null;
        client.textRenderer.drawWithShadow(matrixStack, this.getTitle().getString(), x + 10, y, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public static void open() {
        MinecraftClient.getInstance().openScreen(new DebugUI());
    }

    public void toggleGameMode() {
        assert client != null;
        assert client.player != null;
        if (client.player.isCreative()) {
            client.player.sendChatMessage("/gamemode survival");
        } else {
            client.player.sendChatMessage("/gamemode creative");
        }
    }

    public void cmd(String cmd) {
        assert client != null;
        assert client.player != null;
        client.player.sendChatMessage(cmd);
    }
}

