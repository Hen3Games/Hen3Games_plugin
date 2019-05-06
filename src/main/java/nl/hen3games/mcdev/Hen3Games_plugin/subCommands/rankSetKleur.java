package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.Files.functions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Records;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rankSetKleur {
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 4 && player.hasPermission(Permissions.RankColour)) {
                Records.rankRec rankRecord = functions.getRankRecord(args[2]);
                if (rankRecord != null){
                    if (functions.isRankColourAlreadySet(rankRecord)){
                        functions.removeColourFormRank(rankRecord);
                        functions.setColourRank(rankRecord, args[3]);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.newColourForRank));
                    }else{
                        functions.setColourRank(rankRecord, args[3]);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.newColourForRank));
                    }
                }
            }else {
                if (!player.hasPermission(Permissions.RankColour)){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
                }
                if (args.length != 4){
                    player.sendMessage(ChatColor.RED + "/" + command + " rank setcolour <Rank> <colourcode>");
                }
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
    }
}
