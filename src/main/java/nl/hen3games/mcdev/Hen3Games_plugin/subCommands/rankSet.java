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

public class rankSet {
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 4 && player.hasPermission(Permissions.RankSet)){
                Player tagPlayer = Bukkit.getPlayer(args[3]);
                if (tagPlayer == null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.isNotAPlayer));
                }else {
                    Records.playerRec playerRecord = functions.getPlayerRecord(tagPlayer.getUniqueId().toString());
                    Records.rankRec rankRecord = functions.getRankRecord(args[2]);
                    if (functions.isPlayerInRank(playerRecord)){
                        functions.removePlayerFormRank(rankRecord, playerRecord);
                        functions.setPlayerInRank(rankRecord, playerRecord);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.newRankForPlayer) + args[2]);
                    }else if (!functions.isPlayerInRank(playerRecord)){
                        functions.setPlayerInRank(rankRecord, playerRecord);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.newRankForPlayer) + args[2]);
                    }
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
        return;
    }
}
