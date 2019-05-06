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

public class rankRemove {
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 3 && player.hasPermission(Permissions.RankRemove)){
                Player tagPlayer = Bukkit.getPlayer(args[2]);
                if (tagPlayer == null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.isNotAPlayer));
                }else {
                    Records.playerRec playerRecord = functions.getPlayerRecord(tagPlayer.getUniqueId().toString());
                    if (functions.isPlayerInRank(playerRecord)) {
                        functions.removePlayerFormRank(playerRecord.Rank, playerRecord);
                        player.sendMessage(ChatColor.GREEN + "Je hebt de rank succesvol verwijderd van speler: " + tagPlayer.getName());
                    }
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
    }
}
