package nl.hen3games.mcdev.Hen3Games_plugin.Commands;

import nl.hen3games.mcdev.Hen3Games_plugin.Mijnplugin;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly implements CommandExecutor {
    public fly(Mijnplugin mijnplugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("fly")){
            if (args.length == 0) {
                if (player.isFlying()) {

                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage(ChatColor.GOLD + "Jij kan nu niet meer vliegen");

                }else {
                    //als speler is in creative en staat op grond maar kan wel vliegen nog steeds!
                    if (player.getAllowFlight()) {

                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.sendMessage(ChatColor.GOLD + "Jij kan nu niet meer vliegen");

                    }else {

                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.sendMessage(ChatColor.GOLD + "Jij kan nu vliegen");

                    }
                }
            }else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    targetfly(target, player);
                }else {
                    player.sendMessage(ChatColor.RED + "De speler " + args[0] + " bestaat niet of is niet online!");
                }
            }else {
                player.sendMessage(ChatColor.RED + "Maak gebruik van /fly of /fly <naam>");
            }
            }else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.GOLD + "Maak gebruik van /fly <naam>");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        targetfly(target, sender);
                    } else {
                        sender.sendMessage(ChatColor.RED + "De speler " + args[0] + " bestaat niet of is niet online!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Maak gebruik van /fly <naam>");
                }
            }
        }
        return false;
    }

    public void targetfly(Player target, CommandSender sender) {
        if (target.isFlying()) {

            target.setAllowFlight(false);
            target.setFlying(false);
            target.sendMessage(ChatColor.GOLD + "Jij kan nu niet meer vliegen door " + sender.getName());

        }else {
            //als speler is in creative en staat op grond maar kan wel vliegen nog steeds!
            if (target.getAllowFlight()) {

                target.setAllowFlight(false);
                target.setFlying(false);
                target.sendMessage(ChatColor.GOLD + "Je kan nu niet meer vliegen door " + sender.getName());

            }else {

                target.setAllowFlight(true);
                target.setFlying(true);
                target.sendMessage(ChatColor.GOLD + "Jij kan nu vliegen door " + sender.getName());

            }
        }
    }

}
