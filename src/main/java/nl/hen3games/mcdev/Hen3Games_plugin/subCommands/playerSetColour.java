package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.Files.functions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Records;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class playerSetColour {
    public void oncommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 3){
                if (player.hasPermission(Permissions.PlayerSetColour)){
                    Player tagplayer = Bukkit.getPlayer(args[2]);
                    if (tagplayer != null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.isNotAPlayer));
                    }else{
                        Records.playerRec playerRecord = functions.getPlayerRecord(tagplayer.getUniqueId().toString());
                        if (functions.isPlayerColourAlreadySet(playerRecord)){
                            functions.removeColourFormPlayer(playerRecord);
                            functions.setColourPlayer(playerRecord, args[3]);
                        }else{
                            functions.setColourPlayer(playerRecord, args[3]);
                        }
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
                }
            }else{
             player.sendMessage(ChatColor.RED + "/" + command + " player setcolour <playername> <colourcode>");
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
    }
}
