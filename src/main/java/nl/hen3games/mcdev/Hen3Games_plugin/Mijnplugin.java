package nl.hen3games.mcdev.Hen3Games_plugin;

import nl.hen3games.mcdev.Hen3Games_plugin.Commands.fly;
import nl.hen3games.mcdev.Hen3Games_plugin.Commands.flySpeed;
import nl.hen3games.mcdev.Hen3Games_plugin.Commands.gamemode;
import nl.hen3games.mcdev.Hen3Games_plugin.Commands.mod;
import nl.hen3games.mcdev.Hen3Games_plugin.Files.functions;
import nl.hen3games.mcdev.Hen3Games_plugin.Library.SpelerChat;
import nl.hen3games.mcdev.Hen3Games_plugin.Library.SpelerJoin;
import nl.hen3games.mcdev.Hen3Games_plugin.Library.SpelerQuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mijnplugin extends JavaPlugin {

    @Override
    public void onEnable() {

        functions.loadFiles();

        getServer().getPluginManager().registerEvents(new SpelerJoin(), this);
        getServer().getPluginManager().registerEvents(new SpelerQuit(), this);
        getServer().getPluginManager().registerEvents(new SpelerChat(), this);

        getCommand("mod").setExecutor(new mod(this));
        getCommand("gm").setExecutor(new gamemode(this));
        getCommand("gamemode").setExecutor(new gamemode(this));
        getCommand("fly").setExecutor(new fly(this));
        getCommand("flyspeed").setExecutor(new flySpeed(this));

        for (Player player : Bukkit.getOnlinePlayers()){
            if (!functions.playerAlreadyCreated(player)){
                functions.createPlayer(player);
            }
        }

    }

    @Override
    public void onDisable() {
        functions.saveFiles();
    }
}
