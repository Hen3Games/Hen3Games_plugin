package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.Files.functions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Records;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class rankCreate {

    static ArrayList<Records.rankRec> rankList = Records.rankList;

    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args[1].equalsIgnoreCase("create") && player.hasPermission(Permissions.RankCreate)){
                if (args.length == 3){
                    for (Records.rankRec rankRecord : rankList){
                        if (rankRecord.name.toLowerCase().equals(args[2].toLowerCase())){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.ExistRank));
                            return;
                        }else{
                            functions.createRank(args[2], player);
                        }
                    }
                }else{
                    player.sendMessage(ChatColor.GRAY + "/" + ChatColor.GREEN + command + " rank create <name>");
                }
            }else if (!player.hasPermission(Permissions.RankCreate)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
    }
}
