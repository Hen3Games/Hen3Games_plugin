package nl.hen3games.mcdev.Hen3Games_plugin.Commands;

import nl.hen3games.mcdev.Hen3Games_plugin.Mijnplugin;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flySpeed implements CommandExecutor {
    public flySpeed(Mijnplugin mijnplugin) {
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("hen3games.flyspeed")){
                if (args.length != 1){
                    if (player.isFlying()) {
                        if (args[0].equals((float) 0.1)) {
                            player.setFlySpeed((float) 0.1);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.2)){
                            player.setFlySpeed((float) 0.2);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.3)){
                            player.setFlySpeed((float) 0.3);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.4)){
                            player.setFlySpeed((float) 0.4);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.5)){
                            player.setFlySpeed((float) 0.5);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.6)){
                            player.setFlySpeed((float) 0.6);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.7)){
                            player.setFlySpeed((float) 0.7);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.8)){
                            player.setFlySpeed((float) 0.8);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 0.9)){
                            player.setFlySpeed((float) 0.9);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }else if (args[0].equals((float) 1.0)){
                            player.setFlySpeed((float) 1.0);
                            player.sendMessage(ChatColor.GOLD + "je snelheid is veranderd");
                        }
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Gebruik: /flyspeed <snelheid>");
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.NoPerms));
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.senderCmd));
        }
        return false;
    }
}
