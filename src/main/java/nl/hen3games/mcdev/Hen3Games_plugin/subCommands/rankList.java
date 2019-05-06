package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Records;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class rankList {

    static ArrayList<Records.rankRec> rankList = Records.rankList;

    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args[1].equalsIgnoreCase("list") && player.hasPermission(Permissions.RankList)){
                player.sendMessage(ChatColor.GRAY + "===== " + ChatColor.GOLD + "Rank" + ChatColor.GRAY + " =====");
                for (Records.rankRec rankRecord : rankList){
                    if (rankRecord.colour != null){
                        player.sendMessage(ChatColor.GRAY + "- " + ChatColor.translateAlternateColorCodes('&', rankRecord.colour) + rankRecord.name);
                    }else {
                        player.sendMessage(ChatColor.GRAY + "- " + ChatColor.GOLD + rankRecord.name);
                    }
                }
                player.sendMessage(ChatColor.GRAY + "==============");
            }else if (!player.hasPermission(Permissions.RankList)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
    }
}
