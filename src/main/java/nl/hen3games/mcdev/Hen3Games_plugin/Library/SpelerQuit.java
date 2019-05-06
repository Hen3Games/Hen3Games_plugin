package nl.hen3games.mcdev.Hen3Games_plugin.Library;

import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpelerQuit implements Listener {

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();

        String Quit = Messages.QuitMessage.replaceAll("%player%", player.getName());
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', Quit));
    }
}
