package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class rankHelp {
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("help") && sender.hasPermission(Permissions.Rank)){
            Rank.rankMenu(sender, command);
        }else{
            if (!sender.hasPermission(Permissions.Rank)){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }
    }
}
