package net.mcdevmod.carpet;

import com.mojang.brigadier.CommandDispatcher;

import net.mcdevmod.carpet.commands.TickCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.MinecraftServer;

public class CarpetServer // static for now - easier to handle all around the code, its one anyways
{
    //public static SettingsManager settingsManager;

    public static void onGameStarted()
    {
        //settingsManager = new SettingsManager(CarpetSettings.carpetVersion, "carpet", "Carpet Lite");
        //settingsManager.parseSettingsClass(CarpetSettings.class);
    }

    public static void onServerLoaded(MinecraftServer server)
    {
        //settingsManager.attachServer(server);
    }

    public static void tick(MinecraftServer server)
    {
        //CarpetSettings.impendingFillSkipUpdates = false;
    }

    public static void registerCarpetCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        TickCommand.register(dispatcher);
        //CounterCommand.register(dispatcher);
        //SpawnCommand.register(dispatcher);
        //PlayerCommand.register(dispatcher);
        //TestCommand.register(dispatcher);
    }

    public static void onServerClosed(MinecraftServer server)
    {
        //settingsManager.detachServer();
    }
}

