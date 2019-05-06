package nl.hen3games.mcdev.Hen3Games_plugin.Library;

import nl.hen3games.mcdev.Hen3Games_plugin.Files.functions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpelerJoin implements Listener {

    @EventHandler
    public static void PlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!functions.playerAlreadyCreated(player)){
            functions.createPlayer(player);
        }

        String Join = Messages.JoinMessage.replaceAll("%player%", player.getName());
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', Join));
    }
}
