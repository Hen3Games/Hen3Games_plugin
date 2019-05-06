package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class player {

    playerSetColour playersetcolour = new playerSetColour();

    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                menu(sender, command);
            }else {
                switch (args[0]){
                    case "setcolour":
                        playersetcolour.oncommand(sender, command, label, args);
                        break;
                }
            }
        }else{
         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
    }
    public void menu(CommandSender sender, Command command){
        sender.sendMessage(commandMessage(command, sender, "setcolour", Permissions.PlayerSetColour));
    }

    public String commandMessage(Command cmd, CommandSender sender, String command, String permission) {
        if (sender.hasPermission(permission)) {
            return ChatColor.GRAY + "/" + cmd.getName() + " " + ChatColor.GREEN + command;
        }
        return null;
    }
}
