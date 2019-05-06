package nl.hen3games.mcdev.Hen3Games_plugin.Commands;

import nl.hen3games.mcdev.Hen3Games_plugin.Mijnplugin;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import nl.hen3games.mcdev.Hen3Games_plugin.subCommands.Rank;
import nl.hen3games.mcdev.Hen3Games_plugin.subCommands.player;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mod implements CommandExecutor {
    public mod(Mijnplugin mijnplugin) {
    }

    Rank rank = new Rank();
    player p = new player();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.Mod)){
                if (args.length == 0){
                    menu(sender, command);
                }else{
                    switch (args[0]){
                        case "rank":
                            rank.onCommand(sender, command, label,args);
                            break;
                        case "player":
                            p.onCommand(sender, command, label,args);
                            default:
                                menu(sender, command);
                    }
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
        return true;
    }

    public void menu(CommandSender sender, Command command){
        sender.sendMessage(commandMessage(command, sender, "rank", Permissions.Rank));
        sender.sendMessage(commandMessage(command, sender, "player", Permissions.Player));
    }

    public String commandMessage(Command cmd, CommandSender sender, String command, String permission) {
        if (sender.hasPermission(permission)) {
            return ChatColor.GRAY + "/" + cmd.getName() + " " + ChatColor.GREEN + command;
        }
        return null;
    }
}
