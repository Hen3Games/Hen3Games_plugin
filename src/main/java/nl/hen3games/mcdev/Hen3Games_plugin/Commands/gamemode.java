package nl.hen3games.mcdev.Hen3Games_plugin.Commands;

import nl.hen3games.mcdev.Hen3Games_plugin.Mijnplugin;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import nl.hen3games.mcdev.Hen3Games_plugin.subCommands.gamemodeSurvival;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemode implements CommandExecutor {
    public gamemode(Mijnplugin mijnplugin) {
    }

    gamemodeSurvival gamemodesurvival = new gamemodeSurvival();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.Gamemode)){
                if (args.length == 0){
                    player.sendMessage(ChatColor.GOLD + "Gebruik: /gm <0,1,2,3>");
                }else{
                    switch (args[1]){

                    }
                }
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
        return false;
    }
}
