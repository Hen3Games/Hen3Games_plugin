package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemodeSpectator {
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.Gamemode)) {
                if (args[0].equals("2")){
                    if (args.length == 1){
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GREEN + "Je Gamemode is veranderd naar spectator");
                    }else if (args.length == 2){
                        Player tagplayer = Bukkit.getPlayer(args[1]);
                        if (tagplayer == null){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.isNotAPlayer));
                        }else{
                            tagplayer.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(ChatColor.GREEN + "Je hebt de Gamemode van: " + tagplayer + " veranderd naar Gamemode spectator");
                        }
                    }
                }
            }
        }
    }
}
