package nl.hen3games.mcdev.Hen3Games_plugin.subCommands;

import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rank {

    rankHelp rankhelp = new rankHelp();
    rankCreate rankcreate = new rankCreate();
    rankList ranklist = new rankList();
    rankSet rankset = new rankSet();
    rankRemove rankremove = new rankRemove();
    rankSetKleur ranksetkleur = new rankSetKleur();

    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("rank")){
                if (args.length == 1 && player.hasPermission(Permissions.Rank)) {
                    rankMenu(sender, command);
                }else{
                    switch (args[1]){
                        case "help":
                            rankhelp.onCommand(sender, command, label, args);
                            break;
                        case "create":
                            rankcreate.onCommand(sender, command, label, args);
                            break;
                        case "list":
                            ranklist.onCommand(sender, command, label, args);
                            break;
                        case "set":
                            rankset.onCommand(sender, command, label, args);
                            break;
                        case "remove":
                            rankremove.onCommand(sender, command, label, args);
                            break;
                        case "setcolour":
                            ranksetkleur.onCommand(sender, command, label, args);
                        default:
                            rankMenu(sender,command);
                    }
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }
    }

    public static void rankMenu(CommandSender sender, Command command){
        sender.sendMessage(commandMessage(command, sender, "rank help", Permissions.Rank));
        sender.sendMessage(commandMessage(command, sender, "rank create <rankNaam>", Permissions.RankCreate));
        sender.sendMessage(commandMessage(command, sender, "rank list", Permissions.RankList));
        sender.sendMessage(commandMessage(command, sender, "rank remove <SpelerNaam>", Permissions.RankRemove));
        sender.sendMessage(commandMessage(command, sender, "rank set <RankNaam> <SpelerNaam>", Permissions.RankSet));
        sender.sendMessage(commandMessage(command, sender, "rank setcolour <SpelerNaam>", Permissions.RankColour));

    }

    public static String commandMessage(Command cmd, CommandSender sender, String command, String permission) {
        if (sender.hasPermission(permission)) {
            return ChatColor.GRAY + "/" + cmd.getName() + " " + ChatColor.GREEN + command;
        }
        return null;
    }
}
